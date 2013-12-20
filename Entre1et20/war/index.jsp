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
		<section>
			<h1>Entre 1 et 20</h1>
			<form action="/action" method="post">
				<input type="number" name="newNumber"><button><img src="arrow.png" alt="arrow"></button>
			</form>
			<p>Estimez la valeur des nombres proposés avant vous, et tentez de deviner leur moyenne.</p>
			<p><%=request.getAttribute("propositions")%></p>
		</section>
	</body>
</html>
