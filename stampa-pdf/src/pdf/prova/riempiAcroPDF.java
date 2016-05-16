package pdf.prova;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public class riempiAcroPDF {
	public static final String out = "C:/Users/andre/OneDrive/Documents/hello_ocg2.pdf";
	public static void main(String[] args) throws IOException, DocumentException {{
	
        // Leggo il prestampato
 
        PdfReader reader = new PdfReader("C:/Users/andre/OneDrive/Documents/provaDavide.pdf");
 
        // Lo stamper che scriverà il nuovo pdf riempito
 
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(out));
 
 
        //Con questo metodo mi riprendo i campi del FORM presente nel pdf, quelli che, in alcuni casi, si possono riempire anche con Acrobat Reader
 
        AcroFields form = stamper.getAcroFields();
 
 
        // Ho considerato un esempio di riempimento di alcuni dati di una fattura
 
        // Il nome del campo assegnato al PDF è il primo parametro, il secondo è il valore che vedremo scritto dopo l'operazione.
 
        form.setField("campo1", "tyktktkktkt");
 
//        form.setField("DataFattura", "xxxxxxxx");
// 
//        form.setField("Totale", "xxxxxx");
// 
//        form.setField("Scadenza", "xxxxxxx");
 
 
        //Mi riprendo il contenuto del livello sopra quello di default
 
        PdfContentByte cb = stamper.getOverContent(1);
 
        // Genero un barcode con questo metodo che sotto commenterò
 
        //Image img = getBarCodeImage("010002541258974521", cb);
 
        // Setto la posizione
 
       // img.setAbsolutePosition(503, 620);
 
        // Lo giro di 90 gradi
 
        //img.setRotation((float) Math.PI / 2);
 
        // lo aggiungo al livello
 
        //cb.addImage(img);
 
        // Questo metodo è molto importante perchè serve a chiudere il form in modo che non sia modificabile dall'utente
 
        stamper.setFormFlattening(true);
 
        // Questo invece finalizza tutto il pdf
 
        stamper.close();
	}
	}

}
