<!DOCTYPE html>
<html lang="pt">
<head>
 <%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<link href='theme/css/fullcalendar.min.css' rel='stylesheet' />
<link href='theme/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<link href='theme/css/personalizado.css' rel='stylesheet'  />
<script src='assets/fullcalendar/moment.min.js'></script>
<script src='assets/fullcalendar/jquery.min.js'></script>
<script src='assets/fullcalendar/fullcalendar.min.js'></script>
<script src='assets/locale/pt.js'></script>
<script src="assets/jquery-1.7.1.min.js" ></script>   
 <script>
 var jq = $.noConflict();

 jq(document).ready(function() {
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
	      events: [
			        {
			          title: 'Long Event',
			          start: '2018-04-07',
			          end: '2018-04-10'
			        }        
	              ]
	    	});
	jq.ajax({
		type: 'POST',
		url : 'AjaxExameController',
		data: 'dados=1',
		success: function (dados){
			var pegados = dados.split(";");
			if(dados!='')
			{		
				console.log(pegados);
		    }
		}
	});
    
  });


</script>
 
</head>
<body>

  <div id='calendar'></div>

</body>
</html>
