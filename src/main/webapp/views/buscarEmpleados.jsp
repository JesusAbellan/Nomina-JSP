<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Buscar Empleados</title>
<link rel="stylesheet" href="styles/general.css"></link>
</head>
<body>
<error-page> 
     <exception-type>java.lang.Throwable</exception-type> 
     <location>/error.jsp</location> 
</error-page>
<h1>Buscar Empleados</h1>
	<form method="empresa">
		<input type="text" name="nombre"> 
		<input type="submit" name="opcion" value="buscarNombre"style="width:150px">
	</form>
	<form method="empresa">
		<input type="text" name="dni"> 
		<input type="submit" name="opcion" value="buscarDni" style="width:150px">
	</form>
	<form method="empresa">
		<input type="text" name="sexo"> 
		<input type="submit" name="opcion" value="buscarSexo" style="width:150px">
	</form>
	<form method="empresa">
		<input type="text" name="categoria"> 
		<input type="submit" name="opcion" value="buscarCategoria" style="width:150px">
	</form>
	<form method="empresa">
		<input type="text" name="anyos"> 
		<input type="submit" name="opcion" value="buscarAnyos" style="width:150px">
	</form>
	<br>
	<button type="button" name="back" class="back" onclick="history.back()">Volver</button>
</body>
</html>