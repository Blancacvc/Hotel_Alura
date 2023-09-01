package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import CRUD.ReservasCRUD;
import factory.conexionBD;
import modelo.Reservas;


public class ReservaControlador {
	private ReservasCRUD reservas;
	
	public ReservaControlador() {
		Connection con = new conexionBD().recuperarConexion();
		this.reservas = new ReservasCRUD(con);	
	}
	
	public void guardar (Reservas res) {
		this.reservas.guardar(res);
	}
	
	public List<Reservas> buscar(){
		return this.reservas.buscar();
	}
	public List<Reservas> buscarId(String id){
		return this.reservas.buscarId(id);
	}
		
	public void actualizar(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago, Integer id) {
		this.reservas.Actualizar(fecha_entrada, fecha_salida, valor,forma_pago, id);
	}
	
	public void Eliminar(Integer id) {
		this.reservas.Eliminar(id);
	}
}
