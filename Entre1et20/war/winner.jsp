<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		 <meta charset="utf-8">
		<title>Le juste Chiffre</title>
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="styleWin.css" />
	</head>
	<body>
		<nav>
			<a href="javascript:;" class="close"><img src="IMG/close.png" alt="close"></a>
       		<ul>
	            <li><a href="/">Accueil</a></li>
				<li><a href="/winner">Gagnant</a></li>
				<li><a href="about.html">Règles</a></li>
				<li><a href="about.html">A Propos</a></li>
        	</ul>
		</nav>

		<div id="content">
			<a href="javascript:;" class="open"><img src="IMG/menu.png" alt="menu"></a>
			<section>	
				<div id="box">
					<h1>Dernier Gagnant :</h1>
					<ul><%=request.getAttribute("winners")%></ul>
				</div>
			</section>
		</div>

		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript" src="js.js"></script>
	</body>
</html>
