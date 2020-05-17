package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.coremodule.model.VariableComplexity;
import com.sliit.cde.scannermodule.acc.enums.Scope;
import com.sliit.cde.scannermodule.acc.service.JavaLanguageStringPatternService;

class VariableComplexityAnalyzer {
    private Scope scope = Scope.GLOBAL;
    private JavaLanguageStringPatternService javaLanguageStringPatternService = new JavaLanguageStringPatternService();
    private int scopeLevel = 0;

    void analyze(Line lineObject, String codeLine, String language) {
        VariableComplexity variableComplexity = new VariableComplexity();
        String cleanedCodeLine = javaLanguageStringPatternService.cleanStringLiterals(codeLine);

        scopeLevel += javaLanguageStringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= javaLanguageStringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

        if (scopeLevel == 1) {
            variableComplexity.setScopeGlobal(1);
            variableComplexity.setScopeLocal(0);
        } else if (scopeLevel > 1) {
            variableComplexity.setScopeGlobal(0);
            variableComplexity.setScopeLocal(1);
        }

        if (javaLanguageStringPatternService.isVariable(cleanedCodeLine) &&
                javaLanguageStringPatternService.isPrimitiveVariable(cleanedCodeLine)) {
            variableComplexity.setNumberOfPrimitiveDataTypeVariables(1);
        } else if (javaLanguageStringPatternService.isVariable(cleanedCodeLine) &&
                javaLanguageStringPatternService.isCompositeVariable(cleanedCodeLine)) {
            variableComplexity.setNumberOfCompositeDataTypeVariables(1);
        }

        lineObject.setVariableComplexity(variableComplexity);
    }
}
