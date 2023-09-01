package CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespedes;



public class HuespedesCRUD {

	private Connection conexion;
	
	public HuespedesCRUD(Connection conexion) {
		this.conexion= conexion;
	}
	
	public void guardar(Huespedes huespedes) {
		try {
			String sql = "INSERT INTO Huespedes (nombre,apellido,fecha_de_nacimiento,"
					+ "nacionalidad,telefono,id_Reserva) VALUES(?,?,?,?,?,?)";
			try(PreparedStatement pstm = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, huespedes.getNombre());
				pstm.setString(2, huespedes.getApellido());
				pstm.setDate(3, huespedes.getFecha_de_nacimiento());
				pstm.setString(4, huespedes.getNacionalidad());
				pstm.setString(5, huespedes.getTelefono());
				pstm.setInt(6, huespedes.getId_Reserva());
				
				pstm.execute();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while (rst.next()) {
						huespedes.setId(rst.getInt(1));
					}
				}
				
			}
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Huespedes> buscar(){
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre , apellido, fecha_de_nacimiento, nacionalidad,telefono, id_Reserva FROM Huespedes";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
			
				pstm.execute();
				
				transFormarResultSetEnReserva(huespedes,pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huespedes> buscarId(String id){
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id, nombre , apellido, fecha_de_nacimiento,nacionalidad, telefono, id_Reserva FROM Huespedes WHERE id_reserva=?";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
				pstm.setString(1, id);
				pstm.execute();
				
				transFormarResultSetEnReserva(huespedes,pstm);
			}
			return huespedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public void Eliminar(Integer id) {
		try(PreparedStatement stm = conexion.prepareStatement("DELETE FROM Huespedes WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	} 
	
	public void Actualizar(String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad, String telefono, Integer id_Reserva) {
		try (PreparedStatement stm = conexion.prepareStatement("UPDATE Huespedes SET nombre=? , apellido=?, fecha_de_nacimiento=?, nacionalidad telefono=?, id_Reserva=? WHERE  id= ?")) {
			stm.setString(2, nombre);
			stm.setString(3, apellido);
			stm.setDate(4, fecha_de_nacimiento);
			stm.setString(5, nacionalidad);
			stm.setString(6, telefono);
			stm.setInt(7, id_Reserva);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void transFormarResultSetEnReserva(List<Huespedes> huespedes, PreparedStatement pstm) throws SQLException{
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()) {
				Huespedes producto = new Huespedes(rst.getString(2),rst.getString(3),rst.getDate(4), rst.getString(5),rst.getString(6),rst.getInt(7));
				huespedes.add(producto);
			}
		}
	}
	
}