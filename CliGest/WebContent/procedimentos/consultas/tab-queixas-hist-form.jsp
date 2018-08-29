<form name="form1" method="get" action="QueixaAtencedenteController">
				<div >
				 	<span> Queixas Principais</span>
		  			<div id="agenda-perfil">	
		      		 	<textarea name="queixas" id="queixas1" class="form-control" required="required" rows="5" ></textarea>
		      		 </div>
				</div> 
				<p></p>
				<div class="pull-right">
				  <button type="button" class="btn btn-primary btn-sm" onclick="limparQ()">
					  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
				  </button>
				</div>	
				<br>
				 
			 	<div >
				 	<span> Historial da Doença Actual</span>
		  			<div id="agenda-perfil">	
		      		 	<textarea name="historial" id="historial" class="form-control" required="required" rows="5"></textarea>
		      		</div>
				</div> 
				<p></p>
				<input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
			    <input type="hidden" name="funInt" value="${usuario}">
			    <input type="hidden" name="conInt" value="${param.codc}">
				<div class="pull-right">
			   	  <button type="submit" class="btn btn-success btn-sm" name="salvar">
					    Gravar
				  </button>
				  <button type="button" class="btn btn-primary btn-sm" onclick="limparH()">
					  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
				  </button>
				</div>	
</form>
