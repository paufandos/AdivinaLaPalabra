package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security.jwt;

import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.UserDetailsImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities.Base64Utils;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities.DateUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

    private final int AUTH_BEARER_FIELD_SIZE = 7;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationSeconds;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        String userName = Base64Utils.encode(userPrincipal.getName());

        ZonedDateTime now = DateUtils.generateZonedDateTime();
        ZonedDateTime tokenExpiredDate = now.plusSeconds(jwtExpirationSeconds);

        return JWT.create()
                .withClaim("user", userName)
                .withIssuedAt(now.toInstant())
                .withExpiresAt(tokenExpiredDate.toInstant())
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("user").asString();
    }

    public String getUsernameFromAuthHeader(String authHeader){
        String token = getTokenFromHeader(authHeader);
        String username = validateTokenAndRetrieveSubject(token);
        return Base64Utils.decode(username);
    }
    private String getTokenFromHeader(String authHeader){
        return authHeader.substring(AUTH_BEARER_FIELD_SIZE, authHeader.length());
    }
}
