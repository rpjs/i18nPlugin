package com.eny.i18n.plugin.utils

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.openapi.util.TextRange

interface AnnotationFacade {
    fun compositeKeyFullBounds(fullKey: FullKey): TextRange
    fun unresolvedKey(fullKey: FullKey, resolvedPath: List<Literal>): TextRange
    fun unresolvedNs(fullKey: FullKey): TextRange
}

class AnnotationHolderFacade(val annotationHolder: AnnotationHolder?, val textRange: TextRange) : AnnotationFacade {

    override fun unresolvedNs(fullKey: FullKey): TextRange =
        safeRange(
            textRange.startOffset + 1,
            textRange.startOffset + 1 + (fullKey.ns?.length ?: 0)
        )

    override fun unresolvedKey(fullKey: FullKey, resolvedPath: List<Literal>): TextRange =
        safeRange(
            fullKey.compositeKeyStartOffset() + tokensLength(resolvedPath) + (if (resolvedPath.size > 0) 1 else 0),
            textRange.endOffset - 1
        )

    override fun compositeKeyFullBounds(fullKey: FullKey) =
        safeRange(
            fullKey.compositeKeyStartOffset(),
            textRange.endOffset - 1
        )

    private fun FullKey.compositeKeyStartOffset(): Int =
        textRange.startOffset + (this.ns?.let { text -> text.length + 1 } ?: 0) + 1

    private fun safeRange(start: Int, end: Int): TextRange = TextRange(start, Math.max(end, start))
}