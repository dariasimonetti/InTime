<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <%
			List<?> styles = (List<?>) request.getAttribute("prodotti");
			
			if(styles != null) {	
		    Iterator<?> it = styles.iterator();
			int i=1;
			while (it.hasNext()) {
	     %>		
			<br>try: <%=i%> <%= it.next()%>
		 <% 
		    i++;
			}
			}
		 %>
</body>
</html>