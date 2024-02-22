package server;

import java.util.ArrayList;

import dateimanager.Eintrag;

public class SuchErgebnis {
	long dauer = 0;
	ArrayList<Eintrag> ergebnisse = null;
	int ergebnisAnzahl;
	int eintragAnzahl;
	String suche;
	public long getDauer() {
		return dauer;
	}
	public void setDauer(long dauer) {
		this.dauer = dauer;
	}
	public ArrayList<Eintrag> getErgebnisse() {
		return ergebnisse;
	}
	public void setErgebnisse(ArrayList<Eintrag> ergebnisse) {
		this.ergebnisse = ergebnisse;
	}
	public String getSuche() {
		return suche;
	}
	public void setSuche(String suche) {
		this.suche = suche;
	}
	

}
