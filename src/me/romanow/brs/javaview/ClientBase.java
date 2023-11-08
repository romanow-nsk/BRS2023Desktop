/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

import static java.lang.Thread.NORM_PRIORITY;
import java.lang.Thread.UncaughtExceptionHandler;
import javax.swing.JFrame;
import me.romanow.brs.DateTime;
import me.romanow.brs.connect.DBConnection;
import me.romanow.brs.database.DBLogFile;
import me.romanow.brs.database.DBNamedItem;
import me.romanow.brs.database.DBProfile;
import me.romanow.brs.interfaces.BRSException;
import me.romanow.brs.model.MDBaseUser;

/**
 *
 * @author user
 */
public abstract class ClientBase extends JFrame{
    public boolean debug = false;
    public boolean cash=false;
    public MDBaseUser user=null;
    public DBProfile entry=new DBProfile();
    public boolean admin=false;
    public DBConnection baseConnect=null;       // Соединение с bases
    public ClientBase parent=null;
    public ClientBase child=null;
    private boolean busy=false;                 // Занят фоновым процессом
    public synchronized boolean isBusy() {
        return busy;
        }
    public synchronized void setBusy(boolean busy) {
        this.busy = busy;
        }
    //--------------------------------------------------------------------------
    public interface CallBack {
        public void onFinish(Object answer) throws Throwable;
        public void onError(Throwable ee);
        public Object call() throws Throwable;
        }
    public class BackGround extends Thread{
    private CallBack code;
    public BackGround(CallBack codex){
        code=codex;
        setPriority(NORM_PRIORITY-2);
        start();
        }
    public void run(){
	    try {
            setBusy(true);
            final Object oo=code.call();
            java.awt.EventQueue.invokeLater(new Runnable(){
                public void run(){
                    try {
                        setBusy(false);
                        code.onFinish(oo);
                        }
                    catch (Throwable ee) { 
                        setBusy(false);
                        code.onError(ee);
                        }
                    }
                });
            } catch (final Throwable ee2) { 
                setBusy(false);
                java.awt.EventQueue.invokeLater(new Runnable(){
                    public void run(){
                        code.onError(ee2);
                        }
                    });
	        	}
		}  
    }
    //--------------------------------------------------------------------------
    public void closeChilds(){
        if (child != null)
            child.closeChilds();
        dispose();        
        }
    public void onClose(){
        closeChilds();
        if (parent!=null){
            parent.setVisible(true);
            }
        else
            System.exit(0);
        }
    
    public void onOpen(){
        if (parent!=null){
            parent.setVisible(false);
            }
        setVisible(true);
        }
    
    public ClientBase(){
        }
    
    public void setBase(ClientBase parent){
        this.parent = parent;
        String zz="Рейтинг-калькулятор";
        if (parent!=null){
            user = parent.user;
            entry = parent.entry;
            admin = parent.admin;
            baseConnect = parent.baseConnect;
            cash = parent.cash;
            if (user!=null){
                zz="";
                if (user.tutor!=null) zz+=" ("+user.tutor.getName()+")";
                zz+=" "+user.entry.getIP()+" "+user.entry.getName();
                if (cash) zz+=" (автономно)";
                }
            }
        setTitle(zz);
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                fatal(e);
                }
            });    
        }    
    
    public void onErrorMessage(String message){
        }
    
    public void fatal(Throwable ee){
        String ms = ee.toString();
        if (baseConnect!=null)
            ms = "Ошибка передана на сервер";
        onErrorMessage(ms);
        if (parent!=null)
            parent.onErrorMessage(ms);
        if (baseConnect!=null){
            DBLogFile mes = new DBLogFile();
            BRSException e2=new BRSException(ee);
            if (!e2.isProgrammBug())
                return;
            DateTime cc = new DateTime(true);
            mes.setApplication(this.getClass().getSimpleName());
            mes.setCDate(cc.onlyDate());
            mes.setCTime(cc.onlyTime2());
            mes.setType(e2.getCode());
            mes.setdBase(entry.getIP()+":"+entry.getDbName());
            mes.setLogin(user.tutor.getName());
            String ss=e2.getMessage()+"\n";
            StackTraceElement dd[] = ee.getStackTrace();
            for(int i=0;i<dd.length && i<4; i++){
                ss += dd[i].getClassName() + "." + dd[i].getMethodName() + ":" + dd[i].getLineNumber() + "\n";
                }
            mes.setData(ss);
            try {
                baseConnect.insert(mes);
                } catch(Throwable e3){
                    System.out.println(e3.toString());
                    }
            }
        }
    
    public void newClient(final Class client){
        try {
            new Thread(){ 
                public void run() { 
                    try {
                        ClientBase next = (ClientBase)client.newInstance();
                        child = next;
                        next.setBase(ClientBase.this); 
                        } catch(Throwable ee){ 
                            fatal(ee);
                            onErrorMessage(ee.getMessage());
                            } 
                }}.start();
            } catch(Throwable ee){ 
                fatal(ee);
                onErrorMessage(ee.getMessage());
                } 

        }
    //--------------------------------------------------------------------------
    public static void setUIManager(String name){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (name.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    }
                }
            } catch (Throwable ex) {} 
        }
    public static void showUIManager(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                }
            } catch (Throwable ex) {} 
        }
    public static void runMain(final Class runClass){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    setUIManager("Windows Classic");
                    ((ClientBase)runClass.newInstance()).setBase(null);;
                    } catch(Throwable ee){
                        System.out.println(ee.toString());
                        }
                }
            });
        }
    }
