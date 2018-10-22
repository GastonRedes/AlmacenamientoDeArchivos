package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Encriptacion;
import modelo.Estado;
import modelo.Tipo;
import modelo.Usuario;

public class Persistencia {
    
    public Persistencia() throws ClassNotFoundException {
        
        Class.forName("com.mysql.jdbc.Driver");
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {

        Connection connection;
        String sql;
        PreparedStatement preparedStatement;
        Encriptacion encriptacion;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_de_datos", "root", "1234");

        sql = "INSERT INTO usuario (nombre, contraseña) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(sql);

        encriptacion = new Encriptacion();
        
        preparedStatement.setString(1, usuario.getNombre());
        preparedStatement.setString(2, encriptacion.getHash(usuario.getPassword()));

        if (preparedStatement.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Usuario agregado");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el usuario");
        }

        connection.close();
    }

    public void insertarArchivo(String usuario, Archivo archivo) throws SQLException {

        Connection connection;
        String sql;
        PreparedStatement preparedStatement;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_de_datos", "root", "1234");

        sql = "INSERT INTO archivo VALUES (?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, usuario);
        preparedStatement.setString(2, archivo.getEstado().toString());
        preparedStatement.setString(3, archivo.getTipo().toString());
        preparedStatement.setString(4, archivo.getNombre());
        preparedStatement.setBinaryStream(5, archivo.getInputStream());

        if (preparedStatement.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Archivo agregado");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo agregar el archivo");
        }

        connection.close();
    }

    public void actualizarArchivos(String usuario, String estado) throws SQLException {

        Connection connection;
        String sql;
        PreparedStatement preparedStatement;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_de_datos", "root", "1234");

        sql = "UPDATE archivo SET estado=? WHERE usuario=?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, estado);
        preparedStatement.setString(2, usuario);

        if (preparedStatement.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Archivos actualizados");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar los archivos");
        }

        connection.close();
    }

    public Usuario seleccionarUsuario(String nombre) throws SQLException {

        Connection connection;
        String sql;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Usuario usuario;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_de_datos", "root", "1234");

        sql = "SELECT * FROM usuario WHERE nombre=?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, nombre);

        resultSet = preparedStatement.executeQuery();

        usuario = null;

        if (resultSet.next()) {
            usuario = new Usuario(resultSet.getString("nombre"), resultSet.getString("contraseña"));
        }

        connection.close();

        return usuario;
    }

    public ArrayList<Archivo> seleccionarArchivos(String usuario) throws SQLException {

        ArrayList<Archivo> archivos;
        Archivo archivo;
        Connection connection;
        String sql;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        archivos = new ArrayList<>();

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_de_datos", "root", "1234");

        sql = "SELECT estado, tipo, nombre, contenido FROM archivo WHERE usuario=? OR estado='PUBLICO' ORDER BY estado, tipo, nombre";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, usuario);

        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            archivo = new Archivo(resultSet.getString("nombre"), resultSet.getBinaryStream("contenido"));
            archivo.setEstado(Estado.valueOf(resultSet.getString("estado")));
            archivo.setTipo(Tipo.valueOf(resultSet.getString("tipo")));

            archivos.add(archivo);
        }

        connection.close();

        return archivos;
    }

    public void borrarArchivos(String usuario) throws SQLException {

        Connection connection;
        String sql;
        PreparedStatement preparedStatement;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_de_datos", "root", "1234");

        sql = "DELETE FROM archivo WHERE usuario=?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, usuario);

        if (preparedStatement.executeUpdate() > 0) {
            JOptionPane.showMessageDialog(null, "Archivos borrados");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo borrar los archivos");
        }

        connection.close();
    }
}
