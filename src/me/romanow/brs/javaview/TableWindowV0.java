/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import me.romanow.brs.interfaces.TableCallBack;
import me.romanow.brs.model.TableData;

/**
 *
 * @author user
 */
public class TableWindowV0 extends TableData{
    int y0=0,x0=3;
    int dXhd=150;
    int dYhd=40;
    int dY=20;
    int dX=25;
    int dXmid=100;
    int dXlong=250;
    private JFrame jf=null;
    private TextField fld[][]=null;
    private int isel=-1,jsel=-1;
    private int colorBack=0xE0E0E0;
    private int colorSelect=0xA0A0A0;
    JPanel Panel=new JPanel();
    JScrollPane Scroll=new JScrollPane();;
    int yy=30;
    //--------------------------------------------------------------------------------
    private void selectRowCol(){
	for(int i=0;i<nrow;i++)
            for(int j=0;j<ncol;j++)
                if (!select[i][j])
                    fld[i][j].setBackground(new Color(colorBack));
                else
                    fld[i][j].setBackground(new Color(colorSelect));
        if (jsel!=-1)
            for(int j=0;j<nrow;j++)
		fld[j][jsel].setBackground(new Color(colorSelect));
	if (isel!=-1)
            for(int j=0;j<ncol;j++)
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
    public TableWindowV0() { super(); }
    @Override
    public String getExtention(){ return null; }    
    @Override   
    public void createTable(File dst, TableCallBack back ) throws Exception{
        new Thread(rr).start();
        }    
    //--------------------------------------------------------------------------
    Runnable rr=new Runnable(){    
        public void run(){
        jf=new JFrame();
        jf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jf.getContentPane().setLayout(null);
        jf.pack();
        jf.setTitle(title);
        Panel.setLayout(null);
        int xm=dXlong+ncol*dY+200;
        int ym=(nrow+ncol)*dY+100;
        jf.setBounds(300,50,xm+10,610);
        Panel.setBounds(0, 0, xm, ym);
        Panel.setPreferredSize(new Dimension(xm, ym));
        fld=new TextField[nrow][];
        for(int i=0;i<nrow;i++) fld[i]=new TextField[ncol]; 
        for (int i=0;i<ncol;i++){
            int xx=dXlong+i*dX, yy=dY*i;
            TextField cell=createItem(xx,yy,dXhd,dY,cols[i],false);
            final int ii=i;
        	cell.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        jsel=ii;
			selectRowCol();
                    }});
        	}
        for (int i=0;i<nrow;i++){
            TextField cell=createItem(0,(ncol+i)*dY,dXlong,dY,rows[i],false);
            final int ii=i;
            cell.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    isel=ii;
                    selectRowCol();
                    }});
        	}
        for (int i=0;i<nrow;i++){
            for(int j=0;j<ncol-1;j++){
                TextField xx=createItem(dXlong+j*dX,(ncol+i)*dY,dX,dY, data[i][j],select[i][j]);
        	fld[i][j]=xx;
       		}
           }
        for (int i=0;i<nrow;i++){
            int zz=(lastrow ? dXmid : dX);
            TextField xx=createItem(dXlong+(ncol-1)*dX,(ncol+i)*dY,zz,dY,data[i][ncol-1],false);
            fld[i][ncol-1]=xx;        	
            }
        if (bottom.length()!=0) createItem(dXlong,(ncol+nrow)*dY,dXlong,dY, bottom,false);
        if (bottom2.length()!=0) createItem(dXlong,(ncol+nrow+1)*dY,dXlong,dY, bottom2,false);
        Scroll.setBounds(0,0,xm,600);
        Scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        Scroll.setViewportView(Panel);
        jf.getContentPane().add(Scroll,BorderLayout.CENTER);
        jf.setVisible(true);
        jf.show();
        }};
    //--------------------------------------------------------------------------
           
}
