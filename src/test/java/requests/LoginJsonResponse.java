package requests;

public class LoginJsonResponse {

    private int id;

    public LoginJsonResponse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LoginJsonResponse() {
    }

    public void setId(int id) {
        this.id = id;
    }
}
