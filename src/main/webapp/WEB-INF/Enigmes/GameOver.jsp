<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Design/css/gameOver.css">
<title>Game Over</title>
</head>
<body>
<div class="mainContainer">
	<h1>TIME OVER</h1>
	<p>Vous avez depasé le temps</p>
	<button class="botonAzul" id="rejouer">Jouer a nouveau</button><br>
	<button class="botonAzul" id="deconexion">Deconexion</button>
</div>

<script>
	const boton = document.getElementById("rejouer");
	boton.addEventListener("click",()=>{
		document.location.href="HomePage";
	});
	
	const boton2 = document.getElementById("deconexion");
	boton2.addEventListener("click",()=>{
		document.location.href="LogOut";
	});
</script>
</body>
</html>