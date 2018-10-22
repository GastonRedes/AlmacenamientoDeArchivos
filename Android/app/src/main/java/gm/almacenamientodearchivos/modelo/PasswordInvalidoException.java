package gm.almacenamientodearchivos.modelo;

public class PasswordInvalidoException extends RuntimeException{
    
    PasswordInvalidoException(){
        
        super("Contraseña invalida");
    }
}
