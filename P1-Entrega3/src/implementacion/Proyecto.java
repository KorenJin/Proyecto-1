package implementacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import modelo.*;

public class Proyecto {

	private String name;
	private String dateI;
	private String dateF;
	private Participante creator;
	private ArrayList<Actividad> activities;
	static HashMap<String,Participante> participants = new HashMap<String,Participante>();
	private int ID;
	//public final static SimpleDateFormat formato=new SimpleDateFormat("dd/HH/mm" );
	//Constructor
	public Proyecto(String name, String dateI, String dateF, Participante creator, int ID) {
		this.name = name;
		this.dateI = dateI;
		this.dateF = dateF;
		this.creator = creator;
		this.ID = ID;
		this.activities = new ArrayList<Actividad>();
		this.participants = new HashMap<String,Participante>();
		addParticipant(creator);
	}
	
	public String getNombre(){return this.name;}
	public void setNombre(String name){this.name = name;}
	
	public void addParticipant(Participante participant) {
		participants.put(participant.getCorreo(),participant);
	}
	
	public void addActivity(Actividad activity) {
		activities.add(activity);
	}
	
	public int getSize(){
		return activities.size();
	}
	
	public int getID() {
		return ID;
	}
	
	public boolean containsParticipant(Participante participant) {
		return participants.containsKey(participant.getCorreo());
	}
}