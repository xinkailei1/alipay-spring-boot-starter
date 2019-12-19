package com.zql.alipay.springboot.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/***
 *
 *
 * @author 张乾龙
 * @date: 2019.12.18
 */
public class AlipaySignatureUtil {

    /**
     * 回调通知验签
     *
     * @param request              HttpServletRequest
     * @param alipayPublicCertPath 支付宝公钥证书路径
     * @return
     */
    public static boolean checkSignature(HttpServletRequest request, String alipayPublicCertPath) {

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();

        Set<Map.Entry<String, String[]>> entrySet = requestParams.entrySet();
        Iterator<Map.Entry<String, String[]>> entryKeyIterator = entrySet.iterator();

        while (entryKeyIterator.hasNext()) {
            Map.Entry<String, String[]> entry = entryKeyIterator.next();
            String name = entry.getKey();
            String[] values = entry.getValue();

            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr += values[i];
                if (i != values.length - 1) {
                    valueStr += ",";
                }
            }
            params.put(name, valueStr);
        }

        boolean verifyResult;
        try {
            verifyResult = AlipaySignature.rsaCertCheckV1(params, alipayPublicCertPath, params.get("charset"), params.get("sign_type"));
        } catch (AlipayApiException e) {
            verifyResult = false;

        }

        return verifyResult;
    }
}
