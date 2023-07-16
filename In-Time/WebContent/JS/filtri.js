// letiabili globali
let ordini = []; // Dati originali degli ordini
let ordiniFiltrati = []; // Dati filtrati degli ordini
let utentiFiltrati = [];
let righePerPagina = 6; // Numero di righe da visualizzare per pagina
let paginaCorrente = 1; // Pagina corrente
let righePerPagina2 = 10;
let i=0;


//Funzione per visualizzare la pagina specificata della tabella
function mostraPagina(tabellaId, pagina) {
    let tabella = document.getElementById(tabellaId);
  let pagine = tabella.getElementsByClassName("pagina-tabella");

  // Nascondi tutte le pagine
  for (const pagina of pagine) {
	  pagina.style.display = "none";
	}

  // Mostra la pagina selezionata
  pagine[pagina - 1].style.display = "table";
}

// Funzione per gestire il cambio pagina
function cambiaPagina(tabellaId, direzione) {
	  let tabella = document.getElementById(tabellaId);
	  let pagine = tabella.getElementsByClassName("pagina-tabella");
	  let pulsantiPaginazione = tabella.nextElementSibling.querySelectorAll(".pulsante-paginazione");
	  let paginaAttuale = 0;

	  // Trova la pagina attualmente visualizzata
	  for (i = 0; i < pagine.length; i++) {
	    if (pagine[i].style.display === "table") {
	      paginaAttuale = i + 1;
	      break;
	    }
	  }

	  // Calcola la nuova pagina in base alla direzione
	  let nuovaPagina = direzione === "avanti" ? paginaAttuale + 1 : paginaAttuale - 1;

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
  let filtroInput = document.getElementById("filtro-cliente");
  let idCliente = filtroInput.value;

  // Effettua la richiesta AJAX
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "FiltroOrdini", true); // Sostituisci "NOME_SERVLET" con il percorso corretto della tua servlet
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let response = JSON.parse(xhr.responseText);
      ordiniFiltrati = response; // Aggiorna l'array degli ordini filtrati
      aggiornaTabella(); // Aggiorna la tabella con i nuovi dati filtrati
      mostraPagina("tabella-paginata", 1); // Mostra la prima pagina
      aggiornaPulsantiPaginazione(); // Aggiorna lo stato dei pulsanti di paginazione
    }
  };
  xhr.send("idCliente=" + encodeURIComponent(idCliente));
}

function applicaFiltroDataAjax() {
	  let dataInizioInput = document.getElementById("data-inizio");
	  let dataFineInput = document.getElementById("data-fine");
	  let dataInizio = dataInizioInput.value;
	  let dataFine = dataFineInput.value;

	  // Effettua la richiesta AJAX
	  let xhr = new XMLHttpRequest();
	  xhr.open("POST", "FiltroOrdini", true); // Sostituisci "FiltroOrdiniData" con il percorso corretto della tua servlet per il filtro delle date
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	      let response = JSON.parse(xhr.responseText);
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
  let tabella = document.getElementById("tabella-paginata");
  let pagine = tabella.getElementsByClassName("pagina-tabella");

  // Rimuovi tutte le righe esistenti dalla tabella
  for (const pagina of pagine) {
	  let tbody = pagina.querySelector("tbody");
	  tbody.innerHTML = "";
	}

  // Aggiungi le righe degli ordini filtrati alla tabella
  for (let i = 0; i < ordiniFiltrati.length; i++) {
    let pagina = Math.floor(i / righePerPagina); // Calcola a quale pagina appartiene l'ordine
    let tabellaPagina = document.getElementById("pagina" + (pagina + 1));
    let tbody = tabellaPagina.querySelector("tbody");
    let row = document.createElement("tr");
    let idOrdineCell = document.createElement("td");
    let idClienteCell = document.createElement("td");
    let prezzoCell = document.createElement("td");
    let dataOrdineCell = document.createElement("td");
    let allegatoCell = document.createElement("td");

    idOrdineCell.innerText = ordiniFiltrati[i].idOrdine;
    idClienteCell.innerText = ordiniFiltrati[i].idCliente;
    prezzoCell.innerHTML = ordiniFiltrati[i].prezzo + "&euro;";
    dataOrdineCell.innerHTML = ordiniFiltrati[i].dataOrd;
    
    let button = document.createElement("button");
    button.className = "bt";
    button.style.fontSize = "15px";
    button.value = ordiniFiltrati[i].idOrdine;
    let icon = document.createElement("ion-icon");
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
  for ( i = 1; i < pagine.length; i++) {
    pagine[i].style.display = "none";
  }

  // Aggiorna lo stato dei pulsanti di paginazione
  aggiornaPulsantiPaginazione();
}


// Funzione per aggiornare lo stato dei pulsanti di paginazione
function aggiornaPulsantiPaginazione() {
	  let tabella = document.getElementById("tabella-paginata");
	  let pagine = tabella.getElementsByClassName("pagina-tabella");
	  let pulsantiPaginazione = document.getElementById("pulsanti-paginazione").querySelectorAll(".pulsante-paginazione");

	  // Disabilita tutti i pulsanti di paginazione
	  for (const pulsante of pulsantiPaginazione) {
		  pulsante.disabled = true;
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
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'Fattura', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.responseType = 'blob';
    xhr.onload = function() {
        if (xhr.status === 200) {
            // Crea un URL oggetto per il file PDF
            let blob = new Blob([xhr.response], { type: 'application/pdf' });
            let url = URL.createObjectURL(blob);

            // Crea un link per scaricare il PDF
            let link = document.createElement('a');
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
  let filtroInput = document.getElementById("filtro-cliente-admin");
  let idCliente = filtroInput.value;

  // Effettua la richiesta AJAX
  let xhr = new XMLHttpRequest();
  xhr.open("POST", "FiltroClienti", true); // Sostituisci "FiltroClienti" con il percorso corretto della tua servlet
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      let response = JSON.parse(xhr.responseText);
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
	
  let tabella = document.getElementById("tabella-paginata2");
  let pagine = tabella.getElementsByClassName("pagina-tabella");

  // Rimuovi tutte le righe esistenti dalla tabella
  for (const pagina of pagine) {
	  let tbody = pagina.querySelector("tbody");
	  tbody.innerHTML = "";
	}


  // Aggiungi le righe dei clienti filtrati alla tabella
  for (i = 0; i < utentiFiltrati.length; i++) {
	    let pagina = Math.floor(i / righePerPagina2); // Calcola a quale pagina appartiene il cliente
	    let tabellaPagina = document.getElementById("pagina" + (pagina + 1));
	    let tbody = tabellaPagina.querySelector("tbody");
	    let row = document.createElement("tr");
    let idUtenteCell = document.createElement("td");
    let nomeCell = document.createElement("td");
    let cognomeCell = document.createElement("td");
    let emailCell = document.createElement("td");
    let telefonoCell = document.createElement("td");

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
  for (i = 1; i < pagine.length; i++) {
    pagine[i].style.display = "none";
  }

  // Aggiorna lo stato dei pulsanti di paginazione
  aggiornaPulsantiPaginazioneAdmin();
}

// Funzione per aggiornare lo stato dei pulsanti di paginazione nella tabella "Admin e Clienti"
function aggiornaPulsantiPaginazioneAdmin() {
  let tabella = document.getElementById("tabella-paginata2");
  let pagine = tabella.getElementsByClassName("pagina-tabella");
  let pulsantiPaginazione = document.getElementById("pulsanti-paginazione2").querySelectorAll(".pulsante-paginazione");

  // Disabilita tutti i pulsanti di paginazione
  for ( i = 0; i < pulsantiPaginazione.length; i++) {
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