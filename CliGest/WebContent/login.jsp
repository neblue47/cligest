<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
    <meta name="description" content="Responsive Admin Template" />
    <meta name="author" content="RedstarHospital" />
    <title>CLI-GEST | Sistema Integrado de Gestao Clinicas</title>
    <!-- google font -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
	<!-- icons -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- bootstrap -->
    
	<link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- style -->
    <link rel="stylesheet" href="theme/css/login.css">
	<!-- favicon -->
    <link rel="shortcut icon" href="theme/img/favicon.ico" /> 
</head>
<body>
    <!-- logo start -->
      <div class="form-title">
           CLI-GEST 
      </div>
     <!-- logo end -->
    <!-- Login Form-->
    <div class="login-form text-center">
        <div class="toggle"> 
        </div>
        <div class="form formLogin">
            <h2>Entrar no Sistema</h2>
            <form method="post" action="LoginController">
                <input type="text" placeholder="Username" name="usuario"/>
                <input type="password" placeholder="Password" name="senha"/>
                
                <button>Login</button>
                
                <div class="forgetPassword"><a href="javascript:void(0)">Esqueceu sua senha?</a>
                </div>
            </form>
        </div>
        <div class="form formRegister">
             
        </div>
        <div class="form formReset">
            <h2>Reset your password?</h2>
            <form>
                <input type="email" placeholder="Email Address" />
                <button>Send Verification Email</button>
            </form>
        </div>
    </div>
    <!-- start js include path -->
    <script src="assets/jquery.min.js" ></script>
    <script src="assets/login.js"></script>
    <script src="assets/pages.js" ></script>
    <!-- end js include path -->
</body>
</html>