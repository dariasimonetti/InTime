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