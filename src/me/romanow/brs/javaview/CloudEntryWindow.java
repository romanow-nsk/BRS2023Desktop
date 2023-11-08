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



public class CloudEntryWindow extends javax.swing.JFrame {
    private ValueListener lsn=null;
    private DBProfile xx=null;
    public CloudEntryWindow(DBProfile item,ValueListener ls) {
        lsn=ls;
        xx=item;
        initComponents();
        this.setTitle("Параметры соединения");
        this.setBounds(300, 300, 320, 220);
        PORT.setText(""+item.getPort());
        IP.setText(item.getIP());
        PORTPROXY.setText(""+item.getProxyPort());
        IPPROXY.setText(item.getProxyIP());
        PROXY.setState(item.isProxyOn());
        show();
        }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        PORT = new javax.swing.JTextField();
        label5 = new javax.swing.JLabel();
        IP = new javax.swing.JTextField();
        button2 = new javax.swing.JButton();
        label3 = new javax.swing.JLabel();
        PORTPROXY = new javax.swing.JTextField();
        label6 = new javax.swing.JLabel();
        IPPROXY = new javax.swing.JTextField();
        PROXY = new java.awt.Checkbox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        label1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label1.setText(" Порт облака");
        getContentPane().add(label1);
        label1.setBounds(10, 50, 80, 15);
        label1.getAccessibleContext().setAccessibleName("Порт БД");

        getContentPane().add(PORT);
        PORT.setBounds(100, 50, 170, 20);

        label5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label5.setText("IP Облака");
        getContentPane().add(label5);
        label5.setBounds(30, 20, 60, 15);
        getContentPane().add(IP);
        IP.setBounds(100, 20, 170, 20);

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
        button2.setBounds(240, 140, 30, 33);

        label3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label3.setText("Proxi  Port");
        getContentPane().add(label3);
        label3.setBounds(30, 110, 90, 15);
        getContentPane().add(PORTPROXY);
        PORTPROXY.setBounds(100, 110, 170, 20);

        label6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label6.setText("Proxy IP");
        getContentPane().add(label6);
        label6.setBounds(40, 80, 50, 15);
        getContentPane().add(IPPROXY);
        IPPROXY.setBounds(100, 80, 170, 20);

        PROXY.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        PROXY.setLabel("Proxy");
        PROXY.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PROXYItemStateChanged(evt);
            }
        });
        getContentPane().add(PROXY);
        PROXY.setBounds(100, 140, 80, 19);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        try {
            xx.setPort(Integer.parseInt(PORT.getText()));
            xx.setIP(IP.getText());
            xx.setProxyPort(Integer.parseInt(PORTPROXY.getText()));
            xx.setProxyIP(IPPROXY.getText());
            xx.setProxyOn(PROXY.getState());
            lsn.onSelect("");
            CloudEntryWindow.this.dispose();        
            } catch (Throwable ee){}
    }//GEN-LAST:event_button2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void PROXYItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PROXYItemStateChanged

    }//GEN-LAST:event_PROXYItemStateChanged

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IP;
    private javax.swing.JTextField IPPROXY;
    private javax.swing.JTextField PORT;
    private javax.swing.JTextField PORTPROXY;
    private java.awt.Checkbox PROXY;
    private javax.swing.JButton button2;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    // End of variables declaration//GEN-END:variables
}
