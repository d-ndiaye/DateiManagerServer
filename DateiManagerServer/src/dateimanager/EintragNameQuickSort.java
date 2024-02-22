package dateimanager;

import java.util.ArrayList;

public class EintragNameQuickSort {
	ArrayList<Eintrag> list;
	int length;
	
	// hier wird sortieren aufgerufen
    public ArrayList<Eintrag> sort(ArrayList<Eintrag> unsortedList) {
        if (unsortedList == null || unsortedList.size() == 0) {
            return unsortedList;
        }
        // wir kopieren die original arrayliste
        this.list = unsortedList;
        this.length = list.size();
        // sortiere die gesamte liste
        quickSort(0, length - 1);
        // die sortierte liste wird zurückgegeben
        return this.list;
    }

    
    void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        // wir suchen das Element in der mitte zwischen low und high index
        Eintrag pivot = this.list.get(lowerIndex + (higherIndex - lowerIndex) / 2);
        // solange i noch kein treffer (i < j)
        while (i <= j) {
        	// wir gehen eins nach rechts
            while (this.compareEintrag(this.list.get(i),pivot) < 0) {
                i++;
            }
            // wir gehen eins nach links
            while (this.compareEintrag(this.list.get(j),pivot)  > 0) {
                j--;
            }
            // wir vertauchen die elemente
            if (i <= j) {
                this.exchangeNames(i, j);
                i++;
                j--;
            }
        }
        //call quickSort recursively
        if (lowerIndex < j) {
            quickSort(lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(i, higherIndex);
        }
    }
    
    void exchangeNames(int i, int j) {
    	// wir tauschen die Einträge an den Stellen i und j
        Eintrag temp = this.list.get(i);
        this.list.set(i, this.list.get(j));
        this.list.set(j, temp);
    }
    
    // vergleichs methode
    public int compareEintrag(Eintrag a, Eintrag b) {
    	// diese Methode muss angepasst werden, wenn man anders sortieren möchte, zum Beispiel nach Size
    	return a.getName().compareToIgnoreCase(b.getName());
    }


}
