package tequila.rest.services;

import com.google.gson.JsonObject;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface WechatApi {

    //微信登入
    @GET("/sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type=authorization_code")
    void requestWXAccessToken(
            @Path("appId") String appId,
            @Path("secret") String secret,
            @Path("code") String code,
            Callback<JsonObject> callback
    );

    @GET("/sns/oauth2/refresh_token?appid={appId}&grant_type=refresh_token&refresh_token={refreshToken}")
    void refreshWXAccessToken(
            @Path("appId") String appId,
            @Path("refreshToken") String refreshToken,
            Callback<JsonObject> callback
    );

    @GET("/sns/auth?access_token={accessToken}&openid={openId}")
    void isWXAccessTokenValid(
            @Path("accessToken") String accessToken,
            @Path("openId") String openId,
            Callback<JsonObject> callback
    );

    @GET("/sns/userinfo?access_token={accessToken}&openid={openId}")
    void requestWXUserInfo(
            @Path("accessToken") String accessToken,
            @Path("openId") String openId,
            Callback<JsonObject> callback
    );
}
