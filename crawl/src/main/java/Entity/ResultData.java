package Entity;

import org.apache.http.HttpStatus;

public class ResultData {
    String status;
    String accessToken;

    public ResultData() {
    }

    public ResultData(String status, String accessToken) {
        this.status = status;
        this.accessToken = accessToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
