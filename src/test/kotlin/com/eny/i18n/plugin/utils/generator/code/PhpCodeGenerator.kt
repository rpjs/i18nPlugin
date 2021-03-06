package com.eny.i18n.plugin.utils.generator.code

class PhpCodeGenerator: CodeGenerator {

    override fun ext(): String = "php"

    override fun generate(key: String, index: Int): String = """
        <?php
            ${generateLine(key)} 
    """

    override fun multiGenerate(vararg keys: String): String = """
        <?php
            ${keys.map(::generateLine).joinToString("\\n")}
    """

    override fun generateInvalid(key: String): String = """
        <?php
        
        echo str_replace("\n", '<br>', ts($key));
    """

    private fun generateLine(key: String): String = """          echo str_replace("\n", '<br>', t($key));"""
}