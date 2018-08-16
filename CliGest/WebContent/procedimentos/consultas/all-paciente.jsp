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
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Procedimentos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Triagem</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                        
                            <div class="tabbable-line">
                                <ul class="nav customtab nav-tabs" role="tablist">
	                                <li class="nav-item"><a href="#tab1" class="nav-link active"  data-toggle="tab" >Nova Triagem</a></li>
	                            </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1">
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header> PACIENTES PARA TRIAGEM </header>
					                                    <div class="tools">
					                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
						                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                   <table class="table display" id="example1" style="width:100%;">
				                                        <thead>
				                                            <tr>
				                                                <th class="center"> Nome do Paciente</th>
				                                                <th class="center"> Contacto </th>
				                                                <th class="center"> Nome do Doutor </th>
				                                                <th class="center">Serviço</th>
				                                                <th class="center"> Opções </th>
				                                            </tr>
				                                        </thead>
				                                        <tbody>
				                                        <c:forEach var="pc" items="${lsNoTriado }">
				                                        <tr class="odd gradeX">
																 
																<td>${pc.nome_paciente}</td>
																<td class="center"> ${pc.telefonep} </td>
																<td>Dr. ${pc.nome_doutor}</td>
																<td>${pc.servico}</td>
																<td class="center">
																	<a href="navegacaopd?mods=pd&pag=newtriar&codcs=${pc.FK_paciente}&cods=${pc.FK_servico}&codc=${pc.FK_consulta_confirmada}"class="btn btn-primary btn-xs" title="triar">
																		<i class="fa fa-heartbeat"></i>
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
                                    <div class="tab-pane   fontawesome-demo" id="tab2">
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header>TRIADOS</header>
					                                    <div class="tools">
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    <table class="table display" id="example4" style="width:100%;">
				                                        <thead>
				                                            <tr>
				                                                <th class="center"> Nome do Paciente</th>
				                                                <th class="center"> Contacto </th>
				                                                <th class="center"> Nome do Doutor </th>
				                                                <th class="center">Serviço</th>
				                                                <th class="center"> Opções </th>
				                                            </tr>
				                                        </thead>
				                                        <tbody>
				                                        <c:forEach var="pc" items="${lsSiTriado }">
				                                        <tr class="odd gradeX">
																 
																<td>${pc.nome_paciente}</td>
																<td class="center"> ${pc.telefonep} </td>
																<td>Dr. ${pc.nome_doutor}</td>
																<td>${pc.servico}</td>
																<td class="center">
																	<a href="#"class="btn btn-primary btn-xs"  >
																		<i class="fa fa-heartbeat"></i>
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
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page content -->
            <script src="assets/datatables/jquery.dataTables.min.js" ></script>
 	<script src="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
    <script src="assets/table_data.js" ></script>