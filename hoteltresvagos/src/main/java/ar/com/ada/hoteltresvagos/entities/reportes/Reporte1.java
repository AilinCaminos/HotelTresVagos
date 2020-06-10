package ar.com.ada.hoteltresvagos.entities.reportes;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
//@Table(name = "tipo_estado_pago")
public class Reporte1 {

    @Id
    @Column(name = "estado_id")
    private int estadoId;
    private String descripcion;
    @Column(name = "total_reserva")
    private BigDecimal totalReserva;
    @Column(name = "total_importe")
    private BigDecimal totalImporte;
    @Column(name = "total_pagado")
    private BigDecimal totalPagado;
    
}