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
                                    <form action="#" id="form_sample_1" class="form-horizontal" method="post">
                                        <div class="form-body">
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Primeiro Nome
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="primeironome" data-required="1" placeholder="digite primeiro nome" class="form-control input-height" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Ultimo Nome
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="ultimonome" data-required="1" placeholder="digite ultimo nome" class="form-control input-height" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Data da Consulta
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                                <input class="form-control input-height" size="16" placeholder="data da consulta" type="text" value="">
                                                <span class="input-group-addon">&nbsp;<span class="fa fa-calendar"></span></span> 
                                            </div>
                                            <input type="hidden" id="dtp_input2" value="" />
                                                </div>
                                            </div>
                                           <div class="form-group row">
                                            <label class="control-label col-md-3">
                                            			A partir de
                                            			<span class="required"> * </span>
                                            </label>
                                            
                                            <div class="col-md-5">
	                                            	<div class=" ">
		                                                <input class="form-control input-height" type="time" value="13:45:00" id="example-time-input">
		                                            </div>
		                                              
                                            </div>
                                        </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Morada
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <textarea name="address" placeholder=" morada" class="form-control " rows="5" ></textarea>
                                                </div>
                                            </div>
                                             
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Contacto
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input name="number" type="text" placeholder="numero para contacto" class="form-control input-height" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Doutor
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="select">
                                                        <option value="">Selecione...</option>
                                                        <option value="Category 1">Dr. Rajesh</option>
                                                        <option value="Category 2">Dr. Sarah Smith</option>
                                                        <option value="Category 3">Dr. John Deo</option>
                                                        <option value="Category 3">Dr. Jay Soni</option>
                                                        <option value="Category 3">Dr. Jacob Ryan</option>
                                                        <option value="Category 3">Dr. Megha Trivedi</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Serviço
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="select">
                                                        <option value="">Selecione...</option>
                                                        <option value="Category 1">Consulta de Pediatria</option>
                                                        <option value="Category 2">Consulta de Oncologia</option>
                                                        <option value="Category 3">Consulta de Estomatologia</option>
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
                                                    <button type="button" class="btn btn-default">Cancelar</button>
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
 