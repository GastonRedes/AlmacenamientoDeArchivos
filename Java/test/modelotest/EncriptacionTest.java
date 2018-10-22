package modelotest;

import modelo.Encriptacion;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
