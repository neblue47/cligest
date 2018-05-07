<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Perfil do Paciente</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Paciente</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Perfil do Paciente</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
	                    <div class="col-md-4 col-xs-12">
	                        <div class="white-box">
								<div class=" cardbox patient-profile">
									<img src="theme/img/dp.jpg" class="img-responsive" alt="">
								</div>
								<div class="cardbox">
			                    <div class="header">
			                        <h4 class="font-bold">SOBRE PACIENTE</h4>
			                    </div>
				                    <div class="body">
				                        <div class="user-btm-box">
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Nome</strong>
				                                        <p>Alexandre Jo�o</p>
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Ocupa��o</strong>
				                                        <p>Engenheiro</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Contacto</strong>
				                                        <p>244 9234 044 415</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Morada</strong>
				                                        <p>345, Sarju Appt., Mota Varacha, Surat
				                                            <br> Gujarat, India.</p>
				                                    </div>
				                                </div>
				                            </div>
				                    	</div>
			                		</div>
	                        	</div>
	                   	 </div>
	                    <div class="col-md-8 col-xs-12">
                            <div class="cardbox">
			                    <div class="body"> 
                                    <div class="mypost-list">
                                        <div class="post-box">
                                            <p>It is also used to identify any abnormal tissue in the uterine cavity, such as uterine fibroids, endometrial polyps, scar tissue, or retained pregnancy tissue, the presence of which may be suggested by history or previous tests such as a hysterosalpingogram (x-ray of the uterus and tubes). This procedure is done in the office here at IVF New England, and is done by one of the physicians.  </p>
                                            <p>Approximately an hour before the exam we suggest that you take Ibuprofen 600 mg (Motrin/Advil) or a similar medication to minimize some mild to moderate cramping that you may experience during the procedure. </p>
                                        </div>
                                        <hr>
                                        <div class="post-box">
                                            <h4 class="font-bold">General Report</h4>                                        
                                            <hr>
                                            <h5>Heart Beat <span class="pull-right">85</span></h5>
				                            <div class="progress">
				                                <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%"> <span class="sr-only">40% Complete (success)</span> </div>
				                            </div>
				                            <h5>Blood Pressure<span class="pull-right">93</span></h5>
				                            <div class="progress">
				                                <div class="progress-bar progress-bar-warning progress-bar-striped active" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width:90%;"> <span class="sr-only">50% Complete</span> </div>
				                            </div>
				                            <h5>Sugar<span class="pull-right">55</span></h5>
				                            <div class="progress">
				                                <div class="progress-bar progress-bar-primary progress-bar-striped active" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width:50%;"> <span class="sr-only">50% Complete</span> </div>
				                            </div>
				                            <h5>Haemoglobin<span class="pull-right">78%</span></h5>
				                            <div class="progress">
				                                <div class="progress-bar progress-bar-danger progress-bar-striped active" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:70%;"> <span class="sr-only">50% Complete</span> </div>
				                            </div>
                                        </div>
                                        </div>
                                        <hr>
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="row">
					                                <div class="col-md-12">
					                                    <div class="card card-topline-purple">
					                                        <div class="card-head">
					                                            <header>Past Visit History</header>
					                                        </div>
					                                        <div class="card-body ">
					                                        	<div class="table-responsive">
					                                            <table class="table table-striped custom-table table-hover">
					                                                <thead>
					                                                    <tr>
					                                                        <th>Date</th>
					                                                        <th>Doctor</th>
					                                                        <th>Treatment</th>
					                                                        <th>Chart</th>
					                                                        <th>Charges($)</th>
					                                                        <th>Action</th>
					                                                    </tr>
					                                                </thead>
					                                                <tbody>
					                                                    <tr>
					                                                        <td>11/05/2017</td>
					                                                        <td>Dr.Rajesh</td>
					                                                        <td>Check up</td>
					                                                        <td><div id="sparkline"></div></td>
					                                                        <td>14$</td>
					                                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="Edit">
					                                                        	<i class="fa fa-check"></i></a> 
					                                                        	<a href="javascript:void(0)" class="text-inverse" title="Delete" data-toggle="tooltip">
					                                                        	<i class="fa fa-trash"></i></a>
					                                                        </td>
					                                                    </tr>
					                                                    <tr>
					                                                        <td>13/05/2017</td>
					                                                        <td>Dr.Rajesh</td>
					                                                        <td>X-Ray</td>
					                                                        <td><div id="sparkline3"></div></td>
					                                                        <td>16$</td>
					                                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="Edit">
					                                                        	<i class="fa fa-check"></i></a> 
					                                                        	<a href="javascript:void(0)" class="text-inverse" title="Delete" data-toggle="tooltip">
					                                                        	<i class="fa fa-trash"></i></a>
					                                                        </td>
					                                                    </tr>
					                                                    <tr>
					                                                        <td>13/05/2017</td>
					                                                        <td>Dr.Rajesh</td>
					                                                        <td>Blood Test</td>
					                                                        <td><div id="sparkline1"></div></td>
					                                                        <td>24$</td>
					                                                        <td><a href="javascript:void(0)" class="" data-toggle="tooltip" title="Edit">
					                                                        	<i class="fa fa-check"></i></a> 
					                                                        	<a href="javascript:void(0)" class="text-inverse" title="Delete" data-toggle="tooltip">
					                                                        	<i class="fa fa-trash"></i></a>
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
                </div>
                <!-- end page content -->