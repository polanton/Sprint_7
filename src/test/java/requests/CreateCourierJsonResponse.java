package requests;

public class CreateCourierJsonResponse {

    private Boolean ok;

    public CreateCourierJsonResponse() {
    }

    public CreateCourierJsonResponse(Boolean ok) {
        this.ok = ok;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }
}
