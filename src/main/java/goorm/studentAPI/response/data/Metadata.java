package goorm.studentAPI.response.data;

import lombok.Data;

@Data
public class Metadata {

  private int resultCount;

  public Metadata(int resultCount) {
    this.resultCount = resultCount;
  }
}
