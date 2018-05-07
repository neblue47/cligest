<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" pageEncoding="ISO-8859-1" errorPage="erros/404.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html lang="en">
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta name="description" content="Responsive Admin Template" />
    <meta name="author" content="RedstarHospital" />
    <title>CLI-GEST | Sistema Integrado de Gestao Clinicas</title>
    <!-- google font -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
	<!-- icons -->
    <link href="../assets/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<!--bootstrap -->
    
	<link href="../assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Material Design Lite CSS -->
	<link rel="stylesheet" href="../assets/material/material.min.css">
	<link rel="stylesheet" href="css/material_style.css">
	<!-- Theme Styles -->
    <link href="css/theme_style.css" rel="stylesheet" id="rt_style_components" type="text/css" />
    <link href="css/plugins.min.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/responsive.css" rel="stylesheet" type="text/css" />
    <link href="css/theme-color.css" rel="stylesheet" type="text/css" />
	<!-- favicon -->
    <link rel="shortcut icon" href="img/favicon.ico" /> 
 </head>
 <!-- END HEAD -->
<body class="page-header-fixed sidemenu-closed-hidelogo page-content-white page-md header-white white-sidebar-color logo-indigo">
    <div class="page-wrapper">
        <!-- start header -->
        <div class="page-header navbar navbar-fixed-top">
            <div class="page-header-inner ">
                <!-- logo start -->
                <div class="page-logo">
                    <a href="index.html">
                    <span class="logo-icon fa fa-stethoscope fa-rotate-45"></span>
                    <span class="logo-default" > CLI-GEST </span> </a>
                </div>
                <!-- logo end -->
				<ul class="nav navbar-nav navbar-left in">
					<li><a href="#" class="menu-toggler sidebar-toggler"><i class="icon-menu"></i></a></li>
				</ul>
                 <form class="search-form-opened" action="#" method="GET">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search..." name="query">
                        <span class="input-group-btn">
                          <a href="javascript:;" class="btn submit">
                             <i class="icon-magnifier"></i>
                           </a>
                        </span>
                    </div>
                </form>
                <!-- start mobile menu -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                    <span></span>
                </a>
               <!-- end mobile menu -->
                <!-- start header menu -->
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                     
 						<!-- start manage user dropdown -->
 						<li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <img alt="" class="img-circle " src="img/dp.jpg" />
                                <span class="username username-hide-on-mobile"> Kiran </span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                <li>
                                    <a href="user_profile.html">
                                        <i class="icon-user"></i> Profile </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="icon-settings"></i> Settings
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="icon-directions"></i> Help
                                    </a>
                                </li>
                                <li class="divider"> </li>
                                <li>
                                    <a href="lock_screen.html">
                                        <i class="icon-lock"></i> Lock
                                    </a>
                                </li>
                                <li>
                                    <a href="login.html">
                                        <i class="icon-logout"></i> Log Out </a>
                                </li>
                            </ul>
                        </li>
                        <!-- end manage user dropdown -->
                        <li class="dropdown dropdown-quick-sidebar-toggler">
                             <a id="headerSettingButton" class="mdl-button mdl-js-button mdl-button--icon pull-right" data-upgraded=",MaterialButton">
	                           <i class="material-icons">more_vert</i>
	                        </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- end header -->
        <!-- start color quick setting -->
        <div class="quick-setting-main">
			<button class="control-sidebar-btn btn" data-toggle="control-sidebar"><i class="fa fa-cog fa-spin"></i></button>
			<div class="quick-setting display-none">
				<ul id="themecolors" >
				<li><p class="selector-title">Main Layouts</p></li>
				<li class="complete"><div class="theme-color layout-theme">
				<a href="#" data-theme="light"><span class="head"></span><span class="cont"></span></a>
				<a href="../dark/index.html" data-theme="dark"><span class="head"></span><span class="cont"></span></a>
				</div></li>	
				<li><p class="selector-title">Sidebar Color</p></li>
				<li class="complete"><div class="theme-color sidebar-theme">
				<a href="#" data-theme="white"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="dark"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="blue"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="indigo"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="cyan"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="green"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="red"><span class="head"></span><span class="cont"></span></a>
				</div></li>
             	<li><p class="selector-title">Header Brand color</p></li>
             	<li class="theme-option"><div class="theme-color logo-theme">
             	<a href="#" data-theme="logo-white"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="logo-dark"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="logo-blue"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="logo-indigo"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="logo-cyan"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="logo-green"><span class="head"></span><span class="cont"></span></a>
				<a href="#" data-theme="logo-red"><span class="head"></span><span class="cont"></span></a>
             	</div></li>
             	<li><p class="selector-title">Header color</p></li>
             	<li class="theme-option"><div class="theme-color header-theme">
             	<a href="#" data-theme="header-white"><span class="head"></span><span class="cont"></span></a>
             	<a href="#" data-theme="header-dark"><span class="head"></span><span class="cont"></span></a>
             	<a href="#" data-theme="header-blue"><span class="head"></span><span class="cont"></span></a>
             	<a href="#" data-theme="header-indigo"><span class="head"></span><span class="cont"></span></a>
             	<a href="#" data-theme="header-cyan"><span class="head"></span><span class="cont"></span></a>
             	<a href="#" data-theme="header-green"><span class="head"></span><span class="cont"></span></a>
             	<a href="#" data-theme="header-red"><span class="head"></span><span class="cont"></span></a>
             	</div></li>
			</ul>
			</div>
		</div>
		<!-- end color quick setting -->
        <!-- start page container -->
        <div class="page-container">
 			<!-- start sidebar menu -->
 			<div class="sidebar-container">
 				<div class="sidemenu-container navbar-collapse collapse fixed-menu">
	                <div id="remove-scroll">
	                    <ul class="sidemenu  page-header-fixed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
	                        <li class="sidebar-toggler-wrapper hide">
	                            <div class="sidebar-toggler">
	                                <span></span>
	                            </div>
	                        </li>
	                        <li class="sidebar-user-panel">
	                            <div class="user-panel">
	                                <div class="pull-left image">
	                                    <img src="img/dp.jpg" class="img-circle user-img-circle" alt="User Image" />
	                                </div>
	                                <div class="pull-left info">
	                                    <p> Dr. Bernardo </p>
	                                    <a href="#"><i class="fa fa-circle user-online"></i><span class="txtOnline"> Online</span></a>
	                                </div>
	                            </div>
	                        </li>
							<!-- start sidebar menu ADMINISTRAÇÃO -->
	                        <li class="nav-item start  ">
	                            <a href="#" class="nav-link nav-toggle">
	                                <i class="material-icons fa fa-address-book"></i>
	                                <span class="title"> ADMINISTRA��O </span>
	                                <span class="arrow"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item active open  ">
	                                    <a href="index.html" class="nav-link ">
	                                        <span class="title">Estatisticas</span>
	                                        <span class="selected"></span>
	                                    </a>
	                                </li>
	                                <li class="nav-item ">
	                                    <a href="dashboard2.html" class="nav-link ">
	                                        <span class="title">Instituição</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="fornecedor-listar.html" class="nav-link ">
	                                        <span class="title">Fornecedores</span>
	                                    </a>
	                                </li>
									  <li class="nav-item  ">
	                                    <a href="dashboard3.html" class="nav-link ">
	                                        <span class="title">Fornecedores</span>
	                                    </a>
	                                </li>
									<li class="nav-item  ">
	                                    <a href="dashboard3.html" class="nav-link ">
	                                        <span class="title">Instalação</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
							 
	                        <li class="nav-item  ">
	                            <a href="#" class="nav-link nav-toggle"><i class="material-icons fa fa-calendar"></i>
	                            <span class="title">AGENDAMENTOS</span><span class="arrow"></span></a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="schedule.html" class="nav-link "> <span class="title">Agenda do Doutor</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="book_appointment.html" class="nav-link "> <span class="title">Agendar</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="book_appointment_material.html" class="nav-link "> <span class="title">Confirmar</span>
	                                    </a>
	                                </li>
	                                 <li class="nav-item  ">
	                                    <a href="edit_appointment.html" class="nav-link "> <span class="title">Editar Agendar</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="view_appointment.html" class="nav-link "> <span class="title">Listar Agendamentos</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
	                        <li class="nav-item  ">
	                            <a href="#" class="nav-link nav-toggle"> <i class="material-icons fa fa-user-md"></i>
	                                <span class="title">DOUTORES</span> <span class="arrow"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="all_doctors.html" class="nav-link "> <span class="title">Listar Doutores</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="add_doctor.html" class="nav-link "> <span class="title">Add Doutor</span>
	                                    </a>
	                                </li>
	                                
	                                <li class="nav-item  ">
	                                    <a href="doctor_profile.html" class="nav-link "> <span class="title">Perfil do Doctor</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
	                        <li class="nav-item  ">
	                            <a href="#" class="nav-link nav-toggle"> <i class="material-icons fa fa-hospital-o"> </i>
	                                <span class="title">PROCEDIMENTOS</span> <span class="arrow"></span>
	                            </a>
	                             <ul class="sub-menu">
								   <li class="nav-item  ">
	                                    <a href="add_patient.html" class="nav-link nav-toggle"> <span class="title">Antedimento</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Ambulatório</span>
	                                    </a>
										 <ul class="sub-menu">
										
											<li class="nav-item  ">
												<a href="add_patient.html" class="nav-link "> <span class="title">Agendar</span>
												</a>
											</li>
											<li class="nav-item  ">
												<a href="all_patients.html" class="nav-link "> <span class="title">Marcação</span>
												</a>
											</li>
										</ul>
								
	                                </li>
									 <li class="nav-item  ">
	                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Urgencias/Emergencias</span>
	                                    </a>
										 <ul class="sub-menu">
										
											<li class="nav-item  ">
												<a href="add_patient.html" class="nav-link "> <span class="title">Urgencias</span>
												</a>
											</li>
											<li class="nav-item  ">
												<a href="all_patients.html" class="nav-link "> <span class="title">Emergencias</span>
												</a>
											</li>
										</ul>
								
	                                </li>
	                                
	                            </ul>
	                        </li>
	                        <li class="nav-item  ">
	                            <a href="#" class="nav-link nav-toggle"> <i class="material-icons material-icons fa fa-child"></i>
	                                <span class="title">PACIENTES</span> <span class="arrow"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="all_patients.html" class="nav-link "> <span class="title">Listar Pacientes</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="add_patient.html" class="nav-link "> <span class="title">Add Paciente	</span>
	                                    </a>
	                                </li>
	                            
	                                <li class="nav-item  ">
	                                    <a href="patient_profile.html" class="nav-link "> <span class="title">Perfil Paciente</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
							
							<li class="nav-item  ">
	                            <a href="#" class="nav-link nav-toggle"> <i class="material-icons material-icons fa fa-stethoscope"></i>
	                                <span class="title">LABORATORIO</span> <span class="arrow"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="all_patients.html" class="nav-link "> <span class="title">Listar Pacientes</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="add_patient.html" class="nav-link "> <span class="title">Add Paciente	</span>
	                                    </a>
	                                </li>
	                            
	                                <li class="nav-item  ">
	                                    <a href="patient_profile.html" class="nav-link "> <span class="title">Perfil Paciente</span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
	                         
	                        <li class="nav-item  ">
	                            <a href="#" class="nav-link nav-toggle"> <i class="material-icons fa fa-money"></i>
	                                <span class="title">PAGAMENTOS</span> <span class="arrow"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="payments.html" class="nav-link "> <span class="title">Listar Pagamentos</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="add_payment.html" class="nav-link "> <span class="title">Novo Pagamento</span>
	                                    </a>
	                                </li>
	                                 
	                            </ul>
	                        </li>
	                         
	                        
	                    </ul>
	                </div>
                </div>
            </div>
            <!-- end sidebar menu --> 
			<!-- start page content -->
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
                        <div class="col-md-8 col-sm-12">
                            <div class="card  card-box">
                                <div class="card-head">
                                    <header>BOOKED APPOINTMENT</header>
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
                                                 <a href="book_appointment_material.html" id="addRow" class="btn btn-info">
                                                    Add New <i class="fa fa-plus"></i>
                                                </a> 
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <div class="btn-group pull-right">
                                               <button class="btn deepPink-bgcolor  btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                                    <i class="fa fa-angle-down"></i>
                                                </button>
                                                <ul class="dropdown-menu pull-right">
                                                    <li>
                                                        <a href="javascript:;">
                                                            <i class="fa fa-print"></i> Print </a>
                                                    </li>
                                                    <li>
                                                        <a href="javascript:;">
                                                            <i class="fa fa-file-pdf-o"></i> Save as PDF </a>
                                                    </li>
                                                    <li>
                                                        <a href="javascript:;">
                                                            <i class="fa fa-file-excel-o"></i> Export to Excel </a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover table-checkable order-column" id="example4">
                                        <thead>
                                            <tr>
                                                <th>
                                                    <label class="rt-chkbox rt-chkbox-single rt-chkbox-outline">
                                                        <input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" />
                                                        <span></span>
                                                    </label>
                                                </th>
                                                <th>Patient Name</th>
                                                <th>Assigned Doctor</th>
                                                <th>Date</th>
                                                <th>Time</th>
                                                <th>Actions </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="odd gradeX">
                                                <td>
                                                    <label class="rt-chkbox rt-chkbox-single rt-chkbox-outline">
                                                        <input type="checkbox" class="checkboxes" value="1" />
                                                        <span></span>
                                                    </label>
                                                </td>
                                                <td> Jayesh Patel </td>
                                                <td>
                                                    <a href="mailto:shuxer@gmail.com"> Dr.Rajesh </a>
                                                </td>
                                                <td class="center"> 12/05/2016 </td>
                                                <td class="center"> 10:45 </td>
                                                <td class="center">
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs btn-warning dropdown-toggle center no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu pull-left" role="menu">
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Delete </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-ban"></i> Cancel </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-clock-o"></i> Postpone </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="odd gradeX">
                                                <td>
                                                    <label class="rt-chkbox rt-chkbox-single rt-chkbox-outline">
                                                        <input type="checkbox" class="checkboxes" value="1" />
                                                        <span></span>
                                                    </label>
                                                </td>
                                                <td> Pooja Patel </td>
                                                <td>
                                                    <a href="mailto:looper90@gmail.com"> Dr.Sarah Smith </a>
                                                </td>
                                                <td class="center"> 12/05/2016 </td>
                                                <td class="center"> 10:55 </td>
                                                <td class="center">
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs btn-info dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Delete </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-ban"></i> Cancel </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-clock-o"></i> Postpone </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="odd gradeX">
                                                <td>
                                                    <label class="rt-chkbox rt-chkbox-single rt-chkbox-outline">
                                                        <input type="checkbox" class="checkboxes" value="1" />
                                                        <span></span>
                                                    </label>
                                                </td>
                                                <td> Pankaj Singh </td>
                                                <td>
                                                    <a href="mailto:userwow@yahoo.com"> Dr.Rajesh </a>
                                                </td>
                                                <td class="center"> 12/05/2016 </td>
                                                <td class="center"> 11:15 </td>
                                                <td class="center">
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs btn-success dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Delete </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-ban"></i> Cancel </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-clock-o"></i> Postpone </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="odd gradeX">
                                                <td>
                                                    <label class="rt-chkbox rt-chkbox-single rt-chkbox-outline">
                                                        <input type="checkbox" class="checkboxes" value="1" />
                                                        <span></span>
                                                    </label>
                                                </td>
                                                <td> Raj Malhotra </td>
                                                <td>
                                                    <a href="mailto:doctormail@gmail.com"> Dr.Megha Trivedi </a>
                                                </td>
                                                <td class="center"> 12/05/2016 </td>
                                                <td class="center"> 11:25 </td>
                                                <td class="center">
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs btn-primary dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Delete </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-ban"></i> Cancel </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-clock-o"></i> Postpone </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="odd gradeX">
                                                <td>
                                                    <label class="rt-chkbox rt-chkbox-single rt-chkbox-outline">
                                                        <input type="checkbox" class="checkboxes" value="1" />
                                                        <span></span>
                                                    </label>
                                                </td>
                                                <td> Sneha Pandya </td>
                                                <td>
                                                    <a href="mailto:doctormail@gmail.com"> Dr.Sarah Smith </a>
                                                </td>
                                                <td class="center"> 12/05/2016 </td>
                                                <td class="center"> 11:35 </td>
                                                <td class="center">
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs btn-warning dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                          	<li>
                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Delete </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-ban"></i> Cancel </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-clock-o"></i> Postpone </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="odd gradeX ">
                                                <td>
                                                    <label class="rt-chkbox rt-chkbox-single rt-chkbox-outline">
                                                        <input type="checkbox" class="checkboxes" value="1" />
                                                        <span></span>
                                                    </label>
                                                </td>
                                                <td> Sameer Jain </td>
                                                <td>
                                                    <a href="mailto:doctormail@gmail.com"> Dr.Megha Trivedi </a>
                                                </td>
                                                <td class="center"> 12/05/2016 </td>
                                                <td class="center"> 11:45 </td>
                                                <td class="center">
                                                    <div class="btn-group">
                                                        <button class="btn btn-xs btn-danger dropdown-toggle no-margin" type="button" data-toggle="dropdown" aria-expanded="false"> Actions
                                                            <i class="fa fa-angle-down"></i>
                                                        </button>
                                                        <ul class="dropdown-menu" role="menu">
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-trash-o"></i> Delete </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-ban"></i> Cancel </a>
                                                            </li>
                                                            <li>
                                                                <a href="javascript:;"><i class="fa fa-clock-o"></i> Postpone </a>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="card  card-box">
                                <div class="card-head">
                                    <header>DOCTORS LIST</header>
                                    <div class="tools">
                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
	                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
	                                    <a class="t-close btn-color fa fa-times" href="javascript:;"></a>
                                    </div>
                                </div>
                                <div class="card-body no-padding height-9">
                                    <div class="row">
                                        <ul class="docListWindow">
                                            <li>
                                                <div class="prog-avatar">
                                                    <img src="img/doc/doc1.jpg" alt="" width="40" height="40">
                                                </div>
                                                <div class="details">
                                                    <div class="title">
                                                        <a href="#">Dr.Rajesh</a> -(MBBS,MD)
                                                    </div>
                                                        <div>
                                                            <span class="clsAvailable">Available</span>
                                                        </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="prog-avatar">
                                                    <img src="img/doc/doc2.jpg" alt="" width="40" height="40">
                                                </div>
                                                <div class="details">
                                                    <div class="title">
                                                        <a href="#">Dr.Sarah Smith</a> -(MBBS,MD)
                                                    </div>
													<div>
														<span class="clsAvailable">Available</span>
													</div>
												</div>
                                            </li>
                                            <li>
                                                <div class="prog-avatar">
                                                    <img src="img/doc/doc3.jpg" alt="" width="40" height="40">
                                                </div>
                                                <div class="details">
                                                    <div class="title">
                                                        <a href="#">Dr.John Deo</a> - (BDS,MDS)
                                                    </div>
													<div>
														<span class="clsNotAvailable">Not Available</span>
													</div>
												</div>
                                            </li>
                                            <li>
                                                <div class="prog-avatar">
                                                    <img src="img/doc/doc4.jpg" alt="" width="40" height="40">
                                                </div>
                                                <div class="details">
                                                    <div class="title">
                                                        <a href="#">Dr.Jay Soni</a> - (BHMS)
                                                    </div>
													<div>
														<span class="clsOnLeave">On Leave</span>
													</div>
												</div>
                                            </li>
                                            <li>
                                                <div class="prog-avatar">
                                                    <img src="img/doc/doc5.jpg" alt="" width="40" height="40">
                                                </div>
                                                <div class="details">
                                                    <div class="title">
                                                        <a href="#">Dr.Jacob Ryan</a> - (MBBS,MS)
                                                    </div>
													<div>
														<span class="clsNotAvailable">Not Available</span>
													</div>
												</div>
                                            </li>
                                            <li>
                                                <div class="prog-avatar">
                                                    <img src="img/doc/doc6.jpg" alt="" width="40" height="40">
                                                </div>
                                                <div class="details">
                                                    <div class="title">
                                                        <a href="#">Dr.Megha Trivedi</a> - (MBBS,MS)
                                                    </div>
													<div>
														<span class="clsAvailable">Available</span>
													</div>
												</div>
                                            </li>
                                        </ul>
                                        <div class="text-center full-width">
                                            <a href="#">View all</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                     
                </div>
            </div>
            <!-- end page content -->
             
        </div>
        <!-- end page container -->
        <!-- start footer -->
        <div class="page-footer">
            <div class="page-footer-inner"> 2017 &copy; RedStar Hospital Theme By
            <a href="mailto:redstartheme@gmail.com" target="_top" class="makerCss">RT Theme maker</a>
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
            </div>
        </div>
        <!-- end footer -->
    </div>
    <!-- start js include path -->
    <script src="../assets/jquery.min.js" ></script>
    <script src="../assets/popper/popper.js" ></script>
    <script src="../assets/jquery.blockui.min.js" ></script>
	<script src="../assets/jquery.slimscroll.js"></script>
    <!-- bootstrap -->
    <script src="../assets/bootstrap/js/bootstrap.min.js" ></script>
    <script src="../assets/bootstrap-switch/js/bootstrap-switch.min.js" ></script>
    <!-- counterup -->
    <script src="../assets/counterup/jquery.waypoints.min.js" ></script>
    <script src="../assets/counterup/jquery.counterup.min.js" ></script>
    <!-- Common js-->
	<script src="../assets/app.js" ></script>
    <script src="../assets/layout.js" ></script>
    <script src="../assets/theme-color.js" ></script>
    <!-- material -->
    <script src="../assets/material/material.min.js"></script>
    <!-- chart js -->
    <script src="../assets/chart-js/Chart.bundle.js" ></script>
    <script src="../assets/chart-js/utils.js" ></script>
    <script src="../assets/chart-js/home-data.js" ></script>
    <!-- end js include path -->
  </body>
</html>