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
 * Aceasta este o clasa care implementeaza interfata grafica pentru editarea unui client
 * @author Tincu Diana
 *
 */
public class EditClient {

    public JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EditClient window = new EditClient();
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
    public EditClient() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 460, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Edit Client by ID");
        lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        lblNewLabel.setBounds(112, 50, 211, 28);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID:");
        lblNewLabel_1.setBounds(112, 121, 45, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Nume nou:");
        lblNewLabel_2.setBounds(112, 160, 81, 13);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Prenume nou:");
        lblNewLabel_3.setBounds(112, 200, 81, 13);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Oras nou");
        lblNewLabel_4.setBounds(112, 243, 81, 13);
        frame.getContentPane().add(lblNewLabel_4);

        textField = new JTextField();
        textField.setBounds(227, 118, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(227, 157, 96, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(227, 197, 96, 19);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(227, 240, 96, 19);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JButton btnNewButton = new JButton("Back");
        btnNewButton.setBounds(0, 0, 85, 21);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientPresentation c = new ClientPresentation();
                c.frame.setVisible(true);
                c.frame.setLocationRelativeTo(null);
                frame.dispose();
            }
        });

        JButton btnNewButton_1 = new JButton("Edit");
        btnNewButton_1.setBounds(175, 331, 85, 21);
        frame.getContentPane().add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientDao cld= new ClientDao();
                try {
                    cld.edit(Integer.parseInt(textField.getText()), textField_1.getText(), textField_2.getText(), textField_3.getText());
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

}

