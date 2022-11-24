/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.totoro.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.Connection;
import mx.itson.totoro.persistencia.Conexion;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author jesus
 */
public class Alumno {
    
    private int id;
    private String nombre;
    private String apellidos;
    private String idCia;
    private Date fechaNacimiento;
    private String apodo;
    
    public static List<Alumno> obtenerTodos() {
        List<Alumno> alumnos = new ArrayList<>();
        try{
           
           Connection conexion = Conexion.obtener();
           Statement statement = conexion.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT id, nombre, apellidos, idCia, fechaNacimiento, apodo FROM alumno");
           
           while(resultSet.next()){
               Alumno alumno = new Alumno();
               alumno.setId(resultSet.getInt(1));
               alumno.setNombre(resultSet.getString(2));
               alumno.setApellidos(resultSet.getString(3));
               alumno.setIdCia(resultSet.getString(4));
               alumno.setFechaNacimiento(resultSet.getDate(5));
               alumno.setApodo(resultSet.getString(6));
               
               alumnos.add(alumno);
               
           }
        } catch (Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return alumnos;
    }
    
    /**
     * 
     * @param nombre
     * @param apellidos
     * @param idCia
     * @param fechaNacimiento
     * @param apodo
     * @return Indica si se guardó o no el registro.
     */
    public static boolean guardar(String nombre, String apellidos, String idCia, Date fechaNacimiento, String apodo){
        boolean resultado = false;
        try {
            Connection conexion = Conexion.obtener();
            String consulta = "INSERT INTO alumno (nombre, apellidos, idCia, apodo) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nombre);
            statement.setString(2, apellidos);
            statement.setString(3, idCia);
            statement.setString(4, apodo);
            statement.execute();
            resultado = statement.getUpdateCount() == 1;
            conexion.close();
        } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return resultado;
    }
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the idCia
     */
    public String getIdCia() {
        return idCia;
    }

    /**
     * @param idCia the idCia to set
     */
    public void setIdCia(String idCia) {
        this.idCia = idCia;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the apodo
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * @param apodo the apodo to set
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
   

    
}
