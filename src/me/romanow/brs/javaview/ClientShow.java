package me.romanow.brs.javaview;

import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import me.romanow.brs.database.DBCell;
import me.romanow.brs.database.DBNote;
import me.romanow.brs.model.TableData;

public class ClientShow extends javax.swing.JFrame {
    int y0=0,x0=3;
    int dXhd=150;
    int dYhd=40;
    int dY=20;
    int dX=25;
    int dXmid=100;
    int dXlong=250;
    private TableData tbl=null;
    private TextField fld[][]=null;
    private int isel=-1,jsel=-1;
    private int colorBack=0xE0E0E0;
    private int colorSelect=0xA0A0A0;
    JPanel Panel=new JPanel();
    JScrollPane Scroll=new JScrollPane();;
    int yy=30;
    //--------------------------------------------------------------------------------
    private void selectRowCol(){
	for(int i=0;i<tbl.nrow;i++)
            for(int j=0;j<tbl.ncol;j++)
                if (!tbl.select[i][j])
                    fld[i][j].setBackground(new Color(colorBack));
                else
                    fld[i][j].setBackground(new Color(colorSelect));
        if (jsel!=-1)
            for(int j=0;j<tbl.nrow;j++)
		fld[j][jsel].setBackground(new Color(colorSelect));
	if (isel!=-1)
            for(int j=0;j<tbl.ncol;j++)
		fld[isel][j].setBackground(new Color(colorSelect));;	
	}
    public TextField createItem(int x, int y, int w, int h,String text,boolean select){
	TextField cell=new TextField();
    	cell.setText(text);
        cell.setBounds(x, y, w, h);
        if (!select)
            cell.setBackground(new Color(colorBack));
        else
            cell.setBackground(new Color(colorSelect));
        cell.setForeground(Color.black);
        cell.setEditable(false);
        cell.setFont(new Font("Arial",0,12));
        Panel.add(cell);
	return cell;
	}
    //---------------------------------------------------------------------------------
    public ClientShow(TableData tbl0) {
        tbl=tbl0;
        initComponents();
        setTitle(tbl.title);
        Panel.setLayout(null);
        getContentPane().setLayout(null);
        int xm=dXlong+tbl.ncol*dY+200;
        int ym=(tbl.nrow+tbl.ncol)*dY+100;
        setBounds(300,50,xm+10,610);
        Panel.setBounds(0, 0, xm, ym);
        Panel.setPreferredSize(new Dimension(xm, ym));
        fld=new TextField[tbl.nrow][];
        for(int i=0;i<tbl.nrow;i++) fld[i]=new TextField[tbl.ncol]; 
        this.setTitle(tbl.title);
        for (int i=0;i<tbl.ncol;i++){
            int xx=dXlong+i*dX, yy=dY*i;
            TextField cell=createItem(xx,yy,dXhd,dY,tbl.cols[i],false);
            final int ii=i;
        	cell.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        jsel=ii;
			selectRowCol();
                    }});
        	}
        for (int i=0;i<tbl.nrow;i++){
            TextField cell=createItem(0,(tbl.ncol+i)*dY,dXlong,dY,tbl.rows[i],false);
            final int ii=i;
            cell.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    isel=ii;
                    selectRowCol();
                    }});
        	}
        for (int i=0;i<tbl.nrow;i++){
            for(int j=0;j<tbl.ncol-1;j++){
                TextField xx=createItem(dXlong+j*dX,(tbl.ncol+i)*dY,dX,dY, tbl.data[i][j],tbl.select[i][j]);
        	fld[i][j]=xx;
       		}
           }
        for (int i=0;i<tbl.nrow;i++){
            int zz=(tbl.lastrow ? dXmid : dX);
            TextField xx=createItem(dXlong+(tbl.ncol-1)*dX,(tbl.ncol+i)*dY,zz,dY,tbl.data[i][tbl.ncol-1],false);
            fld[i][tbl.ncol-1]=xx;        	
            }
        if (tbl.bottom.length()!=0) createItem(dXlong,(tbl.ncol+tbl.nrow)*dY,dXlong,dY, tbl.bottom,false);
        if (tbl.bottom2.length()!=0) createItem(dXlong,(tbl.ncol+tbl.nrow+1)*dY,dXlong,dY, tbl.bottom2,false);
        Scroll.setBounds(0,0,xm,600);
        Scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        Scroll.setViewportView(Panel);
        getContentPane().add(Scroll,BorderLayout.CENTER);
        this.setVisible(true);
        show();
        }
    //--------------------------------------------------------------------------
           
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}
