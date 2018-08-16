<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
<style>
option.um     {background-color: blue;  color: white;} 
option.dois   {background-color: green; color: white;} 
option.tres   {background-color: yellow; color: black; } 
option.quatro {background-color: orange; color: white;} 
option.cinco  {background-color: red; color: white;} 
</style>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Triagem</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="index.html">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Procedimentos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Triagem</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
	                    <div class="col-md-4 col-xs-12">
	                        <div class="white-box">
								<div class="cardbox">
			                    <div class="header">
			                        <h4 class="font-bold center">DADOS DO PACIENTE</h4>
			                    </div>
				                    <div class="body">
				                        <div class=" ">
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Nome</strong>
				                                        <p>${perfil.nome } ${perfil.nomem } ${perfil.apelido }</p>
				                                    </div>
				                                     
				                                </div>
				                                 <div class="row text-center m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Genero</strong>
				                                        <p>${perfil.nomegenero }</p>
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Idade</strong>
				                                        <p>${perfil.idade }</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Contacto</strong>
				                                        <p>${perfil.editfone }</p>
				                                    </div>
				                                </div>
				                                <!-- /.row -->
				                                <hr>
				                                <!-- .row -->
				                                <div class="row text-center m-t-10">
				                                    <div class="col-md-12"><strong>Morada</strong>
				                                        <p>${perfil.endereco }
				                                          </p>
				                                    </div>
				                                </div>
				                            </div>
				                    	</div>
			                		</div>
	                        	</div>
	                   	 </div>
	                    <div class="col-md-8 col-xs-12">
	                     <form action="TriagemController" id="form_sample_1" class="form-horizontal" method="post">
	                     <div class="white-box">
								<div class="cardbox">
			                    <div class="card-head">
                                    <header>Dados da Triagem</header>
                                </div>
				                    <div class="body">
				                        <div class="user-btm-box">
				                                <div class="row   m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Temperatura</strong>
				                                       <input type="text" class="form-control " name="temperatura" placeholder="temperatura">  
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Respiração</strong>
				                                     <input type="text" class="form-control " name="respiracao" placeholder="respiracao">
				                                    </div>
				                                </div>
				                                <div class="row   m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Pulso</strong>
				                                        <input type="text" class="form-control " name="pulso" placeholder="pulso">
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>T.A Sistolica</strong>
				                                    <input type="text" class="form-control " name="sistolica" placeholder="sistolica">
				                                    </div>
				                                </div>
				                                <div class="row   m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>T.A Diastolica</strong>
				                                        <input type="text" class="form-control " name="diastolica" placeholder="diastolica">
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Peso</strong>
				                                    <div class="input-group">
                                                        <input type="text" class="form-control " name="peso" id="peso"  placeholder="peso" onblur="retormaIMC()"> </div>
				                                    </div>
				                                </div>
				                                <div class="row m-t-10">
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Altura</strong>
				                                    <div class="input-group">
                                                        <input type="text" class="form-control " name="altura" id="altura" placeholder="altura" onblur="retormaIMC()"> </div>
				                                    </div>
				                                    <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>IMC</strong>
				                                       <input type="text" class="form-control " name="imc" id="imc" placeholder="imc" readonly="readonly"> </div>
				                                    </div>
				                                </div>
				                                <div class="row  m-t-10">
				                                    <div class="col-lg-12  "><strong>Estado</strong>
				                                    <select class="form-control  center" name="estado"  >
                                                        <option value="">Selecione...</option>
                                                        <option class="um" value="1">Não urgente</option>
                                                        <option class="dois" value="2">Pouco Urgente</option>
                                                        <option class="tres" value="3">Urgente</option>
                                                        <option class="quatro" value="4">Muito Urgente</option>
                                                        <option class="cinco" value="5">Emergente</option>
                                                        </select>  
				                                    </div>
				                                 
				                                </div>
				                                <input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
											    <input type="hidden" name="funInt" value="${usuario}">
											    <input type="hidden" name="serInt" value="${param.cods}">
											    <input type="hidden" name="conInt" value="${param.codc}">
				                                <div class="row  m-t-10">
				                                    <div class="col-lg-12">
				                                     <label class="control-label  "><strong>Nota</strong></label>
													<textarea name="nota" class="form-control " placeholder=" Notas" rows="5"></textarea>
				                                   </div>
				                    	       </div>
			                		</div>
			                		<div class="form-actions">
                                            <div class="row">
                                                <div class="offset-md-4 col-md-8">
                                                    <button type="submit" class="btn btn-info">Gravar</button>
                                                    <a href="navegacaopd?mods=pd&pag=triapac" class="btn btn-default  ">
														Cancelar
													</a>
                                                </div>
                                            	</div>
	                        		</div>
	                    </div>
	                    </div>
	                    </form>
                    </div>
                </div>
                <!-- end page content -->
               
