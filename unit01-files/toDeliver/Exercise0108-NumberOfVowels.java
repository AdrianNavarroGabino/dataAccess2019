// Adrián Navarro Gabino

/*
 * Create a program that asks the user for the name of a text file and shows the
 * console (total) number of vowels it contains.
 */

import java.util.*;
import java.io.*;

public class Exercise0108
{
    public static void main(String[] args)
    {
        int vowels = 0;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter filename: ");
        String filename = sc.nextLine();

        if (! (new File(filename)).exists() ) {
            System.out.println(filename + " not found");
            return;
        }

        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File(filename)));
            String line = file.readLine();
            while (line != null) {
                for(int i = 0; i < line.length(); i++)
                {
                    switch(line.toLowerCase().charAt(i))
                    {
                        case 'a':
                        case 'e':
                        case 'i':
                        case 'o':
                        case 'u': vowels++; break;
                    }
                }
                line = file.readLine();
            }
            file.close();

            System.out.println("Vowels: " + vowels);
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
