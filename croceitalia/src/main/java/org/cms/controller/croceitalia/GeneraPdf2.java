package org.cms.controller.croceitalia;

import it.asso.util.ApplConfig;

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

	private static Font				bigFont			= new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
	private static Font				smallFont		= new Font(Font.FontFamily.UNDEFINED, 9, Font.BOLD);
	private static Font				piusmallFont	= new Font(Font.FontFamily.UNDEFINED, 8, Font.BOLD);
	List<Integer>					finale			= new ArrayList();

	private List<String>			_nomiFile;

	private final String			_rootDocumenti	= ApplConfig.GetParameter("RepositoryDocumenti");
	private String					_destIn			= ApplConfig.GetParameter("RepositoryDocumentiTmp");
	private String					_destOut		= ApplConfig.GetParameter("RepositoryDocumentiGenerati");
	private String					_src			= ApplConfig.GetParameter("TemplateDocumento");

	private final String			_intestazione1	= ApplConfig.GetParameter("IntestazioneDocumento1");		;
	private final String			_intestazione2	= ApplConfig.GetParameter("IntestazioneDocumento2");		;
	private final String			_intestazione3	= ApplConfig.GetParameter("IntestazioneDocumento3");		;
	private final String			_intestazione4	= ApplConfig.GetParameter("IntestazioneDocumento4");		;
	private final String			_intestazione5	= ApplConfig.GetParameter("IntestazioneDocumento5");		;
	private final String			_intestazione6	= ApplConfig.GetParameter("IntestazioneDocumento6");		;
	private final String			_intestazione7	= ApplConfig.GetParameter("IntestazioneDocumento7");		;
	private final String			_intestazione8	= ApplConfig.GetParameter("IntestazioneDocumento8");		;
	private final String			_intestazione9	= ApplConfig.GetParameter("IntestazioneDocumento9");		;

	private int						_numeroPagine	= 0;
	private int						_numeroRighe	= 0;
	private int						_indiceRiga		= 0;

	int								passo			= 26;
	int								b				= 544;
	int								c				= 548;
	int								d				= 538;

	private Documento_Testata		_testata;
	private List<Documento_Righe>	_righe			= new ArrayList();

	/**
	 * @param src
	 * @param destIn
	 * @param destOut
	 */

	public GeneraPdf2(String nomeFile, Documento_Testata testata, List<Documento_Righe> righe) {

		super();

		_src = _src;
		_destOut = _destOut + nomeFile;
		_destIn = _destIn;
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
	public void manipulatePdf(int numPagina, List<Documento_Righe> row, Documento_Testata testata, Integer importo,
			Integer somma) throws IOException, DocumentException {

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

		scriviBonifico(cb, nested, testata);

		scriviNumeroPAgina(cb, nested, numPagina);

		if (hoFinito()) {
			scriviSomme(importo, cb, nested, testata);
		}

		scriviDate(testata, cb, numero, nested);

		scriviCliente(cb, nested, testata);

		stamper.close();
		reader.close();

	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviCliente(PdfContentByte cb, PdfLayer nested, Documento_Testata testata) {

		// ////////////////////////////////////////////////////
		// cliente/////////////////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Spettabile", smallFont), 270, 668, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getCliente().getRagione_sociale(),
				smallFont), 270, 655, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("Viale/Via " + testata.getCliente().getVia(),
				smallFont), 270, 642, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getCliente().getCap() + " "
				+ testata.getCliente().getComune(), smallFont), 270, 629, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getCliente().getProvincia(), smallFont),
				270, 616, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("P.I.: " + testata.getCliente().getPartitaIva(),
				smallFont), 270, 603, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("CIG: " + testata.getCIG(), smallFont), 270, 590,
				0);

		cb.endLayer();
	}

	/**
	 * @param testata
	 * @param cb
	 * @param numero
	 * @param nested
	 */
	private void scriviDate(Documento_Testata testata, PdfContentByte cb, String numero, PdfLayer nested) {

		// ///////////////////////
		// date///////////////////////////////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getMese_documento(), smallFont), 488,
				750, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		// SimpleDateFormat sdf = new SimpleDateFormat(); // creo l'oggetto
		// String dataStr = sdf.format(testata.getData_documento());
		// sdf.applyPattern("yyyy, MM, dd");

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
	private void scriviSomme(Integer importo, PdfContentByte cb, PdfLayer nested, Documento_Testata testata) {

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("€  " + testata.getImponibile().toString(),
				smallFont), 572, 150, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("€  " + testata.getIva().toString(), smallFont),
				572, 126, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("", smallFont), 572, 94, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("€  " + testata.getTotale().toString(),
				smallFont), 572, 62, 0);

		cb.endLayer();
	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviNumeroPAgina(PdfContentByte cb, PdfLayer nested, int numPagina) {

		// ////////////////// numero pagina//////////////////////////////

		if (_numeroPagine > 1) {
			cb.beginLayer(nested);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("pagina " + numPagina + " di "
					+ _numeroPagine, smallFont), 465, 694, 0);

			cb.endLayer();
		}
	}

	/**
	 * @param cb
	 * @param nested
	 */
	private void scriviBonifico(PdfContentByte cb, PdfLayer nested, Documento_Testata testata) {

		// /////////////////////////////////////////////////////////////////////////////////////////////////////

		// //////////////// bonifico/////////////////////

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("BONIFICO BANCARIO A :", piusmallFont), 27, 144,
				0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("P.A. CROCE ITALIANA BERNATE TICINO",
				piusmallFont), 27, 133, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText
				.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase("COORDINATE BANCARIE:", piusmallFont), 27, 119, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getBanca().getNome() + " "
				+ testata.getBanca().getAgenzia(), piusmallFont), 27, 108, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(testata.getBanca().getVia() + " "
				+ testata.getBanca().getCap() + " " + testata.getBanca().getComune(), piusmallFont), 27, 94, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
				new Phrase("IBAN: " + testata.getBanca().getIban(), bigFont), 27, 69, 0);

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
		// //////////////////////////////////////////////
		// righe--------////////////////////////////////////////

		cellaSedute(row, cb, nested, 0);
		cellaMese(row, cb, nested, 0);
		cellaKm(row, cb, nested, 0);
		cellaNome(row, cb, nested, 0);
		cellaAndata(row, cb, nested, 0);
		cellaPercorsoRitorno(row, cb, nested, 0);
		cellaOre(row, cb, nested, 0);
		cellaQuotaFissa(row, cb, nested, 0);
		cellaDirittoUscita(row, cb, nested, 0);
		cellaImporto(row, cb, nested, 0);

		_numeroRighe--;
		_indiceRiga++;

		if (hoFinito())
			return;

		int dadovepartire = 0;
		int numeroRigheDaStampare;

		if (_numeroRighe > 14) {
			numeroRigheDaStampare = 15;
		} else {
			numeroRigheDaStampare = _numeroRighe;
		}

		for (int y = 1; y <= 14; y++) {

			cellaSedute(row, cb, nested, y);
			cellaMese(row, cb, nested, y);
			cellaKm(row, cb, nested, y);
			cellaNome(row, cb, nested, y);
			cellaAndata(row, cb, nested, y);
			cellaPercorsoRitorno(row, cb, nested, y);
			cellaOre(row, cb, nested, y);
			cellaQuotaFissa(row, cb, nested, y);
			cellaDirittoUscita(row, cb, nested, y);
			cellaImporto(row, cb, nested, y);

			_indiceRiga++;
			_numeroRighe--;

			if (hoFinito())
				break;
		}

		// _numeroRighe = _numeroRighe - 15;
		return;

	}

	private void cellaImporto(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////////// importo//////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("€  " + row.get(_indiceRiga).getImporto(),
				smallFont), 572, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaDirittoUscita(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////////// diritto di uscita//////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase(row.get(_indiceRiga).getDiritto_uscita(),
				smallFont), 513, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaQuotaFissa(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////////// quota fissa//////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("€ " + row.get(_indiceRiga).getQuotaFissa(),
				smallFont), 477, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaOre(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////////// ore//////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getOra_sosta().toString(),
				smallFont), 394, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaPercorsoRitorno(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////// percorso ritorno/////////////
		int pos;
		pos = d - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getPercorsoRitorno(),
				piusmallFont), 260, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaAndata(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////// percorso andata/////////////
		int pos;
		pos = c - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getPercorsoAndata(),
				piusmallFont), 260, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaNome(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////// nome//////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getPaziente()
				.getNominativo(), piusmallFont), 158, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaKm(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////////// km//////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getKm_percorso().toString(),
				smallFont), 130, pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaMese(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// /////////// mese////////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getMese(), smallFont), 70,
				pos, 0);
		cb.beginLayer(nested);
	}

	private void cellaSedute(List<Documento_Righe> row, PdfContentByte cb, PdfLayer nested, int riga) {

		// //////////////// n sedute//////////////////
		int pos;
		pos = b - (passo * riga);

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(row.get(_indiceRiga).getNum_sedute().toString(),
				smallFont), 38, pos, 0);
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
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione1, smallFont), 27, 785, 0);
		// 1 spazio da sinistra 2spazio partendo dal basso 3inclinazione
		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione2, smallFont), 27, 772, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione3, smallFont), 27, 759, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione4, smallFont), 27, 746, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione5, smallFont), 27, 734, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione6, smallFont), 27, 722, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione7, smallFont), 27, 709, 0);

		cb.endLayer();
		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione8, smallFont), 27, 696, 0);

		cb.endLayer();

		cb.beginLayer(nested);
		ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, new Phrase(_intestazione9, smallFont), 27, 684, 0);

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
		_numeroPagine = _numeroRighe / 15;
		if ((_numeroPagine * 15) != _numeroRighe)
			_numeroPagine++;

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