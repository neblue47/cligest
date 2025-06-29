
<form class="row   m-t-10">
	<div class="card-head">Doenças Diagnosticadas </div>
	<div class="col-lg-10 col-md-12 col-sm-8 col-xs-12  "> 
		<select class="form-control " name="diagnotico" id="diagnotico"	>
			<option disabled selected>pesquisar por doenças no CID</option>
		</select>
	</div>
	<div class="col-lg-1 col-md-12 col-sm-2 col-xs-12">
		<button type="button" onclick="addNoCarrinhoDg()" class="btn btn-success btn-sm">	<i class="fa fa-plus"></i></button>
	</div>
</form>
<div class="table-scrollable">
	<table
		class="table table-striped table-bordered table-hover table-checkable order-column valign-middle" 		id="tableHips">
		<thead>
			<tr>
				
				<th>CID</th>
				<th>Descrição</th>
				<th class="center">#</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<div>
	<div class="card-head">Observação</div>
	<textarea name="objectivo" class="form-control" rows="10">${fisicos.objectivo_geral  }</textarea>
</div>
<p></p>
<input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
<input type="hidden" name="funInt" value="${usuario}">
<input type="hidden" name="conInt" value="${conslt}">
<div class="pull-right">
	<button type="submit" class="btn btn-success btn-sm" name="salvar">
		Gravar</button>
	<button type="button" class="btn btn-primary btn-sm"
		onclick="limparH()">
		<span class="fa fa-eraser" aria-hidden="true"></span> Limpar
	</button>
</div>