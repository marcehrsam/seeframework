package mod_customer.gui;

import gui.MyButton;
import gui.MyPanel;
import gui.MyTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mod_customer.AbstractCustomer;
import mod_customer.Customer;
import mod_customer.action.ACT_BT_KLICK_CANCEL_CreateCustomer;
import mod_customer.action.ACT_BT_KLICK_OK_CreateCustomer;
import model_test.ICustomerHolder;
import tools.TO_JFrame;

public class GUI_DIALOG_CreateCustomer extends JDialog implements FocusListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1649844650526692381L;
	
	private AbstractCustomer customer; 

	private MyTextField tfCustomerTitle;
	private MyTextField tfName;
	private MyTextField tfFirstName;
	private MyTextField tfStreet;
	private MyTextField tfNumber;
	private MyTextField tfAdditionalField;
	private MyTextField tfZipCode;
	private MyTextField tfCity;
	private MyTextField tfPhone;
	private MyTextField tfMobile;
	private MyTextField tfMail;
	
	private JLabel jlCustomerTitle;
	private JLabel jlName;
	private JLabel jlFirstName;
	private JLabel jlStreet;
	private JLabel jlNumber;
	private JLabel jlAdress2;
	private JLabel jlZip;
	private JLabel jlCity;
	private JLabel jlPhone;
	private JLabel jlMobile;
	private JLabel jlMail;
	
	public final String TITLE = "Kunde anlegen";
	public final String KDTITLE = "Anrede:";
	public final String NAME = "Name:";
	public final String FIRSTNAME = "Vorname:";
	public final String STREET = "Strasse:";
	public final String NUMBER = "Hausnummer:";
	public final String ADRESS2 = "Zusatzangaben:";
	public final String ZIP = "PLZ:";
	public final String CITY = "Ort:";
	public final String PHONE = "Telefon:";
	public final String MOBILE = "Handy:";
	public final String MAIL = "E-Mail:";
	
	
	private MyButton btOk;
	private MyButton btCancel;
	
	private ICustomerHolder holder;
	//zu testzwecken!!!
	public GUI_DIALOG_CreateCustomer(ICustomerHolder holder){
		
		this.holder = holder;
		this.customer = new Customer();
		
		setLayout(new BorderLayout());
		setSize(new Dimension(600, 600));
		setTitle(TITLE);
		setModal(true);
		TO_JFrame.getInstance().centerJFrame(this);
		InitializeComponents();
		
		setVisible(true);
	}
	
	private void InitializeComponents(){
		
		tfCustomerTitle = new MyTextField(8);
		tfName = new MyTextField(20);
		tfFirstName = new MyTextField(20);
		tfStreet = new MyTextField(20);
		tfNumber = new MyTextField(5);
		tfAdditionalField = new MyTextField(20);
		tfZipCode = new MyTextField(5);
		tfCity = new MyTextField(20);
		tfPhone = new MyTextField(20);
		tfMobile = new MyTextField(20);
		tfMail = new MyTextField(20);
		
		tfCustomerTitle.setText(customer.getAnrede());
		tfName.setText(customer.getName());
		tfFirstName.setText(customer.getVorname());
		tfStreet.setText(customer.getStrasse());
		tfNumber.setText(customer.getNummer());
		tfAdditionalField.setText(customer.getZusatzZeile());
		tfZipCode.setText(customer.getPlz());
		tfCity.setText(customer.getOrt());
		tfPhone.setText(customer.getTel());
		tfMobile.setText(customer.getMobil());
		tfMail.setText(customer.getMail());
		
		tfCustomerTitle.addFocusListener(this);
		tfName.addFocusListener(this);
		tfFirstName.addFocusListener(this);
		tfStreet.addFocusListener(this);
		tfNumber.addFocusListener(this);
		tfAdditionalField.addFocusListener(this);
		tfZipCode.addFocusListener(this);
		tfCity.addFocusListener(this);
		tfPhone.addFocusListener(this);
		tfMobile.addFocusListener(this);
		tfMail.addFocusListener(this);
		
		tfCustomerTitle.addKeyListener(this);
		tfName.addKeyListener(this);
		tfFirstName.addKeyListener(this);
		tfStreet.addKeyListener(this);
		tfNumber.addKeyListener(this);
		tfAdditionalField.addKeyListener(this);
		tfZipCode.addKeyListener(this);
		tfCity.addKeyListener(this);
		tfPhone.addKeyListener(this);
		tfMobile.addKeyListener(this);
		tfMail.addKeyListener(this);
		
		jlCustomerTitle = new JLabel(KDTITLE);
		jlName = new JLabel(NAME);
		jlFirstName = new JLabel(FIRSTNAME);
		jlStreet = new JLabel(STREET);
		jlNumber = new JLabel(NUMBER);
		jlAdress2 = new JLabel(ADRESS2);
		jlZip = new JLabel(ZIP);
		jlCity = new JLabel(CITY);
		jlPhone = new JLabel(PHONE);
		jlMobile = new JLabel(MOBILE);
		jlMail = new JLabel(MAIL);		
		
		btOk = new MyButton(new ACT_BT_KLICK_OK_CreateCustomer(this, holder, customer));
		btCancel = new MyButton(new ACT_BT_KLICK_CANCEL_CreateCustomer(this));
		
		btOk.addKeyListener(this);
		btCancel.addKeyListener(this);
		
		MyPanel content = new MyPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		JPanel leftPlaceHolder = new JPanel();
		leftPlaceHolder.setSize(new Dimension(50, 1));
		content.add(leftPlaceHolder, gbc);
		gbc.gridx++;
		
		//anrede
		gbc.gridy++;
		content.add(jlCustomerTitle, gbc);
		gbc.gridx++;
		gbc.weightx = 2;
		content.add(tfCustomerTitle, gbc);
		gbc.weightx = 1;
		gbc.gridx--;
		
		//name
		gbc.gridy++;
		content.add(jlName, gbc);
		gbc.gridx++;
		content.add(tfName, gbc);
		gbc.gridx--;
		
		//vorname
		gbc.gridy++;
		content.add(jlFirstName, gbc);
		gbc.gridx++;
		content.add(tfFirstName, gbc);
		gbc.gridx--;
		
		//strasse
		gbc.gridy++;
		content.add(jlStreet, gbc);
		gbc.gridx++;
		content.add(tfStreet, gbc);
		gbc.gridx--;
		
		//nummer
		gbc.gridy++;
		content.add(jlNumber, gbc);
		gbc.gridx++;
		content.add(tfNumber, gbc);
		gbc.gridx--;
		
		//adresszeile2
		gbc.gridy++;
		content.add(jlAdress2, gbc);
		gbc.gridx++;
		content.add(tfAdditionalField, gbc);
		gbc.gridx--;
				
		//plz
		gbc.gridy++;
		content.add(jlZip, gbc);
		gbc.gridx++;
		content.add(tfZipCode, gbc);
		gbc.gridx--;
		
		//ort
		gbc.gridy++;
		content.add(jlCity, gbc);
		gbc.gridx++;
		content.add(tfCity, gbc);
		gbc.gridx--;
		
		//TelNr
		gbc.gridy++;
		content.add(jlPhone, gbc);
		gbc.gridx++;
		content.add(tfPhone, gbc);
		gbc.gridx--;
		
		//Handy
		gbc.gridy++;
		content.add(jlMobile, gbc);
		gbc.gridx++;
		content.add(tfMobile, gbc);
		gbc.gridx--;
		
		//Mailadr.
		gbc.gridy++;
		content.add(jlMail, gbc);
		gbc.gridx++;
		content.add(tfMail, gbc);
		gbc.gridx--;
		
		MyPanel btArea = new MyPanel();
		btArea.add(btOk);
		btArea.add(btCancel);
		
		getContentPane().add(content, BorderLayout.CENTER);
		getContentPane().add(btArea, BorderLayout.SOUTH);
	}

	public void focusGained(FocusEvent e) {
		if(e.getSource()==tfCustomerTitle)tfCustomerTitle.selectAll();
		else if(e.getSource()==tfName)tfName.selectAll();
		else if(e.getSource()==tfFirstName)tfFirstName.selectAll();
		else if(e.getSource()==tfStreet)tfStreet.selectAll();
		else if(e.getSource()==tfNumber)tfNumber.selectAll();
		else if(e.getSource()==tfAdditionalField)tfAdditionalField.selectAll();
		else if(e.getSource()==tfZipCode)tfZipCode.selectAll();
		else if(e.getSource()==tfCity)tfCity.selectAll();
		else if(e.getSource()==tfPhone)tfPhone.selectAll();
		else if(e.getSource()==tfMobile)tfMobile.selectAll();
		else if(e.getSource()==tfMail)tfMail.selectAll();
		
	}

	public void focusLost(FocusEvent e) {
		if(e.getSource()==tfCustomerTitle)customer.setAnrede(tfCustomerTitle.getText());
		else if(e.getSource()==tfName)customer.setName(tfName.getText());
		else if(e.getSource()==tfFirstName)customer.setVorname(tfFirstName.getText());
		else if(e.getSource()==tfStreet)customer.setStrasse(tfStreet.getText());
		else if(e.getSource()==tfNumber)customer.setNummer(tfNumber.getText());
		else if(e.getSource()==tfAdditionalField)customer.setZusatzZeile(tfAdditionalField.getText());
		else if(e.getSource()==tfZipCode)customer.setPlz(tfZipCode.getText());
		else if(e.getSource()==tfCity)customer.setOrt(tfCity.getText());
		else if(e.getSource()==tfPhone)customer.setTel(tfPhone.getText());
		else if(e.getSource()==tfMobile)customer.setMobil(tfMobile.getText());
		else if(e.getSource()==tfMail)customer.setMail(tfMail.getText());
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(e.getSource()==tfCustomerTitle){
				customer.setAnrede(tfCustomerTitle.getText());
				tfName.requestFocus();
			}
			else if(e.getSource()==tfName){
				customer.setName(tfName.getText());
				tfFirstName.requestFocus();
			}
			else if(e.getSource()==tfFirstName){
				customer.setVorname(tfFirstName.getText());
				tfStreet.requestFocus();
			}
			else if(e.getSource()==tfStreet){
				customer.setStrasse(tfStreet.getText());
				tfNumber.requestFocus();
			}
			else if(e.getSource()==tfNumber){
				customer.setNummer(tfNumber.getText());
				tfAdditionalField.requestFocus();
			}
			else if(e.getSource()==tfAdditionalField){
				customer.setZusatzZeile(tfAdditionalField.getText());
				tfZipCode.requestFocus();
			}
			else if(e.getSource()==tfZipCode){
				customer.setPlz(tfZipCode.getText());
				tfCity.requestFocus();
			}
			else if(e.getSource()==tfCity){
				customer.setOrt(tfCity.getText());
				tfPhone.requestFocus();
			}
			else if(e.getSource()==tfPhone){
				customer.setTel(tfPhone.getText());
				tfMobile.requestFocus();
			}
			else if(e.getSource()==tfMobile){
				customer.setMobil(tfMobile.getText());
				tfMail.requestFocus();
			}
			else if(e.getSource()==tfMail){
				customer.setMail(tfMail.getText());
				btOk.requestFocus();
			}
			else if(e.getSource()==btOk){
				btOk.doClick();
			}
			else if(e.getSource()==btCancel){
				btCancel.doClick();
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
