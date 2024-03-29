package com.sliit.cde.scannermodule.acc;

import com.sliit.cde.coremodule.model.ControlStructureComplexity;
import com.sliit.cde.coremodule.model.Line;
import com.sliit.cde.scannermodule.acc.service.ControlStructurePatternService;

public class ControlStructureComplexityAnalyzer {
    private ControlStructurePatternService controlStructurePatternService = new ControlStructurePatternService();

    void analyze(Line lineObject, String codeLine) {
        ControlStructureComplexity controlStructureComplexity = new ControlStructureComplexity();

        String cleanedCodeLine = controlStructurePatternService.cleanStringLiterals(codeLine);

        controlStructureComplexity.setNoOfCases(
                controlStructurePatternService.getNumberOfCaseStatements(cleanedCodeLine));
        controlStructureComplexity.setNoOfIfs(
                controlStructurePatternService.getNumberOfIfStatements(cleanedCodeLine));
        controlStructureComplexity.setNoOfSwitches(
                controlStructurePatternService.getNumberOfSwitchStatements(cleanedCodeLine));
        controlStructureComplexity.setNoOfLoops(
                controlStructurePatternService.getNumberOfLoops(cleanedCodeLine));

        lineObject.setControlStructureComplexity(controlStructureComplexity);
    }
}
