// Adrián Navarro Gabino

/*
 * Create a program that shows the model of brand cars entered by the user,
 * followed by their displacement, and sorted down by displacement, for the
 * file "cars.xml", using DOM
 */

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder; 
import org.w3c.dom.Document; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element; 

public class Exercise0127
{ 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter brand: ");
        String brand = sc.nextLine();

        try 
        { 
            File inputFile = new File("cars.xml"); 
            DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance(); 
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            Document doc = dBuilder.parse(inputFile); 
            doc.getDocumentElement().normalize(); 
            
            NodeList nList = doc.getElementsByTagName("coche");

            for (int temp = 0; temp < nList.getLength(); temp++) 
            { 
                Node nNode = nList.item(temp); 
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                { 
                    Element eElement = (Element) nNode;
                    if(eElement.getElementsByTagName("marca")
                               .item(0).getTextContent()
                               .equalsIgnoreCase(brand))
                    System.out.println(
                        eElement.getElementsByTagName("marca")
                               .item(0).getTextContent() + " " +
                        eElement.getElementsByTagName("modelo")
                            .item(0).getTextContent());
                } 
            } 
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        } 
    } 
}