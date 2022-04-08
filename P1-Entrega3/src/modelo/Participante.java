package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import implementacion.Proyecto;

public class Participante{
		//Atributos
		private String nombre;
		private String correo;
		private String reportFileName;
		private ArrayList<Actividad> activities;
		private int ID;
		private String reportText = "";
		
		private long totalTime = 0;
		private long avgTime;
		private HashMap<String,Long> dayStats;
		//Constructor
		public Participante(String pNombre,String pCorreo, int ID) {
			this.nombre=pNombre;
			this.correo=pCorreo;
			this.ID = ID;
			this.reportFileName = correo + "_" + nombre + "_" + ID;
			this.activities = new ArrayList<Actividad>();
			this.dayStats = new HashMap<String,Long>();
		}
		
		public String getNombre() {
			return nombre;
		}
		public String getCorreo() {
			return correo;
		}
		
		public Actividad findActivity(int ID) {
			for (Actividad i: activities) {
				if (i.getID() == ID) {
					return i;
				}
			}
			return null;
		}
		
		public String createReport() {
			String dateI;
			String dateF;
			int hourI;
			int hourF;
			long duration;
			int restantI;
			long valueI;
			int restantF;
			long valueF;
			
			for (Actividad i : activities) {
				
				
				
				totalTime += i.getTime();
				dateI = dateToString(i.getDateI());
				dateF = dateToString(i.getDateF());
				hourI = i.getHourI();
				hourF = i.getHourF();
				duration = i.getTime()*60;
				restantI = 24-hourI;
				if (duration >= restantI) {
					valueI = restantI;
				} else {valueI = duration;}
				restantF = 24-hourF;
				if (duration >= restantF) {
					valueF = restantF;
				} else {valueF = duration;}
				
				if (dayStats.containsKey(dateF)) {
					dayStats.put(dateF,dayStats.get(dateF)+valueF);
				} else {
					dayStats.put(dateF,valueF);
				}
				
				if (dayStats.containsKey(dateI)) {
					dayStats.put(dateI,dayStats.get(dateI)+valueI);
				} else {
					dayStats.put(dateI,valueI);
				}
			}
			
			avgTime = totalTime/activities.size();
			
			reportText += "NAME: " + nombre + "\nEMAIL: " + correo + "\n# OF ACTIVITIES DONE: " + activities.size();
			reportText += "\nTOTAL TIME: " + totalTime + "\nAVERAGE TIME FOR ACTIVITY: " + avgTime + "\nACTIVITIES:\n";
			
			for (Actividad i: activities) {
				reportText += i.getTitle() + "\n" + i.getType() + "\n" + i.getID() + "\n" + i.getProjectID();
			}
			
			reportText += "\nTIME SPENT EACH DAY:\n";
			
			for (String i : dayStats.keySet()) {
				reportText += i + "   " + dayStats.get(i) + "\n";
			}
			
			return reportText;
		}
		
		public String dateToString(Date date) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			String strDate = dateFormat.format(date);
			return strDate;
		}
		
		public void addActivity(Actividad activity) {
			activities.add(activity);
		}
}
