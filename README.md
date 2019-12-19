# alipay-spring-boot-starter


## pom (暂未上传中央仓库)
```
<dependency>
    <groupId>com.zql</groupId>
    <artifactId>alipay-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

## 配置
### application.yml （单商户）
```
alipay:
  # 是否启用
  enable: true
  # 是否沙箱环境
  is-sandbox: true
  # 应用ID
  appid: "xxxxxxxxxxx"
  # 应用公钥证书
  alipay-public-cert-path: "/alipaycrt/alipayCertPublicKey_RSA2.crt"
  # 支付宝根证书
  alipay-root-cert-path: "/alipaycrt/alipayRootCert.crt"
  # 应用公钥证书
  app-public-cert-path: "/alipaycrt/appCertPublicKey_2019120469607721.crt"
  # 私钥
  private-key: "xxxxxxxxxxxxx"

```

### application.yml （多商户）
```
alipay:
  mchname1:
    # 是否启用
    enable: true
    # 是否沙箱环境
    is-sandbox: true
    # 应用ID
    appid: "xxxxxxxxxxx"
    # 应用公钥证书
    alipay-public-cert-path: "/alipaycrt/alipayCertPublicKey_RSA2.crt"
    # 支付宝根证书
    alipay-root-cert-path: "/alipaycrt/alipayRootCert.crt"
    # 应用公钥证书
    app-public-cert-path: "/alipaycrt/appCertPublicKey_2019120469607721.crt"
    # 私钥
    private-key: "xxxxxxxxxxxxx"
  mchname2:
    # 是否启用
    enable: true
    # 是否沙箱环境
    is-sandbox: true
    # 应用ID
    appid: "xxxxxxxxxxx"
    # 应用公钥证书
    alipay-public-cert-path: "/alipaycrt/alipayCertPublicKey_RSA2.crt"
    # 支付宝根证书
    alipay-root-cert-path: "/alipaycrt/alipayRootCert.crt"
    # 应用公钥证书
    app-public-cert-path: "/alipaycrt/appCertPublicKey_2019120469607721.crt"
    # 私钥
    private-key: "xxxxxxxxxxxxx"

```

### 多商户bean要手工注册，单商户跳过此步骤
```$xslt
@Configuration
public class AlipayConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Primary
    @Bean(name = "mchname1")
    AlipayClient alipayClient1() {
        return AlipayUtil.createAlipayClient(applicationContext, "mchname1");
    }

    @Primary
    @Bean(name = "mchname2")
    AlipayClient alipayClient2() {
        return AlipayUtil.createAlipayClient(applicationContext, "mchname2");
    }
}
```


### 使用方式
AlipayClient为官方SDK提供，参考开发文档

```
    //单商户
    @Autowired
    private AlipayClient alipayClient;
    
    //多商户商户
    @Qualifier("mchname1")
    @Autowired
    private AlipayClient alipayClient1;
    
    @Qualifier("mchname2")
    @Autowired
    private AlipayClient alipayClient2;

```