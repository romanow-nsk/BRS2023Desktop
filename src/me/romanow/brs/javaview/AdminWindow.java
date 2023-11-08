package me.romanow.brs.javaview;




import me.romanow.brs.model.MDNote;
import me.romanow.brs.database.DBNote;
import me.romanow.brs.connect.DBConnection;
import me.romanow.brs.interfaces.BRSException;
import java.io.*;
import java.awt.*;
import java.util.Vector;
import me.romanow.brs.connect.DBSQLiteJDBC;
import me.romanow.brs.database.*;
import me.romanow.brs.interfaces.*;


public class AdminWindow extends ClientBase{
    //----------- ПОКА ЗДЕСЬ ВСЕ ДАННЫЕ ----------------------------------------
    public DBTutor tutorItem=null;
    public DBParams paramItem=null;
    public DBGroups groupItem=null;
    public DBStudent studentItem=null;
    public DBCourse courseItem=null;
    public DBRating ratingItem=null;
    public DBCellRating cellRatingItem=null;
    public DBStudRating studRatingItem=null;
    public DBPermission permissionItem=null;
    public DBItem params[]=null;
    public DBUser login=null;
    public byte cellParams[]=new byte[0];
    public boolean twoGroups=false;
    //--------------------------------------------------------------------------
    public DBConnect conn=null;
    public TableComboBox tutorList=new TableComboBox();
    public TableComboBox groupList=new TableComboBox();
    public TableComboBox courseList=new TableComboBox();
    public TableComboBox paramsList=new TableComboBox();
    public TableComboBox ratingList=new TableComboBox();
    private int selectedCellType=-1;
    private int oldIdx=-1;
    private boolean dbIsOpen=false;
    


    @Override
    public synchronized void setBusy(boolean busy){
        super.setBusy(busy);
        reConnect.setVisible(busy);
        }
    
    private void initView() throws Throwable{
        dbIsOpen=false;
        tutorItem=null;
        tutorList.setList(conn.getList(DBTutor.class));
        courseItem=null;
        courseList.setList(conn.getList(DBCourse.class));
        groupItem=null;
        groupList.setList(conn.getList(DBGroups.class));
        ratingItem=null;
        ratingList.setList(conn.getList(DBRating.class));
        params=conn.getList(DBParams.class);
        paramsList.setList(conn.getList(DBParams.class));
        clearAllView();
        dbIsOpen=true;
        }
    
    public void clearAllView(){
        tutorList.set0();
        groupList.set0();
        courseList.set0();
        ratingList.set0();
        clearAll();
        }
    @Override
    public void onErrorMessage(String message) {
        MES.setText(message);
        }
    public void clearAll(){
        groupItem=null;
        studentItem=null;
        courseItem=null;
        ratingItem=null;
        cellRatingItem=null;
        studRatingItem=null;
        }
    public void fatal(Throwable ee){
        super.fatal(ee);
        BRSException e2=new BRSException(ee);
        MES.setText(e2.getMessage());
        if (e2.getCode()==BRSException.msg || e2.getCode()==BRSException.warn) return;
        if (e2.getCode()==BRSException.net || e2.getCode()==BRSException.sql || e2.getCode()==BRSException.serv){
            reConnect.setVisible(true);
        }
        clearAllView();
        }
    //--------------------------------------------------------------------------
    public AdminWindow() {
        }
    public void setBase(ClientBase base) {
        super.setBase(base);
        try {
            initComponents();
            admin=true;
            conn = user.getConnect();
            setMenuBar(menuBar1);   // Почему не сам ????????????????????????????
            setBounds(150, 120, 700, 350);
            reConnect.setVisible(false);
            paramsList=new TableComboBox(ParamsList, new DBParams(),null);
            //---------------------------------------------------------------------------
            tutorList=new TableComboBox(TutorList, new DBTutor(),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    MES.setText("");
                    try{
                        tutorItem=(DBTutor)item;
                    } catch(Throwable e1){ fatal(e1); }
                }}); 
            //----------------------------------------------------------------------------
            groupList=new TableComboBox(GroupList, new DBGroups(""),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    MES.setText("");
                    try {
                        groupItem=(DBGroups)item;
                        studentItem=null;
                    } catch(Throwable e1){ fatal(e1); }
                }}); 
            //----------------------------------------------------------------------------
            courseList=new TableComboBox(CourseList, new DBCourse(""),new TableChoiceListener(){
                public void onSelect(DBItem item) {
                    MES.setText("");
                    try {
                        courseItem=(DBCourse)item;
                    } catch(Throwable e1){ fatal(e1); }
                }}); 
           ratingList=new TableComboBox(RatingList, new DBRating(),new TableChoiceListener(){
                   public void onSelect(DBItem item) {
                        MES.setText("");
                        ratingItem=(DBRating)item;
                   }});
            initView();
            dbIsOpen=true;
            onOpen();
            //------------------------------------------------------------------
            }
        catch(Throwable e1){ 
            fatal(e1); 
            onClose();
            }
        }
    //==========================================================================


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menuItem2 = new java.awt.MenuItem();
        menuItem3 = new java.awt.MenuItem();
        menuItem4 = new java.awt.MenuItem();
        menuItem5 = new java.awt.MenuItem();
        menuItem6 = new java.awt.MenuItem();
        menuItem1 = new java.awt.MenuItem();
        button1 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        button4 = new javax.swing.JButton();
        button7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        button13 = new javax.swing.JButton();
        button14 = new javax.swing.JButton();
        button25 = new javax.swing.JButton();
        button28 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        button27 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        MES = new java.awt.TextArea();
        button10 = new javax.swing.JButton();
        GroupList = new javax.swing.JComboBox();
        CourseList = new javax.swing.JComboBox();
        TutorList = new javax.swing.JComboBox();
        RatingList = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        ParamsList = new javax.swing.JComboBox();
        button12 = new javax.swing.JButton();
        reConnect = new javax.swing.JButton();
        RLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        button15 = new javax.swing.JButton();
        button29 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        menu1.setActionCommand("Базы");
        menu1.setLabel("Экспорт/Импорт");
        menu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu1ActionPerformed(evt);
            }
        });

        menuItem2.setLabel("Экспорт в файл");
        menuItem2.setName("");
        menuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem2ActionPerformed(evt);
            }
        });
        menu1.add(menuItem2);

        menuItem3.setLabel("Импорт из файла");
        menuItem3.setName("");
        menuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem3ActionPerformed(evt);
            }
        });
        menu1.add(menuItem3);

        menuItem4.setLabel("Экспорт в SQLite");
        menuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem4ActionPerformed(evt);
            }
        });
        menu1.add(menuItem4);

        menuItem5.setLabel("Импорт из  SQLite");
        menuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem5ActionPerformed(evt);
            }
        });
        menu1.add(menuItem5);

        menuItem6.setLabel("Инициализация БД");
        menuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem6ActionPerformed(evt);
            }
        });
        menu1.add(menuItem6);

        menuItem1.setLabel("Очистить хранилище");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        menu1.add(menuItem1);

        menuBar1.add(menu1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        button1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        button1.setBorder(null);
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
            }
        });
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1);
        button1.setBounds(410, 70, 30, 30);
        button1.getAccessibleContext().setAccessibleName("+предмет");

        button3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        button3.setBorder(null);
        button3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button3MouseClicked(evt);
            }
        });
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        getContentPane().add(button3);
        button3.setBounds(200, 70, 30, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Группы");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 10, 50, 20);

        button4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button4.setForeground(new java.awt.Color(240, 240, 240));
        button4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        button4.setBorder(null);
        button4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button4MouseClicked(evt);
            }
        });
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        getContentPane().add(button4);
        button4.setBounds(10, 70, 30, 30);

        button7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        button7.setBorder(null);
        button7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button7MouseClicked(evt);
            }
        });
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        getContentPane().add(button7);
        button7.setBounds(250, 70, 30, 30);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(180, 10, 0, 380);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Предметы ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(250, 10, 70, 20);

        button13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        button13.setBorder(null);
        button13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button13MouseClicked(evt);
            }
        });
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });
        getContentPane().add(button13);
        button13.setBounds(460, 70, 30, 30);

        button14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        button14.setBorder(null);
        button14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button14MouseClicked(evt);
            }
        });
        button14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button14ActionPerformed(evt);
            }
        });
        getContentPane().add(button14);
        button14.setBounds(650, 70, 30, 30);

        button25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        button25.setToolTipText("");
        button25.setBorder(null);
        button25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button25MouseClicked(evt);
            }
        });
        button25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button25ActionPerformed(evt);
            }
        });
        getContentPane().add(button25);
        button25.setBounds(10, 170, 30, 30);

        button28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        button28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/remove.png"))); // NOI18N
        button28.setBorder(null);
        button28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button28MouseClicked(evt);
            }
        });
        button28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button28ActionPerformed(evt);
            }
        });
        getContentPane().add(button28);
        button28.setBounds(200, 170, 30, 30);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Преподаватели");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(10, 110, 100, 20);

        button27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        button27.setBorder(null);
        button27.setName(""); // NOI18N
        button27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button27MouseClicked(evt);
            }
        });
        button27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button27ActionPerformed(evt);
            }
        });
        getContentPane().add(button27);
        button27.setBounds(70, 170, 30, 30);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(218, 30, 0, 420);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(230, 270, 200, 0);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(230, 272, 200, 0);

        MES.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(MES);
        MES.setBounds(50, 220, 590, 40);

        button10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/ic_launcher.png"))); // NOI18N
        button10.setBorder(null);
        button10.setBorderPainted(false);
        button10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button10MouseClicked(evt);
            }
        });
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        getContentPane().add(button10);
        button10.setBounds(640, 220, 40, 40);

        GroupList.setEditable(true);
        GroupList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        GroupList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupListActionPerformed(evt);
            }
        });
        getContentPane().add(GroupList);
        GroupList.setBounds(10, 40, 220, 21);

        CourseList.setEditable(true);
        CourseList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(CourseList);
        CourseList.setBounds(250, 40, 190, 21);

        TutorList.setEditable(true);
        TutorList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(TutorList);
        TutorList.setBounds(10, 140, 220, 21);

        RatingList.setEditable(true);
        RatingList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(RatingList);
        RatingList.setBounds(460, 40, 220, 21);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Параметры расчета рейтинга");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(460, 110, 180, 20);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Рейтинги ");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(460, 10, 60, 20);

        ParamsList.setEditable(true);
        ParamsList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(ParamsList);
        ParamsList.setBounds(460, 140, 220, 21);

        button12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        button12.setActionCommand("Редакт.");
        button12.setBorder(null);
        button12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button12MouseClicked(evt);
            }
        });
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });
        getContentPane().add(button12);
        button12.setBounds(550, 170, 30, 30);

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
        reConnect.setBounds(10, 220, 30, 40);

        RLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RLabel11.setText("Правая кнопка мыши на полях и кнопках формы - подсказка");
        getContentPane().add(RLabel11);
        RLabel11.setBounds(10, 270, 360, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(100, 70, 33, 30);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/add.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(460, 170, 30, 33);

        button15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/refresh.png"))); // NOI18N
        button15.setActionCommand("Редакт.");
        button15.setBorder(null);
        button15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button15MouseClicked(evt);
            }
        });
        button15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button15ActionPerformed(evt);
            }
        });
        getContentPane().add(button15);
        button15.setBounds(330, 70, 30, 30);

        button29.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        button29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawable-mdpi/list.png"))); // NOI18N
        button29.setBorder(null);
        button29.setName(""); // NOI18N
        button29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button29MouseClicked(evt);
            }
        });
        button29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button29ActionPerformed(evt);
            }
        });
        getContentPane().add(button29);
        button29.setBounds(130, 170, 30, 30);
        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        //-------- УДАЛЕНИЕ КУРСА -----------------------------------------
        MES.setText("");
        if (courseItem==null) return;
        new YesNoDialog(AdminWindow.this,"Удалить курс "+courseItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                DBItem xx[]=conn.getList(DBCell.class,courseItem);
                //-------------- Удаление связанных оценок -------------------------
                for (int i=0;i<xx.length;i++){
                    DBCell xxx=(DBCell)xx[i];
                    conn.deleteLinked(MDNote.class,xxx);
                    conn.deleteLinked(DBCellRating.class,xxx);
                    }
                conn.deleteLinked(DBCell.class,courseItem);
                conn.delete(courseItem.getClass(),courseItem.getId());
                courseItem=null;
                courseList.setList(conn.getList(DBCourse.class));
                } catch(Throwable e1){ fatal(e1); }
            }
        public void onNo() {}
        });        
    }//GEN-LAST:event_button1ActionPerformed
    
    public void addStudent(){
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        MES.setText("");
        if (groupItem==null) return;
        try {
            DBStudent st=new DBStudent(login.getName(),groupItem.getId());
            st.setPass(login.getPass());
            conn.insert(st);
            studentItem=null;
            } catch (Throwable ee){ fatal(ee); }
        }
        
    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        //---- УДАЛИТЬ ГРУППУ  --------------------------------------------
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        MES.setText("");
        if (groupItem==null) return;
        new YesNoDialog(AdminWindow.this,"Удалить группу "+groupItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                conn.deleteLinked(DBStudent.class, groupItem);
                //------------ Удаление связанных данных студентов ??????????????????
                conn.delete(groupItem.getClass(),groupItem.getId());
                groupItem=null;
                groupList.setList(conn.getList(DBGroups.class));
                } 
                catch(Throwable e1){ fatal(e1); }
            }
        public void onNo() {}
        });
    }//GEN-LAST:event_button3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        onClose();
    }//GEN-LAST:event_formWindowClosing

    public void removeStudent(){
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        //-------- УДАЛИТЬ СТУДЕНТА -----------------------------------------------
        MES.setText("");
        if (groupItem==null || studentItem==null) return;
        new YesNoDialog(AdminWindow.this,"Удалить студента "+studentItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                int id0=groupItem.getId();
                conn.deleteLinked(MDNote.class,studentItem);
                conn.deleteLinked(DBStudRating.class,studentItem);
                conn.deleteLinked(DBPropusk.class,studentItem);
                conn.delete(studentItem.getClass(),studentItem.getId());
                studentItem=null;
                } catch(Throwable e1){ fatal(e1); }
            }
        public void onNo() {}
        });
    }
    
    private void menuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem2ActionPerformed
        MES.setText("");
        FileDialog dlg=new FileDialog(this,"Экспорт - файл",FileDialog.SAVE);
        dlg.setFile(entry.getName()+".brs");
        dlg.show();
        final String path=dlg.getDirectory()+dlg.getFile();
        new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer)throws Throwable {
                    MES.setText("Операция завершена");                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                public Object call()throws Throwable{
                    DataOutputStream si=new DataOutputStream(new FileOutputStream(path));
                    saveAll(conn, si);
                    si.close();
                    return null;
                    }
                });
	}//GEN-LAST:event_menuItem2ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        //--- Эмулятор БАГА
        //DBItem xx=null;
        //xx.mark=false;
        new ValueDialog(AdminWindow.this,"Добавить группу ",new ValueListener(){
            @Override
                public void onError(String value) {
                    MES.setText(value);
                    }
            @Override
            public void onSelect(String value) {
            //--------- ДОБАВИТЬ ГРУППУ --------------------------------------------
            	try {
                    MES.setText("");
                    conn.insert(new DBGroups(value));
                    groupItem=null;
                    groupList.setList(conn.getList(DBGroups.class));
                    } catch(Throwable e1){ fatal(e1); }                
                }
            });
        

    }//GEN-LAST:event_button4ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        new ValueDialog(AdminWindow.this,"Добавить дисциплину ",new ValueListener(){
            @Override
                public void onError(String value) {
                    MES.setText(value);
                    }
            @Override
            public void onSelect(String value) {
                //--------- ДОБАВИТЬ КУРС ---------------------------------------
                try {
                    MES.setText("");
                    conn.insert(new DBCourse(value));
                    courseItem=null;
                    courseList.setList(conn.getList(DBCourse.class));
                } catch(Throwable e1){ fatal(e1); }                }
            });
    }//GEN-LAST:event_button7ActionPerformed
    
    public void addRating(){
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        try {
            //----- Добавить РЕЙТИНГ ГРУППЫ ПО ПРЕДМЕТУ -------------------
            MES.setText("");
            if (courseItem==null){
                MES.setText("Не выбрана дисциплина");
                return;
                }
            if (groupItem==null){
                MES.setText("Не выбрана группа");
                return;
                }
            String name=courseItem.getName()+" "+groupItem.getName();
            DBItem xx[]=conn.getByName(DBRating.class, name);
            if (xx.length!=0){
                MES.setText("Такой рейтинг уже существует");
                return;
                }
            int kk=paramsList.getSelectedId();
            if (kk==-1){
                MES.setText("Не выбраны параметры рейтинга");
                return;
                }
            //DBItem ss[]=studentList.getSource();
            DBItem zz[]=conn.getList(DBCell.class,courseItem);
            DBRating rr=new DBRating(name,courseItem.getId(),groupItem.getId(),twoGroups);
            rr.setIdParams(paramItem.getId());
            conn.insert(rr);
            for(int i=0;i<zz.length;i++){
                DBCell cl=(DBCell)zz[i];
                if (cl.getCellParams()[DBCell.idxPropusk]!=0) 
                    conn.insert(new DBEvent(cl.getName(),rr.getId()));
                }
            ratingList.setList(conn.getList(DBRating.class));
            MES.setText("Создан рейтинг "+name);
        } catch(Throwable e1){ fatal(e1); }
        }
    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button13ActionPerformed
        try {
            new RatingWindow(this).setVisible(true);
            } catch(Throwable ee) { fatal(ee); }
    }//GEN-LAST:event_button13ActionPerformed

    private void button14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button14ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        if (ratingItem==null) return;
        new YesNoDialog(AdminWindow.this,"Удалить рейтинг "+ratingItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                String idName=ratingItem.getLinkName();
                conn.deleteLinked(DBStudRating.class,ratingItem);
                conn.deleteLinked(DBCellRating.class,ratingItem);
                conn.deleteLinked(MDNote.class,ratingItem);
                conn.deleteLinked(DBPermission.class,ratingItem);
                conn.deleteLinked(DBEvent.class,ratingItem);
                conn.deleteLinked(DBPropusk.class,ratingItem);
                conn.delete(ratingItem.getClass(),ratingItem.getId());
                ratingList.setList(conn.getList(DBRating.class));
            } catch(Throwable e1){ fatal(e1); }
            }
        public void onNo() {}
        });        
        MES.setText("");
    }//GEN-LAST:event_button14ActionPerformed
    
    private void addTutor(){
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        try {
            MES.setText("");
            DBTutor tt = new DBTutor(login.getName(),login.getPass());
            if (login.isAdmin() || conn.getList(DBTutor.class).length==0) //Первый - админ
                tt.setAdmin(true);
            conn.insert(tt);
            tutorItem=null;
            tutorList.setList(conn.getList(DBTutor.class));
            }
            catch(Throwable ee){ fatal(ee); }                
        }

    private void button25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button25ActionPerformed
        login = new DBUser();
        new UserWindow(login, true, new YesNoListener(){
            @Override
            public void onYes() {
                addTutor();
                }
            @Override
            public void onNo() {
                }
            });
    }//GEN-LAST:event_button25ActionPerformed

    private void button28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button28ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        MES.setText("");
        if (tutorItem==null) return;
        new YesNoDialog(AdminWindow.this,"Удалить преподавателя "+tutorItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                conn.deleteLinked(DBPermission.class,tutorItem);
                conn.delete(tutorItem.getClass(),tutorItem.getId());
                tutorItem=null;
                tutorList.setList(conn.getList(DBTutor.class));
                } catch(Throwable e1){ fatal(e1); }
            }
        public void onNo() {}
        });     
    }//GEN-LAST:event_button28ActionPerformed

    public void addPermission(){
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        try {
            MES.setText("");
            if (tutorItem==null || ratingItem==null) return;
            //------ Добавить РАЗРЕШЕНИЕ ----------------------------------------
            int rid=ratingItem.getId();
            int tid=tutorItem.getId();
            String name=ratingItem.getName();
            DBItem xx[]=conn.getList(DBPermission.class,ratingItem,tutorItem);
            if (xx.length!=0){
                MES.setText("Такое разрешение уже существует");
                return;
                }
            conn.insert(new DBPermission(name,tid,rid));
            } catch(Throwable  e1){ fatal(e1); }
        }
    
    public void removePermission(){
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
     	//------------ Удалить РАЗРЕШЕНИЕ --------------------------------------
        MES.setText("");
        if (tutorItem==null || permissionItem==null) return;
        new YesNoDialog(AdminWindow.this,"Удалить разрешение "+permissionItem.getName(),new YesNoListener(){
        public void onYes() {
            try {
                conn.delete(permissionItem.getClass(),permissionItem.getId());
                } catch(Throwable  e1){ fatal(e1); }
            }
        public void onNo() {}
        });        
    }    
    public void updateTutor(){
        try {
            if (isBusy()){
                MES.setText("Выполнение фоновой операции");
                return;
            }
            MES.setText("");
            if (tutorItem==null) return;
            conn.update(tutorItem);
            tutorItem=null;
            tutorList.setList(conn.getList(DBTutor.class));
            } catch (Throwable ex) {
                fatal(ex);
                }
        }
   
    private void button27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button27ActionPerformed
        if (tutorItem == null){
            MES.setText("Не выбран преподаватель");
            return;
            }
        try {
            new UserWindow(tutorItem, true, new YesNoListener(){
            @Override
            public void onYes() {
                updateTutor();
                }
            @Override
            public void onNo() {
                }
            });
            }
            catch(Throwable ee){ fatal(ee); }
    }//GEN-LAST:event_button27ActionPerformed

    public void updateStudent(){
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        if (studentItem==null){
            MES.setText("Не выбран студент");
            return;
            }
        try {
            studentItem.setName(login.getName());
            studentItem.setPass(login.getPass());
            conn.update(studentItem);
            studentItem=null;
            } catch (Throwable ee){ fatal(ee); }
        }
        

    private void menuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem3ActionPerformed
        MES.setText("");
        FileDialog dlg=new FileDialog(this,"Импорт - файл",FileDialog.LOAD);
        dlg.setFile("*.brs");
        dlg.show();
        final String path=dlg.getDirectory()+dlg.getFile();
        String s="";
        new YesNoDialog(AdminWindow.this,"Импортировать данные в БД "+entry.getName(),new YesNoListener(){
        public void onYes() {
            new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer)throws Throwable {
                    MES.setText("Операция завершена");                    }
                @Override
                public void onError(Throwable ee) {
                    fatal(ee);
                    }
                @Override
                public Object call()throws Throwable{
                    DataInputStream si=new DataInputStream(new FileInputStream(path));
                    loadAll((DBConnection)conn, si);
                    si.close();                    
                    return null;
                    }
                });
            }
        public void onNo() {}
        });
    }//GEN-LAST:event_menuItem3ActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        try {
            setVisible(false);
            new Thread(){ 
                public void run() { 
                    try {
                        newClient(ClientOn.class);
                        } catch(Throwable ee){ 
                            MES.setText(ee.getMessage());
                            setVisible(true);
                            } 
                    }
                }.start();
            }
            catch(Throwable e1){ 
                if (!(e1 instanceof BRSException))
                    e1=new BRSException(e1);
                MES.setText(e1.getMessage()); 
                }
    }//GEN-LAST:event_button10ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        final int kk=ParamsList.getSelectedIndex();
        if (kk==0){
            MES.setText("Не выбран набор параметров");
            return;
            }
        new ParamWindow((DBParams)params[kk-1],new YesNoListener(){
            @Override
            public void onYes() {
                try{
                    conn.update(params[kk-1]);
                    params=conn.getList(DBParams.class);
                    paramsList.setList(params);
                    } catch(Throwable e1){ fatal(e1); }
                }
            @Override
            public void onNo() {
            }
        });  
    }//GEN-LAST:event_button12ActionPerformed

    private void menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu1ActionPerformed

    private void menuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem4ActionPerformed
        MES.setText("");
        FileDialog dlg=new FileDialog(this,"Экспорт SQLite",FileDialog.SAVE);
        dlg.setFile(entry.getName()+".sqlite");
        dlg.show();
        final String path=dlg.getDirectory()+dlg.getFile();
        new BackGround(new CallBack(){
            @Override
            public void onFinish(Object answer) throws Throwable {
                MES.setText("Операция завершена");
                }
            @Override
            public void onError(Throwable ee) {
                fatal(ee);
                }
            @Override
            public Object call() throws Throwable {
                DBSQLiteJDBC ctrl=new DBSQLiteJDBC();
                ctrl.connect(path);
                DBConnection si=new DBConnection(ctrl);
                copyAll(conn, si);
                si.close();
                return null;
                }
            });
    }//GEN-LAST:event_menuItem4ActionPerformed

    private void menuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem5ActionPerformed
        MES.setText("");
        FileDialog dlg=new FileDialog(this,"Импорт SQLite",FileDialog.LOAD);
        dlg.setFile(entry.getName()+".sqlite");
        dlg.show();
        final String path=dlg.getDirectory()+dlg.getFile();
        new BackGround(new CallBack(){
            @Override
            public void onFinish(Object answer) throws Throwable {
                MES.setText("Операция завершена");
                }
            @Override
            public void onError(Throwable ee) {
                fatal(ee);
                }
            @Override
            public Object call() throws Throwable {
                DBSQLiteJDBC ctrl=new DBSQLiteJDBC();
                ctrl.connect(path);
                DBConnection si=new DBConnection(ctrl);
                copyAll(si, (DBConnection)conn);
                si.close();
                return null;
                }
            });
    }//GEN-LAST:event_menuItem5ActionPerformed

    private void reConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reConnectActionPerformed
        if (isBusy()){
            new YesNoDialog(AdminWindow.this,"Прервать операцию",new YesNoListener(){
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
        MES.setText("");
        if (!dbIsOpen)
            initView();
        else
            user.reconnect();
        } catch(Throwable e1){ fatal(e1); }
    }//GEN-LAST:event_reConnectActionPerformed

    private void menuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem6ActionPerformed
        MES.setText("");
        try{
            if (conn.isConnected()) {
                new YesNoDialog(AdminWindow.this,"Инициализировать БД "+entry.getName(),new YesNoListener(){
                    public void onYes() {
                        new BackGround(new CallBack(){
                            @Override
                            public void onFinish(Object answer) throws Throwable {
                                MES.setText("Операция завершена");
                                }
                            @Override
                            public void onError(Throwable ee) {
                                fatal(ee);
                                }
                            @Override
                            public Object call() throws Throwable {
                                new DBCreator().DBCreate(conn,true); 
                                conn.insert(new DBParams());
                                initView();
                                return null;
                                }
                            });
                    }
                    public void onNo() {}
                    });
                }
            } catch(Throwable  e2){ fatal(e2); }
    }//GEN-LAST:event_menuItem6ActionPerformed

    private void button4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button4MouseClicked
        if (evt.getButton()==3){
            MES.setText("Добавить группу");
            }
    }//GEN-LAST:event_button4MouseClicked

    private void button3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button3MouseClicked
        if (evt.getButton()==3){
            MES.setText("Удалить группу");
            }

    }//GEN-LAST:event_button3MouseClicked

    private void button13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button13MouseClicked
        if (evt.getButton()==3){
            MES.setText("Добавить рейтинг - для выбранных ГРУППЫ и ПРЕДМЕТА");
            }
    }//GEN-LAST:event_button13MouseClicked

    private void button14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button14MouseClicked
        if (evt.getButton()==3){
            MES.setText("Удалить рейтинг");
            }
    }//GEN-LAST:event_button14MouseClicked

    private void reConnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reConnectMouseClicked
        if (evt.getButton()==3){
            MES.setText("Повторное соединение");
            }
    }//GEN-LAST:event_reConnectMouseClicked

    private void button10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button10MouseClicked
        if (evt.getButton()==3){
            MES.setText("Редактор рейтингов (полный список)");
            }
    }//GEN-LAST:event_button10MouseClicked

    private void button7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button7MouseClicked
        if (evt.getButton()==3){
            MES.setText("Добавить предмет");
            }
    }//GEN-LAST:event_button7MouseClicked

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
        if (evt.getButton()==3){
            MES.setText("Удалить предмет");
            }
    }//GEN-LAST:event_button1MouseClicked

    private void button25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button25MouseClicked
        if (evt.getButton()==3){
            MES.setText("Добавить преподавателя. Первая запись - админ");
            }
    }//GEN-LAST:event_button25MouseClicked

    private void button27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button27MouseClicked
        if (evt.getButton()==3){
            MES.setText("Редактировать данные преподавателя");
            }
    }//GEN-LAST:event_button27MouseClicked

    private void button28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button28MouseClicked
        if (evt.getButton()==3){
            MES.setText("Удалить преподавателя");
            }
    }//GEN-LAST:event_button28MouseClicked

    private void button12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button12MouseClicked
        if (evt.getButton()==3){
            MES.setText("Редактировать параметры вычисления рейтинга");
            }
    }//GEN-LAST:event_button12MouseClicked

    private void GroupListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GroupListActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (groupItem == null){
            MES.setText("Не выбрана группа");
            return;
            }
        try {
            new StudentWindow(AdminWindow.this).setVisible(true);
            }
            catch(Throwable ee){ fatal(ee); }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (isBusy()){
            MES.setText("Выполнение фоновой операции");
            return;
            }
        final DBParams xx = new DBParams();
        new ParamWindow(xx,new YesNoListener(){
            @Override
            public void onYes() {
                try{
                    conn.insert(xx);
                    params=conn.getList(DBParams.class);
                    paramsList.setList(params);
                    } catch(Throwable e1){ fatal(e1); }
                }
            @Override
            public void onNo() {
            }
        });  

    }//GEN-LAST:event_jButton2ActionPerformed

    private void button15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button15MouseClicked
        if (evt.getButton()==3){
            MES.setText("Редактировать предмет");
            }
    }//GEN-LAST:event_button15MouseClicked

    private void button15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button15ActionPerformed
        if (courseItem==null){
            MES.setText("Не выбран предмет");
            return;
            }
        try {
            new CellWindow(this).setVisible(true);
            } catch (Throwable ex) {
                fatal(ex);
        }
    }//GEN-LAST:event_button15ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (evt.getButton()==3){
            MES.setText("Добавить параметры расчета рейтингов");
            }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (evt.getButton()==3){
            MES.setText("Редактировать группу");
            }
    }//GEN-LAST:event_jButton1MouseClicked

    private void button29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button29MouseClicked
        if (evt.getButton()==3){
            MES.setText("Редактировать список доступных рейтингов");
            }
    }//GEN-LAST:event_button29MouseClicked

    private void button29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button29ActionPerformed
        if (tutorItem == null){
            MES.setText("Не выбран преподаватель");
            return;
            }
        try {
            new TutorWindow(AdminWindow.this).setVisible(true);
            } catch (Throwable ex) { fatal(ex); }
    }//GEN-LAST:event_button29ActionPerformed

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        MES.setText(""); 
        new YesNoDialog(AdminWindow.this,"Очистить хранилище "+entry.getName(),new YesNoListener(){
        public void onYes() {
                new BackGround(new CallBack(){
                @Override
                public void onFinish(Object answer)throws Throwable {
                MES.setText("Операция завершена");                    }
                @Override
                public void onError(Throwable ee) {
                fatal(ee);
                }
                @Override
                public Object call()throws Throwable{
                DBFileData ff = new DBFileData();
                conn.execSQL("delete from filedata where idfiledata > 0;");
                return null;
                }
                });
            }
        public void onNo() {}
        });
    }//GEN-LAST:event_menuItem1ActionPerformed
    //==========================================================================
    public void loadTableData(DBItem item, DataInputStream in, DBConnection out) 
    							throws Throwable{
    	MES.setText("Загрузка "+item.getTableName());
        int sz=in.readInt();
        Vector<DBField> ff=item.getFields();
        item.setFields(ff);
        while(sz--!=0){
            item.loadDBValues(in);
            out.insert(item, false);
            }
    	}
    public void saveTableData(Class proto,DBConnect in, DataOutputStream out) 
    							throws Throwable{
    	DBItem item=(DBItem)proto.newInstance();
        MES.setText("Сохрание "+item.getTableName());
    	DBItem xx[]=in.getList(proto);
        out.writeInt(xx.length);
        Vector<DBField> ff=(item).getFields();
        for(int i=0;i<xx.length;i++){
            xx[i].setFields(ff);
            xx[i].saveDBValues(out);
            }
    	}
    public void loadAll(DBConnection out,DataInputStream in) throws Throwable{
	new DBCreator().DBCreate(out,false);
        for(int i=0;i<DBCreator.DBClasses.length;i++)
            loadTableData((DBItem)DBCreator.DBClasses[i].newInstance(),in,out);
	}
    public void saveAll(DBConnect in,DataOutputStream out) throws Throwable{
        for(int i=0;i<DBCreator.DBClasses.length;i++)
            saveTableData(DBCreator.DBClasses[i],in,out);
		}
    public void copyAll(DBConnect in, DBConnection out) throws Throwable{
        for(int i=0;i<DBCreator.DBClasses.length;i++){
            MES.setText("Копирование "+DBCreator.DBClasses[i].getSimpleName());
            ((DBConnection)in).copyTable((DBItem)DBCreator.DBClasses[i].newInstance(),out);
            }
        }
    //------------- ВСЯКИЙ СЕРВИС ----------------------------------------------
    //------------- Конвертирование Note в Note2 -------------------------------
    /*
    public void convert() throws Throwable {
        DBItem xx[]=conn.getList(DBNote.class, rating);
        DBItem zz[]=conn.getList(DBVariant.class, rating);
        DBNote2 yy[]=new DBNote2[xx.length];
        for(int i=0;i<xx.length;i++){
            yy[i]=new DBNote2((DBNote)xx[i]);
            }
        for(int i=0;i<zz.length;i++){
            DBVariant vv=(DBVariant)zz[i];
            int j=getLastStudentCell(yy,vv.getIdStudent(),vv.getIdCell());
            if (j==-1){
                DBNote2 ll=new DBNote2(vv.getIdStudent(),rating.getId(),vv.getIdCell(),0,0);
                conn.insert(ll);
                }
            else{
                yy[j].setVariant(vv.getVariant());
                }
            }
        for(int i=0;i<yy.length;i++){
            conn.insert(yy[i]);
            }
        }
    */
    //------------- Конвертирование Note2 в Note File Variant ------------------
    public void convert2() throws Throwable {
        DBItem xx[]=conn.getList(MDNote.class);
        conn.createTable(DBVariant.class);
        conn.createTable(DBArchFile.class);
        conn.createTable(DBDocFile.class);
        for(int i=0;i<xx.length;i++){
            MDNote src=(MDNote)xx[i];
            DBNote out=new DBNote(src);
            conn.insert(out);
            if (src.getVariant().length()!=0){
                conn.insert(new DBVariant(src.getIdRating(),src.getIdStudent(),src.getIdCell(),src.getVariant()));
                }
            if (src.getArchFile().length()!=0){
                conn.insert(new DBArchFile(src.getIdRating(),src.getIdStudent(),src.getIdCell(),src.getIdArch(),src.getArchFile()));
                }
            if (src.getDocFile().length()!=0){
                conn.insert(new DBDocFile(src.getIdRating(),src.getIdStudent(),src.getIdCell(),src.getIdDoc(),src.getDocFile()));
                }            
            }
        }
    //--------------------------------------------------------------------------
    public static void main(String args[]) {
        runMain(AdminWindow.class);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CourseList;
    private javax.swing.JComboBox GroupList;
    private java.awt.TextArea MES;
    private javax.swing.JComboBox ParamsList;
    private javax.swing.JLabel RLabel11;
    private javax.swing.JComboBox RatingList;
    private javax.swing.JComboBox TutorList;
    private javax.swing.JButton button1;
    private javax.swing.JButton button10;
    private javax.swing.JButton button12;
    private javax.swing.JButton button13;
    private javax.swing.JButton button14;
    private javax.swing.JButton button15;
    private javax.swing.JButton button25;
    private javax.swing.JButton button27;
    private javax.swing.JButton button28;
    private javax.swing.JButton button29;
    private javax.swing.JButton button3;
    private javax.swing.JButton button4;
    private javax.swing.JButton button7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private java.awt.Menu menu1;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuItem menuItem1;
    private java.awt.MenuItem menuItem2;
    private java.awt.MenuItem menuItem3;
    private java.awt.MenuItem menuItem4;
    private java.awt.MenuItem menuItem5;
    private java.awt.MenuItem menuItem6;
    private javax.swing.JButton reConnect;
    // End of variables declaration//GEN-END:variables

    
}
