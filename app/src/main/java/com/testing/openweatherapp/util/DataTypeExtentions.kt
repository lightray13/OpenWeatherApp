package com.testing.openweatherapp.util

fun String?.emptyIfNull(): String {
    return this ?: ""
}

fun Double?.tempString(): String {
    return this?.let {
        val number = this.toInt()
        "$number °C"
    } ?: "-"
}

fun Int?.humidityString(): String {
    return this?.let {
        "$this %"
    } ?: "-"
}

fun Int?.pressureString(): String {
    return this?.let {
        "$this mmHg"
    } ?: "-"
}

fun Double?.windString(): String {
    return this?.let {
        "$this m/s"
    } ?: "-"
}

fun Int?.windDirectionString(): String {
    return this?.let {
        if (it > 337) return "Northerly"
        if (it > 292) return "North Westerly"
        if (it > 247) return "Westerly"
        if (it > 202) return "South Westerly"
        if (it > 157) return "Southerly"
        if (it > 122) return "South Easterly"
        if (it > 67) return "Easterly"
        if (it > 22) return "North Easterly"
        return "-"
    } ?: "-"
}