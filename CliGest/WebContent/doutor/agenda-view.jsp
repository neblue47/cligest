<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link href='theme/css/fullcalendar.min.css' rel='stylesheet' />
<link href='theme/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<link href='theme/css/personalizado.css' rel='stylesheet'  />

<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Novo Doutor</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Doutores</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Novo Doutor</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>Dados do Doutor</header>
                                </div>
                                <div class="card-body" id="bar-parent">
                                	<div id='calendar'></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page content -->
      
<script src='assets/fullcalendar/moment.min.js'></script>
<script src='assets/fullcalendar/jquery.min.js'></script>
<script src='assets/fullcalendar/fullcalendar.min.js'></script>
<script src='assets/locale/pt.js'></script>
 <script src="assets/jquery-1.7.1.min.js" ></script>   
     <script>
	 var jq = $.noConflict();
	 var arrayDados = "";
	 jq(document).ready(function() {
			jq.ajax({
				type: 'POST',
				url : 'AjaxExameController',
				data: 'dados=1',
				success: function (dados){
					if(dados!='')
					{	
						arrayDados = dados.split(";");
						console.log(arrayDados);
				    }
				}
			});
		$('#calendar').fullCalendar({
		      header: {
		        left: 'prev,next today',
		        center: 'title',
		        right: 'month,agendaWeek,agendaDay,listWeek'
		      },
		      defaultDate: new Date(),
		      navLinks: true, // can click day/week names to navigate views
		      editable: true,
		      eventLimit: true, // allow "more" link when too many events
		      events:eventos.json
		    	});
	
	    
	  });

</script> 
