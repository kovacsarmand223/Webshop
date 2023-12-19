# Tesztjegyzőkönyv - `Admin funkciói (CRUD)`

Az alábbi tesztdokumentum a Webshop projekthez tartozó 8.3.1. Felhasználók kezelése (admin, felhasználó) (CR), 8.3.2. Felhasználók kezelése (admin, felhasználó) (U) és 8.3.3. Felhasználók kezelése (admin, felhasználó) (D) funkcióhoz készült.

## 1. Teszteljárások (TP)

### 1.1. Felhasználó létrehozása funkció tesztelése

- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Felhasználó létrehozása
  1. lépés: Adminként jelentkezünk be
  2. lépés: Rákattintunk az Admin oldal gombra 
  3. lépés: A Felhasználónév szövegbeviteli mezőbe írjunk be a string1 stringet
  4. lépés: A Keresztnév szövegbeviteli mezőbe írjunk be a string2 stringet
  5. lépés: A Vezetéknév szövegbeviteli mezőbe írjunk be a string3 stringet
  6. lépés: A Jelszó szövegbeviteli mezőbe írjunk be a string4 stringet
  7. lépés: A Jelszó mégegyszer szövegbeviteli mezőbe írjunk be a string5 stringet
  8. lépés: A Jogosultság szövegbeviteli mezőbe írjunk be a string6 stringet
  9. lépés: A Település szövegbeviteli mezőbe írjunk be a string7 stringet
  10. lépés: A Utca szövegbeviteli mezőbe írjunk be a string8 stringet
  11. lépés: A Házszám szövegbeviteli mezőbe írjunk be a egy int számot

### 1.2. Felhasználó módosítása funkció tesztelése

- Azonosító: TP-02
- Tesztesetek: TC-02
- Leírás: Felhasználó módosítása
    1. lépés: Adminként jelentkezünk be
    2. lépés: Rákattintunk az Admin oldal gombra
    3. lépés: Kiválasztjuk, kin szeretnénk módosítani, Kiválaszt gombra rányomunk
    4. lépés: Hozzá tartozó adatokon módosítunk
    5. lépés: Rányomunk a Módosít gombra
    6. lépés: Helyes eredményt kaptam (Megtörtént a módosítás)

### 1.3. Felhasználó törlése funkció tesztelése

- Azonosító: TP-03
- Tesztesetek: TC-03
- Leírás: Felhasználó törlése
    1. lépés: Adminként jelentkezünk be
    2. lépés: Rákattintunk az Admin oldal gombra
    3. lépés: Kiválasztjuk kit szeretnénk törölni, megnyomjuk a Törlés gombot 
    4. lépés: Helyes eredményt kaptam (Megtörtént a törlés)

## 2. Tesztesetek (TC)

### 2.1. Felhasználó létrehozása funkció tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Felhasználó létrehozásának tesztelése
- Bemenet: Felhasználónév = molakopat; Vezetéknév  = MOL; Keresztnév = AKO; Jelszó = molakopat123; Jelszó mégegyszer = molakopat123; Jogosultság = vásárló; Település = Szeged; Utca = Random; Házszám = 18;
- Művelet: Hozzáad gomb megnyomása
- Elvárt kimenet: Megjelenik a felhasználó a táblázatban

### 2.2. Felhasználó módosítása funkció tesztesetei

#### 2.2.1. TC-02
- TP: TP-02
- Leírás: Felhasználó módosítása tesztelése
- Bemenet: Jogosultság = admin;
- Művelet: Kiválaszt gomb, majd Módosít gomb megnyomása
- Elvárt kimenet: Megváltozik a felhasználó adata(i) a táblázatban

### 2.3. Felhasználó törlése funkció tesztesetei

#### 2.3.1. TC-03
- TP: TP-03
- Leírás: Felhasználó törlése tesztelése
- Művelet: Törlés gomb megnyomása
- Elvárt kimenet: Törlődik a felhasználó a táblázatból

## 3. Tesztriportok (TR)

### 3.1. Felhasználó létrehozása funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)

- TP: TP-01
    1. lépés: Megnyomtam a Bejelenkezés gombot
    2. lépés: Megnyomtam az Admin oldal gombot 
    3. lépés: Beírtam, hogy molakopat
    4. lépés: Beírtam, hogy MOL
    5. lépés: Beírtam, hogy AKO
    6. lépés: Beírtam, hogy molakopat123
    7. lépés: Beírtam, hogy molakopat123
    8. lépés: Beírtam, hogy vásárló
    9. lépés: Beírtam, hogy Szeged
    10. lépés: Beírtam, hogy Random
    11. lépés: Beírtam, hogy 18
    12. lépés: Megnyomtam a Hozzáad gombot
    13. lépés: Helyes eredményt kaptam (Hozzáadta a táblázathoz, megfelelő adatokkal)

### 3.2. Felhasználó módosítása funkció tesztriportjai

#### 3.2.1. TR-02 (TC-02)

- TP: TP-02
    1. lépés: Megnyomtam a Bejelenkezés gombot
    2. lépés: Megnyomtam az Admin oldal gombot
    3. Kiválasztottam a felhasználót, megnyomtam a Kiválaszt gombot
    4. Beírtam, hogy admin
    5. Megnyomtam a Módosít gombot
    6. Helyes eredményt kaptam (Módosult a felhasználó adata, admin lett belőle)

### 3.3. Felhasználó törlése funkció tesztriportjai

#### 3.3.1. TR-03 (TC-03)

- TP: TP-03
    1. lépés: Megnyomtam a Bejelenkezés gombot
    2. lépés: Megnyomtam az Admin oldal gombot
    3. lépés: Rányomtam a Törlés gombra
    4. lépés: Helyes eredményt kaptam (Törlődött a táblázatból)
