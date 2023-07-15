package model;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

// Importa le classi necessarie
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

// ...

public class Invoice {
	
	public byte[] generatePDF(String orderId) throws IOException {
		
		Connection con= null;
		PreparedStatement ps= null;
		String idCl="", tot="", dataO="", nome="", cognome="", via="", civico="", cap="", citta="";
		ArrayList <String> idArt= new ArrayList<>();
		ArrayList <String> quantita= new ArrayList<>();
		ArrayList <String> prezzi= new ArrayList<>();
		ArrayList <String> articoli= new ArrayList<>();
		
		
		try {
			con= DriverManagerConnection.createDBConnection();
			
			String query="Select Id_Ordine, Id_Articolo, c.Id_Cliente, PrezzoTotale, DataO, Quantita_ordinata, Prezzo_Articolo, a.Nome as NomeArticolo, cl.Nome AS NomeCliente, cl.Cognome, Via, Civico, CAP, Citta\n" + 
					"FROM ((((intime.ordine AS o JOIN intime.contiene AS c\n" + 
					"ON (o.Id = c.ID_Ordine)) JOIN intime.articolo as a ON (c.Id_Articolo = a.Id))\n" + 
					"JOIN utente as cl ON (c.Id_Cliente = cl.Id)) JOIN datispedizione as ds ON (cl.Id = ds.IdUtente))\n" + 
					"Where Id_Ordine = ?";
			ps= con.prepareStatement(query);
			ps.setString(1, orderId);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				idArt.add(rs.getString("Id_Articolo"));
				idCl = rs.getString("Id_Cliente");
				tot = rs.getString("PrezzoTotale");
				dataO = rs.getString("DataO");
				quantita.add(rs.getString("Quantita_ordinata"));
				prezzi.add(rs.getString("Prezzo_Articolo"));
				articoli.add(rs.getString("NomeArticolo"));
				nome = rs.getString("NomeCliente");
				cognome = rs.getString("Cognome");
				via = rs.getString("Via");
				civico = rs.getString("Civico");
				cap = rs.getString("CAP");
				citta = rs.getString("Citta");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		

	    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
	    	
	    	//Importa il PDF con il modello della fattura non riuscivo a usare un url diverso
	    	PDDocument document = PDDocument.load(new File("C:\\Users\\simon\\git\\InTime\\In-Time\\WebContent\\Fattura.pdf"));
	    	
	    	//Modulo del documento
	    	PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
	    	
	    	//Imposta i valori del campo del modulo
	    	acroForm.getField("NumFatt").setValue(orderId+"/2023");
	    	acroForm.getField("DataFatt").setValue(dataO);
	    	tot=tot+5;
	    	acroForm.getField("TotalePagato").setValue(tot+"€");
	    	acroForm.getField("Spedizione").setValue("5.00€");
	    	acroForm.getField("Spett").setValue(nome+" "+cognome+"\nResidente in Via: "+via+", "+ civico+", "+ citta+", "+ cap+ "\nCodice Identificativo: "+ idCl);
	    	
	    	
	    	
	    	String sub;
	    	float s=0;
	    	
	    	
	    	for (int i = 0; i < articoli.size(); i++) {
	    		
	    		  String campoArticolo = "Articolo" + (i + 1);
	    		  String campoPrezzo = "PrezzoUn" + (i + 1);
	    		  String campoQuantita = "Quantita" + (i + 1);
	    		  String campoPrezzoU= "Pr"+ (i + 1);
	    		  
	    		  String valoreArticolo = articoli.get(i);
	
	    		  String valoreQuantita = quantita.get(i);
	    		  float q= Float.parseFloat(valoreQuantita);
	    		  
	    		  String valorePrezzo = prezzi.get(i);
	    		  
	    		  float p= Float.parseFloat(valorePrezzo);
	    		  float pt=(p*q);
	    		  s+= pt;
	    		  String valoreId = idArt.get(i);
	    		  
	    		  acroForm.getField(campoQuantita).setValue(valoreQuantita);
	    		  acroForm.getField(campoPrezzoU).setValue(valorePrezzo);
	    		  acroForm.getField(campoArticolo).setValue("(Cod.Art : "+ valoreId+")      "+valoreArticolo);
	    		  acroForm.getField(campoPrezzo).setValue(pt+" €");
	    		}
	    	
	    	sub= Float.toString(s);
	    	acroForm.getField("Subtotale").setValue(sub+" €");
	    	
	    	float iv= (22*Float.parseFloat(sub))/122;
	    	
	    	
	    	DecimalFormat decimalFormat = new DecimalFormat("#.##");
	    	decimalFormat.setRoundingMode(RoundingMode.DOWN); // Opzionale, per arrotondare verso il basso

	    	String iva = decimalFormat.format(iv);
	    	acroForm.getField("Iva").setValue(iva+" €");
	    	
	    	List<PDField> fields = acroForm.getFields();

	    	// Imposta l'attributo "read-only" per ogni campo modulo
	    	for (PDField field : fields) {
	    	    field.setReadOnly(true);
	    	}

	      // Salva il documento in un array di byte
	      document.save(outputStream);
	      document.close();

	      // Restituisci l'array di byte del documento PDF
	      return outputStream.toByteArray();
	    }
	  }

	}



