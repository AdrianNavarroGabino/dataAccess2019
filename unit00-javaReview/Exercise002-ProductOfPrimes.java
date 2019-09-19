// Adrián Navarro Gabino

/*
 * 0.2: Ask the user for an integer and show it as a product of their
 * prime factors, something like: 24 = 2 * 2 * 2 * 3
 */

import java.util.*;

public class Exercise002
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer number: ");
        int number = sc.nextInt();
        
        System.out.print(number + " = ");
        
        int factor = 2;
        boolean firstDivider = true;
        
        while(number != 1)
        {
            if(isPrime(factor) && number % factor == 0)
            {
                if(firstDivider)
                {
                    firstDivider = false;
                    System.out.print(factor);
                }
                else
                {
                    System.out.print(" * " + factor);
                }
                
                number /= factor;
            }
            else
            {
                factor++;
            }
        }
    }
    
    public static boolean isPrime(int n)
    {
        for(int i = 2; i <= n / 2; i++)
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        
        return true;
    }
}
