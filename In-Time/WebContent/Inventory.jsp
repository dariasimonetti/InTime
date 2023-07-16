<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.ProductBean" %>
<!DOCTYPE html>
<html lang="it">
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
<!-- Header -->
    <input type="checkbox" id="nav-toggle">
    <div class="sidebar">
    <div class="sidebar-menu">
    	<ul>
    		<li>
    			<a>
                <form action="Admin"><button class="bt" style="margin:0; padding:0; color: #ffd700">
                <span class="fas fa-boxes"></span>
                Dashboard
                </button> </form>
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
				<label for="nav-toggle" style="cursor: pointer"> <ion-icon name="list"></ion-icon>
				</label> Men&ugrave;
			</h2>

			<a class="header__logo" href="index.jsp">
        	<img class="header__logo" src="logo.png" alt="logo In Time" width="150" height="200">
			</a>

			<%
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "0");
				if (session.getAttribute("name") != null) {
			%>
			<div class="profile-dropdown">
				<div onclick="toggle()" class="profile-dropdown-btn">
					<div class="profile-img">
						<i class="fa-solid fa-circle"></i>
					</div>

					<span><%= session.getAttribute ("name") %> <i class="fa-solid fa-angle-down"></i>
					</span>
				</div>
			<%} %>

				<ul class="profile-dropdown-list">
					<li class="profile-dropdown-list-item">
            <a> <button id="modal-button-4">
              <i class="fa-regular fa-user"></i>
              Modifica Profilo
            </button> </a>
            
          </li>


          <li class="profile-dropdown-list-item">
            <a> <button id="modal-button-5">
              <i class="fa-regular fa-circle-question"></i>
              Registra Admin
            </button> </a>
          </li>

          <li class="profile-dropdown-list-item">
            <form action="Logout"><a><button>
              <i class="fa-solid fa-arrow-right-from-bracket"></i>
              Log out
            </button>
            </a></form>
          </li>
				</ul>
			</div>
		</header>
		
		
		<!--CambiaProfilo Modal--> 
      <div id="myModal4" class="modal">
   <div class="modal-content slideDown">
	

		<div class="modal-header">
			<span class="clos4" id="closeModal">&times;</span>
			<h2 >Modifica Profilo <%= session.getAttribute ("name") %></h2>
		</div>
		<div class="modal-body">
		
			<form action="ChangeUser" class="modal-form" method="post">
			
			
			<div class="form-row">
					<label for="">Nome</label>
                  <input  placeholder="Inserisci il nome..." name="nome"></input>
				</div>
				
				<div class="form-row">
					<label for="">Cognome</label>
                  <input  placeholder="Inserisci il cognome..." name="cognome"></input>
				</div>
				
				<div class="form-row">
					<label for="">Email</label>
                  <input  placeholder="Inserisci l'email..." name="email"></input>
				</div>
				
				<div class="form-row">
					<label for="">Password</label>
                  <input type="password" placeholder="Inserisci la password..." name="password"></input>
				</div>
				
				<div class="form-row">
					<label for="">Cellulare</label>
                  <input  placeholder="Inserisci il cellulare..." name="cellulare"></input>
				</div>
				
				<br>
			
	
			<button id="altro-bottone" class="bt" style="background-color: black; color: white;" type="submit" >Salva</button>
		
		</form>
		</div>
	
</div>
	</div>
      
      
      
    <!--Registrazione Modal--> 
    <div id="myModal5" class="modal">
    
	<div class="modal-content slideDown">

		<div class="modal-header">
			<span class="clos5" id="closeModal">&times;</span>
			<h2>Nuovo Admin</h2>
		</div>
		<div class="modal-body">
			<form action="UserRegister" class="modal-form" method="post">
			
				<div class="form-row">
					<label for="">Nome</label>
                  <input  placeholder="Inserisci il nome..." name="nome" required></input>
				</div>
				
				<div class="form-row">
					<label for="">Cognome</label>
                  <input  placeholder="Inserisci il nome..." name="cognome" required></input>
				</div>
				
				<div class="form-row">
					<label for="">Email</label>
                  <input  placeholder="Inserisci il nome..." name="email" required></input>
				</div>
				
				<div class="form-row">
					<label for="">Password</label>
                  <input type="password" placeholder="Inserisci il nome..." name="password" required></input>
				</div>
				
				<div class="form-row">
					<label for="">Cellulare</label>
                  <input  placeholder="Inserisci il nome..." name="cellulare" required></input>
				</div>
				
				<br>
		
			<button class="bt" style="background-color: black; color: white;"  type="submit" name="admin" value="true">Salva</button>
	
		
		</form>
		</div>
	
</div>
	</div>

<!-- Main -->

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
					<label for="">Nome</label>
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
					<label for="">Quantit&agrave;</label>
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
                  <table  style="text-align:center;  width:100%">
                  <caption style="color:transparent">Articoli</caption>
                  <thead>
                    <tr>
                      <th style="font-size: 13px">ID</th>
                      <th style="font-size: 13px">Nome</th>
                      <th style="font-size: 13px">Descrizione</th>
                      <th style="font-size: 13px">Prezzo</th>
                      <th style="font-size: 13px">Materiale</th>
                      <th style="font-size: 13px">Misura</th>
                      <th style="font-size: 13px">Marca</th>
                      <th style="font-size: 13px">Genere</th>
                      <th style="font-size: 13px">Tipo</th>
                      <th style="font-size: 13px">Sconto</th>
                      <th style="font-size: 13px">Quantit&agrave;</th>
                      <th style="color: #6b6e70; font-size: 13px">Immagini</th>
                      <th style="color: #6b6e70; font-size: 13px">Modifica</th>
                      <th style="color: #6b6e70; font-size: 13px">Elimina</th>
                      

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
                      <td><button class=" modal-but-3 bt" type="submit" value="<%=p.getId() %>" name="idP" onclick="getImages('<%=p.getId() %>'); setSavePath(this);"><ion-icon name="images"></ion-icon></button></td>
                      <td>
                      <button class="modal-button-2 bt"  name="idProd" value="<%= p.getId() %>" data-id="<%= p.getId() %>"><ion-icon name="pencil"></ion-icon></button></td>
                      
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
					<label for="">Quantit&agrave;</label>
                  <input  placeholder="Inserisci la quantità se vuoi modificarla..." name="quantita"></input>
				</div>
				
				<br>
			
	
			<button id="salva" class="bt" style="background-color: black; color: white;" type="submit" name="idProd" data-id="">Save</button>
			
			
		
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
        <br>
		<button class="bt" style="background-color: black; color: white;" name="savePath" type="submit" id="altro-b">Aggiungi</button>
		
		
  		</form>
  		<br><br>
  		
  		<div class="modal-header">
  		<h4></h4>
  		<h2 >Cancella Immagini</h2>
		<h4></h4>	
  		</div>
  		<form action="deleteImages" method="post" id="">
  		<div id="imageContainer" class="image"></div>
  		
		<button class="bt" style="background-color: black; color: white; margin-top: 3%;" type="submit" >Rimuovi</button>
		</form>
	
</div>
	</div>
                </div>
              </div>

            </div>

          </div>
        </div>  
 

      </main>
    </div>
    
    
  <script>
  const modalButtons = document.querySelectorAll('.modal-button-2');
  const salvaButton = document.getElementById('salva');

  modalButtons.forEach(button => {
    button.addEventListener('click', () => {
      const id = button.getAttribute('data-id');
      salvaButton.setAttribute('value', id);
    });
  });
</script>

    
    <script>function setSavePath(button) {
  const modalBut = button;
  const altroBott = document.querySelector("#altro-b");
  const selectedId = modalBut.value;
  altroBott.value = selectedId;
}</script>
    
    <script>
    // Funzione per ottenere l'elenco di immagini dalla sottodirectory specificata
    function getImages(subdirectory) {
    
    fetch("getImages?subdirectory=" + subdirectory)
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            displayImages(data.imagePaths);
        })
        .catch(function(error) {
            console.log(error);
        });
}

function displayImages(imagePaths) {
    var imageContainer = document.getElementById("imageContainer");
    imageContainer.innerHTML = "";

    for (var i = 0; i < imagePaths.length; i++) {
        var imageElement = document.createElement("img");
        imageElement.src = imagePaths[i];
        imageElement.alt = "Image " + (i + 1);
        imageElement.className = "imges";
        
        var checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.value = imagePaths[i];
        checkbox.name = "selectedImages";
        checkbox.style="width: 20px; height: 20px;"
        
        imageContainer.appendChild(checkbox);
        imageContainer.appendChild(imageElement);
    }
}

</script>
    

<script src="JS/admin.js"></script>

</body>
</html>