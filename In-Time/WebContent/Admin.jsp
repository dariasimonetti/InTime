<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,1,0" />
<link rel="stylesheet" href="Style/Admin.css"/>
<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!--Material CDN-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Sharp" rel="stylesheet">
    <link rel="stylesheet"
        href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <!--STYLESHEET-->
    
 
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        	response.setHeader("Pragma", "no-cache");
        	response.setHeader("Expires", "0");
        	if(session.getAttribute("name")!=null) {%>

    
    <input type="checkbox" id="nav-toggle">
    <div class="sidebar">
        
       
        <div class="sidebar-menu">
          <ul>
            <li>
              <a href="#" class="active">
                <span class="fas fa-tachometer-alt"></span>
                <span>Dashboard</span>
              </a>
            </li>
            <li>
              <a><form action="Inventory"><button class="bt" style="margin:0; padding:0; color: #ffd700">
                <span class="fas fa-boxes"></span>
                Inventario
                </button> </form>             
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
          Men&ugrave;
        </h2>

       
        
        <a class="header__logo" href="index.jsp">
        <img class="header__logo" src="logo.png" alt="logo In Time" width="150" height="200">
      </a>
     
      <div class="profile-dropdown">
        <div onclick="toggle()" class="profile-dropdown-btn">
          <div class="profile-img">
            <i class="fa-solid fa-circle"></i>
          </div>

          <span
            ><%= session.getAttribute ("name") %>
            <i class="fa-solid fa-angle-down"></i>
          </span>
        </div>
        

        <ul class="profile-dropdown-list">
          <li class="profile-dropdown-list-item">
            <a> <button class="modal-button-2">
              <i class="fa-regular fa-user"></i>
              Modifica Profilo
            </button> </a>
            
          </li>


          <li class="profile-dropdown-list-item">
            <a> <button id="modal-button">
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
      
      
      <!--CambiaProfilo Modal--> 
      <div id="myModal2" class="modal">
   <div class="modal-content slideDown">
	

		<div class="modal-header">
			<span class="clos2" id="closeModal">&times;</span>
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
    <div id="myModal" class="modal">
    
	<div class="modal-content slideDown">

		<div class="modal-header">
			<span class="close" id="closeModal">&times;</span>
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
      
      
        
      </header>

      <main>
        <div class="cards">
        <% ArrayList <Float> info = new ArrayList <Float> ();
        info = (ArrayList <Float>) request.getAttribute("informazioni");
        double n=info.get(0); double n2 =info.get(1); double n3=info.get(2);
        int cust=(int) n; int it= (int) n2; int ord=(int) n3;%>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Clienti <ion-icon name="people"></ion-icon></h1>
              <span><%=cust%></span>
            </div>
            <div>
              <span class="fas fa-users"></span>
            </div>
          </div>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Prodotti <ion-icon name="time"></ion-icon></h1>
              <span><%=it%></span>
            </div>
            <div>
              <span class="fas fa-clipboard-list"></span>
            </div>
          </div>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Ordini <ion-icon name="card"></ion-icon></h1>
              <span><%=ord%></span>
            </div>
            <div>
              <span class="fas fa-shopping-cart"></span>
            </div>
          </div>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Guadagno <ion-icon name="diamond"></ion-icon></h1>
              <span><%=info.get(3)%>&euro;</span>
            </div>
            <div>
              <span class="fas fa-wallet"></span>
            </div>
          </div>

        </div>
        
        
<%
ArrayList<OrderBean> ordini = new ArrayList<OrderBean>();
ordini = (ArrayList<OrderBean>) request.getAttribute("ordini");
int righePerPagina = 6; // Numero di righe da visualizzare per pagina
int numPagine = (int) Math.ceil(ordini.size() / (double) righePerPagina); // Calcolo del numero di pagine necessarie
%>

<div class="recent-grid">
  <div class="projects">
    <div class="card">
      <div class="card-header">
        <h2>Ordini</h2>
        
        <span>
        <input type="text" id="filtro-cliente" placeholder="ID cliente">
        <button class="bt" style="font-size: 10px;" id="applica-filtro" onclick="applicaFiltroAjax()">Applica filtro</button>
        <button class="bt" style="font-size: 10px;" onclick="rimuoviFiltro()">X</button>
        </span>
        
        <span>
        <label for="data-inizio">Dal:</label>
        <input type="date" id="data-inizio" placeholder="Data Inizio">
        <label for="data-fine">Al:</label>
        <input type="date" id="data-fine" placeholder="Data Fine">
        <button class="bt" style="font-size: 10px;" id="applica-filtro" onclick="applicaFiltroDataAjax()">Applica filtro</button>
        <button class="bt" style="font-size: 10px;" onclick="rimuoviFiltro()">X</button>
        </span>
      </div>
      <div class="card-body">
        <div id="tabella-paginata" class="table-responsive">
          <% for (int pagina = 0; pagina < numPagine; pagina++) { %>
            <table id="pagina<%= pagina + 1 %>" class="pagina-tabella" data-tabella="tabella1" width="100%" style="text-align:center">
            <caption style="color:transparent; cursor: default;">Ordini</caption>
              <thead>
                <tr>
                  <th>ID Ordine</th>
                  <th>ID Cliente</th>
                  <th>Prezzo</th>
                  <th>Data Ordine</th>
                  <th>Fattura</th>
                </tr>
              </thead>
              <tbody>
                <% 
                int start = pagina * righePerPagina;
                int end = Math.min(start + righePerPagina, ordini.size());
                for (int i = start; i < end; i++) {
                  OrderBean o = ordini.get(i);
                %>
                  <tr>
                    <td><%= o.getId() %></td>
                    <td><%= o.getIdCliente() %></td>
                    <td><%= o.getPrezzo() %>&euro;</td>
                    <td><%= o.getDataOrd() %></td>
                    <td><button class="bt" style="font-size:15px;" onclick="generaFattura('<%=o.getId()%>')"><ion-icon name="document-attach"></ion-icon></button></td>                    
                  </tr>
                <% } %>
              </tbody>
            </table>
          <% } %>
        </div>
        <div id="pulsanti-paginazione">
          <button id="btn-indietro" class="pulsante-paginazione bt" data-direzione="indietro" disabled>&lt; Indietro</button>
          <button id="btn-avanti" class="pulsante-paginazione bt" data-direzione="avanti">Avanti &gt;</button>
        </div>
      </div>
    </div>
  </div>
</div>


<%ArrayList<UserBean> utenti = new ArrayList<UserBean>();
utenti = (ArrayList<UserBean>) request.getAttribute("utenti");
int righePerPagina2 = 10; // Numero di righe da visualizzare per pagina
int numPagine2 = (int) Math.ceil(utenti.size() / (double) righePerPagina2); %>

<div class="recent-grid">
  <div class="projects">
    <div class="card">
      <div class="card-header">
        <h2>Admin <span class="material-symbols-outlined " style="font-size: 20px;">verified</span> &amp; Clienti</h2>
     	<span>
		    
		  <input type="text" id="filtro-cliente-admin" placeholder="ID cliente">
		  <button class="bt" style="font-size: 10px;" id="applica-filtro-admin" onclick="applicaFiltroClienteAdminAjax()">Applica filtro</button>
		  <button class="bt" style="font-size: 10px;" onclick="rimuoviFiltro()">X</button>

        </span>
       
      </div>
      <div class="card-body">
        <div id="tabella-paginata2" class="table-responsive">
          <% for (int pagina = 0; pagina < numPagine2; pagina++) { %>
            <table id="pagina<%= pagina + 1 %>" class="pagina-tabella" data-tabella="tabella2" width="100%" style="text-align:center">
            <caption style="color:transparent; cursor: default;">Ordini</caption>
              <thead>
                <tr>
                  <th>ID Utente</th>
                  <th>Nome</th>
                  <th>Cognome</th>
                  <th>Email</th>
                  <th>Telefono</th>
                </tr>
              </thead>
              <tbody>
                <% 
                int start = pagina * righePerPagina2;
                int end = Math.min(start + righePerPagina2, utenti.size());
                for (int i = start; i < end; i++) {
                  UserBean u = utenti.get(i);
                %>
                  <tr>
                  <%if (u.isAdmin()==true){%>
                    	<td><%= u.getId() %> <span class="material-symbols-outlined " style="font-size: 15px;">
verified
</span></td>
                    <%} else {%>
                    <td><%= u.getId() %></td> <%} %>
                    <td><%= u.getNome() %> </td>
                    <td><%= u.getCognome() %></td>
                    <td><%= u.getEmail() %></td>
                    <td><%= u.getTelefono() %></td>
                  </tr>
                <% } %>
              </tbody>
            </table>
          <% } %>
        </div>
        <div id="pulsanti-paginazione2">
          <button id="btn-ind" class="pulsante-paginazione bt" data-direzione="indietro" disabled>&lt; Indietro</button>
          <button id="btn-av" class="pulsante-paginazione bt" data-direzione="avanti">Avanti &gt;</button>
        </div>
      </div>
    </div>
  </div>
</div>

      </main>
    </div>
    
    <%} %>
    

<script src="JS/filtri.js"></script>
<script src="JS/admin2.js"></script>

</body>
</html>