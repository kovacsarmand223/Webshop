# Tesztjegyzőkönyv - `Rendelés`

Az alábbi tesztdokumentum a Webshop projekthez tartozó Kosár funkcióhoz készült.

## 1. Teszteljárások (TP)

### 1.1. Kosár funkció tesztelése

- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Kosár tesztelése
    1. lépés: Kosár ikonra rányomunk
    2. lépés: A Település szövegbeviteli mezőbe írjunk be a string1 stringet
    3. lépés: Az Utca szövegbeviteli mezőbe írjunk be a string2 stringet
    4. lépés: A Házszám szövegbeviteli mezőbe írjunk be egy int számot
    5. lépés: Megrendelés gomb megnyomása 

## 2. Tesztesetek (TC)

### 2.1. Kosár funkció tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Kosár funkció tesztelése
- Bemenet: Település = Szeged; Utca = Random; Házszám = 1;
- Művelet: Rendelés gomb megnyomása
- Elvárt kimenet: Törlődik a kosár tartalma, Rendelés táblába bekerül

## 3. Tesztriportok (TR)

### 3.1. Kosár funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)

- TP: TP-01
    1. lépés: Megnyomtam a Kosár ikont
    2. lépés: Beírtam, hogy Szeged
    3. lépés: Beírtam, hogy Random
    4. lépés: Beírtam, hogy 1
    5. lépés: Megnyomtam a Rendelés gombot
    6. lépés: Helyes eredményt kaptam (Kosárból törlődött minden, Megrendel táblába hozzáadódik)