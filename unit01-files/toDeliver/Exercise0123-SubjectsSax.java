// Adrián Navarro Gabino

/*
 * Create a program that shows the name of the second course subjects, using SAX
 */

import javax.xml.parsers.SAXParser; 
import javax.xml.parsers.SAXParserFactory; 
import org.xml.sax.Attributes; 
import org.xml.sax.SAXException; 
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

class ReadXml extends DefaultHandler
{

    public void processXml()
    { 
        try 
        { 
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
            SAXParser saxParser = saxParserFactory.newSAXParser(); 
            DefaultHandler eventHander = new DefaultHandler()
            { 
                String currentTag = "";
                String currentSubject = "";
                String content = ""; 
                
                public void startElement(String uri, String localName, 
                    String qName, Attributes attributes) throws SAXException 
                {
                    currentTag = qName;
                }

                // Obtiene los datos entre '<' y '>'
                
                public void characters(char ch[], int start, int length)
                    throws SAXException 
                {
                    content = new String(ch, start, length);
                }
                
                // Llamado al encontrar un fin de etiqueta: '>' 
                public void endElement(String uri, String localName, 
                    String qName) throws SAXException 
                {
                    if (!currentTag.equals(""))
                    {
                        if(currentTag.equals("nombre"))
                        {
                            currentSubject = content;
                        }
                        else if(currentTag.equals("curso"))
                        {
                            if(content.equalsIgnoreCase("segundo"))
                            {
                                System.out.println(currentSubject);
                            }
                        }
                        currentTag = "";
                    }
                } 
            }; 
            
            // Cuerpo de la función: trata de analizar el fichero deseado 
            // Llamará a startElement(), endElement() y character() 
            saxParser.parse("subjects.xml", eventHander);
        }
        catch (Exception e)
        {
            e.printStackTrace(); 
        } 
    } 
} 

public class Exercise0123 
{ 
    public static void main(String args[]) 
    { 
        ReadXml xmlFile = new ReadXml(); 
        xmlFile.processXml();
    } 
}