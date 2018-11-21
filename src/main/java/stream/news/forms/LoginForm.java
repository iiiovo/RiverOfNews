package stream.news.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

    @Size(min = 5, max = 30, message = "User Name size should be in the range [5-30]")
    private String userName;

    @NotNull
    @Size(min = 5, max = 50)
    private String password;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
