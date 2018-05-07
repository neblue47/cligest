<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                 <jsp:include page="../shared/mensagem-paciente.jsp" />	
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Lista de Pacientes</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Pacientes</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Pacientes</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class=" ">
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1">
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header></header>
					                                    <div style="float: right;">
					                                        <div class="btn-group btn-sm">
					                                                <a href="navegacaoag?mods=ag&pag=novopac" id="addRow" class="btn btn-info">
					                                                      <i class="fa fa-plus"></i> Novo
					                                                </a>
					                                            </div>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    <div class="row">
					                                        
					                                         
					                                    </div>
					                                    <table class="table display" id="example1" style="width:100%;">
					                                        <thead>
					                                            <tr>
					                                            	 
					                                                <th class="center"> Nome </th>
					                                                <th class="center"> Genero </th>
					                                                <th class="center"> Telefone </th>
					                                                <th class="center"> Nascido </th>
					                                                <th class="center"> Idade</th>
					                                                <th class="center"> Sangue</th>
					                                                <th class="center"> Opções </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
					                                        <c:forEach var="pc" items="${lsPaciente }">
					                                        	<tr class="odd gradeX">
																	 
																	<td>${pc.nome }</td>
																	<td class="center">${pc.nomegenero }</td>
																	<td><a href="#">${pc.telefone } </a></td>
																	<td class="center"><fmt:formatDate value="${pc.dataNasc.time}" pattern="dd/MM/yyyy"/></td>
																	<td class="center">${pc.idade}</td>
																	<td class="center">${pc.sanguineo }</td>
																	<td  class="center">
																	<div class="btn-group">
				                                                        <button class="btn btn-xs btn-info dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Acções
				                                                            <i class="fa fa-angle-down"></i>
				                                                        </button>
				                                                        <ul class="dropdown-menu" role="menu">
				                                                        	<li>
				                                                                <a href="navegacaoag?mods=ag&pag=editpac&codp=${pc.FK_paciente }"  ><i class=" fa fa-pencil "></i> Editar </a>
				                                                            </li>
				                                                            <li>
				                                                                <a href="navegacaoag?mods=ag&pag=novoagencon&codp=${pc.FK_paciente }"><i class="fa fa-stethoscope"></i> Consulta </a>
				                                                            </li>
				                                                            
				                                                            <li>
				                                                                <a href="navegacaoag?mods=ag&pag=novoagenexa&codp=${pc.FK_paciente }"><i class="fa fa-medkit"></i> Exame </a>
				                                                            </li>
				                                                            <li>
				                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Eliminar </a>
				                                                            </li>
				                                                        </ul>
				                                                    </div>
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
    <!-- start js include path -->
    <script src="assets/jquery.min.js" ></script>
    <!-- data tables -->
    <script src="assets/datatables/jquery.dataTables.min.js" ></script>
 	<script src="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
    <script src="assets/table_data.js" ></script>
    
    