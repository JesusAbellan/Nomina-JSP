<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Empleado</title>
<link rel="stylesheet" href="styles/general.css"></link>
</head>
<body>
	<h1>Editar Empleado</h1>
	<form action="empresa" method="post">
		<c:set var="empleado" value="${empleado}"></c:set>
		<input type="hidden" name="opcion" value="editar">
		<input type="hidden" name="dni" value="${empleado.dni}">
		<table border="1">
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50" value="${empleado.nombre}"></td>
			</tr>
			<tr>
				<td>Sexo:</td>
				<td><input type="text" name="sexo" size="50" value="${empleado.sexo}"></td>
			</tr>
			<tr>
				<td>Categoria:</td>
				<td><input type="text" name="categoria" size="50" value="${empleado.categoria}"></td>
			</tr>
			<tr>
				<td>Años Trabajados:</td>
				<td><input type="text" name="anyos" size="50" value="${empleado.anyos}"></td>
			</tr>
		</table>
		<input type="submit" value="Guardar">
	</form>
	<br>
	<button type="button" name="back" onclick="history.back()">Volver</button>
</body>
</html>