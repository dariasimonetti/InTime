<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="model.ProductBean" %>

    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Style/ProductPage.css">
<title>Prodotto</title>
</head>
<%@ include file="header.jsp" %>
<body class="body-background">


<% ProductBean p = (ProductBean) request.getAttribute("product");
%>
	
    <!-- Showcase -->
    <section class="section-a">
      <div class="container">
        <div>
          <h1><%= p.getNome()%></h1>
          
          <%if (p.getSconto()!=0.0){ %>
          <h2><del style="color: grey"><%= p.getPrezzo() %> &euro;</del> &ensp;<%= (p.getPrezzo()*p.getSconto())/100 %> &euro;</h2>
  
          <h5 style="color :#90ee90"><%= p.getSconto() %>% di sconto</h5>
          <% } else{ %>
          <h2><%=p.getPrezzo() %>&euro;</h2>
          <%} %>
          <p>
            <%= p.getDescrizione() %>
          </p>
           <form action="AddCart" method="post">
           <div data-tooltip="Compra" class="button">
<button class="button-wrapper" value="<%= p.getId()%>" name="Id">
  <div class="text">Compra</div>
    <span class="icon" style="color: black; border-radius: 5px;"><ion-icon name="cart"></ion-icon>
    </span>
  </button>
</div>

           
			
           </form>
        </div>
        <img src="Sfondo 3.png" style="border-radius:50px" alt="" />
      </div>
    </section>

    <!-- Large Text -->
    <section id="about" class="section-b">
      <div class="overlay">
        <div class="section-b-inner py-5">
          <h3 class="text-2">Loud & Clear</h3>
          <h2 class="text-5 mt-1">People Aren't Hearing All the Music</h2>
          <p class="mt-1">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Dicta
            repudiandae laboriosam quia, error tempore porro ducimus voluptate
            laborum nostrum iure.
          </p>
        </div>
      </div>
    </section>

    <!-- Gallery -->
    <section class="section-c">
      <div class="gallery">
        <a href="https://i.ibb.co/CHLBZnp/gal2323.jpg" class="big"
          ><img src="https://i.ibb.co/CHLBZnp/gal2323.jpg" alt=""
        /></a>
        <a href="https://i.ibb.co/4pBbhfY/gal39834.jpg" class="big"
          ><img src="https://i.ibb.co/4pBbhfY/gal39834.jpg" alt=""
        /></a>
        <a href="https://i.ibb.co/xSnHP7g/gal43884.jpg" class="big"
          ><img src="https://i.ibb.co/xSnHP7g/gal43884.jpg" alt=""
        /></a>
        <a href="" class="big"
          ><img src="Sfondo 2.png" alt=""
        /></a>
        <a href="https://i.ibb.co/dGZvj75/gal4545.jpg" class="big">
          <img src="https://i.ibb.co/dGZvj75/gal4545.jpg" alt=""
        /></a>
        <a href="https://i.ibb.co/S6FVFNt/gal74744.jpg" class="big"
          ><img src="https://i.ibb.co/S6FVFNt/gal74744.jpg" alt=""
        /></a>
      </div>
    </section>


<script src="JS/productpage.js"></script>


</body>
</html>