package goorm.studentAPI.student;

import lombok.Data;

@Data
public class Student {
  public Student(String name, int grade) {
    this.name = name;
    this.grade = grade;
  }

  private Long id;

  private String name;
  private int grade;
}
