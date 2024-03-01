<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Design/css/enigme2.css">
<title>Scape Game</title>
</head>
<body>

	<div class="erreur">
		<h3>${messageErreur }</h3>
	</div>

	<div class="container">
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/1.png" alt=""
						onclick="sendAnswer('1')" />
				</p>
			</div>
		</div>
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/2.png" alt=""
						onclick="sendAnswer('2')" />
				</p>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/3.png" alt=""
						onclick="sendAnswer('3')" />
				</p>
			</div>
		</div>
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/4.png" alt=""
						onclick="sendAnswer('4')" />
				</p>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/5.png" alt=""
						onclick="sendAnswer('5')" />
				</p>
			</div>
		</div>
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/6.png" alt=""
						onclick="sendAnswer('6')" />
				</p>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/7.png" alt=""
						onclick="sendAnswer('7')" />
				</p>
			</div>
		</div>
		<div class="box">
			<span></span>
			<div class="content">
				<p>
					<img src="Design/img/tableaux/8.png" alt=""
						onclick="sendAnswer('8')" />
				</p>
			</div>
		</div>
	</div>

	<script src="Design/js/enigmes/enigme2.js"></script>
</body>
</html>