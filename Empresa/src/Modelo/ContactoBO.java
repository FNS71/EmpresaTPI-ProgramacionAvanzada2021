
package Modelo;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ContactoBO extends Conexion {
    public int AlmacenarContacto (ContactoVO cont) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "INSERT INTO contacto(nombre,apellido,telefono,email,habilitado) VALUES(?,?,?,?,'true')";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setString(1, cont.getNombre());
            cs.setString(2, cont.getApellido());
            cs.setString(3, cont.getTelefono());
            cs.setString(4,cont.getEmail());
            
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
    
    
    public int ModificarContacto (ContactoVO cont) {
        int resultado = 0;
        Connection con = getConectar();
        try{
            String sentencia = "UPDATE contacto SET nombre=?,apellido=?,telefono=?,email=? WHERE idcontacto=?";
            CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
            cs.setInt(5, cont.getIdcontacto());
            cs.setString(1, cont.getNombre());
            cs.setString(2, cont.getApellido());
            cs.setString(3, cont.getTelefono());
            cs.setString(4, cont.getEmail());
            
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
    
    public int EliminarContacto(int id){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia         = "UPDATE contacto SET habilitado='false' WHERE idcontacto=?";
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
    
    
    public ArrayList<ContactoVO> buscarTodos(){
        ArrayList listaContacto = new ArrayList();
        
        ContactoVO contacto;
        
        Connection con = getConectar();
            try{
                String sentencia = "SELECT * FROM contacto WHERE habilitado=true;";
                CallableStatement cs = (CallableStatement) con.prepareCall(sentencia);
                ResultSet rs = cs.executeQuery();
                while (rs.next()){
                    contacto = new ContactoVO();
                    contacto.setIdcontacto(rs.getInt(1));
                    contacto.setNombre(rs.getString(2));
                    contacto.setApellido(rs.getString(3));
                    contacto.setTelefono(rs.getString(4));
                    contacto.setEmail(rs.getString(5));
                    
                    listaContacto.add(contacto);
                }
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error en BO");
        }
        return listaContacto;
    }
    
    public int nuevoId(){
        int resultado = 0;
        Connection con = getConectar();
        try{
                String sentencia = "SELECT (idcontacto+1) AS \"id\" FROM contacto ORDER BY idcontacto DESC LIMIT 1";
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
