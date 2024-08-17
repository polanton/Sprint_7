package requests;

public class CreateCourierJsonRequest {
    String login;
    String password;
    String firstName;

    public CreateCourierJsonRequest() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public CreateCourierJsonRequest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public static CreateCourierJsonRequest withoutLogin(String password, String firstName){
        CreateCourierJsonRequest jsonBody = new CreateCourierJsonRequest();
        jsonBody.password = password;
        jsonBody.firstName=firstName;
        return jsonBody;
    }

    public static CreateCourierJsonRequest withoutPassword(String login, String firstName){
        CreateCourierJsonRequest jsonBody = new CreateCourierJsonRequest();
        jsonBody.login = login;
        jsonBody.firstName=firstName;
        return jsonBody;
    }

    public static CreateCourierJsonRequest withoutFirstName(String login, String password){
        CreateCourierJsonRequest jsonBody = new CreateCourierJsonRequest();
        jsonBody.login = login;
        jsonBody.password=password;
        return jsonBody;
    }
}
