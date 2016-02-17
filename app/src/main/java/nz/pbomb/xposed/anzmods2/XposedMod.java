package nz.pbomb.xposed.anzmods2;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMod implements IXposedHookLoadPackage {
    private static final String ANZ_AU_MOBILE_PAY = "com.anz.mobilepay";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(!loadPackageParam.packageName.equals(ANZ_AU_MOBILE_PAY)) {
            return;
        }

        // Modification Check 1
        XposedHelpers.findAndHookMethod("anj", loadPackageParam.classLoader, "a", XC_MethodReplacement.returnConstant(false));

        // USB Debugging Mode Check 2
        XposedHelpers.findAndHookMethod("anj", loadPackageParam.classLoader, "c", Context.class, XC_MethodReplacement.returnConstant(true));

        // Modification Check 2
        XposedHelpers.findAndHookMethod("anh", loadPackageParam.classLoader, "b", XC_MethodReplacement.returnConstant(false));

    }
}
