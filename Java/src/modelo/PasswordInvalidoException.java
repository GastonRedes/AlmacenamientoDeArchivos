package modelo;

public class PasswordInvalidoException extends RuntimeException{
    
    public PasswordInvalidoException(){
        
        super("Contraseña invalida");
    }
}
