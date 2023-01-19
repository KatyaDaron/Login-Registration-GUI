import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegFormGUI extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfLabel;
    private JTextField tfAddress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;

    public RegFormGUI() {
        super();
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500, 474));
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
    }

    public static void main(String[] args) {
        RegFormGUI myForm = new RegFormGUI();
    }
}
