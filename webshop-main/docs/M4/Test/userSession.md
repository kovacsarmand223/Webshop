# Tesztjegyzőkönyv - 'Felhasználói munkamenet'

## 1. Teszteljárások (TP)

### 1.1 Session megkezdése bejelentkezéskor

- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Session
    1. lépés: Helyes adatok megadása
    2. lépés: Bejelentkezés
    3. lépés: Profil gombra kattintás

### 2. Tesztesetek (TC)

### 2.1. Session funkció tesztjei
- TP: TP-01
- Leírás: Session nem bejelentkezett funkció
- Művelet: Profil/Kosár megtekintése nem bejelentkezett felhasználóként
- Elvárt kimenet: Nem engedélyezi a hozzáférést, hibaüzenetet ír ki, visszairányít a bejelentkezéshez

## 3. Tesztriportok (TR)

### 3.1 Session funkció tesztriportjai

#### 3.1.1 TR-01 (TC-01)

- TP: TP-01
    1. lépés: Megnyitottam nem bejelentkezett felhasználóként a profilt
    2. lépés: Megnyitottam bejelentkezett felhasználóként a profilt
    3. lépés: Megnyitottam nem bejelentkezett felhasználóként a kosárt
    4. lépés: Megnyitottam bejelentkezett felhasználóként a profilt
    5. lépés: Helyes eredményt kaptam (Mindig jó megfelelő oldalra irányított)




