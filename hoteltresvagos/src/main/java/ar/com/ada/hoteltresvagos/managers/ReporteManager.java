package ar.com.ada.hoteltresvagos.managers;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.com.ada.hoteltresvagos.entities.reportes.*;

public class ReporteManager {

    protected SessionFactory sessionFactory;

    public void reporte1Query(){

        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery(
        "SELECT t.estado_id, t.descripcion, " + 
        "SUM(r.importe_reserva) total_reserva, " +
        "SUM(r.importe_total) total_importe, " +
        "SUM(r.importe_pagado) total_pagado " +
        "FROM tipo_estado_pago t " + 
        "INNER JOIN reserva r ON r.estado_id = t.estado_id " + 
        "GROUP BY t.estado_id, t.descripcion;", 
        Reporte1.class);
        
    }

    public void reporte2Query(){

        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery(
        "SELECT h.huesped_id, h.nombre, t.estado_id, " + 
        "SUM(r.importe_reserva) total_reserva, " +
        "SUM(r.importe_total) total_importe, " + 
        "SUM(r.importe_pagado) total_pagado " +
        "FROM huesped h " + 
        "INNER JOIN reserva r on h.huesped_id = r.huesped_id " +
        "INNER JOIN tipo_estado_pago t ON r.estado_id = t.estado_id " +
        "GROUP BY h.huesped_id, h.nombre, t.estado_id;", 
        Reporte2.class);
        
    }
    
}