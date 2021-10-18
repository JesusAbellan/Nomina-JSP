<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%Class.forName("com.mysql.cj.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar todos los empleados</title>
</head>
<body>
	<h1>Empleados</h1>
	<tr>
		<td>Nombre Completo</td>
		<td>DNI</td>
		<td>Sexo</td>
		<td>Categoria</td>
		<td>Años Trabajados</td>
	</tr>
	<c:forEach var="empleado" items= "${empleados}">
	<tr>
		<td>
			<a href="empresa?opcion=meditar&dni=<c:out value="${empleado.dni}"></c:out>"></a>
		</td>
		<td><c:out value= "${empleado.nombre}"></c:out></td>
		<td><c:out value= "${empleado.dni}"></c:out></td>
		<td><c:out value= "${empleado.sexo}"></c:out></td>
		<td><c:out value= "${empleado.categoria}"></c:out></td>
		<td><c:out value= "${empleado.anyos}"></c:out></td>
	</tr>
	</c:forEach>
</body>
</html>