const modal = document.getElementById('myModal')
const modal2 = document.getElementById('myModal2')
const button = document.getElementById("modal-button")
const b2 = document.querySelectorAll(".modal-button-2")
const close = document.querySelectorAll(".close")[0]
const cl2 = document.querySelectorAll(".clos2")[0]
const clickable = document.querySelectorAll('.clickable')


const openModal = function() {
  modal.style.display = "block"
  document.main.style.backgroundColor="black"
}

const opModal2 = function() {
	modal2.style.display = "block"
	document.main.style.backgroundColor="black"  
}

const closeModal = function() {
	  modal.style.display = "none"
	  modal2.style.display = "none"
	}

const closeModal2 = function() {
	  modal2.style.display = "none"
	}

button.addEventListener('click', openModal, false)

for (const element of b2) {
  element.addEventListener('click', opModal2, false);
}

close.addEventListener('click', closeModal, false)
cl2.addEventListener('click', closeModal2, false)


let profileDropdownList = document.querySelector(".profile-dropdown-list");
let btn = document.querySelector(".profile-dropdown-btn");

let classList = profileDropdownList.classList;

const toggle = () => classList.toggle("active");

window.onclick = function(event) {
	  if (event.target == modal) {
	    closeModal();
	  } else if (event.target == modal2) {
	    closeModal2();
	  } else if (!btn.contains(event.target)) {
	    classList.remove("active");
	  }
	};

