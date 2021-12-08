package presentation;

import dao.ClientDao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * Aceasta este o clasa care implementeaza interfata grafica pentru stergerea unui client
 * @author Tincu Diana
 *
 */
public class DeleteClient {

    public JFrame frame;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DeleteClient window = new DeleteClient();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public DeleteClient() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 282, 264);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Delete Client By ID");
        lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        lblNewLabel.setBounds(39, 47, 276, 42);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(53, 106, 64, 27);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(86, 112, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton btnNewButtonBack = new JButton("Back");
        btnNewButtonBack.setBounds(0, 0, 85, 21);
        frame.getContentPane().add(btnNewButtonBack);
        btnNewButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientPresentation c = new ClientPresentation();
                c.frame.setVisible(true);
                c.frame.setLocationRelativeTo(null);
                frame.dispose();
            }
        });

        JButton btnNewButtonDelete = new JButton("Delete");
        btnNewButtonDelete.setBounds(97, 165, 85, 21);
        frame.getContentPane().add(btnNewButtonDelete);
        btnNewButtonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientDao cld= new ClientDao();
                try {
                    cld.delete(Integer.parseInt(textField.getText()));
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }
}

