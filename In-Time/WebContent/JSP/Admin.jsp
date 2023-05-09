<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.ProductBean" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Style/Admin.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>
         

 <%
            ArrayList<ProductBean> products = new ArrayList<>();
             products = (ArrayList<ProductBean>)  request.getAttribute("prodotti");
			for (ProductBean p : products){
			 
	     %>		

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#"><i class="fa fa-home fa-fw"></i>Home Dashboard</a></li>
                <li><a data-toggle="pill" href="#"><i class="fa fa-list-alt fa-fw"></i>Manage Catalog</a></li>
                <li><a data-toggle="pill" href="#"><i class="fa fa-check-circle fa-fw"></i>Approved Products</a></li>
                <li><a data-toggle="pill" href="#"><i class="fa fa-ban fa-fw"></i>Rejected Products</a></li>
<!--                 <li><a href="http://www.jquery2dotnet.com"><i class="fa fa-tasks fa-fw"></i>Forms</a></li> -->
                <li><a data-toggle="pill" href="#"><i class="fa fa-calendar fa-fw"></i>Calender</a></li>
                <li><a data-toggle="pill" href="#"><i class="fa fa-cogs fa-fw"></i>Settings</a></li>
            </ul>
        </div>
        <div class="col-md-9">
            <div class="card">
          <div class="pageheader well" href="#rejectedProducts">
            Rejected Products
          </div>
          <div id="rejectedProducts" class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Product Name</th>
                  <th>Category</th>
                  <th>Price</th>
                  <th>Description</th>
                  <th>Uploaded By</th>
                  <th>Rejected By</th>
                  <th>Rejected Date</th>
                  <th class="text-center">Images Uploaded</th>
                </tr>
              </thead>
              <tbody>
                
                <tr>
                  <td>Name</td>
                  <td>Category</td>
                  <td><i class="fa fa-inr" aria-hidden="true"></i> Price</td>
                  <td>This is Description</td>
                  <td>Business Owner Name</td>
                  <td>Catalog Manager</td>
                  <td>Feb-01-2019</td>
                  
                  <td class="text-center">
                    <!-- {{getImages this}} -->
                    <span id="__{{ this.uploadedBy }}__{{ this._id }}__" class=" viewRejectedProduct helpText">
                      <button type="button" class="btn btnDefault btn-sm m-0">View Images</button></span>
                  </td>
                </tr>
                 <tr>
                  <td>Name</td>
                  <td>Category</td>
                  <td><i class="fa fa-inr" aria-hidden="true"></i> Price</td>
                  <td>This is Description</td>
                  <td>Business Owner Name</td>
                  <td>Catalog Manager</td>
                  <td>Feb-01-2019</td>
                  <td class="text-center">
                    <!-- {{getImages this}} -->
                    <span id="__{{ this.uploadedBy }}__{{ this._id }}__" class=" viewRejectedProduct helpText">
                      <button type="button" class="btn btnDefault btn-sm m-0">View Images</button></span>
                  </td>
                </tr>
                 <tr>
                  <td>Name</td>
                  <td>Category</td>
                  <td><i class="fa fa-inr" aria-hidden="true"></i> Price</td>
                  <td>This is Description</td>
                  <td>Business Owner Name</td>
                  <td>Catalog Manager</td>
                  <td>Feb-01-2019</td>
                  <td class="text-center">
                    <!-- {{getImages this}} -->
                    <span id="__{{ this.uploadedBy }}__{{ this._id }}__" class=" viewRejectedProduct helpText">
                      <button type="button" class="btn btnDefault btn-sm m-0">View Images</button></span>
                  </td>
                </tr>
                
              </tbody>
            </table>
          </div>
          
          <center>
            <h4 class="helpText" style="font-size: 1em;">
              
            </h4>
         
        </div>
        </div>
    </div>
</div>

<% } %>


</body>
</html>