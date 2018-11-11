package com.example.sinukov.acatalog;

public class Constants {

    public static final class MENU {
        public static final int MENU_DELETE = 1;
        public static final int MENU_EDIT = 2;
    }

    public static final class URL {
        public static final String BASE_URL = "http://www.autocentre.ua/";
        public static final String CATALOG = BASE_URL + "catalog/";
    }

    public static final class PARSE {
        public static final String ELEMENT_BY_ID = "allMarks";
        public static final String ELEMENT_BY_TAG = "img";
        public static final String ELEMENT_ATTTR_ALT = "alt";
        public static final String ELEMENT_ATTTR_SRC = "src";
        public static final String FILE_DIR = "sdcard/auto_catalog/brands";
        public static final String FILE_NAME = System.currentTimeMillis() + ".jpg";
    }

    public static final class DB {
        public static final String DATABASE_NAME = "auto_catalog.db";
        public static final int DATABASE_VERSION = 1;
    }

    public static final class ACTIVITY_FOR_RESULT {
        public static final int RESULT_LOAD_IMAGE = 1;
    }

}
