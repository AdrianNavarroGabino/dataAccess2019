// Adrián Navarro Gabino

/*
 * Create a program that deserializes data from people taken from the file
 * "people.dat"
 */

import java.io.*;
import java.lang.ClassNotFoundException;

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

public class Exercise0118
{
	public static void main(String[] args)
	{
		try
		{
			File file = new File("people.dat");
		    FileInputStream fileIn = new FileInputStream(file);
		    ObjectInputStream objectFile = new ObjectInputStream(fileIn);
		    Person p = (Person)objectFile.readObject();
            System.out.println(p);
            p = (Person)objectFile.readObject();
		    System.out.println(p);
		    objectFile.close();
		}
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
