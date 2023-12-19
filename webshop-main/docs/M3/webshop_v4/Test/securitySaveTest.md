# Tesztjegyzőkönyv - `Biztonsági mentés`

Az alábbi tesztdokumentum a Webshop projekthez tartozó 8.3.21. Biztonsági mentés automatikus létrehozása funkcióhoz készült.

## 1. Teszteljárások (TP)

### 1.1. Biztonsági mentés tesztelése

- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Biztonsági mentés tesztelése
    1. lépés: Nyissuk meg a regisztrációs űrlapot!
    2. lépés: A Vezetéknév szövegbeviteli mezőbe írjunk be a string1 stringet
    3. lépés: A Keresztnév szövegbeviteli mezőbe írjunk be a string2 stringet
    4. lépés: A Felhasználónév szövegbeviteli mezőbe írjunk be a string3 stringet
    5. lépés: A Jelszó szövegbeviteli mezőbe írjunk be a string4 stringet
    6. lépés: A Jelszó mégegyszer szövegbeviteli mezőbe írjunk be a string5 stringet
    7. lépés: Nyomjuk meg az Regisztráció gombot
    8. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: Az adatbázisban szerepel a felhasználó

## 2. Teszesetek (TC)

### 2.1. Biztonsági mentés tesztesetei

#### 2.1.1. TC-01

- TP: TP-01
- Leírás: Regisztráció funkció tesztelése
- Bemenet: Vezetéknév  = Molnár; Keresztnév = Ákos; Felhasználónév = molako; Jelszó = molako123; Jelszó mégegyszer = molako123
- Művelet: Regisztráció gomb megnyomása
- Elvárt kimenet: Az adatbázisban szerepelnek az adatok

## 3. Tesztriportok (TR)

### 3.1. Biztonsági mentés  tesztriportjai

#### 3.1.1. TR-01 (TC-01)

- TP: TP-01
    1. lépés: Megnyomtam a Regisztráció gombot
    2. lépés: Beírtam, hogy Molnár
    3. lépés: Beírtam, hogy Ákos
    4. lépés: Beírtam, hogy molako
    5. lépés: Beírtam, hogy molako123
    6. lépés: Beírtam, hogy molako123
    7. lépés: Megnyomtam a gombot
    8. lépés: Helyes eredményt kaptam (Az adatbázisban szerepelnek az adatok)

