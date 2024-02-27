<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form action="">
			<fieldset>

				<label class="container">Facile <input type="radio"
					checked="checked" id="facile" name="level" value="facile">
					<span class="checkmark"></span>
				</label> <label class="container">Moyen <input type="radio"
					id="moyen" name="level" value="moyen"> <span
					class="checkmark"></span>
				</label> <label class="container">Difficile <input type="radio"
					id="difficile" name="level" value="difficile"> <span
					class="checkmark"></span>
				</label>
				<div id="containerBoton">
					<button class="botonAzul" type="submit" >Commencer a Jouer</button>
				</div>



			</fieldset>
		</form>
	</div>

</body>
</html>