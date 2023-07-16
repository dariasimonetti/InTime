  //Gestione aggiunto o rimozione del placeholder"
   
// Seleziona tutti gli input con la classe ".place"
let inputElements = document.querySelectorAll('.place');

// Gestore di eventi a ciascun input
inputElements.forEach(function(inputElement) {
	
  // Salva il valore del placeholder nel dataset dell'input
  inputElement.dataset.placeholder = inputElement.getAttribute('data-placeholder');

  // Gestore di eventi al clic sull'input
  inputElement.addEventListener('click', function() {
    // Mostra il placeholder
    this.placeholder = this.dataset.placeholder;
  });

  // Gestore di eventi al clic sugli altri elementi
  document.addEventListener('click', function(event) {
    // Verifica se l'elemento cliccato non è l'input corrente
    if (!inputElement.contains(event.target)) {
      // Nascondi il placeholder
      inputElement.placeholder = "";
    }
  });
});


