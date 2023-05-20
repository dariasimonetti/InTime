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
          <h4><%=p.getMarca() %></h4>
          <%if (p.getSconto()!=0.0){ %>
          <h2><del style="color: grey"><%= p.getPrezzo() %> &euro;</del> &ensp;<%= p.getPrezzo()-((p.getPrezzo()*p.getSconto())/100) %> &euro;</h2>
          <h5 style="color :#90ee90"><%= p.getSconto() %>% di sconto</h5>
          <% } else{ %>
          <h2><%=p.getPrezzo() %>&euro;</h2>
          <%} %>
          
           <form action="AddCart" method="post">
           <div data-tooltip="Carrello" class="button">
<button class="button-wrapper" value="<%= p.getId()%>" name="Id">
  <div class="text">Aggiungi al</div>
    <span class="icon" style="color: black; border-radius: 5px;"><ion-icon name="cart"></ion-icon>
    </span>
  </button>
</div>

           
			
           </form>
        </div>
        <img id="immagineSopra" src="Sfondo 3.png" style="border-radius:50px" alt="" />
      </div>
    </section>
    <ul class="cont">
    <li class="img_outer watch fade-in"> <img src="p/sfondo4.png" onclick="scambiaImmagine(this)"/></li>
    <li class="img_outer watch fade-in"> <img src="p/sfondo5.png" onclick="scambiaImmagine(this)"/></li>
    <li class="img_outer watch fade-in"> <img src="p/Sfondo6.png" onclick="scambiaImmagine(this)"/></li>
    <li class="img_outer watch fade-in"> <img src="p/Sfondo7.png" onclick="scambiaImmagine(this)"/></li>
    <li class="img_outer watch fade-in"> <img src="p/Sfondo9.png" onclick="scambiaImmagine(this)"/></li>
    <li class="img_outer watch fade-in"> <img src="Sfondo2 .png" onclick="scambiaImmagine(this)"/></li>
    <li class="img_outer watch fade-in"> <img src="Sfondo 3.png" onclick="scambiaImmagine(this)"/></li>
    <li class="img_outer watch fade-in"> <video src="VideoSfondoOrizzontale.mp4" autoplay muted loop ></video></li>
  </ul>
    
	<br>
	<hr color="#c3c3c3">
	
    <!-- Large Text -->
    <section id="about" class="section-b">
      <div class="overlay">
        <div class="section-b-inner py-5">
          <h3 class="text-2">Preziosi istanti:</h3>
          <h2 class="text-5 mt-1">Trova l'orologio che ti fa sentire straordinario ogni giorno.</h2>
          <p class="mt-1"><b>Descrizione prodotto:</b> <%=p.getDescrizione()%>; <br>
          <b>Materiali:</b> <%=p.getMateriale() %><br>
          <b>Genere:</b> <%=p.getGenere() %><br>
          <b>Tipo:</b> <%=p.getTipo() %><br>
          <b>Misura:</b> <%=p.getMisura() %><br>
          </p>
        </div>
      </div>
    </section>
    
    <hr color="#c3c3c3">
    
    <br>


<script src="JS/productpage.js"></script>
<script src="JS/index.js"></script>


</body>

<%@ include file="Footer.jsp" %>
</html>