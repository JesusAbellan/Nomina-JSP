<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Salario Específico</title>
<link rel="stylesheet" href="styles/general.css"></link>
</head>
<body action="empresa" method ="get">
	<c:set var="empleado" value="${empleado}"></c:set>
	<h1>Salario de ${empleado.nombre}</h1>
	<c:set var="salario" value="${salario}"></c:set>
	<input type="hidden" name="volver" value="/index.jsp">
	<table border ="1">
		<tr>
			<th>Empleado</th>
			<th>Salario</th>
		</tr>
		<tr>
			<td><c:out value="${empleado.nombre}"></c:out></td>
			<td><c:out value="${salario}"></c:out></td>
		</tr>
	</table>
	<button type="button" name="back" class="back" onclick="history.back()">Volver</button>
</body>
</html>