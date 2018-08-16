<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
  
    <title>CLI-GEST | Sistema Integrado de Gestao Clinicas</title>
      
    <!-- bootstrap -->
    
	<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- style -->
    <link rel="stylesheet" href="theme/css/login.css">
    <link rel="stylesheet" href="theme/css/bootstrap.min-login.css">
    <link rel="stylesheet" href="theme/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="theme/css/font-awesome.min.css">
    <link rel="stylesheet" href="theme/css/extra_pages.css">
	<!-- favicon -->
    <link rel="shortcut icon" href="theme/img/favicon.ico" /> 
</head>
<body>
    <div class="limiter">
		<div class="container-login100 page-background">
			<div class="wrap-login100">
				<form class="login100-form validate-form" method="post" action="LoginController">
					<span class="login100-form-logo">
<!-- 						<img alt="" src="theme/img/clilogo.png"> -->
					</span>
					<span class="login100-form-title p-b-34 p-t-27">
						Entrar no CliGest
					</span>
					<div class="wrap-input100 validate-input" data-validate="Enter username">
						<input class="input100" type="text" name="usuario" placeholder="Utilizador ID">
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
					</div>
					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" type="password" name="senha" placeholder="Senha">
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
					</div>
					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1">
							Lembrar-me
						</label>
					</div>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Entrar
						</button>
					</div>
					<div class="text-center p-t-30">
						<a class="txt1" href="#">
							Esqueceu a senha
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
    <!-- start js include path -->
    <script src="assets/jquery.min-login.js" ></script>
    <script src="assets/bootstrap.min.js" ></script>
    <script src="assets/extra-login.js"></script>
</body>
</html>