<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
.statu-1  {background-color: blue;  color: white; height: 12px;} 
.statu-2  {background-color: green; color: white; height: 12px;} 
.statu-3  {background-color: yellow; color: black; height: 12px;} 
.statu-4  {background-color: orange; color: white; height: 12px;} 
.statu-5  {background-color: red; color: white; height: 12px;} 
</style>
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
                                <li class="active">Consultas</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                        
                            <div class="tabbable-line">
                                <ul class="nav customtab nav-tabs" role="tablist">
	                                <li class="nav-item"><a href="#tab1" class="nav-link active"  data-toggle="tab" >Historico de Consulta</a></li>
	                            </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1">
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header> Pacientes Atendidos </header>
					                                    <div class="tools">
					                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
						                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    <table class="table display" id="example1" style="width:100%;" data-page-length="5">
				                                        <thead>
				                                            <tr>
				                                                <th  > Nome do Paciente</th>
				                                                <th class="center"> Genero </th>
				                                                <th class="center"> Idade </th>
				                                                <th class="center"> Serviço</th>
				                                                <th class="center"> Doutor</th>
				                                                <th class="center"> Data Consulta</th>
				                                                <th class="center"> Opções </th>
				                                            </tr>
				                                        </thead>
				                                        <tbody>
				                                        <c:forEach var="pc" items="${lsSiTriado }">
				                                        <tr class="odd gradeX">
																 
																<td>${pc.nome_paciente}</td>
																<td class="center"> ${pc.nomegenero} </td>
																<td class="center"> ${pc.pac_idade}</td>
																<td class="center">${pc.servico}</td>
																<td class="center ">${pc.nome_doutor }</td>
																<td class="center"><fmt:formatDate value="${pc.data_do_agendamento.time}" pattern="dd/MM/yyyy"/></td>
																<td class="center">
																	<a target="_blank" href="GeradoRelatorio?nomeRelat=FichaMedicoConsulta&cod_param=${pc.FK_consulta}" class="btn btn-success btn-xs">
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
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page content -->
            <script src="assets/datatables/jquery.dataTables.min.js" ></script>
 			<script src="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
    		<script src="assets/table_data.js" ></script>
            