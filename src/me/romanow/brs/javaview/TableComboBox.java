package me.romanow.brs.javaview;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import me.romanow.brs.database.*;

public class TableComboBox {
    // Выбор "..." приводит к выбору последней записи без события, устанавливается только программно
    private int oldIdx=-1;
    protected JComboBox list=null;
    protected  DBItem item=null,source[]=null;
    protected  TableChoiceListener callBack;
    public int size(){ 
        if (source==null) return 0;
        return source.length; 
        }
    public DBItem []getSource(){ return source; }
    public DBItem getById(int id0){
        if (source==null) return null;
        for (int i=0;i<source.length;i++)
            if (source[i].getId()==id0)
                return source[i];
        return null;
        }
    public int getIdxById(int id0){
        if (source==null) return -1;
        for (int i=0;i<source.length;i++)
            if (source[i].getId()==id0)
                return i;
        return -1;
        }    
    public TableComboBox(){}
    public TableComboBox(JComboBox c0, DBItem i0,TableChoiceListener lsn){
        list=c0;
        item=i0;
        callBack=lsn;
        list.setEnabled(true);
        list.setEditable(true);
        list.addItem("...");
        list.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                int idx=list.getSelectedIndex();
                if (oldIdx==idx) return;
                if (idx==0){
                    list.setSelectedIndex(oldIdx);
                    return;
                    }
                oldIdx=idx;
                if (callBack==null) return;
                callBack.onSelect(source[idx-1]);
            }});
        }
    public int getSelectedIndex(){
        return list.getSelectedIndex()-1;
        }
    public int getSelectedId(){
        int idx=list.getSelectedIndex();
        if (idx==0) return -1;
        idx--;
        if (source==null || idx>=source.length) return -1;
        return source[idx].getId();
        }
    public String getSelectedName(){
        int idx=list.getSelectedIndex();
        if (idx==0) return "";
        idx--;
        if (source==null || idx>=source.length) return "";
        return ((DBNamedItem)source[idx]).getName();
        }
    public void setList(DBItem src[])throws Throwable{
        Vector<String> ss=new Vector();
        ss.add("...");
        source=src;
        for(int i=0;i<source.length;i++)
            ss.add(source[i].twoWord());
        list.setModel(new DefaultComboBoxModel(ss));
        set0();
        }
    public void setPosition(int idx){
        if (idx<0 || idx>source.length)
            return;
        list.setSelectedIndex(idx);
        }
    public void copyPosition(TableComboBox dst){
        dst.setPosition(list.getSelectedIndex());
        }
    public void setPosition(DBItem item){
        for(int i=0;i<source.length;i++)
            if (item==source[i]){
                list.setSelectedIndex(i+1);
                return;
                }
        list.setSelectedIndex(0);        
        }
    public void set0(){ 
        if (list==null) return;
        oldIdx=0;
        list.setSelectedIndex(0);
        //--------- генерирует событие, но не моделирует источник ----------------
        //Object item=new Object();
        //list.dispatchEvent(new ItemEvent(list,ItemEvent.ITEM_STATE_CHANGED,item,0));
        }
}
