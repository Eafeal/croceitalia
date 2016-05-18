package pdf.oggetto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneraPdf2 {
	private static Font bigFont = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
	private static Font smallFont = new Font(Font.FontFamily.UNDEFINED, 9, Font.BOLD);
	private static Font piusmallFont = new Font(Font.FontFamily.UNDEFINED, 8, Font.BOLD);
	List<Integer> finale = new ArrayList();

	private String _src;
	private String _destIn;
	private String _destOut;
	private List<String> _nomiFile;
	private int _numeroRighe = 0;

	/**
	 * @param src
	 * @param destIn
	 * @param destOut
	 */
	public GeneraPdf2(String src, String destIn, String destOut) {
		super();
		_src = src;
		_destIn = destIn;
		_destOut = destOut;
	}

	/**
	 * @param src
	 * @param dest
	 * @param cont2
	 * @param row
	 * @param testata
	 * @param importo
	 * @param somma
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void manipulatePdf(int numPagina, List<Documento_Row> row, List<Documento_Testata> testata, Integer importo,
			Integer somma) throws IOException, DocumentException {

		finale = new ArrayList();
		PdfReader reader = new PdfReader(_src);

		String dest = _destIn + numPagina + ".pdf";
		_nomiFile.add(dest);

		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

		PdfWriter writer = stamper.getWriter();
		PdfContentByte cb = stamper.getOverContent(1);
		int cont = 110;
		String numero = Integer.toString(cont);

		PdfLayer nested = scriviTestata(writer, cb);

		scriviRighe(numPagina, row, cb, nested);

		scriviBonifico(cb, nested);

		scriviNumeroPAgina(cb, nested);

		if (hoFinito()) {
			scriviSomme(importo, cb, nested);
		}

		scriviDate(testata, cb, numero, nested);

		scriviCliente(cb, nested);

		stamper.close();
		reader.close();

	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviCliente(PdfContentByte cb, PdfLayer nested) {
		////////////////////////////////////////////////////// cliente/////////////////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Spettabile", smallFont), 270, 668, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("ASST TERRITORIALE RHODENSE", smallFont), 270,
				655, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Viale Forlanini 25", smallFont), 270, 642, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("20024 Garbagnate Milanese", smallFont), 270, 629,
				0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("MILANO", smallFont), 270, 616, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("P.I.: 09323530965", smallFont), 270, 603, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("CIG Z4518285DB", smallFont), 270, 590, 0);

		cb.endLayer();
	}

	/**
	 * @param testata
	 * @param cb
	 * @param numero
	 * @param nested
	 */
	private void scriviDate(List<Documento_Testata> testata, PdfContentByte cb, String numero, PdfLayer nested) {
		///////////////////////// date///////////////////////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.get(0).getMese_documento(), smallFont),
				488, 750, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.get(0).getData_documento(), smallFont),
				488, 721, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(numero, smallFont), 488, 777, 0);

		cb.endLayer();
	}

	/**
	 * @param importo
	 * @param cb
	 * @param nested
	 */
	private void scriviSomme(Integer importo, PdfContentByte cb, PdfLayer nested) {
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(importo.toString(), smallFont), 539, 150, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("", smallFont), 539, 126, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("", smallFont), 539, 94, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(importo.toString(), smallFont), 539, 62, 0);

		cb.endLayer();
	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviNumeroPAgina(PdfContentByte cb, PdfLayer nested) {
		//////////////////// numero pagina//////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("pagina n", smallFont), 465, 694, 0);

		cb.endLayer();
	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviBonifico(PdfContentByte cb, PdfLayer nested) {
		///////////////////////////////////////////////////////////////////////////////////////////////////////

		////////////////// bonifico/////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("BONIFICO BANCARIO A :", piusmallFont), 27, 144,
				0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("P.A. CROCE ITALIA BERNATE TICINO", piusmallFont),
				27, 133, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("OORDINATE BANCARIE:", piusmallFont), 27, 119, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("BANCA PROSSIMA S.p.A. Filiale 05000", piusmallFont), 27, 108, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Piazza Paolo Ferrari 10 20121 MILANO", piusmallFont), 27, 94, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("IBAN: IT60 W033 5901 6001 0000 0015 705", bigFont), 27, 69, 0);

		cb.endLayer();
	}

	/**
	 * @param numPagina
	 * @param row
	 * @param cb
	 * @param nested
	 * @return
	 */
	private void scriviRighe(int numPagina, List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		//////////////////////////////////////////////// ------
		//////////////////////////////////////////////// righe--------////////////////////////////////////////

		cellaSedute(row, cb, nested);
		cellaMese(row, cb, nested);
		cellaKm(row, cb, nested);
		cellaNome(row, cb, nested);
		cellaAndata(row, cb, nested);
		cellaPercorsoRitorno(row, cb, nested);
		cellaOre(row, cb, nested);
		cellaQuotaFissa(row, cb, nested);
		cellaDirittoUscita(row, cb, nested);
		cellaImporto(row, cb, nested);

		int a = 26;
		int b = 544;
		int c = 548;
		int d = 538;

		int numeroRigheDaStampare;
		if (_numeroRighe > 14) {
			numeroRigheDaStampare = 14;
		} else {
			numeroRigheDaStampare = _numeroRighe;
		}

		for (int i = 1; i <= numeroRigheDaStampare; i++) {
			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					new Phrase(row.get(i).getNum_sedute().toString(), smallFont), 38, b - a, 0);

			cb.endLayer();

			////////////////// mese///////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(i).getMese(), smallFont), 70, b - a,
					0);

			cb.endLayer();

			////////////////// km//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					new Phrase(row.get(i).getKm_percorso().toString(), smallFont), 130, b - a, 0);

			////////////////// NOME//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(i).getFk_id_paziente(), piusmallFont),
					158, b - a, 0);
			cb.beginLayer(nested);

			////////////////// percorso andata//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					new Phrase(row.get(i).getP_partenza() + "-" + row.get(0).getP_arrivo(), piusmallFont), 260, c - a,
					0);
			cb.beginLayer(nested);

			////////////////// percorso ritorno//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					new Phrase(row.get(i).getP_arrivo() + "-" + row.get(0).getP_partenza(), piusmallFont), 260, d - a,
					0);
			cb.beginLayer(nested);

			////////////////// ore //////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					new Phrase(row.get(i).getOra_sosta().toString(), smallFont), 394, b - a, 0);
			cb.beginLayer(nested);

			////////////////// quota fissa//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					new Phrase(row.get(i).getOra_sosta().toString(), smallFont), 420, b - a, 0);
			cb.beginLayer(nested);

			////////////////// diritto di uscita//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(i).getDiritto_uscita(), smallFont),
					495, b - a, 0);
			cb.beginLayer(nested);

			////////////////// importo/////////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					new Phrase("$   " + row.get(i).getImporto_doc(), smallFont), 525, b - a, 0);
			cb.beginLayer(nested);

			d = d - a;
			c = c - a;
			b = b - a;
		}

		_numeroRighe = _numeroRighe - 14;

	}

	private void cellaImporto(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////////// importo//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("$   " + row.get(0).getImporto_doc(), smallFont),
				525, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaDirittoUscita(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////////// diritto di uscita//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(0).getDiritto_uscita(), smallFont), 495,
				544, 0);
		cb.beginLayer(nested);
	}

	private void cellaQuotaFissa(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////////// quota fissa//////////////////
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("$      " + row.get(0).getQF(), smallFont), 420,
				544, 0);
		cb.beginLayer(nested);
	}

	private void cellaOre(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////////// ore//////////////////
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(0).getOra_sosta().toString(), smallFont),
				394, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaPercorsoRitorno(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////// percorso ritorno/////////////
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase(row.get(0).getP_arrivo() + "-" + row.get(0).getP_partenza(), piusmallFont), 260, 538, 0);
		cb.beginLayer(nested);
	}

	private void cellaAndata(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////// percorso andata/////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase(row.get(0).getP_partenza() + "-" + row.get(0).getP_arrivo(), piusmallFont), 260, 548, 0);
		cb.beginLayer(nested);
	}

	private void cellaNome(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////// nome//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(0).getFk_id_paziente(), piusmallFont),
				158, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaKm(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////////// km//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase(row.get(0).getKm_percorso().toString(), smallFont), 130, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaMese(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		///////////// mese////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(0).getMese(), smallFont), 70, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaSedute(List<Documento_Row> row, PdfContentByte cb, PdfLayer nested) {
		////////////////// n sedute//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(0).getNum_sedute().toString(), smallFont),
				38, 544, 0);
	}

	/**
	 * @return
	 */
	private boolean hoFinito() {
		return _numeroRighe <= 0;
	}

	/**
	 * @param writer
	 * @param cb
	 * @return
	 * @throws IOException
	 */
	private PdfLayer scriviTestata(PdfWriter writer, PdfContentByte cb) throws IOException {
		PdfLayer nested = new PdfLayer("Nested layers", writer);
		PdfLayer nested_1 = new PdfLayer("Nested layer 1", writer);
		PdfLayer nested_2 = new PdfLayer("Nested layer 2", writer);
		nested.addChild(nested_1);
		nested.addChild(nested_2);
		writer.lockLayer(nested_2);
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Pubblica Assistenza Croce Italia Bernate Ticino o.n.l.u.s.", smallFont), 65, 785, 0);
		// 1 spazio da sinistra 2spazio partendo dal basso 3inclinazione
		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Sede Legale: Viale Monza n. 40 20014 Milano", smallFont), 27, 772, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Sede Operativa: Largo Poldo Gasparotto snc 20010 Bernate Ticino  ", smallFont), 27, 759, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Sede Operativa: Busto Garolfo C/O Comune 20020 Busto Garolfo", smallFont), 27, 746, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("C.F. 93029640153", smallFont), 27, 733, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("e-mail: croceitaliabernate@libero.it sito: www.croceitaliabernate.it", smallFont), 27, 720,
				0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Iscrizione al n. MI-499 albo regionale sez. Provinciale di Milano del Volontariato",
						smallFont),
				27, 707, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Autorizzazione Sanitaria n. 03/ST/08 del 03/09/2008 ", smallFont), 27, 694, 0);

		cb.endLayer();
		return nested;
	}

	/**
	 * @param nomiFile
	 * @param dest2
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void unisciPag() throws IOException, DocumentException {

		PdfReader uno = null;
		Document document = new Document();
		PdfCopy copy = new PdfCopy(document, new FileOutputStream(_destOut));
		document.open();

		for (int i = 0; i < _nomiFile.size(); i++) {

			uno = new PdfReader(_nomiFile.get(i));
			copy.addDocument(uno);
			uno.close();
			File f = new File(_nomiFile.get(i));
			if (!f.exists()) {
				throw new IllegalArgumentException("Il File o la Directory non esiste: " + _nomiFile.get(i));

			}
			f.delete();
		}

		document.close();
	}

	/**
	 * @throws DocumentException
	 * @throws IOException
	 * 
	 */
	public void stampa() throws IOException, DocumentException {

		List<Documento_Row> row = new ArrayList();
		Integer importo = 0;
		for (int d = 0; d < row.size(); d++) {
			importo = importo + row.get(d).getImporto_doc();
		}

		List<Documento_Testata> testata = new ArrayList();
		row = OggettoPdf.oggettoPdf();
		testata = OggettoPdf.oggettoPdf2();

		Integer somma = 0;
		for (int d = 0; d < row.size(); d++) {
			importo = importo + row.get(d).getImporto_doc();
		}

		_nomiFile = new ArrayList();
		_numeroRighe = row.size();
		int numPagina = 0;
		while (nonHoFinito()) {

			this.manipulatePdf(++numPagina, row, testata, importo, somma);
		}

		this.unisciPag();

	}

	/**
	 * @return
	 */
	private boolean nonHoFinito() {
		return !hoFinito();
	}

}