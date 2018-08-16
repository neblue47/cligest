<form name="form1" method="post" action="ExamesFisicosController">
				 <div >
	       	   		<span>Exame Objectivo Geral</span>
	      		 	<textarea name="objectivo" id="objectivo" class="form-control" >${fisicos.objectivo_geral  }</textarea>
	      		 </div>
	      		 <div >
	       	   		<span>Cabeca</span>
	      		 	<textarea name="cabecao" id="cabecao" class="form-control">${fisicos.exa_cabeca }</textarea>
	      		 </div>
	      		 <div>
	       	   		<span>Pescoco</span>
	      		 	<textarea name="pescoco" id="pescoco" class="form-control">${fisicos.exa_pescoco }</textarea>
	      		 </div>
	      		  <div>
	       	   		<span>Torax</span>
	      		 	<textarea name="torax" id="torax" class="form-control">${fisicos.exa_torax }</textarea>
	      		 </div>
	      		 <div>
	       	   		<span>Abdomen</span>
	      		 	<textarea name="abdomem" id="abdomem" class="form-control">${fisicos.exa_abdomen }</textarea>
	      		 </div>	
	      		 <div>
	       	   		<span>Genito Urinário</span>
	      		 	<textarea name="urinatio" id="urinatio" class="form-control">${fisicos.exa_urinario }</textarea>
	      		 </div>
	      		 <div>
	       	   		<span>Membros Superiores</span>
	      		 	<textarea name="membrosup" id="membrosup" class="form-control">${fisicos.exa_membSup }</textarea>
	      		 </div>
	      		 
	      		  <div>
	       	   		<span>Membros Inferiores</span>
	      		 	<textarea name="membrosinf" id="membrosinf" class="form-control">${fisicos.exa_membInf }</textarea>
	      		 </div>
	      		<div >
	       	   		<span>Sistema Nervoso</span>
	      		 	<textarea name="sistema" id="sistema" class="form-control">${fisicos.sistema_nervoso }</textarea>
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