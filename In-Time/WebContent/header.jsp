<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Style/header.css" />
 <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap" rel="stylesheet">
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
 <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

	 <header class="header">

    <div class="header__content">
      <a class="header__logo" href="index.jsp">
      <img class="header__logo" src="logo.png" alt="logo In Time" width="150" height="200">
      </a>
      <ul class="header__menu">
        <li><a><form action= "Catalogo"><button class="button2">Catalogo Completo</button></form></a></li>
        <li><a><form action= "CatalogoUomo"><button class="button2">Catalogo uomo</button></form></a></li>
        <li><a><form action= "CatalogoDonna"><button class="button2">Catalogo Donna</button></form></a></li>
        <li><a><form action= "Orologio"><button class="button2">Orologi</button></form></a></li>
        <li><a><form action= "Cinturino"><button class="button2">Cinturini</button></form></a></li>
        
        <%
        	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        	response.setHeader("Pragma", "no-cache");
        	response.setHeader("Expires", "0");
        	if(session.getAttribute("name")!=null) { if(session.getAttribute("admin")!=null){ %>
        	<li><a><form action="Admin" method="post" ><button class="button"><%=session.getAttribute ("name") %><span></span> <ion-icon name="person-circle-outline"></ion-icon></ion-icon>  </button></form> </a></li><%} else{ %>
        	
        	<li><a><form action="AreaUtente" method="post" ><button class="button"><%=session.getAttribute ("name") %><span></span> <ion-icon name="person-circle-outline"></ion-icon></ion-icon>  </button></form> </a></li><%} %>
        	<li><a><form action="Cart" ><button class="button">Carrello<span></span> <ion-icon name="cart-outline"></ion-icon></ion-icon></button></form></a> </li>
        	
        	
     	<% } else {  %>
        <li><a><button class="button" id="modal-button" >Utente<span></span> <ion-icon name="finger-print-outline"></ion-icon>  </button></a> </li>
        <li><a><form action="Cart" ><button class="button">Carrello<span></span> <ion-icon name="cart-outline"></ion-icon></ion-icon></button></form></a> </li>
          <% } %>
      </ul>
      
      <div class="icon-hamburger">
            <span></span>
            <span></span>
          </div>
    </div>
    
    
    <!-- Modal content -->
    <div id="myModal" class="modal">
	<div class="modal-content slideDown">

		<div class="modal-header">
			<span class="close" id="closeModal">&times;</span>
			
		</div>
		<h2 class="h2">Accedi</h2>
		<div class="modal-body">
			<form action="Login" class="modal-form modal-form-border" method="post">
			
				<div class="inputbox">
                        <ion-icon name="mail-outline"></ion-icon>
                        <input name="email" required>
                        <label for="">Email</label>
                    </div>
                    <div class="inputbox">
                        <ion-icon name="lock-closed-outline"></ion-icon>
                        <input type="password" name="password" required>
                        <label for="">Password</label>
                    </div>
				
				<br>
			<button class="button btn-bot"> Accedi <span></span> <ion-icon name="finger-print-outline"></ion-icon>  </button>
		   </form>
		   <%if (session.getAttribute("nologin")!=null){ %>
		   <script> window.alert("Login non riuscito")</script>
		   <%} %>
		 </div>
		   <h2 class="h2 h2-top">Registrati</h2>
		   <div class="modal-body">
		   
		   <form action="UserRegister" class="modal-form " method="post">
			
				<div class="inputbox">
					<ion-icon name="person-circle-outline"></ion-icon>
					<input name="nome"required>
					<label for="">Nome*</label>            
				</div>
				
				<div class="inputbox">
					<ion-icon name="person-circle-outline"></ion-icon>
					<input name="cognome" required>
					<label for="">Cognmome*</label>
				</div>
				
				<div class="inputbox">
					<ion-icon name="mail-outline"></ion-icon>
					<input name="email" required>
					<label for="">Email*</label>  
				</div>
				
				<div class="inputbox">
					<ion-icon name="lock-closed-outline"></ion-icon>
					<input type="password" name="password" required>
					<label for="">Password*</label>                  
				</div>
				
				<!--   REINSERISCI PASSWORD
				<div class="inputbox">
					<ion-icon name="lock-closed-outline"></ion-icon>
					<input type="password" name="repassword" required>
					<label for="">Reinserisci Password</label>                  
				</div> -->
				
				<div class="inputbox">
					<ion-icon name="phone-portrait-outline"></ion-icon>
					<input name="cellulare" maxlenght="10">
					<label for="">Cellulare</label>
				</div>
				
				<br>
		
			<button class="button" > Registrati <span></span> <ion-icon name="finger-print-outline"></ion-icon>  </button> 
		</form>
		</div>
		  </div>
		 </div>
		
	 
  </header>
  
  <script src="JS/header.js"></script>
  <script src="JS/index2.js"></script>


</body>
</html>