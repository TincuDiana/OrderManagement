package model;
/**
 * Aceasta este o clasa care corespunde cu tabela Product din baza de date, ea are asadar ca si atribute, coloanele din baza de date
 * contine si setteri si getteri pentru aceste atribute
 * @author Tincu Diana
 *
 */
public class Product {
	public int id_product;
	public String titlu;
	public int pret;
	public int cantitate;
	
	public Product()
	{
		super();
	}
	public Product(int id,String titlu,int pret,int cantitate)
	{
		this.id_product=id;
		this.titlu=titlu;
		this.pret=pret;
		this.cantitate=cantitate;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

}
