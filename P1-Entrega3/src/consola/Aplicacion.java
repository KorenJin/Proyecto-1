package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import implementacion.Proyecto;
import modelo.*;

public class Aplicacion {
	static ArrayList<Proyecto> projects = new ArrayList<Proyecto>();
	static HashMap<String,Participante> participants = new HashMap<String,Participante>();
	static Participante currentUser;
	static boolean hasAccess = false;
	static int option;
	
	public static void start() {
		while (true) {
			// MENU
			System.out.println("Welcome!");
			System.out.println("1. Sign Up/ Sign In");
			System.out.println("2. Create Project");
			System.out.println("3. Add participant to project");
			System.out.println("4. Add finished activity to a project");
			System.out.println("5. Get report of stats of a participant");
			System.out.println("6. Start activity");
			System.out.println("7. Pause activity");
			System.out.println("8. Resume activity");
			System.out.println("9. Stop activity");
			System.out.println("0. Exit");
			// INPUT
			try {
				option = Integer.parseInt(input());
			} catch(NumberFormatException e) {continue;}
			// EXECUTE
			if (option == 0) {System.out.println("Thanks for using!");System.exit(0);}
			if (option == 1) {execute1();}
			if (option == 2) {execute2();}
			if (option == 3) {execute3();}
			if (option == 4) {execute4();}
			if (option == 5) {execute5();}
			if (option == 6) {execute6();}
			if (option == 7) {execute7();}
			if (option == 8) {execute8();}
			if (option == 9) {execute9();}
		}
	}
	
	private static Participante findParticipant(String email) {
		return participants.get(email);
	}
	
	private static Proyecto findProject() {
		System.out.println("What's the project ID?");
		int ID = Integer.parseInt(input());
		return projects.get(ID);
	}
	
	private static Participante signUp() {
		System.out.println("What's your/his/her ugly name?");
		String name = input();
		System.out.println("What's your/his/her ugly email?");
		String email = input();
		int ID = participants.size();
		Participante participant = new Participante(name,email,ID);
		participants.put(email, participant);
		System.out.println("Signed up successfully! Remember your/his/her email so that you can access our system.");
		return participant;
	}
	
	private static Participante signIn() {
		System.out.println("What's your/his/her email?");
		String email = input();
		System.out.println("Signed in successfully! Remember your/his/her email so that you can access our system.");
		return findParticipant(email);
	}
	
	private static Participante startAccess() {
		System.out.println("Are you/she/him Registered already? [y/n]");
		String chosen = input();
		if (chosen.contains("y")) {
			Participante user = signIn();
			currentUser = user;
			hasAccess = true;
			return user;
		}
		else{
			Participante user = signUp();
			currentUser = user;
			hasAccess = true;
			return user;
		}
	}
	
	private static void execute1() {
		startAccess();
	}
	
	private static void execute2() {
		if (hasAccess) {
			System.out.println("Project name?");
			String name = input();
			System.out.println("Initial date?");
			String dateI = input();
			System.out.println("Final date?");
			String dateF = input();
			int ID = projects.size();
			Proyecto project = new Proyecto(name,dateI,dateF,currentUser,ID);
			projects.add(project);
			System.out.println("Done! Your project ID is: "+ID);
		} else {System.out.println("You should get access before creating a project!");execute1();execute2();}
	}

	private static void execute3() {
		Participante participant;
		Proyecto project = findProject();
		System.out.println("Does the participant that you want to add exists in our system already? [y/n]");
		String ans = input();
		if (ans.contains("y")) {
			participant = signIn();
		} else {
			participant = signUp();
		}
		project.addParticipant(participant);
		System.out.println("The participant '" + participant.getNombre() + "' has been added successfully!");
	}
	
	private static void execute4() {
		if (hasAccess) {
			Participante responsible;
			Proyecto project = findProject();
			System.out.println("Title of activity?");
			String title = input();
			System.out.println("Description of activity?");
			String description = input();
			System.out.println("Type of activity?");
			String type = input();
			System.out.println("Is the person that did this activity signed up already? [y/n]");
			String ans = input();
			if (ans.contains("y")) {
				System.out.println("What's the email of this person?");
				String email = input();
				responsible = findParticipant(email);
			} else {
				responsible = signUp();
			}
			int ID = project.getSize();
			Actividad activity = new Actividad(title,responsible,description,type,ID,project.getID());
			System.out.println("Would you like to change the dates of this activity? (current date for default value) [y/n]");
			ans = input();
			if (ans.contains("y")) {
				System.out.println("Format of date: yyyy.MM.dd 'at' h:mm. ex: (1) 2004.01.20 at 2:00, (2) 2014.11.01 at 16:35");
				System.out.println("What's the initial date?");
				String dateI = input();
				System.out.println("What's the final date?");
				String dateF = input();
				activity.changeDateF(dateF);
				activity.changeDateI(dateI);
			}
			if (!project.containsParticipant(responsible)) {
				project.addParticipant(responsible);
			}
			project.addActivity(activity);
			responsible.addActivity(activity);
			System.out.println("Activity '" + title + "' with ID " + ID + " has been successfully added to '" + project.getNombre() + "'.");
		} else {System.out.println("You should get access before creating an activity!");execute1();execute4();}
	}
	
	public static void execute5() {
		Participante participant = startAccess();
		System.out.println(participant.createReport());
	}
	
	public static void execute6() {
		if (hasAccess) {
			System.out.println("ID of the activity to modify?");
			int activityID = Integer.parseInt(input());
			Actividad activity = currentUser.findActivity(activityID);
			activity.startTimer();
		} else {System.out.println("You should get access before accessing an activity!");execute1();execute6();}
	}
	
	public static void execute7() {
		if (hasAccess) {
			System.out.println("ID of the activity to modify?");
			int activityID = Integer.parseInt(input());
			Actividad activity = currentUser.findActivity(activityID);
			activity.pauseTimer();
		} else {System.out.println("You should get access before accessing an activity!");execute1();execute7();}
	}

	public static void execute8() {
		if (hasAccess) {
			System.out.println("ID of the activity to modify?");
			int activityID = Integer.parseInt(input());
			Actividad activity = currentUser.findActivity(activityID);
			activity.resumeTimer();
		} else {System.out.println("You should get access before accessing an activity!");execute1();execute8();}
	}

	public static void execute9() {
		if (hasAccess) {
			System.out.println("ID of the activity to modify?");
			int activityID = Integer.parseInt(input());
			Actividad activity = currentUser.findActivity(activityID);
			activity.stopTimer();
		} else {System.out.println("You should get access before accessing an activity!");execute1();execute9();}
	}

	
	public static void main(String[] args) {
		start();
	}
	
	public static String input()
	{
		try
		{
			System.out.print(">> ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}