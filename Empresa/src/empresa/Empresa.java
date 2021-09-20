/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

//import Controlador.controladorPersonal;
//import Modelo.PersonalBO;
//import Vista.frmMenu;
import Modelo.CreateDatabase;
import Vista.frmMenu;

public class Empresa{
 
    public static void main(String[] args){
        
        CreateDatabase verificador = new CreateDatabase();
        verificador.verificarDB();
        
        frmMenu ventanaMenu = new frmMenu();
        ventanaMenu.setVisible(true);
        ventanaMenu.setLocationRelativeTo(null);
        

        /*frmPersonal ventana = new frmPersonal();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        PersonalBO modelo = new PersonalBO();
        new controladorPersonal(ventana,modelo);*/
    }
}
