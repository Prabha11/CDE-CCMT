package com.sliit.cde.scannermodule.acc.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringPatternService extends CommonStringPatternService {

    public List<String> getPrimitiveDataTypes() {
        String operatorsString = "byte,short,int,long,float,double,boolean,char";
        return Arrays.asList(operatorsString.split(","));
    }

    public String cleanStringLiterals(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            codeLine = codeLine.replace(textInsideDoubleQuotedMatcher.group(0), "");
        }
        return codeLine;
    }

    private String removeWord(String target, String word) {
        Pattern wordPattern = Pattern.compile(word);
        Matcher wordMatcher = wordPattern.matcher(target);
        while (wordMatcher.find())
            target = target.replace(wordMatcher.group(0), "");
        return target;
    }

    private boolean isSubString(String target, String word) {
        Pattern wordPattern = Pattern.compile(word);
        Matcher wordMatcher = wordPattern.matcher(target);
        return wordMatcher.find();
    }

    private int getNumberOfSubstrings(String target, String word) {
        int number = 0;
        Pattern wordPattern = Pattern.compile(word);
        Matcher wordMatcher = wordPattern.matcher(target);
        while (wordMatcher.find()) number++;
        return number;
    }

    public boolean c(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile("public(\\s+)(static|nonstatic|^$)(\\s+)void");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        boolean r = textInsideDoubleQuotedMatcher.find();
//        if (r) System.out.println(textInsideDoubleQuotedMatcher.group(0));
        return r;
    }

    public boolean isMethod(String codeLine) {
        Pattern methodStringPattern = Pattern.compile(
                "(\\s+|^)(.*?)(\\(.*?\\))(.*?)(\\{|\n)");
        Matcher methodStringPatternMatcher = methodStringPattern.matcher(codeLine);
        boolean r = methodStringPatternMatcher.find();
//        System.out.println(r);

        Pattern assignment = Pattern.compile("(=)");
        Matcher assignmentMatcher = assignment.matcher(codeLine);
        boolean assignmentExist = assignmentMatcher.find();
//        if (assignmentExist) System.out.println(assignmentMatcher.group(0));

        Pattern semiColan = Pattern.compile("(;)");
        Matcher semiColanMatcher = semiColan.matcher(codeLine);
        boolean semiColanExist = semiColanMatcher.find();

        boolean datatypeInBrackets = true;

//        if (r && !assignmentExist && !semiColanExist) {
//            String[] parameters = getCodeInsideBrackets(codeLine).split(",");
//            if ((parameters.length > 0) && parameters[0].split("\\s+").length < 2) datatypeInBrackets = false;
//        }

        return r && !assignmentExist && !semiColanExist && datatypeInBrackets;
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

    public boolean isPrimitiveDataType(String datatype) {
        List<String> primitiveDataTypes = this.getPrimitiveDataTypes();
        return primitiveDataTypes.contains(datatype);
    }

    public boolean isVoidDataType(String retType) {
        return retType.equals("void");
    }

    public boolean isPrimitiveVariable(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile(
                "(\\s+|^)(byte|short|int|long|float|double|boolean|char)(\\s+)(.*?);");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        boolean r = textInsideDoubleQuotedMatcher.find();
//        if (r) System.out.println(textInsideDoubleQuotedMatcher.group(0));
        return r;
    }

    public boolean isCompositeVariable(String codeLine) {
        Pattern textInsideDoubleQuoted = Pattern.compile(
                "(\\s+|^)(\\s+)(.*?);");
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

    public boolean isVariable(String codeLine) {
        boolean rejectedWordIncluded = this.isSubString(codeLine, "return");
        return !rejectedWordIncluded;
    }

    public int getNumberOpenCurlBrackets(String codeLine) {
        int numberOfStringLiterals = 0;
        Pattern textInsideDoubleQuoted = Pattern.compile("[{]");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            numberOfStringLiterals++;
        }
        return numberOfStringLiterals;
    }

    public int getNumberCloseCurlBrackets(String codeLine) {
        int numberOfStringLiterals = 0;
        Pattern textInsideDoubleQuoted = Pattern.compile("[}]");
        Matcher textInsideDoubleQuotedMatcher = textInsideDoubleQuoted.matcher(codeLine);
        while (textInsideDoubleQuotedMatcher.find()) {
            numberOfStringLiterals++;
        }
        return numberOfStringLiterals;
    }

    public String getCodeInsideBrackets(String codeLine) {
        String firstBreak =  codeLine.split("\\(", 2)[1];
        int index = firstBreak.lastIndexOf(")");

        if (index > 1)
            return firstBreak.substring(0, index);
        else return null;
    }

    public int getNumberOfPrimitiveParameters(String parametersString) {
        int numberOfPrimitiveParameters = 0;
        String[] splitParameters = parametersString.split(",");

        for (String parameter: splitParameters) {
            if (isPrimitiveParameter(parameter)) numberOfPrimitiveParameters++;
        }
        return numberOfPrimitiveParameters;
    }

    public int getNumberOfCompositeParameters(String parametersString) {
        int numberOfCompositeParameters = 0;
        String[] splitParameters = parametersString.split(",");

        for (String parameter: splitParameters) {
            if (!isPrimitiveParameter(parameter)) numberOfCompositeParameters++;
        }
        return numberOfCompositeParameters;
    }

    public static void maind(String[] args) {
        StringPatternService stringPatternService = new StringPatternService();
        System.out.println(stringPatternService.getReturnVariableTypeOfMethod("    public String index() {\n"));
        System.out.println(stringPatternService.getCodeInsideBrackets("    public String index() {\n"));
        System.out.println(stringPatternService.getNumberOfPrimitiveParameters(stringPatternService.getCodeInsideBrackets("    public String index() {\n")));
        System.out.println(stringPatternService.getNumberOfCompositeParameters(stringPatternService.getCodeInsideBrackets("    public String index() {\n")));
        System.out.println(stringPatternService.isVoidDataType(stringPatternService.getReturnVariableTypeOfMethod("    public String index() {\n")));
//        System.out.println(stringPatternService.isMethod("private int getInt(int pm, String i){\n"));

    }

    public static void main(String[] args) {
        String codeLine = "  public static void main  (@ano String[ ] args, @ano String[] args, @ano String args, @ano int args, int[] args, int args, int args, long args, float args, char args,) {\n";
        StringPatternService stringPatternService = new StringPatternService();

        System.out.println(stringPatternService.getNumberOfPrimitiveParameters(stringPatternService.getCodeInsideBrackets(codeLine)));
        System.out.println(stringPatternService.getNumberOfCompositeParameters(stringPatternService.getCodeInsideBrackets(codeLine)));

        System.out.println(stringPatternService.isPrimitiveParameter("@ano String[ ] args"));
        System.out.println(stringPatternService.isPrimitiveParameter("@ano String[] args"));
        System.out.println(stringPatternService.isPrimitiveParameter("@ano String args"));
        System.out.println(stringPatternService.isPrimitiveParameter("@ano int args"));
        System.out.println(stringPatternService.isPrimitiveParameter(" int[] args"));
        System.out.println(stringPatternService.isPrimitiveParameter(" int args"));
        System.out.println(stringPatternService.isPrimitiveParameter("int args"));
        System.out.println(stringPatternService.isPrimitiveParameter("long args"));
        System.out.println(stringPatternService.isPrimitiveParameter("float args"));
        System.out.println(stringPatternService.isPrimitiveParameter("char args"));
    }
}
