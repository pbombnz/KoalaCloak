package nz.pbomb.xposed.anzmods2;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMod implements IXposedHookLoadPackage {
    private static final String ANZ_AU_MOBILE_PAY = "com.anz.mobilepay";
    private static final String ANZ_AU_SHIELD = "enterprise.com.anz.shield";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(loadPackageParam.packageName.equals(ANZ_AU_MOBILE_PAY)) {
            // Modification Check 1
            XposedHelpers.findAndHookMethod("anj", loadPackageParam.classLoader, "a", XC_MethodReplacement.returnConstant(false));

            // USB Debugging Mode Check 1
            XposedHelpers.findAndHookMethod("anj", loadPackageParam.classLoader, "c", Context.class, XC_MethodReplacement.returnConstant(true));

            // Modification Check 2
            XposedHelpers.findAndHookMethod("anh", loadPackageParam.classLoader, "b", XC_MethodReplacement.returnConstant(false));
        } else if(loadPackageParam.packageName.equals(ANZ_AU_SHIELD)) {
            // Developer Settings Check 1
            XposedHelpers.findAndHookMethod("enterprise.com.anz.shield.a.g", loadPackageParam.classLoader, "a", Context.class, XC_MethodReplacement.returnConstant(false));

        }
    }
}
