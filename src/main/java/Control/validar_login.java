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
public class validar_login {
    
    UsuarioDAO user = new UsuarioDAO();
    
    public validar_login() {
    }
    
    
    public int verificarLogin( Usuario usuario) throws Exception{
        if (!verificarLongitudNombre(usuario.getName())){
            return -1;
        }
        if (!verificarLongitudContrasenia(usuario.getPassword())){
            return -2;
        }
        return user.leer(usuario);
    }
    
    public boolean verificarLongitudNombre( String nombre){
        return (nombre.length() > 4 && nombre.length()<= 30);
    }
    
    public boolean verificarLongitudContrasenia( String contrasenia){
        return (contrasenia.length() > 8 && contrasenia.length() <= 30);
    }

}
