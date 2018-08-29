<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />
  
    <title>CLI-GEST | Sistema Integrado de Gestão Clinicas</title>
    <meta name="author" content="KIQUIM IMMANENT"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="theme/css-login/bootstrap.min.css"/>
    <link rel="stylesheet" href="theme/css-login/font-awesome.min.css"/>
    <link rel="stylesheet" href="theme/css-login/style.css"/>
    <!-- favicon -->
    <link rel="shortcut icon" href="theme/img/logocligest1.ico" /> 
    </head>
    <body>
        <h1 class="hidden">CLIGEST</h1>
        <section class="login bg">
            <div class="container">
                <div class="row">                    
                    <div class="col-lg-5 col-md-6 col-sm-8 col-xs-12 constum-margin">
                        <form class="bg-form" method="post" action="LoginController">
                            <img src="theme/img/logocligest_1.png" class="img-responsive" alt="CLIGEST" title="CLIGEST"/>
                            <h1>Log in</h1>
                            <div class="form-group">
                                <span class="fa fa-user" aria-hidden="true"></span>
                                <input name="usuario" type="text" class="form-control" id="inputSuccess5" aria-describedby="inputSuccess5Status" placeholder="Nome de Utilizador">
                            </div>
                            <div class="form-group">
                                <span class="fa fa-lock" aria-hidden="true"></span>
                                <input name="senha" type="text" class="form-control" id="inputSuccess5" aria-describedby="inputSuccess5Status" placeholder="Senha de Utilizador">
                            </div>
                            <div class="form-group">
                                <div class="">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name=""> Lembre de mim
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <button type="submit" class="btn">Entrar</button>
                            </div>
                            <div class="form-group text-center">
                                <a href="#">Esqueceu a senha?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
