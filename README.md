<div align="center">
    <img src="readme_images/project_readme_banner_1500X450.png" alt="AudioShop Logo">
</div>

<div align="center">
    <h1>AudioShop Inventory Management</h1>
    <h3>Mobil Alkalmazás (Frontend)</h3>
    <h4>Portfólió munka</h4>
    <br>
</div>

---

<div>
    <h3>A projekt leírása:</h3>
    <ul style="font-size: 20px">
        <li>Az alkalmazás fő célja, hogy segítse a bolt leltározásának folyamatát.</li>
        <li>A szoftver úgy lett megalkotva, hogy tudjon futni Android alapú, vonalkód olvasására képes PDA eszközökön, azon belül is az <b>Android 8 (Oreo)</b> alapú eszközökön.</li>
        <li>Az alkalmazáshoz hozzátartozik az <a href="https://github.com/galmihaly/AudioShopInventoryManagementRestAPI">AudioShopInventoryManagementAPI</a> program is, ami az alkalmazás backend részét valósítja meg, ez fogja az adatokat szolgáltatni az alkalmazás számára.</li>
    </ul>
</div>

---

<div>
    <h3>Az alkalmazás grafikai megjelenése:</h3>
    <ul style="font-size: 20px">
        <li>A program grafikáját a jelenleg piacon lévő PDA-k közül a <b>ZEBRA TC-51</b> eszköz alapján terveztem meg, amellyel korábbi munkám során már valós projekt esetén is foglalkoztam.</li>
        <li>A projekt elkészítése során ilyen eszközzel azonban nem rendelkeztem, viszont Android Studio-ban a mobil emulátorok közül a <b>Google Pixel 2</b>-es telefon paraméterei pontosan megfeleltek a <b>TC-51</b>-es PDA eszköz paraméterei alapján.</li>
        <li>Az alkalmazás elemeinek elhelyezkedéseit, méreteit és megjelenésüket <b>Figma</b>-ban készítettem el.</li>
        <li>A logót, valamint az alkalmazásban megtalálható összes ikont pedig az <b>Adobe Illustrator</b> nevű program alkottam meg.</li>
        <li>Az alkalmazás grafikája (logót és dizájnt egybevéve) nem tartozik egyetlen valós céghez sem, csak a projekt érdekéken készítettem el.</li>
    </ul>
</div>

---

<div align="center">
        <h3>A projekt részletes leírása</h3>
</div>

<div>
    <h3>A program részei:</h3>
    <ul style="font-size: 20px">
        <li>A progamnak 3 fő része van:
            <ol style="list-style-type: square;">
                <li>Bejelentkező oldal</li>
                <li>Terméklista készítő oldal</li>
                <li>Terméklista átekintő oldal</li>
            </ol>
        </li>
    </ul>
</div>

---

<div>
    <div align="center">
        <h3>Bejelentkező felület</h3>
        <br>
        <img src="readme_images/login_screen.png" alt="AudioShop Logo">
    </div>
    <br>
    <ul style="font-size: 20px">
        <li>Amikor az alkalmazás elindul, akkor ez az első oldal, amely megjelenik a felhasználó számára.</li>
        <li>Az adatbázisba beregisztrált felhasználó email és jelszó beírásával tud bejelentkezni.</li>
        <li>A bejelentkezés <b>JWT (Json Web Token)</b> segítségével történik: az email és jelszó továbbítódik a backend oldalra, ahol az adatbázis segítségével a rendszer először ellenőrzi a személy azonosságát, majd az API visszaküld egy olyan üzenetet, amely tartalmaz két tokent.</li>
        <li>A tokennek két típusból állnak:
            <ol style="list-style-type: square;">
                <li>Hozzáférési token</li>
                <li>Frissítési token</li>
            </ol>
        </li>
    </ul>
</div>

<div>
    <h4>Hozzáférési token:</h4>
    <ul style="font-size: 20px">
        <li>Asszimetrikus, 512 bites RSA kulccsal van titkosítva a token.</li>
    </ul>
</div>
