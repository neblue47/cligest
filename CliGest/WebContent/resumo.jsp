<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Resumo</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Resumo</li>
                            </ol>
                        </div>
                    </div>
                   
                     <!-- start widget -->
					<div class="state-overview">
							<div class="row">
						        <div class="col-xl-3 col-md-6 col-12">
						          <div class="info-box bg-blue">
						            <span class="info-box-icon push-bottom"><i class="material-icons fa fa-calendar"></i></span>
						            <div class="info-box-content">
						              <span class="info-box-text">Agendamentos</span>
						              <span class="info-box-number">450</span>
						              <div class="progress">
						                <div class="progress-bar" style="width: 45%"></div>
						              </div>
						              <span class="progress-description">
						                    45% Increase in 28 Days
						                  </span>
						            </div>
						            <!-- /.info-box-content -->
						          </div>
						          <!-- /.info-box -->
						        </div>
						        <!-- /.col -->
						        <div class="col-xl-3 col-md-6 col-12">
						          <div class="info-box bg-orange">
						            <span class="info-box-icon push-bottom"><i class="material-icons fa fa-child"></i></span>
						            <div class="info-box-content">
						              <span class="info-box-text">Novo Pacientes</span>
						              <span class="info-box-number">155</span>
						              <div class="progress">
						                <div class="progress-bar" style="width: 40%"></div>
						              </div>
						              <span class="progress-description">
						                    40% Increase in 28 Days
						                  </span>
						            </div>
						            <!-- /.info-box-content -->
						          </div>
						          <!-- /.info-box -->
						        </div>
						        <!-- /.col -->
						        <div class="col-xl-3 col-md-6 col-12">
						          <div class="info-box bg-purple">
						            <span class="info-box-icon push-bottom"><i class="material-icons fa fa-stethoscope"></i></span>
						            <div class="info-box-content">
						              <span class="info-box-text">Procedimentos</span>
						              <span class="info-box-number">52</span>
						              <div class="progress">
						                <div class="progress-bar" style="width: 85%"></div>
						              </div>
						              <span class="progress-description">
						                    85% Increase in 28 Days
						                  </span>
						            </div>
						            <!-- /.info-box-content -->
						          </div>
						          <!-- /.info-box -->
						        </div>
						        <!-- /.col -->
						        <div class="col-xl-3 col-md-6 col-12">
						          <div class="info-box bg-success">
						            <span class="info-box-icon push-bottom"><i class="material-icons fa fa-money"></i></span>
						            <div class="info-box-content">
						              <span class="info-box-text">Pagamentos</span>
						              <span class="info-box-number">13,921</span><span>$</span>
						              <div class="progress">
						                <div class="progress-bar" style="width: 50%"></div>
						              </div>
						              <span class="progress-description">
						                    50% Increase in 28 Days
						                  </span>
						            </div>
						            <!-- /.info-box-content -->
						          </div>
						          <!-- /.info-box -->
						        </div>
						        <!-- /.col -->
						      </div>
						</div>
					<!-- end widget -->
                     <!-- chart start -->
                    <div class="row">
                        <div class="col-md-8">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>HOSPITAL SURVEY</header>
                                    <div class="tools">
                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
										<a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
										<a class="t-close btn-color fa fa-times" href="javascript:;"></a>
                                    </div>
                                </div>
                                <div class="card-body no-padding height-9">
									<div class="row">
									        <canvas id="chartjs_line"></canvas>
									</div>
								</div>
                            </div>
                        </div>
                         <div class="col-md-4">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>HOSPITAL SURVEY</header>
                                    <div class="tools">
                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
										<a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
										<a class="t-close btn-color fa fa-times" href="javascript:;"></a>
                                    </div>
                                </div>
                                <div class="card-body no-padding height-9">
									<div class="row">
									        <canvas id="chartjs_doughnut"></canvas>
									</div>
								</div>
                            </div>
                        </div>
                    </div>
                     <!-- Chart end --> 
                     <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card  card-box">
                                <div class="card-head">
                                    <header>PACIENTES AGENDADOS</header>
                                    <div class="tools">
                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
	                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
	                                    <a class="t-close btn-color fa fa-times" href="javascript:;"></a>
                                    </div>
                                </div>
                                <div class="card-body ">
                                    <div class="row table-padding">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <div class="btn-group">
                                                 <a href="navegacaoag?mods=ag&pag=novoagen"  class="btn btn-info">
                                                    Novo <i class="fa fa-plus"></i>
                                                </a> 
                                            </div>
                                        </div>
                                         
                                    </div>
                                    <div class="table-responsive">
                                     <table class="table table-striped table-bordered table-hover table-checkable order-column" id="example4">
                                        <thead>
                                            <tr>
                                                <th>
                                                     #
                                                </th>
                                                <th>Nome Paciente </th>
                                                <th>Agendado Doutor</th>
                                                <th>Data</th>
                                                <th>Hora</th>
                                                <th>Acções </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                            <c:forEach var="md" items="${listaPac}" >
                                            <tr class="odd gradeX">
                                                <td>
                                                     
                                                </td>
                                                <td> ${md.nome_paciente } </td>
                                                <td>
                                                     Dr. ${md.nome_doutor }  
                                                </td>
                                                <td class="center"> <fmt:formatDate value="${md.data_do_agendamento.time}" pattern="dd/MM/yyyy" /> </td>
                                                <td class="center"> ${md.hora_daconfirmacao} </td>
                                                <td class="center">
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs btn-info dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Eliminar </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-ban"></i> Cancelar </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-clock-o"></i> Adiar </a>
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