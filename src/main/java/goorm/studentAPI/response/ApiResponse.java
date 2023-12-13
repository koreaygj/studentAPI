package goorm.studentAPI.response;

import goorm.studentAPI.response.data.Metadata;
import goorm.studentAPI.response.data.Status;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
public class ApiResponse<T> {

  private Status status;
  private Metadata metadata;
  private List<T> results;

  public ApiResponse() {
    this.results = new ArrayList<>();
    log.info("result={}", results);
  }

  public void setResults(T result) {
    this.results.add(result);
  }

  public void setResults(List<T> results){
    this.results = results;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }
}
