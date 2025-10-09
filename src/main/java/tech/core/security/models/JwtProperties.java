package tech.core.security.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.stereotype.Component;
import tech.core.utils.StringHelper;

import java.io.ByteArrayInputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@ConfigurationProperties(prefix = "jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class JwtProperties {
    String privateKey ;
    String publicKey ;
    long jwtRefreshExpirationS;
    long jwtAccessTokenExpirationS;
    String issuer;
    String jwkSetUri;

    public RSAPublicKey getPublicKey() {
        return (RSAPublicKey) RsaKeyConverters.x509().convert(new ByteArrayInputStream(publicKey.getBytes()));
    }
    public RSAPrivateKey getPrivateKey() {
        return (RSAPrivateKey) RsaKeyConverters.pkcs8().convert(new ByteArrayInputStream(privateKey.getBytes()));
    }
    public boolean emptyJwkSetUri() {
        return StringHelper.isNullOrEmpty(this.jwkSetUri);
    }
}
