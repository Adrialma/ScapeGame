<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Design/css/EnigmeSuivant.css">
<title>Passer a la chambre suivante</title>
</head>
<body>
	 <div id="banner">
        <div class="doorHoverLeft"><span class="doorText">Vous avez reussi </span></div>
        <div class="doorHoverRight"><span class="doorText">Passer a la chambre suivante</span></div>
    </div>



<script>
    const mainDiv = document.getElementById("banner");
    mainDiv.addEventListener("click",()=>{

        document.location.href="Enigme";
        
    });
</script>

</body>
</html>