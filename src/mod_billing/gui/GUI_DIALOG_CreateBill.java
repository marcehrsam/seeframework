package mod_billing.gui;

import gui.MyButton;
import gui.MyPanel;
import gui.MyTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mod_billing.action.ACT_BT_KLICK_ActivateFields;
import mod_billing.action.ACT_BT_KLICK_CANCEL_CreateBill;
import mod_billing.action.ACT_BT_KLICK_ChangeCustomer;
import mod_billing.action.ACT_BT_KLICK_ChooseCustomer;
import mod_billing.action.ACT_BT_KLICK_CreateCustomer;
import mod_billing.action.ACT_BT_KLICK_OK_CreateBill;
import mod_billing.action.ACT_BT_KLICK_SAVE_CreateBill;
import model_test.MyRechnungTable;
import model_test.Rechnung;
import tools.TO_JFrame;

public class GUI_DIALOG_CreateBill extends JDialog implements Observer, FocusListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6764656117948502040L;

	public final String TITLE = "Rechnung anlegen";
	
	//objects to enable
	private Collection<JComponent> componentsToEnable = null; 
	
	//Components
	private JPanel customerArea;
	private JPanel itemArea;
	private JPanel btArea;
	
	//Buttons
	private MyButton btChooseCustomer;
	private MyButton btChangeCustomer;
	private MyButton btCreateCustomer;
	private MyButton btOK;
	private MyButton btCancel;
	private MyButton btSave;
	private MyButton btChangeData;
	
	//Fields Customer
	private MyTextField tfCustomerTitle;
	private MyTextField tfCustomerName;
	private MyTextField tfCustomerFirstName;
	private MyTextField tfCustomerStreet;
	private MyTextField tfCustomerNumber;
	private MyTextField tfCustomerAdditionalField;
	private MyTextField tfCustomerZipCode;
	private MyTextField tfCustomerCity;
	private MyTextField tfCustomerID;
	private MyTextField tfBillDate;
	private MyTextField tfBillNumber;
	
	//Labels
	private JLabel jlCustomerTitle;
	private JLabel jlName;
	private JLabel jlFirstName;
	private JLabel jlStreet;
	private JLabel jlNumber;
	private JLabel jlAdress2;
	private JLabel jlZip;
	private JLabel jlCity;
	private JLabel jlDescription;
	private JLabel jlSum;
	
	private JScrollPane jspTable;
	private JTable tabPositions;
	
	public final String KDTITLE = "Anrede:";
	public final String NAME = "Name:";
	public final String FIRSTNAME = "Vorname:";
	public final String STREET = "Strasse:";
	public final String NUMBER = "Hausnummer:";
	public final String ADRESS2 = "Zusatzangaben:";
	public final String ZIP = "PLZ:";
	public final String CITY = "Ort:";
	public final String DESC = "Hier steht die Beschreibung";
	
	public final Dimension SIZE = new Dimension(1000, 700);
	//instance->Rechnung
	private Rechnung rechnung;
	
	public GUI_DIALOG_CreateBill(){
		setTitle(TITLE);
		setLayout(new BorderLayout());
		setSize(SIZE);
		setModal(true);
		TO_JFrame.getInstance().centerJFrame(this);
		
		rechnung = new Rechnung();
		componentsToEnable = new ArrayList<JComponent>();
		InitializeComponents();
		disableCustomerFields();
		setVisible(true);
	}
	
	private void InitializeComponents(){
		
		rechnung.addObserver(this);
		
		InitializeCustomer();
		InitializeBill();
		InitializeButtons();
		
	}
	
	private void InitializeCustomer(){
		
		//elemente instanziieren
		customerArea = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//customerArea.setBackground(Color.RED);
		
		btChooseCustomer = new MyButton(new ACT_BT_KLICK_ChooseCustomer(null, rechnung));
		btChangeCustomer = new MyButton(new ACT_BT_KLICK_ChangeCustomer(rechnung));
		btCreateCustomer = new MyButton(new ACT_BT_KLICK_CreateCustomer(rechnung));
		
		
		jlCustomerTitle = new JLabel(KDTITLE);
		jlName = new JLabel(NAME);
		jlFirstName = new JLabel(FIRSTNAME);
		jlStreet = new JLabel(STREET);
		jlNumber = new JLabel(NUMBER);
		jlAdress2 = new JLabel(ADRESS2);
		jlZip = new JLabel(ZIP);
		jlCity = new JLabel(CITY);
		
		tfCustomerID = new MyTextField(10);
		tfCustomerTitle = new MyTextField(10);
		tfCustomerName = new MyTextField(20);
		tfCustomerFirstName = new MyTextField(20);
		tfCustomerStreet = new MyTextField(20);
		tfCustomerNumber = new MyTextField(5);
		tfCustomerAdditionalField = new MyTextField(20);
		tfCustomerZipCode = new MyTextField(5);
		tfCustomerCity = new MyTextField(20);
		
		tfCustomerID.addFocusListener(this);
		tfCustomerTitle.addFocusListener(this);
		tfCustomerName.addFocusListener(this);
		tfCustomerFirstName.addFocusListener(this);
		tfCustomerStreet.addFocusListener(this);
		tfCustomerNumber.addFocusListener(this);
		tfCustomerAdditionalField.addFocusListener(this);
		tfCustomerZipCode.addFocusListener(this);
		tfCustomerCity.addFocusListener(this);
		
		tfCustomerID.addKeyListener(this);
		tfCustomerTitle.addKeyListener(this);
		tfCustomerName.addKeyListener(this);
		tfCustomerFirstName.addKeyListener(this);
		tfCustomerStreet.addKeyListener(this);
		tfCustomerNumber.addKeyListener(this);
		tfCustomerAdditionalField.addKeyListener(this);
		tfCustomerZipCode.addKeyListener(this);
		tfCustomerCity.addKeyListener(this);
		
		componentsToEnable.add(tfCustomerID);
		componentsToEnable.add(tfCustomerTitle);
		componentsToEnable.add(tfCustomerName);
		componentsToEnable.add(tfCustomerFirstName);
		componentsToEnable.add(tfCustomerStreet);
		componentsToEnable.add(tfCustomerNumber);
		componentsToEnable.add(tfCustomerAdditionalField);
		componentsToEnable.add(tfCustomerZipCode);
		componentsToEnable.add(tfCustomerCity);
		
		//panel, welches die buttons für kundeninteraktionen beherbergt
		JPanel jpCustomerButtons = new JPanel();
		
		//elemente anordnen
		jpCustomerButtons.add(btCreateCustomer);
		jpCustomerButtons.add(btChooseCustomer);
		jpCustomerButtons.add(btChangeCustomer);
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		customerArea.add(jpCustomerButtons, gbc);
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		
		//anrede
		gbc.gridy++;
		customerArea.add(jlCustomerTitle, gbc);
		gbc.gridx++;
		gbc.weightx = 2;
		customerArea.add(tfCustomerTitle, gbc);
		gbc.weightx = 1;
		gbc.gridx--;
		
		//name
		gbc.gridy++;
		customerArea.add(jlName, gbc);
		gbc.gridx++;
		customerArea.add(tfCustomerName, gbc);
		gbc.gridx--;
		
		//vorname
		gbc.gridy++;
		customerArea.add(jlFirstName, gbc);
		gbc.gridx++;
		customerArea.add(tfCustomerFirstName, gbc);
		gbc.gridx--;
		
		//strasse
		gbc.gridy++;
		customerArea.add(jlStreet, gbc);
		gbc.gridx++;
		customerArea.add(tfCustomerStreet, gbc);
		gbc.gridx--;
		
		//nummer
		gbc.gridy++;
		customerArea.add(jlNumber, gbc);
		gbc.gridx++;
		customerArea.add(tfCustomerNumber, gbc);
		gbc.gridx--;
		
		//adresszeile2
		gbc.gridy++;
		customerArea.add(jlAdress2, gbc);
		gbc.gridx++;
		customerArea.add(tfCustomerAdditionalField, gbc);
		gbc.gridx--;
				
		//plz
		gbc.gridy++;
		customerArea.add(jlZip, gbc);
		gbc.gridx++;
		customerArea.add(tfCustomerZipCode, gbc);
		gbc.gridx--;
		
		//ort
		gbc.gridy++;
		customerArea.add(jlCity, gbc);
		gbc.gridx++;
		customerArea.add(tfCustomerCity, gbc);
		gbc.gridx--;
				
		getContentPane().add(customerArea, BorderLayout.WEST);
		
	}
	
	private void InitializeBill(){
		
		itemArea = new JPanel();
		itemArea.setBackground(Color.BLUE);
		
		jspTable = new JScrollPane();
		tabPositions = new MyRechnungTable(null);
		tabPositions.setModel(rechnung);

		jspTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jspTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		rechnung.setAnrede(rechnung.getAnrede());
			
		//Bereich für Items
		jlDescription = new JLabel(DESC);
		jlSum = new JLabel("€: 5,00");
		
		itemArea.add(jlDescription);
		itemArea.add(jspTable);
		itemArea.add(jlSum);
		
		jspTable.setViewportView(tabPositions);
		getContentPane().add(itemArea, BorderLayout.CENTER);
		
	}
	
	private void InitializeButtons(){
		
		btArea = new JPanel();

		btOK = new MyButton(new ACT_BT_KLICK_OK_CreateBill(this, rechnung));
		btCancel = new MyButton(new ACT_BT_KLICK_CANCEL_CreateBill(this));
		btSave = new MyButton(new ACT_BT_KLICK_SAVE_CreateBill(rechnung));
		btChangeData = new MyButton(new ACT_BT_KLICK_ActivateFields(this));
		
		btArea.add(btOK);
		btArea.add(btCancel);
		btArea.add(btSave);
		btArea.add(btChangeData);
		
		getContentPane().add(btArea, BorderLayout.SOUTH);
		
		
	}
	
	public void update(Observable arg0, Object arg1) {
		
		//alles aktualisieren
		tfCustomerTitle.setText(rechnung.getAnrede());
		tfCustomerName.setText(rechnung.getName());
		tfCustomerFirstName.setText(rechnung.getVorname());
		tfCustomerStreet.setText(rechnung.getStrasse());
		tfCustomerNumber.setText(rechnung.getNummer());
		tfCustomerAdditionalField.setText(rechnung.getZusatzZeile());
		tfCustomerZipCode.setText(rechnung.getPlz());
		tfCustomerCity.setText(rechnung.getOrt());
		tfCustomerID.setText(rechnung.getKundenNummer());
		
		
	}

	public void focusGained(FocusEvent e) {
		if(e.getSource()==tfCustomerID){
			tfCustomerID.selectAll();
		}
		else if(e.getSource()==tfCustomerTitle)tfCustomerTitle.selectAll();
		else if(e.getSource()==tfCustomerName)tfCustomerName.selectAll();
		else if(e.getSource()==tfCustomerFirstName)tfCustomerFirstName.selectAll();
		else if(e.getSource()==tfCustomerStreet)tfCustomerStreet.selectAll();
		else if(e.getSource()==tfCustomerNumber)tfCustomerNumber.selectAll();
		else if(e.getSource()==tfCustomerAdditionalField)tfCustomerAdditionalField.selectAll();
		else if(e.getSource()==tfCustomerZipCode)tfCustomerZipCode.selectAll();
		else if(e.getSource()==tfCustomerCity)tfCustomerCity.selectAll();
		
	}

	public void focusLost(FocusEvent e) {
		if(e.getSource()==tfCustomerID){
			//neue kundendaten aus db abrufen!!
		}
		else if(e.getSource()==tfCustomerTitle)rechnung.setAnrede(tfCustomerTitle.getText());
		else if(e.getSource()==tfCustomerName)rechnung.setName(tfCustomerName.getText());
		else if(e.getSource()==tfCustomerFirstName)rechnung.setVorname(tfCustomerFirstName.getText());
		else if(e.getSource()==tfCustomerStreet)rechnung.setStrasse(tfCustomerStreet.getText());
		else if(e.getSource()==tfCustomerNumber)rechnung.setNummer(tfCustomerNumber.getText());
		else if(e.getSource()==tfCustomerAdditionalField)rechnung.setZusatzZeile(tfCustomerAdditionalField.getText());
		else if(e.getSource()==tfCustomerZipCode)rechnung.setPlz(tfCustomerZipCode.getText());
		else if(e.getSource()==tfCustomerCity)rechnung.setOrt(tfCustomerCity.getText());
	}	
	
	public void enableCustomerFields(){
		for(JComponent c: componentsToEnable){
			c.setEnabled(true);
		}
	}
	
	public void disableCustomerFields(){
		for(JComponent c: componentsToEnable){
			c.setEnabled(false);
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(e.getSource()==tfCustomerID){
				//neue kundendaten aus db abrufen!!
			}
			else if(e.getSource()==tfCustomerTitle){
				rechnung.setAnrede(tfCustomerTitle.getText());
				tfCustomerName.requestFocus();
			}
			else if(e.getSource()==tfCustomerName){
				rechnung.setName(tfCustomerName.getText());
				tfCustomerFirstName.requestFocus();
			}
			else if(e.getSource()==tfCustomerFirstName){
				rechnung.setVorname(tfCustomerFirstName.getText());
				tfCustomerStreet.requestFocus();
			}
			else if(e.getSource()==tfCustomerStreet){
				rechnung.setStrasse(tfCustomerStreet.getText());
				tfCustomerNumber.requestFocus();
			}
			else if(e.getSource()==tfCustomerNumber){
				rechnung.setNummer(tfCustomerNumber.getText());
				tfCustomerAdditionalField.requestFocus();
			}
			else if(e.getSource()==tfCustomerAdditionalField){
				rechnung.setZusatzZeile(tfCustomerAdditionalField.getText());
				tfCustomerZipCode.requestFocus();
			}
			else if(e.getSource()==tfCustomerZipCode){
				rechnung.setPlz(tfCustomerZipCode.getText());
				tfCustomerCity.requestFocus();
			}
			else if(e.getSource()==tfCustomerCity){
				rechnung.setOrt(tfCustomerCity.getText());
				disableCustomerFields();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
