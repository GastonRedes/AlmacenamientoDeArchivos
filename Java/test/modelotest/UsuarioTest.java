package modelotest;

import modelo.NombreInvalidoException;
import modelo.PasswordInvalidoException;
import modelo.Usuario;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UsuarioTest {

    @Test
    public void testCrearUsuario() {

        Usuario usuario;

        usuario = new Usuario("nombre", "password");

        assertTrue(usuario.getNombre().equals("nombre"));
        assertTrue(usuario.getPassword().equals("password"));
    }

    @Test(expected = NombreInvalidoException.class)
    public void testCrearUsuarioConNombreInvalido() {
        
        Usuario usuario;
        
        usuario = new Usuario(" nom bre ", "password");
    }
    
    @Test(expected = PasswordInvalidoException.class)
    public void testCrearUsuarioConPasswordInvalido() {
        
        Usuario usuario;
        
        usuario = new Usuario("nombre", " pass word ");
    }
}
