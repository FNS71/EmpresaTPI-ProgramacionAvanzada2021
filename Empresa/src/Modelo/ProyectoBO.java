/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProyectoBO extends Conexion {
    public int AlmacenarProyecto (ProyectoVO per) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "INSERT INTO proyecto(nombre, descripcionGlobal, fechaCarga, fechaConfirmacion, fechaEntrega, fechaTermino, observaciones, idTipoProyecto, idCliente, habilitado) VALUES(?,?,?,?,?,?,?,?,?,'true')";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setString(1, per.getNombre());
            cs.setString(2, per.getDescripcionGlobal());
            cs.setDate(3, per.getFechaCarga());
            if (per.getFechaConfirmacion().equals(new java.sql.Date(0))){
                cs.setNull(4, java.sql.Types.DATE);
            }else{
                cs.setDate(4, per.getFechaConfirmacion());
            }
            
            if (per.getFechaEntrega().equals(new java.sql.Date(0))){
                cs.setNull(5, java.sql.Types.DATE);
            }else{
                cs.setDate(5, per.getFechaEntrega());
            }
            
            if (per.getFechaTermino().equals(new java.sql.Date(0))){
                cs.setNull(6, java.sql.Types.DATE);
            }else{
                cs.setDate(6, per.getFechaTermino());
            }
            
            cs.setString(7, per.getObservaciones());
            cs.setInt(8, per.getIdTipoProyecto());
            cs.setInt(9, per.getIdCliente());
            
            
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
    
    
    public int ModificarProyecto (ProyectoVO per) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "UPDATE proyecto SET nombre=?, descripcionGlobal=?, fechaCarga=?, fechaConfirmacion=?, fechaEntrega=?, fechaTermino=?, observaciones=?, idTipoProyecto=?, idCliente=? WHERE idproyecto=?";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setInt(10, per.getIdProyecto());
            cs.setString(1, per.getNombre());
            cs.setString(2, per.getDescripcionGlobal());
            cs.setDate(3, per.getFechaCarga());
            cs.setDate(4, per.getFechaConfirmacion());
            cs.setDate(5, per.getFechaEntrega());
            cs.setDate(6, per.getFechaTermino());
            cs.setString(7, per.getObservaciones());
            cs.setInt(8, per.getIdTipoProyecto());
            cs.setInt(9, per.getIdCliente());
            
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
    
    public int EliminarProyecto(int id){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia         = "UPDATE proyecto SET habilitado='false' WHERE idproyecto=?";
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
    
    
    public ArrayList<ProyectoVO> buscarTodos(){
        ArrayList listaProyecto = new ArrayList();
        
        ProyectoVO proyecto;
        
        Connection con = getConectar();
            try{
                String sentencia = "SELECT * FROM proyecto WHERE habilitado=true;";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                ResultSet rs = cs.executeQuery();
                while (rs.next()){
                    proyecto = new ProyectoVO();
                    proyecto.setIdProyecto(rs.getInt(1));
                    proyecto.setNombre(rs.getString(2));
                    proyecto.setDescripcionGlobal(rs.getString(3));
                    proyecto.setFechaCarga(rs.getDate(4));
                    proyecto.setFechaConfirmacion(rs.getDate(5));
                    proyecto.setFechaEntrega(rs.getDate(6));
                    proyecto.setFechaTermino(rs.getDate(7));
                    proyecto.setObservaciones(rs.getString(8));
                    proyecto.setIdTipoProyecto(rs.getInt(10));
                    proyecto.setIdCliente(rs.getInt(11));
                    listaProyecto.add(proyecto);
                }
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en BO: "+e);
        }
        return listaProyecto;
    }
    
    public int nuevoId(){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia = "SELECT (idproyecto+1) AS \"id\" FROM proyecto ORDER BY idproyecto DESC LIMIT 1";
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
            JOptionPane.showMessageDialog(null,"Error en BO: " +e);
        }
        return resultado;
    }
    
    public String getNombreCliente(int id){
        String resultado = "";
        Connection con = getConectar();
        try{
                String sentencia = "SELECT (nombre || ' ' || apellido) AS \"nombre\" FROM cliente WHERE idcliente = ?";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                cs.setInt(1, id);
                ResultSet rs = cs.executeQuery();
                if (rs.isBeforeFirst()){
                    rs.next();
                    resultado = rs.getString(1);
                    return resultado;
                } else{
                    return "";
                }
                
                         
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO: " +e);
        }
        return resultado;
    }
    
    public String getNombreTipoProyecto(int id){
        String resultado = "";
        Connection con = getConectar();
        try{
                String sentencia = "SELECT nombre FROM tipoproyecto WHERE idtipoproyecto = ?";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                cs.setInt(1, id);
                ResultSet rs = cs.executeQuery();
                if (rs.isBeforeFirst()){
                    rs.next();
                    resultado = rs.getString(1);
                    return resultado;
                } else{
                    return "";
                }
                
                         
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO: " +e);
        }
        return resultado;
    }
    
    public void cargarComboCliente(JComboBox cboxCli){
        Connection con = getConectar();
        
        try{
            String sentencia = "SELECT idcliente, (nombre || ' ' || apellido) FROM cliente WHERE habilitado = 'true' ORDER BY apellido, nombre";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                cboxCli.addItem(new CboxItem(rs.getInt(1), rs.getString(2)));
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        }
    }
    
    public void cargarComboTipoProyecto(JComboBox cboxCli){
        Connection con = getConectar();
        
        try{
            String sentencia = "SELECT idtipoproyecto, nombre FROM tipoproyecto WHERE habilitado = 'true' ORDER BY nombre";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                cboxCli.addItem(new CboxItem(rs.getInt(1), rs.getString(2)));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            if(con!=null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        }
    }
    
    
    
    
}
