<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="model.ProductBean" %>

    
<!DOCTYPE html>
<html lang="it">
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
          <% if (p.getQuantita()!=0){ %>
           
           <div data-tooltip="Carrello" class="bt">
<button class="button-wrapper" value="<%= p.getId()%>" id="bottonecarrello" name="Id" onclick="CartAjaxFunction()">
  <div class="text">Aggiungi al</div>
    <span class="icon" style="color: black; border-radius: 5px;"><ion-icon name="cart"></ion-icon>
    </span>
  </button>
  
</div> <%} else{%>
<h3 style="color: #dd1b38;">Prodotto Esaurito</h3>
<%} %>
          <p id="messaggioConferma"></p>

           <% List<String> imagePaths = (List<String>) request.getAttribute("imagePaths"); %>
			
          
        </div>
        <img class="immagines" id="immagineSopra" src="<%=imagePaths.get(0) %>" style="border-radius:50px" alt="" />
      </div>
    </section>
    <ul class="cont">
   
    <% if (imagePaths != null) {
           for (String imagePath : imagePaths) { %>
               <li class=" watch fade-in">
                   <img class="img_outer"src="<%= imagePath %>" onclick="scambiaImmagine(this)" alt="immagine prodotto">
               </li>
    <%     }
       } %>
                
    
  </ul>
  
  
    
	<br>
	<hr style="color:#c3c3c3">
	
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
    
    <hr style="color:#c3c3c3">
    
    <br>
    
    <% if (session != null && session.getAttribute("id") != null){ %>  
     <section class="faq_dynamic-main">
      <form id="reviewForm" action="SubmitReview" >
         
          <div class="container">
            <h2 class="section-b-inner py-5"> Aggiungi una Recensione </h2>
            <br>
            <div class="input-group">
              <textarea class="arearec" name="testo" aria-label="With textarea" rows="4"  id="rewiew_message" placeholder="Scrivi la tua recensione" class="form-control mt-3 w-100 rounded-0" required></textarea>
                <input type="hidden" name="idArticolo" value="<%= p.getId() %>">
                <input type="hidden" name="idUtente" value="<%= session.getAttribute("id") %>">
                <br>
                <h3 class="section-b-inner py-5"> Aggiungi un Voto </h3>
                <div class="radio-buttons-container">
    <div class="radio-button">
        <input name="voto" id="1" class="radio-button__input" type="radio" value="1">
        <label for="1" class="radio-button__label">
            <span class="radio-button__custom"></span>
            1
        </label>
    </div>
    
    <div class="radio-button">
        <input name="voto" id="2" class="radio-button__input" type="radio" value="2">
        <label for="2" class="radio-button__label">
            <span class="radio-button__custom"></span>
            2
        </label>
    </div>
    
    <div class="radio-button">
        <input name="voto" id="3" class="radio-button__input" type="radio" value="3">
        <label for="3" class="radio-button__label">
            <span class="radio-button__custom"></span>
            3
        </label>
    </div>
    
    <div class="radio-button">
        <input name="voto" id="4" class="radio-button__input" type="radio" value="4">
        <label for="4" class="radio-button__label">
            <span class="radio-button__custom"></span>
            4
        </label>
    </div>
    
    <div class="radio-button">
        <input name="voto" id="5" class="radio-button__input" type="radio" value="5">
        <label for="5" class="radio-button__label">
            <span class="radio-button__custom"></span>
            5
        </label>
    </div>
</div>
              <br>
                <button class="box b section-b-inner">Invia la tua recensione</button>
              </div>
            </div>
    </form>
     </section><% } %> 
    <div id="reviews-container"></div>
    
  
   
   <script >
   $(function() {
       const $gallery = $('.gallery a').simpleLightbox();
     });

function scambiaImmagine(imgCliccata) {
	  const immagineSopra = document.getElementById('immagineSopra');
	  const srcCliccata = imgCliccata.src;
	  
	  imgCliccata.src = immagineSopra.src;
	  immagineSopra.src = srcCliccata;
	}
	

	
	</script>
	
	
	
<script src="JS/productpage.js"></script>
<script src="JS/index.js"></script>


</body>


</html>