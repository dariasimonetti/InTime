<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.CatalogoBean" %>
<!DOCTYPE html>
<html>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<link rel="stylesheet" href="Style/CatalogoStyle.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<head>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">

<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">

</head>
<body>
<%@ include file="header.jsp" %>
<main>
<section class ="feature-products">
<div class="container">
    <div class="row">
        <div class="col-md-12 text-center feature-title">
            <h2>CATALOGO</h2>
        </div>
          <%
            ArrayList<CatalogoBean> products = new ArrayList<>();
             products = (ArrayList<CatalogoBean>)  request.getAttribute("prodotti");
			for (CatalogoBean p : products){
			 
	       %>	
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <a href="#" class="image" style="background-color:#F3F3F3;">
                        <img class="pic-1" src="Sfondo 3.png">
                    </a>
                    <a class="add-to-cart" href=""> + </a>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#"><%= p.getNome() %></a></h3>
                     <div class="price"><%= p.getPrezzo() %></div>
                    <ul class="rating">
                   </ul>
                </div>
                 <div class="action-buttons">
                <form action="Product" method="post">
                 <button class="bt" type= "submit" value="<%= p.getId() %>" name = "Id"><ion-icon name="eye" style="font-size: 20px"></ion-icon></button>
                   </form>
                 <form action="AddCart" method="post">
                 <button class="bt" type= "submit" value="<%= p.getId() %>" name = "Id"><ion-icon name="add-circle"style="font-size: 20px"></ion-icon><ion-icon name="cart" style="font-size: 20px"></ion-icon></button>
                 </form>
     
                
                
                </div>
          
                
            </div>
        </div>
    <%}%>
 
    </div>
    
      
</section>
</main>
</html>