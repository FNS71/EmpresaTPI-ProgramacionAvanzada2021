/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CboxItem;
import Modelo.ProyectoBO;
import Modelo.ProyectoVO;
import Vista.frmProyecto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class controladorProyecto implements ActionListener {

    private frmProyecto vista;
    private ProyectoBO modelo;
    
    private String modo = "ALTA";
    
    
    //MODOS: ALTA, MODIFICACION, BAJA
    
    public controladorProyecto(frmProyecto vista, ProyectoBO modeloBO){
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
                String descripcionGlobal = vista.txtDescripcionGlobal.getText();
                java.sql.Date fechaCarga = new java.sql.Date(vista.jdateFechaCarga.getDate().getTime());
                java.sql.Date fechaConfirmacion = new java.sql.Date(0);
                java.sql.Date fechaEntrega = new java.sql.Date(0);
                java.sql.Date fechaTermino = new java.sql.Date(0);
                if (vista.jdateFechaConfirmacion.getDate() != null){
                    fechaConfirmacion = new java.sql.Date(vista.jdateFechaConfirmacion.getDate().getTime());
                }
                if (vista.jdateFechaEntrega.getDate() != null){
                    fechaEntrega = new java.sql.Date(vista.jdateFechaEntrega.getDate().getTime());
                }
                if (vista.jdateFechaTermino.getDate() != null){
                    fechaTermino = new java.sql.Date(vista.jdateFechaTermino.getDate().getTime());
                }
                
                String observaciones = vista.txtObservaciones.getText();
                
                int idTipoProyecto = vista.cboxTipoProyecto.getItemAt(vista.cboxTipoProyecto.getSelectedIndex()).getId();
                int idCliente = vista.cboxCli.getItemAt(vista.cboxCli.getSelectedIndex()).getId();

                ProyectoVO per = new ProyectoVO(nombre, descripcionGlobal, fechaCarga, fechaConfirmacion, fechaEntrega, fechaTermino, observaciones, idTipoProyecto, idCliente);

                if(modelo.AlmacenarProyecto(per)==1){
                    JOptionPane.showMessageDialog(null,"Registro Exitoso");
                } else {
                    JOptionPane.showMessageDialog(null,"NO Registrado");
                }
                
                limpiar();
          }
          
           //MODIFICAR
          
          if (modo=="MODIFICAR") {
            
                int idProyecto = parseInt(vista.txtId1.getText());
                String nombre = vista.txtNombre.getText();
                String descripcionGlobal = vista.txtDescripcionGlobal.getText();
                java.sql.Date fechaCarga = new java.sql.Date(vista.jdateFechaCarga.getDate().getTime());
                java.sql.Date fechaConfirmacion = new java.sql.Date(0);
                java.sql.Date fechaEntrega = new java.sql.Date(0);
                java.sql.Date fechaTermino = new java.sql.Date(0);
                if (vista.jdateFechaConfirmacion.getDate() != null){
                    fechaConfirmacion = new java.sql.Date(vista.jdateFechaConfirmacion.getDate().getTime());
                }
                if (vista.jdateFechaEntrega.getDate() != null){
                    fechaEntrega = new java.sql.Date(vista.jdateFechaEntrega.getDate().getTime());
                }
                if (vista.jdateFechaTermino.getDate() != null){
                    fechaTermino = new java.sql.Date(vista.jdateFechaTermino.getDate().getTime());
                }
                String observaciones = vista.txtObservaciones.getText();
                int idTipoProyecto = vista.cboxTipoProyecto.getItemAt(vista.cboxTipoProyecto.getSelectedIndex()).getId();
                int idCliente = vista.cboxCli.getItemAt(vista.cboxCli.getSelectedIndex()).getId();

                ProyectoVO per = new ProyectoVO(idProyecto, nombre, descripcionGlobal, fechaCarga, fechaConfirmacion, fechaEntrega, fechaTermino, observaciones, idTipoProyecto, idCliente);

                if(modelo.ModificarProyecto(per)==1){
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
                int idProyecto = Integer.parseInt(vista.txtId1.getText());
                
                if(modelo.EliminarProyecto(idProyecto)==1){
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
              int fila = vista.tableProyecto.getSelectedRow();
              if(fila==-1){
                  JOptionPane.showMessageDialog(null,"Seleccione una fila");
              } else {
                    
                    vista.txtId1.setText(vista.tableProyecto.getValueAt(fila,0).toString());
                    vista.txtNombre.setText(vista.tableProyecto.getValueAt(fila,1).toString());
                    vista.txtDescripcionGlobal.setText(vista.tableProyecto.getModel().getValueAt(fila,2).toString());
                    try{
                        Date dateFechaCarga = new SimpleDateFormat("yyyy-MM-dd").parse(vista.tableProyecto.getValueAt(fila,3).toString());
                        vista.jdateFechaCarga.setDate(dateFechaCarga);
                    }catch (Exception ex){
                        vista.jdateFechaCarga.setCalendar(null);
                    }
                    try{
                        Date dateFechaConfirmacion = new SimpleDateFormat("yyyy-MM-dd").parse(vista.tableProyecto.getValueAt(fila,4).toString());
                        vista.jdateFechaConfirmacion.setDate(dateFechaConfirmacion);
                    }catch (Exception ex){
                        vista.jdateFechaConfirmacion.setCalendar(null);
                    }
                    try{
                        Date dateFechaEntrega = new SimpleDateFormat("yyyy-MM-dd").parse(vista.tableProyecto.getValueAt(fila,5).toString());
                        vista.jdateFechaEntrega.setDate(dateFechaEntrega);
                    }catch (Exception ex){
                        vista.jdateFechaEntrega.setCalendar(null);
                    }
                    try{
                        Date dateFechaTermino = new SimpleDateFormat("yyyy-MM-dd").parse(vista.tableProyecto.getValueAt(fila,6).toString());
                        vista.jdateFechaTermino.setDate(dateFechaTermino);
                    }catch (Exception ex){
                        vista.jdateFechaTermino.setCalendar(null);
                    }
                    vista.txtObservaciones.setText(vista.tableProyecto.getValueAt(fila,7).toString());
                    
                    for (int i = 0; i<vista.cboxTipoProyecto.getItemCount();i++){
                        if(vista.cboxTipoProyecto.getItemAt(i).getId() ==  parseInt(vista.tableProyecto.getValueAt(fila,8).toString())){
                            vista.cboxTipoProyecto.setSelectedIndex(i);
                        }
                    }
                    for (int i = 0; i<vista.cboxCli.getItemCount();i++){
                        if(vista.cboxCli.getItemAt(i).getId() ==  parseInt(vista.tableProyecto.getValueAt(fila,9).toString())){
                            vista.cboxCli.setSelectedIndex(i);
                        }
                    }
                  
              }
          }

    }
    
    private void tabla(){
        JTable tabla = vista.tableProyecto;
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tabla.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Descripción Global");
        model.addColumn("Fecha Carga");
        model.addColumn("Fecha Confirmación");
        model.addColumn("Fecha Entrega");
        model.addColumn("Fecha Terminó");
        model.addColumn("Observaciones");
        model.addColumn("idTipoProyecto");
        model.addColumn("idCliente");
        model.addColumn("Cliente");
        model.addColumn("Tipo Proyecto");
        
        Object[] columna = new Object[12];
        ArrayList<ProyectoVO> resultado = new ArrayList();
        resultado = modelo.buscarTodos();
        for (int i = 2; i<10; i++){
            tabla.getColumnModel().getColumn(i).setMinWidth(0);
            tabla.getColumnModel().getColumn(i).setMaxWidth(0);
            tabla.getColumnModel().getColumn(i).setWidth(0);
        }
        int numr = resultado.size();
        for (int i = 0; i<numr; i++){
            columna[0] = resultado.get(i).getIdProyecto();
            columna[1] = resultado.get(i).getNombre();
            columna[2] = resultado.get(i).getDescripcionGlobal();
            columna[3] = resultado.get(i).getFechaCarga();
            columna[4] = resultado.get(i).getFechaConfirmacion();
            columna[5] = resultado.get(i).getFechaEntrega();
            columna[6] = resultado.get(i).getFechaTermino();
            columna[7] = resultado.get(i).getObservaciones();
            columna[8] = resultado.get(i).getIdTipoProyecto();
            columna[9] = resultado.get(i).getIdCliente();
            columna[10] = getNombreCliente(resultado.get(i).getIdCliente());
            columna[11] = getNombreTipoProyecto(resultado.get(i).getIdTipoProyecto());
            
            model.addRow(columna);
        }
        

        
    }
    
    public String getNombreCliente(int id){
        ProyectoBO obj = new ProyectoBO();
        return obj.getNombreCliente(id);
    }
    
     public String getNombreTipoProyecto(int id){
        ProyectoBO obj = new ProyectoBO();
        return obj.getNombreTipoProyecto(id);
    }

    
    
    public void limpiar(){
        vista.txtNombre.setText("");
        vista.txtDescripcionGlobal.setText("");
        vista.jdateFechaCarga.setDate(new Date());
        vista.jdateFechaConfirmacion.setCalendar(null);
        vista.jdateFechaEntrega.setCalendar(null);
        vista.jdateFechaTermino.setCalendar(null);
        vista.txtObservaciones.setText("");
        vista.cboxCli.removeAllItems();
        vista.cboxTipoProyecto.removeAllItems();
        
        ProyectoBO nuevo = new ProyectoBO(); 
        vista.txtId1.setText(String.valueOf(nuevo.nuevoId()));
        nuevo.cargarComboCliente(vista.cboxCli);
        nuevo.cargarComboTipoProyecto(vista.cboxTipoProyecto); 
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
        if (vista.jdateFechaCarga.getDate() == null){
            vista.jdateFechaCarga.setDate(new Date()); // pone la fecha actual nomás, tecnicamente debería ser siempre la actual y no dejarte cambiar, pero ya que estamos dejamos cambiarla
        }
        if (vista.txtNombre.getText().length() == 0){
            vista.txtNombre.requestFocus();
            JOptionPane.showMessageDialog(null,"El campo nombre no puede estar vacío");
            return false;
        }
        
        
        return true;
    }
}
