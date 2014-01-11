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
				<h1>Gagné !</h1>
				<p>Bravo <%=request.getAttribute("pseudo")%>, tu as gagné en <%=request.getAttribute("value")%> coup.</p>
				<a href="/">Re-Jouer</a>
			</section>
		</div>
	</body>
</html>
