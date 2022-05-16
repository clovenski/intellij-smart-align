package com.github.ttyniwa.intellij.plugin.align.aligner

/**
 * Align list of strings according to a string delimiter.
 *
 * @constructor Create empty String delimiter aligner
 */
class StringDelimiterAligner {
    /**
     * Align [lines] by the [delimiter].
     *
     * Ex. align a list of assignment statements by the `=` delimiter.
     *
     * @param lines lines of text to align
     * @param delimiter delimiter for the alignment
     * @param minIdx minimum index to search for the delimiter
     * @return the aligned lines
     */
    fun align(lines: List<String>, delimiter: String, minIdx: Int = 0): List<String> {
        if (lines.isEmpty()) return listOf()

        // 1. run through lines to map to indexOf delimiter
        val delimiterIndexes = lines.map { LineDetails(it, it.indexOf(delimiter, minIdx)) }

        // 2. get max indexOf between the lines
        val targetIdx = delimiterIndexes.maxBy { it.delimiterIdx }!!.delimiterIdx

        // 3. add the spaces to each line wherever necessary
        return alignLines(delimiterIndexes, targetIdx)
    }

    /**
     * Align [lines] by adding spaces to them at their delimiter index
     * so that it aligns with the [targetIdx].
     *
     * Target index **must** be greater or equal to the delimiter indexes.
     *
     * @param lines
     * @param targetIdx
     * @return the aligned lines
     */
    private fun alignLines(lines: List<LineDetails>, targetIdx: Int): List<String> {
        // # maybe should consider tabs or spaces when adding to the line?
        return lines.map {
            if (it.delimiterIdx == -1) return@map it.text

            StringBuilder(it.text).insert(it.delimiterIdx, " ".repeat(targetIdx - it.delimiterIdx)).toString()
        }
    }
}