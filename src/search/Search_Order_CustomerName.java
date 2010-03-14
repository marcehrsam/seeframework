package search;

import java.util.ArrayList;

import mod_customer.Customer;
import mod_customer.MD_Customer;

public class Search_Order_CustomerName extends AbstractSearch{

	private String input = null; 
	
	private ArrayList<String> keyWords;
	
	private ArrayList<Object> result;
	
	public Search_Order_CustomerName(String input){
		this.input = input;
		keyWords = new ArrayList<String>();
		result = new ArrayList<Object>();
		InitKeyWords();
	}
	
	//Suchbegriffe initialisieren, leerzeichen entfernen etc.
	//Resultat = ArrayList mit suchbegriffen
	private void InitKeyWords() {
		
		//wC = working copy
		String wc = new String(input.trim());
		//current position
		int cp = 0;
		
		//solange noch leerzeichen im txt sind "zerstückel" den text
		while(wc.contains(" ")){
			if((wc.charAt(cp)) != ' '){
				cp++;
				//gehe nach rechts weiter
				continue;
			}else{
				//letzte pos nehmen und wort ausschneiden
				String newWord = wc.substring(0, cp);
				keyWords.add(newWord);
				//eliminiere Leerzeichen
				wc = wc.substring(cp+1, wc.length());
				//pointer auf pos 0
				cp=0;
				continue;
			}
		}		
		//letztes wort nicht vergessen ;)
		keyWords.add(wc);
	}

	@Override
	public Object search() {

		if(keyWords.isEmpty()){
			return null;
		}
		
		//Alle Kunden Abfragen
		for(Customer c : MD_Customer.getInstance().getAllCustomers()){
			String cName = c.getName();
			String cFirst = c.getVorname();

			//Iteration über alle Suchbegriffe
			for(String s : keyWords){
				if((cName.contains(s))||(cFirst.contains(s))){
					//gib aufträge des Kd. zurück
					
				}else{
					//nächster Kunde
					continue;
				}
			}
			

		}
		
		return null;
	}

	public static void main(String[] args){
		Search_Order_CustomerName s = new Search_Order_CustomerName("");
		
	}
	
}
