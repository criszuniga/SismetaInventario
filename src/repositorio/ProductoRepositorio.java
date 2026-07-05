/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


import modelo.Producto; 
import excepciones.ProductoDuplicadoException;
/**
 *
 * @author Chess
 */
public class ProductoRepositorio {
    private List<Producto> productos;
    private Set<String> codigos;
    private int siguienteId = 1;
    //Constructor
    public ProductoRepositorio() {
    productos = new ArrayList<>();
    codigos = new HashSet<>();
    }
    
    //Agregar producto
    public void agregar(Producto p) throws ProductoDuplicadoException { 
        if (p == null) { 
            throw new IllegalArgumentException("El producto no puede ser null"); 
        } 
        String codigo = p.getCodigo();
        if (codigos.contains(codigo.toLowerCase())) {
            throw new ProductoDuplicadoException( "Ya existe un producto con ese código: " + codigo ); 
        } 
         p.setId(siguienteId);
        siguienteId++;
        productos.add(p); 
        codigos.add(codigo.toLowerCase()); 
    }
    //Listar productos
    public List<Producto> listar() { 
        return productos; 
    }
    //Buscar por codigo
    public Producto buscarPorCodigo(String codigo){
        for (Producto p : productos){
            if (p.getCodigo().equalsIgnoreCase(codigo)){
                return p;
            }
        }
        return null;
    }
    //Editar producto
    public boolean editar(Producto productoEditado){
        for (int i= 0; i < productos.size(); i++){
            Producto actual = productos.get(i);
            
            if(actual.getCodigo().equalsIgnoreCase(productoEditado.getCodigo())){
                productos.set(i, productoEditado);
                return true;
            }
        }
        return false;
    }
    //Eliminar producto
    public boolean eliminar(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                productos.remove(i);
                codigos.remove(codigo.toLowerCase());
                return true;
            }
        }
        return false;
    }
    //Verificar si existe codigo (usando Set = mas rapido)
    public boolean existeCodigo(String codigo) { 
        return codigos.contains(codigo.toLowerCase()); 
    }
    
}

