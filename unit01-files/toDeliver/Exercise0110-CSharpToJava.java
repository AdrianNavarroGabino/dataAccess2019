// Adrián Navarro Gabino

/*
Create a program that does a basic conversion of a program in C # to its
equivalent in Java, taking as an example the file "program.cs" uploaded on this
platform.

The note will be as follows:

    Basic structure (read from one file, dump to another, analyze command line):
    4 points.
    The "using" will become your equivalent "import" in Java: 1 point.
    "WriteLine" will become (with its nuances) "println": 1 point.
    The data types will be the equivalent in Java. For example, they will not be
    "string", but "String": 1 point
    Any other consideration of changes between language. 3 points

(Here it would enter that the "main" must be in lowercase like the other
methods, both those created by us and those of the libraries. Also keep in mind
that the opening key of the blocks in Java is usually put at the end of the
previous line and in C # in the next line)


Since a converter between both languages ​​would be extremely complex, it is only
necessary to comply with the transformation of the last file for the test.

C# File:

using System;

namespace Proyecto1
{
    class Program
    {
        static int numero;
        double numDecimal;
        bool condicional;
        char letra;
        string cadena;

        static void Ejercicio1()
        {
            int a = 5, b = 5;
            if(a>b)
			{
				Console.WriteLine("A es mayor que B");
			}
			else
			{
				Console.WriteLine("B es mayor o igual que A");
			}
        }

        static void Ejercicio2()
        {
            Console.WriteLine("Introduce una letra");
            var letra = Console.ReadKey(true);
            if (letra.KeyChar >= 'A' && letra.KeyChar <= 'Z')
            {
                Console.WriteLine("La letra es mayúscula");
            }
            else
            {
                Console.WriteLine("La letra no es mayúscula");
            }
        }
        static void Main(string[] args)
        {
            Ejercicio1();
            Ejercicio2();
        }
    }
}
*/

import java.io.*;
import java.util.*;

public class Exercise0110
{
    public static void main(String[] args)
    {
        boolean isLineChanged;
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
            PrintWriter pw = new PrintWriter(
                filename.substring(0, filename.length() - 3) + ".java");
            String line = file.readLine();
            while (line != null) {
                isLineChanged = false;

                if(line.contains("using"))
                {
                    isLineChanged = true;
                    pw.println("import java.util.*;");
                    line = "import java.io.*;";
                }

                if(line.contains("Console.WriteLine"))
                {
                    isLineChanged = true;
                    line = line.replace(
                        "Console.WriteLine", "System.out.println");
                }

                if(line.contains("bool"))
                {
                    isLineChanged = true;
                    line = line.replace("bool", "boolean");
                }

                if(line.contains("string"))
                {
                    isLineChanged = true;
                    line = line.replace("string", "String");
                }

                if(line.trim().contains("string"))
                {
                    isLineChanged = true;
                    line = line.replace("string", "String");
                }

                if(line.contains("static void"))
                {
                    isLineChanged = true;
                    line = line.replace("Ejercicio", "ejercicio");
                    line += " {";
                }

                if(line.contains("class"))
                {
                    isLineChanged = true;
                    line = line.substring(
                        0, line.indexOf("class")) + "public " +
                        line.substring(line.indexOf("class")) + " {";
                }

                if(line.contains("}") && line.indexOf("}") != 0)
                {
                    isLineChanged = true;
                }
                
                if(line.contains("int") || line.contains("double") ||
                    line.contains("char"))
                {
                    isLineChanged = true;
                }

                if(line.contains("Main"))
                {
                    isLineChanged = true;
                    line = line.replace("Main", "main");
                    line = line.substring(0, line.indexOf("static void")) +
                        "public " + line.substring(line.indexOf("static void"));
                }

                if(line.trim().startsWith("if") ||
                    line.trim().startsWith("else"))
                {
                    isLineChanged = true;
                    line += " {";
                }

                if(line.contains("var"))
                {
                    isLineChanged = true;
                    line = line.replace("var", "char");
                }

                if(line.contains(".KeyChar"))
                {
                    isLineChanged = true;
                    line = line.replace(".KeyChar", "");
                }

                if(line.contains("Console.ReadKey"))
                {
                    isLineChanged = true;
                    pw.println(line.substring(0, line.indexOf("char")) +
                        "Scanner sc = new Scanner(System.in);");
                    line = line.replace(
                        "Console.ReadKey(true)", "sc.next().charAt(0)");
                }
                
                if(line.trim().startsWith("Ejercicio"))
                {
                    isLineChanged = true;
                    line = line.replace("Ejercicio", "ejercicio");
                }

                if(isLineChanged)
                    pw.println(line);

                line = file.readLine();
            }
            pw.close();
            file.close();

            System.out.println("Text translated succesfully");
        }
        catch (IOException errorDeFichero) {
            System.out.println(
                "There were problems: " +
                errorDeFichero.getMessage() );
        }
    }
}
