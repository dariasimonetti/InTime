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
  <% if (request.getAttribute("carrello") == null){
	  %>
	  <h1>Il Mio Carrello &Egrave; Ancora Vuoto</h1>
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
             products = (ArrayList<ProductBean>)  request.getAttribute("carrello");
			for (ProductBean p : products){
			 sum=sum+ p.getPrezzo();
 %>	
      <li class="items odd">
        
    <div class="infoWrap"> 
        <div class="cartSection">
        <img src="Sfondo 3.png" alt="" class="itemImg" />
          <p class="itemNumber">#<%= p.getId() %></p>
          <h3><%= p.getNome() %></h3>
        
           <p> <input type="text"  class="qty" placeholder="3"/>&times; $5.00</p>
        
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
      <li class="totalRow"><span class="label">Subtotal</span><span class="value"><%= sum %></span></li>
      
          <li class="totalRow"><span class="label">Shipping</span><span class="value">$5.00</span></li>
      
            <li class="totalRow"><span class="label">Tax</span><span class="value"></span></li>
            <li class="totalRow final"><span class="label">Total</span><span class="value"><%= sum+5 %></span></li>
      <li class="totalRow"><a href="#" class="btn continue">Checkout</a></li>
    </ul>
    <% } %>
  </div>
</div>
 <script src="JS/Carrello.js"></script>
</body>
</html>
