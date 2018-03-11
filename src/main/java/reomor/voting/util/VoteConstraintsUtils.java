package reomor.voting.util;

import java.time.LocalTime;

public class VoteConstraintsUtils {
    private static LocalTime edge = LocalTime.of(11, 0, 0);

    public static boolean isLateTime(LocalTime localTime) {
        //return false;
        return localTime.isAfter(edge);
    }

    public static void setEdgeForTesting() {
        edge = LocalTime.MAX;
    }
}
