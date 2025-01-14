package hokase.hfwork.bukkit.utils;

import org.bukkit.Bukkit;

public class VersionUtils {
    private static final String SERVER_VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    public static boolean isVersionOrHigher(String version) {
        String[] serverParts = SERVER_VERSION.split("_");
        String[] targetParts = version.split("_");

        int serverMajor = Integer.parseInt(serverParts[1]);
        int targetMajor = Integer.parseInt(targetParts[1]);

        if (serverMajor > targetMajor) return true;
        if (serverMajor < targetMajor) return false;

        int serverMinor = Integer.parseInt(serverParts[2]);
        int targetMinor = Integer.parseInt(targetParts[2]);

        return serverMinor >= targetMinor;
    }

    public static String getServerVersion() {
        return SERVER_VERSION;
    }
}
