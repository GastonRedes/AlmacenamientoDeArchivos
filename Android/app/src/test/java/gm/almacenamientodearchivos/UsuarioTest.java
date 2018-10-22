package gm.almacenamientodearchivos;

import org.junit.Test;

import gm.almacenamientodearchivos.modelo.NombreInvalidoException;
import gm.almacenamientodearchivos.modelo.PasswordInvalidoException;
import gm.almacenamientodearchivos.modelo.Usuario;

import static org.junit.Assert.assertTrue;

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
