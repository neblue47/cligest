<style>
.table tbody {
  height: 230px;
  overflow-y: auto;
  width: 100%;
}
</style>
<div class="row   m-t-10">
	<div class="card-head">Hipoteses de Doenças</div>
	<div class="col-lg-10 col-md-12 col-sm-8 col-xs-12  ">
		<input type="text" class="form-control " name="hipotese" id="hipotese"
			placeholder="pesquisar por doenças no CID">
	</div>
	<div class="col-lg-1 col-md-12 col-sm-2 col-xs-12">
		<button type="button" onclick="addNoCarrinhoHp()"
			class="btn btn-success btn-sm">
			<i class="fa fa-plus"></i>
		</button>
	</div>
</div>
<form method="get" action="HipoteseDiagnosticoController">
<div class="table-scrollable">
	<table
		class="table table-striped table-bordered table-hover table-checkable order-column valign-middle"
		id="tableHips">
		<thead>
			<tr align="center">
				
				<th>Descrição</th>
				<th>CID</th>
				<th class="center">#</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>

<div>
	<div class="card-head">Observação</div>
	<textarea name="descricao" class="form-control" rows="10"></textarea>
</div>
<p></p>
<input type="hidden" name="pacInt" value="${perfil.FK_paciente}">
<input type="hidden" name="funInt" value="${usuario}">
<input type="hidden" name="conInt" value="${param.codc}">
<div class="pull-right">
	<button type="submit" class="btn btn-success btn-sm" name="salvar">
		Gravar</button>
	<button type="button" class="btn btn-primary btn-sm"
		onclick="limparH()">
		<span class="fa fa-eraser" aria-hidden="true"></span> Limpar
	</button>
</div>
</form>
<script>
function addNoCarrinhoHp()
{
		$.ajax({
			type: 'GET',
			url : 'AjaxCarrinhoController',
			data: 'addhipotese='+extrairAcentos($('#hipotese').val()),
			contentType: 'text/plain; charset=UTF-8',
			success: function (dados){
				if(dados!='')
				{
					$('#hipotese').val('');	
					$("#tableHips tbody")
						    .append("<tr class='center'>"+
	                                    "<td class='center'>"+dados.split(';')[0]+"<input type='hidden' name='hipotese' value="+dados.split(';')[0]+" class='form-control' style='width: 100px; text-align: center;'/> </td> "+
	                                    "<td class='center'>"+dados.split(';')[1]+" </td> "+
	                                    "<td class='center'><a href='#' title='eliminar' id='trlinha'><i class='fa fa-times' aria-hidden='true'></i></a></td>"+
	                                 "</tr>");
			    }
				
			}
		});	
}
</script>