<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="model.ProductBean" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Carrello</title>
  <link rel="stylesheet" href="Style/Carrello.css">
  <script src="https://cdn.lordicon.com/bhenfmcm.js"></script>
  
  

</head>
<body>
<%@ include file="header.jsp" %>
<div class="wrap cf">
  <h1 class="projTitle">&Egrave; L'Ora Giusta<span> -per?</span> Acquistare!</h1>
  <div class="heading cf">
  <% if (request.getAttribute("carrello")==null){
	  %>
	  <h1>Il Mio Carrello &egrave; Ancora Vuoto</h1>
    <a href="index.jsp" class="continue">Continua lo Shopping</a>
   <% } else {%>
    <h1>Il Mio Carrello</h1>
    <a href="index.jsp" class="continue">Continua lo Shopping</a>
    
  </div>
  <div class="cart">
    <ul class="cartWrap">
    <%
    		float sum = 0;
            ArrayList<ProductBean> products = new ArrayList<>();
             products = (ArrayList<ProductBean>) request.getAttribute("carrello");
			for (ProductBean p : products){
			 sum=sum+ p.getPrezzo();
 %>	
      <li class="items odd">
        
    <div class="infoWrap"> 
        <div class="cartSection">
        <img src="Sfondo 3.png" alt="" class="itemImg" />
          <p class="itemNumber">#<%= p.getId() %></p>
          <h3><%= p.getNome() %></h3>
        
          <p class="stockStatus"> In Stock</p>
        </div>  

        <div class="prodTotal cartSection">
          <p><%= p.getPrezzo() %></p>
        </div>
              <div class="cartSection">
              <form action="RemoveCart" method="post">
           <button class="button" name="id" value="<%=p.getId() %>" ><lord-icon
    src="https://cdn.lordicon.com/gsqxdxog.json"
    trigger="hover"
    colors="primary:#000000,secondary:#000000"
    style="width:25px;height:25px">
</lord-icon></button>
           </form>
        </div>
      </div>
      </li>
      
      <% } %>
      
 
    </ul>
  </div>
  
  
  
  <div class="subtotal cf">
    <ul>
      <li class="totalRow"><span class="label">Subtotale:</span><span class="value"><%= sum %> &euro;</span></li>
      
          <li class="totalRow"><span class="label">Spedizione:</span><span class="value">5.00 &euro;</span></li>
      
            <li class="totalRow"><span class="label">Di cui Iva:</span><span class="value">22%</span></li>
            <li class="totalRow final"><span class="label">Totale:</span><span class="value"><%= sum+5 %> &euro;</span></li>
      <li class="totalRow">
      <%if(session.getAttribute("id")!=null){%><form action="Checkout" method="post"><button class="bt">Checkout</button></form><%}else{ %>
      <div style="font-size: 22px;font-weight: bold;color: black; cursor: default;">Accedi per il Checkout</div>
      
      <%} %></li>
    </ul>
    <% } %>
  </div>
</div>
<%if(request.getAttribute("errore")!=null){ if ((Integer)request.getAttribute("errore")==0){%>
	<script>
	window.alert("Ordine completato con successo");
	</script>
<%} else if((Integer)request.getAttribute("errore")==0) {%>

	<script>
	window.alert("Qualcosa è andato storto. Il tuo ordine non è andato a buon fine");
	</script>

<%}} %>

<script src="JS/index2.js"></script>
</body>
</html>
