var recensioni = []
function CartAjaxFunction() {
  var bcart = document.getElementById("bottonecarrello");
  var valorecart = bcart.value;

  // Effettua la richiesta AJAX
  var xhr = new XMLHttpRequest();
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
	   var form = document.getElementById('reviewForm');
	   var formData = new FormData(form);
	   var url = form.action + '?' + new URLSearchParams(formData).toString();

	   var xhr = new XMLHttpRequest();
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
var idValue = document.getElementById("bottonecarrello").value;
// Effettua la richiesta AJAX
var xhr = new XMLHttpRequest();
xhr.open("GET", "Review?id=" + encodeURIComponent(idValue), true);
xhr.onreadystatechange = function () {
 if (xhr.readyState === 4 && xhr.status === 200) {
   var reviews = JSON.parse(xhr.responseText);	     
                    removeReview();
				    // Creazione della tabella delle recensioni
				    reviews.reverse();
				    // Aggiunta delle righe alla tabella
				    for (var i = 0; i < reviews.length; i++) {
				      var review = reviews[i];
				      var containerDiv = document.createElement("div");
				      containerDiv.className = "container";

				      var rowDiv = document.createElement("div");
				      rowDiv.className = "row";

				      var colDiv = document.createElement("div");
				      colDiv.className = "col-xs-12";

				      var cardDiv = document.createElement("div");
				      cardDiv.className = "card";

				      var cardInfoDiv = document.createElement("div");
				      cardInfoDiv.className = "card-info";

				      var nameDiv = document.createElement("div");
				      nameDiv.className = "name";

				      var nameText = document.createElement("b");
				      var nameParagraph = document.createElement("p");
				      nameParagraph.textContent = review.utente +" - voto  " + review.voto + "/6" ;
				      nameText.appendChild(nameParagraph);
				      nameDiv.appendChild(nameText);

				      var hrElement = document.createElement("hr");

				      var contentDiv = document.createElement("div");
				      contentDiv.className = "content";
				      var contentParagraph = document.createElement("p");
				      contentParagraph.textContent = review.testo;
				      contentDiv.appendChild(contentParagraph);

				      cardInfoDiv.appendChild(nameDiv);
				      cardInfoDiv.appendChild(hrElement);
				      cardInfoDiv.appendChild(contentDiv);

				      cardDiv.appendChild(cardInfoDiv);

				      colDiv.appendChild(cardDiv);

				      rowDiv.appendChild(colDiv);

				      containerDiv.appendChild(rowDiv);
                      var reviewsContainer = document.getElementById("reviews-container");
                      reviewsContainer.appendChild(containerDiv);
				       
				    }
                    
				 
		

		    }
		  };
		  xhr.send();
}

function removeReview(){
    var container = document.getElementById('reviews-container');
    while (container.firstChild) {
      container.removeChild(container.firstChild);
    }
}
