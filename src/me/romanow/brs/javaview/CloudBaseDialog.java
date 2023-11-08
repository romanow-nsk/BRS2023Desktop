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

import java.awt.FileDialog;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import me.romanow.brs.database.DBBases;
import me.romanow.brs.database.DBEntry;


public class CloudBaseDialog extends javax.swing.JFrame {
    private ValueListener lsn=null;
    private DBBases xx=null;
    public CloudBaseDialog(DBBases item,ValueListener ls) {
        lsn=ls;
        xx=item;
        initComponents();
        this.setTitle("Новая БД");
        this.setBounds(300, 300, 270, 200);
        show();
        }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        label5 = new javax.swing.JLabel();
        newLogin = new javax.swing.JTextField();
        newDB = new javax.swing.JTextField();
        newName = new javax.swing.JTextField();
        label6 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

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
        button1.setBounds(20, 130, 30, 30);

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
        button3.setBounds(210, 130, 30, 30);

        label5.setText("БД");
        getContentPane().add(label5);
        label5.setBounds(60, 20, 30, 14);

        newLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(newLogin);
        newLogin.setBounds(100, 60, 140, 20);

        newDB.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(newDB);
        newDB.setBounds(100, 20, 140, 21);

        newName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(newName);
        newName.setBounds(100, 100, 140, 20);

        label6.setText("Логин");
        getContentPane().add(label6);
        label6.setBounds(50, 60, 30, 14);

        label1.setText("Имя в списке");
        getContentPane().add(label1);
        label1.setBounds(20, 100, 70, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        CloudBaseDialog.this.dispose();                
    }//GEN-LAST:event_button3ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        xx.setName(newName.getText());
        xx.setDbName(newDB.getText());
        lsn.onSelect(newLogin.getText());
        CloudBaseDialog.this.dispose();     
    }//GEN-LAST:event_button1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button1;
    private javax.swing.JButton button3;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JTextField newDB;
    private javax.swing.JTextField newLogin;
    private javax.swing.JTextField newName;
    // End of variables declaration//GEN-END:variables
}
