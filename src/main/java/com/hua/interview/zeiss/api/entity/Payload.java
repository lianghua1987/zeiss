package com.hua.interview.zeiss.api.entity;

import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("machine_id")
    private String machineId;
    private String id;
    @SerializedName("timestamp")
    private String timeStamp;
    private String status;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
