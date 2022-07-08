//import javax.swing.*;
//import javax.swing.border.TitledBorder;
//import java.awt.*;
//
//public class Prac {
//
//    public void frame() {
//
////        final boolean shouldFill = true;
//
//        JFrame frame = new JFrame();
//        frame.setSize(1300, 800);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//        frame.setTitle("Slimnīcas / Iestādes nosaukums");
//
////        GridBagConstraints gbc = new GridBagConstraints();
//
//        JPanel panel = new JPanel();
//        panel.setBorder(new TitledBorder("Panel"));
//        panel.setBackground(new Color(208, 239, 255));
//        panel.setPreferredSize(new Dimension(320, 100));
//        frame.add(panel, BorderLayout.WEST);
//
//        JPanel panel2 = new JPanel();
//        panel2.setBorder(new TitledBorder("Panel2"));
//        panel2.setBackground(new Color(255, 0, 0));
//        frame.add(panel2, BorderLayout.NORTH);
//
//
//
//
//        /// MAIN SCREEN ///
//        JTabbedPane pacInfo = new JTabbedPane();
//        frame.add(pacInfo, BorderLayout.CENTER);
//
//        JPanel dm = new JPanel();
//        pacInfo.add("Diagnozes un Medikamenti", dm);
//
//        JPanel nr = new JPanel();
//        pacInfo.add("Nosūtījumi un Rezultāti", nr);
//
//        JPanel potes = new JPanel();
//        pacInfo.add("Potes ?", potes);
//
//        JPanel sl = new JPanel();
//        pacInfo.add("Slimības lapas", sl);
//        /// MAIN SCREEN END ///
//
//        JButton add = new JButton("Pievienot");
//        dm.add(add);
//
//        JButton edit = new JButton("Labot");
//
//        dm.add(edit);
//
//
//        JTextArea ta = new JTextArea();
//        ta.setPreferredSize(new Dimension(800, 120));
//        dm.add(ta);
//
//
//
//
//
//
//
//        JLabel label1 = new JLabel("Test Label");
//        panel2.add(label1);
//
//    }
//
//}
