<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.CatalogoBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
	<link rel="stylesheet" href="Style/Catalogo.css">
	<meta charset="ISO-8859-1">
	
	<script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
	<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

<title>Catalogo</title>
	
</head>
<%@ include file="header.jsp" %>

<body class="bg-body">




<p class="projTitle textGradient">Catalogo Per i Tuoi Acquisti!</p>
<div class="search-container watch fade-in">
	<form action="Search" class="search-bar">
		<input name="cerca" type="text" placeholder="cerca..." name="search">
	<lord-icon
    src="https://cdn.lordicon.com/msoeawqm.json"
    trigger="hover"
    colors="primary:#eee,secondary:#ffd700"
    style="width:45px;height:45px">
</lord-icon>
 
	</form>
	
	<button id="modal-button2">
	<ion-icon name="filter-circle-outline" style="width:45px;height:45px; color:#eee;"></ion-icon>
	</button>
	
</div>

 <div id="myModal2" class="modal2">
	<div class="modal-content2 slideDown">

		<div class="modal-header2">
			<span class="close" id="closeModal2">&times;</span>
			
		</div>
		<h2 class="h2">Filtri</h2>
		<div>
			<form action="Filtri" class="modal-form2" method="post">
			
				
             <div class="filtri">      
             	<div class="left-column">
             		<div class="inputbox2">
                        <label for="">A partire da:</label>                    
                        <input class="size" name="partire" required>
                        <ion-icon name="logo-euro"></ion-icon>
                    </div>   
                </div>                     
                    
                <div class="right-column">
                   	<div class="inputbox2">                        
                        <label for="">Fino a:</label>
                        <input class="size" name="fino" required>
                        <ion-icon name="logo-euro"></ion-icon>
                    </div>    
                </div>
             </div>           
                    <br> <br>
                    
     <div class="radio-buttons-container">
    <div class="radio-button">
        <input name="tipo" id="Orologio" class="radio-button__input" type="radio" value="Orologio">
        <label for="Orologio" class="radio-button__label">
            <span class="radio-button__custom"></span>
            Orologio
        </label>
    </div>
    
    <div class="radio-button">
        <input name="tipo" id="Cinturino" class="radio-button__input" type="radio" value="Cinturino">
        <label for="Cinturino" class="radio-button__label">
            <span class="radio-button__custom"></span>
            Cinturino
        </label>
    </div>               
</div>

<div class="radio-buttons-container">
    <div class="radio-button">
        <input name="genere" id="Uomo" class="radio-button__input" type="radio" value="Uomo">
        <label for="Uomo" class="radio-button__label">
            <span class="radio-button__custom"></span>
            Uomo
        </label>
    </div>
    
    <div class="radio-button">
        <input name="genere" id="Donna" class="radio-button__input" type="radio" value="Donna">
        <label for="Donna" class="radio-button__label">
            <span class="radio-button__custom"></span>
            Donna
        </label>
    </div>
    
    <div class="radio-button">
        <input name="genere" id="Unisex" class="radio-button__input" type="radio" value="Unisex">
        <label for="Unisex" class="radio-button__label">
            <span class="radio-button__custom"></span>
            Unisex
        </label>
    </div>
</div>

				<br>	

			<button class="button btn-bot"> Applica <span></span><ion-icon name="filter-circle-outline" style="width:25px;height:25px;;"></ion-icon>  </button>
		   </form>
		 </div>
		  </div>
		 </div>


<div class="grid-container">

	<div class="grid">
		
          <%
          	 int i = 0;
             ArrayList<CatalogoBean> products = new ArrayList<>();
             products = (ArrayList<CatalogoBean>)  request.getAttribute("prodotti");
             ArrayList<String> imgs = new ArrayList<>();         
             imgs = (ArrayList<String>)  request.getAttribute("firstImagePaths");
			for (CatalogoBean p : products){
				 
				String img = imgs.get(i);
				i++;
			 %>
			 <form action="Product" method="post">
   <div class="container watch fade-in">
   
    <div class="card">
      <div class="front">
      <img src="<%= img %>" alt="immagine prodotto">
      </div>
      <div class="back">
        <h3><%= p.getNome() %></h3>
        
        <h3><%= p.getPrezzo()%>&euro;</h3>
        <form action="Product" method="post">
                 <button class="box b" type= "submit" value="<%= p.getId() %>" name = "Id">Visualizza</button>
                  <input type="hidden" name="nomeSub" value="<%=p.getId()%>">
        </form>
      </div>
    </div>
    
  </div>
			  	
    <%
    } %>
    
    </div>
    </div>	
      	
    
    <script src="https://kit.fontawesome.com/b99e675b6e.js"></script>	
	<script src="JS/catalogo.js"></script>
	<script src="JS/catalogo2.js"></script>
    	
	</body>
	<%@ include file="Footer.jsp" %>
</html>