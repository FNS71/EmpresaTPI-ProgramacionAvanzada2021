/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

public class TipoProyectoVO {
    private int idTipoProyecto;
    private String nombre;
    private String descripcion;
    private boolean habilitado;

    public TipoProyectoVO() {
    }

    public TipoProyectoVO(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilitado = true;
    }
    
    
    public TipoProyectoVO(int idTipoProyecto, String nombre, String descripcion) {
        this.idTipoProyecto = idTipoProyecto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilitado = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public void setIdTipoProyecto(int idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
 
}
