/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

public class ProyectoVO {
    private int idProyecto;
    private String nombre;
    private String descripcionGlobal;
    private java.sql.Date fechaCarga;
    private java.sql.Date fechaConfirmacion;
    private java.sql.Date fechaEntrega;
    private java.sql.Date fechaTermino;
    private String observaciones;
    private int idTipoProyecto;
    private int idCliente;
    private boolean habilitado;

    public ProyectoVO() {
    }

    public ProyectoVO(String nombre, String descripcionGlobal, java.sql.Date fechaCarga, java.sql.Date fechaConfirmacion, java.sql.Date fechaEntrega, java.sql.Date fechaTermino, String observaciones, int idTipoProyecto, int idCliente) {
        this.nombre = nombre;
        this.descripcionGlobal = descripcionGlobal;
        this.fechaCarga = fechaCarga;
        this.fechaConfirmacion = fechaConfirmacion;
        this.fechaEntrega = fechaEntrega;
        this.fechaTermino = fechaTermino;
        this.observaciones = observaciones;
        this.idTipoProyecto = idTipoProyecto;
        this.idCliente = idCliente;
        this.habilitado = true;
    }
    
    
    public ProyectoVO(int idProyecto, String nombre, String descripcionGlobal, java.sql.Date fechaCarga, java.sql.Date fechaConfirmacion, java.sql.Date fechaEntrega, java.sql.Date fechaTermino, String observaciones, int idTipoProyecto, int idCliente) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.descripcionGlobal = descripcionGlobal;
        this.fechaCarga = fechaCarga;
        this.fechaConfirmacion = fechaConfirmacion;
        this.fechaEntrega = fechaEntrega;
        this.fechaTermino = fechaTermino;
        this.observaciones = observaciones;
        this.idTipoProyecto = idTipoProyecto;
        this.idCliente = idCliente;
        this.habilitado = true;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionGlobal() {
        return descripcionGlobal;
    }

    public void setDescripcionGlobal(String descripcionGlobal) {
        this.descripcionGlobal = descripcionGlobal;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public void setIdTipoProyecto(int idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
