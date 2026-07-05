/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Chess
 */
public class DatoInvalidoException extends Exception{
    public DatoInvalidoException(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
    public DatoInvalidoException(String mensaje) {
    super(mensaje);
    }
    
    
}
