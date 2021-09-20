/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteBO extends Conexion {
    public int AlmacenarCliente (ClienteVO per) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "INSERT INTO cliente(razonsocial, nombre, apellido, dni, habilitado) VALUES(?,?,?,?,'true')";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setString(1, per.getRazonSocial());
            cs.setString(2, per.getNombre());
            cs.setString(3, per.getApellido());
            cs.setString(4, per.getDni());
            
            int consul = cs.executeUpdate();
            if (consul >0){
                return resultado=1;
            } else {
                return resultado=0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO");
        }
        return resultado;
    }
    
    
    public int ModificarCliente (ClienteVO per) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "UPDATE cliente SET razonsocial=?,nombre=?,apellido=?,dni=? WHERE idcliente=?";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setInt(5, per.getIdCliente());
            cs.setString(1, per.getRazonSocial());
            cs.setString(2, per.getNombre());
            cs.setString(3, per.getApellido());
            cs.setString(4, per.getDni());
            
            int consul = cs.executeUpdate();
            if (consul >0){
                return resultado=1;
            } else {
                return resultado=0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO" +e);
        }
        return resultado;
    }
    
    public int EliminarCliente(int id){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia         = "UPDATE cliente SET habilitado='false' WHERE idcliente=?";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                cs.setInt(1, id);

                int consul = cs.executeUpdate();
                if (consul >0){
                    return resultado=1;
                } else {
                    return resultado=0;
                }            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO");
        }
        return resultado;
    }
    
    
    public ArrayList<ClienteVO> buscarTodos(){
        ArrayList listaCliente = new ArrayList();
        
        ClienteVO cliente;
        
        Connection con = getConectar();
            try{
                String sentencia = "SELECT * FROM cliente WHERE habilitado=true;";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                ResultSet rs = cs.executeQuery();
                while (rs.next()){
                    cliente = new ClienteVO();
                    cliente.setIdCliente(rs.getInt(1));
                    cliente.setRazonSocial(rs.getString(2));
                    cliente.setNombre(rs.getString(3));
                    cliente.setApellido(rs.getString(4));
                    cliente.setDni(rs.getString(5));
                    
                    listaCliente.add(cliente);
                }
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en BO");
        }
        return listaCliente;
    }
    
    public int nuevoId(){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia = "SELECT (idcliente+1) AS \"id\" FROM cliente ORDER BY idcliente DESC LIMIT 1";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                ResultSet rs = cs.executeQuery();
                if (rs.isBeforeFirst()){
                    rs.next();
                    resultado = rs.getInt(1);
                    return resultado;
                } else{
                    return 0;
                }
                         
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO"+e);
        }
        return resultado;
    }
    
    
}
