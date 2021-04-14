package com.trabalho.tg.Helper;

public class ReportTemplate {

    public static String HTMLSTART =
            "<!DOCTYPE html>" +
                    "<html lang=\"en\">" +
                    "<head>" +
                    "<title>Table V02</title>" +
                    "<meta charset=\"UTF-8\">" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";


    public static String BODYSTART =
            "</head>" +
                    "<body>";


    public static String HEADERGERALSTART =
            "<div class=\"dados_container\">" +
                    "<h1>Relatório geral da produção</h1>" +
                    "<div class=\"cultivo\">";


    public static String HEADERGERALEND =
            "</div>" +
                    "</div>";


    public static String HEADERFISCALSTART =
            "<div class=\"dados_container\">" +
                    "		<h1>Relatório de aplicação de defensivos agrícolas</h1>" +
                    "		<div class=\"agricultor\">" +
                    "			<ul>";

    public static String HEADERFISCALEND =
            "</p>" +
                    "				</li>" +
                    "			</ul>" +
                    "		</div>" +
                    "	</div>";

    public static String HEADERNOME =
            "<li>" +
                    "<label>Nome</label>" +
                    "<p>";

    public static String HEADERNOMEFANT =
            "</p>" +
                    "				</li>" +
                    "				<li>" +
                    "					<label>Nome fantasia</label>" +
                    "					<p>";

    public static String HEADERRAZAO =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>Razão social</label>" +
                    "		<p>";

    public static String HEADERCNPJ =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>CNPJ</label>" +
                    "		<p>";

    public static String HEADEREMAIL =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>Email</label>" +
                    "		<p>";


    public static String HEADERTELEFONE =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>Telefone</label>" +
                    "		<p>";

    public static String HEADERLOGRADOURO =
            "</p>" +
                    "		</li>" +
                    "	</ul>" +
                    "</div>" +
                    "<div class=\"endereco agricultor\">" +
                    "	<ul>" +
                    "		<li>" +
                    "			<label>Logradouro</label>" +
                    "			<p>";

    public static String HEADERCIDADE =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>Cidade/UF</label>" +
                    "		<p>" ;

    public static String HEADERBAIRRO =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>Bairro</label>" +
                    "		<p>";

    public static String HEADERCEP =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>CEP</label>" +
                    "		<p>";

    public static String HEADERLATITUDE =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>Latitude</label>" +
                    "		<p>";

    public static String HEADERLONGITUDE =
            "</p>" +
                    "	</li>" +
                    "	<li>" +
                    "		<label>Longitude</label>" +
                    "		<p>";

    public static String TABLEGERALSTART =
            "<div class=\"limiter\">" +
                    "<div class=\"container-table100\">" +
                    "	<div class=\"wrap-table100\">" +
                    "			<div class=\"table\">" +
                    "				<div class=\"row header\">" +
                    "					<div class=\"cell\">" +
                    "						Data" +
                    "					</div>" +
                    "					<div class=\"cell\">" +
                    "						Descrição" +
                    "					</div>" +
                    "					<div class=\"cell\">" +
                    "						Tempo" +
                    "					</div>" +
                    "					<div class=\"cell\">" +
                    "						Quantidade" +
                    "					</div>" +
                    "					<div class=\"cell\">" +
                    "						Mudas por bandeja" +
                    "					</div>" +
                    "					<div class=\"cell\">" +
                    "						Valor" +
                    "					</div>" +
                    "</div>";


    public static String TABLEGERALEND =
            "		</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>";


    public static String BODYEND =
            "</body>";


    public static String HTMLEND =
            "</html>";

    public static String ROW_START =
            "<div class=\"row\">";

    public static String ROW_END =
            "</div>";

    public static String DATA_ROW_START =
            "<div class=\"cell\" data-title=\"Data\">";

    public static String DATA_ROW_END =
            "</div>";

    public static String DESCRICAO_ROW_START =
            "<div class=\"cell\" data-title=\"Descricao\">";

    public static String DESCRICAO_ROW_END =
            "</div>";

    public static String TEMPO_ROW_START =
            "<div class=\"cell\" data-title=\"Tempo\">";

    public static String TEMPO_ROW_END =
            "</div>";

    public static String QUANTIDADE_ROW_START =
            "<div class=\"cell\" data-title=\"Quantidade\">";

    public static String QUANTIDADE_ROW_END =
            "</div>";

    public static String MUDAS_ROW_START =
            "<div class=\"cell\" data-title=\"Mudas por bandeja\">";

    public static String MUDAS_ROW_END =
            "</div>";

    public static String VALOR_ROW_START =
            "<div class=\"cell\" data-title=\"Valor\">";

    public static String VALOR_ROW_END =
            "</div>";

    public static String TOTAL_ROW_START =
            "    <div class=\"row header bottom\">" +
                    "    <div class=\"cell\">" +
                    "        Total" +
                    "    </div>" +
                    "    <div class=\"cell\">" +
                    "        " +
                    "    </div>" +
                    "    <div class=\"cell\">" +
                    "        " +
                    "    </div>" +
                    "    <div class=\"cell\">" +
                    "        " +
                    "    </div>" +
                    "    <div class=\"cell\">" +
                    "        " +
                    "    </div>" +
                    "    <div class=\"cell\">";


    public static String TOTAL_ROW_END =
            "    </div>" +
                    "</div>";

    public static String SEGUNDOHEADERFISCALSTART =
            "<div class=\"cultivo\">";

    public static String SEGUNDOHEADERFISCALEND =
            "</div>";

    public static String TABLEFISCALSTART =
            "<div class=\"limiter\">" +
                    "		<div class=\"container-table100\">" +
                    "			<div class=\"wrap-table100\">" +
                    "					<div class=\"table\">" +
                    "" +
                    "						<div class=\"row header\">" +
                    "							<div class=\"cell\">" +
                    "								Data" +
                    "							</div>" +
                    "							<div class=\"cell\">" +
                    "								Nome comercial" +
                    "							</div>" +
                    "							<div class=\"cell\">" +
                    "								Período de carência" +
                    "							</div>" +
                    "							<div class=\"cell\">" +
                    "								Dose (a cada 100L)" +
                    "							</div>" +
                    "						</div>" ;

    public static String NOME_ROW_START =
            "<div class=\"cell\" data-title=\"Nome comercial\">";

    public static String NOME_ROW_END =
            "</div>";

    public static String CARENCIA_ROW_START =
            "<div class=\"cell\" data-title=\"Periodo de carencia\">";

    public static String CARENCIA_ROW_END =
            "</div>";

    public static String DOSE_ROW_START =
            "<div class=\"cell\" data-title=\"Dose\">";

    public static String DOSE_ROW_END =
            "</div>";

    public static String TABLEFISCALEND =
            "				</div>" +
                    "		</div>" +
                    "	</div>" +
                    "</div>";

    public static String STYLES =
            "	<style>" +
                    "		/*//////////////////////////////////////////////////////////////////[ FONT ]*/" +
                    "		@font-face {" +
                    "		  font-family: Poppins-Regular;" +
                    "		  src: url('../fonts/poppins/Poppins-Regular.ttf'); " +
                    "		}" +
                    "		@font-face {" +
                    "		  font-family: Poppins-Bold;" +
                    "		  src: url('../fonts/poppins/Poppins-Bold.ttf'); " +
                    "		}" +
                    "		/*//////////////////////////////////////////////////////////////////[ RESTYLE TAG ]*/" +
                    "		* {" +
                    "			margin: 0px; " +
                    "			padding: 0px; " +
                    "			box-sizing: border-box;" +
                    "		}" +
                    "" +
                    "		body, html {" +
                    "			height: 100%;" +
                    "			font-family: sans-serif;" +
                    "			background: #c4d3f6;" +
                    "		}" +
                    "" +
                    "		/* ------------------------------------ */" +
                    "		a {" +
                    "			margin: 0px;" +
                    "			transition: all 0.4s;" +
                    "			-webkit-transition: all 0.4s;" +
                    "		  -o-transition: all 0.4s;" +
                    "		  -moz-transition: all 0.4s;" +
                    "		}" +
                    "" +
                    "		a:focus {" +
                    "			outline: none !important;" +
                    "		}" +
                    "" +
                    "		a:hover {" +
                    "			text-decoration: none;" +
                    "		}" +
                    "" +
                    "		/* ------------------------------------ */" +
                    "		h1,h2,h3,h4,h5,h6 {margin: 0px;}" +
                    "" +
                    "		p {margin: 0px;}" +
                    "" +
                    "		ul, li {" +
                    "			margin: 0px;" +
                    "			list-style-type: none;" +
                    "		}" +
                    "" +
                    "" +
                    "		/* ------------------------------------ */" +
                    "		input {" +
                    "		  display: block;" +
                    "			outline: none;" +
                    "			border: none !important;" +
                    "		}" +
                    "" +
                    "		textarea {" +
                    "		  display: block;" +
                    "		  outline: none;" +
                    "		}" +
                    "" +
                    "		textarea:focus, input:focus {" +
                    "		  border-color: transparent !important;" +
                    "		}" +
                    "" +
                    "		/* ------------------------------------ */" +
                    "		button {" +
                    "			outline: none !important;" +
                    "			border: none;" +
                    "			background: transparent;" +
                    "		}" +
                    "" +
                    "		button:hover {" +
                    "			cursor: pointer;" +
                    "		}" +
                    "" +
                    "		iframe {" +
                    "			border: none !important;" +
                    "		}" +
                    "" +
                    "" +
                    "		/*//////////////////////////////////////////////////////////////////" +
                    "		[ Table ]*/" +
                    "" +
                    "		.limiter {" +
                    "		  width: 100%;" +
                    "		  margin: 0 auto;" +
                    "		}" +
                    "" +
                    "		.container-table100 {" +
                    "		  width: 100%;" +
                    "		  min-height: 100vh;" +
                    "		  background: #c4d3f6;" +
                    "" +
                    "		  display: -webkit-box;" +
                    "		  display: -webkit-flex;" +
                    "		  display: -moz-box;" +
                    "		  display: -ms-flexbox;" +
                    "		  display: flex;" +
                    "		  justify-content: center;" +
                    "		  flex-wrap: wrap;" +
                    "		  padding: 33px 30px;" +
                    "		}" +
                    "" +
                    "		.wrap-table100 {" +
                    "		  width: 960px;" +
                    "		  border-radius: 10px;" +
                    "		  overflow: hidden;" +
                    "		  height: 100%;" +
                    "		}" +
                    "" +
                    "		.table {" +
                    "		  width: 100%;" +
                    "		  display: table;" +
                    "		  margin: 0;" +
                    "		}" +
                    "" +
                    "		@media screen and (max-width: 768px) {" +
                    "		  .table {" +
                    "		    display: block;" +
                    "		  }" +
                    "		}" +
                    "" +
                    "		.row {" +
                    "		  display: table-row;" +
                    "		  background: #fff;" +
                    "		}" +
                    "" +
                    "		.row.header {" +
                    "		  color: #ffffff;" +
                    "		  background: #6c7ae0;" +
                    "		}" +
                    "" +
                    "		@media screen and (max-width: 768px) {" +
                    "		  .row {" +
                    "		    display: block;" +
                    "		  }" +
                    "" +
                    "		  .row.header {" +
                    "		    padding: 0;" +
                    "		    height: 0px;" +
                    "		  }" +
                    "" +
                    "		  .row.header .cell {" +
                    "		    display: none;" +
                    "		  }" +
                    "" +
                    "		  .row .cell:before {" +
                    "		    font-family: Arial-Bold;" +
                    "		    font-size: 12px;" +
                    "		    color: #808080;" +
                    "		    line-height: 1.2;" +
                    "		    text-transform: uppercase;" +
                    "		    font-weight: unset !important;" +
                    "" +
                    "		    margin-bottom: 13px;" +
                    "		    content: attr(data-title);" +
                    "		    min-width: 98px;" +
                    "		    display: block;" +
                    "		  }" +
                    "		}" +
                    "" +
                    "		.cell {" +
                    "		  display: table-cell;" +
                    "		}" +
                    "" +
                    "		@media screen and (max-width: 768px) {" +
                    "		  .cell {" +
                    "		    display: block;" +
                    "		  }" +
                    "		}" +
                    "" +
                    "		.row .cell {" +
                    "		  font-family: Arial;" +
                    "		  font-size: 15px;" +
                    "		  color: #666666;" +
                    "		  line-height: 1.2;" +
                    "		  font-weight: unset !important;" +
                    "" +
                    "		  padding-top: 20px;" +
                    "		  padding-bottom: 20px;" +
                    "		  border-bottom: 1px solid #f2f2f2;" +
                    "		}" +
                    "" +
                    "		.row.header .cell {" +
                    "		  font-family: Arial;" +
                    "		  font-size: 18px;" +
                    "		  color: #fff;" +
                    "		  line-height: 1.2;" +
                    "		  font-weight: unset !important;" +
                    "" +
                    "		  padding-top: 19px;" +
                    "		  padding-bottom: 19px;" +
                    "		}" +
                    "" +
                    "		.row .cell:nth-child(1) {" +
                    "		  padding-left: 40px;" +
                    "		}" +
                    "" +
                    "		.row .cell:nth-child(6) {" +
                    "		  padding-right: 40px;" +
                    "		}" +
                    "" +
                    "" +
                    "" +
                    "		.table, .row {" +
                    "		  width: 100% !important;" +
                    "		}" +
                    "" +
                    "		.row:hover {" +
                    "		  background-color: #ececff;" +
                    "		  cursor: pointer;" +
                    "		}" +
                    "" +
                    "		@media (max-width: 768px) {" +
                    "		  .row {" +
                    "		    border-bottom: 1px solid #f2f2f2;" +
                    "		    padding-bottom: 18px;" +
                    "		    padding-top: 30px;" +
                    "		    padding-right: 15px;" +
                    "		    margin: 0;" +
                    "		  }" +
                    "		  " +
                    "		  .row .cell {" +
                    "		    border: none;" +
                    "		    padding-left: 30px;" +
                    "		    padding-top: 16px;" +
                    "		    padding-bottom: 16px;" +
                    "		  }" +
                    "		  .row .cell:nth-child(1) {" +
                    "		    padding-left: 30px;" +
                    "		  }" +
                    "		  " +
                    "		  .row .cell {" +
                    "		    font-family: Arial;" +
                    "		    font-size: 18px;" +
                    "		    color: #555555;" +
                    "		    line-height: 1.2;" +
                    "		    font-weight: unset !important;" +
                    "		  }" +
                    "" +
                    "		  .table, .row, .cell {" +
                    "		    width: 100% !important;" +
                    "		  }" +
                    "		}" +
                    "" +
                    "" +
                    "		/* dados usuário */" +
                    "" +
                    "		.dados_container{" +
                    "			display: flex;" +
                    "			align-content: space-around;" +
                    "			flex-wrap: wrap;" +
                    "			width: 960px;" +
                    "			background-color: white;" +
                    "			margin: 20px auto auto auto;" +
                    "			border-radius: 10px;" +
                    "			text-align: center;" +
                    "		}" +
                    "		.agricultor{" +
                    "			width: 50%;" +
                    "		}" +
                    "" +
                    "		.agricultor ul{" +
                    "			display: flex;" +
                    "			flex-direction: column;" +
                    "			padding: 20px;" +
                    "		}" +
                    "		.agricultor li{" +
                    "			display: flex;" +
                    "			padding: 10px;" +
                    "		}" +
                    "		.agricultor label{" +
                    "			font-weight: bold;" +
                    "			padding-right: 10px;" +
                    "		}" +
                    "		.dados_container h1{" +
                    "			font-size: 1.5em;" +
                    "			color: black;" +
                    "			width: 100%;" +
                    "			padding: 20px 0 5px 0;" +
                    "		}" +
                    "		.cultivo h2{" +
                    "			text-align: center;" +
                    "			padding: 10px 0;" +
                    "			font-size: 1.5em;" +
                    "		}" +
                    "" +
                    "		.cultivo{" +
                    "			width: 960px;" +
                    "			background-color: white;" +
                    "			margin: 10px auto 0 auto;" +
                    "			border-radius: 10px;" +
                    "			display: flex;" +
                    "			flex-direction: row;" +
                    "			align-content: center;" +
                    "			justify-content: space-around;" +
                    "		}" +
                    "" +
                    "		.bottom{" +
                    "			border-radius: 10px;" +
                    "		}" +
                    "" +
                    "" +
                    "	</style>" +
                    "</head>";




}