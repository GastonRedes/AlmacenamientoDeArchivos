package modelo;

public class NombreInvalidoException extends RuntimeException{
    
    public NombreInvalidoException(){
        
        super("Nombre invalido");
    }
}
