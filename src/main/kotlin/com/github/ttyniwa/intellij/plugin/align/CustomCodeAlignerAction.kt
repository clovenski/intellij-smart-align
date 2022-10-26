package com.github.ttyniwa.intellij.plugin.align

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.DialogWrapper
import java.awt.GridLayout
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

/**
 * Action handler for custom code alignment (custom delimiter).
 *
 * @property caretPosCbSelected whether the caret position checkbox is initially selected on render
 */
open class CustomCodeAlignerAction(private val caretPosCbSelected: Boolean = false) : CodeAlignerActionBase() {

    /** Custom delimiter to use. */
    private var customDelimiter: String = ""

    override val delimiters: Iterable<String>
        get() = listOf(customDelimiter)

    override fun actionPerformed(e: AnActionEvent) {
        // require selected text, otherwise nothing to do
        e.getData(PlatformDataKeys.EDITOR)?.selectionModel?.selectedText
            ?: return

        val dialog = AlignByCustomStringDialogWrapper(caretPosCbSelected)
        if (dialog.showAndGet()) {
            customDelimiter = dialog.getCustomDelimiter()

            if (customDelimiter.isEmpty()) return

            alignFromCaretPos = dialog.getAlignFromCaretPos()

            // run super method to do the alignment workflow with the newly set delimiter
            super.actionPerformed(e)
        }
    }
}

// # move class to another file?
/**
 * Dialog wrapper specific for code alignment with a custom delimiter.
 *
 * @property caretPosCbSelected whether the caret position checkbox is initially selected on render
 */
class AlignByCustomStringDialogWrapper(private val caretPosCbSelected: Boolean = false) : DialogWrapper(true) {
    init {
        title = "Enter string to align by"
        init()
        setResizable(false)
    }

    /** Text field for the custom delimiter input. */
    private lateinit var customDelimiterInput: JTextField

    /** Checkbox for indicating whether to align starting from the caret position. */
    private lateinit var caretPosCb: JCheckBox

    override fun createCenterPanel(): JComponent? {
        val panel = JPanel(GridLayout(2,1)) // # any way to make this a dynamic layout?
        panel.setSize(350, 150) // # somehow not working

        // input for the custom delimiter
        customDelimiterInput = JTextField()
        panel.add(customDelimiterInput)

        // checkbox for whether to align from the caret position
        caretPosCb = JCheckBox("Align from caret position", caretPosCbSelected)
        panel.add(caretPosCb)

        return panel
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return customDelimiterInput
    }

    /**
     * Get custom delimiter input from user.
     *
     * @return custom delimiter
     */
    fun getCustomDelimiter(): String = customDelimiterInput.text

    /**
     * Get whether user wants to align starting from the caret position.
     *
     * @return true if we should align from caret position, otherwise false
     */
    fun getAlignFromCaretPos(): Boolean = caretPosCb.isSelected
}
