# Tesztjegyzőkönyv - `Admin funkciói (CRUD)`

Az alábbi tesztdokumentum a Webshop projekthez tartozó 8.3.9. Webshop kezelése (CR), 8.3.10. Webshop kezelése (UD) és funkcióhoz készült.

## 1. Teszteljárások (TP)

### 1.1. Termék létrehozása funkció tesztelése

- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Termék létrehozása
  1. lépés: Adminként jelentkezünk be
  2. lépés: Rákattintunk az "Admin oldal" gombra 
  3. lépés: A Termékazonosító szövegbeviteli mezőbe írjunk be egy int számot
  4. lépés: A Név szövegbeviteli mezőbe írjunk be a string1 stringet
  5. lépés: A Gyártó szövegbeviteli mezőbe írjunk be a string2 stringet
  6. lépés: A Leírás szövegbeviteli mezőbe írjunk be a string3 stringet
  7. lépés: Az Ár szövegbeviteli mezőbe írjunk be egy int számot
  8. lépés: Az Elérhető-e szövegbeviteli mezőbe írjunk be, hogy igen vagy nem
  9. lépés: A Származásiország szövegbeviteli mezőbe írjunk be a string4 stringet
  10. lépés: Az Osztályzás szövegbeviteli mezőbe írjunk be egy int számot
  11. lépés: Majd nyomjuk meg a "Hozzáad" gombot

### 1.2. Termék módosítása funkció tesztelése

- Azonosító: TP-02
- Tesztesetek: TC-02
- Leírás: Termék módosítása
    1. lépés: Adminként jelentkezünk be
    2. lépés: Rákattintunk az "Admin oldal" gombra
    3. lépés: Kiválasztjuk min szeretnénk módosítani, majd a "Kiválaszt" gombra rányomunk
    4. lépés: Hozzá tartozó adatokon módosítunk
    5. lépés: Rányomunk a Módosít gombra
    6. lépés: Helyes eredményt kaptam (Megtörtént a módosítás)

### 1.3. Termék törlése funkció tesztelése

- Azonosító: TP-03
- Tesztesetek: TC-03
- Leírás: Termék törlése
    1. lépés: Adminként jelentkezünk be
    2. lépés: Rákattintunk az "Admin oldal" gombra
    3. lépés: Kiválasztjuk mit szeretnénk törölni, megnyomjuk a Törlés gombot 
    4. lépés: Helyes eredményt kaptam (Megtörtént a törlés)

## 2. Tesztesetek (TC)

### 2.1. Termék létrehozása funkció tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Termék létrehozásának tesztelése
- Bemenet: Termékazonosító = 1; Név = teszt; Gyártó = teszt; Leírás = valami; Ár = 1000; Elérhető-e = igen; Származásiország = valami; Osztályzás = 6;
- Művelet: Hozzáad gomb megnyomása
- Elvárt kimenet: Megjelenik a termék a táblázatban

### 2.2. Termék módosítása funkció tesztesetei

#### 2.2.1. TC-02
- TP: TP-02
- Leírás: Termék módosítása tesztelése
- Bemenet: Termékazonosító = 1; Név = teszt; Gyártó = teszt; Leírás = valami; Ár = 1000; Elérhető-e = igen; Származásiország = valami; Osztályzás = 10;
- Művelet: Kiválaszt gomb, majd "Módosít" gomb megnyomása
- Elvárt kimenet: Megváltozik a termék osztályzása a táblázatban

### 2.3. Termék törlése funkció tesztesetei

#### 2.3.1. TC-03
- TP: TP-03
- Leírás: Termék törlése tesztelése
- Művelet: Törlés gomb megnyomása
- Elvárt kimenet: Törlődik a termék a táblázatból

## 3. Tesztriportok (TR)

### 3.1. Termék létrehozása funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)

- TP: TP-01
    1. lépés: Megnyomtam a "Bejelenkezés" gombot
    2. lépés: Megnyomtam a "Admin oldal" gombot 
    3. lépés: Beírtam, hogy 1
    4. lépés: Beírtam, hogy teszt
    5. lépés: Beírtam, hogy teszt
    6. lépés: Beírtam, hogy valami
    7. lépés: Beírtam, hogy 1000
    8. lépés: Beírtam, hogy igen
    9. lépés: Beírtam, hogy valami
    10. lépés: Beírtam, hogy 6
    11. lépés: Megnyomtam a "Hozzáad" gombot
    12. lépés: Helyes eredményt kaptam (Hozzáadta a táblázathoz)

### 3.2. Termék módosítása funkció tesztriportjai

#### 3.2.1. TR-02 (TC-02)

- TP: TP-02
    1. lépés: Kiválasztottam a terméket, megnyomtam a "Kiválaszt" gombot
    2. lépés: Beírtam, hogy 10
    3. lépés: Megnyomtam a "Módosít gombot"
    4. lépés: Helyes eredményt kaptam (Módosult a termék osztályzása)

### 3.3. Termék törlése funkció tesztriportjai

#### 3.3.1. TR-03 (TC-03)

- TP: TP-03
    1. lépés: Megnyomtam a Bejelenkezés gombot
    2. lépés: Megnyomtam az "Admin oldal" gombot
    3. lépés: Rányomtam a "Törlés" gombra
    4. lépés: Helyes eredményt kaptam (Törlődött a táblázatból)
