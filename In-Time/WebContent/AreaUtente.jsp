<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.CatalogoBean, model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Style/AreaUtente.css">

<title>Area Utente</title>
</head>
<body class="bg">

<%@ include file="header.jsp" %>
<% UserBean utente = (UserBean) request.getAttribute("utente");
DatiSpedizioneBean ds= (DatiSpedizioneBean) request.getAttribute("datisped");
PagamentoBean card= (PagamentoBean) request.getAttribute("carta");
ArrayList <OrderBean> cart= (ArrayList <OrderBean>) request.getAttribute("ordini"); %>

<h2 class="textGradient projTitle">Area Utente</h2>
<div class="container">
  <div class="left-column">
    <div class="section">
    <h3 class="h3Title">   Gestisci il Profilo <ion-icon name="person-circle-outline"></ion-icon></h3>
    		
				<form action="AggiornaAreaUtente" method="post">
				<div class="inputboxA">
					<ion-icon name="person-circle-outline"></ion-icon>
					<input class="place" placeholder="<%=session.getAttribute ("name") %>" value="<%=session.getAttribute ("name") %>" name="nome">
					<label for="">Nome</label>            
				</div>     			
     			
				
				
				<div class="inputboxA">
					<ion-icon name="person-circle-outline"></ion-icon>
					<input class="place" placeholder="<%=session.getAttribute ("cognome") %>" value="<%=session.getAttribute ("cognome") %>" name="cognome" >
					<label for="">Cognome</label>
				</div>				
     			
				
				
				<div class="inputboxA">
					<ion-icon name="mail-outline"></ion-icon>
					<input class="place" data-placeholder="<%=session.getAttribute ("email") %>" value="<%=session.getAttribute ("email") %>" name="email" >
					<label for="">E-mail</label>  
				</div>				
     			
				
				
				<div class="inputboxA">
					<ion-icon name="lock-closed-outline"></ion-icon>
					<input class="place" placeholder="Inserisci per cambiare password"  type="password" name="password" >
					<label for="">Password</label>                  
				</div>			
     		
				
				<div class="inputboxA">
					<ion-icon name="phone-portrait-outline"></ion-icon>
					<input class="place" maxlenght="10" placeholder="<%=utente.getTelefono() %>" value="<%=utente.getTelefono() %>"name="cellulare" >
					<label for="">Cellulare</label>
				</div>				
     			<button class="buttonA" value="1" name="Operazione">Aggiorna Profilo<span></span> <ion-icon name="refresh-outline"></ion-icon>  </button> 
     			</form>
     			<br><br>
     			<form action = "Logout">
     			<button class="box2 b2">Disconnetti<span></span> <ion-icon name="log-out-outline"></ion-icon>  </button> 
     		</form>
     	</div>
     	<br>
   <div class="section">
     <h3 class="h3Title">   Gestisci Dati Di Spedizione <ion-icon name="walk-outline"></ion-icon></h3>
     	
     			<form action="AggiornaAreaUtente" method="post">
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
     			<button class="buttonA" style="margin-bottom:25px;" value="2" name="Operazione">Aggiorna Dati Spedizione<span></span> <ion-icon name="refresh-outline"></ion-icon>  </button> 
     			</form>
				</div>
				<br>
		  		
    
    <div class="section">
    
    <h3 class="h3Title">   Gestisci il Metodo Di Pagamento <ion-icon name="card-outline"></ion-icon></h3>
    	
    	<form action="AggiornaAreaUtente" method="post">
    	
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
 			
 		<span><button class="box b" value="3" name="Operazione">Modifica</button></span>
  		</div>
  		</form>
  		
  </div>
  <br>
  </div>
  
  
  
   <div class="right-column">
  
    <div class="sectionR">
    <h3 class="h3Title">Riepilogo Ordine <ion-icon name="newspaper-outline"></ion-icon></h3> 
    <h4 class="h4Title">Prodotti</h4>
    <%
			for (OrderBean p : cart){
				
				
			 %>
    <div class="cardOrder">
  		<img style="height:150px; width:150px;" src="Prova.png"/>
  		 <div class="text">Id Ordine: <%= p.getId() %><br>
        <h4>Totale: <%= p.getPrezzo() %> &euro;</h4>
        <h5>DataOrdine: <%= p.getDataOrd() %></h5>
        
        <div class="text">
        <button class="buttonA" style="margin-left:0px; margin-bottom:25px;" style="font-size:15px;" onclick="generaFattura('<%=p.getId() %>')"><ion-icon name="document-attach"></ion-icon></button>
        </div>
        </div>
  	</div>
  	<br>
 
    	<% } %>
    
	
  	
    </div>
        
  </div>
</div>
<br>

<%if(request.getAttribute("successoProfilo")!=null){%>
<script>
window.alert("Hai aggiornato con successo il profilo dell'utente");
</script>
<%} %>

<%if(request.getAttribute("successoSpedizione")!=null){%>
<script>
window.alert("Hai aggiornato con successo i dati di spedizione dell'utente");
</script>
<%} %>

<%if(request.getAttribute("successoCarta")!=null){%>
<script>
window.alert("Hai aggiornato con successo il metodo di pagamento dell'utente");
</script>
<%} %>

 <script src="JS/areaUtente.js"></script>   
 <script src="JS/areaUtente2.js"></script>    
</body>
</html>