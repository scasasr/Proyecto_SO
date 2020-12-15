/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.UsuarioDAO;
import Entidad.Usuario;

/**
 *
 * @author mafec
 */
public class validar_registro {
    
    UsuarioDAO userDAO = new UsuarioDAO();
    Usuario usuario=new Usuario();
    
    public validar_registro() {
    }
    
    public int registro(int id, String user, String contrasenia, String confirmar) throws Exception{
        if (!verificarLongitudNombre(user)){
            return -1;
        }
        if (!verificarLongitudContrasenia(contrasenia)){
            return -2;
        }
        if(!confirmar_contrasenias(contrasenia,confirmar)){
            return -3;
        }
        usuario.setId(id);
        usuario.setName(user);
        usuario.setPassword(contrasenia);
        
        if(userDAO.crear(usuario)){
            return 1;
        }
        else{
            return 0;
        }      
    }
    
    public boolean confirmar_contrasenias(String contrasenia, String confirmar){
       return contrasenia.equals(confirmar);
    }
    public boolean verificarLongitudNombre( String nombre){
        return (nombre.length() > 4 && nombre.length()<= 30);
    }
    
    public boolean verificarLongitudContrasenia( String contrasenia){
        return (contrasenia.length() > 8 && contrasenia.length() <= 30);
    }

    
    
}
