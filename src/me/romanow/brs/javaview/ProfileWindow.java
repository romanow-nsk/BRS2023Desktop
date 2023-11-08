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

import me.romanow.brs.database.DBEntry;
import me.romanow.brs.database.DBProfile;

/**
 *
 * @author user
 */
public class ProfileWindow extends javax.swing.JFrame {
    ProfileListener lsn=null;
    DBProfile xx=null;
    boolean newRecord=false;
    /** Creates new form EntryWindow */
    public ProfileWindow(DBProfile item,ProfileListener ls) {
        newRecord=false;
        lsn=ls;
        xx=item;
        initComponents();
        this.setTitle("Настройка соединения");
        this.setBounds(350, 350, 270, 180);
        PORT.setText(""+item.getPort());
        IP.setText(item.getIP());
        PORT2.setText(""+item.getProxyPort());
        IP2.setText(item.getProxyIP());
        boolean zz=item.isProxyOn();
        IP2.setVisible(zz);
        PORT2.setVisible(zz);
        servlet.setState(zz);
        label6.setVisible(zz);
        label2.setVisible(zz);
        show();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        PORT2 = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        IP = new javax.swing.JTextField();
        button2 = new javax.swing.JButton();
        servlet = new java.awt.Checkbox();
        label6 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        PORT = new javax.swing.JTextField();
        IP2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        label1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label1.setText("Server  Port");
        getContentPane().add(label1);
        label1.setBounds(20, 40, 90, 20);
        label1.getAccessibleContext().setAccessibleName("Порт БД");

        getContentPane().add(PORT2);
        PORT2.setBounds(90, 100, 60, 20);

        label5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label5.setText("Server IP");
        getContentPane().add(label5);
        label5.setBounds(20, 10, 70, 15);
        getContentPane().add(IP);
        IP.setBounds(90, 10, 160, 20);

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
        button2.setBounds(210, 100, 30, 33);

        servlet.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        servlet.setLabel("Proxy");
        servlet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                servletItemStateChanged(evt);
            }
        });
        getContentPane().add(servlet);
        servlet.setBounds(190, 40, 60, 19);

        label6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label6.setText("Proxi IP");
        getContentPane().add(label6);
        label6.setBounds(20, 70, 70, 15);

        label2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label2.setText("Proxi  Port");
        getContentPane().add(label2);
        label2.setBounds(20, 100, 90, 15);
        getContentPane().add(PORT);
        PORT.setBounds(90, 40, 60, 20);
        getContentPane().add(IP2);
        IP2.setBounds(90, 70, 160, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        xx.setProxyPort(Integer.parseInt(PORT2.getText()));
        xx.setProxyIP(IP2.getText());
        xx.setPort(Integer.parseInt(PORT.getText()));
        xx.setIP(IP.getText());
        xx.setProxyOn(servlet.getState());
        lsn.onSelect(xx);
        ProfileWindow.this.dispose();        
    }//GEN-LAST:event_button2ActionPerformed

    private void servletItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_servletItemStateChanged
        boolean zz=servlet.getState();
        IP2.setVisible(zz);
        PORT2.setVisible(zz);        
        label6.setVisible(zz);
        label2.setVisible(zz);
    }//GEN-LAST:event_servletItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IP;
    private javax.swing.JTextField IP2;
    private javax.swing.JTextField PORT;
    private javax.swing.JTextField PORT2;
    private javax.swing.JButton button2;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private java.awt.Checkbox servlet;
    // End of variables declaration//GEN-END:variables
}