package cl.talca.videogame.swing.components;

public class Login {

    public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("ricardo") && password.equals("12345")) {
            return true;
        }
        return false;
    }
}
