<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
.user-btm-box {
    padding: 5px 0 10px;

</style>
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
			                		<div class=" ">
				                    	 <div class="center">
										   	  <button type="button" class="btn btn-success btn-sm" onclick="FinalizarConsulta(${param.codc})">
												    Finalizar a Consulta
											  </button>
													  
										 </div> 
										  <div class="center">
										   	  <button type="button" class="btn btn-default btn-sm" name="salvar">
												    Cancelar a Consulta
											  </button>
													  
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
                                                    <a href="#tab_6_1" data-toggle="tab" class="active"> Dados Triagem </a>
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
<!--                                                 <li class="nav-item"> -->
<!--                                                     <a href="#tab_6_8" data-toggle="tab"> Internamento </a> -->
<!--                                                 </li> -->
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
                                                   <jsp:include page="tab-sinais-triagem.jsp"/>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_2">
                                                <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
														<div >
														 	<span> Queixas Principais</span>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="descricao"class="form-control" required="required" rows="5" readonly="readonly">${queixas.queixa}</textarea>
												      		 </div>
														</div> 
														 <br>
													 	<div >
														 	<span> Historial da Doença Actual</span>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="historia" class="form-control" required="required" rows="5" readonly="readonly">${queixas.historial}</textarea>
												      		</div>
														</div> 
												
                                                </div> 
                                                <div class="form formRegister">
                                                    <jsp:include page="tab-queixas-hist-form.jsp" />
                                                </div>    
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_3">
                                                   <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                    <div >
													 	<span> Pessoais </span>
											  			<div id="agenda-perfil">	
											      		 	<textarea name="descricao" id="queixas1" class="form-control" required="required" rows="10" readonly="readonly"></textarea>
											      		 </div>
													</div> 
													<br>
												 	<div >
													 	<span> Familiar</span>
											  			<div id="agenda-perfil">	
											      		 	<textarea name="historia" id="historial" class="form-control" required="required" rows="10" readonly="readonly"></textarea>
											      		</div>
													</div> 
                                                    </div>
                                                     <div class="form formRegister">
                                                    		<jsp:include page="tab-antecedentes-form.jsp" />
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_4">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                     <div class="form formLogin">
	                                                     <div >
											       	   		<span>Exame Objectivo Geral</span>
											      		 	<textarea name="objectivo"  class="form-control" readonly="readonly">${exmFs.objectivo_geral  }</textarea>
											      		 </div>
											      		 <div >
											       	   		<span>Cabeca</span>
											      		 	<textarea name="cabecao"  class="form-control" readonly="readonly">${exmFs.exa_cabeca }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Pescoco</span>
											      		 	<textarea name="pescoco" class="form-control" readonly="readonly">${exmFs.exa_pescoco }</textarea>
											      		 </div>
											      		  <div>
											       	   		<span>Torax</span>
											      		 	<textarea name="torax"   class="form-control" readonly="readonly">${exmFs.exa_torax }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Abdomen</span>
											      		 	<textarea name="abdomem"  class="form-control" readonly="readonly">${exmFs.exa_abdomen }</textarea>
											      		 </div>	
											      		 <div>
											       	   		<span>Genito Urinário</span>
											      		 	<textarea name="urinatio"  class="form-control" readonly="readonly">${exmFs.exa_urinario }</textarea>
											      		 </div>
											      		 <div>
											       	   		<span>Membros Superiores</span>
											      		 	<textarea name="membrosup"   class="form-control" readonly="readonly">${exmFs.exa_membSup }</textarea>
											      		 </div>
											      		 
											      		  <div>
											       	   		<span>Membros Inferiores</span>
											      		 	<textarea name="membrosinf" id="membrosinf" class="form-control" readonly="readonly">${exmFs.exa_membInf }</textarea>
											      		 </div>
											      		<div >
											       	   		<span>Sistema Nervoso</span>
											      		 	<textarea name="sistema" id="sistema" class="form-control" readonly="readonly">${exmFs.sistema_nervoso }</textarea>
											      		 </div>
                                                     </div>
                                                     <div class="form formRegister">
                                                     	<jsp:include page="tab-examesFisicos-form.jsp" />
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_5">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                     <div class="card-head">
                                            			 Hipoteses de Doenças 
                                       				 </div>
                                                       <div class="table-scrollable">  
		                                                <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle">
		                                                    <thead>
		                                                        <tr >
		                                                            <th align="center">#</th>
		                                                            <th>CID</th>
		                                                            <th>Descrição</th>
		                                                        </tr>
		                                                    </thead>
		                                                    <tbody>
		                                                        <tr>
		                                                            <td align="center">1</td>
		                                                            <td>Mark</td>
		                                                            <td>Otto</td>
		                                                        </tr>
		                                                        <tr>
		                                                            <td align="center">2</td>
		                                                            <td>Jacob</td>
		                                                            <td>Thornton</td>
		                                                        </tr>
		                                                    </tbody>
		                                                </table>
		                                            </div>
		                                             
		                                             <div >
											       	   		<div class="card-head">
                                            			 	Observação 
                                       				 		</div>
											      		 	<textarea name="objectivo"  class="form-control" rows="10" readonly="readonly">${fisicos.objectivo_geral  }</textarea>
											        </div>
                                                    </div>
                                                     <div class="form formRegister">
                                                        <jsp:include page="tab-hipotese.jsp"/>	 
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_6">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                      <div class="card-head">
                                            			 Diagnósticos
                                       				 </div>
                                       				 <div class="table-scrollable">  
		                                                <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle">
		                                                    <thead>
		                                                        <tr >
		                                                            <th align="center">#</th>
		                                                            <th>Descrição da Doença</th>
		                                                        </tr>
		                                                    </thead>
		                                                    <tbody>
		                                                        <tr>
		                                                            <td align="center">1</td>
		                                                            <td>Otto</td>
		                                                        </tr>
		                                                        <tr>
		                                                            <td align="center">2</td>
		                                                            <td>Thornton</td>
		                                                        </tr>
		                                                    </tbody>
		                                                </table>
		                                                </div>
		                                             <div >
											       	   		<div class="card-head">
                                            			 	Observação 
                                       				 		</div>
											      		 	<textarea name="objectivo"  class="form-control" rows="10" readonly="readonly">${fisicos.objectivo_geral  }</textarea>
											        </div>
                                                    </div>
                                                     <div class="form formRegister">
                                       				      <jsp:include page="tab-diagnostico.jsp"/>
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_7">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i> </div>
                                                     
                                                    <div class="form formLogin">
                                                    <br> 
				                                	<table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle"  >
					                                        <thead>
					                                            <tr>
					                                            	<th class="center"> # </th>
					                                                <th> Descricao </th>
					                                                <th> Grupo Exame </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
																<tr class="odd gradeX">
																	<td class="center">1</td>
																	<td> Hemograma Completo</td>
																	<td> Exames Laboratorial</td>
																</tr>
																<tr class="odd gradeX">
																	<td class="center">2</td>
																	<td> Widal</td>
																	<td> Exames Laboratorial</td>
																</tr>
																<tr class="odd gradeX">
																	<td class="center">3</td>
																	<td> TAC</td>
																	<td> Exames Imagiologico</td>
																</tr>
																 
															</tbody>
					                                    </table>
                                                    
                                                    </div>
                                                     <div class="form formRegister">
                                                      <br> 
                                                    <div class="row  m-t-10">
				                                    <div class="col-lg-12  "><strong>Grupo de Exame</strong>
				                                    <select class="form-control  center" name="tipoExam"  >
                                                        <option  value="">Selecione...</option>
                                                        <c:forEach items="${tipoExames }" var="at">
                                                        <option  value="${at.id_grupo_analises_clinicas }">${at.grupo_danalise }</option>
                                                        </c:forEach>
                                                    </select>  
				                                    </div>
				                                 	
				                                	</div>
				                                	<br>
                                                    <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle"  id="examsTable">
					                                        <thead>
					                                            <tr>
					                                            	<th class="center"> # </th>
					                                                <th> Descricao </th>
					                                                <th class="center"> Opção </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
																<tr class="odd gradeX">
																	<td class="center">1</td>
																	<td> Hemograma Completo</td>
																	<td  class="center">
																		<input type="checkbox" name="">
																	</td>
																</tr>
																<tr class="odd gradeX">
																	<td class="center">2</td>
																	<td> Widal</td>
																	<td  class="center">
																		<input type="checkbox" name="">
																	</td>
																</tr>
																<tr class="odd gradeX">
																	<td class="center">3</td>
																	<td> TAC</td>
																	<td  class="center">
																		<input type="checkbox" name="id_exame">
																	</td>
																</tr>
																 
															</tbody>
					                                    </table>
					                                    <div class="card-head">
                                            			 	Observação 
                                       				 		</div>
											      		 	<textarea name="observacao"  class="form-control" rows="5"  ></textarea>
					                                    <p></p>
					                                    
													<input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
												    <input type="hidden" name="funInt" value="${usuario}">
												    <input type="hidden" name="conInt" value="${param.codc}">
													<div class="pull-right">
												   	  <button type="submit" class="btn btn-success btn-sm" name="salvar">
														    Gravar
													  </button>
													  <button type="button" class="btn btn-primary btn-sm" onclick="limparH()">
														  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
													  </button>
													</div> 
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_8">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                      INTERNAMENTO 
                                                    </div>
                                                     <div class="form formRegister">
													  INTERNAMENTO OUTRO
                                                     </div>
                                                </div>
                                                <div class="tab-pane fade" id="tab_6_9">
                                                    <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                      <div >
													 	<strong> Tratamento </strong>
											  			<div id="agenda-perfil">	
											      		 	<textarea name="descricao"   class="form-control" required="required" rows="10" readonly="readonly"></textarea>
											      		 </div>
													</div> 
													<br>
												 	<div >
													 	<strong>  Recomendações</strong>
											  			<div id="agenda-perfil">	
											      		 	<textarea name="historia"   class="form-control" required="required" rows="10" readonly="readonly"></textarea>
											      		</div>
													</div>
                                                    </div>
                                                     <div class="form formRegister">
                                                     <form name="form1" method="get" action="TratamentosController">
														<div >
														 	<strong> Tratamentos</strong>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="tratamento" id="tratamento" class="form-control" required="required" rows="10" >${queixa}</textarea>
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
														 	<strong> Recomendações</strong>
												  			<div id="agenda-perfil">	
												      		 	<textarea name="recomendacao" id="recomendacao" class="form-control" required="required" rows="10">${historial}</textarea>
												      		</div>
														</div> 
														<p></p>
														<input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
													    <input type="hidden" name="funInt" value="${usuario}">
													    <input type="hidden" name="conInt" value="${param.codc}">
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
                                                <div class="tab-pane fade" id="tab_6_10">
                                                   <div class="toggle pull-right"><i class="fa fa-user-plus"></i></div>
                                                    <div class="form formLogin">
                                                      <div class="card-head">
                                            			 Medicamentos 
                                       				 </div>
                                                       <div class="table-scrollable">  
		                                                <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle">
		                                                    <thead>
		                                                        <tr align="center">
		                                                            <th width="40%">Descricao</th>
		                                                            <th>Possologia</th>
		                                                            <th>Qtd</th>
		                                                            <th> </th>
		                                                        </tr>
		                                                    </thead>
		                                                    <tbody>
		                                                        <tr>
		                                                            <td>Mebocaino 500mg- Sharop</td>
		                                                            <td align="center">12h/12h</td>
		                                                            <td align="center">10</td>
		                                                            <td align="center"><a href="#" title="eliminar"><i class="fa fa-times" aria-hidden="true"></i></a></td>
		                                                        </tr>
		                                                    </tbody>
		                                                </table>
		                                            </div>
		                                             
		                                             <div >
											       	   		<div class="card-head">
                                            			 	Observação 
                                       				 		</div>
											      		 	<textarea name="objectivo"  class="form-control" rows="5" readonly="readonly">${fisicos.objectivo_geral}</textarea>
											        </div>
                                                    </div>
                                                     <div class="form formRegister">
                                                      <form class="row   m-t-10">
                                       				   <div class="card-head col-lg-12 col-md-12 col-sm-8 col-xs-12">
                                            			 Medicamentos 
                                       				   </div>
							                                <div class="col-lg-10 col-md-12 col-sm-8 col-xs-12  ">  
							                                   <input type="text" class="form-control " id="produto" placeholder="pesquiar por doenças no CID">  
							                                </div>
							                                <div class="col-lg-1 col-md-12 col-sm-2 col-xs-12"> 
							                                	<button type="button" class="btn btn-success btn-sm" onclick="addNoCarrinho()"> <i class="fa fa-plus"></i> </button>
							                                </div>
							                                
							                            </form>
                                                       <div class="table-scrollable">  
		                                                <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle" id="tableProd">
		                                                    <thead>
		                                                        <tr align="center">
		                                                            <th width="40%">Descricao</th>
		                                                            <th>Possologia</th>
		                                                            <th>Qtd</th>
		                                                            <th> </th>
		                                                        </tr>
		                                                    </thead>
		                                                    <tbody>
		                                                         
		                                                    </tbody>
		                                                </table>
		                                            </div>
		                                             
		                                             <div >
											       	   		<div class="card-head">
                                            			 	Observação 
                                       				 		</div>
											      		 	<textarea name="objectivo"  class="form-control" rows="5"  >${fisicos.objectivo_geral  }</textarea>
											        </div>
											        <p></p>
													<input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
												    <input type="hidden" name="funInt" value="${usuario}">
												    <input type="hidden" name="conInt" value="${conslt}">
													<div class="pull-right">
												   	  <button type="submit" class="btn btn-success btn-sm" name="salvar">
														    Gravar
													  </button>
													  <button type="button" class="btn btn-primary btn-sm" onclick="limparH()">
														  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
													  </button>
													</div>
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
<form action="AgendaConsultaController" method="get" id ="formFinalizar">
 <input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
 <input type="hidden" name="funInt" value="${usuario}">
 <input type="hidden" name="conInt" value="${param.codc}">
 <input type="hidden" name="acao" value="finalizar">
</form>
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
 <link href="assets/js-autocomplete/jquery-ui.css" rel="stylesheet" type="text/css"/>
 <link href="assets/js-autocomplete/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="assets/js-autocomplete/jquery-1.9.1.js"></script>
<script type="text/javascript" src="assets/js-autocomplete/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="assets/js-autocomplete/autocompletar.js"></script> 
<script>
 $(document).ready(function(){
	 $('select[name=tipoExam]').on('change',function(){
			$.ajax({
				type: 'GET',
				url : 'AjaxExameController',
				data: 'grExames='+$('select[name=tipoExam]').val(),
				success: function (dados){
					$("#examsTable tbody tr").remove();
					var pegados = dados.split(";");
					if(dados!='')
					{
						
						for(var i=0;i<pegados.length-1;i++)
						{
							$("#examsTable tbody")
							    .append("<tr>"+
		                                    "<td class='center'><div>"+(i+1)+"</div></td> "+
		                                    "<td class='left'><div>"+pegados[i].split("|")[1]+"</div></td> "+
		                                    "<td class='center' align='center'><div><input type='checkbox' name='id_exame' value='"+pegados[i].split("|")[0]+"'/> </div></td>"+
		                                "</tr>");
						}
				}
					
				}
			});		
		})
	});
 $(document).on('click','#trlinha',function(){
		 $(this).closest('tr').remove();
	 });
 
 function addNoCarrinho()
 {
		$.ajax({
			type: 'GET',
			url : 'AjaxCarrinhoController',
			data: 'addproduto='+$('#produto').val(),
			success: function (dados){
				if(dados!='')
				{
					$('#produto').val('');	
					$("#tableProd tbody")
						    .append("<tr class='center'>"+
	                                    "<td class='left'>"+dados+"<input type='hidden' name='produto' value="+dados+" class='form-control' style='width: 100px; text-align: center;'/> </td> "+
	                                    "<td class='center' align='center'><input type='text' name='possologia' class='form-control' style='width: 100%; text-align: center;'/> </td>"+
	                                    "<td class='center' align='center'><input type='text' name='qtd' class='form-control' style='width: 50px; text-align: center;'/> </td>"+
	                                    "<td class='center'><a href='#' title='eliminar' id='trlinha'><i class='fa fa-times' aria-hidden='true'></i></a></td>"+
	                                 "</tr>");
			    }
				
			}
		});	
 }
 

 function extrairAcentos(texto)
 {
	 var textoLimpo = texto.toLowerCase();
	 textoLimpo = textoLimpo.replace('á','a');  
     textoLimpo = textoLimpo.replace('à','a');  
     textoLimpo = textoLimpo.replace('ã','a');  
     textoLimpo = textoLimpo.replace('â','a');  
     textoLimpo = textoLimpo.replace('é','e');  
     textoLimpo = textoLimpo.replace('è','e');  
     textoLimpo = textoLimpo.replace('ê','e');  
     textoLimpo = textoLimpo.replace('í','i');  
     textoLimpo = textoLimpo.replace('ì','i');  
     textoLimpo = textoLimpo.replace('î','i');  
     textoLimpo = textoLimpo.replace('ó','o');  
     textoLimpo = textoLimpo.replace('ò','o');  
     textoLimpo = textoLimpo.replace('ô','o');  
     textoLimpo = textoLimpo.replace('õ','o');  
     textoLimpo = textoLimpo.replace('ú','u');  
     textoLimpo = textoLimpo.replace('ù','u');  
     textoLimpo = textoLimpo.replace('û','u');  
     textoLimpo = textoLimpo.replace('ü','u');  
     textoLimpo = textoLimpo.replace('ç','c'); 
	 return textoLimpo.toUpperCase();
 }
 function FinalizarConsulta(codcs)
 {
	  var enca = "";
	  swal({
		  title: "",
		  text: "Pretende finalizar a consulta?",
		  icon: "info",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
			$('#formFinalizar').submit();
		  }  
		  else{
			  location.href= enca; 
		  }
		});
	  
 }
 </script>
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
<!-- <script src="assets/js-tab/jquery-tab.min.js" ></script> -->
<!-- <script src="assets/js-tab/bootstrap-tab.min.js" ></script> -->
 <script  >
 $(function() {
	    $('a[data-toggle="tab"]').on('click', function(e) {
	        window.localStorage.setItem('activeTab', $(e.target).attr('href'));
	    });
	    var activeTab = window.localStorage.getItem('activeTab');
	    if (activeTab) {
	        $('#myTab a[href="' + activeTab + '"]').tab('show');
	       // window.localStorage.removeItem("activeTab");
	    }
	});
 
</script>

 
