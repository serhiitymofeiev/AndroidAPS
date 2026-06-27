package app.aaps.plugins.main.general.actions

enum class LongInsulinType(val displayName: String) {
    FLAT("Линейный (Идеальный)"),
    TOUJEO("Toujeo (Тожео)"),
    LEVEMIR("Levemir (Левемир)"),
    LANTUS("Lantus (Лантус)")
}

object InsulinCurves {
    fun getCurve(type: LongInsulinType): DoubleArray {
        return when (type) {
            LongInsulinType.FLAT -> DoubleArray(24) { 1.0 / 24.0 }

            LongInsulinType.TOUJEO -> doubleArrayOf(
                0.015, 0.020, 0.025, 0.030, 0.035, // Часы 0-4 (разгон)
                0.040, 0.045, 0.050, 0.050, 0.050, // Часы 5-9
                0.050, 0.055, 0.055, 0.055, 0.050, // Часы 10-14 (пик)
                0.050, 0.045, 0.045, 0.040, 0.040, // Часы 15-19
                0.040, 0.035, 0.035, 0.030         // Часы 20-23 (спад)
            )

            LongInsulinType.LEVEMIR -> doubleArrayOf(
                0.02, 0.04, 0.06, 0.08, 0.10, 0.12, 0.12, 0.10,
                0.08, 0.07, 0.06, 0.05, 0.04, 0.03, 0.02, 0.01,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
            )

            LongInsulinType.LANTUS -> doubleArrayOf(
                0.02, 0.03, 0.04, 0.04, 0.05, 0.05, 0.05, 0.05,
                0.05, 0.05, 0.05, 0.05, 0.04, 0.04, 0.04, 0.04,
                0.04, 0.04, 0.03, 0.03, 0.03, 0.03, 0.03, 0.02
            )
        }
    }
}