<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tableaux Enigme</title>
<link rel="stylesheet" href="Design/css/enigme2.css">
</head>
<body>
<div class="erreur"> <h3>${messageErreur }</h3> </div>

<!-- A changer -->
<div class="gallery">
    <% for(int i = 1; i <= 6; i++) { %>
    <div class="img-wrapper">
        <img src="Design/img/tableaux/<%= i %>.png" alt="Tableau <%= i %>" onclick="sendAnswer('<%= i %>')" />
    </div>
    <% if (i % 2 == 0) { %>
    <div class="clear"></div>
    <% } %>
    <% } %>
</div>

<script src="Design/js/enigmes/enigme2.js"></script>
</body>
</html>
