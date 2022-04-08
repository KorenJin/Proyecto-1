package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import implementacion.Proyecto;

public class Actividad {
	
	long startTime;
	long endTime;
	long startTimePause;
	long endTimePause;
	long durationPause;
	long duration = 0;
	private String title;
	private java.util.Date dateI;
	private java.util.Date dateF;
	private Participante responsible;
	private String description;
	private String type;
	private int ID;
	private ArrayList<Registro> registers;
	private int projectID;
	//Constructor
	public Actividad(String title, Participante responsible, String description,String type,int ID, int projectID) {
		this.title = title;
		this.dateI = new java.util.Date();
		this.dateF = new java.util.Date();
		this.responsible = responsible;
		this.description = description;
		this.type = type;
		this.ID = ID;
		this.projectID = projectID;
		this.registers=new ArrayList<Registro>();
	}
	
	public void changeDateI(String dateString) {
		this.dateI = parseDate(dateString);
		System.out.println(this.dateI);
	}

	public void changeDateF(String dateString) {
		this.dateF = parseDate(dateString);	
		System.out.println(this.dateF);
	}
	
	 public static Date parseDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy.MM.dd 'at' h:mm").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
	 
	 public void startTimer() {
		 startTime = System.nanoTime();
	 }
	 
	 public void pauseTimer() {
		 startTimePause = System.nanoTime();
	 }
	 
	 public void resumeTimer() {
		endTimePause = System.nanoTime();
		durationPause = (endTimePause - startTimePause);
		duration -= durationPause;
	 }
	 
	 public void stopTimer() {
		 endTime = System.nanoTime();
		 duration += (endTime - startTime);
	 }
	 
	 public long getTime() {
		 return duration;
	 }
	 
	 public String getTitle() {
		 return title;
	 }
	 
	 public String getType() {
		 return type;
	 }
	 
	 public int getID() {
		 return ID;
	 }
	 
	 public int getProjectID() {
		 return projectID;
	 }
	 
	 public Date getDateI() {
		 return dateI;
	 }
	 public Date getDateF() {
		 return dateF;
	 }
	 
	 public int getHourI() {
		 return dateI.getHours();
	 }
	 
	 public int getHourF() {
		 return dateF.getHours();
	 }
}