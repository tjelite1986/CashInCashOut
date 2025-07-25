package com.example.budgetapp.data

enum class AccentColor(val displayName: String, val colorValue: String) {
    SYSTEM("Use System Color", "system"),
    GREEN("Green", "#4CAF50"),
    BLUE("Blue", "#2196F3"),
    PURPLE("Purple", "#9C27B0"),
    RED("Red", "#F44336"),
    ORANGE("Orange", "#FF9800"),
    TEAL("Teal", "#009688"),
    INDIGO("Indigo", "#3F51B5"),
    PINK("Pink", "#E91E63"),
    BROWN("Brown", "#795548")
}

enum class ThemeMode(val displayName: String) {
    LIGHT("Light"),
    DARK("Dark"),
    SYSTEM("System")
}

enum class InterfaceStyle(val displayName: String) {
    MATERIAL("Material"),
    COLORFUL("Colorful & Expressive")
}

data class ThemeSettings(
    val accentColor: AccentColor = AccentColor.SYSTEM,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val interfaceStyle: InterfaceStyle = InterfaceStyle.MATERIAL
)