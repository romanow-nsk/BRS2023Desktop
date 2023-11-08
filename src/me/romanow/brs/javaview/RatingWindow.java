
package me.romanow.brs.javaview;

import me.romanow.brs.database.DBCourse;
import me.romanow.brs.database.DBGroups;
import me.romanow.brs.database.DBItem;
import me.romanow.brs.database.DBParams;

public class RatingWindow extends javax.swing.JFrame {

    private AdminWindow parent=null;
    private TableComboBox groupList=new TableComboBox();
    private TableComboBox courseList=new TableComboBox();
    private TableComboBox paramList=new TableComboBox();
   
    public RatingWindow(AdminWindow parent0) throws Throwable {
        initComponents();
        setTitle("Рейтинги");
        this.parent = parent0;
        setBounds(250, 220, 360, 210);
        parent.studentItem=null;
        groupList=new TableComboBox(GroupList, new DBGroups(),new TableChoiceListener(){
        public void onSelect(DBItem item) {
            parent.groupItem = (DBGroups)item;
            groupList.copyPosition(parent.groupList);
            }
        });
        groupList.setList(parent.conn.getList(DBGroups.class));       
        courseList=new TableComboBox(CourseList, new DBCourse(),new TableChoiceListener(){
        public void onSelect(DBItem item) {
            parent.courseItem = (DBCourse)item;
            courseList.copyPosition(parent.courseList);
            }
        });
        courseList.setList(parent.conn.getList(DBCourse.class));    
        paramList=new TableComboBox(ParamList, new DBParams(),new TableChoiceListener(){
        public void onSelect(DBItem item) {
            parent.paramItem = (DBParams)item;
            paramList.copyPosition(parent.paramsList);
            }
        });
        paramList.setList(parent.params);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button2 = new javax.swing.JButton();
        ParamList = new javax.swing.JComboBox();
        GroupList = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Groups2 = new java.awt.Checkbox();
        jLabel3 = new javax.swing.JLabel();
        CourseList = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        button2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        button2.setBorder(null);
        button2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button2MouseClicked(evt);
            }
        });
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        getContentPane().add(button2);
        button2.setBounds(260, 70, 30, 30);

        ParamList.setEditable(true);
        ParamList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(ParamList);
        ParamList.setBounds(20, 130, 220, 21);

        GroupList.setEditable(true);
        GroupList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(GroupList);
        GroupList.setBounds(20, 30, 220, 21);

        jLabel1.setText("Параметры рейтинга");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 110, 120, 14);

        jLabel2.setText("Группа");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 10, 70, 14);

        Groups2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Groups2.setLabel("2 подгруппы");
        Groups2.setName(""); // NOI18N
        Groups2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Groups2MouseClicked(evt);
            }
        });
        Groups2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Groups2ItemStateChanged(evt);
            }
        });
        getContentPane().add(Groups2);
        Groups2.setBounds(260, 30, 96, 19);

        jLabel3.setText("Предмет");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 60, 70, 14);

        CourseList.setEditable(true);
        CourseList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(CourseList);
        CourseList.setBounds(20, 80, 220, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button2MouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Добавить рейтинг");
        }
    }//GEN-LAST:event_button2MouseClicked

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        if (parent.groupItem==null){
            parent.onErrorMessage("Не выбрана группа");
            return;
            }
        if (parent.courseItem==null){
            parent.onErrorMessage("Не выбрана дисциплина");
            return;
            }
        if (parent.paramItem==null){
            parent.onErrorMessage("Не выбраны параметры рейтинга");
            return;
            }
        parent.addRating();
        //dispose();
    }//GEN-LAST:event_button2ActionPerformed

    private void Groups2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Groups2MouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("1 или 2 подгруппы");
        }
    }//GEN-LAST:event_Groups2MouseClicked

    private void Groups2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Groups2ItemStateChanged
        parent.twoGroups = Groups2.getState();
    }//GEN-LAST:event_Groups2ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CourseList;
    private javax.swing.JComboBox GroupList;
    private java.awt.Checkbox Groups2;
    private javax.swing.JComboBox ParamList;
    private javax.swing.JButton button2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
