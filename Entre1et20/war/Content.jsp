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
		<div id="content">
			<section>
				<h1><%=request.getAttribute("titre")%></h1>
				<form action="/play" method="post">
					<input type="hidden" name="pseudo" value="<%=request.getAttribute("pseudo")%>" />
					<input type="hidden" name="value" value="<%=request.getAttribute("value")%>" />
					<button class="try">Réessayer</button>
				</form>
			</section>
		</div>
	</body>
</html>
