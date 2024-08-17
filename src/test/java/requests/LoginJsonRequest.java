package requests;

public class LoginJson {

    String login;
    String password;

    public String getLogin() {
        return login;
    }

    public LoginJson() {
    }


    public LoginJson(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginJson withoutLogin(String password){
        LoginJson jsonBody = new LoginJson();
        jsonBody.password = password;
        return jsonBody;
    }

    public LoginJson withoutPassword(String login){
        LoginJson jsonBody = new LoginJson();
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
