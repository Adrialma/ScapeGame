<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Design/css/enigme1.css"> 
<title>Scape Game</title>
</head>
<body>

	<div class="mainContainer">
		<div class="images">
			<div class="int"> <img src="Design/img/chiffres/3.png" alt=""> 	</div>
			<div class="int"><img src="Design/img/chiffres/separateur2.png" alt=""></div>
			<div class="int"><img src="Design/img/chiffres/5.png" alt=""></div>
			<div class="int"><img src="Design/img/chiffres/separateur2.png" alt=""></div>
			<div class="int"><img src="Design/img/chiffres/8.png" alt=""></div>
			<div class="int"><img src="Design/img/chiffres/separateur2.png" alt=""></div>
			<div class="int"><img src="Design/img/chiffres/13.png" alt=""></div>
			<div class="int"><img src="Design/img/chiffres/separateur2.png" alt=""></div>
			
				<div class="flotante" id="responseTry">
					
						<img src="Design/img/chiffres/interrogation.png" alt="" >
					
				</div>
			
			
			<div class="int"><img src="Design/img/chiffres/separateur2.png" alt=""></div>
			<div class="int"><img src="Design/img/chiffres/43.png" alt=""></div>
			
		</div>
	</div>


	<div class="hidden" id="hidden">
		<form action="Enigme" method="post">
			<img src="Design/img/chiffres/interrogation.png" alt="" ><br>
			<input name="answer" type="number" id="answer" placeholder="Response?" required alt="Only numbers accepted"><br><br>
			<div id="containerBoton">
				<button class="botonAzul" type="submit" >Sortir de cette chambre!</button>
			</div>	
		</form>

	</div>


<script src="Design/js/enigmes/enigme1.js"></script>
</body>
</html>