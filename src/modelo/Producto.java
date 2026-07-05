/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Chess
 */
public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precio;
    private boolean disponible;
    private String descripcion;
    

//Constructor
public Producto(String codigo, String nombre, String categoria, int cantidad, 
                 double precio, boolean disponible, String descripcion) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.categoria = categoria;
    this.cantidad = cantidad;
    this.precio = precio;
    this.disponible = disponible;
    this.descripcion = descripcion;
}
//Getters y setters 
public int getId(){
    return id;
}
public void setId(int id){
    this.id = id;
}

public String getNombre(){
    return nombre;
}

public void setNombre(String nombre){
    this.nombre = nombre;
}

public int getCantidad(){
    return cantidad;
}
public void setCantidad(int cantidad){
    this.cantidad = cantidad;
}

public String getCategoria(){
    return categoria;
}
public void setCategoria(String categoria){
    this.categoria= categoria;
}

public double getPrecio(){
    return precio;
}

public void setPrecio (double precio){
    this.precio = precio;
}

public String getDescripcion(){
    return descripcion;
}
public void setDescripcion(String descripcion){
    this.descripcion = descripcion;
}

public String getCodigo(){
    return codigo;
}
public void setCodigo(String codigo){
    this.codigo = codigo;
}

public boolean isDisponible(){
    return disponible;
}
public void setDisponible(boolean disponible){
    this.disponible = disponible;
}

@Override
public String toString() {
    return "Producto{id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", categoria=" + categoria +", cantidad=" + cantidad + ", precio=" + precio + ", disponible=" + disponible + ", descripcion=" + descripcion + "}";
}
    
}
