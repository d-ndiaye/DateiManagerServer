package dateimanager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Eintrag {
	public static final int UNKNOWN=0;
	public static final int FOLDER=1;
	public static final int FILE=2;
	private String name;
	private long bytes;
	private String endung;
	private long zeitStempel;
	private int type;
	private String ort;
	
	// alle Einträge, die in dem Folder enthalten sind
	private ArrayList<Eintrag> inhaltsList;
	
	public Eintrag() {
		// ohne ein File, setzen wir ein paar "leere" Daten
		this.name="";
		this.bytes=-1;
		this.endung = "";
		this.zeitStempel = -1;
		this.setOrt("");
		this.type = Eintrag.UNKNOWN;
		this.inhaltsList = new ArrayList<Eintrag>();
	}
	
	/*
	 * Es ist auch möglich das Laden der Daten in Eintrag zu machen
	 * - das macht aber keinen großen Unterschied
	 * - wir nutzen diesen Konstruktor nicht
	 */
	public Eintrag(File f) throws IOException {
		this();
		this.name = f.getName();
		if (f.isDirectory()) {
			this.type = Eintrag.FOLDER;
			this.bytes = -1;
			
		}
		else {
			this.type = Eintrag.FILE;
			this.bytes = f.length();
		}
		this.zeitStempel = f.lastModified();
		this.setOrt(f.getParent());
	}
	
	
	
	/**
	 * 
	 * @param name
	 * @param bytes
	 * @param endung
	 * @param zeitStempel
	 * @param type
	 * @param ort
	 * @param inhaltsList
	 */
	public Eintrag(String name, long bytes, String endung, long zeitStempel, int type, String ort,
			ArrayList<Eintrag> inhaltsList) {
		this();
		this.name = name;
		this.bytes = bytes;
		this.endung = endung;
		this.zeitStempel = zeitStempel;
		this.type = type;
		this.ort = ort;
		this.inhaltsList = inhaltsList;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEndung() {
		return endung;
	}
	public void setEndung(String endung) {
		this.endung = endung;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isFile() {
		return this.getType() == Eintrag.FILE;
	}
	
	public boolean isFolder() {
		return this.getType() == Eintrag.FOLDER;
	}	

	public long getZeitStempel() {
		return zeitStempel;
	}

	public void setZeitStempel(long zeitStempel) {
		this.zeitStempel = zeitStempel;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * berechnet die Anzahl der übergeordneten Verzeichnisse
	 * @return den level des Folders, also wieviele Verzeichnisse es darüber gibt
	 */
	public int getLevel() {
		// berechnungs art 1 (kann manchmal falsch sein, windows ist \ und linux /  als verzeichnis trennzeichen)
		// es gibt leider keine methode in String, die die Anzahl von \ zählt
		int slashes = this.ort.length() - this.ort.replace("\\", "").length();
		
		// wir berechnen es über die Parent Folder
		int level = 0;
		// erstmal das file für ort holen
		File fileOrt = new File(this.ort);
		// dann holen wir uns das übergeordnete Verzeichnis
		File parent = fileOrt.getParentFile();
		// solange es übergeordnete Verzeichnis gibt, erhöhen wir level um 1 und dann holen wir das nächsthöhere verzeichnis
		// die Festplatte C ist null als parent
		while (parent != null) {
			level++;
			parent = parent.getParentFile();
		}
		return level;
	}
	
	/**
	 * virtueller getter für datum. Wir speichern nur einen Zeitstempel (long).
	 * in dieser Methode wird der Zeitstempel in ein Datumsformat mit Uhrzeit umgewandelt
	 * @return das lesbare datum
	 */
	public String getDatum() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String formatiertesDatum =  simpleDateFormat.format(this.getZeitStempel());
		return formatiertesDatum;
	}
	
	public long getBytes() {
		return this.bytes;
	}
	
	public void setBytes(long bytes) {
		this.bytes = bytes;
	}
	/**
	 * virtueller getter für megabytes
	 * @return megabytes
	 */
	public long getMegaBytes() {
		// 1024 bytes sind ein kilobyte
		// 1024 kilobytes sind ein megabyte
		return this.getBytes() / (1024 * 1024);
	}
	
	public String getInfo() {
		return this.getInfo(true);
	}
	
	public String getInfo(boolean mitUnterverzeichnissen) {
		String tabs = "";
		if (mitUnterverzeichnissen) {
			for (int i=0;i<this.getLevel();i++) {
				tabs +="\t";
			}
		}
		String info = "";
		info += tabs+"Name:\t\t"+this.getName()+"\n";
		info += tabs+"Ort:\t\t"+this.getOrt()+"\n";
		info += tabs+"Endung:\t\t"+this.getEndung()+"\n";
		info += tabs+"Type:\t\t"+this.getType()+"\n";
		info += tabs+"ZeitStempel:\t"+this.getZeitStempel()+"\n";
		info += tabs+"Datum:\t\t"+this.getDatum()+"\n";
		if (this.getType() == Eintrag.FILE) { 
			info += tabs+"Bytes:\t\t"+this.getBytes()+"\n";
			info += tabs+"MegaBytes:\t"+this.getMegaBytes()+"\n";
			info += tabs+"Endung:\t\t"+this.getEndung()+"\n";
		}
		info += tabs+"Level:\t\t"+this.getLevel()+"\n";
		
		if (this.isFolder() && mitUnterverzeichnissen) {
			info+=tabs+"\t*** Unterverzeichnis**";
			for (Eintrag eintragInUnterverzeichnis: this.getInhaltsList()) {
				info += eintragInUnterverzeichnis.getInfo(mitUnterverzeichnissen);
			}
		}
		info += "\n";
		return info;
	}

	/**
	 * die liste mit allen files oder foldern
	 * @return die liste mit einträgen
	 */
	public ArrayList<Eintrag> getInhaltsList() {
		return inhaltsList;
	}
	/**
	 * setzt eine liste von einträgen (nur relevant bei foldern)
	 * @param inhaltsList die liste von einträgen
	 */
	public void setInhaltsList(ArrayList<Eintrag> inhaltsList) {
		this.inhaltsList = inhaltsList;
	}

	

}
