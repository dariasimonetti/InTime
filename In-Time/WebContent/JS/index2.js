    //script per l'apertura del form del login e register
    const modal = document.getElementById('myModal')
    const modal2 = document.getElementById('myModal2')
	const button = document.getElementById("modal-button")
	const b2 = document.getElementById("modal-button-2")
	const close = document.querySelectorAll(".close")[0]
	const cl2 = document.querySelectorAll(".clos2")[0]
	const clickable = document.querySelectorAll('.clickable')

	const openModal = function() {
    	modal.style.display = "block"
        document.main.style.backgroundColor="black"
    };
	const opModal2 = function() {
    	modal2.style.display = "block"
        document.main.style.backgroundColor="black"
	};
	const closeModal = function() {
        modal.style.display = "none"
      	modal2.style.display = "none"
    };

	const closeModal2 = function() {
    	modal2.style.display = "none"
	};

    //event listeners
	button.addEventListener('click', openModal, false)
	b2.addEventListener('click', opModal2, false)

	close.addEventListener('click', closeModal, false)
	cl2.addEventListener('click', closeModal2, false)

	for (let i = 0; i < clickable.length; i++) {
    	clickable[i].addEventListener('click', openModal, false)
	}

	window.onclick = function(event) {
		  if (event.target == modal) {
		    modal.style.display = "none";
		  } else if (event.target == modal2) {
		    modal2.style.display = "none";
		  }
		};
