/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.materias;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Usuario
 */
public class Materias {

    public String id;
    public String codigo;
    public String nombre;
    public String docente;
    public String inicio;
    public String fin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
    
    
    public void InsertarMateria(
            JTextField paramcodigo, 
            JTextField paramnombre, 
            JTextField paramdocente, 
            JTextField paraminicio,
            JTextField paramfin
    ){
        setCodigo(paramcodigo.getText());
        setNombre(paramnombre.getText());
        setDocente(paramdocente.getText());
        setInicio(paraminicio.getText());
        setFin(paramfin.getText());
        conexion objetoConexion = new conexion();
        //insertamos en la base de datos y pasamos los valores
        String Consulta = "INSERT INTO materias(codigo, nombre, docente, inicio, fin) VALUES(?,?,?,?,?);";
        try{
            CallableStatement cs = objetoConexion.establecerConexcion().prepareCall(Consulta);
            cs.setString(1, getCodigo());
            cs.setString(2, getNombre());
            cs.setString(3, getDocente());
            cs.setString(4, getInicio());
            cs.setString(5, getFin());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se creo correctamente!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Hubo un error, error"+e.toString());
        }
    }
    public void MostrarTabla (JTable paramtabla){
        conexion objetoConexion = new conexion();
        DefaultTableModel model = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter <TableModel> (model);
        paramtabla.setRowSorter(OrdenarTabla);
        String sql = "";        
        model.addColumn("id");
        model.addColumn("Codigo");
        model.addColumn("Nombre");
        model.addColumn("Docente");
        model.addColumn("Inicio");
        model.addColumn("Fin");   
        sql = "select * from materias";
        String [] datos = new String[6];
        Statement st; //crea una conexion sql
        try{  
            st = objetoConexion.establecerConexcion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                model.addRow(datos);
            }
            paramtabla.setModel(model);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al mostrar registros: " + e.toString());
        }
    }

    
}

