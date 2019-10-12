// Adrián Navarro Gabino

/*
 * Create a program that shows the model of the Seat brand cars, for the file
 * "cars.xml", using SAX
 */

import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class ReadXML extends DefaultHandler
{
	public static void processXml(String inputBrand)
    { 
		try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler eventHandler = new DefaultHandler(){

	            String currentTag = "";
	            String content = "";
	            String brand = "";
	            String model = "";

	            public void startElement(String uri, String localName,
	                    String qName, Attributes attributes) 
	                    throws SAXException {
	                
	            	currentTag = qName;
	            }

	            public void characters(char ch[], int start, int length)
	                    throws SAXException {
	
	            	content = new String(ch, start, length);
	            }

	            public void endElement(String uri, String localName, String qName)
	                    throws SAXException {
	                
	                if(!currentTag.equals("")) {
	                    if(currentTag.equals("marca"))
	                    	brand = content;
						if(currentTag.equals("modelo"))
						{
							model = content;
						}
	                        
	                    if(!brand.equals("") && !model.equals("")) {
							if(brand.equalsIgnoreCase(inputBrand))
							{
								System.out.println(brand + " " + model);
								model = "";
								brand = "";
							}
	                    }
	                    
	                    currentTag = "";
					}
	            }
	        };
	        
	        saxParser.parse("cars.xml", eventHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Exercise0125
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter brand: ");
		String brand = sc.nextLine();
		ReadXML.processXml(brand);
	}
}
