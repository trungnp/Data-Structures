/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.structures;

/**
 *
 * @author trungnp
 */
class Student {
    public String getNameParent(){
        return "This is getNameParent from Student";
    }
}

class CollegeStudent extends Student{
    public String getName(){
        return "This is getName from CollegeStudent";
    }
}

class Test {
    public static void main(String[] args) {
        Student student = new CollegeStudent();
        
        System.out.println(student.getClass().getDeclaredClasses());
        System.out.println(student.getNameParent());
        System.out.println(((CollegeStudent)student).getName());
        
    }
}
