
package me.romanow.brs.javaview;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import me.romanow.brs.database.*;
import me.romanow.brs.model.MDBaseUser;
import me.romanow.brs.model.MDNote;
import me.romanow.brs.interfaces.BRSException;
import me.romanow.brs.model.MDItem;


public class NoteView extends javax.swing.JPanel {
    private final static Color bgColor=new Color(236,233,216);
    private final static Color fgColor=new Color(255,255,255);
    NoteViewListener back=null;
    MDBaseUser G=null;
    boolean second=false;
    /** Creates new form NotePanel */
    public NoteView(boolean edit0, MDBaseUser base0, NoteViewListener bk) {
        edit=edit0;
        G=base0;
        initComponents();
        Vector<String> ss=new Vector();
        ss.add("...");
        for(int i=0;i<DBCell.QualityTypes.length;i++)
            ss.add(DBCell.QualityTypes[i]);
        ParamList.setModel(new DefaultComboBoxModel(ss));
        ParamList.setEnabled(true);
        ParamList.setEditable(true);
        ParamList.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                int idx=ParamList.getSelectedIndex();
                if (idx==0) return;
                second=!second;         // ПРОЧЕМУ-ТО 2 события
                if(!second) return;
                noteParams ^=(1<<(idx-1));
                createParamList();
                ParamList.setSelectedIndex(0);
            }});
        back=bk;    
        this.setVisible(false);
        }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L1 = new javax.swing.JLabel();
        WeekR = new javax.swing.JTextField();
        remove = new javax.swing.JButton();
        BrigadaLabel = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        L3 = new javax.swing.JLabel();
        WeekLabel0 = new javax.swing.JLabel();
        BallR = new javax.swing.JTextField();
        MaxBallR = new javax.swing.JTextField();
        WeekR0 = new javax.swing.JTextField();
        L5 = new javax.swing.JLabel();
        SummR = new javax.swing.JTextField();
        Params = new java.awt.TextArea();
        VariantLabel = new javax.swing.JLabel();
        Brigada = new javax.swing.JTextField();
        history = new javax.swing.JButton();
        WeekLabel2 = new javax.swing.JLabel();
        Variant = new javax.swing.JTextField();
        variant = new javax.swing.JButton();
        ParamList = new javax.swing.JComboBox();
        L2 = new javax.swing.JLabel();

        setLayout(null);

        L1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        L1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L1.setText("Оценка");
        add(L1);
        L1.setBounds(80, 0, 60, 20);

        WeekR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        WeekR.setText("0");
        add(WeekR);
        WeekR.setBounds(60, 60, 40, 21);

        remove.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        remove.setBorder(null);
        remove.setBorderPainted(false);
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        add(remove);
        remove.setBounds(190, 190, 30, 30);

        BrigadaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        BrigadaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BrigadaLabel.setText("Бригада");
        add(BrigadaLabel);
        BrigadaLabel.setBounds(0, 90, 50, 20);

        add.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        add.setBorder(null);
        add.setBorderPainted(false);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        add(add);
        add.setBounds(190, 150, 30, 30);

        L3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        L3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L3.setText("Макс. балл");
        add(L3);
        L3.setBounds(100, 30, 70, 20);

        WeekLabel0.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        WeekLabel0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        WeekLabel0.setText("Срок сдачи");
        add(WeekLabel0);
        WeekLabel0.setBounds(100, 60, 70, 20);

        BallR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        BallR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BallRActionPerformed(evt);
            }
        });
        add(BallR);
        BallR.setBounds(60, 30, 40, 21);

        MaxBallR.setEditable(false);
        MaxBallR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MaxBallR.setText("0");
        add(MaxBallR);
        MaxBallR.setBounds(180, 30, 40, 21);

        WeekR0.setEditable(false);
        WeekR0.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add(WeekR0);
        WeekR0.setBounds(180, 60, 40, 21);

        L5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        L5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L5.setText("Итог");
        add(L5);
        L5.setBounds(100, 90, 30, 20);

        SummR.setEditable(false);
        SummR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        SummR.setText("0");
        add(SummR);
        SummR.setBounds(140, 90, 80, 21);

        Params.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add(Params);
        Params.setBounds(10, 150, 160, 80);

        VariantLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        VariantLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VariantLabel.setText("Вариант");
        add(VariantLabel);
        VariantLabel.setBounds(50, 240, 50, 20);

        Brigada.setEditable(false);
        Brigada.setBackground(new java.awt.Color(236, 233, 216));
        Brigada.setText("...");
        add(Brigada);
        Brigada.setBounds(60, 90, 40, 20);

        history.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/date.png"))); // NOI18N
        history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyActionPerformed(evt);
            }
        });
        add(history);
        history.setBounds(180, 230, 40, 40);

        WeekLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        WeekLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        WeekLabel2.setText("Неделя");
        add(WeekLabel2);
        WeekLabel2.setBounds(0, 60, 50, 20);

        Variant.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add(Variant);
        Variant.setBounds(110, 240, 60, 21);

        variant.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        variant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        variant.setBorder(null);
        variant.setBorderPainted(false);
        variant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                variantActionPerformed(evt);
            }
        });
        add(variant);
        variant.setBounds(10, 240, 30, 30);

        ParamList.setEditable(true);
        ParamList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        add(ParamList);
        ParamList.setBounds(10, 120, 210, 21);

        L2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        L2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L2.setText("Балл");
        add(L2);
        L2.setBounds(10, 30, 30, 20);
    }// </editor-fold>//GEN-END:initComponents
    //--------------------------------------------------------------------------
    private int noteParams=0;
    private boolean edit=false;
    public void setBrigada(String ss){
        Brigada.setText(ss);
        }
    public void setEditable(boolean ss){
        edit=ss;
        }    
    private void createParamList(){
        String ss="";
        int vv=noteParams;
        for(int i=0;i<DBCell.QualityTypes.length;i++){
            if ((vv & 1)!=0) 
                ss+=DBCell.QualityTypes[i]+"\n";
            vv>>=1;
            }
        Params.setText(ss);
        }
    public void setSumBall(double ball){
        SummR.setText(MDItem.printBall(ball)+" ("+MDItem.ECTS((int)ball)+")");
        }
    //--------------------------------------------------------------------------
    public void setEditable(Component cc,boolean enb){
        if (cc instanceof JTextField)
            ((JTextField)cc).setEditable(enb);
        else
            cc.setEnabled(enb);
        cc.setBackground(enb ? fgColor : bgColor);
        }
    //---------------------------------------------------------------------------
    public void clearAllView(){
        Variant.setText("...");
        SummR.setText("...");
        BallR.setText("");
        WeekR.setText("...");
        WeekR0.setText("...");
        MaxBallR.setText("...");
        Params.setText("");
        Brigada.setText("");
        }
    //----------------------------------------------------------------------------
    public void setNote(MDNote note,double sum){
        Variant.setText("...");
        SummR.setText("...");
        BallR.setText("");
        WeekR.setText("...");
        Params.setText("");
        Brigada.setText("");
        noteParams=0;
        if (!G.testStudentCell()) return;
        setSumBall(sum);
        MaxBallR.setText(""+G.cellItem.getBall());
        int cType=G.cellItem.getCType();
        byte bb[]=DBCell.NoteParamValues[cType];  
         String ss="...";
         int k=0;
         if (G.studentItem.brigade!=0) k=G.studentItem.brigade;
         if (k!=0) ss=""+k+"("+(G.studentItem.second ? "2)":"1)");
         setBrigada(ss);
         int week=0;
         WeekR0.setText("");
         week=G.cellItem.week;
         if (bb[DBCell.idxSecond]!=0 && G.studentItem.second && G.rating.getSecond())
            week=G.cellItem.week2;
         if (week!=0) WeekR0.setText(""+week);
         if (note==null) return;
         Variant.setText(note.getVariant());
         if (!note.getRemoved()){
            G.calcBall(note);
            BallR.setText(MDItem.printBall(note.getBall()));
            if (bb[DBCell.idxCData]==1)
                WeekR.setText(""+note.getWeek());
            if (bb[DBCell.idxParams]==1)
                noteParams=note.getParams();
            else
                noteParams=0;
            createParamList();
            }
        }
    //--------------------------------------------------------------------------
    public MDNote getNote() throws Throwable{
        if (!G.testStudentCell()) return null;
        int cType=G.cellItem.getCType();
        byte bb[]=DBCell.NoteParamValues[cType];
        double bl=0;
        int wk=0;
        if (bb[DBCell.idxManual]==1) 
            try {
                bl=Integer.parseInt(BallR.getText());
                } catch(Throwable e1){ throw new BRSException("Нечисловое значение");   }
        if (bb[DBCell.idxCData]==1)
            try {
                wk=Integer.parseInt(WeekR.getText());
                } catch(Throwable e1){ throw new BRSException("Нечисловое значение");   }
        MDNote newNote=new MDNote(
                G.studentItem.getId(),
                G.rating.getId(),
                G.cellItem.getId(),
                bl,wk);
        if (bb[DBCell.idxParams]!=0) 
            newNote.setParams(noteParams);
        if (bb[DBCell.idxManual]==0){
            G.calcBall(newNote);
            BallR.setText(MDItem.printBall(newNote.getBall()));            
            }
        return newNote;
        }

    void setNoteVisibleState(boolean visible){
        setVisible(visible);
        if (!visible) return;
        add.setVisible(edit);
        remove.setVisible(edit);
        history.setVisible(edit);
        variant.setVisible(edit);
        byte cellParams[]=G.cellItem.getCellParams();
        WeekR0.setText("");  
        MaxBallR.setText(""+G.cellItem.getBall());
        BallR.setText("...");
        setEditable(BallR, cellParams[DBCell.idxManual]!=0 && edit); 
        setEditable(ParamList,cellParams[DBCell.idxParams]!=0 && edit);
        setEditable(Params,cellParams[DBCell.idxParams]!=0 && edit);
        setEditable(Variant,edit);
        setEditable(WeekR,cellParams[DBCell.idxCData]!=0 && edit);
        }

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        try { back.inRemoveNote(); } catch(Throwable ee){ back.onError(ee); }
		}//GEN-LAST:event_removeActionPerformed
		

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        try { back.inInsertNote(); } catch(Throwable ee){ back.onError(ee); }
        }//GEN-LAST:event_addActionPerformed

    private void historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyActionPerformed
        try{ back.inNoteHistory(); } catch(Throwable ee){ back.onError(ee); }
    }//GEN-LAST:event_historyActionPerformed

    private void variantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_variantActionPerformed
        if (Variant.getText().length()==0) return;
        if (Variant.getText().equals("...")) return;
        try { back.inInsertVariant(Variant.getText()); } catch(Throwable ee){ back.onError(ee); }
    }//GEN-LAST:event_variantActionPerformed

    private void BallRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BallRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BallRActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BallR;
    private javax.swing.JTextField Brigada;
    private javax.swing.JLabel BrigadaLabel;
    private javax.swing.JLabel L1;
    private javax.swing.JLabel L2;
    private javax.swing.JLabel L3;
    private javax.swing.JLabel L5;
    private javax.swing.JTextField MaxBallR;
    private javax.swing.JComboBox ParamList;
    private java.awt.TextArea Params;
    private javax.swing.JTextField SummR;
    private javax.swing.JTextField Variant;
    private javax.swing.JLabel VariantLabel;
    private javax.swing.JLabel WeekLabel0;
    private javax.swing.JLabel WeekLabel2;
    private javax.swing.JTextField WeekR;
    private javax.swing.JTextField WeekR0;
    private javax.swing.JButton add;
    private javax.swing.JButton history;
    private javax.swing.JButton remove;
    private javax.swing.JButton variant;
    // End of variables declaration//GEN-END:variables
}