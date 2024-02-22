package dateimanager;

import java.util.ArrayList;
/**
 * dieses interface definiert die suchmethoden für Daten aus dem Dateimanger
 * das interface kann für unterschiedliche Speicherarten (ohne, csv, ms access) angewendet werden
 * wenn hier von files gesprochen wird, dann werden immer Dateien und Folder gemeint
 * @author pault
 *
 */
public interface IDateiManagerSuche {
	/**
	 * findet alle Einträge mit Namen (der Name muss genau mit dem Filenamen übereinstimmen, z.b. Test.pptx)
	 * @param name der Name mit Endung
	 * @return die Liste aller Einträge mit diesem Namen
	 */
	public ArrayList<Eintrag> findeMitNamen(String name);
	/**
	 * findet alle Einträge, die den Text im Namen haben
	 * @param text der text, der einthalten sein soll
	 * @return die liste aller treffer
	 */
	public ArrayList<Eintrag> findeNameEnthaeltText(String text);
	/**
	 * alle Einträge mit genau dieser Endung
	 * @param endung die Endung (ohne ., also "pptx")
	 * @return die Liste aller files mit dieser Endung
	 */
	public ArrayList<Eintrag> findeMitEndung(String endung);
	/**
	 * alle Einträge mit einer der Endungen aus der Liste
	 * @param endungsList anendungen,  die Endung (ohne ., also "pptx")
	 * @return die Liste aller files mit einer der Endungen
	 */
	public ArrayList<Eintrag> findeMitEndungen(ArrayList<String> endungsList);
	/**
	 * findet alle Einträge, die zwischen von und bis geändert wurden
	 * @param von zeitstempel ab wann geändert wurde
	 * @param bis zeitstempel bis wann geändert wurde
	 * @return liste aller geänderten einträge
	 */
	public ArrayList<Eintrag> findeInZeitraum(long von, long bis);
	/**
	 * findet alle Einträge deren Filegröße kleiner als die bytes ist
	 * @param bytes die Vergleichsanzahl in bytes
	 * @return alle files die weniger bytes groß sind
	 */
	public ArrayList<Eintrag> findeKleinerAls(int bytes);
	/**
	 * findet alle Einträge deren Filegröße größer als die bytes ist
	 * @param bytes die Vergleichsanzahl in bytes
	 * @return alle files die mehr bytes groß sind
	 */	
	public ArrayList<Eintrag> findeGroesserAls(int bytes);
	/**
	 * findet alle einträge, die genau in dem Verzeichnis gespeichert sind. 
	 * Es muss der volle Pfad des Verzeichnis angegeben werden (zB. C:\test\daten )
	 * @param verzeichnis das verzeichnis nach dem gesucht werden soll
	 * @return alle einträge, die genau in diesem Verzeichnis sind
	 */
	public ArrayList<Eintrag> findeInVerzeichnis(String verzeichnis);
	/**
	 * findet alle einträge, die genau in dem Verzeichnis oder Unterverzeichnis gespeichert sind. 
	 * Es muss der volle Pfad des Verzeichnis angegeben werden (zB. C:\test\daten )
	 * @param verzeichnis das verzeichnis nach dem gesucht werden soll
	 * @return alle einträge, die in diesem Verzeichnis oder einem Unterverzeichnis sind
	 */	
	public ArrayList<Eintrag> findeInVerzeichnisOderUnterverzeichnis(String verzeichnis);
	
	/**
	 * findet alle einträge, die ein Verzeichnis sind 
	 * @return alle verzeichnisse
	 */	
	public ArrayList<Eintrag> findeAlleVErzeichnisse();
	
	
	/**
	 * findet alle einträge, die eine Datei sind 
	 * @return alle dateien
	 */	
	public ArrayList<Eintrag> findeAlleDateien();	
	
}
