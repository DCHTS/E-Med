import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PatientProfile extends javax.swing.JFrame {

    // Top Panel / Add New Patient
    JLabel name, surname, persID, dekAdr, fakAdr, telNr, email, errorMessage;
    JTextField nameField, surnameField, persIDField, dekAdrField, fakAdrField, telNrField, emailField;

    public static String pName, pSurname, pID, pDekAdr, pFakAdr, pTelNr, pEmail, fullName;
    FileWriter fw;


    public void newPatient() {

        JFrame frame2 = new JFrame("Jauns Pacients");
        frame2.setSize(400, 350);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setResizable(false);
        frame2.setVisible(true);

        JPanel panel2 = new JPanel();
        panel2.setSize(400, 350);
        panel2.setLayout(null);
        frame2.add(panel2);


        // LABELS
        name = new JLabel("Vārds:");
        name.setBounds(20, 20 , 120, 20);
        panel2.add(name);

        surname = new JLabel("Uzvārds:");
        surname.setBounds(20, 50 , 120, 20);
        panel2.add(surname);

        persID = new JLabel("Personas kods:");
        persID.setBounds(20, 80 , 120, 20);
        panel2.add(persID);

        dekAdr = new JLabel("Deklerētā adrese:");
        dekAdr.setBounds(20, 110 , 120, 20);
        panel2.add(dekAdr);

        fakAdr = new JLabel("Faktiskā adrese:");
        fakAdr.setBounds(20, 140 , 120, 20);
        panel2.add(fakAdr);

        telNr = new JLabel("Telefona nr:");
        telNr.setBounds(20, 170 , 120, 20);
        panel2.add(telNr);

        email = new JLabel("E-pasts:");
        email.setBounds(20, 200 , 120, 20);
        panel2.add(email);


        // TEXTFIELDS START HERE
        nameField = new JTextField();
        nameField.setBounds(130, 20, 200, 20);
        panel2.add(nameField);

        surnameField = new JTextField();
        surnameField.setBounds(130, 50, 200, 20);
        panel2.add(surnameField);

        persIDField = new JTextField();
        persIDField.setBounds(130, 80, 200, 20);
        panel2.add(persIDField);

        dekAdrField = new JTextField();
        dekAdrField.setBounds(130, 110, 200, 20);
        panel2.add(dekAdrField);

        fakAdrField = new JTextField();
        fakAdrField.setBounds(130, 140, 200, 20);
        panel2.add(fakAdrField);

        telNrField = new JTextField();
        telNrField.setBounds(130, 170, 200, 20);
        panel2.add(telNrField);

        emailField = new JTextField();
        emailField.setBounds(130, 200, 200, 20);
        panel2.add(emailField);



        errorMessage = new JLabel();
        errorMessage.setBounds(150, 250, 300, 40);
        errorMessage.setForeground(new Color(255, 0, 0));
        panel2.add(errorMessage);

        // INPUT BUTTON
        JButton newPatientBtn = new JButton();
        newPatientBtn.setText("Ievadīt");
        newPatientBtn.setBounds(190, 230, 80, 20);
        newPatientBtn.addActionListener(exc -> {
            pName = nameField.getText();
            pSurname = surnameField.getText();
            pID = persIDField.getText();
            pDekAdr = dekAdrField.getText();
            pFakAdr = fakAdrField.getText();
            pTelNr = telNrField.getText();
            pEmail = emailField.getText();

            fullName = pName + " " + pSurname;


            if (pName.isBlank() || pSurname.isBlank() || pID.isBlank() || pDekAdr.isBlank() ||
                    pFakAdr.isBlank() || pTelNr.isBlank() || pEmail.isBlank()) {
                errorMessage.setText("Aizpildiet tukšos laukumus");
                return;
            }

            if (exc.getActionCommand().equals(newPatientBtn.getActionCommand())) {
                try {
                    File filename = new File("patientList.txt");
                    if (!filename.exists()) {
                        filename.createNewFile();
                    }

                    fw = new FileWriter("patientList.txt");

                    fw.write(name.getText() + pName + "\n");
                    fw.write(surname.getText() + pSurname + "\n");
                    fw.write(persID.getText() + pID + "\n");
                    fw.write(dekAdr.getText() + pDekAdr + "\n");
                    fw.write(fakAdr.getText() + pFakAdr + "\n");
                    fw.write(telNr.getText() + pTelNr + "\n");
                    fw.write(email.getText() + pEmail + "\n");

                    fw.close();
                    JOptionPane.showMessageDialog(null, "Pacients tika pievienots");
                    frame2.dispose();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e + "");
                }



            }

        });
        panel2.add(newPatientBtn);

    }

}
