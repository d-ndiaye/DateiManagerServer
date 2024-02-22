package dateimanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * DateiManager ist die Hauptklasse zur Verwaltung von Files und Verzeichnissen
 * 
 * @author pault
 *
 */
public class DateiManager {
	private String delimiter;
	private String lineBreak;

	public DateiManager() {
		// Trennzeichen pro Zelle
		this.delimiter = ";";
		// Zeilenumbruch (neue Zeile = ENTER)
		this.lineBreak = "\n";
	}

	/**
	 * diese methode liest ein Verzeichnis aus und erzeugt für jeden enthaltenen
	 * folder oder jedes enthaltene file ein Objekt der Klasse Eintrag. Alle
	 * Eintrage werden als ArrayList zurückgeben Die Liste ist leer, falls der
	 * folder für path nicht existiert oder leer ist
	 * 
	 * @param path der pfad zu dem folder, der ausgelesen wird
	 * @return die liste aller gefundenen Einträge
	 * @throws IOException tritt auf, falls files oder folder nicht lesbar sind
	 */
	public ArrayList<Eintrag> readVerzeichnis(String path) throws IOException {
		// erzeuge eine leere liste für die Ergebnis
		ArrayList<Eintrag> eintragListe = new ArrayList<Eintrag>();
		// wandle den Pfad in ein File Objekt um
		File verzeichnis = new File(path);
		// prüfe, ob das Verzeichnis existiert und wirklich ein Verzeichnis ist
		if (verzeichnis.exists() && verzeichnis.isDirectory()) {
			// lade alle files im verzeichnis
			File[] fileList = verzeichnis.listFiles();
			// iteriere über alle gefundenen files
			for (File file : fileList) {
				// erzeuge einen Eintrag für das file
				Eintrag e = this.erzeugeEintragVonFile(file);
				// füge den eintrag der liste mit den Ergebnissen hinzu
				eintragListe.add(e);
			}
		}
		// gib die gefunden einträge an den Aufrufer zurück
		return eintragListe;
	}

	/**
	 * sortiert die sourceList nach Namen und gibt das Ergebnis als neue sortierte Liste zurück
	 * @param sourceList liste von Einträgen
	 * @return sortierte liste der Einträge
	 */
	public ArrayList<Eintrag> sortListByName(ArrayList<Eintrag> sourceList) {
		// kopieren der Originalliste, wir möchten die Originalliste nicht verändern
		// wir sortieren list
		ArrayList<Eintrag> list = new ArrayList<Eintrag>(sourceList);
		// temp ist eintrag
		Eintrag temp = null;
		// iteriere über die liste
		for (int j = 0; j < list.size(); j++) {
			// iteriere über die werte größer als j
			for (int i = j + 1; i < list.size(); i++) {
				// vergeliche die benachbarten einträge
				if (list.get(i).getName().compareToIgnoreCase(list.get(j).getName()) < 0) {
					// vertausche die Einträge
					// speichere eintrag j in einer temp variablen
					temp = list.get(j);
					// setze an Stelle von j den Eintrag i
					list.set(j, list.get(i));
					// setze an Stelle i den Eintag temp (früher Eintrag j)
					list.set(i, temp);
				}
			}
		}
		// gebe die neue Liste als ergebnis zurück
		return list;
	}
	
	
	public ArrayList<Eintrag> sortListByNameWithQuickSort(ArrayList<Eintrag> sourceList) {
		// kopieren der Originalliste, wir möchten die Originalliste nicht verändern
		ArrayList<Eintrag> list = new ArrayList<Eintrag>(sourceList);
		// ein neues object der klasse EintragNameQuickSort
		EintragNameQuickSort quickSorter = new EintragNameQuickSort();
		// jetzt lassen wir sortieren
		list = quickSorter.sort(list);
		return list;		
	}
	
	/**
	 * sortiert die sourceList nach Namen und gibt das Ergebnis als neue sortierte Liste zurück
	 * @param sourceList liste von Einträgen
	 * @return sortierte liste der Einträge
	 */
	public ArrayList<Eintrag> sortListByBytes(ArrayList<Eintrag> sourceList) {
		// kopieren der Originalliste, wir möchten die Originalliste nicht verändern
		// wir sortieren list
		ArrayList<Eintrag> list = new ArrayList<Eintrag>(sourceList);
		// temp ist eintrag
		Eintrag temp = null;
		// iteriere über die liste
		for (int j = 0; j < list.size(); j++) {
			// iteriere über die werte größer als j
			for (int i = j + 1; i < list.size(); i++) {
				// vergeliche die benachbarten einträge
				if (list.get(i).getBytes() < list.get(j).getBytes()) {
					// vertausche die Einträge
					temp = list.get(j);
					list.set(j, list.get(i));
					list.set(i, temp);
				}
			}
		}
		return list;
	}	

	/**
	 * diese methode liest ein Verzeichnis aus und erzeugt für jeden enthaltenen
	 * folder oder jedes enthaltene file ein Objekt der Klasse Eintrag. Alle
	 * Eintrage werden als ArrayList zurückgeben Die Liste ist leer, falls der
	 * folder für path nicht existiert oder leer ist
	 * 
	 * @param path der pfad zu dem folder, der ausgelesen wird
	 * @return die liste aller gefundenen Einträge
	 * @throws IOException tritt auf, falls files oder folder nicht lesbar sind
	 */
	public ArrayList<Eintrag> readVerzeichnisMitUnterverzeichnissen(String path) throws IOException {
		// erzeuge eine leere liste für die Ergebnis
		ArrayList<Eintrag> eintragListe = new ArrayList<Eintrag>();
		// wandle den Pfad in ein File Objekt um
		File verzeichnis = new File(path);
		// prüfe, ob das Verzeichnis existiert und wirklich ein Verzeichnis ist
		if (verzeichnis.exists() && verzeichnis.isDirectory()) {
			// lade alle files im verzeichnis
			File[] fileList = verzeichnis.listFiles();
			// iteriere über alle gefundenen files
			for (File file : fileList) {
				// erzeuge einen Eintrag für das file
				Eintrag e = this.erzeugeEintragVonFile(file);
				if (file.isDirectory()) {
					// lies alle DAteien (Einträge) im Unterverzeichnis
					// dies ist rekursive Programmierung, die alle Unterverezeichnisse ausliest
					ArrayList<Eintrag> eintraegeInUnterVerzeichnisList = readVerzeichnisMitUnterverzeichnissen(
							file.getPath());
					e.setInhaltsList(eintraegeInUnterVerzeichnisList);
				}
				// füge den eintrag der liste mit den Ergbnissen hinzu
				eintragListe.add(e);
			}
		}
		// gib die gefunden einträge an den Aufrufer zurück
		return eintragListe;
	}

	/**
	 * diese methode liest alle Daten von einem File (datei oder folder) und erzeugt
	 * einen Eintrag diese Methode ist private, da sie nur in dieser klasse in
	 * readverzeichnis benötigt wird
	 * 
	 * @param f das source file
	 * @return der eintrag
	 */
	private Eintrag erzeugeEintragVonFile(File f) {
		// erzeuge einen Eintrag
		Eintrag eintrag = new Eintrag();
		// setze den Namen
		eintrag.setName(f.getName());
		// behandle Folder anders als Files
		if (f.isDirectory()) {
			eintrag.setType(Eintrag.FOLDER);
			eintrag.setBytes(-1);
			eintrag.setEndung("");
		} else {
			eintrag.setType(Eintrag.FILE);
			eintrag.setBytes(f.length());
			// den Filenamen haben wir schon bei Eintrag gesetzt (zum Beispiel test.jpg)
			// jetzt setzen wir die Endung
			eintrag.setEndung(this.findeEndung(eintrag.getName()));
		}
		// setze den Zeitstempel
		eintrag.setZeitStempel(f.lastModified());
		// setze den Ort
		eintrag.setOrt(f.getParent());
		// gib den Eintrag zurück
		return eintrag;
	}

	/**
	 * diese methode findet die endung eines files diese Methode ist private, da sie
	 * nur in dieser klasse in readverzeichnis benötigt wird
	 * 
	 * @param fileName der filename z. B. test.pptx
	 * @return die endung z. B. pptx
	 */
	private String findeEndung(String fileName) {
		String endung = "";
		// finde die Position des letzten Punkts .
		int positionVonPunkt = fileName.lastIndexOf(".");
		// im Namen ist ein Punkt enthalten
		if (positionVonPunkt > -1) {
			endung = fileName.substring(positionVonPunkt + 1);
		}
		return endung;
	}

	/**
	 * schreibt alle einträge aus der liste in eine csv datei unter filename
	 * 
	 * @param fileName     der pfad zu der csv datei
	 * @param eintragListe die liste mit allen einträgen
	 * @throws IOException die exception, falls die csv datei nicht geschrieben
	 *                     werden kann
	 */
	public void writeToCsv(String fileName, ArrayList<Eintrag> eintragListe) throws IOException {
		try {
			File f = new File(fileName);
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			out.write(this.createHeaderAsCsvString());
			for (Eintrag eintrag : eintragListe) {
				out.write(this.createCsvStringForEintrag(eintrag));
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String createHeaderAsCsvString() {
		String header = "";
		header += "Name" + this.delimiter;
		header += "Size" + this.delimiter;
		header += "Endung" + this.delimiter;
		header += "Datum" + this.delimiter;
		header += "Type" + this.delimiter;
		header += "Verzeichnis" + this.delimiter;
		header += "Level" + this.lineBreak;
		return header;
	}

	private String createCsvStringForEintrag(Eintrag e) {
		String line = "";
		line += e.getName() + this.delimiter;
		line += e.getBytes() + this.delimiter;
		line += e.getEndung() + this.delimiter;
		line += e.getZeitStempel() + this.delimiter;
		line += e.getType() + this.delimiter;
		line += e.getOrt() + this.delimiter;
		line += e.getLevel() + this.lineBreak;
		return line;
	}
}
