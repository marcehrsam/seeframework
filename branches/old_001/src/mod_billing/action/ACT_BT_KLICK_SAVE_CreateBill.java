package mod_billing.action;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.AbstractAction;

import model_test.Rechnung;

import com.lowagie.text.DocumentException;

public class ACT_BT_KLICK_SAVE_CreateBill extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4404890409963413622L;
	
	public final String SAVE = "PDF erzeugen";
	
	private Rechnung rechnung;
	
	public ACT_BT_KLICK_SAVE_CreateBill(Rechnung rechnung){
		this.rechnung = rechnung;
		putValue(AbstractAction.NAME, SAVE);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			rechnung.generatePdf();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
