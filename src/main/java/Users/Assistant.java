package Users;

import java.util.ArrayList;
import java.util.List;

public class Assistant extends Account{
    private static final List<List<String>> signups = new ArrayList<>();

    public Assistant(String username, String password, String house) {
        super(username, password, house);
    }

    public static List<List<String>> getSignups() {
        return signups;
    }

}
