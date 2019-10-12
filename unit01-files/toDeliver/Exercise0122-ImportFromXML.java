package com.adriannavarrogabino.exercises;

//Adrián Navarro Gabino

/*
* Using text files, add an option (5) that allows the program 1.21 to import
* friend data from an XML file (called "friends.xml").
*/

import java.io.*;
import java.lang.ClassNotFoundException;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

class Person implements Serializable
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -5369427449574946063L;
	private String name;
	private int age;
	private String mail;
	private String comments;
	
	public Person() {
		super();
	}

	public Person(String name, int age, String mail, String comments) {
		super();
		this.name = name;
		this.age = age;
		this.mail = mail;
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" +age + ", mail=" + mail +
				", comments=" + comments + "]";
	}

}

public class Exercise0122
{
	public static void main(String[] args)
	{
		try
		{
          List<Person> people = getPeople();
          int option;
          Scanner sc = new Scanner(System.in);

          do
          {
              System.out.println("1. Add a person.");
              System.out.println("2. See everyone's names.");
              System.out.println("3. Search.");
              System.out.println("4. Export to XML.");
              System.out.println("5. Import from XML");
              System.out.println("0. Exit.");
              System.out.print("Choose an option: ");
              option = sc.nextInt();
              System.out.println();

              switch(option)
              {
                  case 0: break;
                  case 1: addPerson(people); break;
                  case 2: showPeople(people); break;
                  case 3: search(people); break;
                  case 4: exportToXML(people); break;
                  case 5: people = importFromXML(); break;
                  default:
                      System.out.println("Wrong option");
                      System.out.println();
                      break;
              }
          }while(option != 0);
          
          System.out.println("See you!");
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

	static List<Person> getPeople() throws IOException, ClassNotFoundException
	{
		List<Person> people = new ArrayList<>();

		if (! (new File("people.dat")).exists() ) {
			return people;
		}	


		File file = new File("people.dat");
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream objectFile = new ObjectInputStream(fileIn);

		people = (ArrayList<Person>)objectFile.readObject();
      
		objectFile.close();

		return people;
	}
  
	static void addPerson(List<Person> people) throws IOException
	{
		Scanner sc = new Scanner(System.in);
      
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Age: ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("Mail: ");
		String mail = sc.nextLine();
		System.out.print("Comments: ");
		String comments = sc.nextLine();

		Person p = new Person(name, age, mail, comments);
		people.add(p);

		File file = new File("people.dat");
		FileOutputStream fileOut = new FileOutputStream(file);
		ObjectOutputStream objectFile = new ObjectOutputStream(fileOut);
		objectFile.writeObject(people);
		objectFile.close();

		System.out.println("Person created succesfully");
		System.out.println();
	}

	static void showPeople(List<Person> people)
	{
		for (Person p : people) {
			System.out.println(p);
		}

		System.out.println();
	}

	static void search(List<Person> people)
	{
		Scanner sc = new Scanner(System.in);
		boolean found = false;

		System.out.print("Person to search: ");
		String search = sc.nextLine();
		
		for (Person p : people) {
			if(p.getName().equalsIgnoreCase(search) ||
					p.getMail().equalsIgnoreCase(search) ||
					p.getComments().equalsIgnoreCase(search))
			{
				System.out.println(p);
				found = true;
			}
		}
      
		if(!found)
		{
			System.out.println("Person not found");
		}

		System.out.println();
	}
  
	static void exportToXML(List<Person> people)
	{
		String xmlFilePath = "friends.xml";
  	
		try {
  		 
			DocumentBuilderFactory documentFactory =
					DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder =
					documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();
			
			Element root = document.createElement("people");
			document.appendChild(root);

			for(Person p: people)
			{
				System.out.println(p.getName());
	            Element person = document.createElement("person");
	            root.appendChild(person);
	            
	            Element name = document.createElement("name");
	            name.appendChild(document.createTextNode(p.getName()));
	            person.appendChild(name);
	            
	            Element age = document.createElement("age");
	            age.appendChild(document.createTextNode(p.getAge() + ""));
	            person.appendChild(age);
	            
	            Element mail = document.createElement("mail");
	            mail.appendChild(document.createTextNode(p.getMail()));
	            person.appendChild(mail);
	            
	            Element comments = document.createElement("comments");
	            comments.appendChild(document.createTextNode(p.getComments()));
	            person.appendChild(comments);
			}
          
			TransformerFactory transformerFactory =
					TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			transformer.transform(domSource, streamResult);

			System.out.println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	private static List<Person> importFromXML()
	{
		List<Person> friends = new ArrayList<>();
		
		try
		{
			File inputFile = new File("friends.xml");
			DocumentBuilderFactory dbFactory
			= DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("friend");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					friends.add(new Person(
							eElement.getElementsByTagName("name")
								.item(0).getTextContent(),
							Integer.parseInt(eElement
								.getElementsByTagName("age")
								.item(0).getTextContent()),
							eElement.getElementsByTagName("mail")
								.item(0).getTextContent(),
							eElement.getElementsByTagName("comments")
								.item(0).getTextContent()));
				}
			}
			
			System.out.println("Friends have been imported");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return friends;
	}
}
