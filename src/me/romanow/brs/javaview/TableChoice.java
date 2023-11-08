package me.romanow.brs.javaview;

import java.awt.AWTEvent;
import me.romanow.brs.interfaces.BRSException;
import me.romanow.brs.connect.DBConnection;
import java.awt.Choice;
import java.awt.Event;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import me.romanow.brs.database.*;

public class TableChoice {
    protected Choice list=null;
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
    public TableChoice(){}
    public TableChoice(Choice c0, DBItem i0,TableChoiceListener lsn){
        list=c0;
        item=i0;
        callBack=lsn;
        list.add("...");
        list.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                int idx=list.getSelectedIndex();
                if (idx==0) return;
                callBack.onSelect(source[idx-1]);
            }});
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
        list.removeAll();
        list.add("...");
        source=src;
        for(int i=0;i<source.length;i++)
            list.add(source[i].toString());
        list.select(0);
        }
    public void setPosition(DBItem item){
        for(int i=0;i<source.length;i++)
            if (item==source[i]){
                list.select(i+1);
                return;
                }
        list.select(0);        
        }
    public void set0(){ 
        if (list==null) return;
        list.select(0);
        }
}
