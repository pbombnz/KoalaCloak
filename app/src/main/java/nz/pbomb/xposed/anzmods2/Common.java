package nz.pbomb.xposed.anzmods2;


public class Common {
    private static Common ourInstance = new Common();

    public final boolean DEBUG = false;

    public final String TAG = "KoalaCloak:Mod"; // Tag used for debugLog

    public final String PACKAGE_APP = "nz.pbomb.xposed.anzmods2";
    public final String PACKAGE_ANZ_AU_MOBILE_PAY = "com.anz.mobilepay";
    public final String PACKAGE_ANZ_AU_SHIELD = "enterprise.com.anz.shield";
    public final String PACKAGE_NAB = "au.com.nab.mobile";
    public final String PACKAGE_WESTPAC = "org.westpac.bank";
    public final String PACKAGE_COMMBANK = "com.commbank.netbank";

    public final String SHARED_PREFS_FILENAME = PACKAGE_APP + "_preferences";

    private Common() {
    }

    public static Common getInstance() {
        return ourInstance;
    }
}
