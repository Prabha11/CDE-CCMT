package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.coremodule.model.MethodComplexity;
import com.sliit.cde.scannermodule.acc.enums.Scope;
import com.sliit.cde.scannermodule.acc.service.JavaLanguageStringPatternService;

class MethodComplexityAnalyzer {
    private Scope scope = Scope.GLOBAL;
    private JavaLanguageStringPatternService javaLanguageStringPatternService = new JavaLanguageStringPatternService();
    private int scopeLevel = 0;

    void analyze(Line lineObject, String codeLine, String language) {
        MethodComplexity methodComplexity = new MethodComplexity();
        String cleanedCodeLine = javaLanguageStringPatternService.cleanStringLiterals(codeLine);
        System.out.println(codeLine);
        if (scopeLevel == 1 && javaLanguageStringPatternService.isMethod(cleanedCodeLine)) {
            String retType = javaLanguageStringPatternService.getReturnVariableTypeOfMethod(cleanedCodeLine);

            boolean isPrimitiveReturnType = javaLanguageStringPatternService.isPrimitiveDataType(retType);
            boolean isVoidReturnType = javaLanguageStringPatternService.isVoidDataType(retType);

            if (isPrimitiveReturnType) {
                methodComplexity.setReturnType(1);
            } else if (isVoidReturnType) {
                methodComplexity.setReturnType(0);
            } else {
                methodComplexity.setReturnType(-1);
            }

            String parametersString = javaLanguageStringPatternService.getCodeInsideBrackets(cleanedCodeLine);

            if (parametersString != null) {

                methodComplexity
                        .setNumberOfPrimitiveDataTypeParameters(
                                javaLanguageStringPatternService.getNumberOfPrimitiveParameters(parametersString));

                methodComplexity
                        .setNumberOfCompositeDataTypeParameters(
                                javaLanguageStringPatternService.getNumberOfCompositeParameters(parametersString));
            }
        }

        scopeLevel += javaLanguageStringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= javaLanguageStringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

        lineObject.setMethodComplexity(methodComplexity);
    }
}
