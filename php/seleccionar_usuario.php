<?php

$mysqli = new mysqli("localhost", "root", "1234", "base_de_datos");
$sql = "SELECT * FROM usuario WHERE nombre = ? AND contraseña = ?";

$statement = $mysqli->prepare($sql);

$statement->bind_param("ss", $_POST["nombre"], $_POST["password"]);
$statement->execute();

if ($statement->fetch()) {

	echo "Ingreso correcto";
}
else {
	echo "Ingreso incorrecto";
}

$mysqli->close();

?>