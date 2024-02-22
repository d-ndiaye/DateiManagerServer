package server;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dateimanager.DateiManager;
import dateimanager.Eintrag;
import dateimanager.EintragsSuche;

/**
 * Servlet implementation class DateiSucheServlet
 * hier sind die pfade definiert, wie man das servlet starten kann
 */
@WebServlet(
		description = "Sucht nach Dateien", 
		urlPatterns = { 
				"/DateiSucheServlet", 
				"/suche"
		})
public class DateiSucheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateiSucheServlet() {
        super();
    }

	/**
	 * http get request mit einem parameter name
	 * http response wird als json zurückgeschickt
	 * http post brauchen wir in unserem falle nicht
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		// wir wollen json zurückgeben
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		// wir holen hier den wert des parameters name
		// suche?name=a
		// und weisen ihn der Variaben name zu
		String name = request.getParameter("name");
		System.out.println("Suche nach Name: "+name);
		
		// keine Datenbank oder csv vorhanden, daher lesen wir die Daten zum Testen neu ein
		// bei dir sollte es über die Datenbank gehen und auch dort suchen
		String startFolder = "C:\\dateimanager";		
		DateiManager dateiManager = new DateiManager();
		ArrayList<Eintrag> eintragList = dateiManager.readVerzeichnisMitUnterverzeichnissen(startFolder);
		EintragsSuche suche = new EintragsSuche(eintragList);
		// suche nach name
		ArrayList<Eintrag> ergebnisList = suche.findeNameEnthaeltText(name);
		// hier könnent natürlich auch andere Suchen gemacht werden (endungen,...). Diese müssten dann über parameter übergeben werden
		
		// erzeuge json
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("duration: "+duration);

		// fülle ein Objekt suchergebnis mit weiteren DAten zur Suche wie zum Beispiel DAuer
		SuchErgebnis suchErgebnis = new SuchErgebnis();		
		suchErgebnis.setDauer(duration);
		suchErgebnis.setErgebnisse(ergebnisList);
		suchErgebnis.setSuche("Name="+name);
		// erzeuge JSON
		Gson gson = new Gson();
		String jsonString = gson.toJson(suchErgebnis);
		response.getWriter().append(jsonString);
	}
	
}
