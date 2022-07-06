import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Scanner;

public class Frame implements ActionListener {

    private static JLabel patientInfo, diagAndMed, testsAndResults, vaccines, sickList;

    JTable jt = new JTable();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel topPanel = new JPanel();

    JPanel patientVisit = new JPanel();

    // Sadaļu paneļi
    JPanel dm, nr, potes, sl;

    JButton patientListBtn = new JButton();
    JButton addNewPatient = new JButton();
    JButton doctor = new JButton();

    PatientProfile pp = new PatientProfile();
    DoctorProfile dp = new DoctorProfile();

    JTextArea diaUnMed, nosUnRez, potesList, slimLap;

    List patientInfoList = new List();

    public void skel() throws FileNotFoundException {

        frame.setSize(1300, 800); // 1024x760 // 512x380 // 256x190
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Slimnīcas / Iestādes nosaukums");
        frame.add(panel);
        frame.setLayout(new BorderLayout());

        panel.setBackground(new Color(208, 239, 255));

        // Izsauc pacienta lista metodi galvenajā kodā
        list();

        // Augšējais panelis
        topPanel.setBackground(new Color(3, 37, 76));
        topPanel.setPreferredSize(new Dimension(100, 50));
        frame.add(topPanel, BorderLayout.PAGE_START);

        // Top paneļa pogas
        patientListBtn.setText("Pacientu saraksts");
        patientListBtn.addActionListener(e -> {
            patientList();
        });
        topPanel.add(patientListBtn);

        addNewPatient.setText("Pievienot jaunu pacientu");
        addNewPatient.addActionListener(evt -> {
            pp.newPatient();
        });
        topPanel.add(addNewPatient);

        doctor.setText("Ārsta profils");
        doctor.addActionListener(evt -> {
            dp.docProfile();
        });
        topPanel.add(doctor);




//        patientInfo = new JLabel("");
//        patientInfo.setBounds(200, 300, 200, 200);
//        panel.add(patientInfo);


        /// LEFT PANEL ///
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Pacients"));
        panel.setBackground(new Color(208, 239, 255));
        panel.setPreferredSize(new Dimension(320, 100));
        frame.add(panel, BorderLayout.WEST);



        /// MAIN SCREEN ///
        JTabbedPane pacInfo = new JTabbedPane();
        frame.add(pacInfo, BorderLayout.CENTER);

        dm = new JPanel();
        pacInfo.add("Diagnozes un Medikamenti", dm);

        nr = new JPanel();
        pacInfo.add("Nosūtījumi un Rezultāti", nr);

        potes = new JPanel();
        pacInfo.add("Potes", potes);

        sl = new JPanel();
        pacInfo.add("Slimības lapas", sl);


        // Pievienot jaunus datus panelim.
        // Būtu labi pogu uztaisīt universālu, lai visas sadaļas var tās izmantot
        // Tā vietā, lai taisītu 2 pogas, katrai sadaļai ar savām metodēm, kuras ir principā vienādas
        JButton add = new JButton("Pievienot");
        add.addActionListener(e -> {
            pievienotDatusDM();
        });
        dm.add(add);

        JButton edit = new JButton("Labot");
        // Vajag izveidot edit opciju, kas ļauj rediģēt tekstu un samaina esošo "Labot" pogu pret "Done"
        dm.add(edit);



        // DIAGNOZES UN MEDIKAMENTI SEKCIJA
        // Tāpat dažviet citur, šeit kods atkārtojas, līdz ar to varētu mēģināt izveidot klasi, kas ļautu
        // Izveidot sadaļas are vienu kodu, tā vietā, lai to kopētu 4x
        diaUnMed = new JTextArea(38, 86);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp3 = new JScrollPane(diaUnMed);
        diaUnMed.setLineWrap(true);
        diaUnMed.setWrapStyleWord(true);
        diaUnMed.setEditable(false);
        // Pagaidu rediģēšanas funkcija. Ar doubleclick var ieslēgt rediģēšanu, bet uz doto brīdi nevar izslēgt.
        diaUnMed.addMouseListener(new MouseAdapter() {
                                      @Override
                                      public void mouseClicked(MouseEvent e) {
                                          if (e.getClickCount() == 2) {
                                              diaUnMed.setEditable(true);
                                          }
                                      }
                                  });
        dm.add(sp3);




        // Pievienot jaunus datus panelim.
        JButton add2 = new JButton("Pievienot");
        add2.addActionListener(e -> {
            pievienotDatusNR();
        });
        nr.add(add2);

        JButton edit2 = new JButton("Labot");

        nr.add(edit2);

        // NOSŪTĪJUMI UN REZULTĀTI SEKCIJA
        nosUnRez = new JTextArea(38, 86);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp4 = new JScrollPane(nosUnRez);
        nosUnRez.setLineWrap(true);
        nosUnRez.setWrapStyleWord(true);
        nosUnRez.setEditable(false);
        nosUnRez.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    nosUnRez.setEditable(true);
//                }
//            }
        });
        nr.add(sp4);




        // Pievienot jaunus datus panelim.
        JButton add3 = new JButton("Pievienot");
        add3.addActionListener(e -> {
            pievienotDatusPotes();
        });
        potes.add(add3);

        JButton edit3 = new JButton("Labot");

        potes.add(edit3);

        // POTES SEKCIJA
        potesList = new JTextArea(38, 86);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp5 = new JScrollPane(potesList);
        potesList.setLineWrap(true);
        potesList.setWrapStyleWord(true);
        potesList.setEditable(false);
        potesList.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    nosUnRez.setEditable(true);
//                }
//            }
        });
        potes.add(sp5);




        // Pievienot jaunus datus panelim.
        JButton add4 = new JButton("Pievienot");
        add4.addActionListener(e -> {
            pievienotDatusSL();
        });
        sl.add(add4);

        JButton edit4 = new JButton("Labot");

        sl.add(edit4);

        // SLIMĪBAS LAPAS SEKCIJA
        slimLap = new JTextArea(38, 86);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp6 = new JScrollPane(slimLap);
        slimLap.setLineWrap(true);
        slimLap.setWrapStyleWord(true);
        slimLap.setEditable(false);
        slimLap.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 2) {
//                    nosUnRez.setEditable(true);
//                }
//            }
        });
        sl.add(sp6);







        // PACIENTA PERSONAS INFO
        // Logs, kas uzrādas kreisajā panelī. list() metode ir jāpārraksta tā, lai Personas informācija uzrādītos šeit,
        // kad viņu selecto Pacientu sarakstā
        JTextArea ta2 = new JTextArea();
        ta2.setBorder(new TitledBorder("Pacienta info šeit"));
        ta2.setBackground(new Color(208, 239, 255));
        ta2.setPreferredSize(new Dimension(250, 400));
        ta2.setEditable(false);
        panel.add(ta2);


    }

    public void list() throws FileNotFoundException {

        DefaultListModel<String> pacienti = new DefaultListModel<>();
        //DEFINING FILE LOCATION
        Scanner scanner = new Scanner(new File("patientList.txt"));

        //CREATE A LIST FOR KEEPING INFORMATION FROM FILE
        // read until end of file
        while (scanner.hasNextLine()) {
            patientInfoList.add(scanner.nextLine().split(":")[1]); //READING LINE BY LINE AND ADDING THEM TO LIST BEFORE SPLITTING THEM USING DELIMITER
        }

        // close the scanner
        scanner.close();

        //GETTING ITEMS FROM LIST
        pacienti.addElement(patientInfoList.getItem(0) + " " + patientInfoList.getItem(1));

//        pacienti.addElement();

        JList<String> plist = new JList<>(pacienti);

        plist.setPreferredSize(new Dimension(250, 200));
        plist.setBackground(new Color(17, 103, 177));
        plist.setFont(new Font("Calibri", Font.BOLD, 17));

        plist.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) { //ONLY CHANGE WHEN VALUE IS ADJUSTED
                String[] columnNames = {"Vārds", "Uzvārds", "Personas kods", "Deklerētā adrese",
                        "Faktiskā adrese", "Telefona nr", "E-pasts"};

                Object[][] data = {
                        {
                                patientInfoList.getItem(0),
                                patientInfoList.getItem(1),
                                patientInfoList.getItem(2),
                                patientInfoList.getItem(3),
                                patientInfoList.getItem(4),
                                patientInfoList.getItem(5),
                                patientInfoList.getItem(6),
                        },
                };

                JTable table = new JTable(data, columnNames);
                table.setBounds(10, 10, 700, 200);

                panel.add(new JScrollPane(table)); //ADD TABLE TO PANEL
            }
        });
//        bottomPanel.add(plist);
    }



    // Jaunais pacientu saraksts, šeit pēc butības būtu jāveido JList ar pogu, kas acceptē izvēlēto personu un sniedz
    // tās personas datus uz cietiem paneļiem. Ja viss sanāk, tad vēl var mēģināt izveidot "search bar", kur var
    // meklēt pacientu pēc personas koda
    public void patientList() {
        JFrame frame = new JFrame("Pacientu saraksts");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setSize(500, 500);
        panel.setLayout(null);
        frame.add(panel);

//        Jlist list = new J
    }



    // PIEVIENO DATUS IEKŠ "Diagnozes un Medikamenti"
    // Pēc iespējām, būtu labi šo metodi uztaisīt jaunā klasē un uztaisīt universālu,
    // jo uz doto brīdi, šis kods būtu jākopē 4 reizes, katrai sadaļai individuāli
    public void pievienotDatusDM() {
        JFrame frame = new JFrame("Pievienot Diagnozi");
        frame.setSize(975, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setSize(975, 400);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(panel);

        JLabel jl = new JLabel("Diagnoze:");
        panel.add(jl);

        JTextArea ta = new JTextArea(7, 86);
        JScrollPane sp = new JScrollPane(ta);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        panel.add(sp);

        JLabel jl2 = new JLabel("Medikamenti:");
        panel.add(jl2);

        JTextArea ta2 = new JTextArea(7, 86);
        JScrollPane sp2 = new JScrollPane(ta2);
        ta2.setLineWrap(true);
        ta2.setWrapStyleWord(true);
        panel.add(sp2);

        JButton btn = new JButton();
        btn.setText("Pievienot");
        btn.setLayout(new FlowLayout(FlowLayout.CENTER));
        btn.addActionListener(e -> {
            String diag = ta.getText();
            String med = ta2.getText();
            String empty = diaUnMed.getText();

//            diaUnMed.setText("Diagnoze: " + "\n" +
//                    diag + "\n" + "\n" +
//                    "Medikamenti: " + "\n" +
//                    med + "\n" +
//                    "--------------------------------------------------------------------------------------" + "\n");

            if (empty.isBlank()) {
                diaUnMed.setText("Diagnoze: " + "\n" +
                        diag + "\n" + "\n" +
                        "Medikamenti: " + "\n" +
                        med + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            } else {
                diaUnMed.setText(empty + "\n" +
                        "Diagnoze: " + "\n" +
                        diag + "\n" + "\n" +
                        "Medikamenti: " + "\n" +
                        med + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            }

        });
        panel.add(btn);


    }


    // PIEVIENOT DATUS IEKŠ "Nosūtījumi un Medikamenti"
    public void pievienotDatusNR() {
        JFrame frame = new JFrame("Pievienot Nosūtījumu");
        frame.setSize(975, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setSize(975, 400);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(panel);

        JLabel jl = new JLabel("Nosūtījums:");
        panel.add(jl);

        JTextArea ta = new JTextArea(7, 86);
        JScrollPane sp = new JScrollPane(ta);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        panel.add(sp);

        JLabel jl2 = new JLabel("Rezultāti:");
        panel.add(jl2);

        JTextArea ta2 = new JTextArea(7, 86);
        JScrollPane sp2 = new JScrollPane(ta2);
        ta2.setLineWrap(true);
        ta2.setWrapStyleWord(true);
        panel.add(sp2);

        JButton btn = new JButton();
        btn.setText("Pievienot");
        btn.setLayout(new FlowLayout(FlowLayout.CENTER));
        btn.addActionListener(e -> {
            String nos = ta.getText();
            String rez = ta2.getText();
            String empty = nosUnRez.getText();

            if (empty.isBlank()) {
                nosUnRez.setText("Nosūtījums: " + "\n" +
                        nos + "\n" + "\n" +
                        "Rezultati: " + "\n" +
                        rez + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            } else {
                nosUnRez.setText(empty + "\n" +
                        "Nosūtījums: " + "\n" +
                        nos + "\n" + "\n" +
                        "Rezultāts: " + "\n" +
                        rez + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            }

        });
        panel.add(btn);


    }



    // PIEVIENOT DATUS IEKŠ "Potes"
    public void pievienotDatusPotes() {
        JFrame frame = new JFrame("Pievienot Potes");
        frame.setSize(975, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setSize(975, 250);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(panel);

        JLabel jl = new JLabel("Pote:");
        panel.add(jl);

        JTextArea ta = new JTextArea(7, 86);
        JScrollPane sp = new JScrollPane(ta);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        panel.add(sp);

        JButton btn = new JButton();
        btn.setText("Pievienot");
        btn.setLayout(new FlowLayout(FlowLayout.CENTER));
        btn.addActionListener(e -> {
            String pote = ta.getText();
            String empty = potesList.getText();

            if (empty.isBlank()) {
                potesList.setText("Pote: " + "\n" +
                        pote + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            } else {
                potesList.setText(empty + "\n" +
                        "Pote: " + "\n" +
                        pote + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            }

        });
        panel.add(btn);


    }


    // PIEVIENOT DATUS IEKŠ "Slimības lapas"
    public void pievienotDatusSL() {
        JFrame frame = new JFrame("Pievienot Slimības lapu");
        frame.setSize(975, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setSize(975, 300);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(panel);

        JLabel jl = new JLabel("Datums:");
        panel.add(jl);

        JTextArea ta = new JTextArea(1, 86);
        JScrollPane sp = new JScrollPane(ta);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        panel.add(sp);

        JLabel jl2 = new JLabel("Apraksts:");
        panel.add(jl2);

        JTextArea ta2 = new JTextArea(7, 86);
        JScrollPane sp2 = new JScrollPane(ta2);
        ta2.setLineWrap(true);
        ta2.setWrapStyleWord(true);
        panel.add(sp2);

        JButton btn = new JButton();
        btn.setText("Pievienot");
        btn.setLayout(new FlowLayout(FlowLayout.CENTER));
        btn.addActionListener(e -> {
            String dat = ta.getText();
            String apr = ta2.getText();
            String empty = slimLap.getText();

            if (empty.isBlank()) {
                slimLap.setText("Datums: " + "\n" +
                        dat + "\n" + "\n" +
                        "Apraksts: " + "\n" +
                        apr + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            } else {
                slimLap.setText(empty + "\n" +
                        "Datums: " + "\n" +
                        dat + "\n" + "\n" +
                        "Apraksts: " + "\n" +
                        apr + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            }

        });
        panel.add(btn);


    }



    public void labotDatus() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
