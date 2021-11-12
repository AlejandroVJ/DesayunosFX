package com.mycompany.desayunosfx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import models.Pedidos;
import java.util.Date;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author USUARIO
 */
public class FuncionPedido {

    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    private static Session s = sf.openSession();
    
    

    public void listaComandas() {
        Query q = s.createQuery("FROM Pedidos", Pedidos.class);
        ArrayList<Pedidos> res = (ArrayList<Pedidos>) q.getResultList();

    }

    public void listaComandasPendientes() {
        Query q = s.createQuery("FROM Pedidos p WHERE p.estado='PENDIENTE'", Pedidos.class);
        ArrayList<Pedidos> res = (ArrayList<Pedidos>) q.getResultList();
        if (res.size() > 0) {
            System.out.println("\n\nPEDIDOS PENDIENTES:");
            res.forEach((p) -> System.out.println(p));
        } else {
            System.out.println("No hay pedidos pendientes");
        }
        
    }

}
