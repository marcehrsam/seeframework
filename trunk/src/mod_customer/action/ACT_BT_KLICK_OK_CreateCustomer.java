package mod_customer.action;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import tools.Debug;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import mod_customer.AbstractCustomer;
import model_test.ICustomerHolder;



public class ACT_BT_KLICK_OK_CreateCustomer extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1945617270684892531L;

	private ICustomerHolder holder;
	private AbstractCustomer customer;
	private JDialog parent;
	
	public final String OK = "Speichern";
	
	public ACT_BT_KLICK_OK_CreateCustomer(JDialog parent, ICustomerHolder holder, AbstractCustomer customer){
		putValue(AbstractAction.NAME, OK);
		this.holder = holder;
		this.customer = customer;
		this.parent = parent;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(holder != null){
			holder.setCustomer(customer);
		}else{
			Debug.out("holder == null in ACT_BT_KLICK_OK_CreateCustomer.");
		}
		
		//in db schreiben
		try {
			customer.writeToDB();
		}catch(MySQLIntegrityConstraintViolationException e){
			JOptionPane.showMessageDialog(null, "Der Kunde \"" + customer.getKundenNummer() + "\" existiert bereits in der Datenbank!", "Fehler", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Fehler beim Schreiben in DB!");
			e.printStackTrace();
		}
		
		//fenster schlieﬂen
		if(parent!=null)parent.dispose();
	}

}
