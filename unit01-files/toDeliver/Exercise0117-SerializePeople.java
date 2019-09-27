// Adrián Navarro Gabino

/*
 * Create a program that serializes personal data (name, e-mail, date of birth)
 */

import java.io.*;

class Person implements Serializable
{
	private String name;
	private String mail;
	private String date;
	
	public Person()
	{
		
	}
	
	public Person(String name, String mail, String date)
	{
		this.name = name;
		this.mail = mail;
		this.date = date;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getMail() 
	{
		return mail;
	}

	public void setMail(String mail) 
	{
		this.mail = mail;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate(String date) 
	{
		this.date = date;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", mail=" + mail + ", date=" +
            date + "]";
	}
}

public class Exercise0117
{
	public static void main(String[] args)
	{
		try
		{
			File file = new File("people.dat");
		    FileOutputStream fileOut = new FileOutputStream(file);
		    ObjectOutputStream objectFile = new ObjectOutputStream(fileOut);
		    Person p = new Person("Adrian", "a@a.com", "01/01/2019");
		    objectFile.writeObject(p);
		    p = new Person("Dani", "b@b.com", "02/01/2019");
		    objectFile.writeObject(p);
		    objectFile.close();
            
            System.out.println("File created");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
