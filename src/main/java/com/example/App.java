package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        try {
            Acount ac = new Acount();
            ac.abono(100);
            ac.cargo(50);
            ac.cargo(-10);
            ac.abono(-15);    
        } catch (IllegalArgumentException e) {
            System.out.println( e.getMessage() );
        }
        catch (Exception e) {
            System.out.println( e.getMessage() );
        }
        
        
    }
}
