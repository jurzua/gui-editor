package cl.talca.videogame.service;

import org.apache.commons.lang3.StringUtils;

public class UserService {

    //hardcode user
    private String USERNAME = "ricardo";
    private String PASSWORD = "12345";

    public boolean login(String username, String password) {
        return StringUtils.equals(username, USERNAME) && StringUtils.equals(password, PASSWORD);
    }
}
