package goorm.studentAPI.controller;

import goorm.studentAPI.student.Student;
import goorm.studentAPI.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

  StudentRepository studentRepository = StudentRepository.getInstance();

  @ResponseBody
  @PostMapping("/register")
  public Student RegisterNew(@RequestParam String name, @RequestParam int grade) {
    Student student = new Student(name, grade);
    studentRepository.save(student);
    log.info("studentName={}, studentGrade={}", student.getName(), student.getGrade());
    return student;
  }


}
