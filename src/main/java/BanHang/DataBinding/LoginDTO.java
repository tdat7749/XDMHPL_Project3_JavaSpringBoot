package BanHang.DataBinding;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank(message = "Không được để trống")
    private String userName;

    @NotBlank(message = "Không được để trống")
    private String password;

    public LoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginDTO() {

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
