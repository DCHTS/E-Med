import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientMenu implements ActionListener {

private static JLabel patientInfo, diagAndMed, testsAndResults, vaccines, sickList;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel topPanel = new JPanel();
//    JPanel patientInfo = new JPanel();
    JPanel patientVisit = new JPanel();

    JButton button = new JButton();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    JButton button4 = new JButton();
    JButton addNewPatient = new JButton();
    JButton doctor = new JButton();

    PatientProfile pp = new PatientProfile();
    DoctorProfile dp = new DoctorProfile();
    PatientList pl = new PatientList();


    public void skel() {

        frame.setSize(1300, 800); // 1024x760 // 512x380 // 256x190
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setTitle("Slimnīcas / Iestādes nosaukums");
        frame.add(panel, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(topPanel, BorderLayout.NORTH);

        panel.setBackground(new Color(208, 239, 255));


        // MAIN PANEL BUTTONS
        button.setText("Pacienta Info");
        button.setActionCommand("Pacienta Info");
        panel.add(button);

        button1.setText("Diagnozes un Medikamenti");
        button1.setActionCommand("Diagnozes un Medikamenti");
        panel.add(button1);

        button2.setText("Nosūtījumi un Rezultāti");
        panel.add(button2);

        button3.setText("Potes ?");
        panel.add(button3);

        button4.setText("Slimības lapas");
        panel.add(button4);

        list();

        button.setName("test");
        button.addActionListener(this);
        button.addActionListener(exc -> {

                panel.setBackground(new Color(255, 0, 0));
                patientInfo.setLayout(null);
                patientInfo.setText("Patient Info");

        });


        button1.addActionListener(exc -> {

            panel.setBackground(new Color(255, 255, 0));
            patientInfo.setLayout(null);
            patientInfo.setText("Diagnozes un Medikamenti");

        });


        button2.addActionListener(exc -> {

            panel.setBackground(new Color(0, 255, 255));
            patientInfo.setText("");

        });


        button3.addActionListener(exc -> {

            panel.setBackground(new Color(255, 0, 255));
            patientInfo.setText("");

        });


        button4.addActionListener(exc -> {

            panel.setBackground(new Color(255, 255, 255));
            patientInfo.setText("");

        });
        // /MAIN BUTTONS


        topPanel.setBackground(new Color(3, 37, 76));
        topPanel.setPreferredSize(new Dimension(100, 80));

        addNewPatient.setName("Pievienot jaunu pacientu");
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

        leftPanel.setBackground(new Color(17, 103, 177));
        leftPanel.setPreferredSize(new Dimension(250, 100));


//        // PATIENT LIST EXAMPLE
//        DefaultListModel<String> pacienti = new DefaultListModel<>();
//
//        PatientProfile pp = new PatientProfile();
//
//        pacienti.addElement("John White");
//        pacienti.addElement("Arthur Snow");
//        pacienti.addElement("Logan Paix");
//        pacienti.addElement("Rick Johnson");
//        pacienti.addElement("Jack Silver");
////        pacienti.addElement(pp.fullname());
//
//        JList<String> plist = new JList<>(pacienti);
//        plist.setPreferredSize(new Dimension(250, 200));
//        plist.setBackground(new Color(17, 103, 177));
//        plist.setFont(new Font("Calibri", Font.BOLD, 17));
//        plist.getSelectionModel().addListSelectionListener(e -> {
//
//            String p = plist.getSelectedValue();
//
//            patientInfo.setText("This is: " +p);
//
//        });
//        leftPanel.add(plist);
//        // PATIENT LIST END


        patientInfo = new JLabel("");
        patientInfo.setBounds(200, 300, 200, 200);
        panel.add(patientInfo);



    }

    public void list() {
        // PATIENT LIST EXAMPLE
        DefaultListModel<String> pacienti = new DefaultListModel<>();

//        PatientProfile pp = new PatientProfile();

        pacienti.addElement("John White");
        pacienti.addElement("Arthur Snow");
        pacienti.addElement("Logan Paix");
        pacienti.addElement("Rick Johnson");
        pacienti.addElement("Jack Silver");

//        pacienti.addElement();

        JList<String> plist = new JList<>(pacienti);
        plist.setPreferredSize(new Dimension(250, 200));
        plist.setBackground(new Color(17, 103, 177));
        plist.setFont(new Font("Calibri", Font.BOLD, 17));
        plist.getSelectionModel().addListSelectionListener(e -> {

            String p = plist.getSelectedValue();

            patientInfo.setText("This is: " +p);

        });
        leftPanel.add(plist);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

//    public void list() {
//        DefaultListModel<String> pacienti = new DefaultListModel<>();
//
//        try {
//            FileReader fr = new FileReader("patientList.txt");
//
//
//        } catch (Exception e) {
//
//        }
//
//        pacienti.addElement();
//
//        JList<String> plist = new JList<>(pacienti);
//        plist.setPreferredSize(new Dimension(250, 200));
//        plist.setBackground(new Color(17, 103, 177));
//        plist.setFont(new Font("Calibri", Font.BOLD, 17));
//        leftPanel.add(plist);
//    }

//    public class notes extends javax.swing.JFrame {
//
//        File f = new File("C:\\Users\\friiks\\IdeaProjects\\untitled");
//
//        public void createFolder() {
//            if (!f.exists()) {
//                f.mkdirs();
//            }
//        }
//
//        public void readFile() {
//            try {
//                FileReader fr = new FileReader(f+"\\test.txt");
//            } catch (FileNotFoundException e) {
//                try {
//                    FileWriter fw = new FileWriter(f+"\\test.txt");
//                } catch (IOException e1) {
//
//                }
//            }
//        }
//
//    }
    // Make new file -> Upon exec check if file exists -> if ex, then copy previous info and add the new input

}
