let recensioni = []
function CartAjaxFunction() {
  let bcart = document.getElementById("bottonecarrello");
  let valorecart = bcart.value;

  // Effettua la richiesta AJAX
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "AddCart", true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {

      // Visualizza l'alert con il messaggio di conferma
      window.alert("Prodotto aggiunto al carrello con successo");
    }
  };
  xhr.send("Id=" + encodeURIComponent(valorecart));
}

function submitReview() {
	   let form = document.getElementById('reviewForm');
	   let formData = new FormData(form);
	   let url = form.action + '?' + new URLSearchParams(formData).toString();

	   let xhr = new XMLHttpRequest();
	   xhr.open('GET', url);
	   xhr.onreadystatechange = function() {
	     if (xhr.readyState === XMLHttpRequest.DONE) {
	       if (xhr.status === 200) {
	         console.log('Recensione inviata con successo!');
	          
	         ReviewAjaxFunction();
	       } else {
	         console.log('Si è verificato un errore durante l\'invio della recensione: ' + xhr.status);
	         // Gestisci l'errore o visualizza un messaggio di errore
	       }
	     }
	   };
	   xhr.send();
	 }
document.getElementById('reviewForm').addEventListener('submit', function(event) {
	   event.preventDefault();
	   submitReview();
	 });
function ReviewAjaxFunction() {
let idValue = document.getElementById("bottonecarrello").value;
//Effettua la richiesta AJAX
let xhr = new XMLHttpRequest();
xhr.open("GET", "Review?id=" + encodeURIComponent(idValue), true);
xhr.onreadystatechange = function () {
if (xhr.readyState === 4 && xhr.status === 200) {
let reviews = JSON.parse(xhr.responseText);	     
                 removeReview();
				    // Creazione della tabella delle recensioni
				    reviews.reverse();
				    // Aggiunta delle righe alla tabella
				    for (const review of reviews) {
				    	  let containerDiv = document.createElement("div");
				    	  containerDiv.className = "container";

				    	  let rowDiv = document.createElement("div");
				    	  rowDiv.className = "row";

				    	  let colDiv = document.createElement("div");
				    	  colDiv.className = "col-xs-12";

				    	  let cardDiv = document.createElement("div");
				    	  cardDiv.className = "card";

				    	  let cardInfoDiv = document.createElement("div");
				    	  cardInfoDiv.className = "card-info";

				    	  let nameDiv = document.createElement("div");
				    	  nameDiv.className = "name";

				    	  let nameText = document.createElement("b");
				    	  let nameParagraph = document.createElement("p");
				    	  nameParagraph.textContent = review.utente + " - voto " + review.voto + "/5";
				    	  nameText.appendChild(nameParagraph);
				    	  nameDiv.appendChild(nameText);

				    	  let hrElement = document.createElement("hr");

				    	  let contentDiv = document.createElement("div");
				    	  contentDiv.className = "content";
				    	  let contentParagraph = document.createElement("p");
				    	  contentParagraph.textContent = review.testo;
				    	  contentDiv.appendChild(contentParagraph);

				    	  cardInfoDiv.appendChild(nameDiv);
				    	  cardInfoDiv.appendChild(hrElement);
				    	  cardInfoDiv.appendChild(contentDiv);

				    	  cardDiv.appendChild(cardInfoDiv);

				    	  colDiv.appendChild(cardDiv);

				    	  rowDiv.appendChild(colDiv);

				    	  containerDiv.appendChild(rowDiv);

				    	  let reviewsContainer = document.getElementById("reviews-container");
				    	  reviewsContainer.appendChild(containerDiv);
				    	}

                 
				 
		

		    }
		  };
		  xhr.send();
}

function removeReview(){
 let container = document.getElementById('reviews-container');
 while (container.firstChild) {
   container.removeChild(container.firstChild);
 }
}

document.addEventListener('DOMContentLoaded', function() {
	  ReviewAjaxFunction();
	});


document.getElementById('reviewForm').addEventListener('submit', function(event) {
	  document.getElementById('rewiew_message').value = ''; // Cancella il contenuto della textarea
	  document.getElementById('reviewForm').reset();
	});