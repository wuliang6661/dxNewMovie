package com.myp.cinema.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Witness on 2019/1/3
 * Describe: 食品
 */
public class HotSellBO implements Serializable {

    /**
     * businessId : 1
     * createTime : 2019-01-16 16:42:59
     * deleteTime : null
     * id : 1
     * itemClass : {"bussinessid":1,"cateId":2,"cinemaId":31,"createTime":"2019-01-16 16:42:57","delFlag":"N","deleteTime":null,"id":1,"imageUrl":"https://hyg.happydoit.com/upload/201901/18rWzLol.jpg","modifyTime":"2019-01-18 10:58:11","name":"常规套餐","sort":1,"status":1,"valid":true,"version":4}
     * merchandiseList : [{"businessId":1,"cardDiscountType":2,"cinemaId":31,"counterPrice":34,"createTime":"2019-01-16 16:44:06","deleteTime":null,"dxId":1,"goodsNum":null,"id":1,"imageUrl":"https://hyg.happydoit.com/upload/201901/18KOfSXu.jpg","inventory":159,"itemClassId":"1","listOrder":0,"modifyTime":"2019-01-18 13:42:52","name":"常规套餐","number":null,"onlinePrice":22,"originalPrice":50,"overInventorySellFlag":0,"packageDetail":{},"type":"package","unit":"","updateTime":"2018-12-29 17:32:54","valid":true,"version":2},{"businessId":1,"cardDiscountType":2,"cinemaId":31,"counterPrice":11,"createTime":"2019-01-16 16:44:06","deleteTime":null,"dxId":2,"goodsNum":null,"id":2,"imageUrl":"https://hyg.happydoit.com/upload/201901/18KjGlIt.jpg","inventory":111,"itemClassId":"2","listOrder":0,"modifyTime":"2019-01-18 13:46:10","name":"常规套餐2","number":null,"onlinePrice":22,"originalPrice":20,"overInventorySellFlag":0,"packageDetail":{},"type":"package","unit":"","updateTime":"2018-12-29 17:32:40","valid":true,"version":2},{"businessId":1,"cardDiscountType":2,"cinemaId":31,"counterPrice":9,"createTime":"2019-01-16 16:44:06","deleteTime":null,"dxId":3,"goodsNum":null,"id":3,"imageUrl":null,"inventory":111,"itemClassId":null,"listOrder":0,"modifyTime":"2019-01-16 16:44:07","name":"常规套餐3","number":null,"onlinePrice":33,"originalPrice":null,"overInventorySellFlag":0,"packageDetail":{},"type":"package","unit":"","updateTime":"2018-12-29 17:31:50","valid":true,"version":1},{"businessId":1,"cardDiscountType":2,"cinemaId":31,"counterPrice":9,"createTime":"2019-01-16 16:44:06","deleteTime":null,"dxId":4,"goodsNum":null,"id":4,"imageUrl":null,"inventory":111,"itemClassId":null,"listOrder":0,"modifyTime":"2019-01-16 16:44:07","name":"常规套餐4","number":null,"onlinePrice":33,"originalPrice":null,"overInventorySellFlag":0,"packageDetail":{},"type":"package","unit":"","updateTime":"2018-12-29 17:32:08","valid":true,"version":1}]
     * modifyTime : 2019-01-16 16:44:09
     * valid : true
     * version : 1
     */

    private int businessId;
    private String createTime;
    private Object deleteTime;
    private int id;
    private ItemClassBean itemClass;
    private String modifyTime;
    private boolean valid;
    private int version;
    private List<MerchandiseListBean> merchandiseList;

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Object deleteTime) {
        this.deleteTime = deleteTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemClassBean getItemClass() {
        return itemClass;
    }

    public void setItemClass(ItemClassBean itemClass) {
        this.itemClass = itemClass;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<MerchandiseListBean> getMerchandiseList() {
        return merchandiseList;
    }

    public void setMerchandiseList(List<MerchandiseListBean> merchandiseList) {
        this.merchandiseList = merchandiseList;
    }

    public static class ItemClassBean implements Serializable{
        /**
         * bussinessid : 1
         * cateId : 2
         * cinemaId : 31
         * createTime : 2019-01-16 16:42:57
         * delFlag : N
         * deleteTime : null
         * id : 1
         * imageUrl : https://hyg.happydoit.com/upload/201901/18rWzLol.jpg
         * modifyTime : 2019-01-18 10:58:11
         * name : 常规套餐
         * sort : 1
         * status : 1
         * valid : true
         * version : 4
         */

        private int bussinessid;
        private int cateId;
        private int cinemaId;
        private String createTime;
        private String delFlag;
        private Object deleteTime;
        private int id;
        private String imageUrl;
        private String modifyTime;
        private String name;
        private int sort;
        private int status;
        private boolean valid;
        private int version;

        public int getBussinessid() {
            return bussinessid;
        }

        public void setBussinessid(int bussinessid) {
            this.bussinessid = bussinessid;
        }

        public int getCateId() {
            return cateId;
        }

        public void setCateId(int cateId) {
            this.cateId = cateId;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    public static class MerchandiseListBean implements Serializable{
        /**
         * businessId : 1
         * cardDiscountType : 2
         * cinemaId : 31
         * counterPrice : 34
         * createTime : 2019-01-16 16:44:06
         * deleteTime : null
         * dxId : 1
         * goodsNum : null
         * id : 1
         * imageUrl : https://hyg.happydoit.com/upload/201901/18KOfSXu.jpg
         * inventory : 159
         * itemClassId : 1
         * listOrder : 0
         * modifyTime : 2019-01-18 13:42:52
         * name : 常规套餐
         * number : null
         * onlinePrice : 22
         * originalPrice : 50
         * overInventorySellFlag : 0
         * packageDetail : {}
         * type : package
         * unit :
         * updateTime : 2018-12-29 17:32:54
         * valid : true
         * version : 2
         */

        private int businessId;
        private int cardDiscountType;
        private int cinemaId;
        private double counterPrice;
        private String createTime;
        private Object deleteTime;
        private int dxId;
        private Object goodsNum;
        private int id;
        private String imageUrl;
        private int inventory;
        private String itemClassId;
        private int listOrder;
        private String modifyTime;
        private String name;
        private Object number;
        private double onlinePrice;
        private double originalPrice;
        private int overInventorySellFlag;
        private List<PackageDetailBean> packageDetail;
        private String type;
        private String unit;
        private String updateTime;
        private boolean valid;
        private int version;

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getCardDiscountType() {
            return cardDiscountType;
        }

        public void setCardDiscountType(int cardDiscountType) {
            this.cardDiscountType = cardDiscountType;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public double getCounterPrice() {
            return counterPrice;
        }

        public void setCounterPrice(double counterPrice) {
            this.counterPrice = counterPrice;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public int getDxId() {
            return dxId;
        }

        public void setDxId(int dxId) {
            this.dxId = dxId;
        }

        public Object getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(Object goodsNum) {
            this.goodsNum = goodsNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public String getItemClassId() {
            return itemClassId;
        }

        public void setItemClassId(String itemClassId) {
            this.itemClassId = itemClassId;
        }

        public int getListOrder() {
            return listOrder;
        }

        public void setListOrder(int listOrder) {
            this.listOrder = listOrder;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public double getOnlinePrice() {
            return onlinePrice;
        }

        public void setOnlinePrice(double onlinePrice) {
            this.onlinePrice = onlinePrice;
        }

        public double getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(double originalPrice) {
            this.originalPrice = originalPrice;
        }

        public int getOverInventorySellFlag() {
            return overInventorySellFlag;
        }

        public void setOverInventorySellFlag(int overInventorySellFlag) {
            this.overInventorySellFlag = overInventorySellFlag;
        }

        public List<PackageDetailBean> getPackageDetail() {
            return packageDetail;
        }

        public void setPackageDetail(List<PackageDetailBean> packageDetail) {
            this.packageDetail = packageDetail;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public static class PackageDetailBean implements Serializable{
        }
    }
}
