package mod_billing.action;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import mod_billing.MD_Billing;
import model_test.Rechnung;
import model_test.Rechnung1;

public class ACT_BT_KLICK_OK_CreateBill extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8800239684746357734L;
	
	private JDialog parent;
	private Rechnung rechnung; 
	
	public final String OK = "OK";
	
	public ACT_BT_KLICK_OK_CreateBill(JDialog parent, Rechnung rechnung){
		putValue(AbstractAction.NAME, OK);
		this.parent = parent;
		this.rechnung = rechnung;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		MD_Billing.getInstance().addRechnung(rechnung);
		try {
			rechnung.writeToDb();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(parent!=null)parent.dispose();

	}

}
