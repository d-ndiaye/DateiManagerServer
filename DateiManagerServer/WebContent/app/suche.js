// initialisiere den listener für den suche-button
function init() {
    document.getElementById("suchbutton").addEventListener('click', startSuche);
}

// starte die suche
async function startSuche() {
    // lese die eingaben aus
    let suchwort = document.getElementById("suchfeld").value;
    console.log(suchwort);
    // hier rufen wir das Servlet auf und warten auf das Ergebnis als JSON
    // die url ist dann automatisch http://localhost:8080/DateimanagerServer/suche?suchwort=suchwort
    const response = await fetch('../suche?name=' + suchwort);
    const suchErgebnis = await response.json();
    // hier haben wir ein JSON-Objekt mit den Ergebnissen (siehe java)
    console.log(suchErgebnis);
    // such ergebnis anzeigen
    zeigeSuchErgebnis(suchErgebnis);
}

// zeige das suchergebnis
function zeigeSuchErgebnis(suchErgebnis) {
    // zeige die details an
    let details = `
        <p>Dauer   : <b>${suchErgebnis.dauer}</b> ms</p>
        `
    document.getElementById("details").innerHTML = details;

    // zeige jeden treffer an, hier erzeugen wir mit javascript elemente.
    // let ergebnisHtml =`<h2>Ergebnisse</h2>`
    let ergebnisElement = document.getElementById("ergebnisse");
    // wir löschen erstmal alles html aus dem ergebnis
    ergebnisElement.innerHTML = '';
    let titelElement = document.createElement("h2");
    titelElement.innerHTML = "Ergebnisse (" + suchErgebnis.ergebnisse.length + ")";
    ergebnisElement.appendChild(titelElement);

    // schleife für jeden eintrag
    for (let i = 0; i < suchErgebnis.ergebnisse.length; i++) {
        let eintrag = suchErgebnis.ergebnisse[i];
        // wir erzeugen die elemente(tags) und fügen sie dem div zu
        let eintragDiv = document.createElement("div");
        let nameElement = document.createElement("p");
        let ortElement = document.createElement("p");
        // setze die inhalte der elemente
        nameElement.innerHTML = eintrag.name;
        nameElement.classList.add("largetext");
        ortElement.innerHTML = eintrag.ort;
        ortElement.classList.add("smalltext");
        // füge die elemmnte dem div hinzu
        eintragDiv.appendChild(nameElement);
        eintragDiv.appendChild(ortElement);
        ergebnisElement.appendChild(eintragDiv);
        // so könnten wir das html auch erzeugen, diese Schreibweise ist oft schneller
        // über die Objekte ist es aber einfacher event listeneren hinzuzufügen
        /*
        ergebnisHtml += `
            <p class="largetext">${eintrag.name}</p>
            <p class="smalltext">${eintrag.ort}</p>
            <hr>
            `;
        */
    }
    // document.getElementById("ergebnisse").innerHTML = ergebnisHtml;
}