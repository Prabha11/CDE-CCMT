package com.sliit.cde.scannermodule.acc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouplingPatternServiceTest {

    @Test
    void isMethod() {
        CouplingPatternService couplingPatternService = new CouplingPatternService();
        System.out.println(couplingPatternService.isMethod("System.out.println(methods.size());"));
        System.out.println(couplingPatternService.isMethod("println(methods.size());"));
        System.out.println(couplingPatternService.isMethod("println(methods);"));
        System.out.println(couplingPatternService.isMethod("(println(methods));"));

        System.out.println(!couplingPatternService.isMethod("println = (methods.size);"));
        System.out.println(!couplingPatternService.isMethod("getMethd = 0 + 8 * 7;"));
    }

    @Test
    void getMethodName() {
        CouplingPatternService couplingPatternService = new CouplingPatternService();
        System.out.println(couplingPatternService.getMethodName("System.out.println(methods.size());"));
    }
}
