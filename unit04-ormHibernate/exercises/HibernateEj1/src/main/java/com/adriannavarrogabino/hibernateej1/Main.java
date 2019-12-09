/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adriannavarrogabino.hibernateej1;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author adrian
 */
public class Main
{
    static Session sesion;
    static Scanner sc;
    
    public static void showSeries()
    {
        System.out.println ("Series:");
        Query query = sesion.createQuery("from Serie");
        List result = query.list();
        for(Serie serie : (List<Serie>)result)
        {
            System.out.println ( serie.getCodigo() + ": " +
                serie.getNombre() + " (" +
                serie.getCadena() + ") - " + 
                serie.getDuracion() + " min.");
        }
    }
    
    public static void addSerie()
    {
        System.out.print("Serie: ");
        String name = sc.nextLine();
        System.out.print("Channel: ");
        String channel = sc.nextLine();
        System.out.print("Duration: ");
        int duration = sc.nextInt();
        sc.nextLine();
        
        Serie serie = new Serie();
        serie.setNombre(name);
        serie.setCadena(channel);
        serie.setDuracion(duration);
        
        Transaction trans = sesion.beginTransaction();
        sesion.save(serie);
        trans.commit();
    }
    
    public static void main(String[] args)
    {
        sc = new Scanner(System.in);
        
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
        
        boolean exit = false;
        int option;
        
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        do
        {
            System.out.println("1. Show series");
            System.out.println("2. Insert serie");
            System.out.println("0.Exit");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine();
            System.out.println();
            
            switch(option)
            {
                case 1: showSeries(); break;
                case 2: addSerie(); break;
                case 0: exit = true; break;
            }
        } while(!exit);
        showSeries();
        sesion.close();
    }
}
