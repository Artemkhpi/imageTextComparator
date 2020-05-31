package ua.khpi.it.comparison.db.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter


@Converter
class ListToStringConverter : AttributeConverter<List<Long>, String> {
    override fun convertToDatabaseColumn(stringList: List<Long>): String {
        return stringList.joinToString(separator = SPLIT_CHAR)
    }

    override fun convertToEntityAttribute(value: String): List<Long> {
        return if (value.isNotBlank()) {
            value.split(SPLIT_CHAR).map { it.toLong() }.toCollection(mutableListOf())
        } else {
            mutableListOf()
        }
    }

    companion object {
        private const val SPLIT_CHAR = ";"
    }
}