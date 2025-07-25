# Ikoner i Budget App

Detta dokument listar alla ikoner som används i Budget App's kategorisystem.

## 📋 Översikt

**Totalt antal ikoner**: 28 st  
**Utgiftskategorier**: 22 st  
**Inkomstkategorier**: 6 st  

## 💸 Utgiftskategorier (22 ikoner)

| Ikonnamn | Filnamn | Beskrivning | Kategori |
|----------|---------|-------------|----------|
| 🍽️ Dining | `ic_dining.xml` | Restauranger och utemat | Mat & Dryck |
| 🛒 Groceries | `ic_groceries.xml` | Matinköp och dagligvaror | Mat & Dryck |
| 🛍️ Shopping | `ic_shopping.xml` | Allmän shopping och inköp | Shopping |
| 🚌 Transit | `ic_transit.xml` | Kollektivtrafik och transport | Transport |
| 🎬 Entertainment | `ic_entertainment.xml` | Nöje och underhållning | Fritid |
| 📄 Bills | `ic_bills.xml` | Räkningar och avgifter | Ekonomi |
| 🏠 Home | `ic_home.xml` | Boende och hem | Boende |
| 🏥 Health | `ic_health.xml` | Hälsa och sjukvård | Hälsa |
| 💻 Technology | `ic_tech.xml` | Teknik och elektronik | Teknik |
| ⚽ Sports | `ic_sport.xml` | Sport och träning | Fritid |
| 🚗 Car | `ic_car.xml` | Bil och fordon | Transport |
| ⛽ Gas | `ic_gas.xml` | Bensin och bränsle | Transport |
| 👕 Clothes | `ic_clothes.xml` | Kläder och mode | Shopping |
| 📚 Education | `ic_education.xml` | Utbildning och kurser | Utbildning |
| ☕ Coffee | `ic_coffee.xml` | Kaffe och café | Mat & Dryck |
| 🎁 Gifts | `ic_gift.xml` | Presenter och gåvor | Shopping |
| 🐕 Pets | `ic_pets.xml` | Husdjur och djurvård | Husdjur |
| ✈️ Travel | `ic_travel.xml` | Resor och semester | Resor |
| 💄 Beauty | `ic_beauty.xml` | Skönhet och kosmetika | Personlig vård |
| 💊 Pharmacy | `ic_pharmacy.xml` | Apotek och mediciner | Hälsa |
| 🏪 Store | `ic_store.xml` | Butiker och handel | Shopping |
| 🚬 Tobacco | `ic_tobacco.xml` | Tobak och cigaretter | Laster |
| 🍸 Drinks | `ic_drinks.xml` | Alkohol och cocktails | Mat & Dryck |

## 💰 Inkomstkategorier (6 ikoner)

| Ikonnamn | Filnamn | Beskrivning | Kategori |
|----------|---------|-------------|----------|
| 💼 Salary | `ic_salary.xml` | Lön och fast inkomst | Arbete |
| 🎉 Bonus | `ic_bonus.xml` | Bonus och extra ersättning | Arbete |
| 💻 Freelance | `ic_freelance.xml` | Frilansarbete och uppdrag | Arbete |
| 📈 Investment | `ic_investment.xml` | Investeringar och utdelningar | Investering |
| 💳 Sale | `ic_sale.xml` | Försäljning och handel | Försäljning |
| 💵 Money | `ic_money.xml` | Allmän inkomst | Allmänt |

## 🎨 Tilläggsinformation

### Används även (men inte för kategorier):
| Ikonnamn | Filnamn | Beskrivning | Användning |
|----------|---------|-------------|------------|
| 📅 Calendar | `ic_calendar.xml` | Kalender och datum | Allmän UI |
| ❓ Other | `ic_other.xml` | Övriga och okategoriserade | Fallback |

### Standard fallback:
- **ic_category_default** - Används när ingen specifik ikon hittas

## 🔧 Teknisk information

### Ikonformat:
- **Format**: Android Vector Drawable (XML)
- **Storlek**: 24dp x 24dp
- **ViewportWidth**: 24
- **ViewportHeight**: 24
- **Tint**: `?attr/colorOnSurface` (följer appens tema)

### Färganpassning:
- Ikonerna använder `android:tint="?attr/colorOnSurface"`
- Bakgrundsfärger definieras i CategoryConstants.kt
- 20 olika färger tillgängliga för kategorier

### Filsökväg:
```
app/src/main/res/drawable/
├── ic_dining.xml
├── ic_groceries.xml
├── ic_shopping.xml
├── ic_transit.xml
├── ic_entertainment.xml
├── ic_bills.xml
├── ic_home.xml
├── ic_health.xml
├── ic_tech.xml
├── ic_sport.xml
├── ic_car.xml
├── ic_gas.xml
├── ic_clothes.xml
├── ic_education.xml
├── ic_coffee.xml
├── ic_gift.xml
├── ic_pets.xml
├── ic_travel.xml
├── ic_beauty.xml
├── ic_pharmacy.xml
├── ic_store.xml
├── ic_tobacco.xml
├── ic_drinks.xml
├── ic_salary.xml
├── ic_bonus.xml
├── ic_freelance.xml
├── ic_investment.xml
├── ic_sale.xml
├── ic_money.xml
├── ic_calendar.xml
├── ic_other.xml
└── ic_category_default.xml
```

## 📝 Hur man lägger till nya ikoner

1. **Skapa ikonefil**: Lägg till ny XML-fil i `/res/drawable/`
2. **Uppdatera CategoryConstants.kt**: Lägg till ikonen i `CATEGORY_ICONS` map
3. **Uppdatera adapters**: Lägg till mapping i alla tre adapters:
   - `AddEditCategoryActivity.kt` → `getIconResource()` 
   - `CategoryIconAdapter.kt` → `getIconResource()`
   - `CategoryManagerAdapter.kt` → `getCategoryIconResource()`

## 🧭 Bottom Navigation Ikoner

Appen använder följande **5 ikoner** för bottom navigation:

| Position | Tab | Ikon | Filnamn | Beskrivning |
|----------|-----|------|---------|-------------|
| 1 | **Översikt** | 📊 | `ic_overview.xml` | Dashboard och hemskärm |
| 2 | **Transaktioner** | 💸 | `ic_expenses.xml` | Inkomster och utgifter |
| 3 | **Lån** | 💳 | `ic_loans.xml` | Lånehantering |
| 4 | **Budget** | 📋 | `ic_budget.xml` | Budgetplanering |
| 5 | **Mer** | ⋯ | `ic_menu_dots.xml` | Inställningar och mer |

**Observationer:**
- `ic_expenses.xml` används för "Transaktioner" (även om namnet säger "expenses")
- `ic_loans.xml` finns som egen ikon för lånfliken
- `ic_menu_dots.xml` används för "Mer"-fliken (tre punkter)
- Dessa ikoner är **separata** från kategori-ikonerna

## 🛠️ Övriga UI-ikoner

Utöver kategori- och navigationsikoner finns följande UI-ikoner:

### Allmänna åtgärder:
- `ic_add.xml` - Lägg till knapp
- `ic_edit.xml` - Redigera
- `ic_delete.xml` - Ta bort
- `ic_save.xml` - Spara
- `ic_search.xml` - Sök
- `ic_refresh.xml` - Uppdatera
- `ic_arrow_back.xml` - Tillbaka

### Specialfunktioner:
- `ic_add_product.xml` - Lägg till produkt
- `ic_filter.xml` - Filtrera
- `ic_statistics.xml` - Statistik
- `ic_bar_chart.xml` - Diagram
- `ic_notifications.xml` - Notifikationer
- `ic_settings.xml` - Inställningar

### Status och feedback:
- `ic_check.xml` - Bekräftelse
- `ic_warning.xml` - Varning
- `ic_info.xml` - Information
- `ic_empty_state.xml` - Tom vy
- `ic_star.xml` - Stjärna/favorit
- `ic_thumb_up.xml` - Gillande

### Smarta rekommendationer:
- `ic_optimization.xml` - Optimering
- `ic_price_alert.xml` - Prisvarning
- `ic_tips.xml` - Tips
- `ic_seasonal.xml` - Säsong
- `ic_trending_up.xml` - Uppåtgående trend
- `ic_trending_down.xml` - Nedåtgående trend

## 🎯 Förslag på nya ikoner

Om du vill utöka ikonsamlingen, här är några förslag:

### Utgifter:
- `ic_insurance` - Försäkringar
- `ic_subscription` - Prenumerationer
- `ic_charity` - Välgörenhet
- `ic_tools` - Verktyg och reparationer
- `ic_garden` - Trädgård och växter

### Inkomster:
- `ic_pension` - Pension
- `ic_rental` - Hyresintäkter
- `ic_refund` - Återbetalningar
- `ic_lottery` - Vinster och tävlingar
- `ic_royalty` - Royalties och licenser