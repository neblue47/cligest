 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Atribuir Privilégios</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="navegacao?mods=ng">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Acessos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Privilégios</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>Atribuir Privilégios</header>
                                </div>
                                <div class="card-body" id="bar-parent">
                                    <form action="GrupoPrivilegiosController" id="form_sample_1" class="form-horizontal" method="post">
                                        <div class="form-body">
                                             <div class="form-group row">
                                                <label class="control-label col-md-3">Grupos
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-7">
                                                    <select class="form-control input-height" name="grupos">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach items="${lsGrupos }" var="at">
                                                         <option value="${at.id_grupo }">${at.nomgrupo }</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                             </div>
                                             <div class="form-group row">
                                                <label class="control-label col-md-3">Modulos
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-7">
                                                    <select class="form-control input-height" name="modulos">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach items="${lsModulos}" var="at">
                                                         <option value="${at.id_modulo }">${at.modulo }</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                             </div> 
                                             <div class="form-group row">
                                                <label class="control-label col-md-3">Funcionalidades
                                                <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-7">
                                                    <table class="table table-striped table-bordered table-hover valign-middle" id="examsTable">
					                                        <thead>
					                                            <tr>
					                                                <th class="center"> Funcionalidade </th>
					                                                <th class="center"> Descrição </th>
					                                                <th class="center"> Opção </th>
					                                            </tr>
					                                         </thead>
					                                          <tbody>
					                                          </tbody>
                                                    </table>
                                                </div>
                                            </div>
										</div>
										<input type="hidden" name="usuario" value="${usuario }">
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="offset-md-3 col-md-9">
                                                    <button type="submit" class="btn btn-info">Gravar</button>
                                                    <a href="navegacao?mods=ad&pag=lscons"   class="btn btn-default">Cancelar</a>
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
 
  <script>
 $(document).ready(function(){
	 $('select[name=modulos]').on('change',function(){
			$.ajax({
				type: 'GET',
				url : 'AjaxAgendaController',
				data: 'modulo='+$('select[name=modulos]').val()+'&grupo='+$('select[name=grupos]').val(),
				success: function (dados){
					$("#examsTable tbody tr").remove();
					var pegados = dados.split(";");
					if(dados!='')
					{
						
						for(var i=0;i<pegados.length-1;i++)
						{
							$("#examsTable tbody")
							    .append("<tr>"+
		                                    "<td class='center'><div>"+pegados[i].split("|")[1]+"</div></td> "+
		                                    "<td class='center'><div>"+pegados[i].split("|")[2]+"</div></td> "+
		                                    "<td class='center' align='center'><div><input type='checkbox' name='id_tela' value='"+pegados[i].split("|")[0]+"'/> </div></td>"+
		                                 "</tr>");
						}
				}
					
				}
			});		
		})
	});
 
 </script>