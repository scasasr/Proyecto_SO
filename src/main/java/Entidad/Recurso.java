/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author mafec
 */
public class Recurso {
    String name;
    int disponible;
    String u_medida;
    int minimo;
    String Categoria;

    public Recurso(String name, int disponible, String u_medida, int minimo, String Categoria, String fecha_venc) {
        this.name = name;
        this.disponible = disponible;
        this.u_medida = u_medida;
        this.minimo = minimo;
        this.Categoria = Categoria;
        this.fecha_venc = fecha_venc;
    }

    public Recurso() {
    }

    public Recurso(String name, int disponible, String u_medida, String fecha_venc) {
        this.name = name;
        this.disponible = disponible;
        this.u_medida = u_medida;
        this.fecha_venc = fecha_venc;
    }
    String fecha_venc;

    public Recurso(String name, int disponible, String u_medida, String Categoria, String fecha_venc) {
        this.name = name;
        this.disponible = disponible;
        this.u_medida = u_medida;
        this.Categoria = Categoria;
        this.fecha_venc = fecha_venc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getU_medida() {
        return u_medida;
    }

    public void setU_medida(String u_medida) {
        this.u_medida = u_medida;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getFecha_venc() {
        return fecha_venc;
    }

    public void setFecha_venc(String fecha_venc) {
        this.fecha_venc = fecha_venc;
    }
     
  
    
}
