/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EntryWindow.java
 *
 * Created on 06.01.2013, 10:23:58
 */
package me.romanow.brs.javaview;

import me.romanow.brs.database.DBCell;


public class CellDialog extends javax.swing.JDialog {
    private ValueListener lsn=null;
    private DBCell cellItem=null;
    private boolean onStart=true;
    public CellDialog(java.awt.Frame parent,DBCell cellItem,int summ, ValueListener ls) {
        super(parent, true);
        onStart=true;
        this.cellItem = cellItem;
        lsn=ls;
        initComponents();
        this.setTitle("Единица контроля");
        CType.addItem("...");
        for(int i=0;i<DBCell.NoteTypes.length;i++)
            CType.addItem(DBCell.NoteTypes[i]);
        this.setBounds(450, 300, 250, 180);
        Summ.setText(""+summ);
        MaxBall.setText(""+cellItem.getBall());
        String name=cellItem.getName();
        Name.setText(name);
        if (name.length()!=0)
            CType.setSelectedIndex(cellItem.getCType()+1);
        else
            CType.setSelectedIndex(0);
        onStart=false;
        setVisible(true);
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Name = new javax.swing.JTextField();
        button1 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        MaxBall = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Summ = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        CType = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(Name);
        Name.setBounds(10, 10, 200, 20);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        button1.setBorder(null);
        button1.setBorderPainted(false);
        button1.setName(""); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1);
        button1.setBounds(10, 100, 30, 30);

        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        button3.setBorder(null);
        button3.setBorderPainted(false);
        button3.setName(""); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        getContentPane().add(button3);
        button3.setBounds(180, 100, 30, 30);

        MaxBall.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MaxBall.setText("0");
        getContentPane().add(MaxBall);
        MaxBall.setBounds(180, 40, 30, 21);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Тек. сумма");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(90, 70, 80, 20);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Вид занятий");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 40, 80, 20);

        Summ.setEditable(false);
        Summ.setBackground(new java.awt.Color(236, 233, 216));
        Summ.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Summ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SummActionPerformed(evt);
            }
        });
        getContentPane().add(Summ);
        Summ.setBounds(180, 70, 30, 21);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Макс. балл");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(90, 40, 80, 20);

        CType.setEditable(true);
        CType.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        CType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CTypeItemStateChanged(evt);
            }
        });
        getContentPane().add(CType);
        CType.setBounds(10, 70, 90, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        int k=CType.getSelectedIndex();
        if (k==0){
            lsn.onError("Не выбран тип учебной единицы");  
            return;
            }
        cellItem.setCType(k-1);
        int n1;
        try {
            n1=Integer.parseInt(MaxBall.getText());
            } catch(Exception ee){ 
                lsn.onError("Нечисловое значение балла");  
                return;
                }
        cellItem.setBall(n1);
        cellItem.setName(Name.getText());
        lsn.onSelect("");
        CellDialog.this.dispose();     
    }//GEN-LAST:event_button1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        CellDialog.this.dispose();
    }//GEN-LAST:event_button3ActionPerformed

    private void SummActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SummActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SummActionPerformed

    private void CTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CTypeItemStateChanged
        if (onStart){
            return;
            }
        Name.setText(CType.getSelectedItem().toString());
    }//GEN-LAST:event_CTypeItemStateChanged

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CType;
    private javax.swing.JTextField MaxBall;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Summ;
    private javax.swing.JButton button1;
    private javax.swing.JButton button3;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
