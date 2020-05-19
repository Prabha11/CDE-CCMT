package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.coremodule.model.SizeComplexity;
import com.sliit.cde.scannermodule.acc.enums.Language;
import com.sliit.cde.scannermodule.acc.service.CPPLanguageSizeStringPatternService;
import com.sliit.cde.scannermodule.acc.service.JavaLanguageSizeStringPatternService;
import com.sliit.cde.scannermodule.acc.service.SizeStringPatternService;

public class SizeComplexityAnalyzer {
    private SizeStringPatternService sizeStringPatternService = new JavaLanguageSizeStringPatternService();

    public SizeComplexityAnalyzer(Language language) {
        if (language == Language.JAVA) {
            this.sizeStringPatternService = new JavaLanguageSizeStringPatternService();
        } else if (language == Language.CPP) {
            this.sizeStringPatternService = new CPPLanguageSizeStringPatternService();
        }
    }

    void analyze(Line lineObject, String codeLine) {
        SizeComplexity sizeComplexity = new SizeComplexity();

        sizeComplexity.setNumberOfStringLiterals(sizeStringPatternService.getNumberOfStringLiterals(codeLine));

        String cleanedCodeLine = sizeStringPatternService.cleanStringLiterals(codeLine);

        sizeComplexity.setNumberOfKeyWords(sizeStringPatternService.getNumberOfKeyWords(cleanedCodeLine));
        sizeComplexity.setNumberOfIdentifiers(sizeStringPatternService.getNumberOfIdentifiers(cleanedCodeLine));
        sizeComplexity.setNumberOfNumericalValues(sizeStringPatternService.getNumberOfNumericValues(cleanedCodeLine));
        sizeComplexity.setNumberOfOperators(sizeStringPatternService.getNumberOfOperators(cleanedCodeLine));

        lineObject.setSizeComplexity(sizeComplexity);
    }
}
