package bll;

import connection.ConnectionFactory;
import dao.ProductDao;
import model.Product;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Aceasta este o clasa care apeleaza metodele din clasa DAO corespunzatoare
 * @author Tincu Diana
 *
 */
public class ProductBll {
    private ProductDao productDao;

    public ProductBll()
    {
        productDao=new ProductDao();
    }
    /**
     * Aceasta metoda apeleaza metoda de cautare a unui produs dupa id-ul transmis ca si parametru din clasa abstracta DAO
     * @param id
     * @return Product
     */
    public Product findProductById(int id) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
        Product product = productDao.findById(id);
        if (product == null) {
            throw new NoSuchElementException("No product found with the id of " + id);
        }
        return product;
    }
    /**
     * Aceasta metoda apeleaza metoda de insert product din clasa abstracta DAO
     * @param product
     * @return int
     */
    public int insertProduct( Product product ) throws IllegalArgumentException, IllegalAccessException{
        int result = productDao.insert(product);
        return result;
    }

    /**
     * Aceasta metoda apeleaza metoda de delete a unui produs dupa id-ul transmis ca si parametru din clasa abstracta DAO
     * @param product, id
     * @return int
     */
    public int deleteProduct(Product product, int id) throws IllegalArgumentException, IllegalAccessException, SQLException {
        int result = productDao.delete(id);
        return result;
    }

    /**
     * Aceasta metoda apeleaza metoda de edit product din clasa abstracta DAO
     * @param id, titlu, pret, cantitate
     * @return
     */
    public void editProduct(int id, String titlu, String pret, String cantitate) throws IllegalArgumentException, IllegalAccessException, SQLException {
        productDao.edit(id, titlu, pret, cantitate);
    }

    /**
     * Aceasta metoda apeleaza metoda afiseaza toate produsele din tabela de produse care au id-ul >0
     * @param
     * @return List<Product></Product>
     */
    public List<Product> viewAllProducts(){
        Connection dbConnection = ConnectionFactory.getConnection();
        List<Product> products=new ArrayList<>();
        String query = "SELECT * FROM `product` WHERE id_product>0";
        PreparedStatement viewStatement = null;
        try {   viewStatement = dbConnection.prepareStatement(query); } catch (SQLException throwables) { throwables.printStackTrace(); }
        ResultSet rs = null;
        try { rs = viewStatement.executeQuery(); } catch (SQLException throwables) { throwables.printStackTrace(); }
        String id= "";
        String titlu= "";
        String pret = "";
        String cantitate = "";
        try {
            while(rs.next())
            {
                id = rs.getString("id_product");
                titlu = rs.getString("titlu");
                pret = rs.getString("pret");
                cantitate = rs.getString("cantitate");
                products.add(new Product(Integer.valueOf(id), titlu, Integer.valueOf(pret), Integer.valueOf(cantitate)));
            }
        } catch (SQLException throwables) { throwables.printStackTrace(); }
        return products;
    }

    public static void main(String[] args) throws IllegalAccessException, SecurityException, IllegalArgumentException, SQLException {
        ProductBll c=new ProductBll();
        System.out.println(c.viewAllProducts());
        c.editProduct(2,"Brumbrum", "22", "13");
    }
}
