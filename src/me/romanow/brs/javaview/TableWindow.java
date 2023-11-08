/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import me.romanow.brs.model.TableData;
import me.romanow.brs.interfaces.TableCallBack;
import me.romanow.view.ScrollTableView;

/**
 *
 * @author user
 */
public class TableWindow extends TableData{
    int cellSize=25;
    int rCnt=15;
    int cCnt=20;
    int base=15;
    TableCallBack back=null;
    ScrollTableView xx=null;
    private JFrame jf=null;
    public TableWindow() { super(); }
    @Override
    public String getExtention(){ return null; }    
    @Override   
    public void createTable(File dst, TableCallBack back0) throws Exception{
        back=back0;
        new Thread(rr).start();
        }    
    //--------------------------------------------------------------------------
    Runnable rr=new Runnable(){    
        public void run(){
        jf=new JFrame();
        jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jf.addWindowListener(new WindowListener(){
            public void windowOpened(WindowEvent e) {}
            public void windowClosing(WindowEvent e) {
                back.onClose();
                }
            public void windowClosed(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
            });
        jf.getContentPane().setLayout(null);
        jf.pack();
        jf.setTitle(title);
        xx=new ScrollTableView(cellSize,rCnt,cCnt,new TableCallBack(){
            @Override
            public void rowSelected(int row) {}
            @Override
            public void colSelected(int col) {}
            @Override
            public void cellSelected(int row, int col) {
                if (back==null || col==0 || col>=ncol-3 || row==nrow-1) return;
                jf.setState(JFrame.ICONIFIED); 
                back.cellSelected(row, col);
                }
            @Override
            public void onClose() {
                jf.dispose();
                back.onClose();
                }
            });
        Dimension dd=xx.setData(rows, cols, data, select);
        jf.add(xx);
        jf.setBounds(300,50,dd.width+base+50,dd.height+base+75);
        xx.setBounds(base, base, dd.width,dd.height);
        if (bottom.length()!=0){
            JLabel l1=new JLabel(bottom);
            l1.setBounds(base, dd.width+base+40, 200, 20);
            l1.setFont(new Font("Arial",0,12));
            jf.add(l1);
            }
        if (bottom2.length()!=0){
            JLabel l1=new JLabel(bottom2);
            l1.setBounds(base, dd.width+base+60, 200, 20);
            l1.setFont(new Font("Arial",0,12));
            jf.add(l1);
            }
        jf.setVisible(true);
        jf.show();
        }};
    //--------------------------------------------------------------------------
    public void closeTable(){
        if (jf!=null) {
            jf.dispose();
            back.onClose();
            }
        } 
    public void refreshTable(){  
        xx.refreshData(data, select);
        xx.repaint(); 
        jf.setState(JFrame.NORMAL); 
        }
}
