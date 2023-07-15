//script per l'apertura del filtro di ricerca
    const modal2 = document.getElementById('myModal2')
	const button2 = document.getElementById("modal-button2")
	const close2 = document.getElementById("closeModal2")
	

	const openModal2 = function() {
   	modal2.style.display = "block"
       document.main.style.backgroundColor="black"
   };
	const closeModal2 = function() {
       modal2.style.display = "none"
   };


   //event listeners
	if (modal2!=null && button2!=null && close2!=null && clickable!=null){
	button2.addEventListener('click', openModal2, false)

	close2.addEventListener('click', closeModal2, false)

	button2.addEventListener('click', openModal2, false)

	window.onclick = function(event) {
		  if (event.target == modal2) {
		    modal2.style.display = "none";
		  } 
		}};