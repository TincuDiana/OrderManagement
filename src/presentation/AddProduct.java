package presentation;

import dao.ProductDao;
import model.Product;

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
public class AddProduct {

    public JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textFieldCantitate;
    private ProductDao productDao= new ProductDao();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddProduct window = new AddProduct();
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
    public AddProduct() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 444, 399);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Add Product");
        lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        lblNewLabel.setBounds(135, 40, 140, 38);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("ID");
        lblNewLabel_1.setBounds(114, 129, 45, 13);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Titlu");
        lblNewLabel_2.setBounds(114, 164, 45, 13);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Pret");
        lblNewLabel_3.setBounds(114, 203, 45, 13);
        frame.getContentPane().add(lblNewLabel_3);

        /*JLabel lblNewLabel_4 = new JLabel("Origine");
        lblNewLabel_4.setBounds(114, 241, 45, 13);
        frame.getContentPane().add(lblNewLabel_4);*/

        JLabel lblNewLabel_5 = new JLabel("Cantitate");
        lblNewLabel_5.setBounds(114, 279, 60, 13);
        frame.getContentPane().add(lblNewLabel_5);

        textField = new JTextField();
        textField.setBounds(185, 126, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(185, 161, 96, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(185, 200, 96, 19);
        frame.getContentPane().add(textField_2);
        textField_2.setColumns(10);

        /*textField_3 = new JTextField();
        textField_3.setBounds(185, 238, 96, 19);
        frame.getContentPane().add(textField_3);
        textField_3.setColumns(10);*/

        textFieldCantitate = new JTextField();
        textFieldCantitate.setBounds(185, 276, 96, 19);
        frame.getContentPane().add(textFieldCantitate);
        textFieldCantitate.setColumns(10);

        JButton btnNewButton = new JButton("Back");
        btnNewButton.setBounds(0, 0, 85, 21);
        frame.getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductPresentation c = new ProductPresentation();
                c.frame.setVisible(true);
                c.frame.setLocationRelativeTo(null);
                frame.dispose();
            }
        });

        JButton btnNewButtonAdd = new JButton("Add");
        btnNewButtonAdd.setBounds(165, 312, 85, 21);
        frame.getContentPane().add(btnNewButtonAdd);
        btnNewButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Product cl=new Product();
                try {
                    cl.id_product= Integer.parseInt(textField.getText());
                    cl.titlu=textField_1.getText();
                    cl.pret= Integer.parseInt(textField_2.getText());
                    cl.cantitate= Integer.parseInt(textFieldCantitate.getText());
                    productDao.insert(cl);
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });
    }

}
