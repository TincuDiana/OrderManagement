package presentation;
import dao.ClientDao;
import model.Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
/**
 * Aceasta este o clasa care implementeaza interfata grafica pentru adaugarea unui nou client
 * @author Tincu Diana
 *
 */
public class AddClient {

    public JFrame frame;
    private JTextField textFieldId;
    private JTextField textFieldNume;
    private JTextField textFieldPrenume;
    private JTextField textFieldOras;
    ClientDao cld= new ClientDao();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddClient window = new AddClient();
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
    public AddClient() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 419, 337);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Add New Client");
        lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        lblNewLabel.setBounds(101, 27, 234, 28);
        frame.getContentPane().add(lblNewLabel);

        textFieldId = new JTextField();
        textFieldId.setBounds(153, 79, 121, 19);
        frame.getContentPane().add(textFieldId);
        textFieldId.setColumns(10);

        textFieldNume = new JTextField();
        textFieldNume.setBounds(151, 120, 121, 19);
        frame.getContentPane().add(textFieldNume);
        textFieldNume.setColumns(10);

        textFieldPrenume = new JTextField();
        textFieldPrenume.setBounds(153, 162, 121, 19);
        frame.getContentPane().add(textFieldPrenume);
        textFieldPrenume.setColumns(10);

        textFieldOras = new JTextField();
        textFieldOras.setBounds(153, 203, 121, 19);
        frame.getContentPane().add(textFieldOras);
        textFieldOras.setColumns(10);


        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(98, 82, 45, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Nume");
        lblNewLabel_2.setBounds(98, 123, 45, 13);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Prenume");
        lblNewLabel_3.setBounds(98, 165, 45, 13);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Oras");
        lblNewLabel_4.setBounds(98, 206, 45, 13);
        frame.getContentPane().add(lblNewLabel_4);


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

        JButton btnNewButtonAdd = new JButton("Add");
        btnNewButtonAdd.setBounds(164, 256, 85, 21);
        frame.getContentPane().add(btnNewButtonAdd);
        btnNewButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Client cl=new Client();
                try {
                    cl.id_client= Integer.parseInt(textFieldId.getText());
                    cl.nume=textFieldNume.getText();
                    cl.prenume=textFieldPrenume.getText();
                    cl.oras=textFieldOras.getText();
                    cld.insert(cl);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });
    }
}
