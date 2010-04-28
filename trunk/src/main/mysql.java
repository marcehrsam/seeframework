package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql
  {

  public static void main( String[] argv )
    {
    String treiber=null, DbUrl=null ;

    //*** "Name des Datenbanktreibers eingeben
    treiber = "org.gjt.mm.mysql.Driver" ;
    //*** "Url der Databank eingeben *********
    //*** Server : linux
    //*** Service-Nummer : 3306
    //*** Bezeichnung der Datenbank : test1
    //DbUrl = "jdbc:mysql://db4free.net:3306/sfw_db1" ;
    //DbUrl = "jdbc:mysql://localhost:3306/test" ;
    DbUrl = "jdbc:mysql://marc-ehrsam.de:3306/usr_web1187_8" ;
    
     try {
         //*** Treiber laden ***********************************
         Class.forName( treiber ).newInstance();
          //*** Verbindung aufnehmen:    ************************
          //*** Der User peter mit Kennwort mysql möcht was wissen
         Connection cn = DriverManager.getConnection( DbUrl, "web1187", "dechemax" );
         BufferedReader in = new BufferedReader(
                             new InputStreamReader( System.in ) );
        String frage = null ;
        while( true )
            {  //*** Endlosschleife für die Abfragen *****************
             System.out.println( "Verbindung steht!!" );
             System.out.println( "Gib Deine Anfrage ein!!") ;
             System.out.println( "Beenden, wenn Du stop eingibst!!" ) ;
             frage = in.readLine() ;
             if( frage.equals( "stop" ) ) break ;
             try { //*** Anfrage  - Fehler abfangen **********************
                 Statement  st = cn.createStatement();
                 ResultSet  rs = st.executeQuery( frage );
                 ResultSetMetaData rsmd = rs.getMetaData();
                 int n, nmax = rsmd.getColumnCount();
                 System.out.println("----------- Antwort -------------------") ;
                 while( rs.next() )
                    { //**** SchauMerMal  Primitive Ausgabe    **********
                    for( n=1 ; n<=nmax ; n++ )
                       System.out.print( rs.getString( n ) + "--" ) ;
                    System.out.println() ;
                    }
                 System.out.println("----------- Antwort -------------------") ;
                 rs.close();
                 st.close();
                 } catch( SQLException ex )
                         {  System.out.println( ex );   }
            }
        cn.close();
        } catch( Exception ex ) {   System.out.println( ex );   }
   }
}