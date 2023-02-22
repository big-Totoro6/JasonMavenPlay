package com.jason.cloud.common;


import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;

public class KeyStoreKeyFactory {
    private Resource resource;
    private char[] password;
    private KeyStore store;
    private Object lock;
    private String type;

    public KeyStoreKeyFactory(Resource resource, char[] password) {
        this(resource, password, type(resource));
    }

    private static String type(Resource resource) {
        String ext = StringUtils.getFilenameExtension(resource.getFilename());
        return ext == null ? "jks" : ext;
    }

    public KeyStoreKeyFactory(Resource resource, char[] password, String type) {
        this.lock = new Object();
        this.resource = resource;
        this.password = password;
        this.type = type;
    }

    public KeyPair getKeyPair(String alias) {
        return this.getKeyPair(alias, this.password);
    }

    public KeyPair getKeyPair(String alias, char[] password) {
        try {
            synchronized(this.lock) {
                if (this.store == null) {
                    synchronized(this.lock) {
                        this.store = KeyStore.getInstance(this.type);
                        InputStream stream = this.resource.getInputStream();

                        try {
                            this.store.load(stream, this.password);
                        } finally {
                            if (stream != null) {
                                stream.close();
                            }

                        }
                    }
                }
            }

            RSAPrivateCrtKey key = (RSAPrivateCrtKey)this.store.getKey(alias, password);
            Certificate certificate = this.store.getCertificate(alias);
            PublicKey publicKey = null;
            if (certificate != null) {
                publicKey = certificate.getPublicKey();
            } else if (key != null) {
                RSAPublicKeySpec spec = new RSAPublicKeySpec(key.getModulus(), key.getPublicExponent());
                publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
            }

            return new KeyPair(publicKey, key);
        } catch (Exception var16) {
            throw new IllegalStateException("Cannot load keys from store: " + this.resource, var16);
        }
    }
}