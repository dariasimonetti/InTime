<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.ProductBean" %>
<!DOCTYPE html>
<html>
<head>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Inventory</title>
<!--Material CDN-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <!--STYLESHEET-->
    <link rel="stylesheet" href="Style/Admin.css"/>
</head>
<body>

    
    <input type="checkbox" id="nav-toggle">
    <div class="sidebar">
        
        
        <div class="sidebar-menu">
          <ul>
            <li>
              
              <a href="http://localhost/In-Time/Admin?" >
                <span class="fas fa-boxes"></span>
                Dashboard          
              </a>
              
            </li>
            <li>
              <a href="#" class="active">
                <span class="fas fa-boxes"></span>
                <span>Inventario</span>
              </a>
            </li>
          </ul>

        </div>
    </div>    

    <div class="main-content">
      <header>
        <h2>
           <label for="nav-toggle" style="cursor:pointer">
            <ion-icon name="list"></ion-icon>
          </label>
          Menu
        </h2>

        <!--  <div class="search-wrapper">
          <span class="fas fa-search"> </span>
          <input type="search" placeholder="Search..." />

        </div>-->
        
         <a class="header__logo" href="index.html">
        <img class="header__logo" src="logo.png" alt="logo In Time" width="150" height="200">
      </a>

        <div class="user-wrapper">
         <div class="">
            <h4>DAAC</h4>
            <small>Super Admin</small>
         </div>
        </div>
      </header>

      <main>
      
      <div class="recent-grid">
          <div class="projects">
            <div class="card">
              <div class="card-header controls">
              	<ion-icon name="timer" style="font-size: 20px"></ion-icon>
                <h2>Articoli</h2>
                <button class="bt" id="modal-button" style="background: transparent; border-radius: 5px; color: black;font-size: 20px;padding: 0.5rem 1rem;border: 1px solid white;"><ion-icon name="add-circle"></ion-icon></button>
                <div id="myModal" class="modal">

	<!-- Modal content -->
	
	<div class="modal-content slideDown">

		<div class="modal-header">
			<span class="close" id="closeModal">&times;</span>
			<h2>Nuovo Articolo</h2>
		</div>
		<div class="modal-body">
			<form action="AddProduct" class="modal-form" method="post">
			
				<div class="form-row">
					<label for="">Name</label>
                  <input  placeholder="Inserisci il nome..." name="nome"></input>
				</div>
				
				<div class="form-row">
					<label for="">Descrizione</label>
                  <input  placeholder="Inserisci una descrizione..." name="descrizione"></input>
				</div>
				
				<div class="form-row">
					<label for="">Prezzo</label>
                  <input  placeholder="Inserisci il prezzo..." name="prezzo"></input>
				</div>
				
				<div class="form-row">
					<label for="">Materiale</label>
                  <input  placeholder="Inserisci il materiale..." name="materiale"></input>
				</div>
				
				<div class="form-row">
					<label for="">Misura</label>
                  <input  placeholder="Inserisci la misura..." name="misura"></input>
				</div>
				
				<div class="form-row">
					<label for="">Marca</label>
                  <input  placeholder="Inserisci la marca..." name="marca"></input>
				</div>
				
				<div class="form-row">
					<label for="">Genere</label>
                  <input  placeholder="Inserisci il genere..." name="genere"></input>
				</div>
				
				<div class="form-row">
					<label for="">Tipo</label>
                  <input  placeholder="Inserisci il tipo..." name="tipo"></input>
				</div>
				
				<div class="form-row">
					<label for="">Sconto</label>
                  <input  placeholder="Inserisci lo sconto..." name="sconto"></input>
				</div>
				
				<div class="form-row">
					<label for="">Quantità</label>
                  <input  placeholder="Inserisci la quantità..." name="quantita"></input>
				</div>
				
				<br>
		
			<button class="bt" type="submit">Save</button>
	
		
		</form>
		</div>
	
</div>
	</div>
              </div>
              
              <div class="card-body">
                <div class="table-responsive">
                  <table width="100%" style="text-align:center">
                  <thead>
                    <tr>
                      <td>ID</td>
                      <td>Nome</td>
                      <td>Descrizione</td>
                      <td>Prezzo</td>
                      <td>Materiale</td>
                      <td>Misura</td>
                      <td>Marca</td>
                      <td>Genere</td>
                      <td>Tipo</td>
                      <td>Sconto</td>
                      <td>Quantità</td>
                      <td style="color: #6b6e70">Immagini</td>
                      <td style="color: #6b6e70">Modifica</td>
                      <td style="color: #6b6e70">Elimina</td>
                      

                    </tr>
                  </thead>
                  <tbody >
                  <% ArrayList <ProductBean> prodotti = new ArrayList <ProductBean>();
                  	prodotti= (ArrayList <ProductBean>) request.getAttribute("prodotti");
        		   
        		   for(ProductBean p : prodotti){ 
        		   
                  %>
                  <tr>
                      <td><%=p.getId()%></td>
                      <td><%= p.getNome() %></td>
                      <td><%= p.getDescrizione() %></td>
                      <td><%= p.getPrezzo() %>&euro;</td>
                      <td><%= p.getMateriale() %></td>
                      <td><%= p.getMisura() %></td>
                      <td><%= p.getMarca() %></td>
                      <td><%= p.getGenere() %></td>
                      <td><%= p.getTipo() %></td>
                      <td><%= p.getSconto() %>%</td>
                      <td><%= p.getQuantita() %></td>
                      <td><button class=" modal-but-3 bt" type="submit"><ion-icon name="images"></ion-icon></button></td>
                      <td>
                      <button class="modal-button-2 bt"  name="idProd" value="<%= p.getId() %>"><ion-icon name="pencil"></ion-icon></button></td>
                      
                      <td ><form action="DeleteProduct" method="post">
                      <button id="bs" class="bt" type="submit"  value="<%= p.getId() %>" name="idProdotto"><ion-icon name="trash-bin"></ion-icon></button></form></td>
					  
					
                    </tr>
                   
                    
                  
                  <% } %>
      
      
	
	     
	     </tbody>
	     

                </table>
                
                
                
                
                
                
                <div id="myModal2" class="modal">
                
                

	<!-- Modal content -->
	
	<div class="modal-content slideDown">
	

		<div class="modal-header">
			<span class="clos2" id="closeModal">&times;</span>
			<h2 >Cambia Articolo</h2>
		</div>
		<div class="modal-body">
		
			<form action="ChangeProduct" class="modal-form" method="post">
			
			
				<div class="form-row">
					<label for="">Name</label>
                  <input  placeholder="Inserisci il nome se vuoi modificarlo..." name="nome"></input>
				</div>
				
				
				
				
				<div class="form-row">
					<label for="">Descrizione</label>
                  <input  placeholder="Inserisci una descrizione se vuoi modificarla..." name="descrizione"></input>
				</div>
				
				<div class="form-row">
					<label for="">Prezzo</label>
                  <input  placeholder="Inserisci il prezzo se vuoi modificarlo..." name="prezzo"></input>
				</div>
				
				<div class="form-row">
					<label for="">Materiale</label>
                  <input  placeholder="Inserisci il materiale se vuoi modificarlo..." name="materiale"></input>
				</div>
				
				<div class="form-row">
					<label for="">Misura</label>
                  <input  placeholder="Inserisci la misura se vuoi modificarla..." name="misura"></input>
				</div>
				
				<div class="form-row">
					<label for="">Marca</label>
                  <input  placeholder="Inserisci la marca se vuoi modificarla..." name="marca"></input>
				</div>
				
				<div class="form-row">
					<label for="">Genere</label>
                  <input  placeholder="Inserisci il genere se vuoi modificarlo..." name="genere"></input>
				</div>
				
				<div class="form-row">
					<label for="">Tipo</label>
                  <input  placeholder="Inserisci il tipo se vuoi modificarlo..." name="tipo"></input>
				</div>
				
				<div class="form-row">
					<label for="">Sconto</label>
                  <input  placeholder="Inserisci lo sconto se vuoi modificarlo..." name="sconto"></input>
				</div>
				
				<div class="form-row">
					<label for="">Quantità</label>
                  <input  placeholder="Inserisci la quantità se vuoi modificarla..." name="quantita"></input>
				</div>
				
				<br>
			
	
			<button id="altro-bottone" class="bt" style="background-color: black; color: white;  " type="submit" name="idProd" >Save</button>
			<script>
    			const modalButton = document.querySelector(".modal-button-2");
    			const altroBottone = document.querySelector("#altro-bottone");

    			altroBottone.value = modalButton.value;
   				
  				</script>
		
		</form>
		</div>
	
</div>
	</div>
	
	
	<div id="myModal3" class="modal">
                
                

	<!-- Modal content -->
	
	<div class="modal-content slideDown">
	

		<div class="modal-header">
			<span class="clos3" id="closeModal">&times;</span>
			<h2 >Aggiungi Immagini</h2>
			<h4></h4>
		</div>
		<div class="modal-body" style="text-align:center; ">
		
		<form action="uploadImage" method="post" enctype="multipart/form-data" >
        <input type="file" name="image" accept="image/*" required class="bt">
        <br>
        <label for="savePath">Percorso di salvataggio:</label>
        <input type="text" id="savePath" name="savePath" required>
        <br><br>
		<button class="bt" style="background-color: black; color: white;" type="submit" value="Carica">Save</button>
		
		</form>
		</div>
	
</div>
	</div>
                
                
                
                
                
                
                </div>
              </div>

            </div>

          </div>
     
          
        </div>  
 

      </main>
    </div>

<script src="JS/admin.js"></script>
</body>
</html>