package ar.com.ada.hoteltresvagos.entities.reportes;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class Reporte2 {
    
    @Id
    @Column(name = "huesped_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int huespedId;
    private String nombre;
    @Column(name = "estado_id")
    private int estado_id;
    @Column(name = "total_reserva")
    private BigDecimal totalReserva;
    @Column(name = "total_importe")
    private BigDecimal totalImporte;
    @Column(name = "total_pagado")
    private BigDecimal totalPagado;



}