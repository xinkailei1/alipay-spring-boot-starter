package com.zql.alipay.springboot.util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/***
 *
 *
 * @author 张乾龙
 * @date: 2019.12.19
 */
public class AlipayUtil {


    public static AlipayClient createAlipayClient(ApplicationContext applicationContext, String configName) {

        Environment environment = applicationContext.getEnvironment();

        if (environment instanceof ConfigurableEnvironment) {
            String prefix = "alipay";
            MutablePropertySources mutablePropertySources = ((ConfigurableEnvironment) environment).getPropertySources();
            Map<String, String> propertiesMap = getPropertiesMap(mutablePropertySources, prefix);
            String enable = propertiesMap.get(prefix + "." + configName + ".enable");
            String isSandbox = propertiesMap.get(prefix + "." + configName + ".is-sandbox");
            String appid = propertiesMap.get(prefix + "." + configName + ".appid");
            String alipayPublicCertPath = propertiesMap.get(prefix + "." + configName + ".alipay-public-cert-path");
            String alipayRootCertPath = propertiesMap.get(prefix + "." + configName + ".alipay-root-cert-path");
            String appPublicCertPath = propertiesMap.get(prefix + "." + configName + ".app-public-cert-path");
            String privateKey = propertiesMap.get(prefix + "." + configName + ".private-key");

            if (enable == null || enable.equals("false")) {
                throw new RuntimeException("alipay properties error , enable is null or false");
            }

            if (appid == null || appid.equals("")) {
                throw new RuntimeException("alipay properties error , appid is blank");
            }

            if (alipayPublicCertPath == null || alipayPublicCertPath.equals("")) {
                throw new RuntimeException("alipay properties error , alipay-public-cert-path is blank");
            }

            if (alipayRootCertPath == null || alipayRootCertPath.equals("")) {
                throw new RuntimeException("alipay properties error , alipay-root-cert-path is blank");
            }

            if (appPublicCertPath == null || appPublicCertPath.equals("")) {
                throw new RuntimeException("alipay properties error , app-public-cert-path is blank");
            }

            if (privateKey == null || privateKey.equals("")) {
                throw new RuntimeException("alipay properties error , private-key is blank");
            }


            String serverUrl = isSandbox == null || isSandbox.equals("true") ? "https://openapi.alipay.com/gateway.do" : "https://openapi.alipaydev.com/gateway.do";
            try {
                CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
                certAlipayRequest.setServerUrl(serverUrl);
                certAlipayRequest.setAppId(appid);
                certAlipayRequest.setPrivateKey(privateKey);
                certAlipayRequest.setFormat("json");
                certAlipayRequest.setCharset("UTF-8");
                certAlipayRequest.setSignType("RSA2");
                certAlipayRequest.setCertPath(appPublicCertPath);
                certAlipayRequest.setAlipayPublicCertPath(alipayPublicCertPath);
                certAlipayRequest.setRootCertPath(alipayRootCertPath);

                return new DefaultAlipayClient(certAlipayRequest);
            } catch (AlipayApiException e) {
                throw new RuntimeException(e);
            }


        }

        throw new RuntimeException("environment isnot instanceof ConfigurableEnvironment");
    }


    private static Map<String, String> getPropertiesMap(PropertySources propertySources, String prefix) {

        Map<String, String> result = new HashMap<String, String>();

        Iterator<PropertySource<?>> iterator = propertySources.iterator();
        while (iterator.hasNext()) {
            PropertySource propertySource = iterator.next();
            if (propertySource.getName().startsWith("applicationConfig")) {
                Map sourceMap = (Map) propertySource.getSource();
                if (prefix == null || prefix.equals("")) {
                    result.putAll(sourceMap);
                } else {
                    Set<Map.Entry> entrySet = sourceMap.entrySet();
                    Iterator<Map.Entry> entryIterator = entrySet.iterator();
                    while (entryIterator.hasNext()) {
                        Map.Entry item = entryIterator.next();
                        String key = item.getKey().toString();
                        String value = item.getValue().toString();
                        if (key.startsWith(prefix)) {
                            result.put(key, value);
                        }
                    }
                }
            }
        }

        return result;
    }
}
