
package DAO;

//import Control.ContraseniaHasheada;
//import Control.encriptadorAES;
import Control.ContraseniaHasheada;
import Control.encriptadorAES;
import Entidad.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mafec
 */
public class UsuarioDAO {
    
    ContraseniaHasheada contrasenia=new ContraseniaHasheada();
    //encriptadorAES AES= new encriptadorAES();
    
    static final String DB_URL
            = "jdbc:mysql://database-1.ciqkauv4rtvh.us-east-2.rds.amazonaws.com:3306/Proyecto_SO"; 
    static final String DB_DRV
            = "com.mysql.jdbc.Driver";
    static final String DB_USER = "User_estandar";
    static final String DB_PASSWD = "Inventario_10";
   
   
    
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
     
    public int leer(Usuario par) throws Exception {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            resultSet = null;
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM `Proyecto_SO`.`USUARIOS`"
                    + "WHERE NOMBRE_USUARIOS = '" + par.getName() + "'");

            if (resultSet.next()) {
                if (ContraseniaHasheada.check(par.getPassword(), resultSet.getString(3))) {
                    return 1;
                } else {
                    return -9;
                }
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex);
            return -3;
        }
    }
    
}
