<!DOCTYPE html>
<html>
	<head>
		 <meta charset="utf-8">
		<title>Le juste Chiffre</title>
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="styleWin.css" />
	</head>
	<body>
		<header>
			<a href="/">Accueil</a>
			<a href="/winner">Gagnant</a>
			<a href="">Règles</a>
			<a href="">A Propos</a>
			<a href="">Github</a>
		</header>
		<section>
			<div id="content">
				<h1>Gagnant :</h1>
				<ul><%=request.getAttribute("winners")%></ul>
			</div>
		</section>
	</body>
</html>
