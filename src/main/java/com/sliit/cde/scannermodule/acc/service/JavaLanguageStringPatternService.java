package com.sliit.cde.scannermodule.acc.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaLanguageStringPatternService extends StringPatternService {

    public List<String> getPrimitiveDataTypes() {
        String operatorsString = "byte,short,int,long,float,double,boolean,char";
        return Arrays.asList(operatorsString.split(","));
    }

    public String getReturnVariableTypeOfMethod(String codeLine) {
        if (isMethod(codeLine)) {
            codeLine = this.removeWord(codeLine, "static");
            String newCodeLine = this.removeWord(codeLine, "private|protected|public");

            String delimiter = "\\s+";
            Pattern pat = Pattern.compile(delimiter, Pattern.CASE_INSENSITIVE);
            String[] scattered = pat.split(newCodeLine);

            for (String s: scattered) {
                if (!s.equals("")) return s;
            }
        }
        return null;
    }

    public boolean isPrimitiveVariable(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile(
                "(\\s+|^)(byte|short|int|long|float|double|boolean|char)(\\s+)(.*?);");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        boolean r = textInsideDoubleQuotedMatcher.find();
//        if (r) System.out.println(textInsideDoubleQuotedMatcher.group(0));
        return r;
    }

    public boolean isPrimitiveParameter(String parameter) {
        Pattern squareBracket = Pattern.compile("\\[(.*?)\\]");
        Matcher squareBracketMatcher = squareBracket.matcher(parameter);
        boolean isArray = squareBracketMatcher.find();

        boolean isPrimitive = isSubString(parameter, "byte|short|int|long|float|double|boolean|char");

        return !isArray && isPrimitive;
    }

    public static void maind(String[] args) {
        JavaLanguageStringPatternService javaLanguageStringPatternService = new JavaLanguageStringPatternService();
        System.out.println(javaLanguageStringPatternService.getReturnVariableTypeOfMethod("    public String index() {\n"));
        System.out.println(javaLanguageStringPatternService.getCodeInsideBrackets("    public String index() {\n"));
        System.out.println(javaLanguageStringPatternService.getNumberOfPrimitiveParameters(javaLanguageStringPatternService.getCodeInsideBrackets("    public String index() {\n")));
        System.out.println(javaLanguageStringPatternService.getNumberOfCompositeParameters(javaLanguageStringPatternService.getCodeInsideBrackets("    public String index() {\n")));
        System.out.println(javaLanguageStringPatternService.isVoidDataType(javaLanguageStringPatternService.getReturnVariableTypeOfMethod("    public String index() {\n")));
//        System.out.println(stringPatternService.isMethod("private int getInt(int pm, String i){\n"));

    }

    public static void main(String[] args) {
        String codeLine = "  public static void main  (@ano String[ ] args, @ano String[] args, @ano String args, @ano int args, int[] args, int args, int args, long args, float args, char args,) {\n";
        JavaLanguageStringPatternService javaLanguageStringPatternService = new JavaLanguageStringPatternService();

        System.out.println(javaLanguageStringPatternService.getNumberOfPrimitiveParameters(javaLanguageStringPatternService.getCodeInsideBrackets(codeLine)));
        System.out.println(javaLanguageStringPatternService.getNumberOfCompositeParameters(javaLanguageStringPatternService.getCodeInsideBrackets(codeLine)));

        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("@ano String[ ] args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("@ano String[] args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("@ano String args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("@ano int args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter(" int[] args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter(" int args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("int args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("long args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("float args"));
        System.out.println(javaLanguageStringPatternService.isPrimitiveParameter("char args"));
    }
}
