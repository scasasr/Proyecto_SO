/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author mafec
 */
public class inventarioDAO {

    static final String DB_URL
            = "jdbc:mysql://database-1.ciqkauv4rtvh.us-east-2.rds.amazonaws.com:3306/Proyecto_SO";
    static final String DB_DRV
            = "com.mysql.jdbc.Driver";
    static final String DB_USER = "User_estandar";
    static final String DB_PASSWD = "Inventario_10";

    /*
    public boolean crear(Usuario object) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet2 = null;
        int resultSet;

      
        try {
            resultSet = -1;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet2 = statement.executeQuery("SELECT * FROM USUARIOS "
                    + "WHERE NOMBRE_USUARIOS = '" + object.getName()+ "'");
            if (resultSet2.next()) {
                return false;
            } else {
                resultSet = statement.executeUpdate("INSERT INTO `Proyecto_SO`.`USUARIOS`( `ID_USUARIOS`, `NOMBRE_USUARIOS`, `PASSWORD`) VALUES ('"
                        + object.getId() + "','" + object.getName() + "','" + ContraseniaHasheada.getSaltedHash(object.getPassword()) + "'" + ")");
                return resultSet > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex);
            return false;
        } 
    }
     */
    public String[][] leer() throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[][] mat = null;
        try {
            resultSet = null;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("call Proyecto_SO.get_inventario();");

            if (resultSet.next()) {
               mat=obtener_data(resultSet);
               return mat;
            }
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex);
            return null;
        }
        return mat;
    }

    public String[][] obtener_data(ResultSet rs) throws SQLException {
        int fila = 0;
        rs.afterLast();
        rs.previous();

        int tamanio = rs.getRow();
        rs.absolute(0);
        String[][] mat = new String[tamanio][5];
        rs.previous();
        while (rs.next()) {
            for(int i=1; i<=5;i++){
                mat[fila][i-1]=rs.getString(i);
            }
            fila++;
        }
        return mat;
    }
    
    public String[]leer_espe(String columna, String tabla) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String[] mat = null;
        try {
            resultSet = null;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT "+columna+" FROM Proyecto_SO."+tabla+";");

            if (resultSet.next()) {
               mat=obtener_data_vec(resultSet);
               return mat;
            }
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex);
            return null;
        }
        return mat;
    }
    
    public String[] obtener_data_vec(ResultSet rs) throws SQLException {
        rs.afterLast();
        rs.previous();
        int tamanio = rs.getRow();
        rs.absolute(0);
        String[]mat = new String[tamanio];
        rs.previous();
        int i=1;
        while (rs.next()) {
            mat[i-1]=rs.getString(1);
            i++;
        }
        return mat;
    }
    
    public int[] Disponible(String recurso,int id_uni) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int[] flag=new int[2];
        try {
            resultSet = null;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("call Proyecto_SO.Diponible('"+recurso+"',"+id_uni+");");
            if (resultSet.next()) {
                flag[0]=Integer.parseInt(resultSet.getString(1));
                flag[1]=1;
               return flag;
            }else{
                flag[0]=0;
                flag[1]=0;
                return flag;
            }
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex);
            flag[0]=0;
            flag[1]=-1;
            return flag;
        }
    }
  
    public boolean update(String Nombre, int cantidad, int categoria, String vencimiento) throws Exception {
        Connection connection = null;
        Statement statement = null;
        int resultSet;
        Calendar c = new GregorianCalendar();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        String modificacion = annio + '-' + mes + '-' + dia;

        try {
            resultSet = -1;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeUpdate("UPDATE `Proyecto_SO`.`RECURSOS` SET `NOMBRE_RECURSO` = '"+Nombre+"', `DISPONIBLE` = "+cantidad+
                                                " ,`ID_CATEGORIA` ="+categoria+" ,`FECHA_VENCI` = '"+vencimiento+"', `FECHA_MODIF` = '"+modificacion+
                                                "' WHERE `NOMBRE_RECURSO` = '"+Nombre+"';");
            return resultSet>0;
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex);
            return false;

        }
    }
}
