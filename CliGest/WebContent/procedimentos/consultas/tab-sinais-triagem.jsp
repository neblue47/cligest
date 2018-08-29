	<div class="body">
                    <div class="user-btm-box">
                            <div class="row   m-t-10">
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Temperatura</strong>
                                   <input type="text" class="form-control " name="temperatura" placeholder="temperatura" value="${sinais.valor_tmp }" readonly="readonly">  
                                </div>
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Respiração</strong>
                                 <input type="text" class="form-control " name="respiracao" placeholder="respiracao" value="${sinais.valor_resp }" readonly="readonly">
                                </div>
                            </div>
                            <div class="row   m-t-10">
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Pulso</strong>
                                    <input type="text" class="form-control " name="pulso" placeholder="pulso" value="${sinais.valor_pulso }" readonly="readonly">
                                </div>
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>T.A Sistolica</strong>
                                <input type="text" class="form-control " name="sistolica" placeholder="sistolica" value="${sinais.tensao_sistolica }" readonly="readonly">
                                </div>
                            </div>
                            <div class="row   m-t-10">
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>T.A Diastolica</strong>
                                    <input type="text" class="form-control " name="diastolica" placeholder="diastolica" value="${sinais.tensao_diastolica }" readonly="readonly">
                                </div>
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>Peso</strong>
                                <div class="input-group">
                                                <input type="text" class="form-control " name="peso" id="peso"  placeholder="peso" value="${sinais.peso }" readonly="readonly"> </div>
                                </div>
                            </div>
                            <div class="row m-t-10">
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12 b-r"><strong>Altura</strong>
                                <div class="input-group">
                                                <input type="text" class="form-control " name="altura" id="altura" placeholder="altura" value="${sinais.altura }" readonly="readonly"> </div>
                                </div>
                                <div class="col-lg-6 col-md-12 col-sm-6 col-xs-12"><strong>IMC</strong>
                                   <input type="text" class="form-control " name="imc" id="imc" placeholder="imc" value="${sinais.imc }" readonly="readonly"> </div>
                                </div>
                            </div>
                            <div class="row  m-t-10">
                                <div class="col-lg-12  "><strong>Estado</strong>
                                <input type="text" class="form-control " name="status"   placeholder="imc" value="${sinais.altura }" readonly="readonly"> </div> 
                                </div>
                             
                            </div>
                            <input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
						    <input type="hidden" name="funInt" value="${usuario}">
						    <input type="hidden" name="conInt" value="${conslt}">
                            <div class="row  m-t-10">
                                <div class="col-lg-12">
                                 <label class="control-label  "><strong>Nota</strong></label>
									<textarea name="nota" class="form-control " placeholder=" Notas" rows="5"></textarea>
                               </div>
                	       </div>
           		 