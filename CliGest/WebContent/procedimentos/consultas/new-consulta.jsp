<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Consulta</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Procedimentos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Consulta</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
	                    <div class="col-md-4 col-xs-12">
	                        <div class="white-box">
								<div class="cardbox">
			                    <div class="header">
			                        <h4 class="font-bold">DADOS DO PACIENTE</h4>
			                    </div>
				                    <div class="body">
				                        <div class=" ">
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Nome</strong>
				                                        <p>Alexandre João</p>
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Ocupação</strong>
				                                        <p>Engenheiro</p>
				                                    </div>
				                                </div>
				                                 <div class="row text-center m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Genero</strong>
				                                        <p>Masculino</p>
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Idade</strong>
				                                        <p>2 Anos e 7 Meses</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Contacto</strong>
				                                        <p>244 9234 044 415</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Morada</strong>
				                                        <p>345, Sarju Appt., Mota Varacha, Surat
				                                            <br> Gujarat, India.</p>
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
                                    <header>CONSULTA</header>
                                </div>
                                <div class="card-body " id="bar-parent">
                                    <div class="row">
                                        <div class="col-md-3 col-sm-3 col-xs-3">
                                            <ul class="nav nav-tabs tabs-left"  id="myTab">
                                                <li class="nav-item">
                                                    <a href="#tab_6_1" data-toggle="tab" class="active"> Sinais e Dados </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_2" data-toggle="tab"> Queixas e Historial </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_3" data-toggle="tab"> Antecedentes </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_4" data-toggle="tab"> Exame Fisico </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_5" data-toggle="tab"> Hipotese </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_6" data-toggle="tab"> Diagnostico </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_7" data-toggle="tab"> Exame Clinico </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_8" data-toggle="tab"> Internamento </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_9" data-toggle="tab"> Tratamento e Recomendacoes </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_10" data-toggle="tab"> Receituario </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a href="#tab_6_11" data-toggle="tab"> Transferencia e Justificativo </a>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                            <div class="tab-content nb-form">
                                                <div class="tab-pane active" id="tab_6_1">
                                                    <p>Sinais e Dados</p>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_2">
                                                <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
														<div >
														 	<span> Queixas Principais</span>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="descricao" id="queixas1" class="form-control" required="required" rows="5" readonly="readonly">${queixa}</textarea>
												      		 </div>
														</div> 
														 <br>
													 	<div >
														 	<span> Historial da Doença Actual</span>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="historia" id="historial" class="form-control" required="required" rows="5" readonly="readonly">${historial}</textarea>
												      		</div>
														</div> 
												
                                                </div> 
                                                <div class="form formRegister">
                                                    <form name="form1" method="post" action="queixahistorcontroller">
														<div >
														 	<span> Queixas Principais</span>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="descricao" id="queixas1" class="form-control" required="required" rows="5" >${queixa}</textarea>
												      		 </div>
														</div> 
														<p></p>
														<div class="pull-right">
														  <button type="button" class="btn btn-primary btn-sm" onclick="limparQ()">
															  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
														  </button>
														</div>	
														<br>
														 
													 	<div >
														 	<span> Historial da Doença Actual</span>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="historia" id="historial" class="form-control" required="required" rows="5">${historial}</textarea>
												      		</div>
														</div> 
														<p></p>
														<div class="pull-right">
													   	  <button type="submit" class="btn btn-success btn-sm" name="salvar">
															    Gravar
														  </button>
														  <button type="button" class="btn btn-primary btn-sm" onclick="limparH()">
															  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
														  </button>
														</div>	
												</form>
                                                </div>    
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_3">
                                                   <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    Texto aqui
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_4">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                     <div class="form formLogin">
	                                                     <div >
											       	   		<span>Exame Objectivo Geral</span>
											      		 	<textarea name="objectivo" id="objectivo" class="form-control" readonly="readonly">${fisicos.objectivo_geral  }</textarea>
											      		 </div>
											      		 <div >
											       	   		<span>Cabeca</span>
											      		 	<textarea name="cabecao" id="cabecao" class="form-control" readonly="readonly">${fisicos.exa_cabeca }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Pescoco</span>
											      		 	<textarea name="pescoco" id="pescoco" class="form-control" readonly="readonly">${fisicos.exa_pescoco }</textarea>
											      		 </div>
											      		  <div>
											       	   		<span>Torax</span>
											      		 	<textarea name="torax" id="torax" class="form-control" readonly="readonly">${fisicos.exa_torax }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Abdomen</span>
											      		 	<textarea name="abdomem" id="abdomem" class="form-control" readonly="readonly">${fisicos.exa_abdomen }</textarea>
											      		 </div>	
											      		 <div>
											       	   		<span>Genito Urinário</span>
											      		 	<textarea name="urinatio" id="urinatio" class="form-control" readonly="readonly">${fisicos.exa_urinario }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Membros Superiores</span>
											      		 	<textarea name="membrosup" id="membrosup" class="form-control" readonly="readonly">${fisicos.exa_membSup }</textarea>
											      		 </div>
											      		 
											      		  <div>
											       	   		<span>Membros Inferiores</span>
											      		 	<textarea name="membrosinf" id="membrosinf" class="form-control" readonly="readonly">${fisicos.exa_membInf }</textarea>
											      		 </div>
											      		<div >
											       	   		<span>Sistema Nervoso</span>
											      		 	<textarea name="sistema" id="sistema" class="form-control" readonly="readonly">${fisicos.sistema_nervoso }</textarea>
											      		 </div>
                                                     </div>
                                                     <div class="form formRegister">
                                                     	<div >
											       	   		<span>Exame Objectivo Geral</span>
											      		 	<textarea name="objectivo" id="objectivo" class="form-control" >${fisicos.objectivo_geral  }</textarea>
											      		 </div>
											      		 <div >
											       	   		<span>Cabeca</span>
											      		 	<textarea name="cabecao" id="cabecao" class="form-control">${fisicos.exa_cabeca }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Pescoco</span>
											      		 	<textarea name="pescoco" id="pescoco" class="form-control">${fisicos.exa_pescoco }</textarea>
											      		 </div>
											      		  <div>
											       	   		<span>Torax</span>
											      		 	<textarea name="torax" id="torax" class="form-control">${fisicos.exa_torax }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Abdomen</span>
											      		 	<textarea name="abdomem" id="abdomem" class="form-control">${fisicos.exa_abdomen }</textarea>
											      		 </div>	
											      		 <div>
											       	   		<span>Genito Urinário</span>
											      		 	<textarea name="urinatio" id="urinatio" class="form-control">${fisicos.exa_urinario }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Membros Superiores</span>
											      		 	<textarea name="membrosup" id="membrosup" class="form-control">${fisicos.exa_membSup }</textarea>
											      		 </div>
											      		 
											      		  <div>
											       	   		<span>Membros Inferiores</span>
											      		 	<textarea name="membrosinf" id="membrosinf" class="form-control">${fisicos.exa_membInf }</textarea>
											      		 </div>
											      		<div >
											       	   		<span>Sistema Nervoso</span>
											      		 	<textarea name="sistema" id="sistema" class="form-control">${fisicos.sistema_nervoso }</textarea>
											      		 </div>
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_5">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    Texto aqui
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_6">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    Texto aqui
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_7">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i> </div>
                                                     
                                                    <div class="form formLogin">
                                                    <br> 
                                                    <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle"  >
					                                        <thead>
					                                            <tr>
					                                            	<th class="center"> <i class="fa fa-calendar"></i> </th>
					                                                <th class="center"> Descricao </th>
					                                                <th class="center"> Grupo </th>
					                                                <th class="center"> Opções </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
																<tr class="odd gradeX">
																	<td class="center"></td>
																	<td class="center"></td>
																	<td>Alexandre Alberto Joao</td>
																	<td  class="center">
																		<a href="navegacaopd?mods=pd&pag=newcons" class="btn btn-primary btn-xs">
																			<i class="fa fa-heartbeat"></i>
																		</a>
																	</td>
																</tr>
																 
															</tbody>
					                                    </table>i
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_8">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    Texto aqui
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_9">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    Texto aqui
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_10">
                                                   <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    Texto aqui
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_11">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    Texto aqui
                                                    </div>
                                                     <div class="form formRegister">
                                                     outro textoa qui
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
//Toggle Function
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
<script src="assets/js-tab/jquery-tab.min.js" ></script>
<script src="assets/js-tab/bootstrap-tab.min.js" ></script>
 <script  >
 $(function() {
	    $('a[data-toggle="tab"]').on('click', function(e) {
	        window.localStorage.setItem('activeTab', $(e.target).attr('href'));
	    });
	    var activeTab = window.localStorage.getItem('activeTab');
	    if (activeTab) {
	        $('#myTab a[href="' + activeTab + '"]').tab('show');
	        window.localStorage.removeItem("activeTab");
	    }
	});
</script>

 
