// Adrián Navarro Gabino

/*
 * 1.2.2: Create a program that shows all the contents of the
 * "annotations.txt" file.
 */

import java.io.*;

public class Exercise122
{
    public static void main(String[] args)
    {
        if (! (new File("annotations.txt")).exists() ) {
            System.out.println("annotations.txt not found");
            return;
        }

        try {
            BufferedReader file = new BufferedReader(
                new FileReader(new File("annotations.txt")));

            String line = file.readLine();
            while (line != null) {
                System.out.println(line);

                line = file.readLine();
            }
            
            file.close();
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}