package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Aceasta este o clasa care implementeaza interfata grafica pentru selectarea tipului de operatie dorit
 * @author Tincu Diana
 *
 */
public class MainPresentation {

    public JFrame mainframe;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainPresentation window = new MainPresentation();
                    window.mainframe.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainPresentation() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mainframe = new JFrame();
        mainframe.setBounds(100, 100, 766, 451);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Main");
        lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        lblNewLabel.setBounds(338, 28, 169, 21);
        mainframe.getContentPane().add(lblNewLabel);

        JButton btnNewButtonClient = new JButton("Client");
        btnNewButtonClient.setBounds(269, 108, 205, 33);
        mainframe.getContentPane().add(btnNewButtonClient);
        btnNewButtonClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientPresentation cl= new ClientPresentation();
                cl.frame.setVisible(true);
                mainframe.setVisible(false);
            }
        });

        JButton btnNewButtonProduct = new JButton("Product");
        btnNewButtonProduct.setBounds(269, 177, 205, 33);
        mainframe.getContentPane().add(btnNewButtonProduct);
        btnNewButtonProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductPresentation cl= new ProductPresentation();
                cl.frame.setVisible(true);
                mainframe.setVisible(false);
            }
        });

        JButton btnNewButtonOrder = new JButton("Order");
        btnNewButtonOrder.setBounds(269, 245, 205, 33);
        mainframe.getContentPane().add(btnNewButtonOrder);
        btnNewButtonOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderPresentation cl= null;
                try {
                    cl = new OrderPresentation();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                cl.frame.setVisible(true);
                    mainframe.setVisible(false);
            }
        });
    }
}
