package com.simplilearn.lockme.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import java.util.Scanner;

import com.simplilearn.lockme(phase1).model.UserCredentials;
import com.simplilearn.lockme(phase1).model.Users;

public class Authentication {
	//input data
	private static Scanner keyboard;
	private static Scanner input;
	private static Scanner lockerInput;
	//output data 
	private static PrintWriter output;
	private static PrintWriter lockerOutput;
	//model to store data.
	private static Users users;
	private static UserCredentials userCredentials;
	
	
	public static void main(String[] args) {
		initApp();
		welcomeScreen();
		signInOptions();

	}
	public static void signInOptions() {
		System.out.println("1. Registration ");
		System.out.println("2. Login ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
				registerUser();
				break;
			case 2 :
				loginUser();
				break;
			default :
				System.out.println("Please select 1 or 2");
				break;
		}
		keyboard.close();
		input.close();
	}
	
	public static void lockerOptions(String inpUsername) {
		System.out.println("1. FETCH ALL STORED CREDENTIALS ");
		System.out.println("2. STORED CREDENTIALS ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
				fetchCredentials(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			default :
				System.out.println("Please select 1 or 2");
				break;
		}
		lockerInput.close();
	}
	
	public static void registerUser() {
		System.out.println("----------------------------------------");
		System.out.println("WELCOME TO REGISTRATION PAGE");
		System.out.println("----------------------------------------");
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		users.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		users.setPassword(password);
		
		output.println(users.getUsername());
		output.println(users.getPassword());
		
		System.out.println("User Registration Successfull!");
		output.close();
		
	}
	public static void loginUser() {
		System.out.println("------------------------------------------");
	System.out.println("WELCOME TO LOGIN PAGE");
		System.out.println("------------------------------------------");
		System.out.println("Enter Username :");
		String inpUsername = keyboard.next();
		boolean found = false;
		while(input.hasNext() && !found) {
			if(input.next().equals(inpUsername)) {
				System.out.println("Enter Password :");
				String inpPassword = keyboard.next();
				if(input.next().equals(inpPassword)) {
					System.out.println("Login Successfull!");
					found = true;
					lockerOptions(inpUsername);
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found : Login Failure : 404");
		}
		
	}
	
	public static void welcomeScreen() {
		System.out.println("--------------------------------------");
		System.out.println("Welcome To LockMe.com");
		System.out.println("Your Personal Digital Locker");
	    System.out.println("------------------------------------------");
		
	}
	//store credentails
	public static void storeCredentials(String loggedInUser) {
		System.out.println("-----------------------------------------------------");
		System.out.println("WELCOME TO DIGITAL LOCKER STORE YOUR CREDENTIALS HERE");
		System.out.println("-----------------------------------------------------");
		
		userCredentials.setLoggedInUser(loggedInUser);
		
		System.out.println("Enter Site Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		userCredentials.setUsername(username);
		
		System.out.println("Enter Password :");
		String password = keyboard.next();
		userCredentials.setPassword(password);
		
		lockerOutput.println(userCredentials.getLoggedInUser());
		lockerOutput.println(userCredentials.getSiteName());
		lockerOutput.println(userCredentials.getUsername());
		lockerOutput.println(userCredentials.getPassword());
		
		List<String> List = new ArrayList<String>();
		Map<String, String> Map = new HashMap<>();
		
		List.add(loggedInUser);
		List.add(siteName);
		List.add(userID);
		List.add(password);
		
		Map.put("User Name is:", loggedInUser);
		Map.put("The Website Name is:", siteName);					
		Map.put("User ID is:", userID);
		Map.put("Password is:", password);
		
		System.out.println(Map);
		filename = filename.concat(".");
					filename = filename.concat(siteName);
					FileOutputStream file = null;
					try {
						 file = new FileOutputStream(filename);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					ObjectOutputStream out = null;
					try {
						out = new ObjectOutputStream(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					try {
						out.writeObject(list);
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						file.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		System.out.println("YOUR CREDENTIALS ARE STORED SAFE AND SECURED!1");
		lockerOutput.close();		
	}
	
	//fetch credentials
	public static void fetchCredentials(String inpUsername) {
		System.out.println("-----------------------------------------");
		System.out.println("WELCOME TO DIGITAL LOCKER");
		System.out.println("YOUR CREDENTIALS ARE");
	        System.out.println("-----------------------------------------");
		System.out.println(inpUsername);
		
		
		while(lockerInput.hasNext()) {
//			System.out.println(lockerInput.hasNext());
			if(lockerInput.next().equals(inpUsername)) {
				System.out.println("Site Name: "+lockerInput.next());
				System.out.println("User Name: "+lockerInput.next());
				System.out.println("User Password: "+lockerInput.next());
			}
		}
		
	}
	
	public static void deleteAllCredentials(String inpUsername) {
		File myObj = new File(filename); 
				    if (myObj.delete()) { 
				      System.out.println("Deleted the file: " + myObj.getName());
				    } 
				    else {
				      System.out.println("Failed to delete the file");
				    } 
		   } 
	
	public static void initApp() {

		File  dbFile = new File("database.txt");
		File  lockerFile = new File("locker-file.txt");
		
		try {
			//read data from db file
			input = new Scanner(dbFile);
			
			//red data from locker file
			lockerInput = new Scanner(lockerFile);
			
			//read data from keyboard
			keyboard = new Scanner(System.in);
			
			//out put 
			output = new PrintWriter( new FileWriter(dbFile,true));
			lockerOutput = new PrintWriter( new FileWriter(lockerFile,true));
			
			users = new Users();
			userCredentials  = new UserCredentials();
			
			
		} catch (IOException e) {
			System.out.println("404 : File Not Found ");
		}
		
	}

}
