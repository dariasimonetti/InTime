<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.CatalogoBean" %>
<!DOCTYPE html>
<html>
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

<div class="scroll">
<p class="projTitle textGradient">Catalogo Per i Tuoi Acquisti!</p>
<div class="search-container watch fade-in">
	<form action="" class="search-bar">
		<input id="ricerca" type="text" placeholder="cerca..." name="search">
		<div id="risultati"></div>
	<lord-icon
    src="https://cdn.lordicon.com/msoeawqm.json"
    trigger="hover"
    colors="primary:#121331,secondary:#ffd700"
    style="width:45px;height:45px">
</lord-icon>
 
	</form>
	<ion-icon name="filter-circle-outline" style="width:45px;height:45px"></ion-icon>
</div>



<div class="grid-container">

	<div class="grid">
		
          <%
          	
            ArrayList<CatalogoBean> products = new ArrayList<>();
             products = (ArrayList<CatalogoBean>)  request.getAttribute("prodotti");
			for (CatalogoBean p : products){
				
				
			 %>
			 <form action="Product" method="post">
   <div class="container watch fade-in">
   
    <div class="card">
      <div class="front">
      <img src="Prova.png"/>
      </div>
      <div class="back">
        <h3><%= p.getNome() %></h3>
        
        <h3><%= p.getPrezzo()%>&euro;</h3>
        <form action="Product" method="post">
                 <button class="button" type= "submit" value="<%= p.getId() %>" name = "Id">Visualizza</button>
        </form>
      </div>
    </div>
    
  </div>
			  	
    <% } %>
    
    </div>
    </div>	
    <script src="JS/index.js"></script>
    	<script src="https://kit.fontawesome.com/b99e675b6e.js"></script>
    	</div>
	</body>
</html>