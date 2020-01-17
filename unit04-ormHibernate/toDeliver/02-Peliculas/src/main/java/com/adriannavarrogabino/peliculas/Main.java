/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adriannavarrogabino.peliculas;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author adrian
 */
public class Main {
    static Session sesion;
    static Scanner sc;
    static boolean exit;
    
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        exit = false;
        int opcion;
        
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger
                .getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate")
                .setLevel(java.util.logging.Level.SEVERE);
        
        
            
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        while(!exit)
        {
            opcion = mostrarMenu();
            switch(opcion)
            {
                case 1: guardarPelicula(); break;
                case 2: guardarTematica(); break;
                case 3: verPeliculas(); break;
                case 4: verTematicas(); break;
                case 5: modificarPelicula(); break;
                case 6: borrarPelicula(); break;
                case 7: borrarTematica(); break;
                case 0: exit = true; break;
            }
        }
        sesion.close();
    }
    
    public static int mostrarMenu()
    {
        System.out.println("1. Guardar película");
        System.out.println("2. Guardar temática");
        System.out.println("3. Ver películas");
        System.out.println("4. Ver temáticas");
        System.out.println("5. Modificar película");
        System.out.println("6. Borrar película");
        System.out.println("7. Borrar temática");
        System.out.println("0. Salir");
        System.out.println("Elige una opcion:");
        
        int op = sc.nextInt();
        sc.nextLine();
        return op;
    }
    
    public static void guardarPelicula()
    {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        
        Query consulta = sesion.createQuery("from Peliculas where nombre = '" +
                nombre + "'");
        List resultados = consulta.list();
        
        if(resultados.size() != 0)
        {
            System.out.println("La película ya existe");
        }
        else
        {
            System.out.print("Puntuación: ");
            int puntuacion = sc.nextInt();
            System.out.print("Duración: ");
            int duracion = sc.nextInt();
            sc.nextLine();
            System.out.print("Temática: ");
            String tematica = sc.nextLine();

            Query consulta2 =
                    sesion.createQuery("FROM Tematicas WHERE nombre = '" +
                    tematica + "'");
            List resultados2 = consulta2.list();

            Tematicas tematicaResultado;

            if(resultados2.size() > 0)
            {
                tematicaResultado = (Tematicas)resultados2.get(0);
            }
            else
            {
                Transaction trans = sesion.beginTransaction();
                Tematicas t = new Tematicas(tematica);
                sesion.save(t);
                trans.commit();
                System.out.println("Temática creada");

                tematicaResultado = t;
            }

            Transaction trans = sesion.beginTransaction();
            Peliculas pelicula = new Peliculas(
                    nombre, tematicaResultado, puntuacion, duracion);
            sesion.save(pelicula);
            trans.commit();
            System.out.println("Película creada");
        }
    }
    
    public static void guardarTematica()
    {
        System.out.print("Temática: ");
        String tematica = sc.nextLine();
        
        Query consulta = sesion.createQuery("FROM Tematicas WHERE nombre = '" +
                tematica + "'");
        List resultados = consulta.list();
        
        if(resultados.size() != 0)
        {
            System.out.println("La temática ya existe");
        }
        else
        {
            Set<Peliculas> peliculases = new HashSet(0);
            
            Transaction trans = sesion.beginTransaction();
            Tematicas t = new Tematicas(tematica, peliculases);
            sesion.save(t);
            trans.commit();
            
            System.out.println("Temática creada");
        }
    }
    
    public static void verPeliculas()
    {
        System.out.println ("Mostrando todas las peliculas:");
        Query consulta = sesion.createQuery("from Peliculas");
        List resultados = consulta.list();
        for(Peliculas pelicula : (List<Peliculas>)resultados)
        {
            System.out.println(pelicula);
        }
    }
    
    public static void verTematicas()
    {
        System.out.println ("Mostrando todas las tematicas:");
        Query consulta = sesion.createQuery("from Tematicas");
        List resultados = consulta.list();
        for(Tematicas tematica : (List<Tematicas>)resultados)
        {
            System.out.println(tematica);
        }
    }
    
    public static void borrarPelicula() {
        System.out.print("Película: ");
        String nombre = sc.nextLine();
        
        try {
            Query consulta =
                    sesion.createQuery("FROM Peliculas WHERE nombre = '" +
                            nombre);
            List<Peliculas> peliculas = consulta.list();
            if (peliculas.size() > 0) {
                Transaction trans = sesion.beginTransaction();
                sesion.delete(peliculas.get(0));
                trans.commit();
                System.out.println("Pelicula borrada");
            } else {
                System.out.println("No existe esa película");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar película");
        }
    }
    
    public static void borrarTematica() {
        System.out.print("Película: ");
        String nombre = sc.nextLine();
        
        try {
            Query consulta =
                    sesion.createQuery("FROM Peliculas WHERE nombre = '" +
                            nombre);
            List<Peliculas> peliculas = consulta.list();
            if (peliculas.size() > 0) {
                Transaction trans = sesion.beginTransaction();
                sesion.delete(peliculas.get(0));
                trans.commit();
                System.out.println("Pelicula borrada");
            } else {
                System.out.println("No existe esa película");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar película");
        }
    }
    
    public static void modificarPelicula()
    {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        
        Query consulta = sesion.createQuery("from Peliculas where nombre = '" +
                nombre + "'");
        List<Peliculas> resultados = consulta.list();
        
        if(resultados.isEmpty())
        {
            System.out.println("La película no existe");
        }
        else
        {
            System.out.print("Puntuación: ");
            String puntuacion = sc.nextLine();
            System.out.print("Duración: ");
            String duracion = sc.nextLine();
            System.out.print("Temática: ");
            String tematica = sc.nextLine();

            Query consulta2 =
                    sesion.createQuery("FROM Tematicas WHERE nombre = '" +
                    tematica + "'");
            List resultados2 = consulta2.list();

            Tematicas tematicaResultado = null;

            if(resultados2.size() > 0)
            {
                tematicaResultado = (Tematicas)resultados2.get(0);
            }
            else if(!tematica.equals(""))
            {
                Transaction trans = sesion.beginTransaction();
                Tematicas t = new Tematicas(tematica);
                sesion.save(t);
                trans.commit();
                System.out.println("Temática creada");

                tematicaResultado = t;
            }

            Transaction trans = sesion.beginTransaction();
            if(!puntuacion.equals(""))
            {
                resultados.get(0).setPuntuacion(Integer.parseInt(puntuacion));
            }
            if(!duracion.equals(""))
            {
                resultados.get(0).setDuracion(Integer.parseInt(duracion));
            }
            if(tematicaResultado != null)
            {
                resultados.get(0).setTematicas(tematicaResultado);
            }
            sesion.update(resultados.get(0));
            trans.commit();
            System.out.println("Película modificada");
        }
    }
}
