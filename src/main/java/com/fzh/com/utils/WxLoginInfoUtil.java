package com.fzh.com.utils;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取微信的必要请求的数据
 *
 * */
public class WxLoginInfoUtil {
    private static final String AppID = "wx8134c8308241cbe1";
    private static final  String AppSecret ="501d72acc84c53d6807d080aa6d27310";

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        System.out.println("获取openid、session_key等信息");
        System.out.println(getOpenidAndSessionkey());

        System.out.println("获取access_token");
        System.out.println(getAccessToken());
    }

    /**
     * 执行获取openid、session_key等信息  json
     * */
    public static String getOpenidAndSessionkey() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String code="043zXjJd0Q69ou1UZhHd00aeJd0zXjJY";
        String authCode2SessionUrl="https://api.weixin.qq.com/sns/jscode2session?appid="+AppID+"&secret="+AppSecret+"&js_code="+code+"&grant_type=authorization_code";

        ResponseEntity<String> responseEntity = restTemplate().getForEntity(authCode2SessionUrl, String.class);
        return  responseEntity.getBody();
    }

    /**
     * 执行获取openid、session_key等信息  json
     * */
    public static String getOpenidAndSessionkey(String code) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String authCode2SessionUrl="https://api.weixin.qq.com/sns/jscode2session?appid="+AppID+"&secret="+AppSecret+"&js_code="+code+"&grant_type=authorization_code";
        System.out.println(authCode2SessionUrl);
        ResponseEntity<String> responseEntity = restTemplate().getForEntity(authCode2SessionUrl, String.class);
        return  responseEntity.getBody();
    }

    /**
     * 获取access_token
     * */
    public static String getAccessToken() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String authGetAccessTokenUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+AppID+"&secret="+AppSecret;
        ResponseEntity<String> responseEntity = restTemplate().getForEntity(authGetAccessTokenUrl, String.class);
        return  responseEntity.getBody();
    }

    private static RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }


}
