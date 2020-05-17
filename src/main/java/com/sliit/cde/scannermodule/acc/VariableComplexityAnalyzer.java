package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.coremodule.model.VariableComplexity;
import com.sliit.cde.scannermodule.acc.enums.Scope;
import com.sliit.cde.scannermodule.acc.service.StringPatternService;

class VariableComplexityAnalyzer {
    private Scope scope = Scope.GLOBAL;
    private StringPatternService stringPatternService = new StringPatternService();
    private int scopeLevel = 0;

    void analyze(Line lineObject, String codeLine, String language) {
        VariableComplexity variableComplexity = new VariableComplexity();
        String cleanedCodeLine = stringPatternService.cleanStringLiterals(codeLine);

        scopeLevel += stringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= stringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

        if (scopeLevel == 1) {
            variableComplexity.setScopeGlobal(1);
            variableComplexity.setScopeLocal(0);
        } else if (scopeLevel > 1) {
            variableComplexity.setScopeGlobal(0);
            variableComplexity.setScopeLocal(1);
        }

        if (stringPatternService.isVariable(cleanedCodeLine) &&
                stringPatternService.isPrimitiveVariable(cleanedCodeLine)) {
            variableComplexity.setNumberOfPrimitiveDataTypeVariables(1);
        } else if (stringPatternService.isVariable(cleanedCodeLine) &&
                stringPatternService.isCompositeVariable(cleanedCodeLine)) {
            variableComplexity.setNumberOfCompositeDataTypeVariables(1);
        }

        lineObject.setVariableComplexity(variableComplexity);
    }
}
