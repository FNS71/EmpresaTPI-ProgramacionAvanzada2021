/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PersonalBO extends Conexion {
    public int AlmacenarPersonal (PersonalVO per) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "INSERT INTO personal(nombre,apellido,dni,habilitado) VALUES(?,?,?,'true')";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setString(1, per.getNombre());
            cs.setString(2, per.getApellido());
            cs.setString(3, per.getDni());
            
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
    
    
    public int ModificarPersonal (PersonalVO per) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "UPDATE personal SET nombre=?,apellido=?,dni=? WHERE idpersonal=?";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setInt(4, per.getIdPersonal());
            cs.setString(1, per.getNombre());
            cs.setString(2, per.getApellido());
            cs.setString(3, per.getDni());
            
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
    
    public int EliminarPersonal(int id){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia         = "UPDATE personal SET habilitado='false' WHERE idpersonal=?";
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
    
    
    public ArrayList<PersonalVO> buscarTodos(){
        ArrayList listaPersonal = new ArrayList();
        
        PersonalVO personal;
        
        Connection con = getConectar();
            try{
                String sentencia = "SELECT * FROM personal WHERE habilitado=true;";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                ResultSet rs = cs.executeQuery();
                while (rs.next()){
                    personal = new PersonalVO();
                    personal.setIdPersonal(rs.getInt(1));
                    personal.setNombre(rs.getString(2));
                    personal.setApellido(rs.getString(3));
                    personal.setDni(rs.getString(5));
                    
                    listaPersonal.add(personal);
                }
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en BO");
        }
        return listaPersonal;
    }
    
    public int nuevoId(){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia = "SELECT (idpersonal+1) AS \"id\" FROM personal ORDER BY idpersonal DESC LIMIT 1";
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
