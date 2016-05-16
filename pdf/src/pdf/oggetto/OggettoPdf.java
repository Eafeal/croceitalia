package pdf.oggetto;

import java.util.ArrayList;
import java.util.List;

public class OggettoPdf {

	public static List<Documento_Row> oggettoPdf() {

		List<Documento_Row> row = new ArrayList<>();

		row.add(new Documento_Row(1, 2, "frnco palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				45));
		row.add(new Documento_Row(1, 2, "pablo escobar", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 67));
		row.add(new Documento_Row(1, 2, "axel palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				43));
		row.add(new Documento_Row(1, 2, "frnco fdgdhnhd", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		row.add(new Documento_Row(1, 2, "frnco palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				10));
		row.add(new Documento_Row(1, 2, "pablo escobar", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 20));
		row.add(new Documento_Row(1, 2, "axel palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				90));
		row.add(new Documento_Row(1, 2, "frnco fdgdhnhd", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		row.add(new Documento_Row(1, 2, "frnco palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				0));
		row.add(new Documento_Row(1, 2, "pablo escobar", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		row.add(new Documento_Row(1, 2, "axel palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				80));
		row.add(new Documento_Row(1, 2, "frnco fdgdhnhd", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		row.add(new Documento_Row(1, 2, "frnco palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				90));
		row.add(new Documento_Row(1, 2, "pablo escobar", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		row.add(new Documento_Row(1, 2, "axel palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				60));
		row.add(new Documento_Row(1, 2, "frnco fdgdhnhd", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		row.add(new Documento_Row(1, 2, "pablo escobar", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		row.add(new Documento_Row(1, 2, "axel palmieri", 3, "aprile", 59, 23, "", "paderno", "niguarda", 0, 23, "S",
				60));
		row.add(new Documento_Row(1, 2, "frnco fdgdhnhd", 3, "maggio", 70, 45, "", "monza", "galeazzi", 0, 23, "N", 0));
		return row;
	}

	public static List<Documento_Testata> oggettoPdf2() {

		List<Documento_Testata> testata = new ArrayList<>();
		testata.add(new Documento_Testata(1, 2, 3, 4, 100, "2016", "agosto", "12/08/2020", 0, 0, "s", "s", 0, 0, "a",
				"n", "andrea"));
		// testata.add(new Documento_Testata(1, 2, 3, 4, 100, "2016", 5,
		// "12/08/2016", 0, 0, "s", "s", 0, 0, "a", "n", "andrea"));
		return testata;
	}

}
