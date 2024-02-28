<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Design/css/homepage.css">
<title>Scape Game</title>
</head>
<body>

	<div class="mainContainer">
		<h1>Selectioner le level a jouer</h1>
		<h2>Bonjour ${user.userName}</h2>
		<form action="HomePage" method="POST">
			<fieldset>

				<label class="container">Facile <input type="radio"
					checked="checked" id="facile" name="level" value="3"> <span
					class="checkmark"></span>
				</label> <label class="container">Moyen <input type="radio"
					id="moyen" name="level" value="2"> <span class="checkmark"></span>
				</label> <label class="container">Difficile <input type="radio"
					id="difficile" name="level" value="1"> <span
					class="checkmark"></span>
				</label>
				<div id="containerBoton">
					<button class="botonAzul" type="submit">Commencer a Jouer</button>
				</div>



			</fieldset>
		</form>
	</div>

</body>
</html>
