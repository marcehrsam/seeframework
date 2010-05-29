package mod_products.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import model_test.Produkt;

public class ProductViewPanel extends JPanel implements Observer, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5328584459474145891L;

	private JButton saveBtn = null;
	private JButton cancelBtn = null;
	
	private JTextField artTxt = null;
	private JTextField nameTxt = null;
	private JTextArea descTxtArea = null;
	private JRadioButton stckBtn = null;
	private JRadioButton stundeBtn = null;
	private JRadioButton sonstBtn = null;
	private JTextField sonstTxt = null;
	private JTextField priceTxt = null;
	private JTextField geaeTxt = null;
	private JTextField vonTxt = null;
	
	private Produkt prod = null;
	
	public ProductViewPanel(Produkt selection) {
		this.prod = selection;
		if(prod!=null){
			prod.addObserver(this);
		}
		initLayout();
		update(null, null);
	}

	private void initLayout() {
		setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setSize(30, 1);
		add(leftPanel, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel(new GridBagLayout());
		add(centerPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.WEST;
		//gbc.fill = GridBagConstraints.BOTH;
		//gbc.gridwidth = 3;
		//gbc.insets = new Insets(20, 20, 20,20);
		
		gbc.gridy++;
		gbc.weightx = 3;
		JLabel titleLab = new JLabel("Produktinfos");
		centerPanel.add(titleLab, gbc);
		
		gbc.gridy++;
		gbc.weightx = 1;
		JLabel artLab = new JLabel("Artikelnummer");
		centerPanel.add(artLab, gbc);
		gbc.gridx++;
		gbc.weightx = 2;
		artTxt = new JTextField(20);
		artTxt.setEnabled(false);
		centerPanel.add(artTxt, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		gbc.weightx = 1;
		JLabel nameLab = new JLabel("Bezeichnung");
		centerPanel.add(nameLab, gbc);
		gbc.gridx++;
		nameTxt = new JTextField(50);
		nameTxt.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//TODO: nach nicht zulässigen zeichen filtern
				prod.setBezeichnung(((JTextField)e.getSource()).getText());
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyTyped(e);
			}
			
		});
		centerPanel.add(nameTxt, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel descLab = new JLabel("Beschreibung");
		centerPanel.add(descLab, gbc);
		gbc.gridx++;
		descTxtArea = new JTextArea(10,50);
		descTxtArea.addKeyListener(this);
		descTxtArea.setBorder(nameTxt.getBorder());
		centerPanel.add(descTxtArea, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel einhLab = new JLabel("Einheit");
		centerPanel.add(einhLab, gbc);
		gbc.weightx = 3;
		gbc.gridwidth = 3;
		gbc.gridx++;
		JPanel einheitPanel = new JPanel();
		einheitPanel.setBorder(new EtchedBorder());
		centerPanel.add(einheitPanel, gbc);
		gbc.gridx--;
		gbc.gridwidth = 1;
		
		ButtonGroup einheitButtons = new ButtonGroup();
		stckBtn = new JRadioButton("Stück");
		einheitButtons.add(stckBtn);
		stundeBtn = new JRadioButton("Stunde");
		einheitButtons.add(stundeBtn);
		sonstBtn = new JRadioButton("sonstiges");
		sonstBtn.addKeyListener(this);
		einheitButtons.add(sonstBtn);
		sonstTxt = new JTextField(20);
		
		einheitPanel.add(stckBtn);
		einheitPanel.add(stundeBtn);
		einheitPanel.add(sonstBtn);
		einheitPanel.add(sonstTxt);
		
		gbc.gridy++;
		gbc.gridx = 1;
		JLabel priceLab = new JLabel("Einzelpreis");
		centerPanel.add(priceLab, gbc);
		gbc.gridx++;
		priceTxt = new JTextField(10);
		priceTxt.addKeyListener(this);
		centerPanel.add(priceTxt, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel geaeLab = new JLabel("zuletzt geändert am");
		centerPanel.add(geaeLab, gbc);
		gbc.gridx++;
		geaeTxt = new JTextField(20);
		geaeTxt.addKeyListener(this);
		centerPanel.add(geaeTxt, gbc);
		gbc.gridx--;
		
		gbc.gridy++;
		JLabel vonLab = new JLabel("von");
		centerPanel.add(vonLab, gbc);
		gbc.gridx++;
		vonTxt = new JTextField(20);
		vonTxt.addKeyListener(this);
		centerPanel.add(vonTxt, gbc);
		gbc.gridx--;
		
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		
		saveBtn = new JButton("Speichern");
		southPanel.add(saveBtn);
		cancelBtn = new JButton("Abbrechen");
		southPanel.add(cancelBtn);
	}
	
	private void setButtonsEnabled(boolean value){
		saveBtn.setEnabled(value);
		cancelBtn.setEnabled(value);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		artTxt.setText(prod.getArtNr());
		nameTxt.setText(prod.getBezeichnung());
		descTxtArea.setText("nix");
		stckBtn.setSelected(true);
		stundeBtn.setSelected(true);
		sonstBtn.setSelected(true);
		sonstTxt.setText("nix");
		priceTxt.setText(prod.getPreis() + "");
		geaeTxt.setText("01.01.2010");
		vonTxt.setText("niemandem");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
