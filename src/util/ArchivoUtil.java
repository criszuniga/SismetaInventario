/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;
import modelo.Producto;
import excepciones.ArchivoException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Chess
 */
public class ArchivoUtil {
     public static void exportarCSV(List<Producto> productos, String rutaArchivo) throws ArchivoException {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write("ID,Codigo,Nombre,Categoria,Cantidad,Precio,Disponible\n");
            
            for (Producto p : productos) {
                writer.write(p.getId() + "," 
                        + p.getCodigo() + "," 
                        + p.getNombre() + "," 
                        + p.getCategoria() + "," 
                        + p.getCantidad() + "," 
                        + p.getPrecio() + "," 
                        + p.isDisponible() + "\n");
            }
            
        } catch (IOException e) {
            throw new ArchivoException("Error al exportar el archivo: " + e.getMessage(), e);
        }
    }
}
