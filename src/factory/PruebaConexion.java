package factory;
import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {


		
		public static void main(String[] args) throws SQLException {
			conexionBD conexion = new conexionBD();
			Connection con = conexion.recuperarConexion();
			
			System.out.println("Probando conexion");
			
			con.close();
			System.out.println("cerrando conexion");
		}

	}
