package fuentes.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import fuentes.constantes.*;

public class Jdbc {


        public synchronized static void clearHash()
        {
          Connection con = null;
                 try
                 {
                         Class.forName("org.sqlite.JDBC");
                         con = DriverManager.getConnection("jdbc:sqlite:libreria.sqlite");
                         con.setAutoCommit(false);
                         PreparedStatement updateSales = con.prepareStatement("delete from MD5");
                         int resultado = updateSales.executeUpdate();
                         con.commit();
                         System.out.println("Limpiada correctamente: "+resultado);
                         con.setAutoCommit(true);
                 }
                 catch(Exception e)
                 {
                                 System.out.println("Ha ocurrido una excepción: "+e.getMessage());
                 }
                 finally
                 {
                         try{
                                 if (con != null) con.close();
                         }
                         catch (Exception e){ System.out.println("Ha ocurrido una excepción clearHash al cerrar: "+e.getMessage());};
                 }

        }

	public synchronized static boolean existeHash(String hashComprobacion)
	{
		boolean existe = false;
		Connection con = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
                        con = DriverManager.getConnection("jdbc:sqlite:libreria.sqlite");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery("select hash from MD5 where hash='"+hashComprobacion+"'");

			if ( rs.next() ) {
				//int res = rs.getInt("hash" );
				//System.out.println( res );
				//if (res >= 1) existe=true;
                                if (Constante.debug) System.out.println( "Sí que existe hash" );
				existe=true;
			}
		}
		catch (Exception e)
		{
			System.out.println("Ha ocurrido una excepción existeHash: "+e.getMessage());
		}
		finally
		{
			try{
				if (con != null) con.close();
			}
			catch (Exception e){ System.out.println("Ha ocurrido una excepción al cerrar: "+e.getMessage());};
		}
		return existe;
	}

	public synchronized static void insertaHash(String hashInsertar)
	{
		Connection con = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			//Connection con = DriverManager.getConnection("jdbc:odbc:EclipseJDBC", "Dani", "Dani");
			con = DriverManager.getConnection("jdbc:sqlite:libreria.sqlite");
			con.setAutoCommit(false);
			PreparedStatement updateSales = con.prepareStatement("insert into MD5 (hash) values (?)");
			updateSales.setString(1, hashInsertar);
			int resultado = updateSales.executeUpdate();
			con.commit();
                        if (Constante.debug)System.out.println("Insertado correctamente: "+resultado);
			con.setAutoCommit(true);
		}
		catch(Exception e)
		{
				System.out.println("Ha ocurrido una excepción: "+e.getMessage());
		}
		finally
		{
			try{
				if (con != null) con.close();
			}
                         catch (Exception e){ System.out.println("Ha ocurrido una excepción insertaHash al cerrar: "+e.getMessage());};
		}
	}

}
