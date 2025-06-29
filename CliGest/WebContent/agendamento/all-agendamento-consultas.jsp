<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
		          <jsp:include page="../shared/mensagem.jsp" />				  
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Lista de Agendamentos - Consultas</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Agendamentos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Lista de Agendamentos</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row"> 
                       
                    	<div class="col-md-12 col-sm-12">
                                 
                                <form action="navegacaoag" id="form_sample_1" class="card card-topline-red" method="get">
								<input type="hidden" name="mods" value ="ag">
								<input type="hidden" name="pag" value ="listagen">
                                <input type="hidden" name="acao" value ="psquisar">
                                <div class="card-head"> <header>Pesquisar</header></div>
                                <div class="card-body">
                                <div class="row">    
                                <div class="col-md-7">
									    <div class="form-group">
									        <label class="control-label">Paciente </label>
									        <input  name="nomPaciente" class="form-control"  value="${param.nomPaciente}" />
									    </div>
									    
								    </div>                            
								    <div class="col-md-5">
									    <div class="form-group " data-date="" data-date-format="dd/MM/yyyy" data-link-field="dtp_input2" data-link-format="dd/MM/yyyy">
									        <label class="control-label">Data Consulta</label>
                                            <input class="form-control  " size="10" placeholder="data da consulta" type="date" value="" name="dtConsulta" id="dtConsulta">  	 
									    </div>    
								    </div>
								    
								    <div class="col-md-7">
								    	<div class="form-group">
									        <label class="control-label">Doutor </label>
									        <input  name="nomDoutor" class="form-control" value="${param.nomDoutor}"   />
									    </div>
								    </div>
								    <div class="col-md-5">
								    	<div class="form-group">
									        <label class="control-label">Serviço </label>
									        <select name ="servico" class="form-control">
									        <option value="0">Selecione...</option>
                                            <c:forEach var="dc" items="${lsConsultas }">
                                           	<option value="${dc.id_servico }" <c:if test = "${dc.id_servico == param.servico}">selected</c:if>>  ${dc.servico }  </option>
                                           	</c:forEach>
									        </select>
									    </div>
								    </div>
							    </div>
							     
							</div>
							<!-- /.card-body -->
							<div class="card-footer">
							    <div class=" float-sm-right">
							        <a href="navegacaoag?mods=ag&pag=listagen"   class="btn btn-default">Cancelar</a>
							        <button type="submit" class="btn btn-info">Consultar</button>
							    </div>
							                        
							</div>
							
                       		</form>
                                
                            
                        </div> 
                        <div class="col-md-12">
                            <div class="card card-topline-red">
                                <div class="card-head">
                                    <header></header>
                                     
                                </div>
                                <div class="card-body ">
                                    <table class="table display" id="example1" style="width:100%;" data-page-length="5">
                                        <thead>
                                            <tr>
                                            	<th></th>
                                                <th class="left"> Paciente </th>
                                                <th class="center"> Data  </th>
                                                <th class="center"> Hora  </th>
                                                <th class="center"> Contacto </th>
                                                <th class="center"> Doutor </th>
                                                <th class="center">Serviço</th>
                                                <th class="center"> Opções </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="pc" items="${lsPaciente }">
                                        <tr class="odd gradeX">
												<td class="patient-img">
													 <c:if test="${pc.confirmado }">
														<a target="_blank" href="GeradoRelatorio?nomeRelat=ConsultasConfirmadaA5&cod_param=${pc.FK_consulta_confirmada}" class="btn btn-success btn-xs" title="Gerar Comprovativo">
		  													<i class="fa fa-print"></i> 
	 													 </a>
													 </c:if>
													 <c:if test="${not pc.confirmado }">
														<a target="_blank" href="GeradoRelatorio?nomeRelat=ConsultasAgendadaA5&cod_param=${pc.FK_consulta_marcada}" class="btn btn-warning btn-xs" title="Gerar Comprovativo">
		  													<i class="fa fa-print"></i> 
	 													 </a>
													 </c:if>
												</td>
												<td>${pc.nome_paciente}</td>
												<td class="center"><fmt:formatDate value="${pc.data_do_agendamento.time}" pattern="dd/MM/yyyy"/></td>
												<td class="center">${pc.hora_daconfirmacao}</td>
												<td class="center"> ${pc.telefonep} </td>
												<td>${pc.nome_doutor}</td>
												<td>${pc.servico}</td>
												<td class="center" width="15%">
													<a onclick="ConfirmaConsulta('${pc.FK_consulta_marcada}')" class="btn btn-success btn-xs" title="Confirmar">
														<i class="fa fa-check-circle"></i>
													</a>
													<a onclick="AlterarConsulta('${pc.FK_consulta_marcada}')" class="btn btn-warning btn-xs" title="Adiar">
														<i class="fa fa-times-circle-o"></i>
													</a>
													<a onclick="CancelAgenda('${pc.FK_consulta_marcada}')" class="btn btn-danger btn-xs" title="Cancelar">
														<i class="fa fa-times-circle-o"></i>
													</a>
													 
												</td>
											</tr>
                                        </c:forEach>
										</tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page content -->
            
            <script>
            function CancelAgenda(codcs)
            {
          	  var enca = "";
          	  swal({
          		  title: "",
          		  text: "Pretende cancelar a consulta?",
          		  icon: "warning",
          		  buttons: true,
          		  dangerMode: true,
          		})
          		.then((willDelete) => {
          		  if (willDelete) {
          			enca = "AgendaConsultaController?acao=cancel&codcs="+codcs;
          		  }  
          		  location.href= enca; 
          		});
          	  
            }
            
            function ConfirmaConsulta(codcs)
            {
          	  var enca = "";
          	  swal({
          		  title: "",
          		  text: "Pretende confirmar a consulta?",
          		  icon: "info",
          		  buttons: true,
          		  dangerMode: true,
          		})
          		.then((willDelete) => {
          		  if (willDelete) {
          			enca = "AgendaConsultaController?acao=confirmar&codcs="+codcs;
          		  }  
          		  location.href= enca; 
          		});
          	  
            }
            
            function AlterarConsulta(codcs)
            {
          	  var enca = "";
          	  swal({
          		  title: "",
          		  text: "Pretende alterar agendamento?",
          		  icon: "info",
          		  buttons: true,
          		  dangerMode: true,
          		})
          		.then((willDelete) => {
          		  if (willDelete) {
          			enca = "AgendaConsultaController?cod="+codcs;
          		  }  
          		  location.href= enca; 
          		});
          	  
            }
            </script>
         <!-- start js include path -->
    <script src="assets/jquery.min.js" ></script>
    <!-- data tables -->
    <script src="assets/datatables/jquery.dataTables.min.js" ></script>
 	<script src="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
    <script src="assets/table_data.js" ></script>
       