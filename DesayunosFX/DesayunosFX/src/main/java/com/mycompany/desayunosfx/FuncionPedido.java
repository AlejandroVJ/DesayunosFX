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
    
     //Creamos pedido
    public void crearPedido() {
        String nombre;
        String curso;
        String estado;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserta tu nombre: ");
        nombre = sc.next();
        System.out.println("Inserta tu curso: ");
        curso = sc.next();
        System.out.println("Inserta el id del producto que desea: ");
        Integer cartaId = sc.nextInt();

        java.util.Date fechaAhora = new java.util.Date();
        java.sql.Date fecha = new java.sql.Date(fechaAhora.getTime());

        Pedidos p = new Pedidos();
        p.setCliente(nombre);
        p.setCurso(curso);
        p.setEstado("PENDIENTE");
        p.setFecha(new Date());
        p.setCartaId(cartaId);
        Transaction t = s.beginTransaction();
        s.save(p);
        t.commit();

    }

    public void marcarPedido() {
        Integer pedidoId;
        Scanner sc = new Scanner(System.in);
        listaComandas();

        System.out.println("Inserta el id del pedido que se va a recoger: ");
        pedidoId = sc.nextInt();
        Serializable id = pedidoId;

        Transaction t = s.beginTransaction();
        Pedidos p = s.load(Pedidos.class, id);
        p.setEstado("ENTREGADO");
        s.update(p);
        t.commit();
    }

    public void borrarPedido() {
        Integer pedido;
        Integer r = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserta el id del pedido que quieres eliminar: ");
        pedido = sc.nextInt();
        Serializable id = pedido;

        Transaction t = s.beginTransaction();
        Pedidos p = s.load(Pedidos.class, id);
        s.remove(p);
        t.commit();
    }

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
