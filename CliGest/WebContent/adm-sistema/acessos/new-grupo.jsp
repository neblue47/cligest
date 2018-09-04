<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Serviço</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="navegacao?mods=ng">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Acessos</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Novo Grupo</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="card card-box">
                                <div class="card-head">
                                    <header>Criar Novo Grupo</header>
                                </div>
                                <div class="card-body" id="bar-parent">
                                    <form action="AgendaConsultaController" id="form_sample_1" class="form-horizontal" method="post">
                                        <div class="form-body">
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Descrição
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="servico" data-required="1"  class="form-control input-height" value=" "   />
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="control-label col-md-3">Preço
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <input type="text" name="servico" data-required="1"  class="form-control input-height" value=" "   />
                                                </div>
                                            </div>
                                             <div class="form-group row">
                                                <label class="control-label col-md-3">Especialidade
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="funcao">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach items="${lsEspm}" var="at">
                                                         <option value="${at.especialidade }">${at.nomeEsp }</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                             </div>
                                             <div class="form-group row">
                                                <label class="control-label col-md-3">Taxa
                                                    <span class="required"> * </span>
                                                </label>
                                                <div class="col-md-5">
                                                    <select class="form-control input-height" name="funcao">
                                                        <option value="">Selecione...</option>
                                                        <c:forEach items="${lsEspm}" var="at">
                                                         <option value="${at.especialidade }">${at.nomeEsp }</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                             </div> 
										</div>
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
  function CancelAgenda()
  {
	 
	  
  }
 </script>