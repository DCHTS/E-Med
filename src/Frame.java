import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Frame implements ActionListener {

    private static JLabel patientInfo, diagAndMed, testsAndResults, vaccines, sickList;

    String[] columnNames = {"Vārds", "Uzvārds", "Personas kods", "Deklarētā adrese",
            "Faktiskā adrese", "Telefona nr", "E-pasts"}; //Pacientu saraksta tabulas kolonas

    String selectedPatientName, selectedPatientSurname, selectedPatientID, selectedPatientDek,
    selectedPatientFak, selectedPatientNr, selectedPatientEmail; //Izvēlētā pacienta v., uzv un t.t.

    JTable jt = new JTable();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel topPanel = new JPanel();

    JPanel patientVisit = new JPanel();

    JPanel leftPanel = new JPanel();

    // Sadaļu paneļi
    JPanel dm, nr, potes, sl;

    JButton patientListBtn = new JButton();
    JButton addNewPatient = new JButton();
    JButton doctor = new JButton();

    PatientProfile pp = new PatientProfile();
    DoctorProfile dp = new DoctorProfile();

    JTextArea diaUnMed, nosUnRez, potesList, slimLap;

    JTextArea pta;
    List patientInfoList = new List();

    Font font = new Font("Roboto", Font.BOLD, 13);

    public void skel()  {

        frame.setSize(1300, 800); // 1024x760 // 512x380 // 256x190
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Slimnīcas / Iestādes nosaukums");
        frame.add(panel);
        frame.setLayout(new BorderLayout());

        panel.setBackground(new Color(208, 239, 255));

        // Augšējais panelis
        topPanel.setBackground(new Color(3, 37, 76));
        topPanel.setPreferredSize(new Dimension(100, 50));
        frame.add(topPanel, BorderLayout.PAGE_START);

        // Top paneļa pogas
        patientListBtn.setText("Pacientu saraksts");
        patientListBtn.addActionListener(e -> {
            try {
                patientList();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        topPanel.add(patientListBtn);

        // Poga
        addNewPatient.setText("Pievienot jaunu pacientu");
        addNewPatient.addActionListener(evt -> {
            pp.newPatient();
        });
        topPanel.add(addNewPatient);

        // Poga
        doctor.setText("Ārsta profils");
        doctor.addActionListener(evt -> {
            dp.docProfile();
        });
        topPanel.add(doctor);

        /// LEFT PANEL ///
//        leftPanel.setBorder(new TitledBorder("Pacients"));
        leftPanel.setBackground(new Color(208, 239, 255));
        leftPanel.setPreferredSize(new Dimension(320, 100));
        frame.add(leftPanel, BorderLayout.WEST);


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

//        JButton edit = new JButton("Labot");
//        // Vajag izveidot edit opciju, kas ļauj rediģēt tekstu un samaina esošo "Labot" pogu pret "Done"
//        dm.add(edit);


        // DIAGNOZES UN MEDIKAMENTI SEKCIJA
        // Tāpat dažviet citur, šeit kods atkārtojas, līdz ar to varētu mēģināt izveidot klasi, kas ļautu
        // Izveidot sadaļas are vienu kodu, tā vietā, lai to kopētu 4x
        diaUnMed = new JTextArea(35, 79);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp3 = new JScrollPane(diaUnMed);
        diaUnMed.setFont(font);
        diaUnMed.setLineWrap(true);
        diaUnMed.setWrapStyleWord(true);
        diaUnMed.setEditable(false);
        // Pagaidu rediģēšanas funkcija. Ar doubleclick var ieslēgt rediģēšanu, izklikšķinot ārā, nevar rediģēt
        diaUnMed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    diaUnMed.setEditable(true);
                } else {
                    diaUnMed.setEditable(false);
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

//        JButton edit2 = new JButton("Labot");
//        nr.add(edit2);

        // NOSŪTĪJUMI UN REZULTĀTI SEKCIJA
        nosUnRez = new JTextArea(35, 79);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp4 = new JScrollPane(nosUnRez);
        nosUnRez.setFont(font);
        nosUnRez.setLineWrap(true);
        nosUnRez.setWrapStyleWord(true);
        nosUnRez.setEditable(false);
        nosUnRez.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    nosUnRez.setEditable(true);
                } else {
                    nosUnRez.setEditable(false);
                }
            }
        });
        nr.add(sp4);


        // Pievienot jaunus datus panelim.
        JButton add3 = new JButton("Pievienot");
        add3.addActionListener(e -> {
            pievienotDatusPotes();
        });
        potes.add(add3);

//        JButton edit3 = new JButton("Labot");
//        potes.add(edit3);

        // POTES SEKCIJA
        potesList = new JTextArea(35, 79);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp5 = new JScrollPane(potesList);
        potesList.setFont(font);
        potesList.setLineWrap(true);
        potesList.setWrapStyleWord(true);
        potesList.setEditable(false);
        potesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    potesList.setEditable(true);
                } else {
                    potesList.setEditable(false);
                }
            }
        });
        potes.add(sp5);


        // Pievienot jaunus datus panelim.
        JButton add4 = new JButton("Pievienot");
        add4.addActionListener(e -> {
            pievienotDatusSL();
        });
        sl.add(add4);

//        JButton edit4 = new JButton("Labot");
//        sl.add(edit4);

        // SLIMĪBAS LAPAS SEKCIJA
        slimLap = new JTextArea(35, 79);
//        diaUnMed.setPreferredSize(new Dimension(800, 120));
        JScrollPane sp6 = new JScrollPane(slimLap);
        slimLap.setFont(font);
        slimLap.setLineWrap(true);
        slimLap.setWrapStyleWord(true);
        slimLap.setEditable(false);
        slimLap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    slimLap.setEditable(true);
                } else {
                    slimLap.setEditable(false);
                }
            }
        });
        sl.add(sp6);


//        // PACIENTA PERSONAS INFO
        Font font = new Font("Roboto", Font.BOLD, 13);

        pta = new JTextArea(10, 25);
//        pta.setBorder(new TitledBorder("Pacienta info"));
        pta.setBackground(new Color(208, 239, 255));
        pta.setFont(font);
        pta.setLineWrap(true);
        pta.setWrapStyleWord(true);
        pta.setEditable(false);
    }

    // PACIENTU SARAKSTS
    // Ja viss sanāk, tad vēl var mēģināt izveidot "search bar", kur var meklēt pacientu pēc personas koda
    public void patientList() throws FileNotFoundException {
        JFrame frame = new JFrame("Pacientu saraksts");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

//        JPanel panel = new JPanel();
////        panel.setSize(500, 500);
//        frame.add(panel, BorderLayout.CENTER);

        Scanner scanner = new Scanner(new File("patientList.txt")); //Pacientu saglabātā info fails

        //Izveido faila info lasītāju, līdz faila beigām
        while (scanner.hasNextLine()) {
            patientInfoList.add(scanner.nextLine().split(":")[1]); //Lasa pa 1 līnijai, atdalot ievadīto info ar :
        }

        // Dabūt Pacienta datus tabulai
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
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        //Pievieno opciju izvēlēties pacientu no Pacientu Saraksta tabulas un attēlo izvēli Pacienta info panelī
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint()); //paņem info no izvēlētās tabulas rindas

                selectedPatientName = table.getValueAt(row, 0).toString();
                selectedPatientSurname = table.getValueAt(row, 1).toString();
                selectedPatientID = table.getValueAt(row, 2).toString();
                selectedPatientDek = table.getValueAt(row, 3).toString();
                selectedPatientFak = table.getValueAt(row, 4).toString();
                selectedPatientNr = table.getValueAt(row, 5).toString();
                selectedPatientEmail = table.getValueAt(row, 6).toString();



                //Aizver Pacientu Sarakstu, pēc pacienta izvēles
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                //Pievieno Pacienta v., uzv. Pacienta info, kreisajā panelī
                pta.setText("Vārds: " + "\n" + selectedPatientName + "\n" + "\n" +
                        "Uzvārds: " + "\n" + selectedPatientSurname + "\n" + "\n" +
                        "Personas kods: " + "\n" + selectedPatientID + "\n" + "\n" +
                        "Deklarētā adrese: " + "\n" + selectedPatientDek + "\n" + "\n" +
                        "Faktiskā adrese: " + "\n" + selectedPatientFak + "\n" + "\n" +
                        "Telefona nr: " + "\n" + selectedPatientNr + "\n" + "\n" +
                        "E-pasts: " + "\n" + selectedPatientEmail + "\n");

                leftPanel.add(pta);
            }
        });
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
            if (diag.isBlank() || med.isBlank()) {
                JOptionPane.showMessageDialog(null, "Aizpildiet tukšos laukus");
                return;
            }

            if (empty.isBlank()) {
                diaUnMed.setText("Diagnoze: " + "\n" +
                        diag + "\n" + "\n" +
                        "Medikamenti: " + "\n" +
                        med + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            } else {
                diaUnMed.setText(empty + "\n" +
                        "Diagnoze: " + "\n" +
                        diag + "\n" + "\n" +
                        "Medikamenti: " + "\n" +
                        med + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            }
            frame.dispose();
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

            if (nos.isBlank() || rez.isBlank()) {
                JOptionPane.showMessageDialog(null, "Aizpildiet tukšos laukus");
                return;
            }

            if (empty.isBlank()) {
                nosUnRez.setText("Nosūtījums: " + "\n" +
                        nos + "\n" + "\n" +
                        "Rezultati: " + "\n" +
                        rez + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            } else {
                nosUnRez.setText(empty + "\n" +
                        "Nosūtījums: " + "\n" +
                        nos + "\n" + "\n" +
                        "Rezultāts: " + "\n" +
                        rez + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            }
            frame.dispose();
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

            if (pote.isBlank()) {
                JOptionPane.showMessageDialog(null, "Aizpildiet tukšo lauku");
                return;
            }

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
            frame.dispose();
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

            if (dat.isBlank() || apr.isBlank()) {
                JOptionPane.showMessageDialog(null, "Aizpildiet tukšos laukus");
                return;
            }

            if (empty.isBlank()) {
                slimLap.setText("Datums: " + "\n" +
                        dat + "\n" + "\n" +
                        "Apraksts: " + "\n" +
                        apr + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            } else {
                slimLap.setText(empty + "\n" +
                        "Datums: " + "\n" +
                        dat + "\n" + "\n" +
                        "Apraksts: " + "\n" +
                        apr + "\n" + "\n" +
                        "--------------------------------------------------------------------------------------" + "\n");
            }
            frame.dispose();
        });
        panel.add(btn);


    }


    public void labotDatus() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
