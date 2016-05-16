package pdf.prova;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModificaPdf {
    //Percorso del file da modificare
    String pathFileDaModificare;
 
    //Percorso dell'immagine da aggiungere
    String pathImmagineDaAggiungere;
 
    //Se l'immagine viene salvata sopra o sotto al testo già presente nel pdf
    boolean salvaSopra;
 
    //Nel costruttore passo tutte le informazioni che mi servono
    public ModificaPdf(String pathFileDaModificare, String pathImmagineDaAggiungere, boolean salvaSopra)
    {
 
        this.pathFileDaModificare = pathFileDaModificare;
        this.pathImmagineDaAggiungere = pathImmagineDaAggiungere;
        this.salvaSopra = salvaSopra;
 
    }
 
    //Il metodo che modifica effettivamente il PDF
    public void modifica()
    {
 
        try
        {
            //Apro il PDF con un reader
            PdfReader reader = new PdfReader(pathFileDaModificare);
 
            //Creo un nuovo PDF passando allo stamper un FileOutputStream. il metodo addPath lo descriverò sotto ma serve solo per cambiare nome al file originale
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(addPath(pathFileDaModificare)));
 
            //Istanzio un'immagine con l'immagine da aggiungere con (com.itextpdf.text.Image)
            Image img = Image.getInstance(pathImmagineDaAggiungere);
 
            //Preparo un rettangolo che ospiterà le dimensioni della siongola pagina
            Rectangle dim = null;
 
            //Preparo una classe per contenere il livello sotto e quello sopra del pdf aperto
            PdfContentByte under, over;
 
            //Il numero di pagine
            int total = reader.getNumberOfPages() + 1;
 
            //Per ogni pagina
            for (int i = 1; i<total; i++)
            {
                //Prendo le dimensioni
                dim = reader.getPageSize(reader.getPageN(i));
 
                //Riprendo la larghezza dell'immagine
                float wi = img.getWidth();
 
                //e la larghezza della pagina
                float wp = dim.getWidth();
 
                //avendo deciso di aggiungere l'immagine su tutte le pagine in basso e partendo le cordinate dal basso a sinistra le altezze mi
                //potrebbero servire solo per calcolare se l'immagine sia più bassa del documento ma per questo esempio tralascio il controllo
                //float hi = img.getHeight();
                //float hp = dim.getHeight();
 
                // Setto la posizione in basso a destra togliendo alla larghezza del foglio quella dell'immagine e 10 pixel per scostarla dal bordo
                float posx = wp - wi - 10;
                float posy = 0 + 10;
 
                //L'imposto nell'immagine
                img.setAbsolutePosition(posx, posy);
                img.setAlignment(Image.TEXTWRAP);
 
                //Creo il font per il numero di pagina
                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
 
                //Se la devo salvare sopra il testo presente
                if (salvaSopra)
                {
 
                    //Uso il livello sopra
                    over = stamper.getOverContent(i);
 
                    //e ci aggiungo l'immagine
                    over.addImage(img);
 
                    //Poi aggiungo il numero della pagina
 
                    //Inizio a scrivere
                    over.beginText();
 
                    //Setto il font
                    over.setFontAndSize(bf, 18);
 
                    //Setto la posizione dove andrò a scriverlo
                    over.setTextMatrix(30, 30);
 
                    //Scrivo il numero di pagina
                    over.showText("Pagina " + 100);
 
                    //Chiudo la scrittura
                    over.endText();
                }
                else
                {
                    //Altrimenti l'aggiungo sotto
                    under = stamper.getUnderContent(i);
                    under.addImage(img);
 
                    //Poi aggiungo il numero della pagina
 
                    //Inizio a scrivere
                    under.beginText();
 
                    //Setto il font
                    under.setFontAndSize(bf, 18);
 
                    //Setto la posizione dove andrò a scriverlo
                    under.setTextMatrix(30, 30);
 
                    //Scrivo il numero di pagina
                    under.showText("Pagina " + i);
 
                    //Chiudo la scrittura
                    under.endText();
                }
            }
            stamper.close();
        }
        catch (IOException e)
        {
            System.out.println("" + e.getMessage());
        }
        catch (DocumentException e)
        {
 
            System.out.println("" + e.getMessage());
        }
    }
    // Il metodo che serve per aggiungere la scritta timbrato al nome del file
    private String addPath(String f)
    {
        f = f.substring(0, f.lastIndexOf(".")) + "_timbrato" + ".pdf";
        return f;
    }
}