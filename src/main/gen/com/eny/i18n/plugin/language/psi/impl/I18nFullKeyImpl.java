// This is a generated file. Not intended for manual editing.
package com.eny.i18n.plugin.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.eny.i18n.plugin.language.psi.I18nTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.eny.i18n.plugin.language.psi.*;

public class I18nFullKeyImpl extends ASTWrapperPsiElement implements I18nFullKey {

  public I18nFullKeyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull I18nVisitor visitor) {
    visitor.visitFullKey(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof I18nVisitor) accept((I18nVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public I18nCompositeKey getCompositeKey() {
    return findNotNullChildByClass(I18nCompositeKey.class);
  }

  @Override
  @NotNull
  public I18nNamespace getNamespace() {
    return findNotNullChildByClass(I18nNamespace.class);
  }

}