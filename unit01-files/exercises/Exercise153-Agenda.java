// Adrián Navarro Gabino

/*
 * 1.5.3: Expanding the skeleton of the two previous exercises, creates an
 * "agenda", which allows the user to enter data of people and search through
 * existing data. Each time a new data is added, all of them will be saved in
 * file. When the program is re-launched, existing data will be loaded from the
 * file.
 */

import java.io.*;
import java.lang.ClassNotFoundException;
import java.util.*;

class Person implements Serializable
{
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

public class Exercise153
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
}
