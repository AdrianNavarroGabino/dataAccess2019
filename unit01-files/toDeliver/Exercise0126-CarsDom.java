// Adrián Navarro Gabino

/*
 * Create a program that shows the model of the Seat brand cars, for the
 * "cars.xml" file, using DOM
 */

import java.io.File; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.DocumentBuilder; 
import org.w3c.dom.Document; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element; 

public class Exercise0126
{ 
    public static void main(String[] args)
    { 
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
                               .equalsIgnoreCase("seat"))
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