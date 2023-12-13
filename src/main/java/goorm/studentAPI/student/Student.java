package goorm.studentAPI.student;

import lombok.Data;

@Data
public class Student {

  private Long id;
  private String name;
  private int grade;

  public Student(String name, int grade) {
    this.name = name;
    this.grade = grade;
  }

  public int getGrade() {
    return grade;
  }
}
