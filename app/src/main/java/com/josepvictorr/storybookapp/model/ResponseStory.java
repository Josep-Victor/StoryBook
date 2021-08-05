package com.josepvictorr.storybookapp.model;

import com.google.gson.annotations.SerializedName;
import com.josepvictorr.storybookapp.item.SemuaStoryItem;

import java.util.List;

public class ResponseStory {
    @SerializedName("semuastory")
    private List<SemuaStoryItem> semuastory;

    public List<SemuaStoryItem> getSemuastory() {
        return semuastory;
    }

    public void setSemuastory(List<SemuaStoryItem> semuastory) {
        this.semuastory = semuastory;
    }

    @Override
    public String toString(){
        return
                "ResponseStory{" +
                        "semuastory = '" + semuastory +
                        "}";
    }
}
