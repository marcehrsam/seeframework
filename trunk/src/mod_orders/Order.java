package mod_orders;

import java.util.ArrayList;

import mod_customer.Customer;
import model_test.AbstractBeleg;

public class Order extends AbstractBeleg {

	private ArrayList<AbstractBeleg> belege;
		
	public Order(Customer kunde){
		super("xxxxxx");
		setKunde(kunde);
		Init();
	}
	
	public Order(){
		super("xxxxxx");
		Init();
	}

	private void Init(){
		belege = new ArrayList<AbstractBeleg>();
		
	}
	
	public ArrayList<AbstractBeleg> getBelege() {
		return belege;
	}

	public void setBelege(ArrayList<AbstractBeleg> belege) {
		this.belege = belege;
	}

	@Override
	public boolean canAddBeleg() {
		return true;
	}
	
}
