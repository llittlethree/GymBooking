package com.fzh.com.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private final  static String SING = "zhangxiaosan";
    private final  static Integer DEFAULT_EXPIRES_TIME = 7 ;
    /**
     * 生成token
     * @param map Map<String,String> 存放在token中的payload中的数据
     * @return  token String 生成的token
     */
    public static String createToken(Map<String,String> map){
        /**生成token*/
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,DEFAULT_EXPIRES_TIME); //7天之后的时间
        JWTCreator.Builder builder = JWT.create();
        /*将存在payload部分的数据Map遍历存放*/
        map.forEach((k,v)->{
            if (v != null) {
                builder.withClaim(k, v);
            }
        });
        builder.withExpiresAt(cal.getTime()); //令牌过期时间
        String token = builder.sign(Algorithm.HMAC256(SING));//signature签名部分,其中的参数为密钥，开始生成token
        return token;
    }

    /**
     * 验证签名
     * @param  token String  需要验证的token
     * @return resMap Map 验证结果Map  {"code":"0000","msg":"成功"}
     * */
    public static Map verifyToken(String token){
        Map map = new HashMap();
        try {
            DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
            map.put("code","0000");
            map.put("msg","成功");
            map.put("data",verify);
        }catch (AlgorithmMismatchException e){
            map.put("code","1001");
            map.put("msg","验证算法不匹配");
            map.put("data",null);
        }catch (TokenExpiredException e){
            map.put("code","1002");
            map.put("msg","token已失效");
            map.put("data",null);
        }catch (JWTDecodeException e) {
            map.put("code","1003");
            map.put("msg","解码失败，token无效");
            map.put("data",null);
        }catch (Exception e){
            map.put("code","1000");
            map.put("msg","验证异常");
            map.put("data",null);
        }
        return map;
    }

    /**
     * 根据token获取payload中的数据
     * @param  token String  需要获取数据的token
     * */
    public static DecodedJWT getDateByToken(String token){
        Map map = verifyToken(token);
        Object data = map.get("data");
        if (data == null){
            return null;
        }
        return (DecodedJWT) data;
    }

    public static void main(String[] args) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("username", "zhangsan");
        String token = createToken(stringStringHashMap);
        System.out.println(token);

        Map map = verifyToken(token);
        System.out.println(map);

        System.out.println(getDateByToken(token));
    }
}
