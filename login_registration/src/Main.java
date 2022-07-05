import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Login login = new Login(null);
        Registration reg = new Registration(null);
        User user = reg.user;
        if (user != null) {
            System.out.println("Veiksmīgi reģistrēts epasts: " + user.email);
        }
        else {
            System.out.println("Reģistrācija neizdevās");
        }
    }
}