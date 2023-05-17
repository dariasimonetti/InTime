<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="model.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
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
              <a href="http://localhost/In-Time/Inventory?" >
                <span class="fas fa-boxes"></span>
                Inventario             
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

        <!-- <div class="search-wrapper">
          <span class="fas fa-search"> </span>
          <input type="search" placeholder="Search..." />

        </div> -->
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
        <div class="cards">
        <% ArrayList <Double> info = new ArrayList <Double> ();
        info = (ArrayList <Double>) request.getAttribute("informazioni");
        double n=info.get(0); double n2 =info.get(1); double n3=info.get(2);
        int cust=(int) n; int it= (int) n2; int ord=(int) n3;%>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Customer <ion-icon name="people"></ion-icon></h1>
              <span><%=cust%></span>
            </div>
            <div>
              <span class="fas fa-users"></span>
            </div>
          </div>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Items <ion-icon name="time"></ion-icon></h1>
              <span><%=it%></span>
            </div>
            <div>
              <span class="fas fa-clipboard-list"></span>
            </div>
          </div>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Orders <ion-icon name="card"></ion-icon></h1>
              <span><%=ord%></span>
            </div>
            <div>
              <span class="fas fa-shopping-cart"></span>
            </div>
          </div>
          <div class="card-single">
            <div style="text-align:center">
              <h1>Income <ion-icon name="diamond"></ion-icon></h1>
              <span><%=info.get(3)%>&euro;</span>
            </div>
            <div>
              <span class="fas fa-wallet"></span>
            </div>
          </div>

        </div>

        <div class="recent-grid">
          <div class="projects">
            <div class="card" >
              <div class="card-header">
              	<h2></h2>
                <h2 >Recent Orders</h2>
                <h2></h2>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table width="100%" style="text-align:center">
                  <thead>
                    <tr>
                      <td>ID Ordine</td>
                      <td>ID Cliente</td>
                      <td>Prezzo</td>

                    </tr>
                  </thead>
                  <tbody >
                  <% ArrayList <OrderBean> ordini = new ArrayList <OrderBean>();
                  ordini = (ArrayList <OrderBean>) request.getAttribute("ordini");
                  
                  for (OrderBean o : ordini){%>
                    <tr>
                      <td><%=o.getId() %></td>
                      <td><%=o.getIdCliente() %></td>
                      <td><%=o.getPrezzo() %>&euro;</td>
                    </tr>
                    <% } %>
                  </tbody>

                </table>
                </div>
              </div>

            </div>

          </div>
     
          
        </div>  

      </main>
    </div>


</body>
</html>