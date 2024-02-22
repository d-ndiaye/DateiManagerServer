package dateimanager;

import java.util.ArrayList;

/**
 * diese klasse testet DateiManager
 * @author pault
 *
 */
public class DateiManagerTest {

	
	public static void main(String[] args) {
		
		try {
			String startFolder = "C:\\dateimanager\\beispiel";
			//String startFolder = "C:\\dateimanager\\statictest\\statictest";
			String csvFileName = "C:\\dateimanager\\ergebnisse\\dateimanager_ergebnisse.csv";
			
			// erzeuge ein Objekt vom Typ DateiManager
			DateiManager dateiManager = new DateiManager();
			// 
			ArrayList<Eintrag> eintragsListe = dateiManager.readVerzeichnisMitUnterverzeichnissen(startFolder);
			System.out.println(eintragsListe.size());
			/*
			ArrayList<Eintrag> sortierteListe = dateiManager.sortListByName(eintragsListe);
			System.out.println(sortierteListe.size());
			*/
			ArrayList<Eintrag> sortierteListe = dateiManager.sortListByNameWithQuickSort(eintragsListe);
			System.out.println(sortierteListe.size());
			/*
			for (Eintrag e:sortierteListe) {
				System.out.println(e.getName());
			}
			*/
			EintragsSuche suche = new EintragsSuche(sortierteListe);
			ArrayList<Eintrag> ergebnisse = suche.findeNameEnthaeltText("a");
			for (int i=0;i<ergebnisse.size();i++) {
				System.out.println(ergebnisse.get(i).getName());
				System.out.println("");
				System.out.println("-----------------------------------------");
			}
			/*
			// Anzahl der gefundenen Einträge noch ausgeben
			System.out.println("");
			System.out.println("");
			System.out.println(eintragsListe.size()+" Einträge gefunden");
			System.out.println("");
			dateiManager.writeToCsv(csvFileName, eintragsListe);
			System.out.println("Die Einträge sind unter "+csvFileName+" erfolgreich gespeichert worden.");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");			
			EintragsSuche dms = new EintragsSuche(eintragsListe);
			ArrayList<Eintrag> flach = dms.createFlacheList(eintragsListe);
			System.out.println("Flach: "+flach.size());
			System.out.println("Name: "+dms.findeNameEnthaeltText("cal_").size());
			System.out.println("Zip-Files: "+dms.findeMitEndung("zip").size());
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
