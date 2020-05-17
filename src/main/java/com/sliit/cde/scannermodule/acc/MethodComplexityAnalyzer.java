package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.coremodule.model.MethodComplexity;
import com.sliit.cde.scannermodule.acc.enums.Language;
import com.sliit.cde.scannermodule.acc.enums.Scope;
import com.sliit.cde.scannermodule.acc.service.CPPLanguageStringPatternService;
import com.sliit.cde.scannermodule.acc.service.JavaLanguageStringPatternService;
import com.sliit.cde.scannermodule.acc.service.StringPatternService;

class MethodComplexityAnalyzer {
    private Scope scope = Scope.GLOBAL;
    private StringPatternService StringPatternService = new JavaLanguageStringPatternService();
    private int scopeLevel = 0;

    public MethodComplexityAnalyzer(Language language) {
        if (language == Language.JAVA)
            StringPatternService = new JavaLanguageStringPatternService();
        else if (language == Language.CPP)
            StringPatternService = new CPPLanguageStringPatternService();
    }

    void analyze(Line lineObject, String codeLine) {
        MethodComplexity methodComplexity = new MethodComplexity();
        String cleanedCodeLine = StringPatternService.cleanStringLiterals(codeLine);
        System.out.println(codeLine);
        if (scopeLevel == 1 && StringPatternService.isMethod(cleanedCodeLine)) {
            String retType = StringPatternService.getReturnVariableTypeOfMethod(cleanedCodeLine);

            boolean isPrimitiveReturnType = StringPatternService.isPrimitiveDataType(retType);
            boolean isVoidReturnType = StringPatternService.isVoidDataType(retType);

            if (isPrimitiveReturnType) {
                methodComplexity.setReturnType(1);
            } else if (isVoidReturnType) {
                methodComplexity.setReturnType(0);
            } else {
                methodComplexity.setReturnType(-1);
            }

            String parametersString = StringPatternService.getCodeInsideBrackets(cleanedCodeLine);

            if (parametersString != null) {

                methodComplexity
                        .setNumberOfPrimitiveDataTypeParameters(
                                StringPatternService.getNumberOfPrimitiveParameters(parametersString));

                methodComplexity
                        .setNumberOfCompositeDataTypeParameters(
                                StringPatternService.getNumberOfCompositeParameters(parametersString));
            }
        }

        scopeLevel += StringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= StringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

        lineObject.setMethodComplexity(methodComplexity);
    }
}
