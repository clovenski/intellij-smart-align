package com.github.ttyniwa.intellij.plugin.align

/**
 * Code aligner action with a delimiter of `=`.
 *
 * @constructor Create empty Assignment code aligner action
 */
class AssignmentCodeAlignerAction : CodeAlignerActionBase() {
    override val delimiter = "="
}