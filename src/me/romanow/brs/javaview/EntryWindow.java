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
import me.romanow.brs.database.DBEntry;


public class EntryWindow extends javax.swing.JFrame {
    private EntryListener lsn=null;
    private DBEntry xx=null;
    public EntryWindow(DBEntry item,EntryListener ls) {
        lsn=ls;
        xx=item;
        //---------------- Эмулятор БАГА
        //xx=null;
        //xx.getPass();
        initComponents();
        this.setTitle("Параметры соединения с БД");
        this.setBounds(300, 300, 270, 200);
        PORT.setText(""+item.getPort());
        DBASE.setText(item.getName());
        IP.setText(item.getIP());
        TYPE.setSelectedIndex(item.getType());
        TYPE.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                switch(TYPE.getSelectedIndex()){
            case DBEntry.jdbc:
                    PORT.setText("3306");
                    IP.setText("localhost");
                    DBASE.setText("***");
                    break;
            case DBEntry.sqlite:
                    PORT.setText("");
                    IP.setText("c:\\temp\\");
                    DBASE.setText("***");
                    break;
            case DBEntry.http:
                    PORT.setText("8084");
                    IP.setText("localhost");
                    DBASE.setText("***");
                    break;
            case DBEntry.local:
                    PORT.setText("");
                    IP.setText("c:\\temp\\");
                    DBASE.setText("***");
                    break;                
                    }
                }});
            show();
        }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new javax.swing.JLabel();
        PORT = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        DBASE = new javax.swing.JTextField();
        IP = new javax.swing.JTextField();
        button1 = new javax.swing.JButton();
        button2 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        TYPE = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        label1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label1.setText(" Порт БД / HTTP");
        getContentPane().add(label1);
        label1.setBounds(20, 70, 90, 15);
        label1.getAccessibleContext().setAccessibleName("Порт БД");

        getContentPane().add(PORT);
        PORT.setBounds(120, 70, 120, 20);

        label2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label2.setText("Имя БД / файл");
        getContentPane().add(label2);
        label2.setBounds(20, 40, 90, 15);

        label5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label5.setText("IP / каталог");
        getContentPane().add(label5);
        label5.setBounds(20, 100, 70, 15);
        getContentPane().add(DBASE);
        DBASE.setBounds(120, 40, 120, 20);
        getContentPane().add(IP);
        IP.setBounds(120, 100, 120, 20);

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
        button1.setBounds(30, 130, 30, 30);

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
        button2.setBounds(120, 130, 30, 33);

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/save.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(210, 0, 30, 30);

        TYPE.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TYPE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MySQL", "Web", "SQLite", "Files" }));
        getContentPane().add(TYPE);
        TYPE.setBounds(20, 10, 170, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        try {
            xx.setPort(Integer.parseInt(PORT.getText()));
            xx.setIP(IP.getText());
            xx.setName(DBASE.getText());
            xx.setType(TYPE.getSelectedIndex());
            lsn.onSelect(xx,0);
            EntryWindow.this.dispose();        
            } catch(Throwable ee){}
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        new YesNoDialog(this, "Удалить: "+DBASE.getText(), new YesNoListener(){
            @Override
            public void onYes() {
                lsn.onSelect(xx, 1);
                EntryWindow.this.dispose();                
                }
            @Override
            public void onNo() {
                EntryWindow.this.dispose();                
                }
            });
    }//GEN-LAST:event_button3ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try{
            xx=new DBEntry();
            xx.setPort(Integer.parseInt(PORT.getText()));
            xx.setIP(IP.getText());
            xx.setName(DBASE.getText());
            xx.setType(TYPE.getSelectedIndex());
            lsn.onSelect(xx, 2);
            EntryWindow.this.dispose(); 
        } catch(Throwable ee){}
    }//GEN-LAST:event_button1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int k=TYPE.getSelectedIndex();
        if (k==DBEntry.jdbc || k==DBEntry.http) return;
        String zz = (k==DBEntry.sqlite ? "Локальный файл SQLite" : "Каталог локальных рейтингов");
        FileDialog dlg=new FileDialog(this,zz,FileDialog.LOAD);
        dlg.setFile(xx.getName()+ (k==DBEntry.sqlite ? DBEntry.sqliteExt : DBEntry.localExt));
        dlg.show();
        String ss=dlg.getFile();
        if (ss==null) return;
        int kk=ss.lastIndexOf(".");
        if (kk!=-1) ss=ss.substring(0,kk);
        if (k==DBEntry.sqlite) 
            DBASE.setText(ss);
        else 
            DBASE.setText("");
        IP.setText(dlg.getDirectory());
        PORT.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DBASE;
    private javax.swing.JTextField IP;
    private javax.swing.JTextField PORT;
    private javax.swing.JComboBox TYPE;
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JButton button3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label5;
    // End of variables declaration//GEN-END:variables
}
