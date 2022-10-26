package com.github.ttyniwa.intellij.plugin.align

/**
 * Action handler for custom code alignment (custom delimiter),
 * with the option selected to align from caret position.
 */
class CaretCodeAlignerAction : CustomCodeAlignerAction(caretPosCbSelected = true)