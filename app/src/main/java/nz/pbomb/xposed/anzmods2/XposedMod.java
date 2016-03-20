package nz.pbomb.xposed.anzmods2;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMod implements IXposedHookLoadPackage {
    private static final String ANZ_AU_MOBILE_PAY = "com.anz.mobilepay";
    private static final String ANZ_AU_SHIELD = "enterprise.com.anz.shield";

    //private static final String NAB = "au.com.nab.mobile";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if(loadPackageParam.packageName.equals(ANZ_AU_MOBILE_PAY)) {
            /* 1.0.2 Code that is obsolete
            // Modification Check 1
            //XposedHelpers.findAndHookMethod("anj", loadPackageParam.classLoader, "a", XC_MethodReplacement.returnConstant(false));

            // USB Debugging Mode Check 1
            //XposedHelpers.findAndHookMethod("anj", loadPackageParam.classLoader, "c", Context.class, XC_MethodReplacement.returnConstant(true));

            // Modification Check 2
            //XposedHelpers.findAndHookMethod("anh", loadPackageParam.classLoader, "b", XC_MethodReplacement.returnConstant(false));
            */

            // Modification Check - Add Flags to Collection if bad things are detected. Therefore return empty collection
            Object collection = XposedHelpers.newInstance(XposedHelpers.findClass("com.anz.util.Collection", loadPackageParam.classLoader));
            XposedHelpers.findAndHookMethod("va", loadPackageParam.classLoader, "a", XC_MethodReplacement.returnConstant(collection));

            //Disable Debug Enabled Check
            XposedHelpers.findAndHookMethod("amz", loadPackageParam.classLoader, "c", XC_MethodReplacement.returnConstant(false));

        } else if(loadPackageParam.packageName.equals(ANZ_AU_SHIELD)) {
            // Developer Settings Check 1
            XposedHelpers.findAndHookMethod("enterprise.com.anz.shield.a.g", loadPackageParam.classLoader, "a", Context.class, XC_MethodReplacement.returnConstant(false));

        } /* else if(loadPackageParam.packageName.equals(NAB)) {
              // NAB Root Bypass does not work.
              // Application stalls when enabled due to one of the Root method bypass.
              // Almost like its stuck in a loop somewhere and do not have the  time to
              // find out the problem (I actually tried to find the problem but failed).

            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.device.Rooted", loadPackageParam.classLoader, "value", Context.class, XC_MethodReplacement.returnConstant("0"));

            XposedHelpers.findAndHookMethod("au.com.nab.mobile.nabpay.utils.RootUtils", loadPackageParam.classLoader, "a", XC_MethodReplacement.returnConstant(false));
            // XposedHelpers.findAndHookMethod("au.com.nab.mobile.nabpay.utils.RootUtils", loadPackageParam.classLoader, "b", XC_MethodReplacement.returnConstant(false));
            // XposedHelpers.findAndHookMethod("au.com.nab.mobile.nabpay.utils.RootUtils", loadPackageParam.classLoader, "c", XC_MethodReplacement.returnConstant(false));
            // XposedHelpers.findAndHookMethod("au.com.nab.mobile.nabpay.utils.RootUtils", loadPackageParam.classLoader, "d", XC_MethodReplacement.returnConstant(false));

            // Logger Methods hooked to print out debug info from app developers. These do not
            // work when the above hooks are active and I have no idea why

            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "a", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d((String) param.args[0], (String) param.args[1]);
                }
            });

            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "a", String.class, String.class, Throwable.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d((String) param.args[0], (String) param.args[1], (Throwable) param.args[2]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "b", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d((String) param.args[0], (String) param.args[1]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "a", String.class, Exception.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.w((String) param.args[0], (Throwable) param.args[1]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "b", String.class, String.class, Throwable.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d((String) param.args[0], (String) param.args[1]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "c", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d((String) param.args[0], (String) param.args[1]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "d", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d((String) param.args[0], (String) param.args[1]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "a", String.class, String.class, Exception.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.e((String) param.args[0],(String) param.args[1], (Throwable) param.args[2]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "e", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d((String) param.args[0], (String) param.args[1]);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "a", int.class, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.println((int) param.args[0], (String) param.args[1], (String) param.args[2]);
                }
            });
        }*/
    }
}
