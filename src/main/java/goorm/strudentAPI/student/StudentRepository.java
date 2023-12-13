package goorm.strudentAPI.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRepository {

  private static Long sequence = 0L;
  private static HashMap<Long, Student> store = new HashMap<>();
  private static final StudentRepository instance = new StudentRepository();

  public Student save(Student student) {
    student.setId(++sequence);
    store.put(student.getId(), student);
    return student;
  }

  public Student findById(Long id) {
    return store.get(id);
  }

  public int size() {
    return store.size();
  }

  public List<Student> findAll() {
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }


}
