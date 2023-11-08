package me.romanow.brs.javaview;

import me.romanow.brs.model.MDNote;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import me.romanow.brs.Values;
import me.romanow.brs.controller.ViewController;
import me.romanow.brs.controller.ViewControllerCommands;
import me.romanow.brs.controller.ViewControllerListener;
import me.romanow.brs.database.*;
import me.romanow.brs.interfaces.*;
import me.romanow.brs.model.*;


    
public class ClientOn extends ClientBase implements ViewControllerListener{
    public final static int MaxFileLength = 5;
    double mid=0;
    int nn=0;
    ClientShow ww=null;
    private TableComboBox ratingList=new TableComboBox();
    private TableComboBox studentList=new TableComboBox();
    private TableComboBox cellList=new TableComboBox();
    private TableComboBox eventList=new TableComboBox();
    private MultipleList paramList=null;
    private MultipleList propuskList=null;
    private final static Color bgColor=new Color(236,233,216);
    private final static Color fgColor=new Color(255,255,255);
    private String propuskLine="";
    private ViewControllerCommands ctrl=null;
    private boolean second=false;
    private TableData table=null;

    @Override
    public synchronized void setBusy(boolean busy){
        super.setBusy(busy);
        reConnect.setVisible(busy);
        }
    @Override
    public void onErrorMessage(String message) {
        setMessage(message,Values.stateRed);
        }
    public void fatal(final Throwable ee){
        java.awt.EventQueue.invokeLater(new Runnable(){
        public void run() {
            try {
                try { user.flush(); } catch (Throwable e2){}
                ClientOn.super.fatal(ee);
                BRSException e2=new BRSException(ee);
                ee.printStackTrace(System.out);
                setMessage(e2.getMessage(),Values.stateRed);
                if (e2.getCode()==BRSException.msg || e2.getCode()==BRSException.warn) return;
                if (e2.getCode()==BRSException.net || e2.getCode()==BRSException.sql || e2.getCode()==BRSException.serv){
                    reConnect.setVisible(true);
                    }
                ctrl.setState(0);
                } catch(Throwable ee){}
            }});
        }
    
    public void setBase(ClientBase pp){
        super.setBase(pp);
        initComponents();
        try {
            Component []comp = this.getComponents();
            // Тестер для БАГА
            // ctrl.getSum();          
            ctrl=new ViewController(user,this);
            if (cash)
                ctrl.testLocalRatingChanges();
            else
                ctrl.updateAllLocalRatings();
            Groups2List.addItem("1");
            Groups2List.addItem("2");
            reConnect.setVisible(false);
            Admin.setVisible(cash==false && !admin && user.tutor.isAdmin());
            paramList=new MultipleList(Params,DBCell.QualityTypes);
            ratingList=new TableComboBox(RatingList, new DBRating(),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    try {                                   //002+
                        setMessage("",Values.stateGreen);
                        ctrl.loadRating(item.getId());
                        studentList.setList(user.rating.groups.students.toArray());  
                        cellList.setList(user.rating.course.cells.toArray());
                        eventList.setList(user.rating.events.toArray());  
                        user.testEdit();
                        user.editEnabled=true;
                        ctrl.setState(1);
                        propuskList = new MultipleList(PropuskList,user.rating.groups.students.toArray());
                    } catch(Throwable e1){ fatal(e1); }
                }});                                        //002-
            if (admin)                                      //001
                user.getRatingList(0,0);                    //001
            else                                            //001
                user.getRatingList(user.tutor.getId(),1);   //001    
            ratingList.setList(user.ratingList);
            studentList=new TableComboBox(StudentList, new DBStudent(),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    try {
                        setMessage("");                     //003+
                        if (user.studentItem==item) return;
                        user.studentItem=(MDStudent)item;
                        ctrl.setState(3);                   //003-
                        } catch(Throwable e1){ fatal(e1); }
                   }});  
            //----------------------------------------------------------------------------
            cellList=new TableComboBox(CellList, new DBCell(),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    try {
                        setMessage("");                     //007+
                        user.cellItem=(MDCell)item;
                        ctrl.setState(2);                   //007-
                        } catch(Throwable e1){ fatal(e1); }
                }});
            //----------------------------------------------------------------------------
            eventList=new TableComboBox(EventList, new DBEvent(),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    setMessage("");
                    try {
                        user.eventItem=(DBEvent)item;
                        propuskList.setSelectedMarks(user.createPropuskMarks());
                        }
                        catch(Throwable e2){ fatal(e2); }
                    }});
            //----------------------------------------------------------------------------
            this.setBounds(350, 200, 740, 470);
            ctrl.setState(0);
            show();
            onOpen();
            } catch(Throwable e1){ 
                fatal(e1); 
                onClose();
                }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        RatingButton2 = new javax.swing.JButton();
        RatingButton1 = new javax.swing.JButton();
        RatingButton3 = new javax.swing.JButton();
        RatingButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        html4 = new javax.swing.JButton();
        html1 = new javax.swing.JButton();
        pdf4 = new javax.swing.JButton();
        html3 = new javax.swing.JButton();
        Brigada = new javax.swing.JTextField();
        blabel2 = new javax.swing.JLabel();
        bbutton = new javax.swing.JButton();
        blabel1 = new javax.swing.JLabel();
        html2 = new javax.swing.JButton();
        pdf2 = new javax.swing.JButton();
        pdf1 = new javax.swing.JButton();
        pdf3 = new javax.swing.JButton();
        MES = new java.awt.TextArea();
        Week1 = new javax.swing.JTextField();
        Week2 = new javax.swing.JTextField();
        Week1Label = new javax.swing.JLabel();
        WeekButton = new javax.swing.JButton();
        Week2Label = new javax.swing.JLabel();
        labelP = new javax.swing.JLabel();
        labelR = new javax.swing.JLabel();
        RatingList = new javax.swing.JComboBox();
        CellList = new javax.swing.JComboBox();
        StudentList = new javax.swing.JComboBox();
        Groups2List = new javax.swing.JComboBox();
        notePanel = new javax.swing.JPanel();
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
        VariantLabel = new javax.swing.JLabel();
        Brigada1 = new javax.swing.JTextField();
        history = new javax.swing.JButton();
        WeekLabel2 = new javax.swing.JLabel();
        Variant = new javax.swing.JTextField();
        variant = new javax.swing.JButton();
        FTP2 = new javax.swing.JButton();
        FTP3 = new javax.swing.JButton();
        RLabel8 = new javax.swing.JLabel();
        FTP1 = new javax.swing.JButton();
        FTP4 = new javax.swing.JButton();
        RLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Params = new javax.swing.JList();
        L2 = new javax.swing.JLabel();
        RLabel3 = new javax.swing.JLabel();
        propuskPanel = new javax.swing.JPanel();
        BP2 = new javax.swing.JButton();
        EventList = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        PropuskList = new javax.swing.JList();
        BP1 = new javax.swing.JButton();
        BP3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        BP4 = new javax.swing.JButton();
        reConnect = new javax.swing.JButton();
        RLabel11 = new javax.swing.JLabel();
        Compress = new javax.swing.JButton();
        LoadFiles = new javax.swing.JButton();
        RLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        RLabel4 = new javax.swing.JLabel();
        RLabel5 = new javax.swing.JLabel();
        FileCount = new javax.swing.JTextField();
        Admin = new javax.swing.JButton();
        LoadAllFiles = new javax.swing.JButton();
        RLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Студент");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 180, 60, 20);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Ед.контроля");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 80, 80, 20);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Рейтинг (Предмет / Группа)");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(80, 20, 170, 20);

        RatingButton2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RatingButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/table.png"))); // NOI18N
        RatingButton2.setBorder(null);
        RatingButton2.setBorderPainted(false);
        RatingButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RatingButton2MouseClicked(evt);
            }
        });
        RatingButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatingButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(RatingButton2);
        RatingButton2.setBounds(90, 240, 30, 30);

        RatingButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RatingButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/table.png"))); // NOI18N
        RatingButton1.setBorder(null);
        RatingButton1.setBorderPainted(false);
        RatingButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RatingButton1MouseClicked(evt);
            }
        });
        RatingButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatingButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(RatingButton1);
        RatingButton1.setBounds(90, 140, 30, 30);

        RatingButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/table.png"))); // NOI18N
        RatingButton3.setBorder(null);
        RatingButton3.setBorderPainted(false);
        RatingButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RatingButton3MouseClicked(evt);
            }
        });
        RatingButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatingButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(RatingButton3);
        RatingButton3.setBounds(90, 280, 30, 33);

        RatingButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/table.png"))); // NOI18N
        RatingButton4.setBorder(null);
        RatingButton4.setBorderPainted(false);
        RatingButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RatingButton4MouseClicked(evt);
            }
        });
        RatingButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RatingButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(RatingButton4);
        RatingButton4.setBounds(90, 320, 33, 33);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(250, 20, 2, 240);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(490, 20, 2, 240);

        html4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/firefox.png"))); // NOI18N
        html4.setBorder(null);
        html4.setBorderPainted(false);
        html4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                html4MouseClicked(evt);
            }
        });
        html4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                html4ActionPerformed(evt);
            }
        });
        getContentPane().add(html4);
        html4.setBounds(130, 320, 33, 33);

        html1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        html1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/firefox.png"))); // NOI18N
        html1.setBorder(null);
        html1.setBorderPainted(false);
        html1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                html1MouseClicked(evt);
            }
        });
        html1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                html1ActionPerformed(evt);
            }
        });
        getContentPane().add(html1);
        html1.setBounds(130, 140, 30, 33);

        pdf4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/pdf.png"))); // NOI18N
        pdf4.setBorder(null);
        pdf4.setBorderPainted(false);
        pdf4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pdf4MouseClicked(evt);
            }
        });
        pdf4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdf4ActionPerformed(evt);
            }
        });
        getContentPane().add(pdf4);
        pdf4.setBounds(170, 320, 33, 33);

        html3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/firefox.png"))); // NOI18N
        html3.setBorder(null);
        html3.setBorderPainted(false);
        html3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                html3MouseClicked(evt);
            }
        });
        html3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                html3ActionPerformed(evt);
            }
        });
        getContentPane().add(html3);
        html3.setBounds(130, 280, 30, 33);

        Brigada.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Brigada.setText("0");
        Brigada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BrigadaMouseClicked(evt);
            }
        });
        getContentPane().add(Brigada);
        Brigada.setBounds(90, 210, 30, 21);

        blabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        blabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        blabel2.setText("П/г");
        getContentPane().add(blabel2);
        blabel2.setBounds(110, 210, 30, 20);

        bbutton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        bbutton.setBorder(null);
        bbutton.setBorderPainted(false);
        bbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bbuttonMouseClicked(evt);
            }
        });
        bbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(bbutton);
        bbutton.setBounds(210, 210, 30, 33);

        blabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        blabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        blabel1.setText("Бригада");
        getContentPane().add(blabel1);
        blabel1.setBounds(30, 210, 50, 20);

        html2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/firefox.png"))); // NOI18N
        html2.setBorder(null);
        html2.setBorderPainted(false);
        html2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                html2MouseClicked(evt);
            }
        });
        html2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                html2ActionPerformed(evt);
            }
        });
        getContentPane().add(html2);
        html2.setBounds(130, 240, 30, 33);

        pdf2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/pdf.png"))); // NOI18N
        pdf2.setBorder(null);
        pdf2.setBorderPainted(false);
        pdf2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pdf2MouseClicked(evt);
            }
        });
        pdf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdf2ActionPerformed(evt);
            }
        });
        getContentPane().add(pdf2);
        pdf2.setBounds(170, 240, 30, 30);

        pdf1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pdf1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/pdf.png"))); // NOI18N
        pdf1.setBorder(null);
        pdf1.setBorderPainted(false);
        pdf1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pdf1MouseClicked(evt);
            }
        });
        pdf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdf1ActionPerformed(evt);
            }
        });
        getContentPane().add(pdf1);
        pdf1.setBounds(170, 140, 30, 30);

        pdf3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/pdf.png"))); // NOI18N
        pdf3.setBorder(null);
        pdf3.setBorderPainted(false);
        pdf3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pdf3MouseClicked(evt);
            }
        });
        pdf3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdf3ActionPerformed(evt);
            }
        });
        getContentPane().add(pdf3);
        pdf3.setBounds(170, 280, 30, 33);

        MES.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(MES);
        MES.setBounds(50, 360, 670, 40);

        Week1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Week1.setText("0");
        Week1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Week1MouseClicked(evt);
            }
        });
        Week1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Week1ActionPerformed(evt);
            }
        });
        getContentPane().add(Week1);
        Week1.setBounds(90, 110, 30, 21);

        Week2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Week2.setText("0");
        Week2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Week2MouseClicked(evt);
            }
        });
        getContentPane().add(Week2);
        Week2.setBounds(160, 110, 30, 21);

        Week1Label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Week1Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Week1Label.setText("Неделя п/г 1");
        getContentPane().add(Week1Label);
        Week1Label.setBounds(0, 110, 80, 20);

        WeekButton.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        WeekButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        WeekButton.setBorder(null);
        WeekButton.setBorderPainted(false);
        WeekButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        WeekButton.setName(""); // NOI18N
        WeekButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WeekButtonMouseClicked(evt);
            }
        });
        WeekButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WeekButtonActionPerformed(evt);
            }
        });
        getContentPane().add(WeekButton);
        WeekButton.setBounds(210, 110, 33, 30);

        Week2Label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Week2Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Week2Label.setText("п/г 2");
        getContentPane().add(Week2Label);
        Week2Label.setBounds(120, 110, 30, 20);

        labelP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelP.setText("Пропуски");
        getContentPane().add(labelP);
        labelP.setBounds(20, 320, 60, 20);

        labelR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelR.setText("Вся группа");
        getContentPane().add(labelR);
        labelR.setBounds(20, 280, 70, 20);

        RatingList.setEditable(true);
        RatingList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(RatingList);
        RatingList.setBounds(10, 50, 230, 21);

        CellList.setEditable(true);
        CellList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(CellList);
        CellList.setBounds(90, 80, 150, 21);

        StudentList.setEditable(true);
        StudentList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(StudentList);
        StudentList.setBounds(90, 180, 150, 21);

        Groups2List.setEditable(true);
        Groups2List.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(Groups2List);
        Groups2List.setBounds(150, 210, 50, 21);

        notePanel.setLayout(null);

        L1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        L1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L1.setText("Оценка");
        notePanel.add(L1);
        L1.setBounds(60, 0, 60, 20);

        WeekR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        WeekR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WeekRMouseClicked(evt);
            }
        });
        notePanel.add(WeekR);
        WeekR.setBounds(70, 60, 40, 21);

        remove.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        remove.setBorder(null);
        remove.setBorderPainted(false);
        remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeMouseClicked(evt);
            }
        });
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        notePanel.add(remove);
        remove.setBounds(200, 200, 30, 30);

        BrigadaLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        BrigadaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BrigadaLabel.setText("Бригада");
        notePanel.add(BrigadaLabel);
        BrigadaLabel.setBounds(10, 90, 50, 20);

        add.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        add.setBorder(null);
        add.setBorderPainted(false);
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        notePanel.add(add);
        add.setBounds(200, 120, 30, 30);

        L3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        L3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L3.setText("Макс. балл");
        notePanel.add(L3);
        L3.setBounds(110, 30, 70, 20);

        WeekLabel0.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        WeekLabel0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        WeekLabel0.setText("Срок сдачи");
        notePanel.add(WeekLabel0);
        WeekLabel0.setBounds(110, 60, 70, 20);

        BallR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        BallR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BallRMouseClicked(evt);
            }
        });
        BallR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BallRActionPerformed(evt);
            }
        });
        notePanel.add(BallR);
        BallR.setBounds(70, 30, 40, 21);

        MaxBallR.setEditable(false);
        MaxBallR.setBackground(new java.awt.Color(236, 233, 216));
        MaxBallR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MaxBallR.setText("0");
        MaxBallR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MaxBallRMouseClicked(evt);
            }
        });
        notePanel.add(MaxBallR);
        MaxBallR.setBounds(190, 30, 40, 21);

        WeekR0.setEditable(false);
        WeekR0.setBackground(new java.awt.Color(236, 233, 216));
        WeekR0.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        WeekR0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                WeekR0MouseClicked(evt);
            }
        });
        notePanel.add(WeekR0);
        WeekR0.setBounds(190, 60, 40, 21);

        L5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        L5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L5.setText("Итог");
        notePanel.add(L5);
        L5.setBounds(110, 90, 30, 20);

        SummR.setEditable(false);
        SummR.setBackground(new java.awt.Color(236, 233, 216));
        SummR.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        SummR.setText("0");
        SummR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SummRMouseClicked(evt);
            }
        });
        notePanel.add(SummR);
        SummR.setBounds(150, 90, 80, 21);

        VariantLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        VariantLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        VariantLabel.setText("Вариант");
        notePanel.add(VariantLabel);
        VariantLabel.setBounds(20, 240, 50, 20);

        Brigada1.setEditable(false);
        Brigada1.setBackground(new java.awt.Color(236, 233, 216));
        Brigada1.setText("...");
        Brigada1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Brigada1MouseClicked(evt);
            }
        });
        notePanel.add(Brigada1);
        Brigada1.setBounds(70, 90, 40, 20);

        history.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        history.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/date.png"))); // NOI18N
        history.setBorder(null);
        history.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyMouseClicked(evt);
            }
        });
        history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyActionPerformed(evt);
            }
        });
        notePanel.add(history);
        history.setBounds(190, 280, 40, 40);

        WeekLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        WeekLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        WeekLabel2.setText("Неделя");
        notePanel.add(WeekLabel2);
        WeekLabel2.setBounds(10, 60, 50, 20);

        Variant.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Variant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VariantMouseClicked(evt);
            }
        });
        notePanel.add(Variant);
        Variant.setBounds(80, 240, 60, 21);

        variant.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        variant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        variant.setBorder(null);
        variant.setBorderPainted(false);
        variant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                variantMouseClicked(evt);
            }
        });
        variant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                variantActionPerformed(evt);
            }
        });
        notePanel.add(variant);
        variant.setBounds(150, 240, 30, 30);

        FTP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/synch.png"))); // NOI18N
        FTP2.setBorder(null);
        FTP2.setBorderPainted(false);
        FTP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FTP2MouseClicked(evt);
            }
        });
        FTP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP2ActionPerformed(evt);
            }
        });
        notePanel.add(FTP2);
        FTP2.setBounds(100, 280, 40, 40);

        FTP3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/download.png"))); // NOI18N
        FTP3.setBorder(null);
        FTP3.setBorderPainted(false);
        FTP3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FTP3MouseClicked(evt);
            }
        });
        FTP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP3ActionPerformed(evt);
            }
        });
        notePanel.add(FTP3);
        FTP3.setBounds(50, 280, 40, 40);

        RLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel8.setText("Архив");
        notePanel.add(RLabel8);
        RLabel8.setBounds(100, 320, 90, 15);

        FTP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/synch.png"))); // NOI18N
        FTP1.setBorder(null);
        FTP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FTP1MouseClicked(evt);
            }
        });
        FTP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP1ActionPerformed(evt);
            }
        });
        notePanel.add(FTP1);
        FTP1.setBounds(10, 280, 40, 40);

        FTP4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/download.png"))); // NOI18N
        FTP4.setBorder(null);
        FTP4.setBorderPainted(false);
        FTP4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FTP4MouseClicked(evt);
            }
        });
        FTP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FTP4ActionPerformed(evt);
            }
        });
        notePanel.add(FTP4);
        FTP4.setBounds(140, 280, 40, 40);

        RLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel10.setText("Отчет");
        notePanel.add(RLabel10);
        RLabel10.setBounds(20, 320, 40, 15);

        Params.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Params.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ParamsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Params);

        notePanel.add(jScrollPane3);
        jScrollPane3.setBounds(20, 120, 170, 110);

        L2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        L2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        L2.setText("Балл");
        notePanel.add(L2);
        L2.setBounds(20, 30, 30, 20);

        RLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel3.setText("Всё хранилище");
        notePanel.add(RLabel3);
        RLabel3.setBounds(490, 340, 90, 15);

        getContentPane().add(notePanel);
        notePanel.setBounds(250, 20, 240, 320);

        propuskPanel.setLayout(null);

        BP2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BP2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        BP2.setBorder(null);
        BP2.setBorderPainted(false);
        BP2.setName(""); // NOI18N
        BP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BP2MouseClicked(evt);
            }
        });
        BP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BP2ActionPerformed(evt);
            }
        });
        propuskPanel.add(BP2);
        BP2.setBounds(200, 40, 30, 30);

        EventList.setEditable(true);
        EventList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        propuskPanel.add(EventList);
        EventList.setBounds(10, 40, 170, 21);

        PropuskList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        PropuskList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PropuskListMouseClicked(evt);
            }
        });
        PropuskList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                PropuskListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(PropuskList);

        propuskPanel.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 180, 210);

        BP1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        BP1.setBorder(null);
        BP1.setBorderPainted(false);
        BP1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BP1MouseClicked(evt);
            }
        });
        BP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BP1ActionPerformed(evt);
            }
        });
        propuskPanel.add(BP1);
        BP1.setBounds(200, 0, 30, 30);

        BP3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BP3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        BP3.setBorder(null);
        BP3.setBorderPainted(false);
        BP3.setName(""); // NOI18N
        BP3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BP3MouseClicked(evt);
            }
        });
        BP3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BP3ActionPerformed(evt);
            }
        });
        propuskPanel.add(BP3);
        BP3.setBounds(200, 130, 30, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Пропуски занятий");
        propuskPanel.add(jLabel3);
        jLabel3.setBounds(50, 10, 120, 20);

        BP4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        BP4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/info.png"))); // NOI18N
        BP4.setBorder(null);
        BP4.setBorderPainted(false);
        BP4.setName(""); // NOI18N
        BP4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BP4MouseClicked(evt);
            }
        });
        BP4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BP4ActionPerformed(evt);
            }
        });
        propuskPanel.add(BP4);
        BP4.setBounds(190, 240, 40, 40);

        getContentPane().add(propuskPanel);
        propuskPanel.setBounds(490, 10, 230, 290);

        reConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/status_red.png"))); // NOI18N
        reConnect.setBorder(null);
        reConnect.setBorderPainted(false);
        reConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reConnectMouseClicked(evt);
            }
        });
        reConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reConnectActionPerformed(evt);
            }
        });
        getContentPane().add(reConnect);
        reConnect.setBounds(10, 360, 30, 40);

        RLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel11.setText("Правая кнопка мыши на полях и кнопках формы - подсказка");
        getContentPane().add(RLabel11);
        RLabel11.setBounds(50, 400, 650, 15);

        Compress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/date_clear.png"))); // NOI18N
        Compress.setBorder(null);
        Compress.setBorderPainted(false);
        Compress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CompressMouseClicked(evt);
            }
        });
        Compress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompressActionPerformed(evt);
            }
        });
        getContentPane().add(Compress);
        Compress.setBounds(630, 300, 40, 40);

        LoadFiles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/download.png"))); // NOI18N
        LoadFiles.setActionCommand("скачать");
        LoadFiles.setBorder(null);
        LoadFiles.setBorderPainted(false);
        LoadFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoadFilesMouseClicked(evt);
            }
        });
        LoadFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadFilesActionPerformed(evt);
            }
        });
        getContentPane().add(LoadFiles);
        LoadFiles.setBounds(530, 300, 40, 40);

        RLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel2.setText("Исходник");
        getContentPane().add(RLabel2);
        RLabel2.setBounds(350, 340, 80, 15);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/ic_launcher.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 0, 50, 50);

        RLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel4.setText("Файлы группы    ");
        getContentPane().add(RLabel4);
        RLabel4.setBounds(450, 340, 140, 15);

        RLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel5.setText("Отчет");
        getContentPane().add(RLabel5);
        RLabel5.setBounds(260, 340, 80, 15);

        FileCount.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FileCount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FileCountMouseClicked(evt);
            }
        });
        getContentPane().add(FileCount);
        FileCount.setBounds(490, 310, 40, 21);

        Admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/login.png"))); // NOI18N
        Admin.setBorder(null);
        Admin.setBorderPainted(false);
        Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminMouseClicked(evt);
            }
        });
        getContentPane().add(Admin);
        Admin.setBounds(680, 300, 40, 40);

        LoadAllFiles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/download.png"))); // NOI18N
        LoadAllFiles.setActionCommand("скачать");
        LoadAllFiles.setBorder(null);
        LoadAllFiles.setBorderPainted(false);
        LoadAllFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoadAllFilesMouseClicked(evt);
            }
        });
        LoadAllFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadAllFilesActionPerformed(evt);
            }
        });
        getContentPane().add(LoadAllFiles);
        LoadAllFiles.setBounds(580, 300, 40, 40);

        RLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel6.setText("Все файлы");
        getContentPane().add(RLabel6);
        RLabel6.setBounds(580, 340, 70, 15);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ctrl.close();
        onClose();
    }//GEN-LAST:event_formWindowClosing
        

    private void RatingButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatingButton1ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        closeTable();
        table=ctrl.createRatingCellTable(TableWindow.class);
    }//GEN-LAST:event_RatingButton1ActionPerformed

    private void RatingButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatingButton2ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        closeTable();
        table=ctrl.createRatingStudentTable(TableWindow.class);
    }//GEN-LAST:event_RatingButton2ActionPerformed

    private void closeTable(){
        if (table!=null){
            table.closeTable();
            table=null;
            }
        }
       
    private void RatingButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatingButton3ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        closeTable();
        table=ctrl.createRatingTable(TableWindow.class);
    }//GEN-LAST:event_RatingButton3ActionPerformed

    private void RatingButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RatingButton4ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        closeTable();
        table=ctrl.createPropuskTable(TableWindow.class);
    }//GEN-LAST:event_RatingButton4ActionPerformed

    private void BP2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }        //---- УДАЛИТЬ СОБЫТИЕ  --------------------------------------------
        setMessage("");
       if (!user.editEnabled || user.eventItem==null) return;
        new YesNoDialog(ClientOn.this,"Удалить занятие "+user.eventItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                ctrl.deleteEvent();
                user.eventItem=null;
                eventList.setList(user.rating.events.toArray()); 
                PropuskList.clearSelection();
                } 
                catch(Throwable e1){ fatal(e1); }
            }
        public void onNo() {}
        });
    }    

    private void html1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_html1ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createRatingCellTable(TableHTML.class);
    }//GEN-LAST:event_html1ActionPerformed

    private void pdf4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdf4ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createPropuskTable(TablePDF.class);
    }//GEN-LAST:event_pdf4ActionPerformed

    private void html3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_html3ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createRatingTable(TableHTML.class);
    }//GEN-LAST:event_html3ActionPerformed

    private void html4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_html4ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createPropuskTable(TableHTML.class);
    }//GEN-LAST:event_html4ActionPerformed

    private void bbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbuttonActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        setMessage("");
        try {
            ctrl.changeBrigade(Brigada.getText(),Groups2List.getSelectedIndex()==1);
            } catch(Throwable e1){ fatal(e1); }
    }//GEN-LAST:event_bbuttonActionPerformed

    private void html2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_html2ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createRatingStudentTable(TableHTML.class);
    }//GEN-LAST:event_html2ActionPerformed

    private void pdf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdf2ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createRatingStudentTable(TablePDF.class);
    }//GEN-LAST:event_pdf2ActionPerformed

    private void pdf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdf1ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createRatingCellTable(TablePDF.class);
    }//GEN-LAST:event_pdf1ActionPerformed

    private void pdf3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdf3ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        ctrl.createRatingTable(TablePDF.class);
    }//GEN-LAST:event_pdf3ActionPerformed

    private void WeekButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WeekButtonActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        setMessage("",Values.stateGreen);
        try {
            ctrl.changeWeek(Week1.getText(),Week2.getText());
        } catch(Throwable e1){ fatal(e1); }
    }//GEN-LAST:event_WeekButtonActionPerformed

    private void CompressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompressActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        if (user.rating==null || !user.editEnabled){
            setMessage("Выберите рейтинг и разрешение редактирования");
            return;
            }
        new YesNoDialog(ClientOn.this,"Удалить старые записи рейтинга",new YesNoListener(){
            public void onYes() {
                try {
                setMessage("Удалено записей - "+user.compressRating());
                    } catch(Throwable e1){ fatal(e1); }
                }
            public void onNo() {}
        });
    }//GEN-LAST:event_CompressActionPerformed

    private void LoadFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadFilesActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        if (user.rating==null || !user.editEnabled){
            setMessage("Выберите рейтинг и разрешение редактирования");
            return;
            }
        new YesNoDialog(ClientOn.this,"Скачать и удалить файлы",new YesNoListener(){
            public void onYes() {
                FileDialog dlg0=new FileDialog(ClientOn.this,"Выберите каталог для скачивания",FileDialog.SAVE);
                dlg0.setFile("a.dat");
                dlg0.show();
                String ss=dlg0.getDirectory();
                final String ss1=ss.substring(0, ss.length()-1);
                new BackGround(new CallBack(){
                    @Override
                    public void onFinish(Object answer) throws Throwable {
                        setMessage("Скачано файлов - "+((Integer)answer).intValue());
                        ctrl.setState(1);
                        }
                    @Override
                    public void onError(Throwable ee) {
                        fatal(ee);
                        }
                    @Override
                        public Object call() throws Throwable {
                        return new Integer(user.getAndRemoveRatingFiles(ss1));
                        }
                    });
                }
            public void onNo() {}
        });
    }//GEN-LAST:event_LoadFilesActionPerformed

    private void Week1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Week1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Week1ActionPerformed


    private void BP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BP1ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        new ValueDialog(ClientOn.this,"Добавить занятие",new ValueListener(){
            @Override
            public void onSelect(String value) {
                try {
                    setMessage("");
                    ctrl.insertEvent(value,0);
                    ctrl.changeEvents();
                    eventList.setList(user.rating.events.toArray());
                    user.eventItem=null;
                } catch(Throwable  e2){ fatal(e2); }                
            }
            @Override
            public void onError(String value) {
                }
        });
    }//GEN-LAST:event_BP1ActionPerformed

    private void BP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BP3ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        try {
            ctrl.testState(1);
            if (user.eventItem==null){
                setMessage("Не выбрано событие");
                return;
                }
            propuskList.fixChanges();
            int xx[]=propuskList.getChangedIndices();
            for(int i=0;i<xx.length;i++){
                int id=user.rating.groups.students.get(xx[i]).getId();
                ctrl.changeEvent(id, !propuskList.getSelected(xx[i]));
                }
            propuskList.resetChanges();
            ctrl.changeEvents();
            propuskList.setSelectedMarks(user.createPropuskMarks());
        }
        catch(Throwable e2){ fatal(e2); }
    }//GEN-LAST:event_BP3ActionPerformed

    private void FTP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP4ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        try {
            setMessage("");
            final String ss=ctrl.getReadFileName(false);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    setMessage("Файл "+(String)answer+" загружен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.readFile(ss, false);
                    }
                });
        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP4ActionPerformed

    private void FTP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP1ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        try {
            setMessage("");
            final String ss=ctrl.getWriteFileName(true);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    setMessage("Файл "+(String)answer+" сохранен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.writeFile(ss, true);
                    }
                });
        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP1ActionPerformed

    private void FTP3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP3ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        try {
            setMessage("");
            final String ss=ctrl.getReadFileName(true);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    setMessage("Файл "+(String)answer+" загружен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.readFile(ss, true);
                    }
                });
        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP3ActionPerformed

    private void FTP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FTP2ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        try {
            setMessage("");
            final String ss=ctrl.getWriteFileName(false);
            //------------------------------------------------------------------
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer) throws Throwable {
                    setMessage("Файл "+(String)answer+" сохранен");
                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                    public Object call() throws Throwable {
                    return ctrl.writeFile(ss, false);
                    }
                });
        } catch(Throwable  e1){ fatal(e1); return; }
    }//GEN-LAST:event_FTP2ActionPerformed

    private void variantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_variantActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        String variant=Variant.getText();
        if (variant.length()==0 || Variant.getText().equals("...")) {
            setMessage("Не задан вариант");
            return;
        }
        try {
            ctrl.testState(4);
            ctrl.changeVariant(variant);
            }
            catch(Throwable e2){ fatal(e2); }
    }//GEN-LAST:event_variantActionPerformed

    private void historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        try {
            ctrl.testState(4);
            TableData tableSource=user.createNoteHistoryTable();
            if (tableSource==null) return;
            new ClientShow(tableSource);
        } catch(Throwable e2){ fatal(e2); }
    }//GEN-LAST:event_historyActionPerformed

    private void BallRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BallRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BallRActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        try {
            paramList.fixChanges();
            ctrl.setParamList(paramList.getSelectedMask());
            ctrl.insertNote(BallR.getText(),WeekR.getText());
        } catch(Throwable e1){ fatal(e1); }
    }//GEN-LAST:event_addActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        new YesNoDialog(ClientOn.this,"Удалить оценку "+user.studentItem.twoWord()+"/"+user.cellItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                ctrl.removeNote();
                } 
                catch(Throwable e1){ fatal(e1); }
            }
        public void onNo() {}
        });
    }//GEN-LAST:event_removeActionPerformed

    private void reConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reConnectActionPerformed
        if (isBusy()){
            new YesNoDialog(ClientOn.this,"Прервать операцию",new YesNoListener(){
                @Override
                public void onYes() {
                    try {
                        user.reconnect();
                        } catch(Throwable e1){ fatal(e1); }
                    }
                @Override
                public void onNo() {
                    }
                });
            return;
            }        
        reConnect.setVisible(false);
        try {
            setMessage("");
            user.reconnect();
        } catch(Throwable e1){ fatal(e1); }
    }//GEN-LAST:event_reConnectActionPerformed

    private void PropuskListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_PropuskListValueChanged
        System.out.println(""+evt.getFirstIndex()+" "+evt.getLastIndex());
    }//GEN-LAST:event_PropuskListValueChanged

    private void exportRating(String ss) throws Throwable{
        MDBaseUserBinFile out=new MDBaseUserBinFile(ss);
        out.setKeyString(user.getDBIdentification().getKeyString());
        ctrl.loadFullRating(user.rating.getId());
        out.save(user);
        setMessage("Рейтинг скопирован в файл");       
        }
    
    private void WeekButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WeekButtonMouseClicked
        if (evt.getButton()==3){
            setMessage("Сохранить срок сдачи (неделя) для подгрупп");
            }
    }//GEN-LAST:event_WeekButtonMouseClicked

    private void Week1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Week1MouseClicked
        if (evt.getButton()==3){
            setMessage("Дата сдачи для группы (первой подгруппы)");
            }        
    }//GEN-LAST:event_Week1MouseClicked

    private void Week2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Week2MouseClicked
        if (evt.getButton()==3){
            setMessage("Дата сдачи для второй подгруппы");
            }
    }//GEN-LAST:event_Week2MouseClicked

    private void RatingButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RatingButton1MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по единице контроля в таблице (левая кнопка мыши по элементу - возврат и позиционирование в это окно)");
            }
    }//GEN-LAST:event_RatingButton1MouseClicked

    private void html1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_html1MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по единице контроля в html-файле");
            }       
    }//GEN-LAST:event_html1MouseClicked

    private void pdf1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pdf1MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по единице контроля в pdf-файле");
            }          
    }//GEN-LAST:event_pdf1MouseClicked

    private void BrigadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BrigadaMouseClicked
        if (evt.getButton()==3){
            setMessage("Номер бригады и подгруппа (список)");
            }   
    }//GEN-LAST:event_BrigadaMouseClicked

    private void bbuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bbuttonMouseClicked
        if (evt.getButton()==3){
            setMessage("Сохранить номер бригады и подгруппы");
            }   
    }//GEN-LAST:event_bbuttonMouseClicked

    private void RatingButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RatingButton2MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по студенту в таблице (левая кнопка мыши по элементу - возврат и позиционирование в это окно)");
            }
    }//GEN-LAST:event_RatingButton2MouseClicked

    private void html2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_html2MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по студенту в html-файле");
            } 
    }//GEN-LAST:event_html2MouseClicked

    private void pdf2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pdf2MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по студенту в pdf-файле");
            }     
    }//GEN-LAST:event_pdf2MouseClicked

    private void RatingButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RatingButton3MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по группев таблице (левая кнопка мыши по элементу - возврат и позиционирование в это окно)");
            }
    }//GEN-LAST:event_RatingButton3MouseClicked

    private void html3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_html3MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по группе в html-файле");
            } 
    }//GEN-LAST:event_html3MouseClicked

    private void pdf3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pdf3MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по группе в pdf-файле");
            }    
    }//GEN-LAST:event_pdf3MouseClicked

    private void RatingButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RatingButton4MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по пропускам в таблице (левая кнопка мыши по элементу - возврат и позиционирование в это окно)");
            }
    }//GEN-LAST:event_RatingButton4MouseClicked

    private void html4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_html4MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по пропускам (вся группа) в html-файле");
            } 
    }//GEN-LAST:event_html4MouseClicked

    private void pdf4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pdf4MouseClicked
        if (evt.getButton()==3){
            setMessage("Данные по пропускам (вся группа) в pdf-файле");
            }
    }//GEN-LAST:event_pdf4MouseClicked

    private void reConnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reConnectMouseClicked
        if (evt.getButton()==3){
            setMessage("Сброс ошибки и повторное соединение с БД");
            }
    }//GEN-LAST:event_reConnectMouseClicked

    private void ParamsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ParamsMouseClicked
        if (evt.getButton()==3){
            setMessage("Показатели качества исполнения (множественный выбор по CTRL+левая кнопка мыши)");
            }
    }//GEN-LAST:event_ParamsMouseClicked

    private void PropuskListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PropuskListMouseClicked
        if (evt.getButton()==3){
            setMessage("Пропуск занятия (множественный выбор по CTRL+левая кнопка мыши)");
            }
    }//GEN-LAST:event_PropuskListMouseClicked

    private void BP3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BP3MouseClicked
        if (evt.getButton()==3){
            setMessage("Сохранить данные о посещаемости занятия");
            }
    }//GEN-LAST:event_BP3MouseClicked

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        if (evt.getButton()==3){
            setMessage("Сохранить оценку (балл, срок сдачи, показатели качества)");
            }
    }//GEN-LAST:event_addMouseClicked

    private void removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMouseClicked
        if (evt.getButton()==3){
            setMessage("Удалить оценку");
            }
    }//GEN-LAST:event_removeMouseClicked

    private void VariantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VariantMouseClicked
        if (evt.getButton()==3){
            setMessage("Вариант задания (допустим нечисловой, напр. 3-7.2а)");
            }
    }//GEN-LAST:event_VariantMouseClicked

    private void variantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_variantMouseClicked
        if (evt.getButton()==3){
            setMessage("Сохранить вариант задания");
            }
    }//GEN-LAST:event_variantMouseClicked

    private void historyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyMouseClicked
        if (evt.getButton()==3){
            setMessage("История редактирования оценки");
            }
    }//GEN-LAST:event_historyMouseClicked

    private void BallRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BallRMouseClicked
        if (evt.getButton()==3){
            setMessage("Балл рейтинга, ввод, если невычисляемый");
            }
    }//GEN-LAST:event_BallRMouseClicked

    private void WeekRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WeekRMouseClicked
        if (evt.getButton()==3){
            setMessage("Фактический срок сдачи (номер недели)");
            }
    }//GEN-LAST:event_WeekRMouseClicked

    private void MaxBallRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaxBallRMouseClicked
        if (evt.getButton()==3){
            setMessage("Нормативная оценка (не редактируется)");
            }
    }//GEN-LAST:event_MaxBallRMouseClicked

    private void WeekR0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_WeekR0MouseClicked
        if (evt.getButton()==3){
            setMessage("Нормативный срок сдачи (не редактируется)");
            }
    }//GEN-LAST:event_WeekR0MouseClicked

    private void Brigada1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Brigada1MouseClicked
        if (evt.getButton()==3){
            setMessage("Бригада/подгруппа (не редактируется)");
            }
    }//GEN-LAST:event_Brigada1MouseClicked

    private void SummRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SummRMouseClicked
        if (evt.getButton()==3){
            setMessage("Общий итоговый рейтинг студента (не редактируется)");
            }
    }//GEN-LAST:event_SummRMouseClicked

    private void FTP1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FTP1MouseClicked
        if (evt.getButton()==3){
            setMessage("Загрузить отчет в хранилище (следите за размером файла)");
            }
    }//GEN-LAST:event_FTP1MouseClicked

    private void FTP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FTP2MouseClicked
        if (evt.getButton()==3){
            setMessage("Загрузить файл в хранилище (архив исходников/данные - следите за размером файла)");
            }

    }//GEN-LAST:event_FTP2MouseClicked

    private void FTP3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FTP3MouseClicked
        if (evt.getButton()==3){
            setMessage("Скачать отчет из хранилища");
            }
    }//GEN-LAST:event_FTP3MouseClicked

    private void FTP4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FTP4MouseClicked
        if (evt.getButton()==3){
            setMessage("Скачать файл из хранилища (архив исходников/данные)");
            }
    }//GEN-LAST:event_FTP4MouseClicked

    private void LoadFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadFilesMouseClicked
        if (evt.getButton()==3){
            setMessage("Скачать и удалить из хранилища все файлы рейтинга (группа - предмет)");
            }
    }//GEN-LAST:event_LoadFilesMouseClicked

    private void CompressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CompressMouseClicked
        if (evt.getButton()==3){
            setMessage("Очистить историю редактирования оценок (оставить по 1 последней записи)");
            }
    }//GEN-LAST:event_CompressMouseClicked

    private void BP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BP2MouseClicked
        if (evt.getButton()==3){
            setMessage("Удалить занятие и данные о его посещаемости");
            }
    }//GEN-LAST:event_BP2MouseClicked

    private void BP1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BP1MouseClicked
        if (evt.getButton()==3){
            setMessage("Добавить занятие для учета его посещаемости");
            }

    }//GEN-LAST:event_BP1MouseClicked

    private void FileCountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FileCountMouseClicked
        if (evt.getButton()==3){
            setMessage("Кол-во файлов в хранилище или автономных изменений (для рейтинга)");
            }

    }//GEN-LAST:event_FileCountMouseClicked

    private void AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminMouseClicked
            if (evt.getButton()==3){
            setMessage("Администрирование БД");
            return;
            }
        newClient(AdminWindow.class);
    }//GEN-LAST:event_AdminMouseClicked

    private void BP4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BP4MouseClicked
        if (evt.getButton()==3){
            setMessage("Инвертировать пропуски");
            return;
            }
    }//GEN-LAST:event_BP4MouseClicked

    private void BP4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BP4ActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        propuskList.invertAll();
    }//GEN-LAST:event_BP4ActionPerformed

    private void LoadAllFilesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoadAllFilesMouseClicked
        if (evt.getButton()==3){
            setMessage("Скачать и удалить из хранилища все файлы");
            }
    }//GEN-LAST:event_LoadAllFilesMouseClicked

    private void LoadAllFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadAllFilesActionPerformed
        if (isBusy()){
            setMessage("Выполнение фоновой операции");
            return;
            }
        new YesNoDialog(ClientOn.this,"Скачать и удалить ВСЕ файлы",new YesNoListener(){
            public void onYes() {
                FileDialog dlg0=new FileDialog(ClientOn.this,"Выберите каталог для скачивания",FileDialog.SAVE);
                dlg0.setFile("a.dat");
                dlg0.show();
                String ss=dlg0.getDirectory();
                final String ss1=ss.substring(0, ss.length()-1);
                setMessage("");
                new BackGround(new CallBack(){
                    @Override
                    public void onFinish(Object answer) throws Throwable {
                        setMessage("Скачано файлов - "+((Integer)answer).intValue());
                        ctrl.setState(0);
                        }
                    @Override
                    public void onError(Throwable ee) {
                        fatal(ee);
                        }
                    @Override
                    public Object call() throws Throwable {
                        int sum = 0;
                        for(int i=0;i<ratingList.source.length;i++){
                            ctrl.loadRating(ratingList.source[i].getId());
                            sum += user.getAndRemoveRatingFiles(ss1);
                            }
                        return new Integer(sum);
                        }
                    });
                }
            public void onNo() {}
        });

    }//GEN-LAST:event_LoadAllFilesActionPerformed
    
  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Admin;
    private javax.swing.JButton BP1;
    private javax.swing.JButton BP2;
    private javax.swing.JButton BP3;
    private javax.swing.JButton BP4;
    private javax.swing.JTextField BallR;
    private javax.swing.JTextField Brigada;
    private javax.swing.JTextField Brigada1;
    private javax.swing.JLabel BrigadaLabel;
    private javax.swing.JComboBox CellList;
    private javax.swing.JButton Compress;
    private javax.swing.JComboBox EventList;
    private javax.swing.JButton FTP1;
    private javax.swing.JButton FTP2;
    private javax.swing.JButton FTP3;
    private javax.swing.JButton FTP4;
    private javax.swing.JTextField FileCount;
    private javax.swing.JComboBox Groups2List;
    private javax.swing.JLabel L1;
    private javax.swing.JLabel L2;
    private javax.swing.JLabel L3;
    private javax.swing.JLabel L5;
    private javax.swing.JButton LoadAllFiles;
    private javax.swing.JButton LoadFiles;
    private java.awt.TextArea MES;
    private javax.swing.JTextField MaxBallR;
    private javax.swing.JList Params;
    private javax.swing.JList PropuskList;
    private javax.swing.JLabel RLabel10;
    private javax.swing.JLabel RLabel11;
    private javax.swing.JLabel RLabel2;
    private javax.swing.JLabel RLabel3;
    private javax.swing.JLabel RLabel4;
    private javax.swing.JLabel RLabel5;
    private javax.swing.JLabel RLabel6;
    private javax.swing.JLabel RLabel8;
    private javax.swing.JButton RatingButton1;
    private javax.swing.JButton RatingButton2;
    private javax.swing.JButton RatingButton3;
    private javax.swing.JButton RatingButton4;
    private javax.swing.JComboBox RatingList;
    private javax.swing.JComboBox StudentList;
    private javax.swing.JTextField SummR;
    private javax.swing.JTextField Variant;
    private javax.swing.JLabel VariantLabel;
    private javax.swing.JTextField Week1;
    private javax.swing.JLabel Week1Label;
    private javax.swing.JTextField Week2;
    private javax.swing.JLabel Week2Label;
    private javax.swing.JButton WeekButton;
    private javax.swing.JLabel WeekLabel0;
    private javax.swing.JLabel WeekLabel2;
    private javax.swing.JTextField WeekR;
    private javax.swing.JTextField WeekR0;
    private javax.swing.JButton add;
    private javax.swing.JButton bbutton;
    private javax.swing.JLabel blabel1;
    private javax.swing.JLabel blabel2;
    private javax.swing.JButton history;
    private javax.swing.JButton html1;
    private javax.swing.JButton html2;
    private javax.swing.JButton html3;
    private javax.swing.JButton html4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelP;
    private javax.swing.JLabel labelR;
    private javax.swing.JPanel notePanel;
    private javax.swing.JButton pdf1;
    private javax.swing.JButton pdf2;
    private javax.swing.JButton pdf3;
    private javax.swing.JButton pdf4;
    private javax.swing.JPanel propuskPanel;
    private javax.swing.JButton reConnect;
    private javax.swing.JButton remove;
    private javax.swing.JButton variant;
    // End of variables declaration//GEN-END:variables


    @Override
    public String getInputFileName(String title, String defName) throws Throwable {
        FileDialog dlg=new FileDialog(this,title,FileDialog.LOAD);
        defName=defName.replace(' ', '_');
        dlg.setFile(defName);
        dlg.show();
        String fname=dlg.getDirectory();
        if (fname==null) return null;
        fname+="/"+dlg.getFile();
        File ff = new File(fname);
        long ll = ff.length()/1000000;
        if (ff.length()/1000000 > MaxFileLength)
            throw new BRSException(BRSException.msg, "Файл " + ff +" Мб, ограничение "+MaxFileLength+" Мб");
        return fname;
    }
    @Override
    public String getOutputFileName(String title, String defName) throws Throwable {
        FileDialog dlg=new FileDialog(this,title,FileDialog.SAVE);
        dlg.setFile(defName);
        dlg.show();
        String fname=dlg.getDirectory();
        if (fname==null) return null;
        fname+=dlg.getFile();
        return fname;
        }
    @Override
    public void stateChanged(int state) {
        if (state==0) {
            ratingList.set0();
            eventList.set0();
            PropuskList.removeAll();
            }
        if (state==1 || state==0){
            studentList.set0();
            cellList.set0();
            PropuskList.clearSelection();
            closeTable();
            }
        //setMessage("Состояние контроллера:"+ctrl.getState());
        }
    @Override
    public void setRatingVisible(boolean enb){
        //-------------- localMode -------------------------------------------------------
        Compress.setVisible(enb && !cash);
        LoadFiles.setVisible(enb && !cash);
        LoadAllFiles.setVisible(!cash);
        RLabel6.setVisible(!cash);
        RLabel4.setVisible(enb);
        RLabel4.setText(cash ? "Лок. изменения" : "Файлы группы");
        int k=parent.entry.getType();
        if (enb){
            int count=0;
            if (cash){
                count=((MDBaseUserBinFile)user).testAllChanges();
                }
            else{
                try {
                    count = user.getFileCount();
                    } catch (Throwable ee){ FileCount.setText("???");  }
                }
            FileCount.setText(count==0 ? "" : ""+count);
            }
        //--------------------------------------------------------------------------------
        RatingButton3.setVisible(enb);
        RatingButton4.setVisible(enb);
        BP1.setVisible(enb);
        BP2.setVisible(enb);
        BP3.setVisible(enb);
        propuskPanel.setVisible(enb);
        html3.setVisible(enb);
        html4.setVisible(enb);
        pdf3.setVisible(enb);
        pdf4.setVisible(enb);
        labelR.setVisible(enb);
        labelP.setVisible(enb);
        FileCount.setVisible(enb);
        }
    @Override
    public void setCellVisible(boolean enb){
        RatingButton1.setVisible(enb);
        html1.setVisible(enb);
        pdf1.setVisible(enb);
        }
    @Override
    public void setStudentVisible(boolean enb){
        RatingButton2.setVisible(enb);
        html2.setVisible(enb);
        pdf2.setVisible(enb);            
        blabel1.setVisible(enb);
        bbutton.setVisible(enb);            
        Brigada.setVisible(enb);
        }

    @Override
    public void setWeekVisible(boolean enb, String s) {
        Week1Label.setVisible(enb);     //005+
        Week1.setVisible(enb);
        WeekButton.setVisible(enb);
        Week1.setText(s);               //005-
        }    
    @Override
    public void setWeek2Visible(boolean enb, String s) {
        Week2Label.setVisible(enb);     //006+
        Week2.setVisible(enb);
        Week2.setText(s);               //006-
        }
    @Override
    public void setNoteVisible(boolean enb) {
        notePanel.setVisible(enb);      //012
        FTP1.setVisible(enb && !cash);
        FTP2.setVisible(enb && !cash);
        RLabel2.setVisible(enb && !cash);
        RLabel5.setVisible(enb && !cash);
        //noteView.setNoteVisibleState(enb);
        //----------------------------------------------------------------------
        MDNote noteItem=ctrl.getNoteItem();
        //noteView.setNote(noteItem,ctrl.getSum());
        }
    @Override
    public void setBrigadeVisible(boolean enb, String s,boolean enb2, boolean two) {
        blabel2.setVisible(enb);        //012
        Brigada.setText(s);
        Groups2List.setVisible(enb2);
        Groups2List.setSelectedIndex(two ? 1:0);    
        }
    @Override
    public void setArchFileVisible(boolean enb) {
        FTP4.setVisible(enb && !cash);
        }
    @Override
    public void setDocFileVisible(boolean enb) {
        FTP3.setVisible(enb  && !cash);
        }
    @Override
    public void setMaxBall(String var) {
        MaxBallR.setText(var);          //011
        }
    @Override
    public void setBall(String var, boolean editable) {
        BallR.setEditable(editable);    //010
        BallR.setText(var);
        }
    @Override
    public void setSumBall(String var) {
        SummR.setText(var);             //012
        }
    @Override
    public void setVariant(String var) {
        Variant.setText(var);           //009
        }
    @Override
    public void setWeek0(String var) {
        WeekR0.setText(var);            //005
        }
    @Override
    public void setWeek(String var, boolean editable) {
        WeekR.setEditable(editable);    //008
        WeekR.setText(var);
        }
    @Override
    public void setBrigade(String var) {
        Brigada1.setText(var);          //004
        }
    @Override
    public void setParamsVisible(boolean enb,String var,int pars) {
        Params.setVisible(enb);
        paramList.setSelectedIndices(pars);        }
    @Override
    public void selectTableCell(TableData tbl,int row, int col) {
        switch(tbl.getTableType()){
            case ViewController.ratingTableType:
                StudentList.setSelectedIndex(row+1);
                CellList.setSelectedIndex(col);
                break;
            case ViewController.propuskTableType:
                StudentList.setSelectedIndex(row+1);
                EventList.setSelectedIndex(col);
                break;
            case ViewController.cellTableType:
                StudentList.setSelectedIndex(row+1);
                break;                
            case ViewController.studentTableType:
                CellList.setSelectedIndex(row+1);
                break;
                }
        }
    public void onTableClose(TableData tbl) {
        table=null;
        }
    public void ratingIsChanged() {
        try {
            if (table==null) return;
            int type=table.getTableType();
            switch(type){
                case ViewController.ratingTableType:
                    ctrl.createRatingTable(table);      // Для мягкой загрузки
                    break;
                case ViewController.propuskTableType:
                    ctrl.createPropuskTable(table);     // Для мягкой загрузки
                    break;
                case ViewController.cellTableType:
                    user.createRatingCellTable(table);
                    break;
                case ViewController.studentTableType:
                    user.createRatingStudentTable(table);
                    break;
                    }
                table.refreshTable();
            } catch(Throwable ee){ fatal(ee); }
        }

    public void setMessage(String message) {
        setMessage(message,Values.stateGreen);
        }
    @Override
    public void setMessage(String message, int state) {
        MES.setText(message);
        }
    public void setFileCount(int count) {
        if (count==0)
            FileCount.setText("");
        else
            FileCount.setText(""+count);
        }
    @Override
    public String getFileDirectory() {
        return Values.desktopFileDirectory;
        }
    @Override
    public void lastCallBack() {
        }
}
