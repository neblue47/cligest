<form name="form1" method="post" action="AntecedenteController">
				<div >
				 	<span> Pessoal </span>
		  			<div id="agenda-perfil">	
		      		 	<textarea name="descricao" id="antecedente" class="form-control" required="required" rows="10" > </textarea>
		      		 </div>
				</div> 
				<p></p>
				<div class="pull-right">
				  <button type="button" class="btn btn-primary btn-sm"  >
					  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
				  </button>
				</div>	
				<br>
				 
			 	<div >
				 	<span> Familiar </span>
		  			<div id="agenda-perfil">	
		      		 	<textarea name="historia" id="historial" class="form-control" required="required" rows="10"> </textarea>
		      		</div>
				</div> 
				<p></p>
				<input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
			    <input type="hidden" name="funInt" value="${usuario}">
			    <input type="hidden" name="conInt" value="${conslt}">
				<div class="pull-right">
			   	  <button type="submit" class="btn btn-success btn-sm" name="salvar">
					    Gravar
				  </button>
				  <button type="button" class="btn btn-primary btn-sm" onclick="limparH()">
					  <span class="fa fa-eraser" aria-hidden="true"></span> Limpar
				  </button>
				</div>	
</form>