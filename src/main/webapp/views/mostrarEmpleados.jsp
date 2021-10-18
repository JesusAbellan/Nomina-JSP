<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar todos los empleados</title>
<link rel="stylesheet" href="styles/general.css"></link>

</head>
<body>
<error-page> 
     <exception-type>java.lang.Throwable</exception-type> 
     <location>/error.jsp</location> 
</error-page>
	<h1>Empleados</h1>
	<table border="1">
		<tr>
			<th>Nombre Completo</th>
			<th>DNI</th>
			<th>Sexo</th>
			<th>Categoria</th>
			<th>Años Trabajados</th>
		</tr>
		<c:forEach var="empleado" items="${empleados}">
			<tr>
				<td><c:out value="${empleado.nombre}"></c:out></td>
				<td><a href="empresa?opcion=meditar&dni=<c:out value="${empleado.dni}"></c:out>"><c:out value="${empleado.dni}"></c:out></a></td>
				<td><c:out value="${empleado.sexo}"></c:out></td>
				<td><c:out value="${empleado.categoria}"></c:out></td>
				<td><c:out value="${empleado.anyos}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<button type="button" name="back" class="back" onclick="history.back()">Volver</button>
</body>
</html>