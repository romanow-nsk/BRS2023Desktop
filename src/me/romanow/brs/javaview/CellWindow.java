
package me.romanow.brs.javaview;

import me.romanow.brs.database.DBCell;
import me.romanow.brs.database.DBCellRating;
import me.romanow.brs.database.DBItem;
import me.romanow.brs.database.DBRating;
import me.romanow.brs.database.DBStudent;
import me.romanow.brs.interfaces.DBConnect;
import me.romanow.brs.model.MDNote;

public class CellWindow extends javax.swing.JFrame {

    private AdminWindow parent=null;
    private TableComboBox cellList=new TableComboBox();
    private boolean cellNoManual=false;
    private DBCell cellItem=null;
    private DBConnect conn=null;

    public CellWindow(AdminWindow parent0) throws Throwable {
        initComponents();
        setTitle("Единицы контроля");
        this.parent = parent0;
        conn = parent.conn;
        setBounds(250, 220, 230, 160);
        parent.studentItem=null;
        cellList=new TableComboBox(CellList, new DBCell(),new TableChoiceListener(){
            public void onSelect(DBItem item) {
                parent.onErrorMessage("");
                cellItem = (DBCell)item;
                int ct=cellItem.getCType();
                //cellParams=DBCell.NoteParamValues[ct];
                Summ.setText(""+cellItem.getBall()+" из "+calcCourseBalls());
                cellNoManual=true;
                CType.setText(DBCell.NoteTypes[ct]);
            }});
        cellList.setList(parent.conn.getList(DBCell.class,parent.courseItem));    
        }
    public int calcCourseBalls(){
        if (parent.courseItem==null) return 0;
        int sum=0;
        try {
            DBItem xx[]=parent.conn.getList(DBCell.class, parent.courseItem);
            for(int i=0;i<xx.length;i++) sum+=((DBCell)xx[i]).getBall();
            } catch(Throwable ee){ parent.fatal(ee); }
        return sum;
        }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button7 = new javax.swing.JButton();
        button9 = new javax.swing.JButton();
        button31 = new javax.swing.JButton();
        CellList = new javax.swing.JComboBox();
        Summ = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        CType = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        button7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        button7.setBorder(null);
        button7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button7MouseClicked(evt);
            }
        });
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        getContentPane().add(button7);
        button7.setBounds(180, 50, 30, 30);

        button9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        button9.setBorder(null);
        button9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button9MouseClicked(evt);
            }
        });
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });
        getContentPane().add(button9);
        button9.setBounds(20, 50, 30, 30);

        button31.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        button31.setBorder(null);
        button31.setName(""); // NOI18N
        button31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button31MouseClicked(evt);
            }
        });
        button31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button31ActionPerformed(evt);
            }
        });
        getContentPane().add(button31);
        button31.setBounds(100, 50, 33, 30);

        CellList.setEditable(true);
        CellList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        CellList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CellListActionPerformed(evt);
            }
        });
        getContentPane().add(CellList);
        CellList.setBounds(20, 20, 190, 21);

        Summ.setEditable(false);
        Summ.setBackground(new java.awt.Color(217, 214, 198));
        Summ.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Summ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SummMouseClicked(evt);
            }
        });
        Summ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SummActionPerformed(evt);
            }
        });
        getContentPane().add(Summ);
        Summ.setBounds(150, 100, 60, 21);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Балл");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(100, 100, 40, 20);

        CType.setEditable(false);
        CType.setBackground(new java.awt.Color(223, 223, 223));
        CType.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        CType.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CTypeMouseClicked(evt);
            }
        });
        CType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CTypeActionPerformed(evt);
            }
        });
        getContentPane().add(CType);
        CType.setBounds(20, 100, 80, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/down.PNG"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(140, 50, 33, 30);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/up.PNG"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(60, 50, 33, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button7MouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Удалить единицу контроля");
        }
    }//GEN-LAST:event_button7MouseClicked

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        if (parent.isBusy()){
            parent.onErrorMessage("Выполнение фоновой операции");
            return;
        }
        //------------- УДАЛИТЬ УЧЕБНУЮ ЕДИНИЦУ ----------------------------
        parent.onErrorMessage("");
        if (parent.courseItem==null || cellItem==null) return;
        new YesNoDialog(CellWindow.this,"Удалить учебную единицу "+cellItem.getName(),new YesNoListener(){
            public void onYes() {
                try {
                    int id0=parent.courseItem.getId();
                    conn.deleteLinked(MDNote.class,cellItem);
                    conn.deleteLinked(DBCellRating.class,cellItem);
                    conn.delete(cellItem.getClass(),cellItem.getId());
                    cellItem=null;
                    cellList.setList(conn.getList(DBCell.class,parent.courseItem));
                    Summ.setText("... из "+calcCourseBalls());
                    CType.setText("");
                } catch(Throwable e1){ parent.fatal(e1); }
            }
            public void onNo() {}
        });
    }//GEN-LAST:event_button7ActionPerformed

    private void button9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button9MouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Добавить единицу контроля");
        }
    }//GEN-LAST:event_button9MouseClicked

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        if (parent.isBusy()){
            parent.onErrorMessage("Выполнение фоновой операции");
            return;
        }
        parent.onErrorMessage("");
        if (parent.courseItem==null){
            parent.onErrorMessage("Не выбрана дисциплина");
            return;
        }
        final DBCell item=new DBCell();
        new CellDialog(CellWindow.this,item,calcCourseBalls(),new ValueListener(){
            @Override
            public void onError(String value) {
                parent.onErrorMessage(value);
            }
            @Override
            public void onSelect(String value) {
                //--------- ДОБАВИТЬ УЧЕБНУЮ ЕДИНИЦУ -----------------------------
                try {
                    int sz=cellList.size();
                    int kk=(sz==0 ? 0 : ((DBCell)cellList.getSource()[sz-1]).getOrdNum()+1);
                    item.setOrdNum(kk);
                    item.setIdCourse(parent.courseItem.getId());
                    conn.insert(item);
                    CType.setText("");
                    cellItem=null;
                    cellList.setList(conn.getList(DBCell.class,parent.courseItem));
                    Summ.setText("... из "+calcCourseBalls());
                    //------------------------------------------------------------------
                    if (item.getCellParams()[DBCell.idxCData]!=0){
                        DBItem ss[]=parent.ratingList.getSource();
                        for(int i=0;i<ss.length;i++){
                            DBRating rr=(DBRating)ss[i];
                            if (rr.getIdCourse()!=item.getIdCourse()) continue;
                            conn.insert(new DBCellRating(rr.getId(),item.getId(),0,0));
                        }
                    }
                } catch(Throwable e1){ parent.fatal(e1); }
            }
        });
    }//GEN-LAST:event_button9ActionPerformed

    private void button31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button31MouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Редактировать данные единицы контроля");
        }
    }//GEN-LAST:event_button31MouseClicked

    private void button31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button31ActionPerformed
        if (parent.isBusy()){
            parent.onErrorMessage("Выполнение фоновой операции");
            return;
        }
        try {
            final int k=cellList.list.getSelectedIndex();
            //---------------- Обновить учебную единицу --------------------
            parent.onErrorMessage("");
            if (cellItem==null){
                parent.onErrorMessage("Не выбрана единица контроля");
                return;
            }
            new CellDialog(CellWindow.this,cellItem,calcCourseBalls(),new ValueListener(){
                @Override
                public void onError(String value) {
                    parent.onErrorMessage(value);
                }
                @Override
                public void onSelect(String value) {
                    try {
                        conn.update(cellItem);
                        CType.setText("");
                        cellList.setList(conn.getList(DBCell.class,parent.courseItem));
                        cellList.list.setSelectedIndex(k);
                        Summ.setText(""+cellItem.getBall()+" из "+calcCourseBalls());
                    } catch(Throwable e1){ parent.fatal(e1); }
                }
            });
        } catch(Throwable e1){ parent.fatal(e1); }
    }//GEN-LAST:event_button31ActionPerformed

    private void CellListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CellListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CellListActionPerformed

    private void SummMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SummMouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Номативный балл и текущая сумма");
        }
    }//GEN-LAST:event_SummMouseClicked

    private void SummActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SummActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SummActionPerformed

    private void CTypeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CTypeMouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Вид единицы контроля");
        }
    }//GEN-LAST:event_CTypeMouseClicked

    private void CTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CTypeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DBItem xx[] = cellList.getSource();
        int k=cellList.getSelectedIndex();
        if (k<=0)
            return;
        try {
            DBCell v1=(DBCell)xx[k];
            DBCell v2=(DBCell)xx[k-1];
            int cc=v1.getOrdNum(); v1.setOrdNum(v2.getOrdNum()); v2.setOrdNum(cc);
            DBItem vv = xx[k]; xx[k]=xx[k-1]; xx[k-1]=vv;
            conn.update(v1);
            conn.update(v2);
            cellList.setList(xx);
            cellList.setPosition(k);
            } catch(Throwable ee)
                    { parent.fatal(ee); }        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DBItem xx[] = cellList.getSource();
        int k=cellList.getSelectedIndex();
        if (k<0 || k==xx.length-1)
            return;
        try {
            DBCell v1=(DBCell)xx[k];
            DBCell v2=(DBCell)xx[k+1];
            int cc=v1.getOrdNum(); v1.setOrdNum(v2.getOrdNum()); v2.setOrdNum(cc);
            DBItem vv = xx[k]; xx[k]=xx[k+1]; xx[k+1]=vv;
            conn.update(v1);
            conn.update(v2);
            cellList.setList(xx);
            cellList.setPosition(k+2);
            } catch(Throwable ee)
                    { parent.fatal(ee); }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Сдвинуть единицу контроля в списке");
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (evt.getButton()==3){
            parent.onErrorMessage("Сдвинуть единицу контроля в списке");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CType;
    private javax.swing.JComboBox CellList;
    private javax.swing.JTextField Summ;
    private javax.swing.JButton button31;
    private javax.swing.JButton button7;
    private javax.swing.JButton button9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel17;
    // End of variables declaration//GEN-END:variables
}
