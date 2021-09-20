/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

public class ClienteVO {
    private int idCliente;
    private String nombre;
    private String apellido;
    private String dni;
    private String razonSocial;
    private boolean habilitado;

    public ClienteVO() {
    }

    public ClienteVO(String nombre, String apellido, String dni, String razonSocial) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.razonSocial = razonSocial;
        this.habilitado = true;
    }
    
    
    public ClienteVO(int idCliente, String nombre, String apellido, String dni, String razonSocial) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.razonSocial = razonSocial;
        this.habilitado = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    
    
    
}
