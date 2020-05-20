package com.sliit.cde.scannermodule.acc.service;

import org.junit.jupiter.api.Test;

class ControlStructurePatternServiceTest {

    @Test
    void getNumberOfIfStatements() {
        ControlStructurePatternService c = new ControlStructurePatternService();
        System.out.println(c.getNumberOfIfStatements("if()") == 1);
        System.out.println(c.getNumberOfIfStatements("if ()") == 1);
        System.out.println(c.getNumberOfIfStatements("}if()") == 1);
        System.out.println(c.getNumberOfIfStatements("{if ()") == 1);
        System.out.println(c.getNumberOfIfStatements("ifd") == 0);
        System.out.println(c.getNumberOfIfStatements("dif") == 0);
        System.out.println(c.getNumberOfIfStatements(" if ") == 0);
        System.out.println(c.getNumberOfIfStatements(";if") == 0);
        System.out.println(c.getNumberOfIfStatements("if") == 0);
        System.out.println(c.getNumberOfIfStatements("(if") == 0);
    }

    @Test
    void getNumberOfLoops() {
        ControlStructurePatternService c = new ControlStructurePatternService();

        System.out.println(c.getNumberOfLoops("}for(") == 1);
        System.out.println(c.getNumberOfLoops("{for (") == 1);
        System.out.println(c.getNumberOfLoops(";for(") == 1);
        System.out.println(c.getNumberOfLoops(" for  (") == 1);
        System.out.println(c.getNumberOfLoops("  for\t(") == 1);
        System.out.println(c.getNumberOfLoops("\tfor(") == 1);
        System.out.println(c.getNumberOfLoops("ffor") == 0);
        System.out.println(c.getNumberOfLoops("forf") == 0);
        System.out.println(c.getNumberOfLoops(" for ") == 0);
        System.out.println(c.getNumberOfLoops("sd for dd") == 0);
        System.out.println(c.getNumberOfLoops("}while(") == 1);
        System.out.println(c.getNumberOfLoops("{while (") == 1);
        System.out.println(c.getNumberOfLoops(";while(") == 1);
        System.out.println(c.getNumberOfLoops(" while  (") == 1);
        System.out.println(c.getNumberOfLoops("  while\t(") == 1);
        System.out.println(c.getNumberOfLoops("\twhile(") == 1);
        System.out.println(c.getNumberOfLoops("fforwhile") == 0);
        System.out.println(c.getNumberOfLoops("whileforf") == 0);
        System.out.println(c.getNumberOfLoops(" while ") == 0);
        System.out.println(c.getNumberOfLoops("sd while dd") == 0);
        System.out.println(c.getNumberOfLoops("}do(") == 0);
        System.out.println(c.getNumberOfLoops("{do (") == 0);
        System.out.println(c.getNumberOfLoops(";do(") == 0);
        System.out.println(c.getNumberOfLoops("}do{") == 1);
        System.out.println(c.getNumberOfLoops("{do {") == 1);
        System.out.println(c.getNumberOfLoops(";do{") == 1);
        System.out.println(c.getNumberOfLoops(" do  {") == 1);
        System.out.println(c.getNumberOfLoops("  do\t{") == 1);
        System.out.println(c.getNumberOfLoops("\tdo{") == 1);
        System.out.println(c.getNumberOfLoops("ado {") == 0);
        System.out.println(c.getNumberOfLoops(" dora") == 0);
        System.out.println(c.getNumberOfLoops(" do ") == 0);
        System.out.println(c.getNumberOfLoops("sd do dd") == 0);
    }

    @Test
    void getNumberOfSwitchesStatements() {
        ControlStructurePatternService c = new ControlStructurePatternService();

        System.out.println(c.getNumberOfSwitchStatements("}switch(") == 1);
        System.out.println(c.getNumberOfSwitchStatements("{switch (") == 1);
        System.out.println(c.getNumberOfSwitchStatements(";switch(") == 1);
        System.out.println(c.getNumberOfSwitchStatements(" switch  (") == 1);
        System.out.println(c.getNumberOfSwitchStatements("  switch\t(") == 1);
        System.out.println(c.getNumberOfSwitchStatements("\tswitch(") == 1);
        System.out.println(c.getNumberOfSwitchStatements("fswitch") == 0);
        System.out.println(c.getNumberOfSwitchStatements("switchf") == 0);
        System.out.println(c.getNumberOfSwitchStatements(" switch ") == 0);
        System.out.println(c.getNumberOfSwitchStatements("sd switch dd") == 0);
    }

    @Test
    void getNumberOfCaseStatements() {
        ControlStructurePatternService c = new ControlStructurePatternService();

        System.out.println(c.getNumberOfCaseStatements("case ") == 1);
        System.out.println(c.getNumberOfCaseStatements("} case ") == 1);//
        System.out.println(c.getNumberOfCaseStatements("    case ") == 1);
        System.out.println(c.getNumberOfCaseStatements("\tcase ") == 1);
        System.out.println(c.getNumberOfCaseStatements("{case ") == 1);//
        System.out.println(c.getNumberOfCaseStatements("}case") == 0);
        System.out.println(c.getNumberOfCaseStatements(";case ") == 1);//
        System.out.println(c.getNumberOfCaseStatements(";case\n") == 1);//
        System.out.println(c.getNumberOfCaseStatements(";case") == 0);//
        System.out.println(c.getNumberOfCaseStatements("gcase") == 0);
        System.out.println(c.getNumberOfCaseStatements("cased") == 0);
        System.out.println(c.getNumberOfCaseStatements(" cases") == 0);
        System.out.println(c.getNumberOfCaseStatements("acase ") == 0);
        System.out.println(c.getNumberOfCaseStatements("case\n") == 1);
    }
}
