package model;
/**
 * Aceasta este o clasa care corespunde cu tabela orders din baza de date, ea are asadar ca si atribute,
 * coloanele tabelului respectiv din baza de date si contine setteri si getteri pentru aceste atribute
 * @author Tincu Diana
 *
 */
public class Orders {
	public int id_order;
	public int cantitate;
	public int id_product_orders;
	public int id_client_orders;
	public Orders()
	{
		super();
	}
	public Orders(int id,int cantitate,int id_product,int id_client)
	{
		this.id_order=id;
		//this.total=total;
		this.cantitate=cantitate;
		this.id_product_orders=id_product;
		this.id_client_orders=id_client;
	}
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	public int getId_product_orders() {
		return id_product_orders;
	}
	public void setId_product_orders(int id_product_orders) {
		this.id_product_orders = id_product_orders;
	}
	public int getId_client_orders() {
		return id_client_orders;
	}
	public void setId_client_orders(int id_client_orders) {
		this.id_client_orders = id_client_orders;
	}
}
