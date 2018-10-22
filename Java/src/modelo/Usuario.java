package modelo;

public class Usuario {

    private String nombre, password;

    public Usuario(String nombre, String password) throws NombreInvalidoException, PasswordInvalidoException {
        
        if (nombre == null || nombre.length() == 0 || nombre.indexOf(' ') != -1) {
            throw new NombreInvalidoException();
        }

        if (password.length() == 0 || password.indexOf(' ') != -1) {
            throw new PasswordInvalidoException();
        }
        
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {

        return nombre;
    }

    public String getPassword() {

        return password;
    }
}
