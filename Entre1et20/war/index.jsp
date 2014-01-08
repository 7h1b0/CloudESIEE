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
					<h1>Entre 1 et 20</h1>
					<form action="/post" method="post">
						<input type="number" name="newNumber"><button><img src="IMG/arrow.png" alt="arrow"></button>
					</form>
					<p><%=request.getAttribute("propositions")%></p>
			</section>
		</div>

		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript" src="js.js"></script>

	</body>
</html>
