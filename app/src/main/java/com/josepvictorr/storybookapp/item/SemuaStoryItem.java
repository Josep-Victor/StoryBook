package com.josepvictorr.storybookapp.item;

import com.google.gson.annotations.SerializedName;

public class SemuaStoryItem {
    @SerializedName("id")
    private String id;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("status")
    private String status;

    @SerializedName("id_user")
    private String id_user;

    @SerializedName("username")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString(){
        return
                "SemuaStoryItem{" +
                        "id = '" + id + '\'' +
                        ",created_at = '" + created_at + '\'' +
                        ",updated_at = '" + updated_at + '\'' +
                        ",status = '" + status + '\'' +
                        ",id_user = '" + id_user + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}
