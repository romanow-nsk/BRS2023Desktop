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

import me.romanow.brs.connect.DBConnection;
import me.romanow.brs.database.DBEntry;
import me.romanow.brs.database.DBParams;

/**
 *
 * @author user
 */
public class ParamWindow extends javax.swing.JFrame {
    DBParams xx=null;
    YesNoListener lsn=null;
    boolean newRecord=false;
    /** Creates new form EntryWindow */
    public ParamWindow(DBParams item, YesNoListener lsn0) {
        newRecord=false;
        lsn=lsn0;
        xx=item;
        initComponents();
        this.setTitle("Параметры расчета рейтинга");
        this.setBounds(300, 300, 270, 250);
        semestr.setText(item.getSemestr());
        maxWeek.setText(""+item.getMaxWeek());
        weekProc.setText(""+item.getWeekProc());
        plusProc.setText(""+item.getPlusProc());
        propuskBall.setText(""+item.getPropuskBall());
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

        plusProc = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        semestr = new javax.swing.JTextField();
        weekProc = new javax.swing.JTextField();
        maxWeek = new javax.swing.JTextField();
        button2 = new javax.swing.JButton();
        label6 = new javax.swing.JLabel();
        propuskBall = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        plusProc.setName(""); // NOI18N
        plusProc.setText("10");
        getContentPane().add(plusProc);
        plusProc.setBounds(140, 70, 90, 20);

        label2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label2.setText("Начало семестра");
        getContentPane().add(label2);
        label2.setBounds(21, 10, 100, 15);

        label3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label3.setText("% в неделю");
        getContentPane().add(label3);
        label3.setBounds(20, 40, 100, 15);

        label4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label4.setText("% за показатель");
        getContentPane().add(label4);
        label4.setBounds(20, 70, 100, 15);

        label5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label5.setText("Недель штрафа");
        getContentPane().add(label5);
        label5.setBounds(20, 100, 100, 15);

        semestr.setText("11.2.2013");
        getContentPane().add(semestr);
        semestr.setBounds(140, 10, 90, 20);

        weekProc.setText("10");
        getContentPane().add(weekProc);
        weekProc.setBounds(140, 40, 90, 20);

        maxWeek.setText("5");
        getContentPane().add(maxWeek);
        maxWeek.setBounds(140, 100, 90, 20);

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        button2.setBorder(null);
        button2.setBorderPainted(false);
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        getContentPane().add(button2);
        button2.setBounds(20, 160, 40, 33);

        label6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label6.setText("Баллов за пропуск");
        getContentPane().add(label6);
        label6.setBounds(20, 130, 110, 15);

        propuskBall.setText("1");
        getContentPane().add(propuskBall);
        propuskBall.setBounds(140, 130, 90, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        try {
            if (newRecord) xx=new DBParams();
            xx.setSemestr(semestr.getText());
            xx.setPlusProc(Integer.parseInt(plusProc.getText()));
            xx.setWeekProc(Integer.parseInt(weekProc.getText()));
            xx.setMaxWeek(Integer.parseInt(maxWeek.getText()));
            xx.setPropuskBall(Double.parseDouble(propuskBall.getText()));
            lsn.onYes();
            } catch(Throwable ee){}
            this.dispose();        
    }//GEN-LAST:event_button2ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button2;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JTextField maxWeek;
    private javax.swing.JTextField plusProc;
    private javax.swing.JTextField propuskBall;
    private javax.swing.JTextField semestr;
    private javax.swing.JTextField weekProc;
    // End of variables declaration//GEN-END:variables
}
