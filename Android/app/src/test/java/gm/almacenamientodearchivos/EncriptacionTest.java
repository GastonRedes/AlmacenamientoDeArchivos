package gm.almacenamientodearchivos;

import org.junit.Test;

import gm.almacenamientodearchivos.modelo.Encriptacion;

import static org.junit.Assert.assertTrue;

public class EncriptacionTest {

    @Test
    public void testEncriptarPassword(){

        Encriptacion encriptacion;
        String hash;

        encriptacion = new Encriptacion();
        hash = encriptacion.getHash("password");

        assertTrue(hash.equals(encriptacion.getHash("password")));
    }
}
