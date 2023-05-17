const modal = document.getElementById('myModal')
const modal2 = document.getElementById('myModal2')
const modal3 = document.getElementById('myModal3')
const button = document.getElementById("modal-button")
const b2 = document.querySelectorAll(".modal-button-2")
const b3 = document.querySelectorAll(".modal-but-3")
const close = document.querySelectorAll(".close")[0]
const cl2 = document.querySelectorAll(".clos2")[0]
const cl3 = document.querySelectorAll(".clos3")[0]
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

//event listeners



button.addEventListener('click', openModal, false)

for (let i = 0; i < b2.length; i++) {
  b2[i].addEventListener('click', opModal2, false);
}

for (let i = 0; i < b3.length; i++) {
	  b3[i].addEventListener('click', opModal3, false);
	}

close.addEventListener('click', closeModal, false)
cl2.addEventListener('click', closeModal2, false)
cl3.addEventListener('click', closeModal3, false)

for (let i = 0; i < clickable.length; i++) {
  clickable[i].addEventListener('click', openModal, false)
  clickable[i].addEventListener('click', function() {
    let name = this.dataset.name
    let price = this.dataset.price
    document.querySelector("#product-name").textContent = name
    document.querySelector("#product-price").textContent = price
  }, false)
}

window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none"
  }
}

window.onclick = function(event) {
  if (event.target == modal2) {
    modal2.style.display = "none"
  }
}

window.onclick = function(event) {
	  if (event.target == modal3) {
	    modal3.style.display = "none"
	  }
	}