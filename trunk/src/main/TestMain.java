package main;

import mod_user.MyUser;
import mod_user.User;
import tools.SQLTools;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLTools tool = new SQLTools();
//		MyDatabaseStructureFactory fac = new MyDatabaseStructureFactory();
////		tool.exec(fac.createCreateTableStatement(fac.S_USERS));
//		tool.createMissingTablesInDB(fac.struc.keySet());
//		if(tool.validateStructure()){
//			System.out.println("Alle Tabellen in Ordnung.");
//		}else{
//			System.out.print("DB ist inkonsistent.");
//		}
//		tool.createMissingColumns(null);
		User us = new MyUser(1);
		System.out.println(us);
	}

}
