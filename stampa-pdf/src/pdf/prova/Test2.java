package pdf.prova;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
public class Test2 {
 private static String FILE = "D:/pdf/CroceItalia.pdf";
 
 public static void main(String[] args) {
  try {
   Document document = new Document();
   PdfWriter.getInstance(document, new FileOutputStream(FILE));
   document.open();
   // Destra
   Paragraph paragraph = new Paragraph("Questo paragrafo è allineato a destra");
   paragraph.setAlignment(Element.ALIGN_RIGHT);
   document.add(paragraph);
   // Centro
   paragraph = new Paragraph("Questo paragrafo è centrato");
   paragraph.setAlignment(Element.ALIGN_CENTER);
   document.add(paragraph);
   // Sinistra
   paragraph = new Paragraph("Questo paragrafo è allineato a sinistra");
   paragraph.setAlignment(Element.ALIGN_LEFT);
   document.add(paragraph);
   // Sinistra con indentazione
   paragraph = new Paragraph("Questo paragrafo è allineato a sinistra con indentazione");
   paragraph.setAlignment(Element.ALIGN_LEFT);
   paragraph.setIndentationLeft(50);
   document.add(paragraph);
 
   document.close();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
 
}
