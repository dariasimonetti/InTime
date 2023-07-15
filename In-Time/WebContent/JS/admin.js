const modal = document.getElementById('myModal')
const modal2 = document.getElementById('myModal2')
const modal3 = document.getElementById('myModal3')
const modal4 = document.getElementById('myModal4')
const modal5 = document.getElementById('myModal5')
const button = document.getElementById("modal-button")
const b2 = document.querySelectorAll(".modal-button-2")
const b3 = document.querySelectorAll(".modal-but-3")
const b4 = document.getElementById("modal-button-4")
const b5 = document.getElementById("modal-button-5")
const close = document.querySelectorAll(".close")[0]
const cl2 = document.querySelectorAll(".clos2")[0]
const cl3 = document.querySelectorAll(".clos3")[0]
const cl4 = document.querySelectorAll(".clos4")[0]
const cl5 = document.querySelectorAll(".clos5")[0]
const clickable = document.querySelectorAll('.clickable')

const openModal = function() {
  modal.style.display = "block"
  document.main.style.backgroundColor="black"
}

const opModal2 = function() {
	modal2.style.display = "block"
	document.main.style.backgroundColor="black"  
}

const opModal3 = function() {
	modal3.style.display = "block"
	document.main.style.backgroundColor="black"  
}

const openModal4 = function() {
	  modal4.style.display = "block"
	  document.main.style.backgroundColor="black"
	}

const openModal5 = function() {
	  modal5.style.display = "block"
	  document.main.style.backgroundColor="black"
	}

const closeModal = function() {
  modal.style.display = "none"
  modal2.style.display = "none"
}

const closeModal2 = function() {
  modal2.style.display = "none"
}

const closeModal3 = function() {
	  modal3.style.display = "none"
	}

const closeModal4 = function() {
	  modal4.style.display = "none"
	}

const closeModal5 = function() {
	  modal5.style.display = "none"
	}



//event listeners



button.addEventListener('click', openModal, false)

for (let i = 0; i < b2.length; i++) {
  b2[i].addEventListener('click', opModal2, false);
}

for (let i = 0; i < b3.length; i++) {
	  b3[i].addEventListener('click', opModal3, false);
	}

b4.addEventListener('click', openModal4, false)

b5.addEventListener('click', openModal5, false)

close.addEventListener('click', closeModal, false)
cl2.addEventListener('click', closeModal2, false)
cl3.addEventListener('click', closeModal3, false)
cl4.addEventListener('click', closeModal4, false)
cl5.addEventListener('click', closeModal5, false)


let profileDropdownList = document.querySelector(".profile-dropdown-list");
let btn = document.querySelector(".profile-dropdown-btn");

let classList = profileDropdownList.classList;

const toggle = () => classList.toggle("active");

window.onclick = function(event) {
	  if (event.target == modal) {
	    closeModal();
	  } else if (event.target == modal2) {
	    closeModal2();
	  } else if (event.target == modal3) {
	    closeModal3();
	  } else if (event.target == modal4) {
	    closeModal4();
	  } else if (event.target == modal5) {
	    closeModal5();
	  } else if (!btn.contains(event.target)) {
	    classList.remove("active");
	  }
	};

	
