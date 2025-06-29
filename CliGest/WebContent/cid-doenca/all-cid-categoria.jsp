<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                 <jsp:include page="../shared/mensagem-users.jsp" />	
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">LISTA DE DOENÇAS</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="navegacao?mods=ng">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">CIDs</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Categorias CIDs</li>
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
					                                    <header>LISTA DE CATEGORIAS DE CID</header>					                                     
					                                </div>
					                                <div class="card-body ">
					                                    <table class="table display" id="example1" style="width:100%;" data-page-length="10" data-page-list = "false">
					                                        <thead>
					                                            <tr>
					                                                <th class="center"> Código  </th>
					                                                <th class="left"  > Descrição  </th>
					                                                <th class="center"  > Grupo Associado </th>					                                                 
					                                            </tr>
					                                        </thead>
					                                        <tbody>
					                                        <c:forEach items="${lsCategoriaGruposCid}" var="at">
																<tr class="odd gradeX">
																	<td class="center" width="10%">${at.categorizaGrupo }</td>
																	<td class="left" width="75%">${at.descricaoCategoria }</td>
																	<td class="center" width="15%">${at.listarCodigoGrupo }</td> 																	 
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
    
    
    