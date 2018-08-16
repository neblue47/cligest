<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt">
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta name="description" content="Responsive Admin Template" />
    <meta name="author" content="RedstarHospital" />
    <title>CLIN-GEST | Sistema Integrado de Gestao Clinicas</title>
    <!-- google font -->
<!--     <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" /> -->
	<!-- icons -->
<!--     <link href="assets/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" /> -->
    <link href="theme/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<!-- 	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> -->
	<!--bootstrap -->
	<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="assets/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
     <!-- data tables -->
    <link href="assets/datatables/plugins/bootstrap/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css"/>
    <!-- Material Design Lite CSS -->
	<link rel="stylesheet" href="assets/material/material.min.css">
	<link rel="stylesheet" href="theme/css/material_style.css">
	<!-- Theme Styles -->
    <link href="theme/css/theme_style.css" rel="stylesheet" id="rt_style_components" type="text/css" />
    <link href="theme/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <link href="theme/css/style.css" rel="stylesheet" type="text/css" />
    <link href="theme/css/responsive.css" rel="stylesheet" type="text/css" />
    <link href="theme/css/theme-color.css" rel="stylesheet" type="text/css" />
    <link href="theme/css/login.css" rel="stylesheet" type="text/css"/>
	<!-- favicon -->
    <link rel="shortcut icon" href="theme/img/favicon.ico" /> 
 </head>
 <!-- END HEAD -->
<body class="page-header-fixed sidemenu-closed-hidelogo page-content-white page-md header-dark dark-sidebar-color logo-dark dark-theme">
    <div class="page-wrapper">
        <!-- start header -->
        <div class="page-header navbar navbar-fixed-top">
            <div class="page-header-inner ">
                <!-- logo start -->
                <div class="page-logo">
                    <a href="navegacao?mods=ng">
                    <span class="logo-icon fa fa-stethoscope fa-rotate-45"></span>
                    <span class="logo-default" > CLIN-GEST </span> </a>
                </div>
                <!-- logo end -->
				<ul class="nav navbar-nav navbar-left in">
					<li><a href="#" class="menu-toggler sidebar-toggler"><i class="icon-menu"></i></a></li>
				</ul>
                  
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
                                <img alt="" class="img-circle " src="theme/img/dp.jpg" />
                                <span class="username username-hide-on-mobile"> ${nomeUsa} </span> 
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                
                                <li class="divider"> </li>
                                <li>
                                    <a href="lock_screen.html">
                                        <i class="icon-lock"></i> Alterar Senha
                                    </a>
                                </li>
                                <li>
                                    <a href="LoginController?acao=out">
                                        <i class="icon-logout"></i> Log Out </a>
                                </li>
                            </ul>
                        </li>
                        <!-- end manage user dropdown -->
                        
                    </ul>
                </div>
            </div>
        </div>
        <!-- end header -->
         
        <!-- start page container -->
        <div class="page-container">
        
 			<!-- start sidebar menu -->
 			<jsp:include page="menus.jsp" />
            <!-- end sidebar menu --> 
            
			<!-- start page content -->
            <c:if test="${param.mods eq 'ng' && empty param.pag}">
            	<jsp:include page="resumo.jsp" />
            </c:if>
            
            <!-- DOUTORES -->
            <c:if test="${param.mods eq 'ad' && param.pag eq 'doc'}">
            	<jsp:include page="doutor/all-doutores.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ad' && param.pag eq 'novodoc'}">
            	<jsp:include page="doutor/new-doutor.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ad' && param.pag eq 'editdoc'}">
            	<jsp:include page="doutor/edit-doutor.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ad' && param.pag eq 'profdoc'}">
            	<jsp:include page="doutor/profile-doutor.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ad' && param.pag eq 'agenddoc'}">
            	<jsp:include page="doutor/agenda-view.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ad' && param.pag eq 'profdoc'}">
            	<jsp:include page="doutor/profile-doutor.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ad' && param.pag eq 'profdoc'}">
            	<jsp:include page="doutor/profile-doutor.jsp" />
            </c:if>
            
           <!-- PACIENTES -->
            <c:if test="${param.mods eq 'ag' && param.pag eq 'pac'}">
            	<jsp:include page="pacientes/all-paciente.jsp" />
            </c:if>
             <c:if test="${param.mods eq 'ag' && param.pag eq 'novopac'}">
            	<jsp:include page="pacientes/new-paciente.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ag' && param.pag eq 'editpac'}">
            	<jsp:include page="pacientes/edit-paciente.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ag' && param.pag eq 'profpac'}">
            	<jsp:include page="pacientes/profile-paciente.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ag' && param.pag eq 'novoagen'}">
            	<jsp:include page="agendamento/all-paciente.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ag' && param.pag eq 'novoagenexa'}">
            	<jsp:include page="agendamento/new-agenda-exame.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ag' && param.pag eq 'novoagencon'}">
            	<jsp:include page="agendamento/new-agenda-consulta.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ag' && param.pag eq 'listagen'}">
            	<jsp:include page="agendamento/all-agendamento-consultas.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'ag' && param.pag eq 'listagexa'}">
            	<jsp:include page="agendamento/all-agendamento-exames.jsp" />
            </c:if>
            
             <!-- CONSULTAS TRIAGEM -->
            <c:if test="${param.mods eq 'pd' && param.pag eq 'triapac'}">
            	<jsp:include page="procedimentos/consultas/all-paciente.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'pd' && param.pag eq 'newtriar'}">
            	<jsp:include page="procedimentos/consultas/new-triagem.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'pd' && param.pag eq 'conspac'}">
            	<jsp:include page="procedimentos/consultas/all-consultas.jsp" /> 
            </c:if>
            <c:if test="${param.mods eq 'pd' && param.pag eq 'newcons'}">
            	<jsp:include page="procedimentos/consultas/new-consulta.jsp" /> 
            </c:if>
            
            <!-- URGENCIAS / EMERGENCIAS -->
            <c:if test="${param.mods eq 'pd' && param.pag eq 'atdconspac'}">
            	<jsp:include page="procedimentos/urgencias/all-paciente.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'pd' && param.pag eq 'triconspac'}">
            	<jsp:include page="procedimentos/urgencias/all-paciente.jsp" />
            </c:if>
            
              <!-- PAGEMENTOS - Consultas -->
            <c:if test="${param.mods eq 'fat' && param.pag eq 'listacon'}">
            	<jsp:include page="pagamentos/consultas/all-pacientes.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'fat' && param.pag eq 'pagarcon'}">
            	<jsp:include page="pagamentos/consultas/new-pagamento.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'fat' && param.pag eq 'listaconHis'}">
            	<jsp:include page="pagamentos/consultas/all-pacientesHis.jsp" />
            </c:if>
              <!-- PAGEMENTOS - Exames -->
            <c:if test="${param.mods eq 'fat' && param.pag eq 'listaexa'}">
            	<jsp:include page="pagamentos/exames/all-pacientes.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'fat' && param.pag eq 'pagarexa'}">
            	<jsp:include page="pagamentos/exames/new-pagamento.jsp" />
            </c:if>
            <c:if test="${param.mods eq 'pd' && param.pag eq 'listaexaHis'}">
            	<jsp:include page="pagamentos/exames/all-pacientesHis.jsp" />
            </c:if>
            <!-- end page content -->
             
        </div>
        <!-- end page container -->
        <!-- start footer -->
        <div class="page-footer">
            <div class="page-footer-inner"> 2018 &copy; Sistema Integrado de Gestao Clinicas
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
            </div>
        </div>
        <!-- end footer -->
    </div>
    
    <!-- start js include path -->
    
<!--     <script src="assets/jquery.min.js" ></script> -->
    <script src="assets/popper/popper.js" ></script>
    <script src="assets/jquery.blockui.min.js" ></script>
	<script src="assets/jquery.slimscroll.js"></script>
    <!-- bootstrap -->
    <script src="assets/bootstrap/js/bootstrap.min.js" ></script>
    <script src="assets/bootstrap-switch.min.js" ></script>
    <!-- counterup -->
    <script src="assets/counterup/jquery.waypoints.min.js" ></script>
    <script src="assets/counterup/jquery.counterup.min.js" ></script>
    <!-- Common js-->
	<script src="assets/app.js" ></script>
    <script src="assets/layout.js" ></script>
    <script src="assets/theme-color.js" ></script>
     <script src="assets/util-cligest.js" ></script>
    <!-- material -->
    <script src="assets/material/material.min.js"></script>
    <script src="assets/sweetalert.min.js" ></script>
    <c:if test="${param.mods eq 'ng' && empty param.pag}">
       <!-- chart js -->
	    <script src="assets/chart-js/Chart.bundle.js" ></script>
	    <script src="assets/chart-js/utils.js" ></script>
	    <script src="assets/chart-js/home-data.js" ></script>
    </c:if>
    
    
     <!-- Login js -->
<!--     <script src="assets/jquery.min.js" ></script> -->
<!--     <script src="assets/login.js"></script> -->
    <script src="assets/pages.js" ></script>
    <!-- end js include path -->
    
  </body>
</html>
