package me.romanow.brs.javaview;


import java.awt.FileDialog;
import me.romanow.brs.Values;
import me.romanow.brs.database.DBCell;
import me.romanow.brs.interfaces.BRSException;
import me.romanow.brs.database.DBEntry;
import me.romanow.brs.database.DBItem;
import me.romanow.brs.database.DBNamedItem;
import me.romanow.brs.model.MDNote;
import me.romanow.brs.database.DBProfile;
import me.romanow.brs.database.DBRating;
import me.romanow.brs.database.DBStudent;
import me.romanow.brs.controller.ViewController;
import me.romanow.brs.controller.ViewControllerCommands;
import me.romanow.brs.controller.ViewControllerListener;
import me.romanow.brs.model.MDBaseUser;
import me.romanow.brs.model.MDCell;
import me.romanow.brs.model.MDStudent;
import me.romanow.brs.model.TableData;
import me.romanow.brs.model.TableHTML;
import me.romanow.brs.model.TablePDF;

public class ClientStudent extends ClientBase implements ViewControllerListener{  
    private DBProfile profile=new DBProfile("");
    private TableComboBox ratingList=new TableComboBox();
    private TableComboBox cellList=new TableComboBox();
    private MDNote noteItem=null;
    private DBNamedItem groupItem=null;
    private DBStudent studentItem=null;
    private ViewControllerCommands ctrl=null;
 
    
    public void fatal(Throwable ee){
        super.fatal(ee);
        BRSException e2=new BRSException(ee);
        MES.setText(e2.getMessage());
        if (e2.getCode()==BRSException.msg || e2.getCode()==BRSException.warn) return;
        if (e2.getCode()==BRSException.net || e2.getCode()==BRSException.sql || e2.getCode()==BRSException.serv){
            reConnect.setVisible(true);
            }
        ctrl.setState(0);
        }

    public ClientStudent(DBStudent studentItem0){
        this.studentItem = studentItem0;        }
    public void setBase(ClientBase base){
        super.setBase(base);
        initComponents();
        try {
            this.setBounds(300, 200, 360, 250);
            setRatingVisible(false);
            reConnect.setVisible(false);
                if (!profile.load()){
                    DBProfile entry=new DBProfile();
                    profile.save();
                    }
            ctrl=new ViewController(user,this);
            setTitle(studentItem.getName());
            ratingList=new TableComboBox(RatingList, new DBRating(),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    try {
                        MES.setText("");
                        ctrl.loadRating(item.getId());
                        ctrl.setState(1);
                        user.studentItem=(MDStudent)user.rating.groups.students.getById(studentItem.getId());
                        ctrl.setState(3);
                        user.cellItem=null;
                        cellList.setList(user.rating.course.cells.toArray());  
                        user.editEnabled=false;
                        } catch(Throwable e1){ fatal(e1); }
                    }}); 
                user.getRatingList(studentItem.getIdGroups(), 2);
                ratingList.setList(user.ratingList);
                cellList=new TableComboBox(CellList, new DBCell(),new TableChoiceListener(){
                    public void onSelect(DBItem item) {
                        try {
                            MES.setText("");
                            user.cellItem=(MDCell)item;
                            ctrl.setState(4);
                        } catch(Throwable e1){ fatal(e1); }
                    }});
            show();
            onOpen();
            }
        catch(Throwable e2){
            super.fatal(e2);
            onClose();
            }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox1 = new java.awt.Checkbox();
        jComboBox1 = new javax.swing.JComboBox();
        MES = new java.awt.TextArea();
        jLabel2 = new javax.swing.JLabel();
        RatingButton2 = new javax.swing.JButton();
        SummR = new javax.swing.JTextField();
        FTP1 = new javax.swing.JButton();
        FTP2 = new javax.swing.JButton();
        FTP3 = new javax.swing.JButton();
        FTP4 = new javax.swing.JButton();
        html2 = new javax.swing.JButton();
        pdf2 = new javax.swing.JButton();
        labelr2 = new javax.swing.JLabel();
        RatingList = new javax.swing.JComboBox();
        CellList = new javax.swing.JComboBox();
        labelr = new javax.swing.JLabel();
        labelr3 = new javax.swing.JLabel();
        reConnect = new javax.swing.JButton();

        checkbox1.setLabel("checkbox1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        MES.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(MES);
        MES.setBounds(10, 150, 330, 40);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Уч.единица");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 50, 70, 20);

        RatingButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RatingButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/table.png"))); // NOI18N
        RatingButton2.setBorder(null);
        RatingButton2.setBorderPainted(false);
        RatingButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatingButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(RatingButton2);
        RatingButton2.setBounds(10, 100, 30, 30);

        SummR.setEditable(false);
        getContentPane().add(SummR);
        SummR.setBounds(220, 170, 70, 20);

        FTP1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FTP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/synch.png"))); // NOI18N
        FTP1.setBorder(null);
        FTP1.setBorderPainted(false);
        FTP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP1ActionPerformed(evt);
            }
        });
        getContentPane().add(FTP1);
        FTP1.setBounds(140, 100, 40, 40);

        FTP2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FTP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/synch.png"))); // NOI18N
        FTP2.setBorder(null);
        FTP2.setBorderPainted(false);
        FTP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP2ActionPerformed(evt);
            }
        });
        getContentPane().add(FTP2);
        FTP2.setBounds(240, 100, 40, 40);

        FTP3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FTP3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/download.png"))); // NOI18N
        FTP3.setBorder(null);
        FTP3.setBorderPainted(false);
        FTP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP3ActionPerformed(evt);
            }
        });
        getContentPane().add(FTP3);
        FTP3.setBounds(190, 100, 40, 40);

        FTP4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FTP4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/download.png"))); // NOI18N
        FTP4.setBorder(null);
        FTP4.setBorderPainted(false);
        FTP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP4ActionPerformed(evt);
            }
        });
        getContentPane().add(FTP4);
        FTP4.setBounds(290, 100, 40, 40);

        html2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        html2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/firefox.png"))); // NOI18N
        html2.setBorder(null);
        html2.setBorderPainted(false);
        html2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                html2ActionPerformed(evt);
            }
        });
        getContentPane().add(html2);
        html2.setBounds(50, 100, 30, 33);

        pdf2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pdf2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/pdf.png"))); // NOI18N
        pdf2.setBorder(null);
        pdf2.setBorderPainted(false);
        pdf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdf2ActionPerformed(evt);
            }
        });
        getContentPane().add(pdf2);
        pdf2.setBounds(90, 100, 30, 33);

        labelr2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelr2.setText("Отчет");
        getContentPane().add(labelr2);
        labelr2.setBounds(140, 80, 60, 15);

        RatingList.setEditable(true);
        RatingList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(RatingList);
        RatingList.setBounds(70, 20, 250, 21);

        CellList.setEditable(true);
        CellList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(CellList);
        CellList.setBounds(140, 50, 180, 21);

        labelr.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelr.setText("Рейтинг");
        getContentPane().add(labelr);
        labelr.setBounds(10, 80, 50, 15);

        labelr3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelr3.setText("Исходник");
        getContentPane().add(labelr3);
        labelr3.setBounds(240, 80, 60, 15);

        reConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/status_red.png"))); // NOI18N
        reConnect.setBorder(null);
        reConnect.setBorderPainted(false);
        reConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reConnectActionPerformed(evt);
            }
        });
        getContentPane().add(reConnect);
        reConnect.setBounds(10, 20, 30, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        onClose();
    }//GEN-LAST:event_formWindowClosing

    private void RatingButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatingButton2ActionPerformed
        ctrl.createRatingStudentTable(TableWindow.class);
    }//GEN-LAST:event_RatingButton2ActionPerformed

    private void FTP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP1ActionPerformed
        try {
            MES.setText("");
            final String ss=ctrl.getWriteFileName(true);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    MES.setText("Файл "+(String)answer+" сохранен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.writeFile(ss, true);
                    }
                });

        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP1ActionPerformed

    private void FTP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP2ActionPerformed
        try {
            MES.setText("");
            final String ss=ctrl.getWriteFileName(false);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    MES.setText("Файл "+(String)answer+" сохранен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.writeFile(ss, false);
                    }
                });

        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP2ActionPerformed

    private void FTP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP3ActionPerformed
        try {
            MES.setText("");
            final String ss=ctrl.getReadFileName(true);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    MES.setText("Файл "+(String)answer+" загружен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.readFile(ss, true);
                    }
                });
        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP3ActionPerformed

    private void FTP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP4ActionPerformed
        try {
            MES.setText("");
            final String ss=ctrl.getReadFileName(false);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    MES.setText("Файл "+(String)answer+" загружен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.readFile(ss, false);
                    }
                });

        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP4ActionPerformed

    private void html2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_html2ActionPerformed
        ctrl.createRatingStudentTable(TableHTML.class);
    }//GEN-LAST:event_html2ActionPerformed

    private void pdf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdf2ActionPerformed
        ctrl.createRatingStudentTable(TablePDF.class);
    }//GEN-LAST:event_pdf2ActionPerformed

    private void reConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reConnectActionPerformed
        reConnect.setVisible(false);
        try {
        MES.setText("");
        user.reconnect();
        } catch(Throwable e1){ fatal(e1); }
    }//GEN-LAST:event_reConnectActionPerformed
    
    public static void main(String args[]) {
         runMain(ClientStudent.class);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CellList;
    private javax.swing.JButton FTP1;
    private javax.swing.JButton FTP2;
    private javax.swing.JButton FTP3;
    private javax.swing.JButton FTP4;
    private java.awt.TextArea MES;
    private javax.swing.JButton RatingButton2;
    private javax.swing.JComboBox RatingList;
    private javax.swing.JTextField SummR;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JButton html2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelr;
    private javax.swing.JLabel labelr2;
    private javax.swing.JLabel labelr3;
    private javax.swing.JButton pdf2;
    private javax.swing.JButton reConnect;
    // End of variables declaration//GEN-END:variables

   
    @Override
    public String getInputFileName(String title, String defName) throws Throwable {
        FileDialog dlg=new FileDialog(this,title,FileDialog.LOAD);
        dlg.setFile(defName);
        dlg.show();
        String fname=dlg.getDirectory();
        if (fname==null) return null;
        fname+="/"+dlg.getFile();
        return fname;
        }
    @Override
    public String getOutputFileName(String title, String defName) throws Throwable {
        FileDialog dlg=new FileDialog(this,title,FileDialog.SAVE);
        dlg.setFile(defName);
        dlg.show();
        String fname=dlg.getDirectory();
        if (fname==null) return null;
        fname+="/"+dlg.getFile();
        return fname;
        }

    @Override
    public void stateChanged(int state) {
        }
    public void setRatingVisible(boolean enb) {
        FTP1.setVisible(false);
        FTP2.setVisible(false);   
        FTP3.setVisible(false);
        FTP4.setVisible(false);
        RatingButton2.setVisible(enb);
        labelr.setVisible(enb);
        labelr3.setVisible(enb);
        labelr2.setVisible(enb);
        html2.setVisible(enb);
        pdf2.setVisible(enb);
        if (!enb){
            ratingList.set0();
            cellList.set0();
            if (user!=null) {
                user.clear();
                user.editEnabled=false;
                }
            }
        }
    @Override
    public void setCellVisible(boolean enb) {
        FTP1.setVisible(enb);
        FTP2.setVisible(enb);   
        labelr2.setVisible(enb);
        labelr3.setVisible(enb);
        }
    @Override
    public void setStudentVisible(boolean enb) {
        }
    @Override
    public void setNoteVisible(boolean enb) {
        }
    @Override
    public void setWeekVisible(boolean enb, String s) {
        }
    @Override
    public void setWeek2Visible(boolean enb, String s) {
        }
    @Override
    public void setBrigadeVisible(boolean enb, String s,boolean enb2, boolean two) {
        }
    @Override
    public void setArchFileVisible(boolean enb) {
        FTP4.setVisible(enb);        }
    @Override
    public void setDocFileVisible(boolean enb) {
        FTP3.setVisible(enb);        
        }
    @Override
    public void setMaxBall(String var) {
        }
    @Override
    public void setBall(String var, boolean editable) {
        }
    @Override
    public void setSumBall(String var) {
        }
    @Override
    public void setVariant(String var) {
        }
    @Override
    public void setWeek0(String var) {
        }
    @Override
    public void setWeek(String var, boolean editable) {
        }
    @Override
    public void setBrigade(String var) {
        }
    @Override
    public void setParamsVisible(boolean bb,String var, int pars) {
        }
    @Override
    public void selectTableCell(TableData tbl, int row, int col) {
        }
    @Override
    public void onTableClose(TableData tbl) {
        }
    @Override
    public void ratingIsChanged() {
        }
    @Override
    public void setMessage(String message, int state) {
        MES.setText(message);
        }
    public void setFileCount(int count) {
        }
    @Override
    public String getFileDirectory() {
        return Values.desktopFileDirectory;
        }
    @Override
    public void lastCallBack() {
        }    
 }
