package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.coremodule.model.MethodComplexity;
import com.sliit.cde.scannermodule.acc.enums.Scope;
import com.sliit.cde.scannermodule.acc.service.StringPatternService;

class MethodComplexityAnalyzer {
    private Scope scope = Scope.GLOBAL;
    private StringPatternService stringPatternService = new StringPatternService();
    private int scopeLevel = 0;

    void analyze(Line lineObject, String codeLine, String language) {
        MethodComplexity methodComplexity = new MethodComplexity();
        String cleanedCodeLine = stringPatternService.cleanStringLiterals(codeLine);
        System.out.println(codeLine);
        if (scopeLevel == 1 && stringPatternService.isMethod(cleanedCodeLine)) {
            String retType = stringPatternService.getReturnVariableTypeOfMethod(cleanedCodeLine);

            boolean isPrimitiveReturnType = stringPatternService.isPrimitiveDataType(retType);
            boolean isVoidReturnType = stringPatternService.isVoidDataType(retType);

            if (isPrimitiveReturnType) {
                methodComplexity.setReturnType(1);
            } else if (isVoidReturnType) {
                methodComplexity.setReturnType(0);
            } else {
                methodComplexity.setReturnType(-1);
            }

            String parametersString = stringPatternService.getCodeInsideBrackets(cleanedCodeLine);

            if (parametersString != null) {

                methodComplexity
                        .setNumberOfPrimitiveDataTypeParameters(
                                stringPatternService.getNumberOfPrimitiveParameters(parametersString));

                methodComplexity
                        .setNumberOfCompositeDataTypeParameters(
                                stringPatternService.getNumberOfCompositeParameters(parametersString));
            }
        }

        scopeLevel += stringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= stringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

        lineObject.setMethodComplexity(methodComplexity);
    }
}
