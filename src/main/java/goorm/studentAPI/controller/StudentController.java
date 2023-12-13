package goorm.studentAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import goorm.studentAPI.response.ApiResponse;
import goorm.studentAPI.response.data.Metadata;
import goorm.studentAPI.response.data.Status;
import goorm.studentAPI.student.Student;
import goorm.studentAPI.student.StudentRepository;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequestMapping("/students")
public class StudentController {

  StudentRepository studentRepository = StudentRepository.getInstance();

  @ResponseBody
  @PostMapping(path = "/register")
  public ApiResponse<Student> RegisterNew(@RequestParam String name, @RequestParam int grade) {
    Student result = new Student(name, grade);
    studentRepository.save(result);
    log.info("studentName={}, studentGrade={}", result.getName(), result.getGrade());
    return makeResponse(result);
  }

  @ResponseBody
  @GetMapping
  public ApiResponse<List<Student>> searchAllStudents() {
    log.info("studentList={}", studentRepository);
    List<Student> result = studentRepository.findAll();
    result.sort((a, b) -> a.getGrade() - b.getGrade());
    log.info("result={}", result);
    return makeResponse(result);
  }

  public ApiResponse<Student> makeResponse(Student result) {
    ApiResponse<Student> response = new ApiResponse<>();
    response.setStatus(new Status(200, "ok"));
    response.setResults(result);
    response.setMetadata(new Metadata(1));
    log.info("response={}", response);
    return response;
  }

  public ApiResponse<List<Student>> makeResponse(List<Student> result) {
    ApiResponse<List<Student>> response = new ApiResponse<>();
    response.setStatus(new Status(200, "ok"));
    response.setResults(result);
    response.setMetadata(new Metadata(result.size()));
    log.info("response={}", response);
    return response;
  }


}
