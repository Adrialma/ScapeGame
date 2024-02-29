<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Design/css/enigme3.css"> 
<title>Scape Game</title>
</head>
<body>

	<div class="erreur"> <h3>${messageErreur }</h3> </div>

	<div class="container">
        
        <div class="box">
          <span></span>
          <div class="content">
            <h2>Carte 1</h2>
            <p>1  -  4  -  6  -  9  -  12  -  14  -  17
                19  -  22  -  25  -  27  -  30  -  33  -  35
                38  -  40  -  43  -  46  -  48  -  51  -  53
                56  -  59  -  61  -  64  -  67  -  69  -  72
                74  -  77  -  80  -  82  -  85  -  88  -  90
                93  -  95  -  98</p>
            
          </div>
        </div>
        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 2</h2>
              <p>2  -  7  -  10  -  15  -  20  -  23  -  28
                31  -  36  -  41  -  44  -  49  -  54  -  57
                62  -  65  -  70  -  75  -  78  -  83  -  86
                91  -  96  -  99</p>
              
            </div>
        </div>

        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 3</h2>
              <p>3  -  4  -  11  -  12  -  16  -  17  -  24
                25  -  32  -  33  -  37  -  38  -  45  -  46
                50  -  51  -  58  -  59  -  66  -  67  -  71
                72  -  79  -  80  -  87  -  88  -  92  -  93
                100</p>
              
            </div>
        </div>




    </div>

    <div class="container">
        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 4</h2>
              <p>5  -  6  -  7  -  18  -  19  -  20  -  26
                27  -  28  -  39  -  40  -  41  -  52  -  53
                54  -  60  -  61  -  62  -  73  -  74  -  75
                81  -  82  -  83  -  94  -  95  -  96</p>
              
            </div>
        </div>

        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 5</h2>
              <p>8  -  9  -  10  -  11  -  12  -  29  -  30
                31  -  32  -  33  -  42  -  43  -  44  -  45
                46  -  63  -  64  -  65  -  66  -  67  -  84
                85  -  86  -  87  -  88  -  97  -  98  -  99
                100</p>
              
            </div>
        </div>

        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 6</h2>
              <p>13  -  14  -  15  -  16  -  17  -  18  -  19
                20  -  47  -  48  -  49  -  50  -  51  -  52
                53  -  54  -  68  -  69  -  70  -  71  -  72
                73  -  74  -  75</p>
              
            </div>
        </div>


    </div>



    <div class="container">
        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 7</h2>
              <p>21  -  22  -  23  -  24  -  25  -  26  -  27
                28  -  29  -  30  -  31  -  32  -  33  -  76 - 
                77  -  78  -  79  -  80  -  81  -  82  -  83
                84  -  85  -  86  -  87  -  88</p>
              
            </div>
        </div>

        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 8</h2>
              <p>34  -  35  -  36  -  37  -  38  -  39  -  40
                41  -  42  -  43  -  44  -  45  -  46  -  47
                48  -  49  -  50  -  51  -  52  -  53  -  54</p>
              
            </div>
        </div>

        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 9</h2>
              <p>55  -  56  -  57  -  58  -  59  -  60  -  61
                62  -  63  -  64  -  65  -  66  -  67  -  68
                69  -  70  -  71  -  72  -  73  -  74  -  75
                76  -  77  -  78  -  79  -  80  -  81  -  82
                83  -  84  -  85  -  86  -  87  -  88</p>
              
            </div>
        </div>


    </div>


    <div class="container">
        <div class="box">
            <span></span>
            <div class="content">
              <h2>Carte 10</h2>
              <p>89  -  90  -  91  -  92  -  93  -  94  -  95
                96  -  97  -  98  -  99  -  100</p>
              
            </div>
        </div>
         <div class="box">
            <span></span>
            <div class="content">
              <h2>Lis moi</h2>
              <p> Piste de l'enigme: (1) - (2) - (7), Bonne chance !!!</p>
              
            </div>
        </div>
        
        <div class="box">
            <span></span>
            <div class="content">
              <h2>Saissir reponse</h2>
              <p>
              	<div >
					<form action="Enigme" method="post">
						
						<input name="answer" type="number" id="answer" placeholder="Response?" required alt="Only numbers accepted"><br><br>
						<div id="containerBoton">
							<button class="botonAzul" type="submit" >Sortir !</button>
						</div>	
					</form>
				</div>
              
              </p>
              
            </div>
        </div>
      	
      

    </div>


	


<script src="Design/js/enigmes/enigme1.js"></script>
</body>
</html>