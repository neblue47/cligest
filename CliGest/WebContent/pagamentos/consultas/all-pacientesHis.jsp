<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">			  
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Lista de Pacientes</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Pagamentos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Lista de Pacientes</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card card-topline-red">
                                <div class="card-head">
                                    <header></header>
                                     
                                </div>
                                <div class="card-body ">
                                    <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle" id="example4">
                                        <thead>
                                            <tr>
                                            	<th><i class="fa fa-history"></i></th>
                                                <th class="center"> Nome </th>
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
														<span   class="btn btn-success btn-xs" title="Consulta Agendada">
															<i class="fa fa-times-circle-o"></i>
														</span>
													 </c:if>
													 <c:if test="${not pc.confirmado }">
														<span   class="btn btn-warning btn-xs" title="Consulta Agendada">
															<i class="fa fa-times-circle-o"></i>
														</span>
													 </c:if>
												</td>
												<td>${pc.nome_paciente}</td>
												<td class="center"><fmt:formatDate value="${pc.data_do_agendamento.time}" pattern="dd/MM/yyyy"/></td>
												<td class="center">${pc.hora_daconfirmacao}</td>
												<td class="center"> ${pc.telefonep} </td>
												<td>Dr. ${pc.nome_doutor}</td>
												<td>${pc.servico}</td>
												<td class="center">
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
          			//enca = "AgendaConsultaController?cod="+codcs;
          		  }  
          		  location.href= enca; 
          		});
          	  
            }
            </script>