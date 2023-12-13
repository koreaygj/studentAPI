package goorm.studentAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import goorm.studentAPI.response.ApiResponse;
import goorm.studentAPI.response.customException.CustomException;
import goorm.studentAPI.response.customException.ErrorCode;
import goorm.studentAPI.response.data.InputRestriction;
import goorm.studentAPI.response.data.Metadata;
import goorm.studentAPI.response.data.Status;
import goorm.studentAPI.student.Student;
import goorm.studentAPI.student.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

  @ExceptionHandler
  @ResponseBody
  public ApiResponse customExceptionHandler(CustomException e) {
    log.info("error={}", e.getData().toString());
    return new ApiResponse(e.getErrorCode().getCode(), e.getMessage(), e.getData());
  }

  @ResponseBody
  @PostMapping(path = "/register")
  public ApiResponse<Student> RegisterNew(@RequestParam String name, @RequestParam int grade) {
    Student result = new Student(name, grade);
    if (grade >= 6) {
      throw new CustomException(ErrorCode.SERVER_ERROR, "grade는 6이상 입력 할 수 없습니다.",
          new InputRestriction("6"));
    }
    studentRepository.save(result);
    log.info("studentName={}, studentGrade={}", result.getName(), result.getGrade());
    return makeResponse(result);
  }

  @ResponseBody
  @GetMapping
  public ApiResponse<List<Student>> searchAllStudents() {
    List<Student> result = studentRepository.findAll();
    result.sort((a, b) -> a.getGrade() - b.getGrade());
    return makeResponse(result);
  }

  @ResponseBody
  @GetMapping("/searchByGrade")
  public ApiResponse<List<Student>> searchByGrade(@RequestParam int grade) {
    List<Student> result = studentRepository.findAll().stream()
        .filter((student -> student.getGrade() == grade)).collect(Collectors.toList());
    return makeResponse(result);
  }

  public ApiResponse<Student> makeResponse(Student result) {
    ApiResponse<Student> response = new ApiResponse<>();
    response.setStatus(new Status(200, "ok"));
    response.setResults(result);
    response.setMetadata(new Metadata(1));
    return response;
  }

  public ApiResponse<List<Student>> makeResponse(List<Student> result) {
    ApiResponse<List<Student>> response = new ApiResponse<>();
    response.setStatus(new Status(200, "ok"));
    response.setResults(result);
    response.setMetadata(new Metadata(result.size()));
    return response;
  }
}
