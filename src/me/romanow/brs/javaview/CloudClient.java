package me.romanow.brs.javaview;


import me.romanow.brs.Values;
import me.romanow.brs.ciu.*;
import me.romanow.brs.connect.DBConnection;
import me.romanow.brs.database.DBBases;
import me.romanow.brs.database.DBEntry;
import me.romanow.brs.database.DBItem;
import me.romanow.brs.database.DBProfile;
import me.romanow.brs.database.DBStudent;
import me.romanow.brs.database.DBTutor;
import me.romanow.brs.interfaces.BRSException;
import me.romanow.brs.model.MDBaseUserBinFile;
import me.romanow.brs.model.MDBaseUserSQL;

public class CloudClient extends ClientBase {   
    private DBTutor tutorItem=null;
    private DBStudent studentItem=null;
    private DBBases base=null;
    private TableComboBox dbaseList=null;
    private DBItem admins[]=null;
    protected String cloudIP=Values.dbNstuCloudIP;
    private MDBaseUserBinFile localFileList=null;
    
    @Override
    public void onErrorMessage(String message) {
        MES.setText(message);
        }
    
    public void initLocalDB() throws Throwable{
        cash=true;
        baseConnect = null;
        MES.setText("Нет соединения с БД - автономно (преподаватель)");
        localFileList = new MDBaseUserBinFile(Values.desktopFileDirectory);
        localFileList.entry = entry;
        dbaseList.setList(localFileList.getFileBaseList());
        admins = new DBItem[0];
        }    
    public void initDB() throws Throwable{
        cash=false;
        baseConnect = new DBConnection();
        entry.setIP(cloudIP);
        entry.setDbName("bases");
        try {
            baseConnect.connect(entry);
            dbaseList.setList(baseConnect.getList(DBBases.class));
            admins = baseConnect.getList(DBTutor.class);
            } catch(Throwable ee){
                initLocalDB();
                }
        }
    public CloudClient(){
        }
    public void setBase(ClientBase pp){
        super.setBase(pp);
        initComponents();
        NAME.setNextFocusableComponent(PW);
        this.setBounds(300, 300, 380, 210);
        entry = new DBProfile();
        try {
            if (!entry.load()){
                entry.save();
                }
            dbaseList=new TableComboBox(DBase, new DBEntry(),new TableChoiceListener(){
            public void onSelect(DBItem item) {
                try {
                    base=(DBBases)item;
                    } catch(Throwable e2){ 
                        CloudClient.super.fatal(e2);
                        MES.setText(e2.toString()); 
                        }
                }}); 
            initDB();
            if (debug){ 
                NAME.setText("123");
                PW.setText("31415926");
                dbaseList.setPosition(2);
                }
            }
        catch(Throwable e2){ 
            CloudClient.super.fatal(e2);
            onClose();
            }
        show();
        onOpen();
    }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox1 = new java.awt.Checkbox();
        PW = new javax.swing.JPasswordField();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        DBase = new javax.swing.JComboBox();
        MES = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        NAME = new javax.swing.JTextField();

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
        PW.setBounds(60, 80, 200, 21);

        label1.setText("БД");
        getContentPane().add(label1);
        label1.setBounds(20, 20, 20, 14);

        label2.setText("Логин");
        getContentPane().add(label2);
        label2.setBounds(10, 50, 40, 14);

        label4.setText("Пароль");
        getContentPane().add(label4);
        label4.setBounds(10, 80, 50, 14);

        DBase.setEditable(true);
        DBase.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DBase.setName("DBASE"); // NOI18N
        getContentPane().add(DBase);
        DBase.setBounds(60, 20, 200, 21);

        MES.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(MES);
        MES.setBounds(10, 130, 350, 21);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/network.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(270, 20, 40, 40);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/ic_launcher.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(270, 70, 30, 40);

        NAME.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(NAME);
        NAME.setBounds(60, 50, 200, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    void logonTutor() throws Throwable{
        user=new MDBaseUserSQL(tutorItem,entry,new DBConnection());
        user.connect();
        user.tutor=tutorItem;
        newClient(ClientOn.class);
        }
    
    boolean logonStudent(CIUPersonList persons) throws Throwable{
        CIUPerson oo = persons.getStudent();
        if (oo == null)
            return false;
        DBConnection conn2 = new DBConnection();
        String name2=(oo.FAMILY_NAME+" "+oo.NAME).toUpperCase();
        name2=name2.toUpperCase().trim();
        DBItem xx[] = conn2.getList(DBStudent.class);
        studentItem = null;
        for(int i=0;i<xx.length;i++){
            String ss = ((DBStudent)xx[i]).getName().toUpperCase();
            int idx = ss.lastIndexOf(" ");
            if (idx!=-1)
                ss = ss.substring(0,idx-1);
            ss =ss.replace(" ", "");
            if (name2.equals(ss)){
                studentItem=(DBStudent)xx[i];
                break;
            }
        }
        if (studentItem == null){
            MES.setText("Студент не найден в БД");
            conn2.close();
            return false;
            }
        if (studentItem.getCiuLogin().length()==0){
            studentItem.setCiuLogin(oo.FAMILY_NAME+" "+oo.NAME);
            conn2.update(studentItem);
            }
         //---------- Вызов студента
         //tutorItem = new DBTutor();
         //tutorItem.setAdmin(false);
         //tutorItem.setName(studentItem.getName());
         //tutorItem.setPass("");
         user=new MDBaseUserSQL(null,entry,new DBConnection());
         user.connect();
         //user.tutor = tutorItem;
         new Thread(){
                public void run() {
             try {
                 new ClientStudent(studentItem).setBase(CloudClient.this);
                 } catch(Throwable ee){ MES.setText(ee.getMessage());}
                }}.start();
        return true;
        }

    void logon(){
         if (dbaseList.getSelectedId()==-1){
             MES.setText("Не выбрана БД");
             return;
             }
         entry.setDbName(base.getDbName());
         entry.setName(base.getName());
         if (cash){
             tutorItem = new DBTutor();
             tutorItem.setAdmin(false);
             tutorItem.setName(NAME.getText());
             tutorItem.setPass(PW.getText());
             localFileList.entry = entry;
             localFileList.tutor = tutorItem;
             user=localFileList;
             newClient(ClientOn.class);
             return;
             }
         String name = NAME.getText();
         String pass = PW.getText();
         PW.setText("");
         String errMessage="";
         CIUPersonList persons = new CIUPersonList();
         try {
             CIUUniConnection ciu =  new CIUUniConnection();
             String zz =ciu.getToken(name,pass,super.entry);
             System.out.println(zz);
             persons = ciu.getPerson(zz,super.entry);
             } catch (Throwable ee){
                 if (ee instanceof BRSException)
                     errMessage = "ЦИТ: "+ee.getMessage();
                 else
                     errMessage = "Данные ЦИТ недоступны";
                 }
         try {
             if (logonStudent(persons))
                return;
             DBConnection conn2 = new DBConnection();
             conn2.connect(entry);
             CIUPerson oo = persons.getTutor();
             if (name.equals(Values.dbUser) && pass.equals(Values.dbPass)){
                 tutorItem = new DBTutor();
                 tutorItem.setAdmin(true);
                 tutorItem.setName(name);
                 tutorItem.setPass(pass);
                 logonTutor();
                 }
             else{
                 DBItem xx[] = conn2.getByName(DBTutor.class, name);
                 if (xx.length != 1) {
                    MES.setText("Преподаватель не найден в БД");
                    conn2.close();
                    return;
                    }
                tutorItem = (DBTutor) xx[0];
                if (oo != null) {
                    if (tutorItem.getCiuName().length()==0) {
                        tutorItem.setCiuName(oo.FAMILY_NAME + " " + oo.NAME);
                        conn2.update(tutorItem);
                        }
                    }
                else{
                    if (tutorItem.getPass().length() == 0){
                        MES.setText(errMessage+" Нет локального пароля");
                        conn2.close();
                        return;
                        }
                    if (!tutorItem.getPass().equals(pass)){
                        MES.setText("Неправильный локальный пароль");
                        conn2.close();
                        return;
                        }
                    }
                MES.setText(tutorItem.getCiuName());
                logonTutor();
                return;
                }
            }
        catch(Throwable e1){ 
            MES.setText(e1.toString()); 
            CloudClient.super.fatal(e1);
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
            public void onSelect(DBProfile xx) {
                try {
                    xx.save();
                    initDB();
                    MES.setText("Подключение выполнено");
                } catch(Throwable ee){ 
                    MES.setText(ee.getMessage()); 
                    CloudClient.super.fatal(ee);
                    }
            }});
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        logon();
    }//GEN-LAST:event_jButton2ActionPerformed
    
    public static void main(String args[]) {
        runMain(CloudClient.class);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DBase;
    private javax.swing.JTextField MES;
    private javax.swing.JTextField NAME;
    private javax.swing.JPasswordField PW;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label4;
    // End of variables declaration//GEN-END:variables
    
}
