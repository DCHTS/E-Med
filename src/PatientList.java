//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class PatientList implements ActionListener {
//
//    String[] columnNames = {"Vārds", "Uzvārds", "Personas kods", "Deklarētā adrese",
//            "Faktiskā adrese", "Telefona nr", "E-pasts"}; //Pacientu saraksta tabulas kolonas
//
//    String selectedPatientName, selectedPatientSurname, selectedPatientID, selectedPatientDek,
//            selectedPatientFak, selectedPatientNr, selectedPatientEmail, displayPatientInfo; //Izvēlētā pacienta v., uzv un t.t.
//
//    List patientInfoList = new List();
//
//    JTextArea pta;
//
//    JFrame frame;
//
//    JTable table;
//
//    public void patientList() throws FileNotFoundException {
//        frame = new JFrame("Pacientu saraksts");
//        frame.setSize(500, 500);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setVisible(true);
//
////        JPanel panel = new JPanel();
//////        panel.setSize(500, 500);
////        frame.add(panel, BorderLayout.CENTER);
//
//        Scanner scanner = new Scanner(new File("patientList.txt")); //Pacientu saglabātā info fails
//
//        //Izveido faila info lasītāju, līdz faila beigām
//        while (scanner.hasNextLine()) {
//            patientInfoList.add(scanner.nextLine().split(":")[1]); //Lasa pa 1 līnijai, atdalot ievadīto info ar :
//        }
//
//        // Dabūt Pacienta datus tabulai
//        Object[][] data = {
//                {
//                        patientInfoList.getItem(0),
//                        patientInfoList.getItem(1),
//                        patientInfoList.getItem(2),
//                        patientInfoList.getItem(3),
//                        patientInfoList.getItem(4),
//                        patientInfoList.getItem(5),
//                        patientInfoList.getItem(6),
//                },
//        };
//
//        table = new JTable(data, columnNames);
//        frame.add(new JScrollPane(table), BorderLayout.CENTER);
//
//        displayPatientInfo();
//
////        //Pievieno opciju izvēlēties pacientu no Pacientu Saraksta tabulas un attēlo izvēli Pacienta info panelī
////        table.addMouseListener(new MouseAdapter() {
////            @Override
////            public void mouseClicked(MouseEvent evt) {
////                int row = table.rowAtPoint(evt.getPoint()); //paņem info no izvēlētās tabulas rindas
////
////                selectedPatientName = table.getValueAt(row, 0).toString();
////                selectedPatientSurname = table.getValueAt(row, 1).toString();
////                selectedPatientID = table.getValueAt(row, 2).toString();
////                selectedPatientDek = table.getValueAt(row, 3).toString();
////                selectedPatientFak = table.getValueAt(row, 4).toString();
////                selectedPatientNr = table.getValueAt(row, 5).toString();
////                selectedPatientEmail = table.getValueAt(row, 6).toString();
//
//
//
////                //Aizver Pacientu Sarakstu, pēc pacienta izvēles
////                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
////                //Pievieno Pacienta v., uzv. Pacienta info, kreisajā panelī
////                pta.setText("Vārds: " + "\n" + selectedPatientName + "\n" + "\n" +
////                        "Uzvārds: " + "\n" + selectedPatientSurname + "\n" + "\n" +
////                        "Personas kods: " + "\n" + selectedPatientID + "\n" + "\n" +
////                        "Deklarētā adrese: " + "\n" + selectedPatientDek + "\n" + "\n" +
////                        "Faktiskā adrese: " + "\n" + selectedPatientFak + "\n" + "\n" +
////                        "Telefona nr: " + "\n" + selectedPatientNr + "\n" + "\n" +
////                        "E-pasts: " + "\n" + selectedPatientEmail + "\n");
////
////                leftPanel.add(pta);
////            }
////        });
//    }
//
//    public void displayPatientInfo() {
////        // PACIENTA PERSONAS INFO
////        Font font = new Font("Roboto", Font.BOLD, 13);
////
////        pta = new JTextArea(10, 25);
//////        pta.setBorder(new TitledBorder("Pacienta info"));
////        pta.setBackground(new Color(208, 239, 255));
////        pta.setFont(font);
////        pta.setLineWrap(true);
////        pta.setWrapStyleWord(true);
////        pta.setEditable(false);
//
//        //Pievieno opciju izvēlēties pacientu no Pacientu Saraksta tabulas un attēlo izvēli Pacienta info panelī
//        table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent evt) {
//                int row = table.rowAtPoint(evt.getPoint()); //paņem info no izvēlētās tabulas rindas
//
//                selectedPatientName = table.getValueAt(row, 0).toString();
//                selectedPatientSurname = table.getValueAt(row, 1).toString();
//                selectedPatientID = table.getValueAt(row, 2).toString();
//                selectedPatientDek = table.getValueAt(row, 3).toString();
//                selectedPatientFak = table.getValueAt(row, 4).toString();
//                selectedPatientNr = table.getValueAt(row, 5).toString();
//                selectedPatientEmail = table.getValueAt(row, 6).toString();
//
//
//
//                //Aizver Pacientu Sarakstu, pēc pacienta izvēles
//                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//                //Pievieno Pacienta v., uzv. Pacienta info, kreisajā panelī
////                displayPatientInfo = ("Vārds: " + "\n" + selectedPatientName + "\n" + "\n" +
////                        "Uzvārds: " + "\n" + selectedPatientSurname + "\n" + "\n" +
////                        "Personas kods: " + "\n" + selectedPatientID + "\n" + "\n" +
////                        "Deklarētā adrese: " + "\n" + selectedPatientDek + "\n" + "\n" +
////                        "Faktiskā adrese: " + "\n" + selectedPatientFak + "\n" + "\n" +
////                        "Telefona nr: " + "\n" + selectedPatientNr + "\n" + "\n" +
////                        "E-pasts: " + "\n" + selectedPatientEmail + "\n");
//
//            }
//        });
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        displayPatientInfo = ("Vārds: " + "\n" + selectedPatientName + "\n" + "\n" +
//                "Uzvārds: " + "\n" + selectedPatientSurname + "\n" + "\n" +
//                "Personas kods: " + "\n" + selectedPatientID + "\n" + "\n" +
//                "Deklarētā adrese: " + "\n" + selectedPatientDek + "\n" + "\n" +
//                "Faktiskā adrese: " + "\n" + selectedPatientFak + "\n" + "\n" +
//                "Telefona nr: " + "\n" + selectedPatientNr + "\n" + "\n" +
//                "E-pasts: " + "\n" + selectedPatientEmail + "\n");
//    }
//}
