<?php

$mysqli = new mysqli("localhost", "root", "1234", "base_de_datos");
$sql = "INSERT INTO archivo VALUES (?,?,?,?,?)";

$statement = $mysqli->prepare($sql);

$usuario = $_POST["usuario"];
$estado = $_POST["estado"];
$tipo = $_POST["tipo"];
$nombre = $_FILES["archivo"]["name"];
$contenido = file_get_contents($_FILES["archivo"]["tmp_name"]);

$statement->bind_param("sssss", $usuario, $estado, $tipo, $nombre, $contenido);

if ($statement->execute()) {

	echo "Archivo agregado";
}
else {
	echo "No se pudo agregar el archivo";
}

$mysqli->close();

?>