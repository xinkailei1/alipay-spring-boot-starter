# alipay-spring-boot-starter


## pom
```
<dependency>
    <groupId>com.zql</groupId>
    <artifactId>alipay-spring-boot-starter</artifactId>
    <version>1.0</version>
</dependency>
```

## application.yml
```
alipay:
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

## 使用方式
AlipayClient为官方SDK提供，参考开发文档

```
    @Autowired
    private AlipayClient alipayClient;

```