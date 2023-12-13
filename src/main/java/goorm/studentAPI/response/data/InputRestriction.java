package goorm.studentAPI.response.data;

import lombok.Getter;

@Getter
public class InputRestriction {

  private String maxGrade;

  public InputRestriction() {
  }

  public InputRestriction(String maxGrade) {
    this.maxGrade = maxGrade;
  }
}
