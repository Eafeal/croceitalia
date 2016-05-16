package pdf.prova;
import java.io.FileOutputStream;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
 
public class Test3 {
 private static String INPUTFILE  = "C:/Users/andre/OneDrive/Documents/croce.pdf";
 private static String OUTPUTFILE  = "D:/pdf/CroceItalia.pdf";
 
 public static void main(String[] args) throws DocumentException, IOException {
  Document document = new Document();
 
  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUTFILE));
  document.open();
  PdfReader reader = new PdfReader(INPUTFILE);
   
  //Aggiunta pagina 2
  PdfImportedPage page = writer.getImportedPage(reader, 2);
  Image instance = Image.getInstance(page);
  document.add(instance);
   
//  //Inserimento di contenuto aggiuntivo
//  Paragraph newParagraph = new Paragraph("Contenuto aggiuntivo");
//  document.add(newParagraph);
//   
  document.close();
 
 }
 
}