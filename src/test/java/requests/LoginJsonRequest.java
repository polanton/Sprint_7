package requests;

public class LoginJsonRequest {

    String login;
    String password;

    public String getLogin() {
        return login;
    }

    public LoginJsonRequest() {
    }


    public LoginJsonRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginJsonRequest withoutLogin(String password){
        LoginJsonRequest jsonBody = new LoginJsonRequest();
        jsonBody.password = password;
        return jsonBody;
    }

    public static LoginJsonRequest withoutPassword(String login){
        LoginJsonRequest jsonBody = new LoginJsonRequest();
        jsonBody.login = login;
        return jsonBody;
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
}
