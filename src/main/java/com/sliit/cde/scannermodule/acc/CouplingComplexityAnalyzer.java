package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.CouplingComplexity;
import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.scannermodule.acc.enums.Method;
import com.sliit.cde.scannermodule.acc.service.CouplingPatternService;
import com.sliit.cde.scannermodule.acc.service.MethodAndVariableStringPatternService;
import com.sliit.cde.scannermodule.acc.service.java.JavaLanguageMethodAndVariableStringPatternService;

import java.util.List;

public class CouplingComplexityAnalyzer {
    private CouplingPatternService couplingPatternService = new CouplingPatternService();
    private MethodAndVariableStringPatternService MethodAndVariableStringPatternService
            = new JavaLanguageMethodAndVariableStringPatternService();
    private int scopeLevel = 0;

    void analyze(Line lineObject, String codeLine, List<Method> methodsList) {
        CouplingComplexity couplingComplexity = new CouplingComplexity();
        String cleanedCodeLine = MethodAndVariableStringPatternService.cleanStringLiterals(codeLine);

        if (couplingPatternService.isMethod(cleanedCodeLine)) {
            String methodName = couplingPatternService.getMethodName(cleanedCodeLine);
        }

        scopeLevel += MethodAndVariableStringPatternService.getNumberOpenCurlBrackets(cleanedCodeLine);
        scopeLevel -= MethodAndVariableStringPatternService.getNumberCloseCurlBrackets(cleanedCodeLine);

//        lineObject.setCouplingComplexity(couplingComplexity);
    }
}
