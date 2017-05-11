package com.saxxhw.srn.constant;

import android.os.Environment;

import com.saxxhw.srn.App;

import java.io.File;

/**
 * Created by Saxxhw on 2017/4/11.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class ApiConstants {

    public static final class Paths {
        public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
        public static final String PATH_CACHE = PATH_DATA + "/NetCache";
        public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sr" + File.separator + "SimplifyReader";
    }

    public static final class Strings {
        public static final String BASE_URL = "";
    }

    public static final class Integers {

    }
}
