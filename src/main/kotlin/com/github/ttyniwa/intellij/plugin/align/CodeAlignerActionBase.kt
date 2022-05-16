package com.github.ttyniwa.intellij.plugin.align

import com.github.ttyniwa.intellij.plugin.align.aligner.StringDelimiterAligner
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project

abstract class CodeAlignerActionBase : AnAction() {
    /**
     * Aligner.
     */
    private val delimiterAligner = StringDelimiterAligner() // TODO: IoC?

    /**
     * Delimiter to use for alignment.
     */
    protected abstract val delimiter: String

    /** Indicates whether we should align starting from the caret position. */
    protected var alignFromCaretPos = false

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.getRequiredData(PlatformDataKeys.PROJECT)
        val editor  = e.getData(PlatformDataKeys.EDITOR) ?: return
        if (!editor.document.isWritable) {
            return
        }

        val document = editor.document
        // ## for now, only support selected text alignment
        editor.selectionModel.selectedText ?: return

        val selection = editor.selectionModel
        val startPos  = editor.visualToLogicalPosition(selection.selectionStartPosition!!)
        val endPos    = editor.visualToLogicalPosition(selection.selectionEndPosition  !!)
        val startLine = startPos.line
        val endLine   = endPos.line + if (endPos.column == 0) -1 else 0

        val lineSeparator = getLineSeparator(document.text)

        // compute the new, aligned text
        val alignedText = delimiterAligner.align(
            getLines(document.text, lineSeparator, IntRange(startLine, endLine)),
            delimiter,
            if (alignFromCaretPos) endPos.column else 0
        )

        // make the edit
        replaceString(
            project,
            document,
            alignedText.joinToString(lineSeparator),
            document.getLineStartOffset(startLine),
            document.getLineEndOffset  (endLine)
        )
    }

    /**
     * Get an appropriate line separator for the given [text].
     *
     * @param text text/document to get line separator for
     * @return the line separator
     */
    private fun getLineSeparator(text: String): String {
        // # algo taken from Aligner.findLineSeparator
        return when (val newlineIdx = text.indexOf("\n")) {
            -1   -> ""
             0   -> "\n"
            else -> if (text[newlineIdx - 1] == '\r') "\r\n" else "\n"
        }
    }

    /**
     * Get a list of a document's lines.
     *
     * @param documentText document as a string
     * @param separator line separator
     * @param range **line** range
     * @return list of the document's lines
     */
    private fun getLines(documentText: String, separator: String, range: IntRange): List<String> {
        return documentText
            .split(separator)
            .slice(range)
    }

    /**
     * Replace the string in the [document] with [replaceText].
     *
     * @param project project of the document
     * @param document document object
     * @param replaceText new text to replace with
     * @param startOffset start index in the document of the string to be replaced
     * @param endOffset end index in the document of the string to be replaced
     */
    private fun replaceString(project: Project, document: Document, replaceText: String, startOffset: Int, endOffset: Int) {
        CommandProcessor.getInstance().executeCommand(project,
                {
                    ApplicationManager.getApplication().runWriteAction {
                        document.replaceString(startOffset, endOffset, replaceText)
                    }
                }, "Smart-Align", this)
    }
}
