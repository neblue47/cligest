<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Pagamentos - Consultas</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Pagamentos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Pagamentos</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
	                    <div class="col-md-4 col-xs-12">
	                        <div class="white-box">
								<div class="cardbox">
			                    <div class="header">
			                        <h4 class="font-bold center">DADOS DO PACIENTE</h4>
			                    </div>
				                    <div class="body">
				                        <div class=" ">
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Nome</strong>
				                                        <p>${perfil.nome } ${perfil.nomem } ${perfil.apelido }</p>
				                                    </div>
				                                     
				                                </div>
				                                 <div class="row text-center m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Genero</strong>
				                                        <p>${perfil.nomegenero }</p>
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Idade</strong>
				                                        <p>${perfil.idade }</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Contacto</strong>
				                                        <p>${perfil.editfone }</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Morada</strong>
				                                        <p>${perfil.endereco }
				                                          </p>
				                                    </div>
				                                </div>
				                            </div>
				                    	</div>
			                		</div>
	                        	</div>
	                   	 </div>
                        <div class="col-md-8 col-xs-12">
                            <div class="card card-topline-red">
                                <div class="card-head">
                                    <header>Modelo de Pagamento</header>
                                </div>
                                <div class="card-body " id="bar-parent">
                                    <div class="row">
                                        <div class="col-md-3 col-sm-3 col-xs-3">
                                            <ul class="nav nav-tabs tabs-left" id="myTab">
                                                <li class="nav-item">
                                                    <a href="#tab_6_1" data-toggle="tab" class="active"> CASH  </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_2" data-toggle="tab"> MULTICAIXA </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_3" data-toggle="tab"> MULTIPLO </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <div class="tab-content nb-form">
                                                <div class="tab-pane active" id="tab_6_1">
                                                    <form action="#" method="post"  > 
											          <div class="widget-title">
												 		 <div  class="input-group-addon-ajust"> 
											      	   	 	 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Valor a Pagar (AKZ)</span>
											                 </div>
											                  <div class="form-group input-group">
											                      <input name = "mtPagar"  type="text" value="${pagmt.preco }" class="form-control remove-radius"  required="required" placeholder="AKZ 0,00" readonly="readonly"/>
											                 </div>
											                 
											                  <div class="form-group input-group">
											                      <input name = "mtreceber"  type="text"  class="form-control remove-radius"  required="required" placeholder="AKZ 0,00" />
											                 </div>
												        </div>
												        </div>
												         <div class="pull-right">
											            	 
											            	 
											            	<!-- Fim -->
											            	<button type="button" onclick="salvarFormCash()" class="btn btn-success btn-sm">
																  Pagar
															</button>
												            <a href="navegacaoft?mods=fat&pag=listacon"  class="btn btn-default btn-sm"  >
															      Cancelar
															</a>	  
											            </div>                    
											        </form>	
                                                </div>
                                                
                                                <div class="tab-pane fade" id="tab_6_2">
                                                   <form action="#" method="post"  > 
											          <div class="widget-title">
												 		 <div class="input-group-addon-ajust"> 
											      	   	 	 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Valor a Pagar (AKZ)</span>
											                 </div>
											                  <div class="form-group input-group">
											                      <input name = "mtPagar"  type="text" value="${pagmt.preco }" class="form-control remove-radius"  required="required" placeholder="AKZ 0,00" readonly="readonly"/>
											                 </div>
											                 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Instituição Bancária</span>
											                 </div>
											                 <div class="form-group input-group input-group-addon-ajust">
															    
												           		<select name="idBanco" id="banco" tabindex="1" required="required" class="form-control remove-radius"   required="required">
																	   <option value="">-- Seleccione o banco --</option>	
																	   <c:forEach var="at" items="${lsBancos }">
																	   		<option value="${at.id_banco }"> ${at.banco }</option>
																	   </c:forEach>																									
																</select>
															 </div> 
															 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Nº Cartão</span>
											                 </div>
											                  <div class="form-group input-group">
											                      <input name = "mtreceber"  type="text"  class="form-control remove-radius"  required="required" placeholder="9999" maxlength="4"/>
											                 </div>
												        </div>
												        </div>
												         <div class="pull-right">
											            	 
											            	 
											            	<!-- Fim -->
											            	<button type="button" onclick="salvarFormCash()" class="btn btn-success btn-sm">
																  Pagar
															</button>
												            <a href="navegacaoft?mods=fat&pag=listacon"  class="btn btn-default btn-sm"  >
															      Cancelar
															</a>	  
											            </div>                    
											        </form>
                                                </div>
                                                
                                                <div class="tab-pane fade" id="tab_6_3">
                                                    <form action="#" method="post"  > 
											          <div class="widget-title">
												 		 <div class="input-group-addon-ajust"> 
											      	   	 	 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Valor a Pagar (AKZ)</span>
											                 </div>
											                 <div class="form-group input-group">
											                      <input name = "mtPagar"  type="text"  value="${pagmt.preco }" class="form-control remove-radius"  required="required" placeholder="AKZ 0,00" readonly="readonly"/>
											                 </div>
											                 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Cash</span>
											                 </div>
											                  <div class="form-group input-group">
											                      <input name = "mtreceber"  type="text"  class="form-control remove-radius"  required="required" placeholder="9999" maxlength="4"/>
											                 </div>
											                 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Multicaixa</span>
											                 </div>
											                  <div class="form-group input-group">
											                      <input name = "mtreceber"  type="text"  class="form-control remove-radius"  required="required" placeholder="9999" maxlength="4"/>
											                 </div>
											                 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Instituição Bancária</span>
											                 </div>
											                 <div class="form-group input-group input-group-addon-ajust">
															    
												           		<select name="idBanco" id="banco" tabindex="1" required="required" class="form-control remove-radius"   required="required">
																	   <option value="">-- Seleccione o banco --</option>	
																	   <c:forEach var="at" items="${lsBancos }">
																	   		<option value="${at.id_banco }"> ${at.banco }</option>
																	   </c:forEach>																									
																</select>
															 </div> 
															 <div class="form-group input-group">
											           	  	      <span class="input-group-addon">Nº Cartão</span>
											                 </div>
											                  <div class="form-group input-group">
											                      <input name = "mtreceber"  type="text"  class="form-control remove-radius"  required="required" placeholder="9999" maxlength="4"/>
											                 </div>
												        </div>
												        </div>
												         <div class="pull-right">
											            	 
											            	 
											            	<!-- Fim -->
											            	<button type="button" onclick="salvarFormCash()" class="btn btn-success btn-sm">
																  Pagar
															</button>
												            <a href="navegacaoft?mods=fat&pag=listacon"  class="btn btn-default btn-sm"  >
															      Cancelar
															</a>	  
											            </div>                    
											        </form>
                                                </div>
                                                 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                       </div>
	                     
                </div>
     </div>
</div>
<!-- end page content -->
<style>

.nb-form .toggle {
  cursor: pointer;
  position: absolute;
  top: -0;
  right: -0;
  background: #33b5e5;
  width: 25px;
  height: 25px;
  margin: -5px 15px 0;
  color: #ffffff;
  font-size: 12px;
  line-height: 30px;
  text-align: center;
}
.nb-form .form {
  display: none;
  padding: 40px;
}
.nb-form .form:nth-child(2) {
  display: block;
}
</style>
<script>
$(document).on('click','.toggle',function(){ 
	'use strict';
  // Switches the Icon and form
  if($(this).children('i').attr('class')=='fa fa-user-plus')
  {
	  $(this).children('i').removeClass('fa-user-plus');
	  $(this).children('i').addClass('fa-times');
	  $('.formLogin').slideUp("slow");
	  $('.formRegister').slideDown("slow");
  }
  else
  {
	  $(this).children('i').removeClass('fa-times');
	  $(this).children('i').addClass('fa-user-plus');
	  $('.formLogin').slideDown("slow");
	  if($('.formRegister').is(':visible'))
	     $('.formRegister').slideUp("slow");
	  else
		 $('.formReset').slideUp("slow");
  }
  
});
</script>

<script>
$(function() {
	var op = false;
    $('a[data-toggle="tab"]').on('click', function(e) {
        window.localStorage.setItem('activeTab', $(e.target).attr('href'));
        op = true;
    });
   
    var activeTab = window.localStorage.getItem('activeTab');
    if (op) {
        $('#myTab a[href="' + activeTab + '"]').tab('show');
        window.localStorage.removeItem("activeTab");
    }
});
</script>
