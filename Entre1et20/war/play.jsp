<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		 <meta charset="utf-8">
		<title>Le juste Chiffre</title>
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>
		<nav>
       		<ul>
	            <li><a href="/">Accueil</a></li>
				<li><a href="/winner">Gagnant</a></li>
				<li><a href="about.html">Règles</a></li>
				<li><a href="about.html">A Propos</a></li>
        	</ul>
		</nav>

		<div id="content">
			<section>
					<h1>Entre 1 et 20</h1>
					<form action="/post" method="post">
						<input type="number" name="newNumber" placeholder="Propose un nombre"><button class="play"><img src="IMG/arrow.png" alt="arrow"></button>
						<input type="hidden" name="pseudo" value="<%=request.getAttribute("pseudo")%>" />
					</form>
					<p><%=request.getAttribute("propositions")%></p>
			</section>
		</div>
	</body>
</html>
