<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- start page content -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <div class="page-bar">
                        <div class="page-title-breadcrumb">
                            <div class=" pull-left">
                                <div class="page-title">Lista de Pacientes</div>
                            </div>
                            <ol class="breadcrumb page-breadcrumb pull-right">
                                <li><i class="fa fa-home"></i>&nbsp;<a class="parent-item" href="">Home</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li><a class="parent-item" href="">Pacientes</a>&nbsp;<i class="fa fa-angle-right"></i>
                                </li>
                                <li class="active">Pacientes</li>
                            </ol>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="tabbable-line">
                                <ul class="nav customtab nav-tabs" role="tablist">
	                                <li class="nav-item"><a href="#tab1" class="nav-link active"  data-toggle="tab" >List View</a></li>
	                                <li class="nav-item"><a href="#tab2" class="nav-link" data-toggle="tab">Grid View</a></li>
	                            </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active fontawesome-demo" id="tab1">
                                        <div class="row">
					                        <div class="col-md-12">
					                            <div class="card card-topline-red">
					                                <div class="card-head">
					                                    <header></header>
					                                    <div class="tools">
					                                        <a class="fa fa-repeat btn-color box-refresh" href="javascript:;"></a>
						                                    <a class="t-collapse btn-color fa fa-chevron-down" href="javascript:;"></a>
					                                    </div>
					                                </div>
					                                <div class="card-body ">
					                                    <div class="row">
					                                        <div class="col-md-6 col-sm-6 col-xs-6">
					                                            <div class="btn-group">
					                                                <a href="navegacaoag?mods=ag&pag=novopac" id="addRow" class="btn btn-info">
					                                                      <i class="fa fa-plus"></i> Novo
					                                                </a>
					                                            </div>
					                                        </div>
					                                         
					                                    </div>
					                                    <table class="table table-striped table-bordered table-hover table-checkable order-column valign-middle" id="example4">
					                                        <thead>
					                                            <tr>
					                                            	<th class="center"> <i class="fa fa-calendar"></i> </th>
					                                                <th class="center"> Nome </th>
					                                                <th class="center"> Genero </th>
					                                                <th class="center"> Endereço </th>
					                                                <th class="center"> Telefone </th>
					                                                <th class="center"> Nascido </th>
					                                                <th class="center"> Idade</th>
					                                                <th class="center"> Sangue</th>
					                                                <th class="center"> Opções </th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
																<tr class="odd gradeX">
																	<td class="center">
																		<a href="navegacaoag?mods=ag&pag=novoagen" class="btn btn-success btn-xs">
																			<i class="fa fa-book"></i>
																		</a>
																	</td>
																	<td>Alexandre Alberto Joao</td>
																	<td class="center">Masculino</td>
																	<td class="center">18,Ajay flats, satadhar, ahmedabad</td>
																	<td><a href="tel:4444565756">
																			924 044 145 </a></td>
																	<td class="center">18/12/2017</td>
																	<td class="center">1</td>
																	<td class="center">0+</td>
																	<td>
																		<a href="navegacaoag?mods=ag&pag=editpac" class="btn btn-primary btn-xs">
																			<i class="fa fa-pencil"></i>
																		</a>
																		<a href="javascript()" class="btn btn-danger btn-xs">
																			<i class="fa fa-trash-o "></i>
																		</a>
																	</td>
																</tr>
																 
															</tbody>
					                                    </table>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
                                    </div>
                                    <div class="tab-pane" id="tab2">
                                        <div class="row">
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user10.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Pooja Patel </div>
					                                        </div>
				                                                <p>A-103, shyam gokul flats, Mahatma Road <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                            <a href="navegacaoag?mods=ag&pag=profpac" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user1.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Rajesh </div>
					                                        </div>
				                                                <p>45, Krishna Tower, Near Bus Stop, Satellite, <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user2.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Sarah Smith </div>
					                                        </div>
				                                                <p>456, Estern evenue, Courtage area, <br />New York</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
                    					</div>
                    					<div class="row">
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user3.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">John Deo </div>
					                                        </div>
				                                                <p>A-103, shyam gokul flats, Mahatma Road <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user4.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Jay Soni </div>
					                                        </div>
				                                                <p>45, Krishna Tower, Near Bus Stop, Satellite, <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user5.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Jacob Ryan </div>
					                                        </div>
				                                                <p>456, Estern evenue, Courtage area, <br />New York</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
                    					</div>
                    					<div class="row">
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user6.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Megha Trivedi </div>
					                                        </div>
				                                                <p>A-103, shyam gokul flats, Mahatma Road <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user1.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Rajesh </div>
					                                        </div>
				                                                <p>45, Krishna Tower, Near Bus Stop, Satellite, <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user2.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Sarah Smith </div>
					                                            <div class="name-center"> Anaesthetics </div>
					                                        </div>
				                                                <p>456, Estern evenue, Courtage area, <br />New York</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
                    					</div>
                    					<div class="row">
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user10.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Pooja Patel </div>
					                                            <div class="name-center"> Cardiology </div>
					                                        </div>
				                                                <p>A-103, shyam gokul flats, Mahatma Road <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user1.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">Rajesh </div>
					                                        </div>
				                                                <p>45, Krishna Tower, Near Bus Stop, Satellite, <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
					                        <div class="col-md-4">
				                                <div class="card card-topline-aqua">
				                                    <div class="card-body no-padding ">
				                                    	<div class="doctor-profile">
				                                                <img src="img/user/user3.jpg" class="doctor-pic" alt=""> 
					                                        <div class="profile-usertitle">
					                                            <div class="doctor-name">John Deo </div>
					                                        </div>
				                                                <p>A-103, shyam gokul flats, Mahatma Road <br />Mumbai</p> 
				                                                <div><p><i class="fa fa-phone"></i><a href="tel:(123)456-7890">  (123)456-7890</a></p> </div>
					                                        <div class="profile-userbuttons">
					                                             <a href="patient_profile.html" class="btn btn-circle deepPink-bgcolor btn-sm">Read More</a>
					                                        </div>
				                                        </div>
				                                    </div>
				                                </div>
					                        </div>
                    					</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end page content -->