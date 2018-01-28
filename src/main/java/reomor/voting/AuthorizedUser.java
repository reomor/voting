package reomor.voting;

public class AuthorizedUser {
    private static int id = 1000; // user - 1000, admin - 1001

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}
