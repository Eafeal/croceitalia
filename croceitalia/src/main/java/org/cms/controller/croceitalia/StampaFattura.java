package org.cms.controller.croceitalia;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;

import it.asso.pdf.PDFAssoView;

/**
 * @author axela
 *
 */
public class StampaFattura extends PDFAssoView implements PdfPTableEvent {

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.pdf.PDFAssoView#buildPdfDocument(java.util.Map,
	 * com.itextpdf.text.Document, com.itextpdf.text.pdf.PdfWriter,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Documento_Righe> righe = (LinkedList<Documento_Righe>) model.get("righe");
		List<Documento_Testata> testate = (LinkedList<Documento_Testata>) model.get("testata");
		String fileName = "nomedelfiles";

		response.setHeader("Cache-Control", "max-age=-1");
		response.addHeader("Content-Disposition",
				"attachment;filename=" + java.net.URLEncoder.encode(fileName + ".pdf", "UTF-8"));

		// Document document = new Document(PageSize.A4.rotate());
		document.setPageSize(PageSize.A4.rotate());

		document.addHeader("creator", "Powered by Assocons srl - www.assocons.it");

		document.open();

		PdfPTable table = makeTable(righe);
		table.setTableEvent(this);
		document.add(table);
		document.newPage();

		document.close();
		document.newPage();
	}

	/**
	 * @param document
	 * @param classifica
	 * @return
	 */
	private PdfPTable makeTable(List<Documento_Righe> righe) {

		float[] relativeWidths = new float[] { 5, 20, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 };
		PdfPTable table = new PdfPTable(relativeWidths);
		table.setWidthPercentage(100f);
		table.getDefaultCell().setPadding(3);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);
		table.getDefaultCell().setColspan(12);
		table.getDefaultCell().setBackgroundColor(new BaseColor(0, 190, 0));
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Font whiteFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.WHITE);
		table.addCell(new Phrase("titolo", whiteFont));
		table.getDefaultCell().setColspan(1);
		for (int i = 0; i < 2; i++) {
			table.addCell(new Phrase("col1.", whiteFont));
			table.addCell(new Phrase("col2", whiteFont));
		}

		table.getDefaultCell().setBackgroundColor(null);
		table.setHeaderRows(3);
		table.setFooterRows(1);

		int pos = 0;
		Iterator<Documento_Righe> iterator = righe.iterator();
		while (iterator.hasNext()) {
			Documento_Righe riga = iterator.next();

			table.addCell(String.valueOf(++pos));
			Integer num_sedute = riga.getNum_sedute();
			table.addCell(String.valueOf(num_sedute));
			table.addCell(riga.getMese());
			table.addCell(riga.getPaziente().getCognome() + " " + riga.getPaziente().getNome());
			table.addCell(riga.getPaziente().getComune() + " " + riga.getStruttura().getComune());
			table.addCell(String.valueOf(riga.getOra_sosta()));
			table.addCell(String.valueOf(riga.getQuota_fissa()));
			table.addCell(riga.getDiritto_uscita());
			table.addCell(String.valueOf(riga.getImporto()));
		}
		return table;
	}

	@Override
	public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart,
			PdfContentByte[] canvases) {

		int footer = widths.length - table.getFooterRows();
		int header = table.getHeaderRows() - table.getFooterRows() + 1;

		for (int row = header; row < footer; row += 2) {
			int columns = widths[row].length - 1;
			Rectangle rect = new Rectangle(widths[row][0], heights[row], widths[row][columns], heights[row + 1]);
			// rect.setBackgroundColor(new BaseColor(220, 238, 204));
			rect.setBackgroundColor(new BaseColor(237, 246, 229));
			rect.setBorder(Rectangle.NO_BORDER);
			canvases[PdfPTable.BASECANVAS].rectangle(rect);

		}
	}

}
