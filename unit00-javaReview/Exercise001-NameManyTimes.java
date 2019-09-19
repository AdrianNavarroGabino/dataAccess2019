// Adrián Navarro Gabino

/*
 * 0.1: Create a program that displays the text "Tell me your name:" and
 * then, on the same line, read the user's name, which may contain
 * spaces. Then he will write 5 times "Hello," followed by the name of the
 * user, in 5 different lines.
 */

import java.util.*;

public class Exercise001
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Tell me your name: ");
        String name = sc.nextLine();
        
        for(int i = 0; i < 5; i++)
        {
            System.out.println("Hello, " + name);
        }
    }
}
