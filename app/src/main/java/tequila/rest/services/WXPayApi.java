package tequila.rest.services;

import com.tangxinli.android.tequila.rest.models.WXUnifiedOrderRequest;
import com.tangxinli.android.tequila.rest.models.WXUnifiedOrderResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface WXPayApi {
    //微信支付
    @POST("/pay/unifiedorder")
    void requestWXUnifiedOrder(
            @Body WXUnifiedOrderRequest body,
            Callback<WXUnifiedOrderResponse> callback
    );
}
