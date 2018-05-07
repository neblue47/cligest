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
                                <li class="active">Consultas</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                        
                            <div class="tabbable-line">
                                <ul class="nav customtab nav-tabs" role="tablist">
	                                <li class="nav-item"><a href="#tab1" class="nav-link active"  data-toggle="tab" >Triados</a></li>
	                            </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1">
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header> </header>
					                                    <div class="tools">
					                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
						                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    
					                                    <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle" id="example4">
					                                        <thead>
					                                            <tr>
					                                            	<th class="center"> <i class="fa fa-calendar"></i> </th>
					                                                <th class="center"> Nome </th>
					                                                <th class="center"> Genero </th>
					                                                <th class="center"> Endereço </th>
					                                                <th class="center"> Telefone </th>
					                                                <th class="center"> Nascido </th>
					                                                <th class="center"> Idade</th>
					                                                <th class="center"> Sangue</th>
					                                                <th class="center"> Opções </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
																<tr class="odd gradeX">
																	<td class="center">
																		 
																	</td>
																	<td>Alexandre Alberto Joao</td>
																	<td class="center">Masculino</td>
																	<td class="center">18,Ajay flats, satadhar, ahmedabad</td>
																	<td><a href="tel:4444565756">
																			924 044 145 </a></td>
																	<td class="center">18/12/2017</td>
																	<td class="center">1</td>
																	<td class="center">0+</td>
																	<td  class="center">
																		<a href="navegacaopd?mods=pd&pag=newcons" class="btn btn-primary btn-xs">
																			<i class="fa fa-heartbeat"></i>
																		</a>
																	</td>
																</tr>
																 
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