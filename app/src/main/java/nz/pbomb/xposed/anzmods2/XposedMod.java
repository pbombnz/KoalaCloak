package nz.pbomb.xposed.anzmods2;

import android.content.Context;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedMod implements IXposedHookLoadPackage {
    private static final boolean DEBUG_TRACE = false;

    private static final String ANZ_AU_MOBILE_PAY = "com.anz.mobilepay";
    private static final String ANZ_AU_SHIELD = "enterprise.com.anz.shield";

    private static final String NAB = "au.com.nab.mobile";

    private static final String WESTPAC = "org.westpac.bank";

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals(ANZ_AU_MOBILE_PAY)) {
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

        } else if (loadPackageParam.packageName.equals(ANZ_AU_SHIELD)) {
            // Developer Settings Check 1
            XposedHelpers.findAndHookMethod("enterprise.com.anz.shield.a.g", loadPackageParam.classLoader, "a", Context.class, XC_MethodReplacement.returnConstant(false));
        } else if (loadPackageParam.packageName.equals(WESTPAC)) {
            XposedHelpers.findAndHookMethod("com.splunk.mint.Utils", loadPackageParam.classLoader, "checkForRoot", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if(DEBUG_TRACE) {
                        //XposedBridge.log("Rooted.value() returns \"0\".");
                        //Log.d("SuperKiwi", Log.getStackTraceString(new Exception()));
                    }
                    param.setResult(false);
                }
            });

            XposedHelpers.findAndHookMethod("com.westpac.banking.android.commons.environment.AndroidEnvironmentProvider", loadPackageParam.classLoader, "isDeviceRooted", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if(DEBUG_TRACE) {
                        //XposedBridge.log("Rooted.value() returns \"0\".");
                        //Log.d("SuperKiwi", Log.getStackTraceString(new Exception()));
                    }
                    param.setResult(false);
                }
            });
        }
    }
        /*else if(loadPackageParam.packageName.equals(NAB)) {
        // NAB Root Bypass does not work.
        // Application stalls when enabled due to one of the Root method bypass.
        // Almost like its stuck in a loop somewhere and do not have the  time to
        // find out the problem (I actually tried to find the problem but failed).


        // ROOT METHODS

            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.device.Rooted", loadPackageParam.classLoader, "value", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if(DEBUG_TRACE) {
                        XposedBridge.log("Rooted.value() returns \"0\".");
                        Log.d("SuperKiwi", Log.getStackTraceString(new Exception()));
                    }
                    param.setResult("0");
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.nabpay.utils.RootUtils", loadPackageParam.classLoader, "b", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.nabpay.utils.RootUtils", loadPackageParam.classLoader, "c", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
            XposedHelpers.findAndHookMethod("au.com.nab.mobile.nabpay.utils.RootUtils", loadPackageParam.classLoader, "d", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if(DEBUG_TRACE) {
                        XposedBridge.log("RootUtils.d() returns false.");
                        Log.d("Xposed", Log.getStackTraceString(new Exception()));
                    }
                    param.setResult(false);
                }
            });

            // STRING DECODE (OBST.)

            // b0449щ04490449щщ0449(String, Char)
            XposedHelpers.findAndHookMethod("uuuuuu.nnunuu", loadPackageParam.classLoader, "b0449щ04490449щщ0449", String.class, char.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    decodeObfuscatedString(param.getResult());
                }
            });
            XposedHelpers.findAndHookMethod("uuuuuu.nuunuu", loadPackageParam.classLoader, "b0449щ04490449щщ0449", String.class, char.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    decodeObfuscatedString(param.getResult());
                }
            });
            XposedHelpers.findAndHookMethod("uuuuuu.uunuuu", loadPackageParam.classLoader, "b0449щ04490449щщ0449", String.class, char.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    decodeObfuscatedString(param.getResult());
                }
            });
            XposedHelpers.findAndHookMethod("uuuuuu.uuunuu", loadPackageParam.classLoader, "b0449щ04490449щщ0449", String.class, char.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    decodeObfuscatedString(param.getResult());
                }
            });

            // bщ044904490449щщ0449(String, Char, Char)
            XposedHelpers.findAndHookMethod("uuuuuu.nnnnuu", loadPackageParam.classLoader, "bщ044904490449щщ0449", String.class, char.class, char.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    decodeObfuscatedString(param.getResult());
                }
            });
            XposedHelpers.findAndHookMethod("uuuuuu.nunnuu", loadPackageParam.classLoader, "bщ044904490449щщ0449", String.class, char.class, char.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    decodeObfuscatedString(param.getResult());
                }
            });
            XposedHelpers.findAndHookMethod("uuuuuu.uunnuu", loadPackageParam.classLoader, "bщ044904490449щщ0449", String.class, char.class, char.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    decodeObfuscatedString(param.getResult());
                }
            });






            // XPOSED NATIVE CALL HOOKS

            // byte[] ISO9797MAC(byte[] bArr, byte[] bArr2, byte[] bArr3);
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "ISO9797MAC", byte[].class, byte[].class, byte[].class, new XC_MethodHook() {
                @Override

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "ISO9797MAC");
                    Log.d("Xposed", "\tParam 1 (byte[]): "+param.args[0]);
                    Log.d("Xposed", "\tParam 2 (byte[]): "+param.args[1]);
                    Log.d("Xposed", "\tParam 3 (byte[]): "+param.args[2]);
                    Log.d("Xposed", "\tReturn (byte[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });

            // void bÐ«042BÐ«Ð«042BÐ«();
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "bЫ042BЫЫ042BЫ", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "bЫ042BЫЫ042BЫ");
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });

           // byte[] decryptData(byte[] bArr, byte[] bArr2);
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "decryptData", byte[].class, byte[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "decryptData");
                    Log.d("Xposed", "\tParam 1 (byte[]): "+param.args[0]);
                    Log.d("Xposed", "\tParam 2 (byte[]): "+param.args[1]);
                    Log.d("Xposed", "\tReturn (byte[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });

            //byte[] encryptData(byte[] bArr, byte[] bArr2);
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "encryptData", byte[].class, byte[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "encryptData");
                    Log.d("Xposed", "\tParam 1 (byte[]): "+param.args[0]);
                    Log.d("Xposed", "\tParam 2 (byte[]): "+param.args[1]);
                    Log.d("Xposed", "\tReturn (byte[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });


            //byte[] generateDek();
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "generateDek", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "generateDek");
                    Log.d("Xposed", "\tReturn (byte[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });

            //Object[] onboardLuk(byte[] bArr);
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "onboardLuk", byte[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "onboardLuk");
                    Log.d("Xposed", "\tParam 1 (byte[]): "+param.args[0]);
                    Log.d("Xposed", "\tReturn (Object[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });

            // byte[] tripleDES(byte[] bArr, byte[] bArr2, byte[] bArr3);
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "tripleDES", byte[].class, byte[].class, byte[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "tripleDES");
                    Log.d("Xposed", "\tParam 1 (byte[]): "+param.args[0]);
                    Log.d("Xposed", "\tParam 2 (byte[]): "+param.args[1]);
                    Log.d("Xposed", "\tParam 3 (byte[]): "+param.args[2]);
                    Log.d("Xposed", "\tReturn (byte[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });

            // byte[] upgradeDek(byte[] bArr, String str, String str2);
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "upgradeDek", byte[].class, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "upgradeDek");
                    Log.d("Xposed", "\tParam 1 (byte[]): "+param.args[0]);
                    Log.d("Xposed", "\tParam 2 (String): "+param.args[1]);
                    Log.d("Xposed", "\tParam 3 (String): "+param.args[2]);
                    Log.d("Xposed", "\tReturn (byte[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });


            // Object[] upgradeLuk(Object[] objArr, String str, String str2);
            XposedHelpers.findAndHookMethod("com.visa.cbp.sdk.crypto.JniCrypto", loadPackageParam.classLoader, "upgradeLuk", Object[].class, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Log.d("Xposed", "upgradeLuk");
                    Log.d("Xposed", "\tParam 1 (Object[]): "+param.args[0]);
                    Log.d("Xposed", "\tParam 2 (String): "+param.args[1]);
                    Log.d("Xposed", "\tParam 3 (String): "+param.args[2]);
                    Log.d("Xposed", "\tReturn (Object[]): "+param.getResult());
                    Log.d("Xposed", "\tCall Stack: "+Log.getStackTraceString(new Exception()));
                }
            });


            // File Hooks - Shows what files are being read at runtime
            XposedHelpers.findAndHookConstructor("java.io.File", loadPackageParam.classLoader, String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("File(String) Hook: "+param.args[0]);
                        }
                    });
            XposedHelpers.findAndHookConstructor("java.io.File", loadPackageParam.classLoader, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("File(String, String) Hook: "+param.args[0]+", "+param.args[1]);
                }
            });


            // Logger Methods hooked to print out debug info from app developers. These do not
            // work when the above hooks are active and I have no idea why

            XposedHelpers.findAndHookMethod("au.com.nab.mobile.common.utils.Logger", loadPackageParam.classLoader, "a", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
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
                    Log.println(Log.INFO, (String) param.args[1], (String) param.args[2]);
                }
            });
        }
    }

    private void decodeObfuscatedString(Object string) {
        XposedBridge.log("Decode String: "+string);
        if(DEBUG_TRACE) {
            XposedBridge.log(Log.getStackTraceString(new Exception()));
        }
    }*/
}
