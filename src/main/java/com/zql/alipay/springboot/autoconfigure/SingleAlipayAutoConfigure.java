package com.zql.alipay.springboot.autoconfigure;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.zql.alipay.springboot.properties.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *
 *
 * @author 张乾龙
 * @date: 2019.12.18
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class SingleAlipayAutoConfigure {

    @Autowired
    AlipayProperties alipayApiProperties;

    @Bean
    @ConditionalOnMissingBean(AlipayClient.class)
    public AlipayClient alipayClient() {
        String serverUrl = alipayApiProperties.isSandbox() ? "https://openapi.alipay.com/gateway.do" : "https://openapi.alipaydev.com/gateway.do";

        try {
            CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
            certAlipayRequest.setServerUrl(serverUrl);
            certAlipayRequest.setAppId(alipayApiProperties.getAppid());
            certAlipayRequest.setPrivateKey(alipayApiProperties.getPrivateKey());
            certAlipayRequest.setFormat("json");
            certAlipayRequest.setCharset("UTF-8");
            certAlipayRequest.setSignType("RSA2");
            certAlipayRequest.setCertPath(alipayApiProperties.getAppPublicCertPath());
            certAlipayRequest.setAlipayPublicCertPath(alipayApiProperties.getAlipayPublicCertPath());
            certAlipayRequest.setRootCertPath(alipayApiProperties.getAlipayRootCertPath());
            return new DefaultAlipayClient(certAlipayRequest);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }
}
