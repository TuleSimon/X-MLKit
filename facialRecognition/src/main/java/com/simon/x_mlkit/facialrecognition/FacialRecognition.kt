package com.simon.x_mlkit.facialrecognition

import com.huawei.hms.mlsdk.common.MLApplication

class FacialRecognition {

    fun setUpAuth(accessToken:String){
        MLApplication.getInstance().setAccessToken(accessToken);
        MLApplication.getInstance().setUserRegion(MLApplication.REGION_DR_UNKNOWN);
    }

}