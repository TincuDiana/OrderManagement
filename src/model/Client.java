package model;
/**
 * Aceasta este o clasa care corespunde cu tabela Client din baza de date, ea are asadar ca si atribute,
 * coloanele tabelului respectiv din baza de date si contine setteri si getteri pentru aceste atribute
 * @author Tincu Diana
 *
 */
public class Client {
public  int id_client;
public  String nume;
public	 String prenume;
public	 String oras;
	
	public Client()
	{
		super();
	}
	public Client( int id,String nume,String prenume,String oras)
	{
		this.id_client=id;
		this.nume=nume;
		this.prenume=prenume;
		this.oras=oras;
	}
	
	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getOras() {
		return oras;
	}

	public void setOras(String oras) {
		this.oras = oras;
	}

}
