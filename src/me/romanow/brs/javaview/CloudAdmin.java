package me.romanow.brs.javaview;


import me.romanow.brs.connect.DBConnection;
import me.romanow.brs.database.DBBases;
import me.romanow.brs.database.DBCreator;
import me.romanow.brs.database.DBEntry;
import me.romanow.brs.database.DBItem;
import me.romanow.brs.database.DBLogFile;
import me.romanow.brs.database.DBProfile;
import me.romanow.brs.database.DBStudent;
import me.romanow.brs.database.DBTutor;
import me.romanow.brs.interfaces.DBConnect;
import me.romanow.brs.model.MDBaseUserSQL;

public class CloudAdmin extends ClientBase {
    
    private DBTutor tutorItem=null;
    private DBStudent studentItem=null;
    private DBBases base=null;
    private TableComboBox dbaseList=null;
    private DBItem admins[]=null;
    private DBItem logRecords[]=null;
    private int logIdx=0;
    
    @Override
    public void onErrorMessage(String message) {
        MES.setText(message);
        }
    public void initDB() throws Throwable{
        baseConnect = new DBConnection();
        entry.setDbName("bases");
        baseConnect.connect(entry);
        dbaseList.setList(baseConnect.getList(DBBases.class));
        admins = baseConnect.getList(DBTutor.class);
        logRecords = baseConnect.getList(DBLogFile.class);
        logIdx=logRecords.length-1;
        }
    public CloudAdmin(){      
        }
    public void setBase(ClientBase pp) {
        super.setBase(pp);
        initComponents();
        bAdmin.setVisible(false);
        bEdit.setVisible(false);
        bCreate.setVisible(false);
        bDrop.setVisible(false);
        this.setBounds(300, 300, 800, 300);
        try {
            if (!entry.load()){
                entry.save();
                }
            MES.setText("Подключитесь к серверу: "+entry.getIP());
            tutorItem = new DBTutor();
            tutorItem.setName("Admin");
            tutorItem.setAdmin(true);
            admin = true;
            baseConnect = null;
            dbaseList=new TableComboBox(DBase, new DBEntry(),new TableChoiceListener(){
            public void onSelect(DBItem item) {
                try {
                    base=(DBBases)item;
                    } catch(Throwable e2){ 
                        CloudAdmin.super.fatal(e2);
                        MES.setText(e2.toString()); 
                        }
                }}); 
            //initDB();
            }
        catch(Throwable e2){ 
            CloudAdmin.super.fatal(e2);
            onClose();
            }
        show();
        onOpen();
    }
    
    public void showLog(){
        if (logIdx==-1)
            return;
        LOG.setText(logRecords[logIdx].toString()+"\n");
        LOGNUM.setText(""+(logRecords.length-logIdx));
        }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox1 = new java.awt.Checkbox();
        PW = new javax.swing.JPasswordField();
        label2 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        DBase = new javax.swing.JComboBox();
        MES = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        NAME = new javax.swing.JTextField();
        bDrop = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        bAdmin = new javax.swing.JButton();
        bCreate = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        LOGNUM = new javax.swing.JTextField();
        LOG = new java.awt.TextArea();

        checkbox1.setLabel("checkbox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        PW.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        PW.setName("PASS"); // NOI18N
        PW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PWActionPerformed(evt);
            }
        });
        getContentPane().add(PW);
        PW.setBounds(60, 60, 200, 21);

        label2.setText("Логин");
        getContentPane().add(label2);
        label2.setBounds(10, 20, 40, 14);

        label4.setText("Пароль");
        getContentPane().add(label4);
        label4.setBounds(10, 60, 50, 14);

        DBase.setEditable(true);
        DBase.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DBase.setName("DBASE"); // NOI18N
        getContentPane().add(DBase);
        DBase.setBounds(60, 100, 200, 21);

        MES.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(MES);
        MES.setBounds(20, 210, 570, 21);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/network.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(270, 100, 40, 40);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/ic_launcher.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(270, 20, 40, 40);

        NAME.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(NAME);
        NAME.setBounds(60, 20, 200, 21);

        bDrop.setText("Удалить");
        bDrop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDropActionPerformed(evt);
            }
        });
        getContentPane().add(bDrop);
        bDrop.setBounds(180, 130, 80, 23);

        bEdit.setText("Редактор");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });
        getContentPane().add(bEdit);
        bEdit.setBounds(20, 130, 90, 23);

        bAdmin.setText("Админ БД");
        bAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAdminActionPerformed(evt);
            }
        });
        getContentPane().add(bAdmin);
        bAdmin.setBounds(20, 170, 90, 23);

        bCreate.setText("Создать");
        bCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCreateActionPerformed(evt);
            }
        });
        getContentPane().add(bCreate);
        bCreate.setBounds(180, 170, 80, 23);

        label3.setText("БД");
        getContentPane().add(label3);
        label3.setBounds(20, 100, 20, 14);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(270, 150, 40, 40);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/right.PNG"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(700, 200, 30, 30);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(740, 200, 30, 30);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/left.PNG"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(600, 200, 30, 30);

        LOGNUM.setEnabled(false);
        getContentPane().add(LOGNUM);
        LOGNUM.setBounds(640, 200, 50, 30);
        getContentPane().add(LOG);
        LOG.setBounds(330, 20, 450, 170);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void logon(){
        try {
            String name = NAME.getText();
            String pass = PW.getText();
            MES.setText("");
            if (debug){
                name = "romanow";
                pass = "schwanensee";
                }
            if (admins.length!=0){
                DBTutor adm = (DBTutor)admins[0];
                if (name.equals(adm.getName())){
                    if (!pass.equals(adm.getPass())){
                        MES.setText("Неправильный пароль");
                        return;
                        }
                    bAdmin.setVisible(true);
                    bEdit.setVisible(true);
                    bCreate.setVisible(true);
                    bDrop.setVisible(true);
                    showLog();
                    return;
                    }
                }
        } catch(Throwable ee){
            MES.setText(ee.toString());
            }
        }
    
   
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        onClose();
    }//GEN-LAST:event_formWindowClosing
    
    private void PWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PWActionPerformed
        logon();
    }//GEN-LAST:event_PWActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MES.setText("");
        new ProfileWindow(entry,new ProfileListener(){
            @Override
            public void onSelect(DBProfile item) {
                try {
                    item.save();
                    initDB();
                    MES.setText("Подключение к серверу: "+entry.getIP());                    
                } catch (Throwable ex) {
                    MES.setText(ex.toString());
                    }
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (baseConnect == null){
            MES.setText("Подключитесь к серверу: "+entry.getIP());
            return;
            }
        logon();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAdminActionPerformed
        if (base==null){
            MES.setText("Выберите БД");
            return;
            }
        try{ 
            entry.setDbName(base.getDbName()); 
            entry.setName(base.getName());
            user=new MDBaseUserSQL(tutorItem,entry,new DBConnection());
            user.connect();
            user.tutor=tutorItem;
            newClient(AdminWindow.class);
            } catch(Throwable ee){
                MES.setText(ee.toString()); 
                }
    }//GEN-LAST:event_bAdminActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        if (base==null){
            MES.setText("Выберите БД");
            return;
            }
        try{ 
            entry.setDbName(base.getDbName()); 
            entry.setName(base.getName());
            user=new MDBaseUserSQL(tutorItem,entry,new DBConnection());
            user.connect();
            user.tutor=tutorItem;
            newClient(ClientOn.class);
            } catch(Throwable ee){
                MES.setText(ee.toString()); 
                }
    }//GEN-LAST:event_bEditActionPerformed

    private void bCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCreateActionPerformed
        final DBBases bb = new DBBases();
        new CloudBaseDialog(bb,new ValueListener(){
            @Override
            public void onSelect(String value) {
                try{
                String sql="CREATE DATABASE IF NOT EXISTS "+bb.getDbName()+" CHARACTER SET utf8;";
                baseConnect.execSQL(sql);
                baseConnect.insert(bb);
                entry.setDbName(bb.getDbName()); 
                DBConnect conn3 = new DBConnection();
                conn3.connect(entry);
                new DBCreator().DBCreate(conn3,true);       // Создать новый идентификатор
                DBTutor tut = new DBTutor();
                tut.setName(value);
                tut.setAdmin(true);
                conn3.insert(tut);
                conn3.close();
                dbaseList.setList(baseConnect.getList(DBBases.class));
                MES.setText("БД создана");
            } catch(Throwable ee){
                MES.setText(ee.toString());
                }                }
            @Override
            public void onError(String value) {
                }
            });
    }//GEN-LAST:event_bCreateActionPerformed

    private void bDropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDropActionPerformed
        if (base==null){
            MES.setText("Выберите БД");
            return;
            }
        new YesNoDialog(this, "Удалить: "+base.getName(), new YesNoListener(){
            @Override
            public void onYes() {
                try {
                    String sql="DROP DATABASE "+base.getDbName()+";";
                    baseConnect.execSQL(sql);
                    baseConnect.delete(DBBases.class, base.getId());
                    dbaseList.setList(baseConnect.getList(DBBases.class));
                    MES.setText("БД удалена");
                    } catch(Throwable ee){
                        MES.setText(ee.toString());
                        }           
                }
            @Override
            public void onNo() {
                }
            });
    }//GEN-LAST:event_bDropActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            initDB();
            MES.setText("Подключение выполнено");        
            } catch (Throwable ex) {
                MES.setText(ex.toString());
                }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (logIdx==-1 || logIdx >= logRecords.length-1)
            return;
        logIdx++;
        showLog();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (logIdx <= 0)
            return;
        logIdx--;
        showLog();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (logIdx < 0 || logRecords.length==0)
            return;
        try {
            baseConnect.delete(DBLogFile.class, ((DBLogFile)logRecords[logIdx]).getId());
            logRecords = baseConnect.getList(DBLogFile.class);
            if (logIdx >0 )
                logIdx--;
            showLog();
            } catch (Throwable ex) {
                MES.setText(ex.toString());
                }
    }//GEN-LAST:event_jButton5ActionPerformed
    
    public static void main(String args[]) {
        runMain(CloudAdmin.class);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DBase;
    private java.awt.TextArea LOG;
    private javax.swing.JTextField LOGNUM;
    private javax.swing.JTextField MES;
    private javax.swing.JTextField NAME;
    private javax.swing.JPasswordField PW;
    private javax.swing.JButton bAdmin;
    private javax.swing.JButton bCreate;
    private javax.swing.JButton bDrop;
    private javax.swing.JButton bEdit;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    // End of variables declaration//GEN-END:variables
    
}
