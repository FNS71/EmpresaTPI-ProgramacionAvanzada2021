
package Controlador;

import Modelo.ContactoBO;
import Modelo.ContactoVO;
import Vista.frmContacto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controladorContacto implements ActionListener{
    private frmContacto vista;
    private ContactoBO modelo;
    
    private String modo = "ALTA";
    
     //MODOS: ALTA, MODIFICACION, BAJA
    
    public controladorContacto(frmContacto vista, ContactoBO modeloBO){
        this.vista=vista;
        this.modelo = modeloBO;
        this.vista.btnAgregarCont.addActionListener(this);
        this.vista.btnModificarCont.addActionListener(this);
        this.vista.btnEliminarCont.addActionListener(this);
        this.vista.btnSeleccionarCont.addActionListener(this);
        
        limpiar();
        tabla();
    }
    @Override
    public void actionPerformed(ActionEvent e){
       
        if (e.getSource() == vista.btnAgregarCont){
           //AGREGAR
            if(validacion()==false){
                  return;
            }
          
          if (modo=="ALTA") {
                String nombre = vista.txtNombreCont.getText();
                String apellido = vista.txtApellidoCont.getText();
                String telefono = vista.txtTelefonoCont.getText();
                String email = vista.txtEmailCont.getText();
                ContactoVO cont = new ContactoVO(nombre,apellido,telefono,email);

                if(modelo.AlmacenarContacto(cont)==1){
                    JOptionPane.showMessageDialog(null,"Registro Exitoso");
                } else {
                    JOptionPane.showMessageDialog(null,"No Registrado");
                }
                
                limpiar();
          }
          
           //MODIFICAR
          
          if (modo=="MODIFICAR") {
            
                int idcontacto = Integer.parseInt(vista.txtIdCont.getText());
                String nombre = vista.txtNombreCont.getText();
                String apellido = vista.txtApellidoCont.getText();
                String telefono = vista.txtTelefonoCont.getText();
                String email = vista.txtEmailCont.getText();

                ContactoVO cont = new ContactoVO(idcontacto,nombre,apellido,telefono,email);

                if(modelo.ModificarContacto(cont)==1){
                    JOptionPane.showMessageDialog(null,"Modificacion Exitosa");
                } else {
                    JOptionPane.showMessageDialog(null,"NO Modificado");
                }
                
                limpiar();
                modoAlta();
                
          }          
          
          tabla();
          limpiar();
      }
          //BOTON MODIFICAR / CANCELAR
        
          if (e.getSource() == vista.btnModificarCont){
              if(modo=="ALTA"){
                  modoModificacion();
              } else {
                  modoAlta();
                  limpiar();
              }
              
      }
          
          //BOTON ELIMINAR
          
          if (e.getSource() == vista.btnEliminarCont){
              Object[] siono = {"SI","NO"};
              int opcion = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminarlo?","Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
              if(opcion==0){
                int idPersonal = Integer.parseInt(vista.txtIdCont.getText());
                
                if(modelo.EliminarContacto(idPersonal)==1){
                    JOptionPane.showMessageDialog(null,"Eliminación Exitosa");
                } else {
                    JOptionPane.showMessageDialog(null,"No Eliminado");
                } 

              }
              limpiar();
              tabla();
              
          }
          
          if (e.getSource() == vista.btnSeleccionarCont){
              vista.btnAgregar.setEnabled(false);
              vista.btnEliminar.setEnabled(true);
              vista.btnModificar.setEnabled(true);
              int fila = vista.tableContacto.getSelectedRow();
              if(fila==-1){
                  JOptionPane.showMessageDialog(null,"Seleccione una fila");
              } else {
                  vista.txtIdCont.setText(vista.tableContacto.getValueAt(fila,0).toString());
                  vista.txtNombreCont.setText(vista.tableContacto.getValueAt(fila,1).toString());
                  vista.txtApellidoCont.setText(vista.tableContacto.getValueAt(fila,2).toString());
                  vista.txtTelefonoCont.setText(vista.tableContacto.getValueAt(fila,3).toString());
                  vista.txtEmailCont.setText(vista.tableContacto.getValueAt(fila,3).toString());
              }
          }

    }
    private void tabla(){
        JTable tabla = vista.tableContacto;
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tabla.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Telefono");
        model.addColumn("Email");
        
        Object[] columna = new Object[5];
        ArrayList<ContactoVO> resultado = new ArrayList();
        resultado = modelo.buscarTodos();
        
        int numr = resultado.size();
        for (int i = 0; i<numr; i++){
            columna[0] = resultado.get(i).getIdcontacto();
            columna[1] = resultado.get(i).getNombre();
            columna[2] = resultado.get(i).getApellido();
            columna[3] = resultado.get(i).getTelefono();
            columna[4] = resultado.get(i).getEmail();
            
            model.addRow(columna);
        }
    }
    public void limpiar(){
        vista.txtApellidoCont.setText("");
        vista.txtTelefonoCont.setText("");
        vista.txtNombreCont.setText("");
        vista.txtEmailCont.setText("");
        
        ContactoBO nuevo = new ContactoBO(); 
        vista.txtIdCont.setText(String.valueOf(nuevo.nuevoId()));
        modoAlta();
    }
        public void modoAlta(){
        modo = "ALTA";
        vista.btnAgregarCont.setText("Agregar");
        vista.btnModificarCont.setText("Modificar");
        vista.btnEliminarCont.setText("Eliminar");
        vista.btnAgregar.setEnabled(true);
        vista.btnEliminar.setEnabled(false);
        vista.btnModificar.setEnabled(false);
    }
     public void modoModificacion(){
        modo = "MODIFICAR";
        vista.btnAgregarCont.setText("Aceptar");
        vista.btnModificarCont.setText("Cancelar");
        vista.btnEliminarCont.setText("");
        vista.btnAgregar.setEnabled(true);
        vista.btnModificar.setEnabled(true);
        vista.btnEliminar.setEnabled(false);
        
    }public boolean validacion(){
        if(vista.txtNombreCont.getText().length()==0){
            vista.txtNombreCont.requestFocus();
            JOptionPane.showMessageDialog(null,"Ingrese un nombre");
            return false;
        }
        if(vista.txtApellidoCont.getText().length()==0){
            vista.txtApellidoCont.requestFocus();
            JOptionPane.showMessageDialog(null,"Ingrese un apellido");
            return false;
        }
        return true;
    }
}
