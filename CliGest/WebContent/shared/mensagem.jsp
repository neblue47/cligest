<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty msgOK}">
	<div class="alert alert-success in">
		<strong>Salvo!</strong> Consulta confirmada com sucesso.
	</div>	
	<c:remove var="msgOK"/>          
</c:if>

<c:if test="${not empty msgOk}">
	<div class="alert alert-success in">
	   <strong>Salvo!</strong> Consulta agendada com sucesso.
	 </div>
	 <c:remove var="msgOk"/> 
</c:if>

<c:if test="${not empty msgNOK}">
	<div class="alert alert-danger out">
   		<strong>Erro!</strong> Ocorreu um problema durante o processamento, contactar o Administrador do sistema.
   </div>
   <c:remove var="msgNOK"/> 
</c:if>		          
				  
				  