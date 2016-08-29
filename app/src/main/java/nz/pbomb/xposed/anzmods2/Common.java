package nz.pbomb.xposed.anzmods2;


public class Common {
    private static Common ourInstance = new Common();

    private final String AppPackage = "nz.pbomb.xposed.anzmods2";

    private Common() {
    }

    public static Common getInstance() {
        return ourInstance;
    }

    public String getAppPackage() {
        return AppPackage;
    }
}
