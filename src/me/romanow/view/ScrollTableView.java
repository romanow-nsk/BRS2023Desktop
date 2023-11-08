/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.view;

import me.romanow.brs.interfaces.TableCallBack;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.border.Border;

public class ScrollTableView extends JPanel{
    TableCallBack lsn=null;
    private int colorBack=0xE0E0E0;
    private int colorSelect=0xA0A0A0;
    private int hsymSize=7; // Размерность символа по горизонтали
    private int vsymSize=14;// Размерность символа по вертикали
    private int yy,xx;
    private int yt0,xt0;
    private int csize;      // Размерность ячейки
    private int hsize;      // Длина гориз. заголовка
    private int vsize;      // Длина вертик. заголовка
    private int hcnt0,hcnt; // Количество ячеек в гориз. списке.
    private int vcnt0,vcnt; // Количество ячеек в вертик. списке.
    private int i0=0;       // Начальные индексы рисования
    private int j0=0;
    private JPanel HList;
    private JScrollBar HListScroll;
    private JPanel VList;
    private JScrollBar VListScroll; 
    private JPanel TableArea;
    private JTextField H[]=null;
    private JTextArea V[]=null;
    private JTextField T[][]=null;
    private Border border=null;
    //--------------------------------------------------------------------------
    private String rows[]=null;
    private String cols[]=null;
    private String data[][]=null;
    private boolean select[][]=null;
    private int maxRow,maxCol;
    public void refreshData(String d[][], boolean sl[][]){
        data=d; select=sl;
        }
    //--------------------------------------------------------------------------
    private void correct(){
        if (i0>rows.length-hcnt) i0=rows.length-hcnt;
        if (i0<0) i0=0;
        if (j0>cols.length-vcnt) j0=cols.length-vcnt;
        if (j0<0) j0=0;
        }
    public void toFront(){
        if (!dataValid()) return;
        i0=0; j0=0; repaint();
        }
    public void toEnd(){
        if (!dataValid()) return;
        i0=rows.length-hcnt;
        if (i0<0) i0=0;
        j0=cols.length-vcnt;
        if (j0<0) j0=0;
        repaint();
        }
    private String toVertical(String ss){
        char cc[]=ss.toCharArray();
        char out[]=new char[ss.length()*3];
        for (int i=0;i<cc.length;i++){
            out[3*i]=' ';
            out[3*i+1]=cc[i];
            out[3*i+2]='\n';
            }
        return new String(out);
        }
    public void repaint(){
        super.repaint();
        if (!dataValid()) return;
        correct();
        for(int i=0;i<hcnt;i++){
            if (i0+i>=rows.length) H[i].setText("");
            else H[i].setText(" "+rows[i0+i]);
            }
        for(int j=0;j<vcnt;j++){
            if (j0+j>=cols.length) V[j].setText("");
            else V[j].setText(toVertical(cols[j0+j]));
            }
        for(int i=0;i<hcnt;i++){
            for(int j=0;j<vcnt;j++){
                if (i0+i>=rows.length || j0+j>=cols.length) 
                    T[i][j].setText("");
                else
                    T[i][j].setText(" "+data[i0+i][j0+j]);
                }
            }
        }
    public boolean dataValid(){
        return cols!=null && rows!=null && data!=null;
        }
    public Dimension setData(String r[], String c[], String d[][], boolean s[][]){
        rows=r;
        cols=c;
        data=d;
        select=s;
        maxRow=0;
        hcnt=(hcnt0<rows.length ? hcnt0 : rows.length);
        vcnt=(vcnt0<cols.length ? vcnt0 : cols.length);
        for(int j=0;j<rows.length;j++){
            int k=rows[j].length();
            if (k>maxRow) maxRow=k;
            }
        maxCol=0;
        for(int j=0;j<cols.length;j++){
            int k=cols[j].length();
            if (k>maxCol) maxCol=k;
            }
        hsize=(maxRow+1)*hsymSize;
        vsize=(maxCol+1)*vsymSize;
        yy=vsize+(hcnt+1)*csize;
        xx=hsize+(vcnt+1)*csize;       
        Dimension dd=new Dimension();
        dd.height=yy;
        dd.width=xx;
        xt0=csize+hsize;
        yt0=csize+vsize;
        HListScroll.setOrientation(JScrollBar.VERTICAL);
        HListScroll.setBounds(0,yt0, csize, yy-yt0);
        VListScroll.setOrientation(JScrollBar.HORIZONTAL);
        VListScroll.setBounds(xt0,0, xx-xt0,csize);
        HList.setBounds(csize, csize+vsize, hsize, yy-yt0);
        VList.setBounds(csize+hsize, csize, xx-xt0, vsize);
        TableArea.setBounds(xt0, yt0, xx-xt0, yy-yt0);
        H=new JTextField[hcnt];
        for(int i=0;i<hcnt;i++){
            H[i]=new JTextField();
            setCell(H[i]);
            H[i].setBounds(0,i*csize,hsize,csize);
            HList.add(H[i]);
            }
        V=new JTextArea[vcnt];
        for(int i=0;i<vcnt;i++){
            V[i]=new JTextArea();
            setCell(V[i]);
            V[i].setBounds(i*csize,0,csize,vsize);
            VList.add(V[i]);
            }
        T=new JTextField[hcnt][];
        for(int i=0;i<hcnt;i++){
            T[i]=new JTextField[vcnt];
            for(int j=0;j<vcnt;j++){
                final int ii=i;
                final int jj=j;
                T[i][j]=new JTextField();
                setCell(T[i][j]);
                T[i][j].setBounds(j*csize,i*csize,csize,csize);
                TableArea.add(T[i][j]); 
                T[i][j].addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (dataValid() && lsn!=null)
                            lsn.cellSelected(i0+ii, j0+jj);
                        }
                    @Override
                    public void mousePressed(MouseEvent e) {
                        }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        }
                    });
                }
            }
        HListScroll.setMaximum(rows.length);    // Сразу вызывает событие
        VListScroll.setMaximum(cols.length);
        return dd;
        }
    private void setCell(JComponent cell){
        cell.setBackground(new Color(colorBack));
        cell.setForeground(Color.black);
        if (cell instanceof JTextArea) ((JTextArea)cell).setEditable(false);
        else ((JTextField)cell).setEditable(false);
        cell.setFont(new Font("Arial",0,12));
        cell.setBorder(border);
        }
    public ScrollTableView(int size0, int h0, int v0, TableCallBack lsn0){
        lsn=lsn0;
        csize=size0;
        hcnt0=hcnt=h0;
        vcnt0=vcnt=v0;
        this.setLayout(null);
        TableArea = new javax.swing.JPanel();
        VList = new javax.swing.JPanel();
        HList = new javax.swing.JPanel();
        HListScroll = new javax.swing.JScrollBar();
        VListScroll = new javax.swing.JScrollBar();
        border=BorderFactory.createLineBorder(Color.black);
        TableArea.setBorder(border);
        VList.setBorder(border);
        HList.setBorder(border);
        TableArea.setLayout(null);
        VList.setLayout(null);
        HList.setLayout(null);
        VListScroll.setBorder(border);
        HListScroll.setBorder(border);
        add(TableArea);
        add(VList);
        add(HList);
        add(VListScroll);
        add(HListScroll);
        HListScroll.addAdjustmentListener(new AdjustmentListener(){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (!dataValid()) return;
                i0=e.getValue();
                repaint();
                System.out.println(e.getValue());
                }}); 
        VListScroll.addAdjustmentListener(new AdjustmentListener(){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if (!dataValid()) return;
                j0=e.getValue();
                repaint();
                System.out.println(e.getValue());
                }}); 
        }
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
