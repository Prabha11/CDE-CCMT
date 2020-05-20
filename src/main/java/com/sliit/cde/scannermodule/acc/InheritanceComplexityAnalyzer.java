package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.InheritanceComplexity;
import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.scannermodule.acc.enums.Language;
import com.sliit.cde.scannermodule.acc.service.InheritancePatternService;

public class InheritanceComplexityAnalyzer {
    private InheritancePatternService inheritancePatternService = new InheritancePatternService();

    void analyze(Line lineObject, String codeLine, Language language) {
        InheritanceComplexity inheritanceComplexity = new InheritanceComplexity();

        String cleanedCodeLine = inheritancePatternService.cleanStringLiterals(codeLine);

        if (inheritancePatternService.isAClass(cleanedCodeLine)) {
            inheritanceComplexity.setaClass(true);
            if (language == Language.JAVA) {
                inheritanceComplexity.setNoOfInheritances(
                        inheritancePatternService.getNumberOfInheritancesJava(codeLine));
            } else if (language == Language.CPP) {
                inheritanceComplexity.setNoOfInheritances(
                        inheritancePatternService.getNumberOfInheritancesCPP(codeLine));
            }
        }

        lineObject.setInheritanceComplexity(inheritanceComplexity);
    }
}
