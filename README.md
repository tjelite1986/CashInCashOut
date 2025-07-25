# CashInCashOut

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

### ⚙️ Mer
- **Kategorihantering** - avancerat system för att hantera inkomst- och utgiftskategorier
- **Smarta rekommendationer** - AI-driven prisanalys och besparingstips
- **Inköpslistor** - skapa och hantera inköpslistor med prishistorik
- **Prishistorik** - spåra prisutveckling över tid med grafer
- **Kvittohantering** - scanna och registrera kvitton med butiksinformation
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
- **Prishistorik** - automatisk lagring av alla prisändringar över tid

### Streckkodscanning
- **Kamerabaserad scanning** med ML Kit
- **Automatisk produktidentifiering** baserat på streckkod
- **Förifyllt formulär** om produkten redan finns i databasen
- **Fallback till manuell input** om scanning inte fungerar

## 🤖 Smarta rekommendationer

### Prisanalys och besparingstips
- **Intelligent prisanalys** - avancerade algoritmer analyserar prishistorik
- **Flera rekommendationstyper** - Bästa pris, prisfall, bra deals, undvik höga priser
- **Konfidenspoäng** - bedömer tillförlitlighet baserat på datamängd och aktualitet
- **Inköpslisteoptimeringar** - får personliga tips för att spara pengar

## 🏗️ Teknisk arkitektur

### Systemdesign
Appen använder en **lagerad arkitektur** med tydlig separation av ansvar:

1. **Presentationslager** - Activities, Fragments, Adapters
2. **Affärslogiklager** - Services, Repositories, ViewModels  
3. **Datalager** - Database entities, DAOs, Room database

### Kärnkomponenter

#### Activities (`com/example/budgetapp/`)
| Aktivitet | Syfte | Nyckelfunktioner |
|-----------|-------|------------------|
| `MainActivity.kt` | Huvudnavigering | Bottom navigation, fragmenthantering, tema/notifikationsinställningar |
| `AddExpenseActivity.kt` | Utgiftsskapande | Kategorival, butikslänkning, betalmetoder |
| `AddIncomeActivity.kt` | Inkomstspårning | Inkomstkategorier, återkommande inkomststöd |
| `AddLoanActivity.kt` | Lånhantering | Lånade/utlånade pengar med räntor |
| `ProductListActivity.kt` | Produktkatalog | Produkthantering med prishistorik |
| `SmartRecommendationsActivity.kt` | AI-drivna insikter | Prisanalys och sparrekommendationer |

#### Fragmentstruktur (`fragments/`)
- **OverviewFragment** - Dashboard med balans, snabbåtgärder, lånöversikt
- **TransactionsFragment** - Enhetlig transaktionsvy med filtrering
- **LoansFragment** - Komplett lånhanteringssystem
- **BudgetFragment** - Budgetplanering och spårning
- **MoreFragment** - Inställningar och ytterligare funktioner

#### Databasarkitektur (`database/`)
**Room Database Version 16** med 17 entiteter:

**Kärnfinansiella entiteter:**
- `Expense` - Utgiftsspårning med kategorier, butiker, betalmetoder
- `Income` - Inkomsthantering med återkommande stöd
- `Loan` - Lånspårning (lånat/utlånat) med ränteberäkningar
- `Budget` - Budgetplanering och gränser

**Produkthantering:**
- `Product` - Produktkatalog med streckkodsstöd
- `ProductCategory` - Produktkategoriseringssystem
- `ProductStore` - Prisspårning per butik
- `PriceHistory` - Historiska prisdata för rekommendationer

**Avancerade funktioner:**
- `Receipt` + `ReceiptItem` - Kvittohanteringssystem
- `ShoppingList` + `ShoppingListItem` - Inköpslisteoptimering
- `TransactionNotification` - Smart notifikationssystem

#### Services & Bakgrundsbearbetning (`services/`)
- **SmartRecommendationService** - AI-driven prisanalys
- **BudgetNotificationService** - Automatiserade budgetvarningar
- **BudgetRolloverWorker** - Månatlig budgetbearbetning
- **ShoppingListOptimizer** - Butiksbaserad inköpsoptimering

#### Repository Pattern (`repository/`)
Rent dataåtkomstlager med repositories för:
- Budgethantering
- Kategorihantering
- Inköpslisteoperationer
- Butikshantering

### Tekniska höjdpunkter
1. **Material Design 3** - Modernt UI med temastöd
2. **Room Database** - Robust lokal lagring med migreringar
3. **Kotlin Coroutines** - Asynkrona operationer
4. **ViewBinding** - Typsäkra vyreferenser
5. **WorkManager** - Bakgrundsuppgiftsbearbetning
6. **Streckkodscanning** - Produktidentifiering med ML Kit
7. **Notifikationssystem** - Smarta budgetvarningar
- **Smarta förslag** - notifieringar om prisfall och besparingsmöjligheter
- **Butiksrekommendationer** - hitta de mest kostnadseffektiva butikerna
- **Potentiella besparingar** - beräknar hur mycket du kan spara

### Funktioner
- **Prishistorikanalys** - 30 dagars trendanalys för exakt prisförutsägelse
- **Butiksjämförelser** - jämför priser mellan butiker för samma produkt
- **Prisvarningar** - få meddelanden när priser sjunker över 10%
- **Säsongsanalys** - upptäck säsongsvariation i priser
- **Individuella rekommendationer** - anpassade tips baserat på dina inköpsvanor

## 📋 Inköpslistor

### Listhantering
- **Skapa inköpslistor** - organisera dina inköp med anpassningsbara listor
- **Produktsökning** - lägg till produkter från din befintliga produktdatabas
- **Mängdhantering** - ange kvantitet och enheter för varje vara
- **Prisuppskattningar** - automatiska prisförslag baserat på historik
- **Listdelning** - dela listor med familjemedlemmar
- **Kategoriindelning** - sortera produkter efter kategori

### Smart integration
- **Prisrekommendationer** - få förslag på bästa butiker för din lista
- **Besparingsberäkning** - se potentiella besparingar före handlandet
- **Rutt-optimering** - förslag på mest effektiv inköpsrutt
- **Kampanjintegration** - automatisk uppdatering när kampanjer upptäcks

## 📊 Prishistorik och analytics

### Prishistorik
- **Automatisk spårning** - alla prisändringar lagras automatiskt
- **Grafisk presentation** - interaktiva charts med MPAndroidChart
- **Trendanalys** - upptäck prismönster och säsongsvariation
- **Historisk jämförelse** - jämför nuvarande priser med historiska data
- **Exportfunktioner** - exportera prisdata för vidare analys

### 🏷️ Kategorihantering

### Avancerat kategorisystem
- **Visuell kategorihantering** - färgade ikoner och intuitivt gränssnitt
- **Inkomst- och utgiftskategorier** - separata kategorier för olika transaktionstyper
- **Anpassningsbara ikoner** - över 28 ikoner att välja mellan (mat, transport, löner, etc.)
- **Färgkodning** - 20 vibrant färger för enkel identifiering
- **Tobak- och dryckeskategorier** - specialikoner för specifika utgiftstyper
- **Sökfunktionalitet** - hitta kategorier snabbt med sökfunktion
- **Lägg till/redigera/ta bort** - fullständig CRUD-funktionalitet
- **Transaktionsräkning** - spåra hur många transaktioner per kategori
- **Subkategorier** - stöd för hierarkiska kategorier (planerad funktion)

### Ikoner som inkluderas
- **Utgiftskategorier**: Mat, transport, shopping, underhållning, räkningar, hem, hälsa, teknik, sport, bil, bensin, kläder, utbildning, kaffe, presenter, husdjur, resor, skönhet, apotek, butik, tobak, drycker
- **Inkomstkategorier**: Lön, bonus, frilans, investeringar, försäljning, kalendrar, pengar

### 🧾 Kvittohantering

### Kvittoscanning
- **Digital kvittolagring** - lagra och organisera kvitton digitalt
- **Butiksinformation** - automatisk koppling till butiksdatabas
- **Produktmatchning** - koppla kvittoinköp till befintliga produkter
- **Utgiftsregistrering** - automatisk registrering av utgifter från kvitton
- **OCR-integration** - textigenkänning för automatisk dataextraktion

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
- **Prishistoriksystem** - automatisk spårning av alla prisändringar
- **Inköpslistesystem** - komplett listhantering med kvantiteter
- **Kvittosystem** - digital lagring med butikskoppling
- **Rekommendationssystem** - AI-driven prisanalys
- **Relationshantering** mellan produkter, butiker och kategorier
- **Sökfunktioner** baserat på namn och varumärke
- **Databasmigration** från version 2 till 11+

## Teknisk specifikation

- **Språk**: Kotlin
- **UI Framework**: Material Design 3
- **Navigation**: Bottom Navigation Bar med fragments
- **MinSDK**: 24 (Android 7.0)
- **TargetSDK**: 34
- **Architecture**: Fragment-based navigation med MVVM-pattern
- **Databas**: Room (SQLite) version 11+
- **Kamera**: CameraX med ML Kit för streckkodscanning
- **Charts**: MPAndroidChart för prishistorikgrafer  
- **AI-analys**: Anpassad smart rekommendationsalgoritm

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
│   │   ├── CategoryManagerActivity.kt
│   │   ├── AddEditCategoryActivity.kt
│   │   ├── EditHomeActivity.kt
│   │   ├── StoreManagerActivity.kt
│   │   ├── SmartRecommendationsActivity.kt
│   │   ├── ShoppingListActivity.kt
│   │   ├── ShoppingListDetailActivity.kt
│   │   ├── PriceHistoryActivity.kt
│   │   ├── ProductAdapter.kt
│   │   ├── LoanAdapter.kt
│   │   ├── TransactionAdapter.kt
│   │   ├── ProductWithPricesAdapter.kt
│   │   ├── ProductPriceAdapter.kt
│   │   ├── adapters/
│   │   │   ├── CategoryManagerAdapter.kt
│   │   │   ├── CategoryIconAdapter.kt
│   │   │   ├── ColorPickerAdapter.kt
│   │   │   ├── SmartSuggestionAdapter.kt
│   │   │   ├── RecommendationAdapter.kt
│   │   │   ├── ShoppingListAdapter.kt
│   │   │   └── ShoppingListItemAdapter.kt
│   │   ├── fragments/
│   │   │   ├── OverviewFragment.kt
│   │   │   ├── TransactionsFragment.kt
│   │   │   ├── LoansFragment.kt
│   │   │   ├── BudgetFragment.kt
│   │   │   ├── MoreFragment.kt
│   │   │   └── SettingsFragment.kt
│   │   ├── services/
│   │   │   └── SmartRecommendationService.kt
│   │   ├── repository/
│   │   │   ├── BudgetRepository.kt
│   │   │   └── ShoppingListRepository.kt
│   │   ├── viewmodels/
│   │   │   ├── CategoryViewModel.kt
│   │   │   ├── ShoppingListViewModel.kt
│   │   │   └── ShoppingListViewModelFactory.kt
│   │   ├── utils/
│   │   │   └── CategoryConstants.kt
│   │   ├── data/
│   │   │   ├── ProductWithPrices.kt
│   │   │   ├── PriceRecommendation.kt
│   │   │   └── BackupData.kt
│   │   └── database/
│   │       ├── BudgetDatabase.kt
│   │       ├── entities/
│   │       │   ├── Product.kt
│   │       │   ├── Category.kt
│   │       │   ├── Store.kt
│   │       │   ├── ProductStore.kt
│   │       │   ├── Income.kt
│   │       │   ├── Expense.kt
│   │       │   ├── Loan.kt
│   │       │   ├── PriceHistory.kt
│   │       │   ├── ShoppingList.kt
│   │       │   ├── ShoppingListItem.kt
│   │       │   ├── Receipt.kt
│   │       │   ├── ReceiptItem.kt
│   │       │   └── Budget.kt
│   │       └── dao/
│   │           ├── ProductDao.kt
│   │           ├── CategoryDao.kt
│   │           ├── StoreDao.kt
│   │           ├── ProductStoreDao.kt
│   │           ├── IncomeDao.kt
│   │           ├── ExpenseDao.kt
│   │           ├── LoanDao.kt
│   │           ├── PriceHistoryDao.kt
│   │           ├── ShoppingListDao.kt
│   │           ├── ShoppingListItemDao.kt
│   │           ├── ReceiptDao.kt
│   │           ├── ReceiptItemDao.kt
│   │           └── BudgetDao.kt
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
- **color**: Färgkod (hex-format)
- **iconResName**: Ikonresursnamn (t.ex. "ic_dining")
- **type**: Kategoritype (EXPENSE/INCOME enum)
- **parentCategoryId**: Foreign key för subkategorier (valfri)
- **isSubcategory**: Boolean för subkategori-status
- **associatedKeywords**: Sökord för automatisk kategorisering
- **transactionCount**: Antal transaktioner i kategorin
- **isActive**: Boolean för aktiv status
- **sortOrder**: Sorteringsordning
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

### PriceHistory Table
- **id**: Primary key
- **productId**: Foreign key till Product
- **storeId**: Foreign key till Store
- **price**: Registrerat pris
- **recordedAt**: Tidsstämpel för prisregistrering
- **createdAt**: Skapad-tidsstämpel

### ShoppingList Table
- **id**: Primary key
- **name**: Listnamn
- **description**: Beskrivning (valfri)
- **isCompleted**: Boolean för slutförd status
- **totalEstimatedCost**: Uppskattad total kostnad
- **totalActualCost**: Faktisk total kostnad
- **completedAt**: Slutfört datum (valfri)
- **createdAt/updatedAt**: Tidsstämplar

### ShoppingListItem Table
- **id**: Primary key  
- **shoppingListId**: Foreign key till ShoppingList
- **productId**: Foreign key till Product (valfri)
- **customItemName**: Anpassat produktnamn (valfri)
- **quantity**: Kvantitet
- **unit**: Enhet (st, kg, l, etc.)
- **estimatedPrice**: Uppskattat pris
- **actualPrice**: Faktiskt pris (valfri)
- **notes**: Anteckningar (valfri)
- **isCompleted**: Boolean för slutförd status
- **priority**: Prioritetsnivå (-1=låg, 0=normal, 1=hög)
- **createdAt/updatedAt**: Tidsstämplar
- **completedAt**: Slutfört datum (valfri)

### Receipt Table
- **id**: Primary key
- **storeId**: Foreign key till Store
- **totalAmount**: Total belopp
- **receiptDate**: Kvittodatum  
- **receiptNumber**: Kvittonummer (valfri)
- **paymentMethod**: Betalmetod
- **notes**: Anteckningar (valfri)
- **createdAt/updatedAt**: Tidsstämplar

### ReceiptItem Table  
- **id**: Primary key
- **receiptId**: Foreign key till Receipt
- **productId**: Foreign key till Product (valfri)
- **itemName**: Produktnamn
- **quantity**: Kvantitet
- **unitPrice**: Enhetspris
- **totalPrice**: Totalpris
- **category**: Produktkategori (valfri)
- **createdAt**: Skapad-tidsstämpel

### Budget Table
- **id**: Primary key
- **name**: Budgetnamn
- **description**: Beskrivning (valfri)
- **budgetType**: Budgettyp (MONTHLY, WEEKLY, YEARLY)
- **totalBudget**: Total budgetgräns
- **spentAmount**: Använt belopp
- **remainingAmount**: Återstående belopp
- **period**: Budgetperiod
- **categoryId**: Foreign key till Category (valfri)
- **isActive**: Boolean för aktiv status
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
12. **Hantera kategorier**: Mer-fliken → "Kategorier" → Lägg till/redigera kategorier → Välj ikoner och färger
13. **Smart rekommendationer**: Mer-fliken → "Smarta tips" → Se prisanalys och besparingsförslag
14. **Inköpslistor**: Mer-fliken → "Inköpslistor" → Skapa listor → Lägg till produkter → Få prisrekommendationer
15. **Prishistorik**: Produktlista → "Visa prishistorik" → Se prisutveckling över tid

## Nästa steg

Appen är nu komplett med avancerade funktioner. Möjliga förbättringar:

1. **Butikskartor** - Google Maps integration för att hitta närmaste butiker ⭐
2. **Dataexport**: Exportera prisdata och rekommendationer till CSV/Excel
3. **Förbättrad AI**: Maskininlärning för ännu mer exakta prisförutsägelser  
4. **Push-notifikationer**: Realtidsvarningar när priser sjunker
5. **Delning**: Dela inköpslistor och rekommendationer med familjen
6. **OCR-kvittoscanning**: Automatisk textigenkänning från kvitton
7. **Avancerad budgetering**: Automatisk kategorisering och budgetvarningar
8. **Säsongsanalys**: Förutsäg säsongspriser och optimala inköpstider
9. **Kundklubsintegration**: Synkronisering med ICA Kortet, Coop medlemskap

✅ **Implementerat**:
- Avancerat kategorisystem med visuella ikoner och färger
- Smarta rekommendationer med AI-analys
- Komplett prishistoriksystem  
- Inköpslistor med prisoptimering
- Kvittohantering
- Avancerad databas (11+ tabeller)
- Modern Material Design 3 UI

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