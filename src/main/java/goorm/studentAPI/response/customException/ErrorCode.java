package goorm.studentAPI.response.customException;

public enum ErrorCode {

  BAD_REQUEST(400,"Bad Request"),

  UNAUTHORIZED(401,"Unauthorized"),

  FORBIDDEN(403,"Forbidden"),

  NOT_FOUND(404,"Not Found"),

  SERVER_ERROR(500,"Internal Server Error");


  private final int code;
  private final String description;

  ErrorCode(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }
}
