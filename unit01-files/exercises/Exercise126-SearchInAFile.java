// Adrián Navarro Gabino

/*
 * 1.2.6: Create a program that asks the user for the name of a file and a word
 * to look for in it. You must display on the screen all the lines of the file
 * that contain that word.
 */

import java.io.*;
import java.util.*;

public class Exercise126
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }

        System.out.print("Enter a word to search: ");
        String search = sc.nextLine().toLowerCase();

        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File(filename)));

            String line = file.readLine();
            boolean isInTheText = false;
            while (line != null) {
                if(line.toLowerCase().contains(search))
                {
                    isInTheText = true;
                    System.out.println(line);
                }

                line = file.readLine();
            }
            
            file.close();
            
            if(!isInTheText)
            {
                System.out.println("The word " + search +
                    " is NOT in the text");
            }
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
