package com.sliit.cde.scannermodule.acc.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InheritancePatternServiceTest {

    @Test
    void getNumberOfInheritancesCPP() {
        InheritancePatternService i = new InheritancePatternService();
        System.out.println(i.getNumberOfInheritancesCPP("class Programmer: public Employee ,public Employee ,public Employee ,public Employee {") == 4);
        System.out.println(i.getNumberOfInheritancesCPP("class Programmer: protected Employee ,private Employee ,public Employee {") == 3);
        System.out.println(i.getNumberOfInheritancesCPP("class MyChildClass: public MyClass, public MyOtherClass {") == 2);
        System.out.println(i.getNumberOfInheritancesCPP("class MyChildClass: public MyClass, public MyOtherClass \n") == 2);
        System.out.println(i.getNumberOfInheritancesCPP("    class    MyChildClass:    public    MyClass  ,   public   MyOtherClass   {") == 2);
        System.out.println(i.getNumberOfInheritancesCPP("    class    MyChildClass:    public    MyClass  ,   public   MyOtherClass   \n") == 2);
        System.out.println(i.getNumberOfInheritancesCPP("    class    MyChildClass:    public    MyClass  ,   public   MyOtherClass\n") == 2);

    }

    @Test
    void isAClass() {
        InheritancePatternService i = new InheritancePatternService();
        System.out.println(i.isAClass("class Programmer: public Employee ,public Employee ,public Employee ,public Employee {"));
        System.out.println(i.isAClass("class Programmer: protected Employee ,private Employee ,public Employee {"));
        System.out.println(i.isAClass("class MyChildClass: public MyClass, public MyOtherClass {"));
        System.out.println(i.isAClass("class MyChildClass: public MyClass, public MyOtherClass \n"));
        System.out.println(i.isAClass("    class    MyChildClass:    public    MyClass  ,   public   MyOtherClass   {"));
        System.out.println(i.isAClass("    class    MyChildClass:    public    MyClass  ,   public   MyOtherClass   \n"));
        System.out.println(i.isAClass("    class    MyChildClass:    public    MyClass  ,   public   MyOtherClass\n"));

        System.out.println(!i.isAClass("Programmer: public Employee ,public Employee ,public Employee ,public Employee {"));
        System.out.println(!i.isAClass("Programmer: protected Employee ,private Employee ,public Employee {"));
        System.out.println(!i.isAClass("MyChildClass: public MyClass, public MyOtherClass {"));
        System.out.println(!i.isAClass("MyChildClass: public MyClass, public MyOtherClass \n"));
        System.out.println(!i.isAClass("        MyChildClass:    public    MyClass  ,   public   MyOtherClass   {"));
        System.out.println(!i.isAClass("        MyChildClass:    public    MyClass  ,   public   MyOtherClass   \n"));
        System.out.println(!i.isAClass("        MyChildClass:    public    MyClass  ,   public   MyOtherClass\n"));

        System.out.println(!i.isAClass("myclass"));
        System.out.println(!i.isAClass("classU"));

        System.out.println(!i.isAClass("(class"));
        System.out.println(!i.isAClass("}class"));
        System.out.println(!i.isAClass("{class"));
        System.out.println(i.isAClass("(class "));
        System.out.println(i.isAClass("}class "));
        System.out.println(i.isAClass("{class "));
        System.out.println(i.isAClass("( class "));
        System.out.println(i.isAClass("} class "));
        System.out.println(i.isAClass("{ class "));
    }
}
