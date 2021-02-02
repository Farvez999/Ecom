package com.korearbazar.ecom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMessageResponse {
    @SerializedName("data")
    @Expose
    private SendMessageData data;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public SendMessageData getData() {
        return data;
    }

    public void setData(SendMessageData data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
