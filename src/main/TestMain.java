package main;

import tools.MyDatabaseStructureFactory;
import tools.SQLTools;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SQLTools tool = new SQLTools();
		tool.connectToDB();
		MyDatabaseStructureFactory fac = new MyDatabaseStructureFactory();
		tool.exec(fac.createCreateTableStatement(fac.S_USERS));
		tool.validateStructure(fac.S_USERS);
		tool.disconnectFromDB();
	}

}
