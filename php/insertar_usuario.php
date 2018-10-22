<?PHP

$mysqli = new mysqli("localhost", "root", "1234", "base_de_datos");
$sql = "INSERT INTO usuario VALUES (?,?)";

$statement = $mysqli->prepare($sql);

$statement->bind_param("ss", $_POST["nombre"], $_POST["password"]);

if ($statement->execute()) {

	echo "Usuario agregado";
}
else {
	echo "No se pudo agregar el usuario";
}

$mysqli->close();

?>