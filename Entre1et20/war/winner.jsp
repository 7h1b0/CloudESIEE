<!DOCTYPE html>
<html>
	<head>
		 <meta charset="utf-8">
		<title>Le juste Chiffre</title>
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>
		<header>
			<a href="/">Accueil</a>
			<a href="">A Propos</a>
			<a href="">Github</a>
		</header>
		<section>
			<h1>Gagnant :</h1>
			<p><%=request.getAttribute("winners")%></p>
			<a href="/">Réessayer</a>
		</section>
	</body>
</html>
