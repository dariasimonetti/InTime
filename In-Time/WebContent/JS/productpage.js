$(function() {
        const $gallery = $('.gallery a').simpleLightbox();
      });

function scambiaImmagine(imgCliccata) {
	  const immagineSopra = document.getElementById('immagineSopra');
	  const srcCliccata = imgCliccata.src;
	  
	  imgCliccata.src = immagineSopra.src;
	  immagineSopra.src = srcCliccata;
	}