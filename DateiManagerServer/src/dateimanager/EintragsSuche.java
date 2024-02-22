package dateimanager;

import java.util.ArrayList;

public class EintragsSuche implements IDateiManagerSuche {
	
	// dies ist die rekursive liste
	private ArrayList<Eintrag> originalList;
	// hier sind alle einträge auch der Unterverzeichnisse in der flachen Liste
	private ArrayList<Eintrag> flacheList;
	public EintragsSuche(ArrayList<Eintrag> list) {
		this.originalList = list;
		this.flacheList = this.createFlacheList(this.originalList);
	}
	/**
	 * liefert die flache Liste zurück
	 * @return the flat list
	 */
	protected ArrayList<Eintrag> getSearchList() {
		return this.flacheList;
	}
	
	
	/**
	 * löst die Rekursion auf und macht eine flache Liste mit allen einträgen
	 * @param list die rekursive liste
	 * @return die flache liste
	 */
	public ArrayList<Eintrag> createFlacheList(ArrayList<Eintrag> list) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e:list) {
			results.add(e);
			if (e.isFolder()) {
				results.addAll(this.createFlacheList(e.getInhaltsList()));
			}
		}
		return results;
	}
	
	@Override
	public ArrayList<Eintrag> findeMitNamen(String name) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getName().equals(name)) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeNameEnthaeltText(String text) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getName().contains(text)) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeMitEndung(String endung) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getEndung().equals(endung)) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeMitEndungen(ArrayList<String> endungsList) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			for (String endung: endungsList) {
				if (e.getEndung().equals(endung)) {
					results.add(e);
				}
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeInZeitraum(long von, long bis) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getZeitStempel() >= von && e.getZeitStempel()<=bis) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeKleinerAls(int bytes) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getBytes()<bytes) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeGroesserAls(int bytes) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getBytes()>bytes) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeInVerzeichnis(String verzeichnis) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getOrt().equals(verzeichnis)) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeInVerzeichnisOderUnterverzeichnis(String verzeichnis) {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.getOrt().startsWith(verzeichnis)) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeAlleVErzeichnisse() {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.isFolder()) {
				results.add(e);
			}
		}
		return results;
	}

	@Override
	public ArrayList<Eintrag> findeAlleDateien() {
		ArrayList<Eintrag> results = new ArrayList<Eintrag>();
		for (Eintrag e: this.getSearchList()) {
			if (e.isFile()) {
				results.add(e);
			}
		}
		return results;	
	}
	
}
