<div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <form action="">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header ">
                <h4 class="modal-title w-100 font-weight-bold">ALTERAR SENHA</h4>
            </div>
            <div class="modal-body mx-6">
                <div class="">
                    <input type="password" id="defaultForm-pass-at" class="form-control validate" name="senhaAt" autocomplete="off">
                    <label data-error="wrong" data-success="right" for="defaultForm-email">Senha Actual</label>
                </div>
                <div class="">
                    <input type="password" id="defaultForm-pass-nv" class="form-control validate" name="senhaNv" autocomplete="off">
                    <label data-error="wrong" data-success="right" for="defaultForm-pass">Nova Senha</label>
                </div>
			<div class="pull-right">
				<label>Confirmar</label>
                <input type="checkbox"  required="required" >
            </div>
            </div>
            <div class="modal-footer d-flex justify-content-right">
                <button class="btn btn-danger">Gravar</button>
                <button class="btn btn-default"  data-dismiss="modal" > Fechar</button>
            </div>
        </div>
    </div>
    </form>
  </div>