package tequila.rest.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tangxinli.android.tequila.dao.models.Channel;
import retrofit.Callback;
import retrofit.http.*;

import java.util.List;

public interface TangxlApi {

    /*
     * User
     */
    @POST("/~/users/auth")
    void auth(
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @POST("/~/users/login")
    void login(
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @POST("/~/users/")
    void getUsers(
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @POST("/~/users/{userId}")
    void getUser(
            @Path("userId") String userId,
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @POST("/~/wechat/bind")
    void bindWechat(
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @GET("/~/fields")
    void fetchFields(
            Callback<JsonArray> callback
    );

     /*
     * POST
     */

    @GET("/~/posts")
    void fetchPosts(
            @Query("type") String type,
            @Query("tags") String tags,
            Callback<JsonArray> callback
    );

    /*
     * Channels
     */

    @GET("/~/channels")
    void fetchChannels(
            Callback<List<Channel>> callback
    );

    @POST("/~/channels")
    void createNewChannel(
            @Body JsonObject body,
            Callback<JsonArray> callback
    );

    @GET("/~/channels/{channelId}")
    void fetchChannel(
            @Path("channelId") String userId,
            Callback<JsonObject> callback
    );

    @POST("/~/channels/{channelId}")
    void updateChannel(
            @Path("channelId") String userId,
            Callback<JsonObject> callback
    );

    /*
     * Tweets
     */
    @GET("/~/tweets")
    void fetchTweets(
            @Query("channelId") String channelId,
            Callback<JsonArray> callback
    );

    @POST("/~/tweets")
    void createNewTweet(
            @Query("channelId") String channelId,
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @GET("/~/tweets/{tweetId}")
    void fetchTweet(
            @Path("tweetId") String userId,
            Callback<JsonObject> callback
    );

    @POST("/~/tweets/{tweetId}")
    void updateTweet(
            @Path("tweetId") String userId,
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @DELETE("/~/tweets/{tweetId}")
    void updateTweet(
            Callback<JsonObject> callback
    );

    /*
     * Order
     */
    @GET("/~/orders")
    void fetchOrders(
            Callback<JsonArray> callback
    );

    @POST("/~/orders")
    void createOrder(
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @GET("/~/orders/init")
    void initOrder(
            @Query("topic") String topic,
            @Query("date") String date,
            @Query("time") String time,
            Callback<JsonObject> callback
    );

    @GET("/~/orders/{orderId}")
    void fetchOrder(
            Callback<JsonObject> callback
    );

    @POST("/~/orders/{orderId}")
    void updateOrder(
            @Body JsonObject body,
            Callback<JsonObject> callback
    );

    @GET("/~/orders/{orderId}/notify")
    void fetchOrderNotification(
            Callback<JsonObject> callback
    );

    @GET("/~/orders/{orderId}/call")
    void callExpert(
            Callback<JsonObject> callback
    );

    @GET("/~/orders/{orderId}/callUser")
    void callUser(
            Callback<JsonObject> callback
    );
}