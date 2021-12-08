package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 *
 * @author Tincu Diana
 * Aceasta este o clasa abstracta care contine metode pentru accesarea bazei de date: creeare, editare, stergere, gasire
 * obiect de tip T prin intermediul reflection techinique
 * @param <T>
 */
public abstract class AbstractDao<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDao.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Acesta este o metoda care cauta un obiect dupa id-ul sau si il returneaza(metoda preluata din materialul indrumator)
     * @param givenId
     * @return object T
     */
    public T findById(int givenId) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
        Connection dbConnection = null;
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        String query = createSelectQuery();
        System.out.println(query);
        try {
            dbConnection = ConnectionFactory.getConnection();
            findStatement = dbConnection.prepareStatement(query);
            findStatement.setInt(1, givenId);
            rs = findStatement.executeQuery();
            return createObjects(rs).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "*Exceptie la findById linia 45*" + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    /**
     * Acesta este o metoda pentru inserarea unui obiect de tip T in baza de date
     * @param object
     * @return int
     */
    public int insert(T object) throws IllegalArgumentException, IllegalAccessException {
        Connection dbConnection = ConnectionFactory.getConnection();
        String query = null;
        if (type.getSimpleName().equalsIgnoreCase("Client"))
            query = "INSERT INTO `client` (id_client,nume,prenume,oras)" + " VALUES (?,?,?,?)";
        else if (type.getSimpleName().equalsIgnoreCase("Orders"))
            query = "INSERT INTO `orders` (id_order,cantitate,id_product_orders,id_client_orders)" + " VALUES (?,?,?,?)";
       else if (type.getSimpleName().equalsIgnoreCase("Product"))
            query = "INSERT INTO `product` (id_product,titlu,pret,cantitate)" + " VALUES (?,?,?,?)";
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            if (type != null) {
                for (Field field : type.getDeclaredFields()) {
                    Object value = field.get(object);
                    insertStatement.setString(i, String.valueOf(value));
                    i++;
                }
                insertStatement.executeUpdate();
                ResultSet rs = insertStatement.getGeneratedKeys();
                if (rs.next()) { insertedId = rs.getInt(1); }
            }
        } catch (SQLException e) { LOGGER.log(Level.WARNING, "AbstractDao: insert linia 77" + e.getMessage()); }
        finally {ConnectionFactory.close(insertStatement);ConnectionFactory.close(dbConnection); }
        return insertedId;
    }

    /**
     * Acesta este o metoda care selecteaza din baza de date toate obiectele care au un anumit id (metoda preluata din materialul indrumator)
     * @return String
     */
    public String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName().toLowerCase());
        sb.append("` WHERE id_" + type.getSimpleName().toLowerCase() + " =?");
        return sb.toString();
    }

    /**
     * Acesta este o metoda care creeaza o lista de obiecte de tipul T
     * @param resultSet
     * @return List<T>
     */
    @SuppressWarnings("deprecation")
    private List<T> createObjects(ResultSet resultSet) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException, IntrospectionException {
        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Acesta este o metoda care sterge un obiect T care are id ul transmis ca si parametru din baza de date
     * @param id
     * @return int
     */
    public int delete(int id) throws IllegalArgumentException, IllegalAccessException, SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        String query = null;
        if (type.getSimpleName().equalsIgnoreCase("Client"))
            query = "DELETE FROM `client` WHERE id_client=?";
        else if (type.getSimpleName().equalsIgnoreCase("Order"))
            query = "DELETE FROM `orders` WHERE id_order=?";
        if (type.getSimpleName().equalsIgnoreCase("Product"))
            query = "DELETE FROM `product` WHERE id_product=?";

        PreparedStatement deleteStatement = dbConnection.prepareStatement(query);
        deleteStatement.setString(1, String.valueOf(id));
        int deletedRow=deleteStatement.executeUpdate();
        return deletedRow;
    }

    /**
     * Acesta este o metoda care editeaza datele despre un obiect T identificat prin id ul dat ca si parametru din baza de date
     * @param id, s1, s2, s3
     * @return
     */
	public void edit(int id, String s1, String s2, String s3) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Connection dbConnection = ConnectionFactory.getConnection();
		String query = null;
		if (type.getSimpleName().equalsIgnoreCase("Client"))
        {query = "UPDATE `client` SET nume=?, prenume=?, oras=? " + "WHERE id_client=?";

        }
		else
		if (type.getSimpleName().equalsIgnoreCase("Product"))
			query = "UPDATE `product` SET titlu=?, pret=?, cantitate=?" + "WHERE id_product=?";
		PreparedStatement editStatement = dbConnection.prepareStatement(query);
		editStatement.setString(1, String.valueOf(s1));
		editStatement.setString(2, String.valueOf(s2));
		editStatement.setString(3, String.valueOf(s3));
		editStatement.setString(4, String.valueOf(id));
		editStatement.executeUpdate();
	}
}


