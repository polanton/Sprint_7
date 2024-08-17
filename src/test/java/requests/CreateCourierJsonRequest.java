package requests;

public class CreateCourierJson {
    String login;
    String password;
    String firstName;

    public CreateCourierJson() {

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

    public CreateCourierJson(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public CreateCourierJson withoutLogin(String password, String firstName){
        CreateCourierJson jsonBody = new CreateCourierJson();
        jsonBody.password = password;
        jsonBody.firstName=firstName;
        return jsonBody;
    }

    public CreateCourierJson withoutPassword(String login, String firstName){
        CreateCourierJson jsonBody = new CreateCourierJson();
        jsonBody.login = login;
        jsonBody.firstName=firstName;
        return jsonBody;
    }

    public CreateCourierJson withoutFirstName(String login, String password){
        CreateCourierJson jsonBody = new CreateCourierJson();
        jsonBody.login = login;
        jsonBody.password=password;
        return jsonBody;
    }
}
