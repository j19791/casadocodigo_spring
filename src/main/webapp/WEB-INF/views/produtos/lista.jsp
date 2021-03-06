<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>
</head>
</head>
<body>
<h1>Lista de Produtos</h1>
<p> ${sucesso} </p>
<table border="1">
        <tr>
            <td>Título</td>
            <td>Descrição</td>
            <td>Páginas</td>
        </tr>

    <c:forEach items="${produtos}" var="produto">
        <tr>
            <%-- <td>${produto.titulo}</td> --%>
            <!-- método arg para adicionar um parâmetro a url -->
            <td><a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}">${produto.titulo}</a></td>
            <td>${produto.descricao}</td>
            <td>${produto.paginas}</td>
            
            
        </tr>
    </c:forEach>
    </table>
</body>
</html>