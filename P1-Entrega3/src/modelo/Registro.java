package modelo;

public class Registro {
	
	private String horaInicio;
	private String horaFin;
	private Actividad actividad;
	
	public Registro(String pHoraInicio, String pHoraFin, Actividad pActividad) 
	{
		this.horaInicio=pHoraInicio;
		this.horaFin=pHoraFin;
		this.actividad=pActividad;
	}
	
	public String actualizarRegistro() 
	{
		return "";
	}
	
	public String mostrarRegistro() 
	{
		return actividad+" hecha desde: "+ horaInicio+" hasta "+ horaFin;
	}
}