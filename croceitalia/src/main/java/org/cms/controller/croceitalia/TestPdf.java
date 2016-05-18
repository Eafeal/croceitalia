package org.cms.controller.croceitalia;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

public class TestPdf {

	public static void main(String[] args) {

		System.out.println("=======================START===============");

		String src = "C:/Users/andre/OneDrive/Documents/TemplateFattura.pdf";
		String destIn = "C:/Users/andre/OneDrive/Documents/generapdf/documento";
		String destOut = "C:/Users/andre/OneDrive/Documents/generapdf/compl/croceitaliafattura.pdf";

		GeneraPdf2 generaPdf2 = new GeneraPdf2(src, destIn, destOut);
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
