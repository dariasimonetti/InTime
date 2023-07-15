<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
<link rel="stylesheet" href="Style/Checkout.css">
</head>
<%@ include file="header.jsp" %>
<body>
<h2 class="textGradient projTitle">Checkout</h2>
<form action="CompletaOrdine" method="post">
<div class="container ">

  <div class="left-column">
         	<br>
   <div class="section">
     <h3 class="h3Title">   Gestisci Dati Di Spedizione <ion-icon name="walk-outline"></ion-icon></h3>
     
     <%DatiSpedizioneBean ds= (DatiSpedizioneBean) request.getAttribute("datiS");
     PagamentoBean card= (PagamentoBean) request.getAttribute("carta");
     ArrayList <ProductBean> cart= (ArrayList <ProductBean>) request.getAttribute("carrello"); %>     			
     			
				<div class="inputboxA">
					<ion-icon name="person-circle-outline"></ion-icon>
					<%if (ds!=null) {%>
					<input class="place" placeholder="<%= ds.getNome() %>" value="<%= ds.getNome() %>" name="nomeS"><%} else { %>
					<input class="place" placeholder="Inserisci il nome..." name="nomeS" required><%} %>
					<label for="">Nome per la Spedizione</label>            
				</div>
     			
				
				<div class="inputboxA">
					<ion-icon name="person-circle-outline"></ion-icon>
					<%if (ds!=null) {%>
					<input class="place" placeholder="<%= ds.getCognome() %>" value="<%= ds.getCognome() %>" name="cognomeS"><%} else { %>
					<input class="place" placeholder="Inserisci il cognome..." name="cognomeS" required><%} %>
					<label for="">Cognome per la Spedizione</label>
				</div>
				
				<div class="inputboxA">
					<ion-icon name="mail-outline"></ion-icon>
					<%if (ds!=null) {%>
					<input class="place" placeholder="<%=session.getAttribute ("email") %>" value="<%=session.getAttribute ("email") %>" name="email"><%} else { %>
					<input class="place" placeholder="Inserisci e-mail..." name="email" required><%} %>
					<label for="">E-mail</label>  
				</div>		
 
				
				<div class="inputboxA">
					<ion-icon name="mail-outline"></ion-icon>
					<%if (ds!=null) {%>
					<input class="place" placeholder="<%= ds.getVia()%>" value="<%= ds.getVia()%>" name="via"><%} else { %>
					<input class="place" placeholder="Inserisci la via..." name="via" required><%} %>
					<label for="">Via</label>  
				</div>
				
				
				<div class="inputboxA">
					<ion-icon name="lock-closed-outline"></ion-icon>
					<%if (ds!=null) {%>
					<input class="place" placeholder="<%= ds.getCivico() %>" value="<%= ds.getCivico() %>" name="civico"><%} else { %>
					<input class="place" placeholder="Inserisci il civico..." name="civico" required><%} %>
					<label for="">Civico</label>                  
				</div>				
     			
				
				<div class="inputboxA">
					<ion-icon name="phone-portrait-outline"></ion-icon>
					<%if (ds!=null) {%>
					<input class="place" placeholder="<%= ds.getCap() %>"  value="<%= ds.getCap() %>" name="cap"><%} else { %>
					<input class="place" placeholder="Inserisci il cap..." name="cap" required><%} %>
					<label for="">CAP</label>
				</div>
     			
				<div class="inputboxA">
					<ion-icon name="phone-portrait-outline"></ion-icon>
					<%if (ds!=null) {%>
					<input class="place" placeholder="<%= ds.getCitta() %>" value="<%= ds.getCitta() %>" name="citta"><%} else { %>
					<input class="place" placeholder="Inserisci la città..." name="citta" required><%} %>
					<label for="">Citta</label>
				</div>				
				</div>
				<br>
		  		
    
    <div class="section">
    
    <h3 class="h3Title">   Gestisci il Metodo Di Pagamento <ion-icon name="card-outline"></ion-icon></h3>
    	
    	<div class="card">
   			<img src="https://seeklogo.com/images/V/VISA-logo-62D5B26FE1-seeklogo.com.png" class="logo-card">
 		<label class="label">Numero Della Carta:</label>
 		<% if(card!=null){ %>
 			<input type="text" maxlength="16" class="input cardnumber"  placeholder="<%= card.getnCarta() %>" value="<%= card.getnCarta() %>" name="numC"><%} else { %>
 			<input type="text" maxlength="16" class="input cardnumber"  placeholder="**** **** **** ****" required name="numC"> <%} %>
 		<br>
 		
 		<label for="nome, cognome" class="label">Nome e Cognome:</label>
 		<%if(card!=null){ %>
 			<span>
 			<input style="width: 45%;" id="nome" type="text"class="input name"  placeholder="<%= card.getNomeT() %>" value="<%= card.getNomeT() %>" name="nomeC">
 			<input style="width: 45%;" id="cognome" type="text" class="input name"  placeholder="<%=card.getCognomeT() %>" value="<%=card.getCognomeT() %>" name="cognomeC">
 			</span>
 		 <%}else {%>
 			<span>
 			<input style="width: 45%;" id="nome" type="text" class="input name"  placeholder="Nome" required name="nomeC">
 			<input style="width: 45%;" id="cognome" type="text" class="input name"  placeholder="Cognome" required name="cognomeC">
 			</span>
 		<%} %>

 		<label class="toleft label">Data Scadenza:</label>
 		<% if(card!=null){ %>
 			<input  type="text" maxlength="5" class="input cardnumber"  placeholder="<%= card.getScadenza() %>" value="<%= card.getScadenza() %>" name="datascad"><%} else { %>
 			<input type="text" maxlength="5" class="input cardnumber"  placeholder="MM/AA" required name="datascad"> <%} %>
 		<br>
 		<label class="toleft label">CVV:</label>
 			<input maxlength="3" type="password" class="input toleft ccv" placeholder="***" required name="cvc">
 			

  		</div>
  		
  </div>
  <br>
  </div>
  
  
  
  <div class="right-column">
  
    <div class="sectionR">
    <h3 class="h3Title">Riepilogo Ordine <ion-icon name="newspaper-outline"></ion-icon></h3> 
    <h4 class="h4Title">Prodotti</h4>
    <%
			for (ProductBean p : cart){
				
				
			 %>
    <div class="cardOrder">
  		<img style="height:100px; width:100px;" src="Prova.png"/>
  		 <div class="text"><%= p.getNome() %><br>
        <h4><%= p.getPrezzo()%>&euro;</h4></div>
  	</div>
  	<br>
 
    	<% } %>
    
	
  	<button type="submit" class="box b">Completa Ordine</button>
  	
    </div>
        
  </div>

</div>
  </form>
</body>
<script src="JS/Checkout.js"></script>
</html>