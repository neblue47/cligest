<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Editar Dados do Paciente</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Pacientes</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Editar Paciente</li>
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
                                    <form action="#" id="form_sample_1" class="form-horizontal" method="post">
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
		                                                <input class="form-control input-height" size="16" placeholder="data de nascimento" type="text" value="">&nbsp;
		                                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
	                                            	</div>
	                                            	<input type="hidden" id="dtp_input2" value="" />
	                                            </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Genero
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="genero">
                                                        <option value="">Selecione...</option>
                                                        <option value="1">Masculino</option>
                                                        <option value="2">Feminino</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Idade
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="idade" data-required="1" placeholder="idade" class="form-control input-height" /> </div>
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
                                                    <textarea name="address" placeholder="morada" class="form-control-textarea" rows="5" ></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Estado Civil 
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="estadoCvl">
                                                        <option value="">Selecione...</option>
                                                        <option value="1">Solterio (a)</option>
                                                        <option value="2">Divorciado (a)</option>
                                                        <option value="3">Casado (a)</option>
                                                        <option value="4">Uniao de Facto</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Gropo Sanguineo
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="gruposag">
                                                        <option value="">Selecione...</option>
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
                                                <label class="control-label col-md-3">Upload Foto
                                                </label>
                                                <div class="compose-editor">
                                                  <input type="file" class="default" multiple>
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