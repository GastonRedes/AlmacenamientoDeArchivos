package gm.almacenamientodearchivos.modelo;

public class NombreInvalidoException extends RuntimeException{
    
    NombreInvalidoException(){
        
        super("Nombre invalido");
    }
}
