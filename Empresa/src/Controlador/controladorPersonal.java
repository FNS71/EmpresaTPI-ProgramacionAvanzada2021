/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.PersonalBO;
import Modelo.PersonalVO;
import Vista.frmPersonal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class controladorPersonal implements ActionListener {

    private frmPersonal vista;
    private PersonalBO modelo;
    
    private String modo = "ALTA";
    
    
    //MODOS: ALTA, MODIFICACION, BAJA
    
    public controladorPersonal(frmPersonal vista, PersonalBO modeloBO){
        this.vista=vista;
        this.modelo = modeloBO;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnSeleccionar.addActionListener(this);
        
        limpiar();
        tabla();
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e){
       
        if (e.getSource() == vista.btnAgregar){
           //AGREGAR
          if(validacion()==false){
              return;
          }
          
          if (modo=="ALTA") {
                String nombre = vista.txtNombre.getText();
                String apellido = vista.txtApellido.getText();
                String dni = vista.txtDni.getText();

                PersonalVO per = new PersonalVO(nombre,apellido,dni);

                if(modelo.AlmacenarPersonal(per)==1){
                    JOptionPane.showMessageDialog(null,"Registro Exitoso");
                } else {
                    JOptionPane.showMessageDialog(null,"NO Registrado");
                }
                
                limpiar();
          }
          
           //MODIFICAR
          
          if (modo=="MODIFICAR") {
            
                int idPersonal = Integer.parseInt(vista.txtId1.getText());
                String nombre = vista.txtNombre.getText();
                String apellido = vista.txtApellido.getText();
                String dni = vista.txtDni.getText();

                PersonalVO per = new PersonalVO(idPersonal,nombre,apellido,dni);

                if(modelo.ModificarPersonal(per)==1){
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
        
          if (e.getSource() == vista.btnModificar){
              if(modo=="ALTA"){
                  modoModificacion();
              } else {
                  modoAlta();
                  limpiar();
              }
              
 
      }
          
          //BOTON ELIMINAR
          
          if (e.getSource() == vista.btnEliminar){
              Object[] siono = {"SI","NO"};
              int opcion = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminarlo?","Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
              if(opcion==0){
                int idPersonal = Integer.parseInt(vista.txtId1.getText());
                
                if(modelo.EliminarPersonal(idPersonal)==1){
                    JOptionPane.showMessageDialog(null,"Eliminación Exitosa");
                } else {
                    JOptionPane.showMessageDialog(null,"NO Eliminado");
                } 

              }
              limpiar();
              tabla();
              
          }
          
          if (e.getSource() == vista.btnSeleccionar){
              vista.btnAgregar.setEnabled(false);
              vista.btnEliminar.setEnabled(true);
              vista.btnModificar.setEnabled(true);
              int fila = vista.tablePersonal.getSelectedRow();
              if(fila==-1){
                  JOptionPane.showMessageDialog(null,"Seleccione una fila");
              } else {
                  vista.txtId1.setText(vista.tablePersonal.getValueAt(fila,0).toString());
                  vista.txtNombre.setText(vista.tablePersonal.getValueAt(fila,1).toString());
                  vista.txtApellido.setText(vista.tablePersonal.getValueAt(fila,2).toString());
                  vista.txtDni.setText(vista.tablePersonal.getValueAt(fila,3).toString());
              }
          }

    }
    
    private void tabla(){
        JTable tabla = vista.tablePersonal;
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
        model.addColumn("DNI");
        
        Object[] columna = new Object[4];
        ArrayList<PersonalVO> resultado = new ArrayList();
        resultado = modelo.buscarTodos();
        
        int numr = resultado.size();
        for (int i = 0; i<numr; i++){
            columna[0] = resultado.get(i).getIdPersonal();
            columna[1] = resultado.get(i).getNombre();
            columna[2] = resultado.get(i).getApellido();
            columna[3] = resultado.get(i).getDni();
            
            model.addRow(columna);
        }
        
    }

    
    
    public void limpiar(){
        vista.txtApellido.setText("");
        vista.txtDni.setText("");
        vista.txtNombre.setText("");
        
        PersonalBO nuevo = new PersonalBO(); 
        vista.txtId1.setText(String.valueOf(nuevo.nuevoId()));
        modoAlta();
    }
    
    
    
    public void modoAlta(){
        modo = "ALTA";
        vista.btnAgregar.setText("Agregar");
        vista.btnModificar.setText("Modificar");
        vista.btnEliminar.setText("Eliminar");
        vista.btnAgregar.setEnabled(true);
        vista.btnEliminar.setEnabled(false);
        vista.btnModificar.setEnabled(false);
    }
    
    public void modoModificacion(){
        modo = "MODIFICAR";
        vista.btnAgregar.setText("Aceptar");
        vista.btnModificar.setText("Cancelar");
        vista.btnEliminar.setText("");
        vista.btnAgregar.setEnabled(true);
        vista.btnModificar.setEnabled(true);
        vista.btnEliminar.setEnabled(false);
    }
    
    public boolean validacion(){
        if(vista.txtNombre.getText().length()==0){
            vista.txtNombre.requestFocus();
            JOptionPane.showMessageDialog(null,"Ingrese un nombre");
            return false;
        }
        if(vista.txtApellido.getText().length()==0){
            vista.txtApellido.requestFocus();
            JOptionPane.showMessageDialog(null,"Ingrese un apellido");
            return false;
        }

        if(vista.txtDni.getText().length()<7 || vista.txtDni.getText().length()>9){
            vista.txtDni.requestFocus();
            JOptionPane.showMessageDialog(null,"Ingrese un DNI entre 7 y 9 caracteres");
            return false;
        }
        try {
            Integer.parseInt(vista.txtDni.getText());
        } catch (NumberFormatException excepcion) {
            vista.txtDni.requestFocus();
            JOptionPane.showMessageDialog(null,"Ingrese un DNI válido");
            return false;
        }
        return true;
    }

}
