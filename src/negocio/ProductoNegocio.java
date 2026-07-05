/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;
import modelo.Producto;
import repositorio.ProductoRepositorio;
import excepciones.DatoInvalidoException;
import excepciones.ProductoDuplicadoException;

import java.util.*;
/**
 *
 * @author Chess
 */
public class ProductoNegocio {
    private ProductoRepositorio repositorio;
    private Map<String, Integer> productosPorCategoria;
    private Stack<String> historial;
    
    //Constructor
    public ProductoNegocio(){
        repositorio = new ProductoRepositorio();
        productosPorCategoria = new HashMap<>();
        historial = new Stack<>();
        
    }
    //validaciones 
    private void validarProducto(Producto p) throws DatoInvalidoException {
        if (p == null){
            throw new DatoInvalidoException("El producto no puede ser null");
            
        }
        
        if (p.getCodigo() == null || p.getCodigo().trim().isEmpty()) {
            throw new DatoInvalidoException("El código es obligatorio");
        }
        
        if (p.getNombre() == null || p.getNombre().trim().length() < 3) {
            throw new DatoInvalidoException("El nombre debe tener al menos 3 caracteres");
        }
        if (p.getPrecio() <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor a 0");
        }
        if (p.getCantidad() < 0) {
            throw new DatoInvalidoException("La cantidad no puede ser negativa");
            
        }
        if (p.getCategoria() == null || p.getCategoria().trim().isEmpty()) {
            throw new DatoInvalidoException("La categoría es obligatoria");
        }
        
    }
    //Agregar producto
    public void agregarProducto(Producto p)
            throws DatoInvalidoException, ProductoDuplicadoException {
        
        validarProducto(p);
        repositorio.agregar(p);
        
        //Actualizar categoria
        productosPorCategoria.put(
                p.getCategoria(),
                productosPorCategoria.getOrDefault(p.getCategoria(),0) + 1
        
        );
        historial.push("Se agrego: " + p.getCodigo());
        
        
    }
    //listar productos
    public List<Producto> listarProductos() {
        return repositorio.listar();
    }
    //Buscar producto
    public Producto buscarProducto(String codigo){
        return repositorio.buscarPorCodigo(codigo);
    }
    public List<Producto> buscar(String texto) {
    List<Producto> resultado = new ArrayList<>();
    for (Producto p : repositorio.listar()) {
        if (p.getCodigo().toLowerCase().contains(texto.toLowerCase()) 
                || p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
            resultado.add(p);
        }
    }
    return resultado;
    }
    //Editar producto
    public boolean editarProducto(Producto p) throws DatoInvalidoException {
        validarProducto(p);
        
        boolean editado = repositorio.editar(p);
        
        if (editado){
            historial.push("Se edito producto: " + p.getCodigo());
        }
        return editado;
    }
    //Eliminar Producto
    public boolean eliminarProducto(String codigo){
        Producto p = repositorio.buscarPorCodigo(codigo);
        
        if (p != null) {
            boolean eliminado = repositorio.eliminar(codigo);
            
            if(eliminado){
                //Actualizar categoria
                String categoria = p.getCategoria();
                productosPorCategoria.put (
                        categoria,
                        productosPorCategoria.get(categoria) - 1
                        
                );
                historial.push("Se elimino producto: " + codigo);
            }
            return eliminado;
            
        }
        return false;
    }
    //Obtener productos por categoria
    public Map<String, Integer> getProductosPorCategoria(){
        return productosPorCategoria;
        
    }
    //Historial
    public Stack<String> getHistorial(){
        return historial;
    }
    //Ordenar por nombre
    public void ordenarPorNombre(){
        Collections.sort(repositorio.listar(), Comparator.comparing(Producto::getNombre));
    }
    //Ordenar por precio
    public void ordenarPorPrecio(){
        Collections.sort(repositorio.listar(), Comparator.comparingDouble(Producto::getPrecio));
    }
    public void ordenarPorCantidad() {
    Collections.sort(repositorio.listar(), new Comparator<Producto>() {
        public int compare(Producto p1, Producto p2) {
            return Integer.compare(p1.getCantidad(), p2.getCantidad());
        }
    });
    }
    //total de productos
    public int totalProductos(){
        return repositorio.listar().size();
        
    }
    //Productos disponibles (cantidad>0)
    public long productoDisponibles(){
        return repositorio.listar()
              .stream()
              .filter(p-> p.getCantidad() > 0)
              .count();
            
        
    }
    //valor total del inventario
    public double valorInventario(){
        return repositorio.listar()
                .stream()
                .mapToDouble(p -> p.getPrecio()* p.getCantidad() )
                .sum();
    }
    public String generarReporteEstadisticas() {
    List<Producto> lista = repositorio.listar();
    
    int total = lista.size();
    int disponibles = 0;
    int noDisponibles = 0;
    int unidadesTotales = 0;
    double valorTotal = 0;
    Producto mayorPrecio = null;
    Producto menorPrecio = null;
    
    for (Producto p : lista) {
        if (p.isDisponible()) {
            disponibles++;
        } else {
            noDisponibles++;
        }
        unidadesTotales += p.getCantidad();
        valorTotal += p.getPrecio() * p.getCantidad();
        
        if (mayorPrecio == null || p.getPrecio() > mayorPrecio.getPrecio()) {
            mayorPrecio = p;
        }
        if (menorPrecio == null || p.getPrecio() < menorPrecio.getPrecio()) {
            menorPrecio = p;
        }
    }
    
    StringBuilder sb = new StringBuilder();
    sb.append("=== ESTADÍSTICAS DEL INVENTARIO ===\n\n");
    sb.append("Total de productos: ").append(total).append("\n");
    sb.append("Productos disponibles: ").append(disponibles).append("\n");
    sb.append("Productos no disponibles: ").append(noDisponibles).append("\n");
    sb.append("Unidades totales almacenadas: ").append(unidadesTotales).append("\n");
    
    if (mayorPrecio != null) {
        sb.append("Producto con mayor precio: ").append(mayorPrecio.getNombre())
          .append(" ($").append(mayorPrecio.getPrecio()).append(")\n");
        sb.append("Producto con menor precio: ").append(menorPrecio.getNombre())
          .append(" ($").append(menorPrecio.getPrecio()).append(")\n");
    }
    
    sb.append("\nProductos por categoría:\n");
    for (String categoria : productosPorCategoria.keySet()) {
        sb.append("  - ").append(categoria).append(": ").append(productosPorCategoria.get(categoria)).append("\n");
    }
    
    sb.append("\nValor total del inventario: $").append(valorTotal).append("\n");
    
    return sb.toString();
    }
    public List<Producto> filtrarPorCategoria(String categoria) {
    List<Producto> resultado = new ArrayList<>();
    for (Producto p : repositorio.listar()) {
        if (p.getCategoria().equalsIgnoreCase(categoria)) {
            resultado.add(p);
        }
    }
    return resultado;
    }
}
