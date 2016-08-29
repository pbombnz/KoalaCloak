package nz.pbomb.xposed.anzmods2.preferences;


public class PreferencesSettings {
    public static final class DEFAULT_VALUES {
        public static final class ANZ_MOBILE_PAY {
            public static final boolean ROOT_DETECTION = true; // true, means that the root mounts are hidden.
        }

        public static final class ANZ_SHIELD {
            public static final boolean DEVELOPER_SETTINGS = true; // true, means that the developer settings option are seen as inactive.
        }

        public static final class WESTPAC {
            public static final boolean ROOT_DETECTION = true; // true, means that the root mounts are hidden.
        }
    }

    public static final class KEYS {
        public static final class ANZ_MOBILE_PAY {
            public static final String ROOT_DETECTION = "anzMobilePayRootDetection";
        }

        public static final class ANZ_SHIELD {
            public static final String  DEVELOPER_SETTINGS = "anzShieldDeveloperSettingsInvisible";
        }

        public static final class WESTPAC {
            public static final String ROOT_DETECTION = "westpacRootDetection";
            // public static final String SPOOF_DEVICE = "anzSpoofDevice";
            // public static final String SPOOF_DEVICE_CHOOSER = "anzSpoofDeviceChooser";
        }

        public static final class MAIN {
            public static final String ANZ_MOBILE_PAY = "anzMobilePayPrefs";
            public static final String ANZ_SHIELD = "anzShieldPrefs";
            public static final String WESTPAC = "westpacPrefs";

            // public static final String DEBUG = "debug";
        }
    }
}
