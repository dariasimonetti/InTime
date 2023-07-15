// Variabili globali
var ordini = []; // Dati originali degli ordini
var ordiniFiltrati = []; // Dati filtrati degli ordini
var utentiFiltrati = [];
var righePerPagina = 6; // Numero di righe da visualizzare per pagina
var paginaCorrente = 1; // Pagina corrente
var righePerPagina2 = 10;


//Funzione per visualizzare la pagina specificata della tabella
function mostraPagina(tabellaId, pagina) {
    var tabella = document.getElementById(tabellaId);
  var pagine = tabella.getElementsByClassName("pagina-tabella");

  // Nascondi tutte le pagine
  for (var i = 0; i < pagine.length; i++) {
    pagine[i].style.display = "none";
  }

  // Mostra la pagina selezionata
  pagine[pagina - 1].style.display = "table";
}

// Funzione per gestire il cambio pagina
function cambiaPagina(tabellaId, direzione) {
	  var tabella = document.getElementById(tabellaId);
	  var pagine = tabella.getElementsByClassName("pagina-tabella");
	  var pulsantiPaginazione = tabella.nextElementSibling.querySelectorAll(".pulsante-paginazione");
	  var paginaAttuale = 0;

	  // Trova la pagina attualmente visualizzata
	  for (var i = 0; i < pagine.length; i++) {
	    if (pagine[i].style.display === "table") {
	      paginaAttuale = i + 1;
	      break;
	    }
	  }

	  // Calcola la nuova pagina in base alla direzione
	  var nuovaPagina = direzione === "avanti" ? paginaAttuale + 1 : paginaAttuale - 1;

	  // Mostra la nuova pagina se esiste
	  if (nuovaPagina >= 1 && nuovaPagina <= pagine.length) {
	    mostraPagina(tabellaId, nuovaPagina);
	    paginaCorrente = nuovaPagina;
	  }

	  // Abilita o disabilita i pulsanti di navigazione in base alla pagina corrente
	  pulsantiPaginazione[0].disabled = paginaCorrente === 1;
	  pulsantiPaginazione[1].disabled = paginaCorrente === pagine.length;
	}



// Associa i pulsanti di paginazione alle funzioni di cambio pagina
document.getElementById("btn-indietro").addEventListener("click", function() {
  cambiaPagina("tabella-paginata", "indietro");
});

document.getElementById("btn-avanti").addEventListener("click", function() {
  cambiaPagina("tabella-paginata", "avanti");
});

document.getElementById("btn-ind").addEventListener("click", function() {
  cambiaPagina("tabella-paginata2", "indietro");
});

document.getElementById("btn-av").addEventListener("click", function() {
  cambiaPagina("tabella-paginata2", "avanti");
});




//Funzione per applicare il filtro utilizzando AJAX
function applicaFiltroAjax() {
  var filtroInput = document.getElementById("filtro-cliente");
  var idCliente = filtroInput.value;

  // Effettua la richiesta AJAX
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "FiltroOrdini", true); // Sostituisci "NOME_SERVLET" con il percorso corretto della tua servlet
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      ordiniFiltrati = response; // Aggiorna l'array degli ordini filtrati
      aggiornaTabella(); // Aggiorna la tabella con i nuovi dati filtrati
      mostraPagina("tabella-paginata", 1); // Mostra la prima pagina
      aggiornaPulsantiPaginazione(); // Aggiorna lo stato dei pulsanti di paginazione
    }
  };
  xhr.send("idCliente=" + encodeURIComponent(idCliente));
}

function applicaFiltroDataAjax() {
	  var dataInizioInput = document.getElementById("data-inizio");
	  var dataFineInput = document.getElementById("data-fine");
	  var dataInizio = dataInizioInput.value;
	  var dataFine = dataFineInput.value;

	  // Effettua la richiesta AJAX
	  var xhr = new XMLHttpRequest();
	  xhr.open("POST", "FiltroOrdini", true); // Sostituisci "FiltroOrdiniData" con il percorso corretto della tua servlet per il filtro delle date
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	      var response = JSON.parse(xhr.responseText);
	      ordiniFiltrati = response; // Aggiorna l'array degli ordini filtrati
	      aggiornaTabella(); // Aggiorna la tabella con i nuovi dati filtrati
	      mostraPagina("tabella-paginata", 1); // Mostra la prima pagina
	      aggiornaPulsantiPaginazione(); // Aggiorna lo stato dei pulsanti di paginazione
	    }
	  };
	  xhr.send("dataInizio=" + encodeURIComponent(dataInizio) + "&dataFine=" + encodeURIComponent(dataFine));
	}


// Funzione per aggiornare la tabella con i dati filtrati
//Funzione per aggiornare la tabella con i dati filtrati
function aggiornaTabella() {
  var tabella = document.getElementById("tabella-paginata");
  var pagine = tabella.getElementsByClassName("pagina-tabella");

  // Rimuovi tutte le righe esistenti dalla tabella
  for (var i = 0; i < pagine.length; i++) {
    var tbody = pagine[i].querySelector("tbody");
    tbody.innerHTML = "";
  }

  // Aggiungi le righe degli ordini filtrati alla tabella
  for (var i = 0; i < ordiniFiltrati.length; i++) {
    var pagina = Math.floor(i / righePerPagina); // Calcola a quale pagina appartiene l'ordine
    var tabellaPagina = document.getElementById("pagina" + (pagina + 1));
    var tbody = tabellaPagina.querySelector("tbody");
    var row = document.createElement("tr");
    var idOrdineCell = document.createElement("td");
    var idClienteCell = document.createElement("td");
    var prezzoCell = document.createElement("td");
    var dataOrdineCell = document.createElement("td");
    var allegatoCell = document.createElement("td");

    idOrdineCell.innerText = ordiniFiltrati[i].idOrdine;
    idClienteCell.innerText = ordiniFiltrati[i].idCliente;
    prezzoCell.innerHTML = ordiniFiltrati[i].prezzo + "&euro;";
    dataOrdineCell.innerHTML = ordiniFiltrati[i].dataOrd;
    
    var button = document.createElement("button");
    button.className = "bt";
    button.style.fontSize = "15px";
    button.value = ordiniFiltrati[i].idOrdine;
    var icon = document.createElement("ion-icon");
    icon.name = "document-attach";
    button.appendChild(icon);
    allegatoCell.appendChild(button);

    row.appendChild(idOrdineCell);
    row.appendChild(idClienteCell);
    row.appendChild(prezzoCell);
    row.appendChild(dataOrdineCell);
    row.appendChild(allegatoCell);

    tbody.appendChild(row);
  }

  // Nascondi tutte le pagine tranne la prima
  for (var i = 1; i < pagine.length; i++) {
    pagine[i].style.display = "none";
  }

  // Aggiorna lo stato dei pulsanti di paginazione
  aggiornaPulsantiPaginazione();
}


// Funzione per aggiornare lo stato dei pulsanti di paginazione
function aggiornaPulsantiPaginazione() {
	  var tabella = document.getElementById("tabella-paginata");
	  var pagine = tabella.getElementsByClassName("pagina-tabella");
	  var pulsantiPaginazione = document.getElementById("pulsanti-paginazione").querySelectorAll(".pulsante-paginazione");

	  // Disabilita tutti i pulsanti di paginazione
	  for (var i = 0; i < pulsantiPaginazione.length; i++) {
	    pulsantiPaginazione[i].disabled = true;
	  }

	  // Abilita il pulsante avanti solo se ci sono altre pagine dopo quella corrente
	  if (pagine.length > 1 && paginaCorrente < pagine.length) {
	    pulsantiPaginazione[1].disabled = false;
	  }

	  // Abilita il pulsante indietro solo se ci sono altre pagine prima di quella corrente
	  if (pagine.length > 1 && paginaCorrente > 1) {
	    pulsantiPaginazione[0].disabled = false;
	  }
	}



// Associa il pulsante Applica filtro alla funzione di applicazione del filtro
document.getElementById("applica-filtro").addEventListener("click", function() {
  applicaFiltroAjax();
});



// Funzione per rimuovere il filtro e ricaricare la pagina
function rimuoviFiltro() {
  location.reload();
}

	
function generaFattura(orderId) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Fattura', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.responseType = 'blob';
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Crea un URL oggetto per il file PDF
            var blob = new Blob([xhr.response], { type: 'application/pdf' });
            var url = URL.createObjectURL(blob);

            // Crea un link per scaricare il PDF
            var link = document.createElement('a');
            link.href = url;
            link.download = 'fattura.pdf';
            link.click();

            // Rilascia la risorsa URL oggetto
            URL.revokeObjectURL(url);
        }
    };
    xhr.send('orderId=' + encodeURIComponent(orderId));
}

//Funzione per applicare il filtro agli utenti utilizzando AJAX
function applicaFiltroClienteAdminAjax() {
  var filtroInput = document.getElementById("filtro-cliente-admin");
  var idCliente = filtroInput.value;

  // Effettua la richiesta AJAX
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "FiltroClienti", true); // Sostituisci "FiltroClienti" con il percorso corretto della tua servlet
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      utentiFiltrati=response;// Aggiorna la tabella dei clienti con i nuovi dati filtrati
      aggiornaTabellaClientiAdmin();
      mostraPagina("tabella-paginata2", 1); // Mostra la prima pagina
      aggiornaPulsantiPaginazioneAdmin(); // Aggiorna lo stato dei pulsanti di paginazione
    }
  };
  xhr.send("idCliente=" + encodeURIComponent(idCliente));
}

// Funzione per aggiornare la tabella dei clienti con i dati filtrati
function aggiornaTabellaClientiAdmin() {
	
  var tabella = document.getElementById("tabella-paginata2");
  var pagine = tabella.getElementsByClassName("pagina-tabella");

  // Rimuovi tutte le righe esistenti dalla tabella
  for (var i = 0; i < pagine.length; i++) {
    var tbody = pagine[i].querySelector("tbody");
    tbody.innerHTML = "";
  }

  // Aggiungi le righe dei clienti filtrati alla tabella
  for (var i = 0; i < utentiFiltrati.length; i++) {
	    var pagina = Math.floor(i / righePerPagina2); // Calcola a quale pagina appartiene il cliente
	    var tabellaPagina = document.getElementById("pagina" + (pagina + 1));
	    var tbody = tabellaPagina.querySelector("tbody");
	    var row = document.createElement("tr");
    var idUtenteCell = document.createElement("td");
    var nomeCell = document.createElement("td");
    var cognomeCell = document.createElement("td");
    var emailCell = document.createElement("td");
    var telefonoCell = document.createElement("td");

    idUtenteCell.innerText = utentiFiltrati[i].idUtente;
    nomeCell.innerText = utentiFiltrati[i].nome;
    cognomeCell.innerText = utentiFiltrati[i].cognome;
    emailCell.innerText = utentiFiltrati[i].email;
    telefonoCell.innerText = utentiFiltrati[i].telefono;

    row.appendChild(idUtenteCell);
    row.appendChild(nomeCell);
    row.appendChild(cognomeCell);
    row.appendChild(emailCell);
    row.appendChild(telefonoCell);

    tbody.appendChild(row);
  }

  // Nascondi tutte le pagine tranne la prima
  for (var i = 1; i < pagine.length; i++) {
    pagine[i].style.display = "none";
  }

  // Aggiorna lo stato dei pulsanti di paginazione
  aggiornaPulsantiPaginazioneAdmin();
}

// Funzione per aggiornare lo stato dei pulsanti di paginazione nella tabella "Admin e Clienti"
function aggiornaPulsantiPaginazioneAdmin() {
  var tabella = document.getElementById("tabella-paginata2");
  var pagine = tabella.getElementsByClassName("pagina-tabella");
  var pulsantiPaginazione = document.getElementById("pulsanti-paginazione2").querySelectorAll(".pulsante-paginazione");

  // Disabilita tutti i pulsanti di paginazione
  for (var i = 0; i < pulsantiPaginazione.length; i++) {
    pulsantiPaginazione[i].disabled = true;
  }

  // Abilita il pulsante avanti solo se ci sono altre pagine dopo quella corrente
  if (pagine.length > 1 && paginaCorrente < pagine.length) {
    pulsantiPaginazione[1].disabled = false;
  }

  // Abilita il pulsante indietro solo se ci sono altre pagine prima di quella corrente
  if (pagine.length > 1 && paginaCorrente > 1) {
    pulsantiPaginazione[0].disabled = false;
  }
}

document.getElementById("applica-filtro-admin").addEventListener("click", function() {
	applicaFiltroClienteAdminAjax()
	});

//Mostra la prima pagina all'avvio
mostraPagina("tabella-paginata", 1);
mostraPagina("tabella-paginata2", 1);