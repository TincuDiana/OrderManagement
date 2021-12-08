package bll;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import connection.ConnectionFactory;
import dao.ClientDao;
import model.Client;

/**
 * Aceasta este o clasa care apeleaza metodele din clasa DAO corespunzatoare
 * @author Tincu Diana
 *
 */
public class ClientBll {
	private ClientDao clientDao;
	
	public ClientBll()
	{
		clientDao=new ClientDao();
	}
	/**
	 * Aceasta metoda apeleaza metoda de cautare a unui client dupa id-ul transmis ca si parametru din clasa abstracta DAO
	 * @param id
	 * @return Client
	 */
	public Client findClientById(int id) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, IntrospectionException  {
		Client client = clientDao.findById(id);
        if (client == null) {
            throw new NoSuchElementException("No client found with the id of " + id);
        }
        return client;
	}
	/**
	 * Aceasta metoda apeleaza metoda de delete a unui client dupa id-ul transmis ca si parametru din clasa abstracta DAO
	 * @param id
	 * @return int
	 */
	public int deleteClient( int id) throws IllegalArgumentException, IllegalAccessException, SQLException {
		int result = clientDao.delete(id);
		return result;
	}

	/**
	 * Aceasta metoda apeleaza metoda de insert client din clasa abstracta DAO
	 * @param client
	 * @return int
	 */
	public int insertClient( Client client ) throws IllegalArgumentException, IllegalAccessException{
	        int result = clientDao.insert(client);
	        return result;
	    }

	/**
	 * Aceasta metoda apeleaza metoda de edit client din clasa abstracta DAO
	 * @param id, nume, prenume, oras
	 * @return
	 */
	public void editClient(int id, String nume, String prenume, String oras) throws IllegalArgumentException, IllegalAccessException, SQLException {
		clientDao.edit(id, nume, prenume, oras);
	}

	/**
	 * Aceasta metoda apeleaza metoda afiseaza toti clientii din tabela de clienti care au id-ul >0
	 * @param
	 * @return List<Client></Client>
	 */
	public List<Client> viewAllClients() {
		Connection dbConnection = ConnectionFactory.getConnection();
		List<Client> clients=new ArrayList<>();
		String query = "SELECT * FROM `client` WHERE id_client>0";
		PreparedStatement viewStatement = null;
		try { viewStatement = dbConnection.prepareStatement(query); } catch (SQLException throwables) { throwables.printStackTrace(); }
		ResultSet rs = null;
		try { rs = viewStatement.executeQuery(); } catch (SQLException throwables) { throwables.printStackTrace(); }
		int i =0;
		String id= "";
		String nume= "";
		String prenume = "";
		String oras = "";
		try {
			while(rs.next())
			{
				id = rs.getString("id_client");
				nume = rs.getString("nume");
				prenume = rs.getString("prenume");
				oras = rs.getString("oras");
				clients.add(new Client(Integer.valueOf(id), nume, prenume, oras));
			}
		} catch (SQLException throwables) { throwables.printStackTrace(); }
		return clients;
	}

	public static void main(String[] args) throws IllegalAccessException, SecurityException, IllegalArgumentException, InvocationTargetException, IntrospectionException, SQLException {
		ClientBll c=new ClientBll();
		c.editClient(3, "Carl", "Amanda", "Bucuresti");
	}

}
