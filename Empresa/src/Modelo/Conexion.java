
package Modelo;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.sql.DataSource;

public class Conexion {
    public DataSource dataSource;
    String DB = "jdbc:postgresql://localhost:5432/Empresa";
    String usuario = "java";
    String contra = "java";
    
    public Connection getConectar(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB,usuario,contra);
            //JOptionPane.showMessageDialog(null,"Conexion exitosa");
        } catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Error en conexion");
        }
    return conn;
    }
}
    
