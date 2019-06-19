package com.lambdaschool.wellness.service.Auth0;

import com.auth0.jwk.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.security.interfaces.RSAPublicKey;

public class JWTHelper {

    public static DecodedJWT getDecodedJWT(String token) {
      DecodedJWT decodedJWT = JWT.decode(token);
      return decodedJWT;
    }
    public static Jwk getJwk(DecodedJWT decodedJWT) throws JwkException {
        JwkProvider provider = new UrlJwkProvider("https://akshay-gadkari.auth0.com/.well-known/jwks.json");
        Jwk jwk = provider.get(decodedJWT.getKeyId());
        return jwk;
    }
    public static void verifyDecodedJWT(Jwk jwk, DecodedJWT decodedJWT) throws InvalidPublicKeyException {
        // Use the RSA256 algorithm for verification of jwt
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        Verification verifier = JWT.require(algorithm);
        verifier.build().verify(decodedJWT);
    }
}

