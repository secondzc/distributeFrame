package com.tongyuan.distributeFrame.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhangcy on 2018/2/13
 */
public final class PropertiesUtil extends PropertyPlaceholderConfigurer {
        private static Map<String, String> ctxPropertiesMap;

        @Override
        protected void loadProperties(Properties props) throws IOException {
            super.loadProperties(props);
            ctxPropertiesMap = new HashMap<String, String>();
            for(Object key:props.keySet()){
                String keyStr = key.toString();
                String value = props.getProperty(keyStr);
                ctxPropertiesMap.put(keyStr,value);
            }
        }

        /**
         * Get a value based on key , if key does not exist , null is returned
         *
         * @param key
         * @return
         */
        public static String getString(String key) {
            try {
                return ctxPropertiesMap.get(key);
            } catch (MissingResourceException e) {
                return null;
            }
        }

        /**
         * Get a value based on key , if key does not exist , null is returned
         *
         * @param key
         * @return
         */
        public static String getString(String key, String defaultValue) {
            try {
                String value = ctxPropertiesMap.get(key);
                if (!DataUtil.isNotEmpty(value)) {
                    return defaultValue;
                }
                return value;
            } catch (MissingResourceException e) {
                return defaultValue;
            }
        }

        /**
         * 根据key获取值
         *
         * @param key
         * @return
         */
        public static int getInt(String key) {
            return Integer.parseInt(ctxPropertiesMap.get(key));
        }

        /**
         * 根据key获取值
         *
         * @param key
         * @param defaultValue
         * @return
         */
        public static int getInt(String key, int defaultValue) {
            String value = ctxPropertiesMap.get(key);
            if (!DataUtil.isNotEmpty(value)) {
                return defaultValue;
            }
            return Integer.parseInt(value);
        }

        /**
         * 根据key获取值
         *
         * @param key
         * @param defaultValue
         * @return
         */
        public static boolean getBoolean(String key, boolean defaultValue) {
            String value = ctxPropertiesMap.get(key);
            if (!DataUtil.isNotEmpty(value)) {
                return defaultValue;
            }
            return new Boolean(value);
        }
}
