import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JDialog {
    private JTextField email;
    private JButton btnL;
    private JButton btnR;
    private JPanel logins;
    private JPasswordField password;

    Registration reg = new Registration();

    Frame menu = new Frame();




    public void Login(){
        JFrame parentL = new JFrame();
        parentL.setTitle("Login");
        parentL.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        parentL.setMinimumSize(new Dimension(600,500));
        parentL.setVisible(true);
        btnL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                checkUser();
                menu.skel();
            }
        });
        btnR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reg.RegistrationPopUp();
            }
        });
        parentL.add(logins);

    }
    // Šo vajadzēs, kad pievienos datubāzi
//    public void checkUser(){
//        final String DB_URL = "jdbc:mysql://localhost:3306";
//        final String USERNAME = "root";
//        final String PASSWORD = "";
//
//        try {
//            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//            Statement stmt = con.createStatement();
//            String sql = "SELECT * FROM projectRCS.users WHERE email = ? AND password = ?";
//
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1, email.getText());
//            preparedStatement.setString(2, password.getText());
//            ResultSet result = preparedStatement.executeQuery();
//            if (result.next()) {
//                  Frame menu = new Frame();
//                  menu.skel();
//            } else {
//                JOptionPane.showMessageDialog(this,
//                        "Nepareizs epasts un/vai parole",
//                        "Pieslēgšanās neizdevās!",
//                        JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//
//        } catch (Exception e){
//            System.out.println("Nenolasits");
//        }
//    }
}



