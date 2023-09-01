package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import CRUD.HuespedesCRUD;
import factory.conexionBD;
import modelo.Huespedes;

public class HuespedesControlador {
	private HuespedesCRUD huespedesCrud;
	
	
	public HuespedesControlador() {
		Connection connection = new conexionBD().recuperarConexion();
		this.huespedesCrud = new HuespedesCRUD(connection);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesCrud.guardar(huespedes);
	}
	
	public  List<Huespedes> buscarHuespedes(){
		return this.huespedesCrud.buscar();
	}
	
	public  List<Huespedes> buscarHuespedesId(String id){
		return this.huespedesCrud.buscarId(id);
	}
	
	public void actualizar(String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad, String telefono,Integer id_Reserva) {
		this.huespedesCrud.Actualizar(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_Reserva);
	}
	 
	public void Eliminar(Integer id_Reserva) {
		this.huespedesCrud.Eliminar(id_Reserva);
	}
	
	
}