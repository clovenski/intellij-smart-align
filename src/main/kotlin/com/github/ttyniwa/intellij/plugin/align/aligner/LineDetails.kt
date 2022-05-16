package com.github.ttyniwa.intellij.plugin.align.aligner

/**
 * Line details
 *
 * @property text string of the line
 * @property delimiterIdx index in the line that contains the delimiter
 * @constructor Create empty Line details
 */
data class LineDetails(val text: String, val delimiterIdx: Int)