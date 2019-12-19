package com.zql.alipay.springboot.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/***
 *
 *
 * @author 张乾龙
 * @date: 2019.12.18
 */
@Data
@ConfigurationProperties("alipay")
public class AlipayProperties {

    /**
     * 是否启用
     */
    private boolean enable;

    /**
     * 是否沙箱环境
     */
    private boolean isSandbox;

    /**
     * 应用ID
     */
    private String appid;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 应用公钥证书文件路径
     */
    private String appPublicCertPath;

    /**
     * 支付宝公钥证书文件路径
     */
    private String alipayPublicCertPath;

    /**
     * 支付宝根证书文件路径
     */
    private String alipayRootCertPath;

}
