window.onload = function() {
  var pagine = document.querySelectorAll('.pagina-tabella');
  var numPagine = pagine.length;
  var paginaCorrente = 1;

  function mostraPagina(pagina) {
    for (var i = 0; i < numPagine; i++) {
      pagine[i].style.display = 'none';
    }
    pagine[pagina - 1].style.display = 'table';
  }

  function cambiaPagina(delta) {
    paginaCorrente += delta;
    if (paginaCorrente < 1) {
      paginaCorrente = 1;
    } else if (paginaCorrente > numPagine) {
      paginaCorrente = numPagine;
    }
    mostraPagina(paginaCorrente);
  }

  mostraPagina(paginaCorrente);

  document.onkeydown = function(e) {
    e = e || window.event;
    if (e.keyCode == '37') {
      // Freccia sinistra
      cambiaPagina(-1);
    } else if (e.keyCode == '39') {
      // Freccia destra
      cambiaPagina(1);
    }
  };
};