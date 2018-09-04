<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                 <jsp:include page="../../shared/mensagem-users.jsp" />	
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Lista  de Utilizadores</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="navegacao?mods=ng">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Acessos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Lista  de Utilizadores</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1" >
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header></header>
					                                    <div class=" " style="float: right;">
					                                        <div class="">
					                                            <div class="btn-group btn-sm">
					                                                <a href="navegacao?mods=ad&pag=novousers" id="addRow" class="btn btn-info">
					                                                     <i class="fa fa-plus"></i> Novo
					                                                </a>
					                                            </div>
					                                        </div>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    <div class="row">
					                                        
					                                   
					                                    </div>
					                                    <table class="table display" id="example1" style="width:100%;">
					                                        <thead>
					                                            <tr>
					                                                <th class="center"> Numero </th>
					                                                <th class="left"  > Nome </th>
					                                                <th class="left"  > Utilizador </th>
					                                                <th class="center"> Grupo </th>
					                                                <th class="center"> Opções </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
					                                        <c:forEach items="${lsUsers}" var="at">
																<tr class="odd gradeX">
																	<td class="center">${at.num_fun }</td>
																	<td>${at.nome } ${at.apelido }</td>
																	<td class="left">${at.nome_us }</td>
																	<td class="center">${at.nomgrupo }</td>
																	 
																	<td class="center">
																		<a href="navegacao?mods=ad&pag=editusers" class="btn btn-primary btn-xs">
																			<i class="fa fa-pencil"></i>
																		</a>
																		<button class="btn btn-danger btn-xs">
																			<i class="fa fa-trash-o "></i>
																		</button>
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
            <!-- end page content -->
            <!-- start js include path -->
    <script src="assets/jquery.min.js" ></script>
    <!-- data tables -->
    <script src="assets/datatables/jquery.dataTables.min.js" ></script>
 	<script src="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
    <script src="assets/table_data.js" ></script>
    
    