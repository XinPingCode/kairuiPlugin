package kairuiPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
//import sun.rmi.runtime.Log;
import android.app.Activity;
import android.util.Log;
import java.io.File;
import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Build;
import android.content.ComponentName;
import android.widget.Toast;
import android.util.Log;
import android.content.Context;

/**
 * This class echoes a string called from JavaScript.
 */
public class kairuiPlugin extends CordovaPlugin {
    CallbackContext cbc;
    String[] permissions = { Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE };
    int nu;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.cbc = callbackContext;
        if (action.equals("startService")) {
            String tel = args.getString(0);
            String password = args.getString(1);
            this.startService(tel, password, callbackContext);
            return true;
        } else if (action.equals("startRTVideo")) {
            String tel = args.getString(0);
            String password = args.getString(1);
            String callNum = args.getString(2);
            this.startRTVideo(tel, password, callNum, callbackContext);
            return true;
        } else if (action.equals("startRTVideoName")) {
            String tel = args.getString(0);
            String password = args.getString(1);
            String callNum = args.getString(2);
            String callName = args.getString(3);
            this.startRTVideoName(tel, password, callNum, callName, callbackContext);
            return true;
        } else if (action.equals("exitApp")) {
            String message = args.getString(0);
            this.exitApp(callbackContext);
            return true;
        } else
         if (action.equals("isInstall")) {
            String message = args.getString(0);
            this.isInstall(callbackContext);
            return true;
        } 
        else if (action.equals("startLocation")) {
            String message = args.getString(0);
            this.startLocation(message, callbackContext);
            return true;
        }
        return false;
    }

    private void startService(String tel, String password, CallbackContext callbackContext) {
        Intent intent = new Intent();
        intent.setAction("action.caripower.module.MainServices");
        intent.setPackage("com.caripower.module");
        intent.putExtra("tel", tel);// 账号（必填项）  
        intent.putExtra("password", password);// 密码（必填项）
        Log.v("tel", tel);
        Log.v("password", password);
        Context context = this.cordova.getActivity().getApplicationContext();
        context.startService(intent);
        callbackContext.success("startService");
    }

    private void startRTVideo(String tel, String password, String callNum, CallbackContext callbackContext) {
        Intent intent = new Intent("action.cari.VideoActivity");
        intent.setPackage("com.caripower.module");
        intent.putExtra("terminal", tel); // 帐号（必填项）
        intent.putExtra("password", password); // 密码（必填项）
        intent.putExtra("callNum", callNum); // 被叫人号码（必填项）
        intent.putExtra("appType", 2); // 默认值 （必填项）
        Log.v("tel", tel);
        Log.v("password", password);
        Log.v("callNum", callNum);
        // Context context = this.cordova.getActivity().getApplicationContext();
        // context.startActivity(intent);
        this.cordova.startActivityForResult(this, intent, 0);
        callbackContext.success("startRTVideo");
    }

    private void startRTVideoName(String tel, String password, String callNum, String callName,
            CallbackContext callbackContext) {
        Intent intent = new Intent("action.cari.VideoActivity");
        intent.setPackage("com.caripower.module");
        intent.putExtra("terminal", tel); // 帐号（必填项）
        intent.putExtra("password", password); // 密码（必填项）
        intent.putExtra("callNum", callNum); // 被叫人号码（必填项）
        intent.putExtra("callName", callName);// 被叫人中文名称（选填项）
        intent.putExtra("appType", 2); // 默认值 （必填项）
        Log.v("tel", tel);
        Log.v("password", password);
        Log.v("callNum", callNum);
        Log.v("callName", callName);
        // Context context = this.cordova.getActivity().getApplicationContext();
        // context.startActivity(intent);
        this.cordova.startActivityForResult(this, intent, 0);
        callbackContext.success("startRTVideoName");
    }

    private void startLocation(String message, CallbackContext callbackContext) {
        Intent intentLocation = new Intent();
        intentLocation.setAction("com.cari.loc");
        try {
             nu = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            Log.v("isLoc", "not num");
        }
        Log.v("isLoc", ""+nu);
        intentLocation.putExtra("isLoc", nu);// 是否定位: 0 关闭, 1 开启（必填项）
        
       
         Context context = this.cordova.getActivity().getApplicationContext();
         context.sendBroadcast(intentLocation);
        callbackContext.success("startLocation");
    }

    private void exitApp(CallbackContext callbackContext) {
        Intent intent = new Intent("com.caripower.module.other.exit");
        intent.putExtra("appName", "other");// 默认值 （必填项）
        Context context = this.cordova.getActivity().getApplicationContext();
        context.sendBroadcast(intent);
        Log.v("test", "exitApp");
        //this.cordova.startActivityForResult(this, intent, 0);
        callbackContext.success("exitApp");
    }

    private void isInstall(CallbackContext callbackContext) {

        getPermission();
        String backb = "unknow";
        if (isInstallPackage("com.caripower.module")) {
            backb = "true";
        } else {
            backb = "false";
        }
        callbackContext.success(backb);
    }

    private boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
        // cn.redcdn.hvs
    }

    public void getPermission() {
        if (hasPermisssion()) {
        } else {
            PermissionHelper.requestPermissions(this, 0, permissions);
        }
    }

    public boolean hasPermisssion() {
        for (String p : permissions) {
            if (!PermissionHelper.hasPermission(this, p)) {
                return false;
            }
        }
        return true;
    }

}
