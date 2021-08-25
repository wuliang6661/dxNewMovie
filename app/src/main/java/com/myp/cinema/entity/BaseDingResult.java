package com.myp.cinema.entity;

/**
 * Created by wuliang on 2017/6/16.
 * <p>
 * 鼎新接口父类
 */

public class BaseDingResult<T> {


    /**
     * res : {"status":1,"errorMessage":null,"errorCode":null,"data":[{"x":"1","y":"1","rowValue":"0","columnValue":"0","cineSeatId":"2945","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"2","rowValue":"0","columnValue":"0","cineSeatId":"2946","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"3","rowValue":"0","columnValue":"0","cineSeatId":"2947","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"4","rowValue":"1","columnValue":"1","cineSeatId":"2948","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"5","rowValue":"0","columnValue":"0","cineSeatId":"2949","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"6","rowValue":"1","columnValue":"2","cineSeatId":"2950","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"7","rowValue":"1","columnValue":"3","cineSeatId":"2951","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"8","rowValue":"1","columnValue":"4","cineSeatId":"2952","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"9","rowValue":"1","columnValue":"5","cineSeatId":"2953","seatStatus":"selled","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"10","rowValue":"1","columnValue":"6","cineSeatId":"2954","seatStatus":"selled","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"11","rowValue":"1","columnValue":"7","cineSeatId":"2955","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"12","rowValue":"1","columnValue":"8","cineSeatId":"2956","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"13","rowValue":"0","columnValue":"0","cineSeatId":"2957","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"14","rowValue":"1","columnValue":"9","cineSeatId":"2958","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"15","rowValue":"0","columnValue":"0","cineSeatId":"2959","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"16","rowValue":"0","columnValue":"0","cineSeatId":"2960","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"17","rowValue":"0","columnValue":"0","cineSeatId":"2961","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"1","rowValue":"0","columnValue":"0","cineSeatId":"2962","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"2","rowValue":"0","columnValue":"0","cineSeatId":"2963","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"3","rowValue":"2","columnValue":"1","cineSeatId":"2964","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"4","rowValue":"2","columnValue":"2","cineSeatId":"2965","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"5","rowValue":"0","columnValue":"0","cineSeatId":"2966","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"6","rowValue":"2","columnValue":"3","cineSeatId":"2967","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"7","rowValue":"2","columnValue":"4","cineSeatId":"2968","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"8","rowValue":"2","columnValue":"5","cineSeatId":"2969","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"9","rowValue":"2","columnValue":"6","cineSeatId":"2970","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"10","rowValue":"2","columnValue":"7","cineSeatId":"2971","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"11","rowValue":"2","columnValue":"8","cineSeatId":"2972","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"12","rowValue":"2","columnValue":"9","cineSeatId":"2973","seatStatus":"ok","type":"danren","pairValue":"","areaId":null}]}
     */

    private Res<T> res;

    public Res<T> getRes() {
        return res;
    }

    public void setRes(Res<T> res) {
        this.res = res;
    }

    public static class Res<T> {
        /**
         * status : 1
         * errorMessage : null
         * errorCode : null
         * data : [{"x":"1","y":"1","rowValue":"0","columnValue":"0","cineSeatId":"2945","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"2","rowValue":"0","columnValue":"0","cineSeatId":"2946","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"3","rowValue":"0","columnValue":"0","cineSeatId":"2947","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"4","rowValue":"1","columnValue":"1","cineSeatId":"2948","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"5","rowValue":"0","columnValue":"0","cineSeatId":"2949","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"6","rowValue":"1","columnValue":"2","cineSeatId":"2950","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"7","rowValue":"1","columnValue":"3","cineSeatId":"2951","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"8","rowValue":"1","columnValue":"4","cineSeatId":"2952","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"9","rowValue":"1","columnValue":"5","cineSeatId":"2953","seatStatus":"selled","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"10","rowValue":"1","columnValue":"6","cineSeatId":"2954","seatStatus":"selled","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"11","rowValue":"1","columnValue":"7","cineSeatId":"2955","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"12","rowValue":"1","columnValue":"8","cineSeatId":"2956","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"13","rowValue":"0","columnValue":"0","cineSeatId":"2957","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"14","rowValue":"1","columnValue":"9","cineSeatId":"2958","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"1","y":"15","rowValue":"0","columnValue":"0","cineSeatId":"2959","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"16","rowValue":"0","columnValue":"0","cineSeatId":"2960","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"1","y":"17","rowValue":"0","columnValue":"0","cineSeatId":"2961","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"1","rowValue":"0","columnValue":"0","cineSeatId":"2962","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"2","rowValue":"0","columnValue":"0","cineSeatId":"2963","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"3","rowValue":"2","columnValue":"1","cineSeatId":"2964","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"4","rowValue":"2","columnValue":"2","cineSeatId":"2965","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"5","rowValue":"0","columnValue":"0","cineSeatId":"2966","seatStatus":"ok","type":"road","pairValue":"","areaId":null},{"x":"2","y":"6","rowValue":"2","columnValue":"3","cineSeatId":"2967","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"7","rowValue":"2","columnValue":"4","cineSeatId":"2968","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"8","rowValue":"2","columnValue":"5","cineSeatId":"2969","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"9","rowValue":"2","columnValue":"6","cineSeatId":"2970","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"10","rowValue":"2","columnValue":"7","cineSeatId":"2971","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"11","rowValue":"2","columnValue":"8","cineSeatId":"2972","seatStatus":"ok","type":"danren","pairValue":"","areaId":null},{"x":"2","y":"12","rowValue":"2","columnValue":"9","cineSeatId":"2973","seatStatus":"ok","type":"danren","pairValue":"","areaId":null}]
         */

        private String status;
        private String errorMessage;
        private String errorCode;
        private T data;


        public boolean surcess() {
            return "1".equals(status);
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
}
