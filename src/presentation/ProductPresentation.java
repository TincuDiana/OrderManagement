package presentation;

import connection.ConnectionFactory;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * Aceasta este o clasa care implementeaza interfata grafica pentru operatiile pe tabela product
 * @author Tincu Diana
 *
 */
public class ProductPresentation {
    public JFrame frame;
    private JPanel panel;
    private JButton btnNewButtonAdd;
    private JButton btnNewButtonDelete;
    private JButton btnNewButtonEdit;
    private JButton btnNewButtonView;
    private JTable table;
    String[] columnNames = {"IDProd", "Titlu", "Pret", "Cantitate"};
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientPresentation window = new ClientPresentation();
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
    public ProductPresentation() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblNewLabel = new JLabel("Product");
        lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 20));
        frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        btnNewButtonAdd = new JButton("Add");
        panel.add(btnNewButtonAdd);
        btnNewButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddProduct c = new AddProduct();
                c.frame.setVisible(true);
                c.frame.setLocationRelativeTo(null);
                frame.dispose();
            }
        });


        btnNewButtonDelete = new JButton("Delete");
        panel.add(btnNewButtonDelete);
        btnNewButtonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteProduct c = new DeleteProduct();
                c.frame.setVisible(true);
                c.frame.setLocationRelativeTo(null);
                frame.dispose();
            }
        });

        btnNewButtonEdit = new JButton("Edit");
        panel.add(btnNewButtonEdit);
        btnNewButtonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EditProduct c = new EditProduct();
                c.frame.setVisible(true);
                c.frame.setLocationRelativeTo(null);
                frame.dispose();
            }
        });

        btnNewButtonView = new JButton("View");
        panel.add(btnNewButtonView);

        table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(40, 22, 460, 260);
        frame.add(scrollPane);
        frame.getContentPane().add(table, BorderLayout.CENTER);
        scrollPane.setViewportView(table);

        btnNewButtonView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection dbConnection = ConnectionFactory.getConnection();
                String query = "SELECT * FROM `product` WHERE id_product>0";
                PreparedStatement viewStatement = null;
                try { viewStatement = dbConnection.prepareStatement(query); } catch (SQLException throwables) { throwables.printStackTrace(); }
                ResultSet rs = null;
                try { rs = viewStatement.executeQuery(); } catch (SQLException throwables) { throwables.printStackTrace(); }
                int i =0;
                String id= "";
                String titlu= "";
                String pret = "";
                String cantitate= "";
                try {
                    while(rs.next())
                    {
                        id = rs.getString("id_product");
                        titlu = rs.getString("titlu");
                        pret = rs.getString("pret");
                        cantitate= rs.getString("cantitate");
                        model.addRow(new Object[]{id, titlu, pret,cantitate});
                        i++;
                    }
                } catch (SQLException throwables) { throwables.printStackTrace(); }
            }
        });

        JButton btnNewButtonBack = new JButton("Back");
        btnNewButtonBack.setBounds(580, 222, 147, 23);
        panel.add(btnNewButtonBack);
        btnNewButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPresentation c = new MainPresentation();
                c.mainframe.setVisible(true);
                c.mainframe.setLocationRelativeTo(null);
                frame.dispose();
            }
        });
    }
}
