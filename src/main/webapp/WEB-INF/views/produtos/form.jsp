<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>
</head>
</head>
<body>

<%-- <form action="/casadocodigo_spring/produtos" method="post"> --%>
<%-- <form:form action="/casadocodigo/produtos" method="post" commandName="produto"><!-- commandName: formulário só trata de um produto especifico, não precisamos ficar repetindo a informação em todos os campos.podemos fazer uma referencia a qual entidade aquele formulário se refere --> --%>
<form:form action="${s:mvcUrl('PC#gravar').build()}" method="post" commandName="produto">    
 <!-- tag mvcUrl gera uma URL de acordo com um determinado controller. Se passarmos as iniciais PC para se referir a ProdutosController, o Spring já conseguirá fazer a relação entre os dois. -->   
    <div>
        <label>Título</label>        
        <input type="text" name="titulo" />
<%--         <form:errors path="produto.titulo" /> --%>
        <form:errors path="titulo" />
    </div>
    <div>
        <label>Descrição</label>        
        <textarea rows="10" cols="20" name="descricao"></textarea>
<%--         <form:errors path="produto.descricao" /> --%>
		<form:errors path="descricao" />
    </div>
    <div>
        <label>Páginas</label>       
        <input type="text" name="paginas" /><!-- as menaagens de erro estarão no arquivo messages.properties -->
<%--         <form:errors path="produto.paginas" /> <!-- msg de validação q vai retornar p/ o usuario caso não preencha corretamente o form --> --%>
    	<form:errors path="paginas" />
    </div>
    
    <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
        <div>
            <label>${tipoPreco}</label>
            <input type="text" name="precos[${status.index}].valor" />
            <input type="hidden" name="precos[${status.index}].tipo" value="${tipoPreco}" />
        </div>
    </c:forEach>
    
    <button type="submit">Cadastrar</button>
<%-- </form> --%>
</form:form>

</body>
</html>