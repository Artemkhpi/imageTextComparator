package ua.khpi.it.comparison.model

data class Report(
        val images: MutableMap<String, ImageReport> = mutableMapOf()
)