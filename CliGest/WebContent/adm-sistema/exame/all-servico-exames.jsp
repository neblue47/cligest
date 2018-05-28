<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
		          <jsp:include page="../../shared/mensagem.jsp" />				  
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Lista de Agendamentos - Exames</div>
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
                        <div class="col-md-12">
                            <div class="card card-topline-red">
                                <div class="card-head">
                                    <header></header>
                                     
                                </div>
                                <div class="card-body ">
                                    <table class="table display" id="example1" style="width:100%;">
                                        <thead>
                                            <tr>
                                            	 
                                                <th class="center"> Nome </th>
                                                <th class="center"> Data  </th>
                                                <th class="center"> Hora  </th>
                                                <th class="center"> Contacto </th>
                                                 
                                                <th class="center"> Opções </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="pc" items="${lsPaciente }">
                                        <tr class="odd gradeX">
												 
												<td>${pc.nome_paciente}</td>
												<td class="center"><fmt:formatDate value="${pc.data_do_agendamento.time}" pattern="dd/MM/yyyy"/></td>
												<td class="center">${pc.hora_daconfirmacao}</td>
												<td class="center"> ${pc.telefonep} </td>
												<td class="center">
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
          		  text: "Pretende cancelar o exame?",
          		  icon: "warning",
          		  buttons: true,
          		  dangerMode: true,
          		})
          		.then((willDelete) => {
          		  if (willDelete) {
          			enca = "AgendaExameController?acao=cancel&codcs="+codcs;
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
          			enca = "AgendaExameController?acao=alterar&codcs="+codcs;
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
       