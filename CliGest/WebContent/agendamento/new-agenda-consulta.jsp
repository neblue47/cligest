<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Agendar Consulta</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Agendamento</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Agendar Consulta</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>Agendar Consulta</header>
                                </div>
                                <div class="card-body" id="bar-parent">
                                    <form action="AgendaConsultaController" id="form_sample_1" class="form-horizontal" method="post">
                                        <div class="form-body">
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Nome do Paciente
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="primeironome" data-required="1"  class="form-control input-height" value="${pac.nome } ${pac.apelido }" readonly="readonly" />
                                                    <input type="hidden" name="pac" value="${pac.FK_paciente}" readonly="readonly" /> 
                                                    <input type="hidden" name="usuario" value="${usuario}" readonly="readonly" /> 
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Data da Consulta
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <div class="input-group date form_date " data-date="" data-date-format="dd/MM/yyyy" data-link-field="dtp_input2" data-link-format="dd/MM/yyyy" >
                                                	<input class="form-control input-height" size="10" placeholder="data da consulta" type="date" value="" name="dtConsulta" id="dtConsulta">
                                                	 
                                            		</div>
                                                </div>
                                            </div>
                                           <div class="form-group row">
                                            <label class="control-label col-md-3">
                                            			A partir de
                                            			<span class="required"> * </span>
                                            </label>
                                            
                                            <div class="col-md-5">
	                                            	<div class=" ">
		                                                <input class="form-control input-height" type="time"   id="example-time-input" name="hrConsulta">
		                                            </div>
		                                              
                                            </div>
                                        </div>
                                             
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Contacto
                                                    
                                                </label>
                                                <div class="col-md-5">
                                                    <input name="number" type="text" placeholder="numero para contacto" class="form-control input-height" size="9" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Doutor
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="doutor" required="required">
                                                    <option value="">Selecione...</option>
                                                    <c:forEach var="dc" items="${lsDoutores }">
                                                    	<option value="${dc.FK_entidade }">Dr. ${dc.nome } ${dc.apelido }</option>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Consulta
                                                <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="servico" required="required">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach var="dc" items="${lsConsultas }">
                                                    	<option value="${dc.id_servico }">  ${dc.servico }  </option>
                                                    	</c:forEach>
                                                    </select>
                                                </div>
                                            </div>
											<div class="form-group row">
												<label class="control-label col-md-3">Note 
												</label>
												<div class="col-md-5">
													<textarea name="notas" class="form-control" placeholder=" notas sobre estado do paciente " rows="5"></textarea>
												</div>
											</div>
										</div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="offset-md-3 col-md-9">
                                                    <button type="submit" class="btn btn-info">Gravar</button>
                                                    
                                                    <button type="button" class="btn btn-default" onclick="CancelAgenda()">Cancelar</button>
                                                </div>
                                            </div> 
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page content -->
 
  
 <script>
  function CancelAgenda()
  {
	 
	  
  }
 </script>