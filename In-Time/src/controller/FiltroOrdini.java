package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminManager;
import model.OrderBean;

/**
 * Servlet implementation class FiltroOrdini
 */
@WebServlet("/FiltroOrdini")
public class FiltroOrdini extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltroOrdini() {
        super();
        
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Questo metodo doGet è vuoto perché la servlet non supporta richieste GET.
	    // Le operazioni di gestione delle richieste HTTP saranno implementate in altri metodi.
		throw new UnsupportedOperationException("Metodo doGet non supportato per questa servlet");
	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idClienteParam = request.getParameter("idCliente");
        String dataInizioParam = request.getParameter("dataInizio");
        String dataFineParam = request.getParameter("dataFine");

        if (idClienteParam != null) {
            // Filtra gli ordini corrispondenti all'ID cliente
            int idCliente = Integer.parseInt(idClienteParam);
            ArrayList<OrderBean> ordiniFiltrati = filtraOrdiniPerIDCliente(idCliente);

            // Invia i risultati filtrati come risposta JSON
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(convertiInJSON(ordiniFiltrati));
            out.flush();
        } else if (dataInizioParam != null && dataFineParam != null) {
            // Filtra gli ordini corrispondenti all'intervallo di date
            LocalDate dataInizio = LocalDate.parse(dataInizioParam);
            LocalDate dataFine = LocalDate.parse(dataFineParam);
            List<OrderBean> ordiniFiltrati = filtraOrdiniPerData(dataInizio, dataFine);

            // Invia i risultati filtrati come risposta JSON
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(convertiInJSON(ordiniFiltrati));
            out.flush();
        }
    }

    // Funzione per filtrare gli ordini per ID cliente
    private ArrayList<OrderBean> filtraOrdiniPerIDCliente(int idCliente) {
        ArrayList<OrderBean> ordiniFiltrati = new ArrayList<>();
        AdminManager am = new AdminManager();
        ArrayList<OrderBean> ordini = (ArrayList<OrderBean>) am.getOrdini();

        for (OrderBean ordine : ordini) {
            if (ordine.getIdCliente() == idCliente) {
                ordiniFiltrati.add(ordine);
            }
        }

        return ordiniFiltrati;
    }

    // Funzione per filtrare gli ordini per intervallo di date
    private List<OrderBean> filtraOrdiniPerData(LocalDate dataInizio, LocalDate dataFine) {
        List<OrderBean> ordiniFiltrati = new ArrayList<>();

        AdminManager am = new AdminManager();
        ArrayList<OrderBean> ordini = (ArrayList<OrderBean>) am.getOrdini();

        for (OrderBean ordine : ordini) {
        	LocalDate dataOrdine = ordine.getDataOrd().toLocalDate();


            if (dataOrdine.compareTo(dataInizio) >= 0 && dataOrdine.compareTo(dataFine) <= 0) {
                ordiniFiltrati.add(ordine);
            }
        }

        return ordiniFiltrati;
    }

    // Funzione per convertire gli oggetti OrderBean in una stringa JSON
    private String convertiInJSON(List<OrderBean> ordini) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < ordini.size(); i++) {
            OrderBean ordine = ordini.get(i);
            sb.append("{\"idOrdine\": \"").append(ordine.getId()).append("\",");
            sb.append("\"idCliente\": \"").append(ordine.getIdCliente()).append("\",");
            sb.append("\"prezzo\": \"").append(ordine.getPrezzo()).append("\",");
            sb.append("\"dataOrd\": \"").append(ordine.getDataOrd()).append("\"}");

            if (i < ordini.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
