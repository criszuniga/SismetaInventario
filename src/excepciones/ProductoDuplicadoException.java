/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Chess
 */
public class ProductoDuplicadoException extends Exception {
    public ProductoDuplicadoException (String mensaje, Throwable causa){
        super(mensaje, causa);
    }
    public ProductoDuplicadoException(String mensaje) {
    super(mensaje);
}
    
}
