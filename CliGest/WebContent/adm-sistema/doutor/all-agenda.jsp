<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Lista  de Doutores</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Doutores</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Agenda  de Doutores</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="tabbable-line">
                                 
                                <ul class="nav customtab nav-tabs" role="tablist">
	                                <li class="nav-item"><a href="#tab1" class="nav-link active"  data-toggle="tab" >List View</a></li>
	                            </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1">
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header></header>
					                                    <div class="tools">
					                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
						                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    <div class="row">
					                                        <div class="col-md-6 col-sm-6 col-xs-6">
					                                            <div class="btn-group">
					                                                <a href="navegacao?mods=ad&pag=novodoc" id="addRow" class="btn btn-info">
					                                                     <i class="fa fa-plus"></i> Novo
					                                                </a>
					                                            </div>
					                                        </div>
					                                   
					                                    </div>
					                                    <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle" id="example4">
					                                        <thead>
					                                            <tr>
					                                            	<th><i class="fa fa-history"></i></th>
					                                                <th class="center"> Nome </th>
					                                                <th class="center"> Especialidade </th>
					                                                <th class="center"> Titulo </th>
					                                                <th class="center"> Contacto </th>
					                                                <th class="center"> Email </th>
					                                                <th class="center"> Opções </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
																<tr class="odd gradeX">
																	<td class="center">
																			<a href="edit_appointment.html" class="btn btn-warning btn-xs">
																				<i class="fa fa-clock-o"></i>
																			</a>
																	</td>
																	<td>Dr.Rajesh Kiala</td>
																	<td class="left">Ortopedia</td>
																	<td class="left">MBBS,MD</td>
																	<td><a href="tel:4444565756">
																			4444565756 </a></td>
																	<td><a href="mailto:shuxer@gmail.com">
																			rajesh@gmail.com </a></td>
																	<td class="center">
																		<a href="navegacao?mods=ad&pag=editdoc" class="btn btn-primary btn-xs">
																			<i class="fa fa-pencil"></i>
																		</a>
																		<button class="btn btn-danger btn-xs">
																			<i class="fa fa-trash-o "></i>
																		</button>
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
              <script src="assets/bootstrap-switch/js/bootstrap-switch.min.js" ></script>
             <!-- data tables -->
    <script src="assets/datatables/jquery.dataTables.min.js" ></script>
 	<script src="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.js" ></script>
    <script src="assets/table_data.js" ></script>
