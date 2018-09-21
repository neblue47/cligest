<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Novo Paciente</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Agendamento</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Pacientes</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>Dados do Paciente</header>
                                      
				                         
                                </div>
                                <div class="card-body" id="bar-parent">
                                    <form action="PacienteController" id="form_sample_1" class="form-horizontal" method="post" enctype="multipart/form-data">
                                        <div class="form-body">
                                        <div class="form-group row">
                                                <label class="control-label col-md-3">Primeiro Nome
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="primeiroNome" data-required="1" placeholder="digite o primeiro nome" class="form-control input-height" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Ultimo Nome
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="ultimoNome" data-required="1" placeholder="digite o ultimo nome" class="form-control input-height" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Data de Nascimento
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <div class="input-group date form_date " data-date="" data-date-format="dd/MM/yyyy" data-link-field="dtp_input2" data-link-format="dd/MM/yyyy">
		                                                <input name="dtNascido" class="form-control input-height" size="16" placeholder="data de nascimento" type="date" value="">&nbsp;
	                                            	</div>
	                                            </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Genero
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="genero" required="required">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach items="${lsGenero }" var="at">
                                                         <option value="${at.genero }" >${at.nomgenero }</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Estado Civil 
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="estadoCvl" required="required">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach items="${lsEstadoC }" var="at">
                                                         <option value="${at.est_civil }"  >${at.nomeEC }</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Grupo Sanguineo
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="gruposag" required="required">
                                                        <option value="0">Selecione...</option>
                                                        <option value="1">A+</option>
                                                        <option value="2">A-</option>
                                                        <option value="3">B+</option>
                                                        <option value="4">B-</option>
                                                        <option value="5">AB+</option>
                                                        <option value="6">AB-</option>
                                                        <option value="7">O+</option>
                                                        <option value="8">O-</option>	
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Tipo Documento
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="tpDocumento" required="required">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach items="${lsDoc }" var="at">
                                                         <option value="${at.tipo_doc }"   >${at.nomeDoc }</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Numero
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="numDocumento" data-required="1" placeholder="numero" class="form-control input-height" /> 
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Contacto
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input name="contacto" type="text" placeholder="numero para contacto" class="form-control input-height" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Email
                                                </label>
                                                <div class="col-md-5">
                                                    <div class="input-group">
                                                        
                                                        <input type="text" class="form-control input-height" name="email" placeholder="endereco electronico"> </div>
                                                </div>
                                            </div>
                                             <div class="form-group row">
                                                <label class="control-label col-md-3">Morada
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <textarea name="morada" placeholder="morada" class="form-control-textarea" rows="5" ></textarea>
                                                </div>
                                            </div>
                                            
                                             <div class="form-group row">
                                                <label class="control-label col-md-3"> Foto
                                                </label>
                                                <div class="compose-editor">
                                                  <input type="file" class="file-input" name="logotipo" id="foto" accept="image/*" >
                                              </div>
                                            </div>
											<div class="form-actions">
                                            <div class="row">
                                                <div class="offset-md-3 col-md-9">
                                                    <button type="submit" class="btn btn-info">Gravar</button>
                                                    <button type="button" class="btn btn-default">Cancelar</button>
                                                </div>
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
  
