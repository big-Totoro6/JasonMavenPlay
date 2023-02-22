import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.cloud.common.KeyStoreKeyFactory;
import com.jason.cloud.domain.UserDTO;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Logger;

@SpringBootTest
public class AppTest {

    /**
     * 从 jwt.jks 文件生成 RSAKey 对象
     * @return
     */
    public RSAKey generateRsaKey() {
        // 从 classpath 下获取 RSA 秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "985211".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "985211".toCharArray());
        // 获取 RSA 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 获取 RSA 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey).privateKey(privateKey).build();

        return rsaKey;
    }
    /**
     * 对称加密
     * @throws JOSEException
     * @throws ParseException
     * @throws JsonProcessingException
     */
    @Test
    public void test_JWT_AES() throws JOSEException, ParseException, JsonProcessingException {
        //我的私钥
        SecureRandom random = new SecureRandom();
        byte[] sharedSecret = new byte[32];
        random.nextBytes(sharedSecret);
        //JWT header
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        //载荷 普通封装 String
//        Payload payload = new Payload("Hello world");
        //载荷 封装 对象
        UserDTO userDTO = new UserDTO(3L, "jason", "123456", 1, CollUtil.toList("ADMIN"));
        ObjectMapper mapper = new ObjectMapper();   // 这里使用的是 Jackson 库
        Payload payload = new Payload(mapper.writeValueAsString(userDTO));
        //设置私钥
        MACSigner signer = new MACSigner(sharedSecret);
        //这就是jwt对象
        JWSObject jwsObject=new JWSObject(header,payload);
        //进行签名
        jwsObject.sign(signer);
        //token 就是 jwt的字符串
        String token = jwsObject.serialize();
        Logger.getGlobal().info(token);

        //反序列化
        JWSObject parse = JWSObject.parse(token);
        MACVerifier macVerifier = new MACVerifier(sharedSecret);
        Logger.getGlobal().info("jwsObject.verify(macVerifier) = " + jwsObject.verify(macVerifier)) ;
//        Logger.getGlobal().info("\"Hello world\".equals(parse.getPayload().toString()) "+ "Hello world".equals(parse.getPayload().toString())) ;
        Logger.getGlobal().info(parse.getPayload().toString());

    }

    @Test
    public void test_res() throws JOSEException, ParseException {
        RSAKey rsaKey = generateRsaKey();
//        根据 RSAKey 对象生成 JWT/JWS 字符串
// JWS 头
        JWSHeader jwsHeader = new JWSHeader
                .Builder(JWSAlgorithm.RS256)    // 指定 RSA 算法
                .type(JOSEObjectType.JWT)
                .build();
// JWS 荷载
        Payload payload = new Payload("hello world");

// JWS 签名
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        JWSSigner jwsSigner = new RSASSASigner(rsaKey, true);   // rsaKey 生成签名器
        jwsObject.sign(jwsSigner);

// JWT/JWS 字符串
        String jwt = jwsObject.serialize();
        System.out.println(jwt);
    }

    @Test
    public void test_de_jwt() throws JOSEException, ParseException {
        //        根据 RSAKey 对象（的公钥）解析 JWT/JWS 字符串：
// JWT/JWS 字符串转 JWSObject 对象
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.aGVsbG8gd29ybGQ.HoozclwXXKsKkx44r8fLbViNq8TDwB_YmW9rH3ETkricsyJ68RAt25gsPt3-o5wBvwRAkxXXKJBu-VXu7kGA2WckmRe5R6ivlpprGHpWS1OLxPl_RedbI9n3z5sqlfPUJPjvsZcL5bmRUBl4SZO1WMUXJ176hTNA_RXuS3x4xzNFjKFjo5dA5FIn1_wfcppxJUnmHlB168O6ZrqPutqxga2UbKMHjXB2ymt5zPhEHsb1VIqbzCVnRDy1uv-16gqUe_H4YD8D7L8prISlF98hk3jK7-jjUXEo9Q1hlQtc6M5S2t74wop_apFzpEiyqORDzsN7DzZr68rddEjToQ889A";
        JWSObject jwsObject = JWSObject.parse(token);

        // 根据公钥生成验证器
        RSAKey rsaKey = generateRsaKey();
        RSAKey publicRsaKey = rsaKey.toPublicJWK();
        System.out.println(publicRsaKey);   // show 公钥
        JWSVerifier jwsVerifier = new RSASSAVerifier(publicRsaKey);

// 使用校验器（校验器里面塞了公钥）校验 JWSObject 对象的合法性  token其实就类似于签名了 后面会根据公钥去验证签名的合法性
        if (!jwsObject.verify(jwsVerifier)) {
            throw new RuntimeException("token签名不合法！");
        }

// 拆解 JWT/JWS，获得荷载中的内容
        String payload = jwsObject.getPayload().toString();
        System.out.println(payload);    // show 荷载
    }

    @Test
    public void test_builder(){

        UserDTO build = new UserDTO().builder().id(22L).password("111").username("name").roles(CollUtil.toList("ADMIN")).build();
        System.out.println(build.toString());
    }
}
