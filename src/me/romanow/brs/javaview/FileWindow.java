/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

import java.awt.FileDialog;

/**
 *
 * @author user
 */
public class FileWindow extends javax.swing.JFrame {

    /**
     * Creates new form FileWIndow
     */
    public FileWindow(String title, String name, int mode, FileWindowListener ls) {
        initComponents();
        FileDialog dlg=new FileDialog(this,title,mode);
        dlg.setFile(name);
        dlg.show();
        ls.finish(dlg.getDirectory(), dlg.getFile());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
