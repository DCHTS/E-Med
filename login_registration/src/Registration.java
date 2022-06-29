import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Registration extends JDialog {
    private JTextField aEmail;
    private JTextField aPassword; // Jāizmanto JPasswordField, lai neredz lietotāja input
    private JTextField aPasswordAg; // Jāizmanto JPasswordField, lai neredz lietotāja input
    private JTextField aSurname;
    private JTextField aPersonID;
    private JTextField aLocation;
    private JTextField aPhone;
    private JButton registerBTN;
    private JPanel registracija;
    private JTextField aName;

    public Registration(JFrame parent) {
        super(parent);
        setTitle("Reģistrācija");
        setModal(true);
        setContentPane(registracija);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registerBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        setVisible(true);
    }

    public void registerUser() {
        String email = aEmail.getText();
        String password = aPassword.getText();
        String passwordAd = aPasswordAg.getText();
        String name = aName.getText();
        String surname = aSurname.getText();
        String personID = aPersonID.getText();
        String location = aLocation.getText();
        String phone = aPhone.getText();

        if (email.isEmpty() || password.isEmpty() || passwordAd.isEmpty() || name.isEmpty() || surname.isEmpty() || personID.isEmpty() || location.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Lūdzu aizpildi visus lauciņus!",
                    "Mēģini vēlreiz!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(passwordAd)) {
            JOptionPane.showMessageDialog(this,
                    "Parole nav vienāda!",
                    "Mēģini vēlreiz!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDB(email, password, name, surname, personID, location, phone);
        if (user != null) {
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Reģistrācija neizdevās!",
                    "Mēģini vēlreiz!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;

    private User addUserToDB(String email, String password, String name, String surname, String personID, String location, String phone) {
        User user = null;
        final String DB_URL = "jdbc:mysql://127.0.0.1:3306";
        final String USERNAME = "root";
        final String PASSWORD = "";

        // Vēlāk izpētīšu kā nosharot MySQL datubāzes, bet testēšanai vienkārši iekopē šo kodu query savā mysql workbenchā un tad piekļuvi attiecīgi nomaini tepat 80-82 rindiņās

        // create database projectRCS

        // use projectRCS

        // CREATE TABLE `users` (
        //  `ID` int NOT NULL AUTO_INCREMENT,
        //  `email` varchar(16) NOT NULL,
        //  `password` varchar(255) DEFAULT NULL,
        //  `name` varchar(32) NOT NULL,
        //  `surname` varchar(32) DEFAULT NULL,
        //  `personID` varchar(45) DEFAULT NULL,
        //  `location` varchar(45) DEFAULT NULL,
        //  `phone` int DEFAULT NULL,
        //  PRIMARY KEY (`ID`),
        //  UNIQUE KEY `ID_UNIQUE` (`ID`)

        // Ja kādi jautājumi, raksti


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO projectRCS.users (email, password, name, surname, personID, location, phone) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, personID);
            preparedStatement.setString(6, location);
            preparedStatement.setString(7, phone);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.email = email;
                user.password = password;
                user.name = name;
                user.surname = surname;
                user.personID = personID;
                user.location = location;
                user.phone = phone;
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;

    }
}


