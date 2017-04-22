package nz.pbomb.xposed.anzmods2.preferences;


public class PreferencesSettings {
    public static final class DEFAULT_VALUES {
        public static final class ANZ_MOBILE_PAY {
            public static final boolean SYSTEM_INTEGRITY = true; // true, means that the root mounts are hidden.
            public static final boolean DEVELOPER_SETTINGS = true;
        }

        public static final class ANZ_SHIELD {
            public static final boolean DEVELOPER_SETTINGS = true; // true, means that the developer settings option are seen as inactive.
        }

        public static final class WESTPAC {
            public static final boolean ROOT_DETECTION = true; // true, means that the root mounts are hidden.
        }

        public static final class COMMBANK {
            public static final boolean ROOT_DETECTION = true; // true, means that the root mounts are hidden.
        }

        public static final class MAIN {
            public static final boolean DEBUG = false; // true, means that the root mounts are hidden.
        }
    }

    public static final class KEYS {
        public static final class ANZ_MOBILE_PAY {
            public static final String SYSTEM_INTEGRITY = "anzMobilePaySystemIntegrity";
            public static final String DEVELOPER_SETTINGS = "anzMobilePayDeveloperSettings";
        }

        public static final class ANZ_SHIELD {
            public static final String  DEVELOPER_SETTINGS = "anzShieldDeveloperSettings";
        }

        public static final class WESTPAC {
            public static final String ROOT_DETECTION = "westpacRootDetection";
            // public static final String SPOOF_DEVICE = "westpacSpoofDevice";
            // public static final String SPOOF_DEVICE_CHOOSER = "westpacSpoofDeviceChooser";
        }

        public static final class COMMBANK {
            public static final String ROOT_DETECTION = "commbankRootDetection";
        }

        public static final class MAIN {
            public static final String ANZ_MOBILE_PAY = "anzMobilePayPrefs";
            public static final String ANZ_SHIELD = "anzShieldPrefs";
            public static final String WESTPAC = "westpacPrefs";
            public static final String COMMBANK = "commbankPrefs";

            public static final String DEBUG = "debug";
        }
    }
}
