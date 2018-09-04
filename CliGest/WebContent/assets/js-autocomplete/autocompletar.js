// Autocomplete dos Medicamentos Consultas
$('#produto').autocomplete({
	minLength: 2,
	source: function(request,response){
		$.ajax({
			url:'AjaxCarrinhoController',
			type: 'GET',
			datatype:'html',
			data: {
				'produto': request.term
			}
		}).done(function(data){
			if(data.length >0){
				data = data.split('|');
				response($.each(data,function(key,item){
					return({
						label: item  
					});
				}));
			}
		});
	}
});

//Autocomplete das Hipoteses Consultas
$('#hipotese').autocomplete({
	minLength: 2,
	source: function(request,response){
		$.ajax({
			url:'AjaxCarrinhoController',
			type: 'GET',
			datatype:'html',
			data: {
				'hipotese': request.term
			}
		}).done(function(data){
			if(data.length >0){
				data = data.split('|');
				response($.each(data,function(key,item){
					return({
						label: item  
					});
				}));
			}
		});
	}
});