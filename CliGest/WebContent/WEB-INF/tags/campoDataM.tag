<%@ attribute name="id" required="true" %>
<%@ attribute name="value" %>
<input id="${id}" name="${id}" type="text" value="${value}" class="txtData">
<script>
$("#${id}").datepicker({
	changeMonth: true,
    changeYear: true,
	dateFormat: 'dd/mm/yy',
    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
});

</script>