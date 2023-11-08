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


public class PassWordDialog extends javax.swing.JDialog {
    private PasswordListener lsn=null;
    public PassWordDialog(boolean admin, java.awt.Frame parent,String name,PasswordListener ls) {
        super(parent, true);
        lsn=ls;
        initComponents();
        this.setTitle(name);
        this.setBounds(450, 300, 200, 150);
        setVisible(true);
        cAdmin.setVisible(admin);
        }
    public PassWordDialog(boolean admin,java.awt.Frame parent,String name,String value0, boolean isAdmin, PasswordListener ls) {
        this(admin,parent,name,ls);
        Value.setText(value0);
        cAdmin.setSelected(isAdmin);
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        Value = new javax.swing.JPasswordField();
        cAdmin = new javax.swing.JCheckBox();

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
        button1.setBounds(10, 70, 30, 30);

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
        button3.setBounds(140, 70, 30, 30);

        Value.setText("jPasswordField1");
        getContentPane().add(Value);
        Value.setBounds(10, 10, 160, 20);

        cAdmin.setText("Администратор");
        getContentPane().add(cAdmin);
        cAdmin.setBounds(10, 40, 120, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        lsn.onSelect(Value.getText(),cAdmin.isSelected());
        PassWordDialog.this.dispose();     
    }//GEN-LAST:event_button1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        PassWordDialog.this.dispose();
    }//GEN-LAST:event_button3ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Value;
    private javax.swing.JButton button1;
    private javax.swing.JButton button3;
    private javax.swing.JCheckBox cAdmin;
    // End of variables declaration//GEN-END:variables
}
