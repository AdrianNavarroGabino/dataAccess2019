// Adrián Navarro Gabino

/*
 * 0.7: Create a variant of exercise 0.6 in which the data will not be
 * numerical, but text strings, so that each phase will end when the word "end"
 * is entered.
 */

import java.util.*;

public class Exercise007
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> words = new ArrayList<String>();
        
        String input;
        
        do
        {
            System.out.print("Enter a string: ");
            input = sc.nextLine();
            
            if(!input.equals("end"))
                words.add(input);
        }while(!input.equals("end"));
        
        do
        {
            System.out.print("Enter a string to search: ");
            input = sc.nextLine();
            
            if(!input.equals("end") && words.contains(input))
                System.out.println("It's in the list");
            else if(!input.equals("end") && !words.contains(input))
                System.out.println("It's NOT in the list");
        }while(!input.equals("end"));
        
        Collections.sort(words);
        for(String word: words)
        {
            System.out.println(word);
        }
    }
}
