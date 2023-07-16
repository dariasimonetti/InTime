    //script per l'apertura del form del login e register
    const modal = document.getElementById('myModal')
	const button = document.getElementById("modal-button")
	const close = document.querySelectorAll(".close")[0]
	const clickable = document.querySelectorAll('.clickable')

	const openModal = function() {
    	modal.style.display = "block"
        document.main.style.backgroundColor="black"
    };
	const closeModal = function() {
        modal.style.display = "none"
    };


    //event listeners
	if (modal!=null && button!=null && close!=null && clickable!=null){
	button.addEventListener('click', openModal, false)

	close.addEventListener('click', closeModal, false)

	for (const element of clickable) {
  element.addEventListener('click', openModal, false);
}

	window.onclick = function(event) {
		  if (event.target == modal) {
		    modal.style.display = "none";
		  } 
		}};