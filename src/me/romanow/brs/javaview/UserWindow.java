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

import me.romanow.brs.database.DBUser;


public class UserWindow extends javax.swing.JFrame {
    private YesNoListener lsn=null;
    private DBUser xx=null;
    public UserWindow(DBUser item,boolean isTutor,YesNoListener ls) {
        lsn=ls;
        xx=item;
        initComponents();
        this.setTitle("Параметры пользователя");
        this.setBounds(300, 300, 350, 160);
        NAME.setText(item.getName());
        PASS.setText(item.getPass());
        ADMIN.setVisible(isTutor);
        ADMIN.setSelected(item.isAdmin());
        show();
        }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        NAME = new javax.swing.JTextField();
        button2 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        PASS = new javax.swing.JPasswordField();
        ADMIN = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        label1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label1.setText("Пароль");
        getContentPane().add(label1);
        label1.setBounds(20, 50, 60, 15);
        label1.getAccessibleContext().setAccessibleName("Порт БД");

        label2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label2.setText("Имя/Логин");
        getContentPane().add(label2);
        label2.setBounds(20, 20, 90, 15);
        getContentPane().add(NAME);
        NAME.setBounds(90, 20, 210, 20);

        button2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        button2.setBorder(null);
        button2.setBorderPainted(false);
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        getContentPane().add(button2);
        button2.setBounds(20, 80, 30, 33);

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
        button3.setBounds(270, 80, 30, 30);

        PASS.setText("jPasswordField1");
        getContentPane().add(PASS);
        PASS.setBounds(90, 50, 210, 20);

        ADMIN.setText("Админ БД");
        getContentPane().add(ADMIN);
        ADMIN.setBounds(90, 90, 80, 23);

        jLabel1.setText("Пустой пароль - пароль ЦИУ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 70, 170, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        try {
            xx.setName(NAME.getText());
            xx.setPass(PASS.getText());
            xx.setAdmin(ADMIN.isSelected());
            lsn.onYes();
            UserWindow.this.dispose();        
            } catch(Throwable ee){}
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
            lsn.onNo();
            UserWindow.this.dispose();        
    }//GEN-LAST:event_button3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ADMIN;
    private javax.swing.JTextField NAME;
    private javax.swing.JPasswordField PASS;
    private javax.swing.JButton button2;
    private javax.swing.JButton button3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables
}
