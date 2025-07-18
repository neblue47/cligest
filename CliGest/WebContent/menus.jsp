<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="assets/jquery.min.js" ></script> 
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
	                                    <img src="theme/img/pop-dc.jpg" class="img-circle user-img-circle" alt="User Image" />
	                                </div>
	                                <div class="pull-left info">
	                                    <p>   <i class=" fa fa-user-md"></i> ${nomeUsa} </p>
	                                    <a href="#"><i class="fa fa-circle user-online"></i><span class="txtOnline"> Logado </span></a>
	                                </div>
	                            </div>
	                        </li>
							<!-- start sidebar menu ADMINISTRACA�O -->
							<c:forEach var="md" items="${AcessoMudulos}">
								 <c:if test="${md.codCl eq 'ad'}" >
								 	<li class="nav-item start ${admings}">
		                            	<a href="#" class="nav-link nav-toggle ">
		                                <i class="material-icons fa fa-cogs"></i>
		                                <span class="title"> ${md.modulo} </span>
		                                <span class="arrow"></span>
		                            	</a>
			                             <ul class="sub-menu">
											   <li class="nav-item  ">
				                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Acessos</span></a>
													 <ul class="sub-menu">
														<li class="nav-item  ">
															<a href="navegacao?mods=ad&pag=usersprv" class="nav-link "> <span class="title">Privilegios</span></a>
														</li>
														<li class="nav-item  ">
															<a href="navegacao?mods=ad&pag=usersgrp" class="nav-link "> <span class="title">Grupos</span></a>
														</li>
														<li class="nav-item  ">
														 	<a href="navegacao?mods=ad&pag=users" class="nav-link nav-toggle"> <span class="title">Utilizadores</span></a>
			                                  		    </li>
													</ul>
				                                </li>
			                                  <li class="nav-item  ">
				                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Cadastros Basicos</span></a>
													 <ul class="sub-menu">
														<li class="nav-item  "> 
					                                    <a href="navegacao?mods=ad&pag=lscons" class="nav-link nav-toggle"> <span class="title">Servi�os</span></a>
					                                    </li>
					                                    <li class="nav-item  ">
					                                    	<a href="navegacao?mods=ad&pag=lsesp" class="nav-link "> <span class="title">Especialidades</span></a>
														</li>
													</ul>
				                                </li>
			                                    <li class="nav-item  ">
				                                    <a href="navegacao?mods=ad&pag=doc" class="nav-link nav-toggle"> <span class="title">Medicos</span></a>
			                                    </li>
	                            		</ul>
		                             </li>
								 </c:if>
				    			 <c:if test="${md.codCl eq 'ag'}" >
				    			 	 <li class="nav-item ${agendm }">
		                            	<a href="#" class="nav-link nav-toggle">
		                                <i class="material-icons fa fa-calendar"></i>
		                                <span class="title"> ${md.modulo} </span>
		                                <span class="arrow"></span>
		                            	</a>
			                            <ul class="sub-menu">
		                                <li class="nav-item  ">
		                                    <a href="navegacaoag?mods=ag&pag=novoagen" class="nav-link "> <span class="title">Agendar</span>
		                                    </a>
		                                </li>
		                                
		                                <li class="nav-item  ">
		                                    <a href="navegacaoag?mods=ag&pag=listagen" class="nav-link "> <span class="title">Consultas</span>
		                                    </a>
		                                </li>
		                                <li class="nav-item  ">
		                                    <a href="navegacaoag?mods=ag&pag=listagexa" class="nav-link "> <span class="title">Exames </span>
		                                    </a>
		                                </li>
		                                <li class="nav-item  ">
	                                    	<a href="navegacaoag?mods=ag&pag=pac" class="nav-link "> <span class="title">Pacientes</span></a>
		                                </li>
		                               </ul>
		                             </li>
				    			 </c:if>
				    			 <c:if test="${md.codCl eq 'fat'}" >
				    			 	 <li class="nav-item ${pagmt }  ">
		                            	<a href="#" class="nav-link nav-toggle">
		                                <i class="material-icons fa fa-money"></i>
		                                <span class="title"> ${md.modulo} </span>
		                                <span class="arrow"></span>
		                            	</a>
			                             <ul class="sub-menu">
										   <li class="nav-item  ">
			                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Consultas</span>
			                                    </a>
												 <ul class="sub-menu">
													<li class="nav-item  ">
														<a href="navegacaoft?mods=fat&pag=listacon" class="nav-link "> <span class="title">Pacientes</span>
														</a>
													</li>
													<li class="nav-item  ">
														<a href="navegacaoft?mods=fat&pag=listaconHis" class="nav-link "> <span class="title">Historico</span>
														</a>
													</li>
												</ul>
			                                </li>
			                                <li class="nav-item  ">
			                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Exames</span>
			                                    </a>
												 <ul class="sub-menu">
													<li class="nav-item  ">
														<a href="navegacaoft?mods=fat&pag=listaexa" class="nav-link "> <span class="title">Pacientes</span>
														</a>
													</li>
													<li class="nav-item  ">
														<a href="navegacaoft?mods=fat&pag=listaexaHis" class="nav-link "> <span class="title">Historico</span>
														</a>
													</li>
												</ul>
			                                </li>
											 <li class="nav-item  ">
			                                    <a href="navegacaoft?mods=fat&pag=listapg" class="nav-link nav-toggle"> <span class="title">Pagamentos</span>
			                                    </a>
			                                </li>
	                            		</ul>
		                             </li>
				    			 </c:if>
				    			 <c:if test="${md.codCl eq 'lab'}">
				    			 	 <li class="nav-item start  ">
		                            	<a href="#" class="nav-link nav-toggle">
		                                <i class="material-icons fa fa-ambulance"></i>
		                                <span class="title"> ${md.modulo} </span>
		                                <span class="arrow"></span>
		                            	</a>
			                             <ul class="sub-menu">
										   <li class="nav-item  ">
			                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Atendimentos</span>
			                                    </a>
												 <ul class="sub-menu">
													<li class="nav-item  ">
														<a href="#navegacaopd?mods=pd&pag=atdpac" class="nav-link "> <span class="title">Pacientes</span>
														</a>
													</li>
													<li class="nav-item  ">
														<a href="#navegacaopd?mods=pd&pag=atdpacHis" class="nav-link "> <span class="title">Historico</span>
														</a>
													</li>
												</ul>
			                                </li>
			                                <li class="nav-item  ">
			                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Exames</span>
			                                    </a>
												 <ul class="sub-menu">
													<li class="nav-item  ">
														<a href="#" class="nav-link "> <span class="title">Add Exame</span>
														</a>
													</li>
													<li class="nav-item  ">
														<a href="#" class="nav-link "> <span class="title">Add Grupo</span>
														</a>
													</li>
													<li class="nav-item  ">
														<a href="#" class="nav-link "> <span class="title">Lista de Exames</span>
														</a>
													</li>
												</ul>
			                                </li>
											 <li class="nav-item  ">
			                                    <a href="#" class="nav-link nav-toggle"> <span class="title">Outros Servi�os</span>
			                                    </a>
												 <ul class="sub-menu">
													<li class="nav-item  ">
														<a href="#" class="nav-link "> <span class="title">Vacinas</span>
														</a>
													</li>
													<li class="nav-item  ">
														<a href="#" class="nav-link "> <span class="title">Imagiologia</span>
														</a>
													</li>
													<li class="nav-item  ">
														<a href="#" class="nav-link "> <span class="title">Hemoterapia</span>
														</a>
													</li>
												</ul>
										
			                                </li>
	                            		</ul>
		                             </li>
				    			 </c:if>
				    			 <c:if test="${md.codCl eq 'pd'}">
				    			  
				    			 	 <li class="nav-item ${procmt }  ">
		                            	<a href="#" class="nav-link nav-toggle">
		                                <i class="material-icons fa fa-stethoscope "></i>
		                                <span class="title"> ${md.modulo} </span>
		                                <span class="arrow"></span>
		                            	</a>
		                            	
			                             <ul class="sub-menu">
			                                <li class="nav-item ">
			                                    <a href="navegacaopd?mods=pd&pag=triapac" class="nav-link ">
			                                        <span class="title">Triagem</span>
			                                        <span class="selected"></span>
			                                    </a>
			                                </li>
		                             		 <li class="nav-item  ">
			                                    <a href="navegacaopd?mods=pd&pag=conspac" class="nav-link ">
			                                        <span class="title">Consultas</span>
			                                        <span class="selected"></span>
			                                    </a>
			                                </li>
										   <li class="nav-item  ">
			                                    <a href="navegacaopd?mods=pd&pag=hstconspac" class="nav-link ">
			                                        <span class="title">Hist. Atendimento</span>
			                                        <span class="selected"></span>
			                                    </a>
			                                </li>
											
	                            		</ul>
		                             </li>
				    			 </c:if>
				    			 <c:if test="${md.codCl eq 'rel'}">
				    			 	 <li class="nav-item start  ">
		                            	<a href="#" class="nav-link nav-toggle">
		                                <i class="material-icons fa fa-archive"></i>
		                                <span class="title"> ${md.modulo} </span>
		                                <span class="arrow"></span>
		                            	</a>
			                            <ul class="sub-menu">
			                                <li class="nav-item active open  ">
			                                    <a href="index.html" class="nav-link ">
			                                        <span class="title">Estatisticas</span>
			                                        <span class="selected"></span>
			                                    </a>
			                                </li>
		                               </ul>
		                             </li>
				    			 </c:if>
							</c:forEach>
	                         
	                        <li class="nav-item  ${cidn}">
	                            <a href="#" class="nav-link nav-toggle"> <i class="material-icons material-icons fa fa-indent"></i>
	                                <span class="title">CID</span> <span class="arrow"></span>
	                            </a>
	                            <ul class="sub-menu">
	                                <li class="nav-item  ">
	                                    <a href="cidnavegacao?mods=cdn&pag=cids" class="nav-link "> <span class="title">CIDs </span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="cidnavegacao?mods=cdn&pag=cidsgrp" class="nav-link "> <span class="title">Grupos CIDs 	</span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="cidnavegacao?mods=cdn&pag=cidscat" class="nav-link "> <span class="title">Categorias CIDs </span>
	                                    </a>
	                                </li>
	                                <li class="nav-item  ">
	                                    <a href="cidnavegacao?mods=cdn&pag=cidscap" class="nav-link "> <span class="title">Capitulos CIDs </span>
	                                    </a>
	                                </li>
	                            </ul>
	                        </li>
	                         
	                        
	                        
	                    </ul>
	                </div>
                </div>
            </div>
 
