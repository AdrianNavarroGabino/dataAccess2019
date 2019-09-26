//Adrián Navarro Gabino

/*
 * 1.3.5: Create a program that says if a file (binary) contains a certain
 * word that the user enters, searching byte by byte.
 */

import java.io.*;
import java.util.*;

class Exercise135
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }

        System.out.print("Word to search: ");
        String wordToSearch = sc.nextLine();
        String result = "";
        boolean isInTheFile = false;

        try
        {
            FileInputStream file = 
                new FileInputStream(new File(filename));

            char data = (char)file.read();

            while(data != -1)
            {
                result += data;

                if(wordToSearch.equals(result))
                {
                    isInTheFile = true;
                    break;
                }

                if(wordToSearch.length() == result.length())
                {
                    result = result.substring(1);
                }

                data = (char)file.read();
            }
            
            file.close();

            if(isInTheFile)
            {
                System.out.println("The word " + wordToSearch +
                    " is in the file");
            }
            else
            {
                System.out.println("The word " + wordToSearch +
                " is NOT in the file");
            }
        }
        catch (Exception e)
        {
            System.out.println( "There were some problems: " + e.getMessage() );
        }
    }
}
