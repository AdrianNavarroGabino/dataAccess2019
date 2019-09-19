// Adrián Navarro Gabino

/*
 * 0.8: Create a variant of the improved exercise 0.7, in which the data list
 * will be a DataList class, with public methods "void include(text)",
 * "bool contains(text)" and "void showOrderedData()".
 */

import java.util.*;

class DataList
{
    private static ArrayList<String> words;
    
    public DataList()
    {
        words = new ArrayList<String>();
    }
    
    public void include(String input)
    {
        if(!input.equals("end"))
            words.add(input);
    }
    
    public boolean contains(String input)
    {
        if(!input.equals("end") && words.contains(input))
            return true;
        return false;
    }
    
    public void showOrderedData()
    {
        Collections.sort(words);
        for(String word: words)
        {
            System.out.println(word);
        }
    }
}

public class Exercise008
{
    public static void main(String[] args)
    {
        DataList dataList = new DataList();
        Scanner sc = new Scanner(System.in);
        String input;
        
        do
        {
            System.out.print("Enter a string: ");
            input = sc.nextLine();
            dataList.include(input);
        }while(!input.equals("end"));
        
        do
        {
            System.out.print("Enter a string to search: ");
            input = sc.nextLine();
        
            boolean appearsInList = dataList.contains(input);
            
            if(!input.equals("end") && appearsInList)
                System.out.println("It's in the list");
            else if(!input.equals("end") && !appearsInList)
                System.out.println("It's NOT in the list");
        }while(!input.equals("end"));
        
        dataList.showOrderedData();
    }
}
