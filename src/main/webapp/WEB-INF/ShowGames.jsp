<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Design/css/tableStyle.css">
<title>Liste de jeux joués</title>
</head>
<body>



	<table>
		<thead>
			<tr>
				<th>User Id</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>UserName</th>

				<th>Id Jeux</th>
				<th>Level</th>
				<th>Date</th>
				<th>Debut</th>
				<th>Fin</th>
				<th>Score</th>

			</tr>
		</thead>

		<tbody>


			<c:forEach items="${userList}" var="currentUser" varStatus="counter">
				<c:forEach items="${currentUser.games}" var="currentGame" 	varStatus="counterGame">
					<tr>

						<!--
				        		Info User
						          -->

						<c:if test="${counterGame.count==1}">
							<td rowspan="${fn:length(currentUser.games)}">${currentUser.idUser}</td>
							<td rowspan="${fn:length(currentUser.games)}">${currentUser.firstName}</td>
							<td rowspan="${fn:length(currentUser.games)}">${currentUser.lastName}</td>
							<td rowspan="${fn:length(currentUser.games)}">${currentUser.userName}</td>
						</c:if>
					
					




						<!--
						        Info Game
						          -->
						<td>${currentGame.idGame}</td>
						<td>${currentGame.levelPlayed}</td>
						<td>${currentGame.date}</td>
						<td>${currentGame.start}</td>
						<td>${currentGame.fin}</td>
						<td>${currentGame.score}</td>


					</tr>


				</c:forEach>


			</c:forEach>






		</tbody>
	</table>


<a href="/ScapeGame/">Back to index</a>





</body>
</html>