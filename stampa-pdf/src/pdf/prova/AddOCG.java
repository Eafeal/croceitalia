package pdf.prova;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



public class AddOCG {
	 private static Font bigFont  = new Font(Font.FontFamily.UNDEFINED, 12,  Font.BOLD);
	 private static Font smallFont  = new Font(Font.FontFamily.UNDEFINED, 9,  Font.BOLD);
	 private static Font piusmallFont  = new Font(Font.FontFamily.UNDEFINED, 8,  Font.BOLD);

    public static final String SRC = "C:/Users/andre/OneDrive/Documents/TemplateFattura.pdf";
    public static final String DEST = "C:/Users/andre/OneDrive/Documents/hello_ocg2.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddOCG().manipulatePdf(SRC, DEST);
    }


    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        Map<String, PdfLayer> layers = stamper.getPdfLayers();
        PdfWriter writer = stamper.getWriter();
        PdfContentByte cb = stamper.getOverContent(1);
        int cont=110;
        String numero=Integer.toString(cont);;
        PdfLayer nested = new PdfLayer("Nested layers", writer);
        PdfLayer nested_1 = new PdfLayer("Nested layer 1", writer);
        PdfLayer nested_2 = new PdfLayer("Nested layer 2", writer);
        nested.addChild(nested_1);
        nested.addChild(nested_2);
        writer.lockLayer(nested_2);
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Pubblica Assistenza Croce Italia Bernate Ticino o.n.l.u.s.",smallFont), 65, 785, 0);
        //1 spazio da sinistra 2spazio partendo dal basso      3inclinazione
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Sede Legale: Viale Monza n. 40 20014 Milano",smallFont), 27, 772, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Sede Operativa: Largo Poldo Gasparotto snc 20010 Bernate Ticino  ",smallFont), 27, 759, 0);
       
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Sede Operativa: Busto Garolfo C/O Comune 20020 Busto Garolfo",smallFont), 27, 746, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "C.F. 93029640153",smallFont), 27, 733, 0);
       
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "e-mail: croceitaliabernate@libero.it sito: www.croceitaliabernate.it",smallFont), 27, 720, 0);
       
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Iscrizione al n. MI-499 albo regionale sez. Provinciale di Milano del Volontariato",smallFont), 27, 707, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Autorizzazione Sanitaria n. 03/ST/08 del 03/09/2008 ",smallFont), 27, 694, 0);
     
        cb.endLayer();
        //////////////////////////////righe///////////////////////////////
        
  //////n sedute///////      
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "11",smallFont), 38, 544, 0);
  /////////////mese////////////////////      
        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "MAGGIO",smallFont), 70, 544, 0);
        cb.beginLayer(nested);
   ////////////km//////////////////     
        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "90",smallFont), 130, 544, 0);
        cb.beginLayer(nested);
   //////////////nome//////////////////
        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "GUALANDI VINCENZO",piusmallFont), 158, 544, 0);
        cb.beginLayer(nested);
        //////////////percorso andata/////////////
        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Settimo M-H San Carlo",piusmallFont), 260, 548, 0);
        cb.beginLayer(nested);
  //////////////percorso ritorno/////////////
        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Settimo M-H San Carlo",piusmallFont), 260, 538, 0);
        cb.beginLayer(nested);
//////////////ore//////////////////
        
  cb.beginLayer(nested);
  ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
          "3",smallFont), 394, 544, 0);
  cb.beginLayer(nested);
 ///////////quota fissa///////////
  cb.beginLayer(nested);
  ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
          "$      50",smallFont), 420, 544, 0);
  cb.beginLayer(nested);
///////////diritto di uscita///////////
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
  "S",smallFont), 495, 544, 0);
cb.beginLayer(nested);
///////////importo///////////
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
"$   123,78",smallFont), 525, 544, 0);
cb.beginLayer(nested);
        
int a=26;
int b=544;
int c=548;
int d=538;
for(int i=0;i<14;i++){
	cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "1",smallFont), 38, b-a, 0);
        //b=b-a;
       
        cb.endLayer();
////////mese///////////////////        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "MAGGIO",smallFont), 70, b-a, 0);
       // b=b-a;
       
        cb.endLayer();
///////km///////////////////////        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "12",smallFont), 130, b-a, 0);
        
        
  /////////////NOME//////////
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "GUALANDI VINCENZO",piusmallFont), 158, b-a, 0);
        cb.beginLayer(nested);
 ////////////percorso andata/////////
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Settimo M-H San Carlo",piusmallFont), 260, c-a, 0);
        cb.beginLayer(nested);
 ////////////////percorso ritorno//////////////
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Settimo M-H San Carlo",piusmallFont), 260, d-a, 0);
        cb.beginLayer(nested);
 ///////////ore//////////////////////
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "3",smallFont), 394, b-a, 0);
        cb.beginLayer(nested);
 //////////quota fissa///////////
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "$      50",smallFont), 420, b-a, 0);
        cb.beginLayer(nested);
///////////diritto di uscita///////////
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
"S",smallFont), 495, b-a, 0);
cb.beginLayer(nested);
///////////importo///////////
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
"$   123,78",smallFont), 525, b-a, 0);
cb.beginLayer(nested);
        
        d=d-a;
        c=c-a;
        b=b-a;
}
//////////////////bonifico/////////////////////
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "BONIFICO BANCARIO A :",piusmallFont), 27, 144, 0);

cb.endLayer();
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "P.A. CROCE ITALIA BERNATE TICINO",piusmallFont), 27, 133, 0);

cb.endLayer();
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "OORDINATE BANCARIE:",piusmallFont), 27, 119, 0);

cb.endLayer();
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "BANCA PROSSIMA S.p.A. Filiale 05000",piusmallFont), 27, 108, 0);

cb.endLayer();

cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "Piazza Paolo Ferrari 10 20121 MILANO",piusmallFont), 27, 94, 0);

cb.endLayer();
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "IBAN: IT60 W033 5901 6001 0000 0015 705",bigFont), 27, 69, 0);

cb.endLayer();
///////////////////calcolo/////////////////////////
cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "234",smallFont), 539, 150, 0);

cb.endLayer();

cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "245",smallFont), 539, 126, 0);

cb.endLayer();

cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "456",smallFont), 539, 94, 0);

cb.endLayer();

cb.beginLayer(nested);
ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        "456",smallFont), 539, 62, 0);

cb.endLayer();
        /////////////////////////date///////////////////////////////////////////////
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "MAGGIO",smallFont), 488, 750, 0);
       
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "11/05/2016",smallFont), 488, 721, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
        		numero,smallFont), 488, 777, 0);
      
        cb.endLayer();
//////////////////////////////////////////////////////cliente/////////////////////////////////////////
        
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Spettabile",smallFont), 270, 668, 0);
       
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "ASST TERRITORIALE RHODENSE",smallFont), 270, 655, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Viale Forlanini 25",smallFont), 270, 642, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "20024 Garbagnate Milanese",smallFont), 270, 629, 0);
    
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "MILANO",smallFont), 270, 616, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "P.I.: 09323530965",smallFont), 270, 603, 0);
        
        cb.endLayer();
        cb.beginLayer(nested);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "CIG Z4518285DB",smallFont), 270, 590, 0);
       
        cb.endLayer();
        /*cb.beginLayer(nested_1);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "nested layer 1"), 100, 800, 0);
        cb.endLayer();
        cb.beginLayer(nested_2);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "nested layer 2"), 100, 750, 0);
        cb.endLayer();
*/
      /*  PdfLayer group = PdfLayer.createTitle("Grouped layers", writer);
        PdfLayer layer1 = new PdfLayer("Group: layer 1", writer);
        PdfLayer layer2 = new PdfLayer("Group: layer 2", writer);
        group.addChild(layer1);
        group.addChild(layer2);
        cb.beginLayer(layer1);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "layer 1 in the group"), 50, 700, 0);
        cb.endLayer();
        cb.beginLayer(layer2);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "layer 2 in the group"), 50, 675, 0);
        cb.endLayer();

        PdfLayer radiogroup = PdfLayer.createTitle("Radio group", writer);
        PdfLayer radio1 = new PdfLayer("Radiogroup: layer 1", writer);
        radio1.setOn(true);
        PdfLayer radio2 = new PdfLayer("Radiogroup: layer 2", writer);
        radio2.setOn(false);
        PdfLayer radio3 = new PdfLayer("Radiogroup: layer 3", writer);
        radio3.setOn(false);
        radiogroup.addChild(radio1);
        radiogroup.addChild(radio2);
        radiogroup.addChild(radio3);
        ArrayList<PdfLayer> options = new ArrayList<PdfLayer>();
        options.add(radio1);
        options.add(radio2);
        options.add(radio3);
        writer.addOCGRadioGroup(options);
        cb.beginLayer(radio1);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "option 1"), 50, 600, 0);
        cb.endLayer();
        cb.beginLayer(radio2);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "option 2"), 50, 575, 0);
        cb.endLayer();
        cb.beginLayer(radio3);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "option 3"), 50, 550, 0);
        cb.endLayer();

        PdfLayer not_printed = new PdfLayer("not printed", writer);
        not_printed.setOnPanel(false);
        not_printed.setPrint("Print", false);
        cb.beginLayer(not_printed);
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(
                "PRINT THIS PAGE"), 300, 700, 90);
        cb.endLayer();

        PdfLayer zoom = new PdfLayer("Zoom 0.75-1.25", writer);
        zoom.setOnPanel(false);
        zoom.setZoom(0.75f, 1.25f);
        cb.beginLayer(zoom);
        ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(
                "Only visible if the zoomfactor is between 75 and 125%"), 30,
                530, 90);
        cb.endLayer();*/
        
        stamper.close();
        reader.close();
    }

}