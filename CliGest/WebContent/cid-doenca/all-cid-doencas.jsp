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
                                <li class="active">CID Doenças</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1" >
                                        <div class="row">
                                        <div class="col-md-12 col-sm-12">
                                 
                                <form action="navegacaoag" id="form_sample_1" class="card card-topline-red" method="get">
								<input type="hidden" name="mods" value ="ag">
								<input type="hidden" name="pag" value ="listagen">
                                <input type="hidden" name="acao" value ="psquisar">
                                <div class="card-head"> <header>Pesquisar</header></div>
                                <div class="card-body">
                                <div class="row">    
                                <div class="col-md-12">
									    <div class="form-group">
									        <label class="control-label">Doença </label>
									        <input  name="nomDoenca" class="form-control"  value="${param.nomDoenca}" />
									    </div>
									    
								    </div>                            
								    <div class="col-md-6">
								    	<div class="form-group">
									        <label class="control-label">Restrição Genero </label>
									        <select name ="rsgenero" class="form-control">
									        <option value="0">Selecione...</option>
                                            <c:forEach var="dc" items="${lsConsultas }">
                                           	<option value="${dc.id_servico }" <c:if test = "${dc.id_servico == param.rsgenero}">selected</c:if>>  ${dc.servico }  </option>
                                           	</c:forEach>
									        </select>
									    </div>
								    </div>
								    
								     
								    <div class="col-md-6">
								    	<div class="form-group">
									        <label class="control-label">Classificação </label>
									        <select name ="clasfcao" class="form-control">
									        <option value="0">Selecione...</option>
                                            <c:forEach var="dc" items="${lsConsultas }">
                                           	<option value="${dc.id_servico }" <c:if test = "${dc.id_servico == param.clasfcao}">selected</c:if>>  ${dc.servico }  </option>
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
					                                    <header>LISTA DE CID</header>					                                     
					                                </div>
					                                <div class="card-body ">
					                                    <table class="table display" id="example1" style="width:100%;" data-page-length="5" data-page-list = "false">
					                                        <thead>
					                                            <tr>
					                                                <th class="center"> Codigo </th>
					                                                <th class="left"  > Descricao </th>
					                                                <th class="left"  > Classificação </th>
					                                                <th class="center"> L. Óbito </th>
					                                                <th class="center"> R. Género </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
					                                         <c:forEach items="${lsCidDoencas}" var="at">
																<tr class="odd gradeX">
																	<td class="center" width="10%">${at.codigocid }</td>
																	<td class="left"> ${at.descricao }</td>
																	<td class="left">${at.listarclassificacao }</td>
																	<td class="center" width="10%">${at.listarcausaobito }</td>
																	<td class="center" width="10%">${at.listarrestricaodsexo }</td>
			
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
    
    
    