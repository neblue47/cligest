<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">			  
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Pagamentos Consulta</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Pagamentos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Lista de Pagamentos</li>
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
                                   <table class="table display" id="example1" style="width:100%;" data-page-length="5">
                                        <thead>
                                            <tr>
                                            	<th class="center">  </th>
                                                <th class="center"> Nome do Paciente</th> 
                                                <th class="center"> Nome do Doutor </th>
                                                <th class=""> Serviço</th>
                                                <th class="center"> Acção </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="pc" items="${lsPaciente }" varStatus="pos">
                                        <tr class="odd gradeX">
												<td  >
													  ${pos.index+1}
												</td>
												<td>${pc.nome_paciente}</td> 
												<td>${pc.nome_doutor}</td>
												<td>${pc.servico}</td>
												<td class="center">
													<a target="_blank" href="GeradoRelatorio?nomeRelat=PagamentoConsultaA4&cod_param=${pc.numero_doc}" class="btn btn-success btn-xs" title="Gerar Comprovativo">
		  													<i class="fa fa-print"></i> 
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
            
            <script src="assets/datatables/jquery.dataTables.min.js" ></script>
 			<script src="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
    		<script src="assets/table_data.js" ></script>