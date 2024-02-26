<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Adrialma Game</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">  <!-- Google web font "Open Sans" -->
    <link rel="stylesheet" href="Design/css/font-awesome-4.5.0/css/font-awesome.min.css">                <!-- Font Awesome -->
    <link rel="stylesheet" href="Design/css/bootstrap.min.css">                                      <!-- Bootstrap style -->
    <link rel="stylesheet" href="Design/css/hero-slider-style.css">                                  <!-- Hero slider style -->
    <link rel="stylesheet" href="Design/css/templatemo-style.css">                                   <!-- Templatemo style -->



</head>
 <body>
        <section class="cd-hero">
            <ul class="cd-hero-slider">  
            
            
             <!-- 
                //////////////////////////////////
            	Slider pour l'Enregistrement
                /////////////////////////////////////

                     TODO -- Changer l'atribut "action" dans la balise form
                             add method POST dans le form
                             
                -->

                <li class="selected">
                    <div class="cd-full-width">
                        <div class="tm-slide-content-div">
                            <form action="" method="POST">
                                
                                <h2 class="text-uppercase">S'enregistrer</h2>
                                <p>L'enregistrement a etait effectué</p>
                                <p class="m-b-mid" ><a href="#0" id="login">Log In</a> </p>

                                
                            </form>                           
                        </div>                        
                    </div> <!-- .cd-full-width -->
                </li>




             <!-- 
                /////////////////////////////////////////
            	    Slider pour le LOG IN
                ////////////////////////////////////////

                    TODO -- Changer l'atribut "action" dans la balise form
                            add method POST dans le form
            -->

                <li >
                    <div class="cd-full-width">
                        <div class="tm-slide-content-div">
                        
                        
                            <form action="Login" id="search-form">
                               
                                <h2 class="text-uppercase">Log In</h2>
                                <p >Entrez dans le fantastique jeu d'evasion </p>
                                <div class="form-group">
                                    
                                    <input name="userName" type="text" class="form-control center-block tm-max-w-400" id="User" placeholder="Nom d'utilisateur">
                                    <input name="pass" type="password" class="form-control center-block tm-max-w-400" id="password" placeholder="Mot de pass">
                                </div>                                
                                <button type="submit" class="cd-btn">Commencer a Jouer</button>
                                <p></p>
                                <p class="m-b-mid" ><a href="#0" id="register">S'enregistrer</a> </p>
                            </form>  



                        </div>                        
                    </div> <!-- .cd-full-width -->
                </li>

               
                
                
                <!-- 
                //////////////////////////////////
            	FIN SLIDERS
                /////////////////////////////////////

                -->
                
                
                
            </ul> <!-- .cd-hero-slider -->

            <div class="cd-slider-nav">
                <nav>
                    <span class="cd-marker item-1"></span>
                    
                    <ul>
                        <li class="selected" id="registerMe"><a href="#0"></a></li>
                        <li id="logMe"><a href="#0"></a></li>                     
                    </ul>
                </nav>  
            </div> <!-- .cd-slider-nav -->
        </section> <!-- .cd-hero -->

        




        <!-- load JS files -->
        <script src="Design/js/jquery-1.11.3.min.js"></script>             <!-- jQuery (https://jquery.com/download/) -->
        <script src="https://www.atlasestateagents.co.uk/javascript/tether.min.js"></script> <!-- Tether for Bootstrap (http://stackoverflow.com/questions/34567939/how-to-fix-the-error-error-bootstrap-tooltips-require-tether-http-github-h) --> 
        <script src="Design/js/bootstrap.min.js"></script>                 <!-- Bootstrap js (v4-alpha.getbootstrap.com/) -->
        <script src="Design/js/hero-slider-script.js"></script>            <!-- Hero slider (https://codyhouse.co/gem/hero-slider/) -->
        <script src="Design/js/jquery.touchSwipe.min.js"></script>         <!-- http://labs.rampinteractive.co.uk/touchSwipe/demos/ -->
        <script>     
       
            $(document).ready(function(){

                /* Auto play bootstrap carousel 
                * http://stackoverflow.com/questions/13525258/twitter-bootstrap-carousel-autoplay-on-load
                -----------------------------------------------------------------------------------------*/                
                $('.carousel').carousel({
                  interval: 3000
                })

                /* Enable swiping carousel for tablets and mobile
                 * http://lazcreative.com/blog/adding-swipe-support-to-bootstrap-carousel-3-0/
                 ---------------------------------------------------------------------------------*/
                if($(window).width() <= 991) {
                    $(".carousel-inner").swipe( {
                        //Generic swipe handler for all directions
                        swipeLeft:function(event, direction, distance, duration, fingerCount) {
                            $(this).parent().carousel('next'); 
                        },
                        swipeRight: function() {
                            $(this).parent().carousel('prev'); 
                        },
                        //Default is 75px, set to 0 for demo so any distance triggers swipe
                        threshold:0
                    });
                }  

                /* Handle window resize */
                $(window).resize(function(){
                    if($(window).width() <= 991) {
                        $(".carousel-inner").swipe( {
                            //Generic swipe handler for all directions
                            swipeLeft:function(event, direction, distance, duration, fingerCount) {
                                $(this).parent().carousel('next'); 
                            },
                            swipeRight: function() {
                                $(this).parent().carousel('prev'); 
                            },
                            //Default is 75px, set to 0 for demo so any distance triggers swipe
                            threshold:0
                        });
                     }
                });                             
            });
            
            
            
            
            

        </script>             

</body>
</html>