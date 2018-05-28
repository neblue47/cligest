<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Agendar Exame</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Agendamento</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Agendar Exame</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>Agendar Exame</header>
                                </div>
                                <div class="card-body" id="bar-parent">
                                    <form action="AgendaExameController" id="form_sample_1" class="form-horizontal" method="post">
                                        <div class="form-body">
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Nome do Paciente
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="primeironome" data-required="1" placeholder="digite primeiro nome" class="form-control input-height" value="${pac.nome } ${pac.apelido }" readonly="readonly" /> 
                                                    <input type="hidden" name="pac" value="${pac.FK_paciente}" readonly="readonly" /> 
                                                    <input type="hidden" name="usuario" value="${usuario}" readonly="readonly" /> 
                                                    </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Data do Exame
                                                    <span class="required"> * </span>
                                                </label>
                                                 <div class="col-md-5">
                                                    <div class="input-group date form_date " data-date="" data-date-format="dd/MM/yyyy" data-link-field="dtp_input2" data-link-format="dd/MM/yyyy" >
                                                	<input class="form-control input-height" size="10" placeholder="data da consulta" type="date" value="" name="dtConsulta" id="dtConsulta">
                                                	 
                                            		</div>
                                                </div>
                                            </div>
                                           <div class="form-group row">
                                            <label class="control-label col-md-3">
                                            			A partir de
                                            			<span class="required"> * </span>
                                            </label>
                                            
                                            <div class="col-md-5">
	                                            	<div class=" ">
		                                                <input class="form-control input-height" type="time"   id="example-time-input" name="hrConsulta">
		                                            </div>
		                                              
                                            </div>
                                        </div>
                                             
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Contacto
                                                </label>
                                                <div class="col-md-5">
                                                    <input name="number" type="text" placeholder="numero para contacto" class="form-control input-height" /> </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Grupo de Exame
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="grExames" required="required">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach var="dc" items="${lsGrupo }">
                                                    	<option value="${dc.id_grupo_analises_clinicas }">  ${dc.grupo_danalise }  </option>
                                                    	</c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Exame
                                                </label>
                                                <div class="col-md-5">
                                                    <table class="table table-striped table-bordered table-hover valign-middle" id="examsTable">
					                                        <thead>
					                                            <tr>
					                                                <th class="center"> Descricao do Exame </th>
					                                                <th class="center"> Selecione </th>
					                                            </tr>
					                                         </thead>
					                                          <tbody>
					                                          </tbody>
                                                    </table>
                                                </div>
                                            </div>
											<div class="form-group row">
												<label class="control-label col-md-3">Note 
												</label>
												<div class="col-md-5">
													<textarea name="notas" class="form-control" placeholder=" notas sobre estado do paciente " rows="5"></textarea>
												</div>
											</div>
										</div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="offset-md-3 col-md-9">
                                                    <button type="submit" class="btn btn-info">Gravar</button>
                                                    
                                                    <button type="button" class="btn btn-default" onclick="CancelAgenda()">Cancelar</button>
                                                </div>
                                            </div> 
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page content -->
  <script src="assets/jquery-1.7.1.min.js" ></script>   
 <script>
 var jq = $.noConflict();
 jq(document).ready(function(){
	 jq('select[name=grExames]').on('change',function(){
			$.ajax({
				type: 'GET',
				url : 'AjaxExameController',
				data: 'grExames='+$('select[name=grExames]').val(),
				success: function (dados){
					jq("#examsTable tbody tr").remove();
					var pegados = dados.split(";");
					if(dados!='')
					{
						
						for(var i=0;i<pegados.length-1;i++)
						{
							jq("#examsTable tbody")
							    .append("<tr>"+
		                                    "<td class='center'><div>"+pegados[i].split("|")[1]+"</div></td> "+
		                                    "<td class='center' align='center'><div><input type='checkbox' name='id_exame' value='"+pegados[i].split("|")[0]+"'/> </div></td>"+
		                                 "</tr>");
						}
				}
					
				}
			});		
		})
	});
 
  function CancelAgenda()
  {
	  var enca = "";
	  swal({
		  title: "",
		  text: "Pretende Cancelar a operação?",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((willDelete) => {
		  if (willDelete) {
		   enca = "navegacaoag?mods=ag&pag=novoagen";
		  }  
		  location.href= enca; 
		});
	  
  }
 </script>
 
    <script src="assets/bootstrap-switch/js/bootstrap-switch.min.js" ></script>
    <script src="assets/bootstrap-datepicker/js/bootstrap-datetimepicker.js"  charset="UTF-8"></script>
    <script src="assets/bootstrap-datepicker/js/bootstrap-datetimepicker-init.js"  charset="UTF-8"></script>
