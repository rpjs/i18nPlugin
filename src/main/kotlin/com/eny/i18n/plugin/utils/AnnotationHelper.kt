package com.eny.i18n.plugin.utils

import com.eny.i18n.plugin.ide.quickfix.AllFilesSelector
import com.eny.i18n.plugin.ide.quickfix.CreateJsonFileQuickFix
import com.eny.i18n.plugin.ide.quickfix.CreatePropertyQuickFix
import com.eny.i18n.plugin.ide.quickfix.UserChoice
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors

/**
 * Annotation helper methods
 */
class AnnotationHelper(val holder: AnnotationHolder, val facade: AnnotationFacade) {
    private val RESOLVED_COLOR = DefaultLanguageHighlighterColors.LINE_COMMENT
    fun annotateResolved(fullKey: FullKey) {
        holder.createInfoAnnotation(facade.compositeKeyFullBounds(fullKey), null).textAttributes = RESOLVED_COLOR
    }
    fun annotateReferenceToJson(fullKey: FullKey) {
        holder.createErrorAnnotation(facade.compositeKeyFullBounds(fullKey), "Reference to Json object")
    }
    fun annotateReferenceToPlural(fullKey: FullKey) {
        holder.createInfoAnnotation(facade.compositeKeyFullBounds(fullKey),"Reference to plural value").textAttributes = RESOLVED_COLOR
    }
    fun annotateUnresolved(fullKey: FullKey, resolvedPath: List<Literal>) {
        val unresolvedPropertyAnnotation = holder.createErrorAnnotation(facade.unresolvedKey(fullKey, resolvedPath), "Unresolved property")
        unresolvedPropertyAnnotation.registerFix(
            CreatePropertyQuickFix(fullKey, UserChoice(), "Create property"))
        unresolvedPropertyAnnotation.registerFix(
            CreatePropertyQuickFix(fullKey, AllFilesSelector(), "Create property in all localization files"))
    }
    fun annotatePartiallyResolved(fullKey: FullKey, resolvedPath: List<Literal>) {
        holder.createInfoAnnotation(facade.unresolvedKey(fullKey, resolvedPath), "Partially resolved").textAttributes = RESOLVED_COLOR
    }
    fun annotateFileUnresolved(fullKey: FullKey) {
        if (fullKey.ns == null) {
            holder.createErrorAnnotation(facade.unresolvedKey(fullKey, listOf()), "Missing default namespace Json").registerFix(CreateJsonFileQuickFix(fullKey))
        } else {
            holder.createErrorAnnotation(facade.unresolvedNs(fullKey), "Unresolved file").registerFix(CreateJsonFileQuickFix(fullKey))
        }
    }
    fun annotateUnresolvedVueKey(fullKey: FullKey) {
        holder.createErrorAnnotation(facade.unresolvedKey(fullKey, listOf()), "Missing localization").registerFix(CreateJsonFileQuickFix(fullKey, true))
    }
}