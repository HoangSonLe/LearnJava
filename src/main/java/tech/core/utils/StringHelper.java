package tech.core.utils;

public final class StringHelper {

    private StringHelper() {
        // ngăn không cho new StringHelper()
    }
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }
}
