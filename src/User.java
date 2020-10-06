import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class User implements Serializable {
String Username,Password,Name,Mail,sendTo;
ArrayList<String> getMessegae = new ArrayList<>(); 
public User(String username ,String password) {
	super();
	
	this.Username = username;
	this.Password = password;
	//al.add(Username);
	
}


public void setUsername(String st) {
	this.Username = st;
	
}
public void setPass(String ps) {
	this.Password = ps;
	
}

public String getUsername() {
	return Username;
}
public String getPassword() {
	return Password;
}
public void getMg(String st){
	getMessegae.add(st);
}
public void setmail(String mail , String name) {
	this.Mail = mail;
	this.sendTo = name;
}

public String getMail() {
	return Mail;
}

//save user and pass to a text file and file.
public void saveUser(String user,String pass) {
	this.Username = user;
	this.Password = pass;
	User user1 = new User(Username , Password);
	StringBuilder str = new StringBuilder(Username);
	 str.append(".dat");
	 String a = str.toString();
	 try {
		FileOutputStream fos = new FileOutputStream(new File(a));
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    
		    oos.writeObject(user1);
		    
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		
	}
	//User user1 = new User(user , pass);
	try {
		File myObj = new File("admin.txt");

		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
			System.out.println("File already exists.");
		}
	} catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	}
	try {
		FileWriter myWriter = new FileWriter("admin.txt",true);
		
		//user1.setUsername(user);
		//user1.setPass(pass);
		myWriter.write(Username+","+Password+"\n");


		myWriter.close();
		System.out.println("Successfully wrote to the file.");
	} catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	}	
	
	
	

}

}
