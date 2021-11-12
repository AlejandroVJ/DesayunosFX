package com.mycompany.desayunosfx;

import java.util.ArrayList;
import javax.persistence.Query;
import models.Carta;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author USUARIO
 */
public class FuncionCarta {
    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    private static Session s = sf.openSession();
    
    public void listaCarta(){
        Query q = s.createQuery("FROM Carta", Carta.class);
        ArrayList<Carta> res = (ArrayList<Carta>)q.getResultList();
        System.out.println("\n\nCONTENIDO DE LA CARTA:");
        res.forEach( (p)->System.out.println(p) );
    }
    
    
}
