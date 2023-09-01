package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import factory.conexionBD;


public class Usuarios {
	private String nombre; 
	private String contrasena;
	public Usuarios(String nombre, String contrasena) {
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;		
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
		
	}
	
	public String getContrasena() {
		return contrasena;		
	}
	
	public void setContrasena(String contrasena){
		this.contrasena = contrasena;
		
	}
	
	public static boolean validarUsuario(String nombre, String contrasena) {
		conexionBD conexion = new conexionBD();
		Connection connection = null;
		PreparedStatement state = null;
		ResultSet result = null;
		try {
			connection = conexion.recuperarConexion();
			state = connection.prepareStatement("SELECT * FROM Usuarios WHERE nombre = ? AND contrasena = ?");
			state.setString(1, nombre);
			state.setString(2, contrasena);
			result = state.executeQuery();
			return result.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (result !=null)
					result.close();
				if (state !=null)
					state.close();
				if (connection !=null)
					connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}		
}
