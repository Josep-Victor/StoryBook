package com.josepvictorr.storybookapp.util.apihelper;

public class UtilsApi {
    public static final String BASE_URL_API = "http://api-storyboard.herokuapp.com/api/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
