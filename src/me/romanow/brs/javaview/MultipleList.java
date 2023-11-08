/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.romanow.brs.javaview;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import me.romanow.brs.database.DBCell;
import me.romanow.brs.database.DBItem;
import me.romanow.brs.database.DBNamedItem;

/**
 *
 * @author user
 */
public class MultipleList {
    private JList list=null;
    private String src[]=null;
    private boolean selected[]=null;
    private boolean changed[]=null;
    private boolean second=false;
    private int []getIndices(boolean bb[]){
        int i,nn;
        for(i=0,nn=0;i<bb.length;i++)
            if (bb[i]) nn++;
        int out[]=new int[nn];
        if (nn==0) return out;
        for(i=0,nn=0;i<bb.length;i++)
            if (bb[i]) out[nn++]=i;
        return out;
        }
    private boolean []getMasks(int bb[]){
        boolean out[]=new boolean[src.length];
        for(int i=0;i<bb.length;i++)
            out[bb[i]]=true;
        return out;
        }
    public void invertAll(){
        boolean bb[]=getMasks(list.getSelectedIndices());
        for(int i=0;i<bb.length;i++){
            bb[i]=!bb[i];
            selected[i]=!selected[i];
            }
        list.setSelectedIndices(getIndices(bb));
        }
    public boolean getSelected(int k){ return selected[k]; }
    public void setSelectedIndices(int vv){
        int i,nn,v;
        for(i=0,nn=0,v=vv;i<src.length;i++){
            if ((v&1)!=0) nn++;
            v>>=1;
            }
        int out[]=new int[nn];
        for(i=0,nn=0,v=vv;i<src.length;i++){
            if ((v&1)!=0) out[nn++]=i;
            v>>=1;
            }
        setSelectedIndices(out);
        }
    public void setSelectedIndices(int vv[]){
        list.clearSelection();
        list.setSelectedIndices(vv);
        for(int i=0;i<changed.length; i++) changed[i]=false;
        for(int i=0;i<selected.length; i++) selected[i]=false;
        for (int i=0;i<vv.length;i++) selected[vv[i]]=true;
        }
    public void setSelectedMarks(boolean bb[]){
        list.clearSelection();
        list.setSelectedIndices(getIndices(bb));
        for(int i=0;i<changed.length; i++) changed[i]=false;
        for(int i=0;i<selected.length; i++) selected[i]=bb[i];
        }
    public int []getSelectedIndices(){
        return getIndices(selected);
        }
    public int []getChangedIndices(){
        return getIndices(changed);
        }
    public int getSelectedMask(){
        int v=0;
        for(int i=0;i<selected.length;i++)
            if(selected[i]) v|=(1<<i);
        return v;
        }
    public void showSelection(){
        list.clearSelection();
        list.setSelectedIndices(getIndices(selected));
        }
    private void init(){
        DefaultListModel model=new DefaultListModel();
        for(int i=0;i<src.length;i++)
            model.addElement(src[i]);
        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        selected=new boolean[src.length];
        for(int i=0;i<src.length;i++) selected[i]=false;
        changed=new boolean[src.length];
        for(int i=0;i<src.length;i++) changed[i]=false;
        }
    public MultipleList(JList list, String src[]){
        this.list=list;
        this.src=src;
        init();
        }
    public MultipleList(JList list, DBItem src[]){
        String xx[]=new String[src.length];
        for(int i=0;i<src.length;i++) xx[i]=((DBNamedItem)src[i]).getName();
        this.list=list;
        this.src=xx;
        init();
        }
    public void fixChanges(){
        boolean bb[]=getMasks(list.getSelectedIndices());
        for(int i=0;i<bb.length;i++){
            if (selected[i]!=bb[i]){
                selected[i]=bb[i];
                changed[i]=true;
                }
            }
        }
    public void resetChanges(){
        for(int i=0;i<changed.length;i++){
            changed[i]=false;
            }
        }
}
