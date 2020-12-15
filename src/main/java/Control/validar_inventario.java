/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.inventarioDAO;

/**
 *
 * @author mafec
 */
public class validar_inventario {
    inventarioDAO dao=new inventarioDAO();
    
    public String[][] get_inventario() throws Exception{
        return dao.leer();
    }
    
    public String[] get_nameRecursos() throws Exception{
        return dao.leer_espe("NOMBRE_RECURSO", "RECURSOS");
    }
    
    public String[] get_nameUnidad() throws Exception{
        return dao.leer_espe("NOMBRE_UNIDAD", "UNIDAD_MEDIDA");
    }
    
    public String[] get_nameCategorias() throws Exception{
        return dao.leer_espe("NOMBRE_CATEGORIA", "CATEGORIAS");
    }
    
    public int[] get_disponible(String nombre,String id_unidad) throws Exception{
        return dao.Disponible(nombre, obtener_unidad(id_unidad));
    }
    
    public boolean update(String nombre,int cantidad,String categoria,String vencimiento) throws Exception{
        return dao.update(nombre, cantidad,obtener_categoria(categoria), vencimiento);
    }
    
    
    public int obtener_unidad(String unidad){
        if(unidad.equals("Kg")){
            return 1;
        }
        if(unidad.equals("g")){
            return 2;
        }
        if(unidad.equals("Lt")){
            return 3;
        }
        if(unidad.equals("u")){
            return 4;
        }
        return 0;
    }
    
    public int obtener_categoria(String categoria){
        if(categoria.equals("PERECEDEROS")){
            return 1;
        }
        if(categoria.equals("CARNICOS")){
            return 2;
        }
        if(categoria.equals("VEGETALES")){
            return 3;
        }
        if(categoria.equals("NO-PERECEDEROS")){
            return 4;
        }
        return 0;
    }
}
