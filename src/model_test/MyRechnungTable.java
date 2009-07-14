package model_test;

import javax.swing.JTable;

public class MyRechnungTable extends JTable {

	private Rechnung rechnung;
	
	public final String POS = "PosNr";
	public final String ART = "ArtNr";
	public final String ANZ = "Anz";
	public final String BEZ = "Bezeichnung";
	public final String PRE = "Einzelpreis";
	
    private final String[] columnNames = {POS, ART, ANZ, BEZ, PRE};

	
	public MyRechnungTable(Rechnung rechnung){
		super(rechnung);
		this.rechnung = rechnung;
		
	}

}
