package presentation;

import bll.ClientBll;
import bll.OrderBll;
import bll.ProductBll;
import model.*;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Aceasta este o clasa care implementeaza interfata grafica pentru operatiile pe tabela orders
 * @author Tincu Diana
 *
 */
public class OrderPresentation {

    public JFrame frame;
    private JTable table_1;
    private JTable table_2;
    private JTextField textField;
    public int idComanda=1;
    String str="";
    OrderBll orderBll=new OrderBll();
    WriteFile writeFile=new WriteFile();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderPresentation window = new OrderPresentation();
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
    public OrderPresentation() throws IOException {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1023, 479);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        table_1 = new JTable();
        table_1.setBounds(58, 50, 417, 298);
        DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model2 = new DefaultTableModel();

        String[] l= {"","","",""};
        ProductBll productBll = new  ProductBll();
        List<String> headers = TableHeader.generateHeaders(productBll.viewAllProducts().get(0));
        int i=0;
        for(String line: headers)
            l[i++]=line;
        model.setColumnIdentifiers(l);
        table_1.setModel(model);
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(58, 50, 417, 298);
        frame.add(scrollPane);
        frame.getContentPane().add(table_1, BorderLayout.CENTER);
        scrollPane.setViewportView(table_1);
        JButton btnNewButton_1 = new JButton("View All Products");
        btnNewButton_1.setBounds(60, 410, 156, 21);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Product> produse= null;
                try {
                    produse = productBll.viewAllProducts();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                for (int i=0;i<produse.size();i++)	{
                    List<String> rows = null;
                    try {
                        rows = TableHeader.generateRows(productBll.viewAllProducts().get(i));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    model.addRow(new Object[]{rows.get(0), rows.get(1),rows.get(2), rows.get(3)});
                }					}
        });
        frame.add(btnNewButton_1);


        table_2 = new JTable();
        table_2.setBounds(545, 50, 402, 298);

        String[] l2= {"","","",""};
        ClientBll clientBll = new ClientBll();
        List<String> headers2 = TableHeader.generateHeaders(clientBll.viewAllClients().get(0));
        int i2=0;
        for(String line: headers2)
            l2[i2++]=line;
        System.out.println(l2[0]);
        model2.setColumnIdentifiers(l2);
        table_2.setModel(model2);
        JScrollPane scrollPane2=new JScrollPane();
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(545, 50, 402, 298);
        frame.add(scrollPane2);
        frame.getContentPane().add(table_2, BorderLayout.CENTER);
        scrollPane2.setViewportView(table_2);

        JButton btnNewButton_2 = new JButton("View All Clients");
        btnNewButton_2.setBounds(545, 410, 156, 21);

        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Client> clienti= null;
                try { clienti = clientBll.viewAllClients(); } catch (Exception exception) { exception.printStackTrace(); }
                for (int i=0;i<clienti.size();i++)	{
                    List<String> rows = null;
                    try {
                        rows = TableHeader.generateRows(clientBll.viewAllClients().get(i));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    model2.addRow(new Object[]{rows.get(0), rows.get(1),rows.get(2), rows.get(3)});
                }					}
        });
        frame.add(btnNewButton_2);



        JLabel lblNewLabel = new JLabel("Order");
        lblNewLabel.setFont(new Font("Garamond", Font.BOLD, 24));
        lblNewLabel.setBounds(474, 10, 65, 30);
        frame.getContentPane().add(lblNewLabel);

        JButton btnNewButtonBack = new JButton("Back");
        btnNewButtonBack.setBounds(0, 0, 85, 21);
        frame.getContentPane().add(btnNewButtonBack);
        btnNewButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPresentation c = new MainPresentation();
                c.mainframe.setVisible(true);
                c.mainframe.setLocationRelativeTo(null);
                frame.dispose();
            }
        });

        JLabel lblNewLabel_1 = new JLabel("Selectati cantitatea dorita");
        lblNewLabel_1.setBounds(60, 380, 156, 21);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(212, 381, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);


        JButton btnNewButtonOrder = new JButton("Order");
        btnNewButtonOrder.setBounds(924, 411, 85, 21);
        frame.getContentPane().add(btnNewButtonOrder);

        btnNewButtonOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idClient=table_2.getSelectedRow()+1;
                int idProdus=table_1.getSelectedRow()+1;
                int cantitate= Integer.parseInt(textField.getText());
                Orders o= new Orders();
                Product pr= null;
                try {
                    pr = productBll.findProductById(idProdus);
                    System.out.println(pr.cantitate);
                    if(cantitate<=pr.cantitate)
                    {
                        int total=cantitate*pr.pret;
                        str+="\nNr comanda: " + idComanda + "\n"+"->ID client:" + idClient + "\n"+"->ID produs: " + idProdus + "\n"+"->Cantitatea: " + cantitate + "\n"+"Total plata: " + total + "\n";
                        o.id_order = idComanda;
                        idComanda++;
                        int cantitateNoua=pr.cantitate-cantitate;
                        productBll.editProduct(idProdus, pr.titlu, Integer.toString(pr.pret), Integer.toString(cantitateNoua));
                        System.out.println(pr.cantitate);
                        o.cantitate=cantitate;
                        System.out.println(o.cantitate);
                        o.id_client_orders=idClient;
                        o.id_product_orders=idProdus;
                        orderBll.insertOrder(o);
                        writeFile.writeFile(str);
                    } else{ JOptionPane.showMessageDialog(null, "Stoc innsuficient!", "Eroare", JOptionPane.WARNING_MESSAGE); }
                } catch (IllegalAccessException illegalAccessException) { illegalAccessException.printStackTrace(); } catch (InvocationTargetException invocationTargetException) { invocationTargetException.printStackTrace(); } catch (IntrospectionException introspectionException) { introspectionException.printStackTrace(); } catch (SQLException throwables) { throwables.printStackTrace(); } catch (IOException ioException) { ioException.printStackTrace(); }
            }
        });

        JLabel lblNewLabel_2 = new JLabel("Selectati produsul");
        lblNewLabel_2.setBounds(207, 31, 123, 13);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Selectati clientul");
        lblNewLabel_3.setBounds(715, 31, 96, 13);
        frame.getContentPane().add(lblNewLabel_3);
    }
}
