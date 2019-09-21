// Adrián Navarro Gabino

/*
 * 1.2.7: Create a program that reads the contents of the "rectangle.txt"
 * file that you created in exercise 1.1.5 and that shows on screen what
 * is the width and height of the asterisk triangle.
 */

import java.io.*;
import java.util.*;

public class Exercise127
{
    public static void main(String[] args)
    {
        if (! (new File("rectangle.txt")).exists() ) {
            System.out.println("rectangle.txt not found");
            return;
        }

        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File("rectangle.txt")));

            String line = file.readLine();
            int width = line.length();
            int height = 0;
            while (line != null) {
                height++;

                line = file.readLine();
            }
            
            file.close();
            
            System.out.println("Width: " + width);
            System.out.println("Height: " + height);
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
