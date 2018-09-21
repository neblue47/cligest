<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                <jsp:include page="../../shared/mensagem-acessos.jsp" />	
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Grupos de Privilégios</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="navegacao?mods=ng">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Acessos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Lista  de Grupos</li>
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
					                                                <a href="navegacao?mods=ad&pag=novousersgrp" id="addRow" class="btn btn-info">
					                                                     <i class="fa fa-plus"></i> Novo
					                                                </a>
					                                            </div>
					                                        </div>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    <div class="row">
					                                        
					                                   
					                                    </div>
					                                    <table class="table display" id="example1" style="width:100%;" data-page-length="5">
					                                        <thead>
					                                            <tr>
					                                                <th style="width: 75%"> Descrição </th>
					                                                <th class="center"> Opções </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
					                                        <c:forEach items="${lsGrupos }" var="at">
					                                        <tr class="odd gradeX">
																	<td  style="width: 75%">${at.nomgrupo }</td>
																	<td class="center">
																		<a href="navegacao?mods=ad&pag=usersprv&codgr=${at.id_grupo }" class="btn btn-success btn-xs">
																			<i class="fa fa-lock"></i>
																		</a>
																		<a href="navegacao?mods=ad&pag=editcons&codgr=${at.id_grupo }" class="btn btn-primary btn-xs">
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
     
    
    