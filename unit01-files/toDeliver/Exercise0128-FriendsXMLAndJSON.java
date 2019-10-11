// Adrián Navarro Gabino

package com.adriannavarrogabino.exercises;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.DocumentBuilder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element; 

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

class Friend
{
	private String name;
	private String age;
	private String phone;
	
	public Friend(String name, String age, String phone)
	{
		super();
		this.name = name;
		this.age = age;
		this.phone = phone;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	@Override
	public String toString()
	{
		return "Friend [name=" + name + ", age=" + age +
            ", phone=" + phone + "]";
	}

	
}

class Equal
{
	private boolean iguales;
	private int total;
	public Equal(boolean equal, int total)
	{
		super();
		this.iguales = equal;
		this.total = total;
	}
	
}

class Different
{
	private boolean iguales;
	private int correctos;
	private int incorrectos;
	public Different(boolean equal, int right, int wrong)
	{
		super();
		this.iguales = equal;
		this.correctos = right;
		this.incorrectos = wrong;
	}
	
	
}

public class Exercise0128
{
	private static List<Friend> readXML()
	{
		List<Friend> friendsXML = new ArrayList<>();

		try 
        { 
            File inputFile = new File("amigos.xml"); 
            DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance(); 
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            Document doc = dBuilder.parse(inputFile); 
            doc.getDocumentElement().normalize(); 
            
            NodeList nList = doc.getElementsByTagName("Amigo");

            for (int temp = 0; temp < nList.getLength(); temp++) 
            { 
                Node nNode = nList.item(temp); 
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                { 
                    Element eElement = (Element) nNode;
                    friendsXML.add(
                    		new Friend(
                                    eElement.getElementsByTagName("Nombre")
                                        .item(0).getTextContent(),
                    				eElement.getElementsByTagName("Edad")
                                        .item(0).getTextContent(),
                    				eElement.getElementsByTagName("Telefono")
                                        .item(0).getTextContent()));
                } 
            } 
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        }
		return friendsXML;
	}
	
	private static List<Friend> readJSON()
	{
		List<Friend> friendsJSON = new ArrayList<>();
		
		Gson gs = new Gson();
		
		try(FileReader reader = new FileReader("amigos.json")) {
			HashMap<String, LinkedTreeMap> gson =
                gs.fromJson(reader, HashMap.class);
			JsonObject jo =null;
			for (Entry<String, LinkedTreeMap> entry : gson.entrySet()) {
			      jo = gs.toJsonTree(entry.getValue()).getAsJsonObject();
			}
			JsonElement je = jo.get("Amigo");
			com.google.gson.JsonArray ja = je.getAsJsonArray();
			for(JsonElement j : ja) {
				JsonObject aux = (JsonObject) j;
				friendsJSON.add(
						new Friend(
								aux.get("Nombre").getAsString(),
								aux.get("Edad").getAsString(),
								aux.get("Telefono").getAsString()
								)
						);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return friendsJSON;
	}
	
	private static boolean areListsEqual(List<Friend> friendsXML,
                                        List<Friend> friendsJSON)
	{
		if(friendsXML.size() != friendsJSON.size())
		{
			return false;
		}
		
		for(Friend f: friendsXML)
		{
			if(!friendsJSON.contains(f))
			{
				return false;
			}
		}
		
		return true;
	}
	
	private static void generateEqual(List<Friend> friendsXML)
	{
		int total = friendsXML.size();
		
		Equal e = new Equal(true, total);
		
		String json = new Gson().toJson(e);
		System.out.println(json);
	}
	
	private static int countEquals(List<Friend> friendsXML,
                                    List<Friend> friendsJSON)
	{
		int count = 0;
		
		for(Friend f1: friendsXML)
		{
			for(Friend f2: friendsJSON)
			{
				if(f1.getName().equals(f2.getName()) &&
						f1.getAge().equals(f2.getAge()) &&
						f1.getPhone().equals(f2.getPhone()))
				{
					count++;
				}
			}
		}
		
		return count;
	}
	
	private static int countDifferent(List<Friend> friendsXML,
                                        List<Friend> friendsJSON)
	{
		return friendsXML.size() + friendsJSON.size() -
				countEquals(friendsXML, friendsJSON) * 2;
	}
	
	private static void generateDifferent(int rightFriends, int wrongFriends)
	{
		Different d = new Different(false, rightFriends, wrongFriends);
		String json = new Gson().toJson(d);
		System.out.println(json);
	}
	
	public static void main(String[] args)
	{
		List<Friend> friendsXML = readXML();
		List<Friend> friendsJSON = readJSON();
		
		if(areListsEqual(friendsXML, friendsJSON))
		{
			generateEqual(friendsXML);
		}
		else
		{
			int rightFriends = countEquals(friendsXML, friendsJSON);
			int wrongFriends = countDifferent(friendsXML, friendsJSON);
			generateDifferent(rightFriends, wrongFriends);
		}
	}
}
