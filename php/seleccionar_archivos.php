<?php

$mysqli = new mysqli("localhost", "root", "1234", "base_de_datos");
$sql = "SELECT estado, tipo, nombre, contenido FROM archivo WHERE usuario=? OR estado='PUBLICO' ORDER BY estado, tipo, nombre";

$statement = $mysqli->prepare($sql);

$statement->bind_param("s", $_POST["usuario"]);
$statement->execute();

$result = $statement->get_result();

$archivos = array();

while ($fila = $result->fetch_assoc()) {

	$archivo = array();

	$archivo["estado"] = $fila["estado"];
	$archivo["tipo"] = $fila["tipo"];
	$archivo["nombre"] = $fila["nombre"];
	$archivo["contenido"] = base64_encode($fila["contenido"]);

	$archivos[] = $archivo;
}

$mysqli->close();

echo json_encode(array("archivos" => $archivos));

?>