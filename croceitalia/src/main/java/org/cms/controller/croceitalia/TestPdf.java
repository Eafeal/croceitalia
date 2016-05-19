package org.cms.controller.croceitalia;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class TestPdf {

	public static void main(String[] args) {

		System.out.println("=======================START===============");

		String src = "D:/workspace/upload/croceitalia/TemplateFattura.pdf";
		String destIn = "D:/workspace/upload/croceitalia/generapdf/documento";
		String destOut = "D:/workspace/upload/croceitalia/generapdf/compl/croceitaliafattura.pdf";

		GeneraPdf2 generaPdf2 = new GeneraPdf2(src, destIn, destOut);
		//generaPdf2.set_testata(_testata);
		
		try {
			generaPdf2.stampa();

			System.out.println("=======================TUTTO OK===============");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("=======================ERRORE===============");

		} catch (DocumentException e) {
			e.printStackTrace();

			System.out.println("=======================ERRORE===============");

		}
	}

}
