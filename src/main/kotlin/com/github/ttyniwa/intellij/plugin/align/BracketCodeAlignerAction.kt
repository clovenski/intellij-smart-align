package com.github.ttyniwa.intellij.plugin.align

/**
 * Code aligner action with a delimiter of `[` and `]`.
 */
class BracketCodeAlignerAction : CodeAlignerActionBase() {
    override val delimiters = listOf("[", "]")
}