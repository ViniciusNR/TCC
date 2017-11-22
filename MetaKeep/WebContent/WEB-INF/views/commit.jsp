<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SaR</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var sldiv = $(".slidingDiv");
		var sh = $(".show_hide");
		$(sldiv).hide();
		$(sh).show();

		$(sh).click(function() {
			$(sldiv).slideToggle();
		});
	});
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var max_fields = 100; //maximum input boxes allowed
						var wrapper = $(".input_fields_wrap"); //Fields wrapper
						var add_button = $(".add_field_button"); //Add button ID
						var salvar = $(".salvar");
						var stringList = [];
						var x = 1; //initlal text box count

						//var text = document.getElementById("json").value;
						var text = $("#json").html().trim();
						console.log("CONSOLE: " + text);

						if (text != null && text != "") {
							var jsOb = JSON.parse(text);
							console.log(jsOb);

							for (var i = 0; i < jsOb.metadados.length; i++) {
								$(wrapper)
										.append(
												$('<div class="rmv"><div class="col-md-6"><input type="text" class="form-control" name="nome" value="'
												+jsOb.metadados[i].nome+
												'"></div><div class="col-md-6 input-group"><input type="text" class="form-control" name="valor" value="'
												+jsOb.metadados[i].valor+
												'"> <span class="input-group-btn"><button class="btn btn-default remove_field btn-danger" type="button"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button></span></div></div>'));
							}
							$("#nomeSoftware").val(
									jsOb.infoBasicas.nomeSoftware);
							$("#descricaoSoftware").val(
									jsOb.infoBasicas.descricao);
							$("#versaoSoftware").val(jsOb.infoBasicas.versao);
							$("#autoresSoftware").val(jsOb.infoBasicas.autores);
							$("#categoriaSoftware").val(jsOb.infoBasicas.categoria);
							$("#ddButton").text(jsOb.infoBasicas.categoria);
						}

						$(add_button)
								.click(
										function(e) { //on add input button click
											e.preventDefault();
											if (x < max_fields) { //max input box allowed
												x++; //text box increment
												$(wrapper)
														.append(
																'<div class="rmv"><div class="col-md-6"><input type="text" class="form-control" name="nome"></div><div class="col-md-6 input-group"><input type="text" class="form-control" name="valor"> <span class="input-group-btn"><button class="btn btn-default remove_field btn-danger" type="button"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></button></span></div></div>'); //add input box
											}
										});

						$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
							e.preventDefault();
							$(this).closest('div.rmv').remove();
							x--;
						});

						$("#ddMenu a").click(function(e) {
							e.preventDefault(); // cancel the link behaviour
							var selText = $(this).text();
							$("#ddButton").text(selText);
							$("#categoriaSoftware").val(selText);
						});

					});
</script>
</head>
<body>
	<div class="container-fluid" style="margin-top: 1%; height: 90%;">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<a class="navbar-brand" href="/ArtifactRepository/index">MetaKeep</a>
					</div>
				</nav>

				<!-- 				<div class="jumbotron well"> -->
				<!-- 					<form action="/ArtifactRepository/commit"> -->
				<!-- 						<button type="button" class="btn btn-default show_hide">Adicionar -->
				<!-- 							Metadados</button> -->
				<!-- 					</form> -->
				<!-- 				</div> -->

				<!-- 				<form action="/ArtifactRepository/salvarArquivo"> -->
				<form>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Cabeçalho Arquivo de Metadados</h3>
							</div>
							<div class="panel-body">
								<div class="input-group">
									<span class="input-group-addon">Nome:</span> <input
										id="nomeSoftware" name="nomeSoftware" type="text"
										class="form-control" placeholder="Nome do Software"
										aria-describedby="basic-addon1">
								</div>
								<div class="input-group">
									<span class="input-group-addon">Descrição:</span> <input
										id="descricaoSoftware" name="descricao" type="text"
										class="form-control" placeholder="Breve Descrição"
										aria-describedby="basic-addon1">
								</div>
								<div class="input-group">
									<span class="input-group-addon">Versão:</span> <input
										id="versaoSoftware" name="versao" type="text"
										class="form-control" placeholder="Versão do Software"
										aria-describedby="basic-addon1">
								</div>
								<div class="input-group">
									<span class="input-group-addon">Autores:</span> <input
										id="autoresSoftware" name="autores" type="text"
										class="form-control" placeholder="Autor 1, Autor 2"
										aria-describedby="basic-addon1">
								</div>
								<div class="btn-group" role="group">
									<button id="ddButton" type="button"
										class="btn btn-default dropdown-toggle" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">
										Categoria <span class="caret"></span>
									</button>
									<ul id="ddMenu" class="dropdown-menu">
										<li><a href="#">Educacional</a></li>
										<li><a href="#">Categoria 1</a></li>
										<li><a href="#">Categoria 2</a></li>
									</ul>
								</div>
								<input id="categoriaSoftware" name="categoria" type="hidden"
									class="form-control" aria-describedby="basic-addon1">
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Metadados [Nome : Valor]</h3>
							</div>
							<div class="panel-body">
								<div id="metaPanel" class="col-md-12 input_fields_wrap"></div>
								<div id="json" style="display: none;">${json}</div>

								<div class="col-md-12" style="text-align: right;">

									<button class="btn btn-default add_field_button btn-primary"
										type="button" style="width: 20%">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
									</button>
									<button class="btn btn-primary"
										formaction="/ArtifactRepository/salvarArquivo"
										style="width: 20%;" type="submit">
										<span class="glyphicon glyphicon-floppy-disk"
											aria-hidden="true"></span> Salvar
									</button>
									<button class="btn btn-primary"
										formaction="/ArtifactRepository/downloadArquivo"
										style="width: 20%;" type="submit">
										<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
										Download
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
