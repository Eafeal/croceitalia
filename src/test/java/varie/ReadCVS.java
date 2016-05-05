package varie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadCVS {

	public static void main(String[] args) {

		ReadCVS obj = new ReadCVS();
		obj.run();

	}

	public void run() {

		String csvFile = "C:\\Users\\Davide\\Desktop\\TMP\\Flusso_730_GM_20151216.CSV";

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		int conta = 0;

		try {

			ArrayList myCsv = new ArrayList();

			Map<String, String[]> maps = new HashMap<String, String[]>();

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] colonnaCsv = line.split(cvsSplitBy);
				// maps.put(colonnaCsv[5], colonnaCsv[6]);
				// maps.put(String.valueOf(conta), colonnaCsv);
				myCsv.add(colonnaCsv);
				conta++;

			}

			// loop map
			// for (Map.Entry<String, String> entry : maps.entrySet()) {
			for (int i = 0; i < myCsv.size(); i++) {
				String[] colonnaCsv = (String[]) myCsv.get(i);
				System.out.println(i + ". colonnaCsv : " + colonnaCsv.toString());

			}
			System.out.println("righe lette " + conta);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

}
