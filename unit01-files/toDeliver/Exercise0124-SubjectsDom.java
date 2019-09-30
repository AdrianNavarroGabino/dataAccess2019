// Adrián Navarro Gabino

/*
 * Create a program that shows the name of the second course subjects, using DOM
 */

import java.io.File; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.DocumentBuilder; 
import org.w3c.dom.Document; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element; 

public class Exercise0124 
{ 
    public static void main(String[] args)
    { 
        try 
        { 
            File inputFile = new File("subjects.xml"); 
            DocumentBuilderFactory dbFactory =
                DocumentBuilderFactory.newInstance(); 
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
            Document doc = dBuilder.parse(inputFile); 
            doc.getDocumentElement().normalize(); 
            
            NodeList nList = doc.getElementsByTagName("asignatura");

            for (int temp = 0; temp < nList.getLength(); temp++) 
            { 
                Node nNode = nList.item(temp); 
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                { 
                    Element eElement = (Element) nNode;
                    if(eElement.getElementsByTagName("curso")
                               .item(0).getTextContent().equals("Segundo"))
                    System.out.println(eElement.getElementsByTagName("nombre")
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