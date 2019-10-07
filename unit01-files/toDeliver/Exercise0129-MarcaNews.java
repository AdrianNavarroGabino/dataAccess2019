// Adrián Navarro Gabino

/*
 * Create a program that reads ALL Marca news
 */

import java.io.*; 
import java.util.*;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.DocumentBuilder; 
import org.w3c.dom.Document; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element;
import java.net.*;

class News implements Serializable
{
    private String title;
    private String description;
    private String creator;
    private String link;
    private ArrayList<String> categories;
    private String guid;
    private String date;

    public News(String title, String description, String creator, String link,
                ArrayList<String> categories, String guid, String date)
    {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.link = link;
        this.categories = categories;
        this.guid = guid;
        this.date = date;
    }

    public String getGuid() { return guid; }
}

public class Exercise0129
{ 
    public static void main(String[] args)
    { 
        List<News> news = new ArrayList<>();

        String urlToRead = "https://www.marca.com/rss.html";
        URL url; // The URL to read
        HttpURLConnection conn; // The actual connection to the web page
        BufferedReader rd; // Used to read results from the web page
        String line; // An individual line of the web page HTML
        List<String> result = new ArrayList<>(); // A long string containing all the HTML
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result.add(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> categories = result.stream()
                                        .filter(l -> l.startsWith("<li") &&
                                            l.contains("href") &&
                                            l.contains(".xml"))
                                        .collect(Collectors.toList());

        Document doc;
        
        for(int i = 0; i < categories.size(); i++)
        {
            String row = categories.get(i);
            String link = row.substring(
                row.indexOf("href=\"") + 6, row.indexOf(".xml\"") + 4);
            try 
            { 
                url = new URL(link); 
                DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance(); 
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); 
                doc = dBuilder.parse(url.openStream());
                doc.getDocumentElement().normalize();
                
                NodeList nList = doc.getElementsByTagName("item");

                for (int temp = 0; temp < nList.getLength(); temp++) 
                { 
                    Node nNode = nList.item(temp); 
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                    { 
                        Element eElement = (Element) nNode;

                        ArrayList<String> categoriesList = new ArrayList<>();

                        for(int j = 0;
                            j < eElement
                                .getElementsByTagName("category")
                                .getLength();
                            j++)
                        {
                            categoriesList.add(
                                eElement.getElementsByTagName("category")
                                        .item(0).getTextContent()
                            );
                        }

                        boolean isInTheList = news.stream()
                                            .anyMatch(a -> a.getGuid().equals(
                                eElement.getElementsByTagName("guid")
                                .item(0).getTextContent()
                        ));
                        
                        if(!isInTheList)
                            news.add(new News(
                                eElement.getElementsByTagName("title")
                                    .item(0).getTextContent(),
                                eElement.getElementsByTagName("description")
                                    .item(0).getTextContent(),
                                eElement.getElementsByTagName("dc:creator")
                                    .item(0).getTextContent(),
                                eElement.getElementsByTagName("link")
                                    .item(0).getTextContent(),
                                categoriesList,
                                eElement.getElementsByTagName("guid")
                                    .item(0).getTextContent(),
                                eElement.getElementsByTagName("pubDate")
                                    .item(0).getTextContent()));
                    } 
                }
            } 
            catch (Exception e) 
            { 
                e.printStackTrace(); 
            }
        }

        try
        {
            File file = new File("news.dat");
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectFile = new ObjectOutputStream(fileOut);
            objectFile.writeObject(news);
            objectFile.close();
            
            System.out.println("File created");
        }
            catch(IOException e)
        {
            e.printStackTrace();
        }
    } 
}
