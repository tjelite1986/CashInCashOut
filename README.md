# Budget App

En avancerad Android budget-app skapad med Kotlin och Material Design 3, med fokus på produkthantering, prisjämförelse, inkomst- och utgiftshantering.

## Funktioner

Appen har en bottom navigation bar med fyra huvudsektioner:

### 📊 Översikt
- Visar total balans, månatliga inkomster och utgifter
- **Lånöversikt** - separata kort för lånade och utlånade belopp
- Snabbåtgärder för att lägga till inkomster, utgifter och lån
- **Floating Action Button** för att lägga till produkter
- **"Visa alla produkter"** knapp för att komma åt produktlistan
- **"Hantera butiker"** knapp för butikshantering
- **Anpassningsbar** - visa/dölj sektioner via EditHomeActivity
- Elegant dashboard med cards

### 💸 Transaktioner
- **Komplett transaktionshantering** för både inkomster och utgifter
- **Unified view** - se alla transaktioner på ett ställe med filtrering
- **Lägg till utgifter** med titel, belopp, kategori, butik och betalmetod
- **Lägg till inkomster** med titel, belopp, kategori, datum och beskrivning
- **Utgiftskategorier** - Mat, Transport, Hygien, Nöje, Räkningar, Kläder, Hälsa, Boende, Övrigt
- **Inkomstkategorier** - Lön, Freelance, Investeringar, Bidrag, Övrigt
- **Butiksregistrering** - spåra var utgiften gjordes
- **Betalmetoder** - Kort, Kontant, Swish, Faktura
- **Återkommande transaktioner** - månadsvis, veckovis, årligen
- **Filtrering** - visa alla, endast inkomster eller endast utgifter
- **Summering** - totala inkomster och utgifter visas överst
- **Floating Action Button** för att lägga till nya transaktioner

### 💳 Lån
- **Komplett lånhanteringssystem** med alla funktioner implementerade
- **Lägg till lån** - både lånade och utlånade pengar
- **Låntyper** - "Lånat från någon" (skulder) och "Lånat ut till någon" (fordringar)
- **Låndetaljer** - titel, belopp, person, ränta, förfallodatum, beskrivning
- **Återbetalningshantering** - markera lån som återbetalda eller ångra återbetalning
- **Filtrering** - visa alla, lånade, utlånade, aktiva eller återbetalda lån
- **Översiktsintegration** - separata kort för lånade och utlånade belopp
- **Redigering och borttagning** - fullständig CRUD-funktionalitet
- **FAB-meny** för snabb åtkomst till låntilläggning

### 📋 Budget
- Sektion för budgetplanering  
- Plats för framtida funktioner som budgetkategorier och gränser

### ⚙️ Inställningar
- App-inställningar och konfiguration
- Information om appen

## 🛒 Produkthantering

### Lägg till produkter
- **Produktnamn** (obligatoriskt fält)
- **Butik** - Välj från fördefinierade butiker (ICA, Coop, Hemköp, Willys, Lidl)
- **Pris** per butik (obligatoriskt fält)
- **Varunamn** - t.ex. "Coca-Cola"
- **Mängd och enhet** - t.ex. "330" + "ml" för "330ml"
- **Enhet dropdown** - st, g, kg, cl, ml, l, mb, gb
- **Kategori** (11 fördefinierade kategorier)
- **Panthantering** - aktivera/inaktivera pant med pantbelopp
- **Kampanjpris** - t.ex. "2 för 25 kr" per butik
- **Streckkod** - manuell input eller genom kamerascanning
- **Beskrivning** för extra information

### Produktlista och prisjämförelse
- **Produktlista** med alla registrerade produkter
- **Prisintervall** - visar lägsta till högsta pris
- **Antal butiker** - visar i hur många butiker produkten finns
- **Prisdetaljer** - klicka på "Visa priser" för att se alla priser
- **Redigera produkter** - uppdatera produktinformation
- **Ta bort produkter** - ta bort produkter från databasen

### Prishantering per butik
- **Lägg till priser** - samma produkt kan ha olika priser i olika butiker
- **Redigera priser** - uppdatera priser för specifika butiker
- **Ta bort priser** - ta bort priser från specifika butiker
- **Kampanjpriser** - hantera kampanjpriser per butik
- **Senast sett** - spåra när priset senast observerades

### Streckkodscanning
- **Kamerabaserad scanning** med ML Kit
- **Automatisk produktidentifiering** baserat på streckkod
- **Förifyllt formulär** om produkten redan finns i databasen
- **Fallback till manuell input** om scanning inte fungerar

### 🏪 Butikshantering
- **Komplett butikshantering** med CRUD-operationer
- **Lägg till butiker** med namn, adress, stad och kedja
- **Redigera butiker** - uppdatera information
- **Ta bort butiker** - med bekräftelse
- **Butikslista** med sökfunktionalitet
- **Kedjehantering** - ICA, Coop, Hemköp, Willys, Lidl
- **Ljust tema** för bättre läsbarhet

### Databas
- **Room database** för lokal lagring
- **Kategorisystem** med färgkodning
- **Butikssystem** med kedjehantering
- **Inkomstsystem** med kategori- och återkommandestöd
- **Utgiftssystem** med kategori-, butiks- och betalmetodsstöd
- **Lånsystem** med låntyper, ränta, förfallodatum och återbetalningshantering
- **Relationshantering** mellan produkter, butiker och kategorier
- **Sökfunktioner** baserat på namn och varumärke
- **Databasmigration** från version 2 till 7

## Teknisk specifikation

- **Språk**: Kotlin
- **UI Framework**: Material Design 3
- **Navigation**: Bottom Navigation Bar med fragments
- **MinSDK**: 24 (Android 7.0)
- **TargetSDK**: 34
- **Architecture**: Fragment-based navigation
- **Databas**: Room (SQLite) version 7
- **Kamera**: CameraX med ML Kit för streckkodscanning

## Byggd struktur

```
app/
├── src/main/
│   ├── java/com/example/budgetapp/
│   │   ├── MainActivity.kt
│   │   ├── AddProductActivity.kt
│   │   ├── ProductListActivity.kt
│   │   ├── EditProductActivity.kt
│   │   ├── ProductPriceManagerActivity.kt
│   │   ├── BarcodeScannerActivity.kt
│   │   ├── AddIncomeActivity.kt
│   │   ├── AddExpenseActivity.kt
│   │   ├── AddLoanActivity.kt
│   │   ├── EditHomeActivity.kt
│   │   ├── StoreManagerActivity.kt
│   │   ├── ProductAdapter.kt
│   │   ├── LoanAdapter.kt
│   │   ├── TransactionAdapter.kt
│   │   ├── ProductWithPricesAdapter.kt
│   │   ├── ProductPriceAdapter.kt
│   │   ├── fragments/
│   │   │   ├── OverviewFragment.kt
│   │   │   ├── TransactionsFragment.kt
│   │   │   ├── LoansFragment.kt
│   │   │   ├── BudgetFragment.kt
│   │   │   └── SettingsFragment.kt
│   │   ├── data/
│   │   │   └── ProductWithPrices.kt
│   │   └── database/
│   │       ├── BudgetDatabase.kt
│   │       ├── entities/
│   │       │   ├── Product.kt
│   │       │   ├── Category.kt
│   │       │   ├── Store.kt
│   │       │   ├── ProductStore.kt
│   │       │   ├── Income.kt
│   │       │   ├── Expense.kt
│   │       │   └── Loan.kt
│   │       └── dao/
│   │           ├── ProductDao.kt
│   │           ├── CategoryDao.kt
│   │           ├── StoreDao.kt
│   │           ├── ProductStoreDao.kt
│   │           ├── IncomeDao.kt
│   │           ├── ExpenseDao.kt
│   │           └── LoanDao.kt
│   └── res/
│       ├── layout/          # Layout-filer för UI
│       ├── drawable/        # Ikoner för navigation
│       ├── values/          # Colors, strings, themes
│       └── menu/            # Bottom navigation menu
```

## Databas schema

### Product Table
- **id**: Primary key
- **name**: Produktnamn
- **categoryId**: Foreign key till Category
- **hasDeposit**: Boolean för pant
- **depositAmount**: Pantbelopp
- **barcode**: Streckkod
- **productName**: Varumärke/varunamn
- **description**: Beskrivning
- **amount**: Mängd (numerisk)
- **unit**: Enhet (st, g, kg, cl, ml, l, mb, gb)
- **createdAt/updatedAt**: Tidsstämplar

### Store Table
- **id**: Primary key
- **name**: Butiksnamn
- **address**: Adress (valfritt)
- **city**: Stad (valfritt)
- **chain**: Kedja (ICA, Coop, etc.)
- **createdAt/updatedAt**: Tidsstämplar

### ProductStore Table
- **id**: Primary key
- **productId**: Foreign key till Product
- **storeId**: Foreign key till Store
- **price**: Pris i butiken
- **hasCampaignPrice**: Boolean för kampanjpris
- **campaignQuantity**: Antal för kampanj
- **campaignPrice**: Kampanjpris
- **lastSeen**: När priset senast observerades
- **createdAt/updatedAt**: Tidsstämplar

### Category Table
- **id**: Primary key
- **name**: Kategorinamn
- **color**: Färgkod
- **createdAt/updatedAt**: Tidsstämplar

### Income Table
- **id**: Primary key
- **title**: Inkomstitel
- **amount**: Belopp
- **description**: Beskrivning (valfri)
- **category**: Kategori (Lön, Freelance, Investeringar, Bidrag, Övrigt)
- **date**: Datum för inkomst
- **isRecurring**: Boolean för återkommande
- **recurringType**: Återkommande typ (MONTHLY, WEEKLY, YEARLY)
- **createdAt/updatedAt**: Tidsstämplar

### Expense Table
- **id**: Primary key
- **title**: Utgiftitel
- **amount**: Belopp
- **description**: Beskrivning (valfri)
- **category**: Kategori (Mat, Transport, Hygien, Nöje, Räkningar, Kläder, Hälsa, Boende, Övrigt)
- **store**: Butik/Plats där utgiften gjordes (valfri)
- **date**: Datum för utgift
- **isRecurring**: Boolean för återkommande
- **recurringType**: Återkommande typ (MONTHLY, WEEKLY, YEARLY)
- **paymentMethod**: Betalmetod (Kort, Kontant, Swish, Faktura)
- **createdAt/updatedAt**: Tidsstämplar

### Loan Table
- **id**: Primary key
- **title**: Låntitel
- **amount**: Lånebelopp
- **description**: Beskrivning (valfri)
- **personName**: Person som lånat till/från
- **type**: Låntyp (BORROWED för skuld, LENT för fordran)
- **interestRate**: Ränta i procent (valfri)
- **dueDate**: Förfallodatum (valfri)
- **isPaidBack**: Boolean för återbetald status
- **paidBackDate**: Datum för återbetalning (valfri)
- **paidBackAmount**: Återbetalt belopp
- **createdAt/updatedAt**: Tidsstämplar

## Fördefinierade data

### Butiker
- ICA Maxi
- Coop Extra
- Hemköp
- Willys
- Lidl

### Kategorier
- Mejeri (#2196F3)
- Kött & Fisk (#F44336)
- Frukt & Grönt (#4CAF50)
- Bröd & Bakverk (#FF9800)
- Konserver (#9C27B0)
- Frysta varor (#00BCD4)
- Drycker (#CDDC39)
- Snacks (#FF5722)
- Hygien & Hälsa (#607D8B)
- Hushåll (#795548)
- Övrigt (#9E9E9E)

### Inkomstkategorier
- Lön
- Bonus
- Freelance
- Investering
- Försäljning
- Övrigt

### Utgiftskategorier
- Mat
- Transport
- Hygien
- Nöje
- Räkningar
- Kläder
- Hälsa
- Boende
- Övrigt

### Betalmetoder
- Kort
- Kontant
- Swish
- Faktura

## Färgtema

Appen använder ett grönt färgtema som är lämpligt för ekonomi-appar:
- **Primary**: #2E7D32 (grön)
- **Secondary**: #4CAF50 (ljusgrön)
- **Inkomst**: #4CAF50 (grön)
- **Utgift**: #F44336 (röd)
- **Varning**: #FF9800 (orange)
- **Framgång**: #4CAF50 (grön)
- **Fel**: #F44336 (röd)

## Användarflöde

1. **Lägg till produkt**: Välj butik → Ange produktinformation → Ange pris → Spara
2. **Visa produkter**: Översikt → "Visa alla produkter" → Se alla produkter med prisintervall
3. **Hantera priser**: Produktlista → "Visa priser" → Se alla priser → Lägg till/redigera/ta bort priser
4. **Redigera produkt**: Produktlista → "Redigera" → Uppdatera produktinformation
5. **Lägg till inkomst**: Översikt → "Lägg till inkomst" → Ange titel, belopp, kategori, datum
6. **Lägg till utgift**: Transaktioner → "Lägg till utgift" → Ange titel, belopp, kategori, butik, betalmetod
7. **Hantera transaktioner**: Transaktionsfliken → Se inkomster och utgifter → Filtrera och redigera
8. **Lägg till lån**: Lånfliken → "Lägg till lån" → Välj typ → Ange detaljer → Spara
9. **Hantera lån**: Lånfliken → Filtrera lån → Markera som återbetalt/ångra → Redigera/ta bort
10. **Anpassa översikt**: Inställningar (meny) → "Edit Home" → Visa/dölj sektioner
11. **Hantera butiker**: Översikt → "Hantera butiker" → Lägg till/redigera/ta bort butiker

## Nästa steg

Grundstrukturen är nu klar och redo för vidareutveckling. Möjliga nästa steg:

1. **Dataexport**: Exportera inkomst- och utgiftsdata till CSV/Excel
2. **Statistik**: Lägg till grafer för inkomst/utgift-utveckling över tid
3. **Budgetgränser**: Sätt månads- och kategorivisa budgetgränser
4. **Rapporter**: Månadsrapporter och kategorivisning
5. **Notifikationer**: Påminnelser om kampanjpriser och budgetvarningar
6. **Inköpslistor**: Skapa och hantera inköpslistor
7. **Prishistorik**: Spåra prisutveckling över tid
8. **Butikskartor**: Integration med Google Maps
9. **Delning**: Dela produkter och priser med andra användare

## Hur man bygger

```bash
./gradlew assembleDebug
```

APK-filen skapas i `app/build/outputs/apk/debug/budget-debug-1.1-<datum>.apk`

## Installation

1. Aktivera "Okända källor" i Android-inställningar
2. Installera `budget-debug-1.1-<datum>.apk` på din Android-enhet
3. Öppna appen och börja lägga till produkter!

## Bidrag

Denna app är byggd som en demonstration av Android-utveckling med Kotlin och Room database. Känner du dig fri att forka och bidra med förbättringar!