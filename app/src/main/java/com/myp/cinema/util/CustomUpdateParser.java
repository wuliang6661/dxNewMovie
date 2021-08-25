package com.myp.cinema.util;

import com.alibaba.fastjson.JSON;
import com.myp.cinema.base.MyApplication;
import com.xuexiang.xupdate.entity.UpdateEntity;
import com.xuexiang.xupdate.listener.IUpdateParseCallback;
import com.xuexiang.xupdate.proxy.IUpdateParser;

/**
 * Created by Witness on 2020/7/14
 * Describe:
 */
public class CustomUpdateParser implements IUpdateParser {

    @Override
    public UpdateEntity parseJson(String json) throws Exception {
        UpdateBean result = JSON.parseObject(json, UpdateBean.class);
        if (result != null) {
            if (result.getStatus() == 1) {
                UpdateEntity updateEntity = new UpdateEntity();
                if (result.getData().getAndroid().getVersionCode() > MyApplication.currentVersionCode) {//更新
                    updateEntity.setHasUpdate(true);
                    if (result.getData().getAndroid().getMinVersionCode() > MyApplication.currentVersionCode) {//强制更新
                        updateEntity.setForce(true);
                    }else {
                        updateEntity.setForce(false);
                    }
                    updateEntity.setIsIgnorable(false);
                    updateEntity.setVersionCode(result.getData().getAndroid().getVersionCode());
                    updateEntity.setVersionName(result.getData().getAndroid().getVersionName());
                    updateEntity.setUpdateContent(result.getData().getAndroid().getUpdateMessage());
                    updateEntity.setDownloadUrl(result.getData().getAndroid().getUrl());
                    updateEntity.setSize(0);
                }else {
                    updateEntity.setHasUpdate(false);
                }
                return updateEntity;
            }
        }
        return null;
    }

    @Override
    public void parseJson(String json, IUpdateParseCallback callback) throws Exception {

    }

    @Override
    public boolean isAsyncParser() {
        return false;
    }
}
