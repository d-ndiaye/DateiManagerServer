package dateimanager;

import java.util.ArrayList;
/**
 * dieses interface definiert die suchmethoden f�r Daten aus dem Dateimanger
 * das interface kann f�r unterschiedliche Speicherarten (ohne, csv, ms access) angewendet werden
 * wenn hier von files gesprochen wird, dann werden immer Dateien und Folder gemeint
 * @author pault
 *
 */
public interface IDateiManagerSuche {
	/**
	 * findet alle Eintr�ge mit Namen (der Name muss genau mit dem Filenamen �bereinstimmen, z.b. Test.pptx)
	 * @param name der Name mit Endung
	 * @return die Liste aller Eintr�ge mit diesem Namen
	 */
	public ArrayList<Eintrag> findeMitNamen(String name);
	/**
	 * findet alle Eintr�ge, die den Text im Namen haben
	 * @param text der text, der einthalten sein soll
	 * @return die liste aller treffer
	 */
	public ArrayList<Eintrag> findeNameEnthaeltText(String text);
	/**
	 * alle Eintr�ge mit genau dieser Endung
	 * @param endung die Endung (ohne ., also "pptx")
	 * @return die Liste aller files mit dieser Endung
	 */
	public ArrayList<Eintrag> findeMitEndung(String endung);
	/**
	 * alle Eintr�ge mit einer der Endungen aus der Liste
	 * @param endungsList anendungen,  die Endung (ohne ., also "pptx")
	 * @return die Liste aller files mit einer der Endungen
	 */
	public ArrayList<Eintrag> findeMitEndungen(ArrayList<String> endungsList);
	/**
	 * findet alle Eintr�ge, die zwischen von und bis ge�ndert wurden
	 * @param von zeitstempel ab wann ge�ndert wurde
	 * @param bis zeitstempel bis wann ge�ndert wurde
	 * @return liste aller ge�nderten eintr�ge
	 */
	public ArrayList<Eintrag> findeInZeitraum(long von, long bis);
	/**
	 * findet alle Eintr�ge deren Filegr��e kleiner als die bytes ist
	 * @param bytes die Vergleichsanzahl in bytes
	 * @return alle files die weniger bytes gro� sind
	 */
	public ArrayList<Eintrag> findeKleinerAls(int bytes);
	/**
	 * findet alle Eintr�ge deren Filegr��e gr��er als die bytes ist
	 * @param bytes die Vergleichsanzahl in bytes
	 * @return alle files die mehr bytes gro� sind
	 */	
	public ArrayList<Eintrag> findeGroesserAls(int bytes);
	/**
	 * findet alle eintr�ge, die genau in dem Verzeichnis gespeichert sind. 
	 * Es muss der volle Pfad des Verzeichnis angegeben werden (zB. C:\test\daten )
	 * @param verzeichnis das verzeichnis nach dem gesucht werden soll
	 * @return alle eintr�ge, die genau in diesem Verzeichnis sind
	 */
	public ArrayList<Eintrag> findeInVerzeichnis(String verzeichnis);
	/**
	 * findet alle eintr�ge, die genau in dem Verzeichnis oder Unterverzeichnis gespeichert sind. 
	 * Es muss der volle Pfad des Verzeichnis angegeben werden (zB. C:\test\daten )
	 * @param verzeichnis das verzeichnis nach dem gesucht werden soll
	 * @return alle eintr�ge, die in diesem Verzeichnis oder einem Unterverzeichnis sind
	 */	
	public ArrayList<Eintrag> findeInVerzeichnisOderUnterverzeichnis(String verzeichnis);
	
	/**
	 * findet alle eintr�ge, die ein Verzeichnis sind 
	 * @return alle verzeichnisse
	 */	
	public ArrayList<Eintrag> findeAlleVErzeichnisse();
	
	
	/**
	 * findet alle eintr�ge, die eine Datei sind 
	 * @return alle dateien
	 */	
	public ArrayList<Eintrag> findeAlleDateien();	
	
}
