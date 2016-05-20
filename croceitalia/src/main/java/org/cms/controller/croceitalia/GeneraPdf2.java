package org.cms.controller.croceitalia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	private static Font		bigFont			= new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
	private static Font		smallFont		= new Font(Font.FontFamily.UNDEFINED, 9, Font.BOLD);
	private static Font		piusmallFont	= new Font(Font.FontFamily.UNDEFINED, 8, Font.BOLD);
	List<Integer>			finale			= new ArrayList();

	private final String	_src;
	private final String	_destIn;
	private final String	_destOut;
	private List<String>	_nomiFile;
	
	private final String _intestazione1;
	private final String _intestazione2;
	private final String _intestazione3;
	private final String _intestazione4;
	private final String _intestazione5;
	private final String _intestazione6;
	private final String _intestazione7;
	private final String _intestazione8;
	private final String _intestazione9;
	
//	private  String _intestazione1;
//	private  String _intestazione2;
//	private  String _intestazione3;
//	private  String _intestazione4;
//	private  String _intestazione5;
//	private  String _intestazione6;
//	private  String _intestazione7;
//	private  String _intestazione8;
//	private  String _intestazione9;
	
	private int				_numeroRighe	= 0;
	private int				_indiceRiga	= 0;

	private Documento_Testata _testata;
	private List<Documento_Righe> _righe = new ArrayList();
	

	/**
	 * @param src
	 * @param destIn
	 * @param destOut
	 */
	
//	public GeneraPdf2(String src, String destIn, String destOut,String intestazione1,
//			String intestazione2,String intestazione3,String intestazione4,String intestazione5,String intestazione6,String intestazione7,String intestazione8,String intestazione9) {
//
//		super();
//		_src = src;
//		_destIn = destIn;
//		_destOut = destOut;
//		_intestazione1=intestazione1;
//		_intestazione2=intestazione2;
//		_intestazione3=intestazione3;
//		_intestazione4=intestazione4;
//		_intestazione5=intestazione5;
//		_intestazione6=intestazione6;
//		_intestazione7=intestazione7;
//		_intestazione8=intestazione8;
//		_intestazione9=intestazione9;
//	}

	public GeneraPdf2(String nomeFile, Documento_Testata testata, List<Documento_Righe> righe) {

		super();
		
		_intestazione1="Pubblica Assistenza Croce Italia Bernate Ticino o.n.l.u.s.";
		_intestazione2="Sede Legale: Viale Monza n. 40 20014 Milano";
		_intestazione3="Sede Operativa: Largo Poldo Gasparotto snc 20010 Bernate Ticino  ";
		_intestazione4="Sede Operativa: Busto Garolfo C/O Comune 20020 Busto Garolfo";
		_intestazione5="C.F. 93029640153";
		_intestazione6="Tel.:02-49.79.05.67  Mobile: 345-59.16.824  Fax: 02-84.56.66.47";
		_intestazione7="e-mail: croceitaliabernate@libero.it sito: www.croceitaliabernate.it";
	    _intestazione8="Iscrizione al n. MI-499 albo regionale sez. Provinciale di Milano del Volontariato";
		_intestazione9="Autorizzazione Sanitaria n. 03/ST/08 del 03/09/2008 ";
		
		_src = "D:/workspace/upload/croceitalia/TemplateFattura.pdf";
		_destIn = "D:/workspace/upload/croceitalia/generapdf/documento";
		_destOut = "D:/workspace/upload/croceitalia/generapdf/compl/"+nomeFile;		
		
		_testata = testata;
		_righe = righe;		

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
	public void manipulatePdf(int numPagina, List<Documento_Righe> row, Documento_Testata testata,
			Integer importo, Integer somma) throws IOException, DocumentException {

		finale = new ArrayList();
		PdfReader reader = new PdfReader(_src);

		String dest = _destIn + numPagina + ".pdf";
		_nomiFile.add(dest);

		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

		PdfWriter writer = stamper.getWriter();
		PdfContentByte cb = stamper.getOverContent(1);
		int cont = testata.getNum_documento();
		String numero = Integer.toString(cont);

		PdfLayer nested = scriviTestata(writer, cb);

		scriviRighe(numPagina, row, cb, nested);

		scriviBonifico(cb, nested,testata);

		scriviNumeroPAgina(cb, nested,numPagina);

		if (hoFinito()) {
			scriviSomme(importo, cb, nested,testata);
		}

		scriviDate(testata, cb, numero, nested);

		scriviCliente(cb, nested,testata);

		stamper.close();
		reader.close();

	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviCliente(PdfContentByte cb, PdfLayer nested,Documento_Testata testata) {

		// //////////////////////////////////////////////////// cliente/////////////////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Spettabile", smallFont), 270, 668, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getCliente().getRagione_sociale(), smallFont), 270,
				655, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Viale/Via "+testata.getCliente().getVia(), smallFont), 270, 642, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getCliente().getCap()+" "+testata.getCliente().getComune(), smallFont), 270,
				629, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getCliente().getProvincia(), smallFont), 270, 616, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("P.I.: "+testata.getCliente().getPartitaIva(), smallFont), 270, 603, 0);

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
	private void scriviDate(Documento_Testata testata, PdfContentByte cb, String numero, PdfLayer nested) {

		// /////////////////////// date///////////////////////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getMese_documento(), smallFont),
				488, 750, 0);

		cb.endLayer();
		cb.beginLayer(nested);
//		SimpleDateFormat sdf = new SimpleDateFormat(); // creo l'oggetto
//	    String dataStr = sdf.format(testata.getData_documento());
//	    sdf.applyPattern("yyyy, MM, dd");  
	   
	    
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getData_String_documento(), smallFont),
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
	private void scriviSomme(Integer importo, PdfContentByte cb, PdfLayer nested,Documento_Testata testata) {

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getImponibile().toString(), smallFont), 539, 150, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getIva().toString(), smallFont), 539, 126, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("", smallFont), 539, 94, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getTotale().toString(), smallFont), 539, 62, 0);

		cb.endLayer();
	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviNumeroPAgina(PdfContentByte cb, PdfLayer nested,int numPagina) {

		// ////////////////// numero pagina//////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("pagina n °"+numPagina, smallFont), 465, 694, 0);

		cb.endLayer();
	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviBonifico(PdfContentByte cb, PdfLayer nested,Documento_Testata testata) {

		// /////////////////////////////////////////////////////////////////////////////////////////////////////

		// //////////////// bonifico/////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("BONIFICO BANCARIO A :", piusmallFont), 27, 144,
				0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase(testata.getBanca().getNome(), piusmallFont), 27, 133, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("COORDINATE BANCARIE:", piusmallFont), 27, 119, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getBanca().getAgenzia(),
				piusmallFont), 27, 108, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getBanca().getVia()+" "+testata.getBanca().getCap()+" "+testata.getBanca().getComune(),
				piusmallFont), 27, 94, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("IBAN: "+testata.getBanca().getIban(),
				bigFont), 27, 69, 0);

		cb.endLayer();
	}

	/**
	 * @param numPagina
	 * @param row
	 * @param cb
	 * @param nested
	 * @return
	 */
	private void scriviRighe(int numPagina, List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// ////////////////////////////////////////////// ------
		// ////////////////////////////////////////////// righe--------////////////////////////////////////////

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
		
		_numeroRighe --;
		_indiceRiga ++;		
		
		if (hoFinito()) return;

		int a = 26;
		int b = 544;
		int c = 548;
		int d = 538;
		
		int dadovepartire = 0;
		int numeroRigheDaStampare;
		
		if (_numeroRighe > 14) {
			numeroRigheDaStampare = 15;
		} else {
			numeroRigheDaStampare = _numeroRighe;
		}

		
		for (int y = 1; y <= 14; y++) {
			
			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getNum_sedute().toString(),
					smallFont), 38, b - a, 0);

			cb.endLayer();

			// //////////////// mese///////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getMese(), smallFont), 70, b - a,
					0);

			cb.endLayer();

			// //////////////// km//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getKm_percorso().toString(),
					smallFont), 130, b - a, 0);

			// //////////////// NOME//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getPaziente().getCognome().toString(),
					piusmallFont), 158, b - a, 0);
			cb.beginLayer(nested);

			// //////////////// percorso andata//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getP_partenza() + "-"
					+ row.get(0).getP_arrivo(), piusmallFont), 260, c - a, 0);
			cb.beginLayer(nested);

			// //////////////// percorso ritorno//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getP_arrivo() + "-"
					+ row.get(0).getP_partenza(), piusmallFont), 260, d - a, 0);
			cb.beginLayer(nested);

			// //////////////// ore //////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getOra_sosta().toString(),
					smallFont), 394, b - a, 0);
			cb.beginLayer(nested);

			// //////////////// quota fissa//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("€      "+row.get(_indiceRiga).getQuotaFissa().toString(),
					smallFont), 420, b - a, 0);
			cb.beginLayer(nested);

			// //////////////// diritto di uscita//////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getDiritto_uscita(), smallFont),
					495, b - a, 0);
			cb.beginLayer(nested);

			// //////////////// importo/////////////////////

			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("€  " + row.get(_indiceRiga).getImporto(),
					smallFont), 525, b - a, 0);
			cb.beginLayer(nested);
			
           
			d = d - a;
			c = c - a;
			b = b - a;
			
			_indiceRiga ++;
			_numeroRighe --;
			
			if (hoFinito()) break;
		}

		
		//		_numeroRighe = _numeroRighe - 15;
		return;

	}

	private void cellaImporto(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////////// importo//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("€  " + row.get(_indiceRiga).getImporto(), smallFont),
				525, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaDirittoUscita(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////////// diritto di uscita//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getDiritto_uscita(), smallFont), 495,
				544, 0);
		cb.beginLayer(nested);
	}

	private void cellaQuotaFissa(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////////// quota fissa//////////////////
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("€      " + row.get(_indiceRiga).getQuotaFissa(), smallFont), 420,
				544, 0);
		cb.beginLayer(nested);
	}

	private void cellaOre(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////////// ore//////////////////
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getOra_sosta().toString(), smallFont),
				394, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaPercorsoRitorno(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////// percorso ritorno/////////////
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getP_arrivo() + "-"
				+ row.get(0).getP_partenza(), piusmallFont), 260, 538, 0);
		cb.beginLayer(nested);
	}

	private void cellaAndata(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////// percorso andata/////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getP_partenza() + "-"
				+ row.get(0).getP_arrivo(), piusmallFont), 260, 548, 0);
		cb.beginLayer(nested);
	}

	private void cellaNome(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////// nome//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getPaziente().getCognome(),
				piusmallFont), 158, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaKm(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////////// km//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase(row.get(_indiceRiga).getKm_percorso().toString(), smallFont), 130, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaMese(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// /////////// mese////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getMese(), smallFont), 70, 544, 0);
		cb.beginLayer(nested);
	}

	private void cellaSedute(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested) {

		// //////////////// n sedute//////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase(row.get(_indiceRiga).getNum_sedute().toString(), smallFont), 38, 544, 0);
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
				new Phrase("Pubblica Assistenza Croce Italia Bernate Ticino o.n.l.u.s.", smallFont), 27, 785, 0);
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
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("C.F. 93029640153", smallFont), 27, 734, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Tel.:02-49.79.05.67  Mobile: 345-59.16.824  Fax: 02-84.56.66.47", smallFont), 27, 722,
				0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("e-mail: croceitaliabernate@libero.it sito: www.croceitaliabernate.it",
						smallFont),
				27, 709, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Iscrizione al n. MI-499 albo regionale sez. Provinciale di Milano del Volontariato", smallFont), 27, 696, 0);

		cb.endLayer();
		
		
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("Autorizzazione Sanitaria n. 03/ST/08 del 03/09/2008 ", smallFont), 27, 684, 0);

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


		Integer importo = 0;
		Integer somma = 0;
		_nomiFile = new ArrayList();
		_numeroRighe = _righe.size();
		int numPagina = 0;
		while (nonHoFinito()) {

			this.manipulatePdf(++numPagina, _righe, _testata, importo, somma);
		}

		this.unisciPag();

	}

	/**
	 * @return
	 */
	private boolean nonHoFinito() {

		return !hoFinito();
	}

	public Documento_Testata get_testata() {
		return _testata;
	}

	public void set_testata(Documento_Testata _testata) {
		this._testata = _testata;
	}

	public List<Documento_Righe> get_righe() {
		return _righe;
	}

	public void set_righe(List<Documento_Righe> _righe) {
		this._righe = _righe;
	}
	
}