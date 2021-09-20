/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TipoProyectoBO extends Conexion {
    public int AlmacenarTipoProyecto (TipoProyectoVO tp) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "INSERT INTO tipoproyecto(nombre,descripcion,habilitado) VALUES(?,?,'true')";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setString(1, tp.getNombre());
            cs.setString(2, tp.getDescripcion());
            
            int consul = cs.executeUpdate();
            if (consul >0){
                return resultado=1;
            } else {
                return resultado=0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO"+e);
        }
        return resultado;
    }
    
    
    public int ModificarTipoProyecto (TipoProyectoVO tp) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "UPDATE tipoproyecto SET nombre=?,descripcion=? WHERE idtipoproyecto=?";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setInt(3, tp.getIdTipoProyecto());
            cs.setString(1, tp.getNombre());
            cs.setString(2, tp.getDescripcion());
 
            
            int consul = cs.executeUpdate();
            if (consul >0){
                return resultado=1;
            } else {
                return resultado=0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error en BO"+e);
        }
        return resultado;
    }
    
    public int EliminarTipoProyecto(int id){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia  = "UPDATE tipoproyecto SET habilitado='false' WHERE idtipoproyecto=?";
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
    
    
    public ArrayList<TipoProyectoVO> buscarTodos(){
        ArrayList listaTipoProyecto = new ArrayList();
        
        TipoProyectoVO tipoproyecto;
        
        Connection con = getConectar();
            try{
                String sentencia = "SELECT * FROM tipoproyecto WHERE habilitado=true;";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                ResultSet rs = cs.executeQuery();
                while (rs.next()){
                    tipoproyecto = new TipoProyectoVO();
                    tipoproyecto.setIdTipoProyecto(rs.getInt(1));
                    tipoproyecto.setNombre(rs.getString(2));
                    tipoproyecto.setDescripcion(rs.getString(3));
      
                    
                    listaTipoProyecto.add(tipoproyecto);
                }
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en BO");
        }
        return listaTipoProyecto;
    }
    
    public int nuevoId(){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia = "SELECT (idtipoproyecto+1) AS \"id\" FROM tipoproyecto ORDER BY idtipoproyecto DESC LIMIT 1";
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
