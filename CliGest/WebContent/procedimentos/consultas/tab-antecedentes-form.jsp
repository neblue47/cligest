<form name="form1" method="post" action="QueixaAtencedenteController">
				<div >
				 	<span> Pessoal </span>
		  			<div id="agenda-perfil">	
		      		 	<textarea name="ant_pessoal" id="antecedente" class="form-control" required="required" rows="10" > </textarea>
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
		      		 	<textarea name="ant_familiar" id="historial" class="form-control" required="required" rows="10"> </textarea>
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
