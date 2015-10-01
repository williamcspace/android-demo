package tequila.rest;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.tangxinli.android.tequila.Config;
import com.tangxinli.android.tequila.common.utils.CookieHandler;
import com.tangxinli.android.tequila.rest.services.TangxlApi;
import com.tangxinli.android.tequila.rest.services.WXPayApi;
import com.tangxinli.android.tequila.rest.services.WechatApi;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.SimpleXMLConverter;

import java.io.IOException;

public class RestServiceProvider {
    private static final String TAG = RestServiceProvider.class.getSimpleName();

    private static TangxlApi sTangxlApi;
    private static WechatApi sWechatApi;
    private static WXPayApi sWXPayApi;

    private static void buildTangxlApi(){

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                CookieHandler.processCookie(originalResponse.headers(Config.COOKIE_KEY));
                return originalResponse;
            }
        });

        sTangxlApi = new RestAdapter.Builder()
                .setEndpoint(Config.TXL_API_URL)
                .setClient(new OkClient(client))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("X-Requested-With", "XMLHttpRequest");
                        request.addHeader("Accept", "application/json");
                        request.addHeader("cookie", CookieHandler.getSessionCookie());
                    }
                })
                .build()
                .create(TangxlApi.class);
    }

    private static void buildWechatApi() {
        sWechatApi = new RestAdapter.Builder()
                .setEndpoint(Config.WX_API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .build()
                .create(WechatApi.class);
    }

    private static void buildWXPayApi() {
        sWXPayApi = new RestAdapter.Builder()
                .setEndpoint(Config.WX_PAY_API_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .setConverter(new SimpleXMLConverter())
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Accept", "application/json");
                        request.addHeader("Content-type", "application/json;utf-8");
                    }
                })
                .build()
                .create(WXPayApi.class);
    }

    public static synchronized TangxlApi getTangxlApi() {
        if (sTangxlApi == null) {
            buildTangxlApi();
        }
        return sTangxlApi;
    }

    public static synchronized WechatApi getWechatApi() {
        if (sWechatApi == null) {
            buildWechatApi();
        }
        return sWechatApi;
    }

    public static synchronized WXPayApi getWXPayApi() {
        if (sWXPayApi == null) {
            buildWXPayApi();
        }
        return sWXPayApi;
    }
}
