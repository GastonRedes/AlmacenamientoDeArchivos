package persistencia;

import java.io.InputStream;
import modelo.Estado;
import modelo.Tipo;

public class Archivo {
    
    private Estado estado;
    private Tipo tipo; 
    private final String nombre;
    private final InputStream inputStream;
    
    public Archivo(String nombre, InputStream inputStream){
        
        this.estado = Estado.PRIVADO;
        this.tipo = Tipo.OTRO;
        this.nombre = nombre;
        this.inputStream = inputStream;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Estado getEstado() {
        return estado;
    }
    
    public Tipo getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
