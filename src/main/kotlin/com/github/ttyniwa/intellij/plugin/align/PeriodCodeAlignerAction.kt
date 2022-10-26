package com.github.ttyniwa.intellij.plugin.align

/**
 * Code aligner action with a delimiter of `.`
 */
class PeriodCodeAlignerAction : CodeAlignerActionBase() {
    override val delimiters = listOf(".")
}