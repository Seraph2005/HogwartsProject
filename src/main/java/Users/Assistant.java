package Users;

import Hogwarts.Hogwarts;

import java.util.ArrayList;
import java.util.List;

public class Assistant extends Account{
    private static final List<List<String>> inbox = new ArrayList<>();

    public static List<List<String>> getInbox() {
        return inbox;
    }

}
