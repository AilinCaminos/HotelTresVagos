package ar.com.ada.hoteltresvagos.managers;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ar.com.ada.hoteltresvagos.entities.*;
public class ReservaManager {

    protected SessionFactory sessionFactory;

    public void setup() {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings
                                                                                                  // from
                                                                                                  // hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw ex;
        }

    }

    public void exit() {
        sessionFactory.close();
    }

    public void create(Reserva reserva) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(reserva);

        session.getTransaction().commit();
        session.close();
    }

    public Reserva read(int reservaId) {
        Session session = sessionFactory.openSession();

        Reserva reserva = session.get(Reserva.class, reservaId);

        session.close();

        return reserva;
    }

    public Reserva readByDNI(int dni) {
        Session session = sessionFactory.openSession();

        Reserva reserva = session.byNaturalId(Reserva.class).using("dni", dni).load();

        session.close();

        return reserva;
    }

    public void update(Reserva reserva) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(reserva);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Reserva reserva) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(reserva);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Este metodo en la vida real no debe existir ya qeu puede haber miles de
     * usuarios
     * 
     * @return
     */
    public List<Reserva> buscarTodos() {

        Session session = sessionFactory.openSession();

        /// NUNCA HARCODEAR SQLs nativos en la aplicacion.
        // ESTO es solo para nivel educativo
        Query query = session.createNativeQuery("SELECT * FROM reserva", Reserva.class);
        //query = session.createQuery("From Obse")
        List<Reserva> todos = query.getResultList();

        return todos;

    }

    /**
     * Busca una lista de reservas por el nombre completo Esta armado para que se
     * pueda generar un SQL Injection y mostrar commo NO debe programarse.
     * 
     * @param nombre
     * @return
     */
    public List<Reserva> buscarPor(String nombre) {

        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("SELECT *  FROM reserva r inner join huesped h on h.huesped_id = r.huesped_id where nombre = ?", Reserva.class);
        query.setParameter(1, nombre);

        List<Reserva> reservas = query.getResultList();

        return reservas;

    }

    
}