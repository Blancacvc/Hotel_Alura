package CRUD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Reservas;


public class ReservasCRUD {
	private Connection conexion;

	public ReservasCRUD(Connection conexion) {
	this.conexion= conexion;
	}
	
	public void guardar (Reservas res) {
		String sql = "INSERT INTO Reservas (fecha_entrada,fecha_salida,valor,forma_pago)"
				+ "VALUES(?,?,?,?)";
		try (PreparedStatement pstm = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setDate(1, res.getFecha_entrada());
			pstm.setDate(2, res.getFecha_salida());
			pstm.setString(3, res.getValor());
			pstm.setString(4, res.getForma_pago());
			
			pstm.executeUpdate();
			
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while (rst.next()) {
					res.setId(rst.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public List<Reservas> buscar(){
		List<Reservas> reservas = new ArrayList<Reservas>();
		try {
			String sql = "SELECT * FROM Reservas";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
				pstm.execute();
				
				transFormarResultSetEnReservas(reservas,pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reservas> buscarId(String id){
		List<Reservas> reservas = new ArrayList<Reservas>();
		try {
			String sql = "SELECT * FROM Reservas WHERE  id= ?";
			
			try(PreparedStatement pstm = conexion.prepareStatement(sql)){
				pstm.setString(1, id);
				pstm.execute();
				
				transFormarResultSetEnReservas(reservas,pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	public void Eliminar(Integer id) {
		try(PreparedStatement stm = conexion.prepareStatement("DELETE FROM Reservas WHERE id = ?")) {
			stm.setInt(1, id);
			stm.execute();  
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void Actualizar(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago, Integer id) {
		try (PreparedStatement stm = conexion.prepareStatement("UPDATE reservas SET fecha_entrada=?, fecha_salida=?, valor=?,forma_de_pago=? WHERE  id= ?")) {
			stm.setDate(1, fecha_entrada);
			stm.setDate(2, fecha_salida);
			stm.setString(3, valor);
			stm.setString(4, forma_pago);
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void transFormarResultSetEnReservas(List<Reservas> reservas, PreparedStatement pstm) throws SQLException{
		try(ResultSet rst = pstm.getResultSet()){
			while(rst.next()) {
				Reservas producto = new Reservas(rst.getInt(1),rst.getDate(2),rst.getDate(3),rst.getString(4), rst.getString(5));
				reservas.add(producto);
			}
		}
	}
	
}
