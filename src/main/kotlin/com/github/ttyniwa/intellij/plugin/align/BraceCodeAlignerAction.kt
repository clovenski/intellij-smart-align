package com.github.ttyniwa.intellij.plugin.align

/**
 * Code aligner action with a delimiter of `{` and `}`.
 */
class BraceCodeAlignerAction : CodeAlignerActionBase() {
    override val delimiters = listOf("{", "}")
}