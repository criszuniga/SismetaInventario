/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Chess
 */
public class ArchivoException extends Exception {
    public ArchivoException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
    public ArchivoException(String mensaje) {
    super(mensaje);
}
}
