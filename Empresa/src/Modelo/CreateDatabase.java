/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import javax.swing.JOptionPane;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 *
 * @author emavi
 */
public class CreateDatabase extends Conexion {
    public void verificarDB(){
        if (existeDB()){
            if(!existenTablas()){
                int opcion = JOptionPane.showConfirmDialog(null,"No se encontraron algunas tablas. ¿Desea correr el script de inicialización? ADVERTENCIA: Se perderan los datos (si existen).","Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (opcion==0){
                    crearTablas();
                }
            }
        }
        
    }
    public boolean existeDB() {
        try{
            Connection con = getConectar();
            ResultSet rs = con.getMetaData().getCatalogs();
            while (rs.next()){
                // base de datos encontrada
                System.out.println("DB encontrada: " + rs.getString(1));
                rs.close();
                return true;
            }
            rs.close();
            System.out.println("No se ha encontrado la base de datos.");
            return false;
        } catch (Exception e) {
            System.out.println("No se ha encontrado la base de datos.");
            JOptionPane.showMessageDialog(null,"Error verificando la existencia de la base de datos: " +e);
            JOptionPane.showMessageDialog(null,"AVISO: Debe crear una base de datos y configurar el acceso (DBURL/DBName, User y PAssword con permisos) en la clase Conexion.");
            return false;
        }
    } 
    
    public boolean existenTablas(){
        Connection con = getConectar();
        try{
            ResultSet rs = con.getMetaData().getTables(null, null, "", null);
            rs.next();
            if (!rs.getString(3).equals("Personal_pkey")){
                JOptionPane.showMessageDialog(null,"No se ha encontrado la tabla personal");
                return false;
            }
            rs.next();
            if (!rs.getString(3).equals("cliente_pkey")){
                JOptionPane.showMessageDialog(null,"No se ha encontrado la tabla cliente");
                return false;
            }
            rs.next();
            if (!rs.getString(3).equals("contacto_pkey")){
                JOptionPane.showMessageDialog(null,"No se ha encontrado la tabla contacto");
                return false;
            }
            rs.next();
            if (!rs.getString(3).equals("proyecto_pkey")){
                JOptionPane.showMessageDialog(null,"No se ha encontrado la tabla proyecto");
                return false;
            }
            rs.next();
            if (!rs.getString(3).equals("tipoproyecto_pkey")){
                JOptionPane.showMessageDialog(null,"No se ha encontrado la tabla tipoproyecto");
                return false;
            }
            System.out.println("Todas las tablas se encontraron.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error verificando la existencia de las tablas: " +e);
            return false;
        }
        
        return true;
    }
    public void crearTablas(){
        try{
            Connection con = getConectar();
            ScriptRunner sr = new ScriptRunner(con);
            Reader scriptfile = new BufferedReader(new FileReader("./src/Modelo/scriptInicial.sql"));
            sr.runScript(scriptfile);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error en la creación de las tablas: " +e);
        }
        return;
    }
}
