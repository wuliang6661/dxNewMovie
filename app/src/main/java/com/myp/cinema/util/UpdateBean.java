package com.myp.cinema.util;

import java.io.Serializable;

/**
 * Created by Witness on 2020/7/14
 * Describe:
 */
public class UpdateBean implements Serializable {

    private int status;
    private DataBean data;
    private String message;
    private int code;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {

        private AndroidBean android;
        private String updateMessage;
        private String apple;
        private int show;
        private int isForce;

        public AndroidBean getAndroid() {
            return android;
        }

        public void setAndroid(AndroidBean android) {
            this.android = android;
        }

        public String getUpdateMessage() {
            return updateMessage;
        }

        public void setUpdateMessage(String updateMessage) {
            this.updateMessage = updateMessage;
        }

        public String getApple() {
            return apple;
        }

        public void setApple(String apple) {
            this.apple = apple;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public static class AndroidBean {

            private int versionCode;
            private String versionName;
            private String updateMessage;
            private int minVersionCode;
            private String minVersionName;
            private String url;

            public int getVersionCode() {
                return versionCode;
            }

            public void setVersionCode(int versionCode) {
                this.versionCode = versionCode;
            }

            public String getVersionName() {
                return versionName;
            }

            public void setVersionName(String versionName) {
                this.versionName = versionName;
            }

            public String getUpdateMessage() {
                return updateMessage;
            }

            public void setUpdateMessage(String updateMessage) {
                this.updateMessage = updateMessage;
            }

            public int getMinVersionCode() {
                return minVersionCode;
            }

            public void setMinVersionCode(int minVersionCode) {
                this.minVersionCode = minVersionCode;
            }

            public String getMinVersionName() {
                return minVersionName;
            }

            public void setMinVersionName(String minVersionName) {
                this.minVersionName = minVersionName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
