package com.sbz.cdss.security;

import com.sbz.cdss.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.ws.rs.NotAuthorizedException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;

public class JWTUtils {

    public static final long TOKEN_VALIDITY_IN_MINUTES = 60 * 24 * 7;

    public static final String SECRET = "123456";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER = "Bearer ";

    private static final String AUTHORITIES_KEY = "role";

    public static String generateToken(User user) {
        ZonedDateTime validity = ZonedDateTime.now(ZoneId.of("UTC")).plusMinutes(TOKEN_VALIDITY_IN_MINUTES);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim(AUTHORITIES_KEY, user.getRole())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setExpiration(Date.from(validity.toInstant()))
                .compact();
    }

    public static Authentication getAuthentication(String token, String secret) {
        final Long userId = getUserIdFromToken(token, secret);
        final String role = getRoleFromToken(token, secret);
        if (isTokenExpired(token, secret)) {
            throw new NotAuthorizedException("Token expired");
        }
        return new PreAuthenticatedAuthenticationToken(userId, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

    private static Claims getClaimsFromToken(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private static long getUserIdFromToken(String token, String secret) {
        final Claims claims = getClaimsFromToken(token, secret);
        return Long.valueOf(claims.getSubject());
    }

    private static String getRoleFromToken(String token, String secret) {
        final Claims claims = getClaimsFromToken(token, secret);
        return claims.get(AUTHORITIES_KEY).toString();
    }


    private static Date getExpirationDateFromToken(String token, String secret) {
        final Claims claims = getClaimsFromToken(token, secret);
        return claims.getExpiration();
    }

    private static boolean isTokenExpired(String token, String secret) {
        final Date expirationDate = getExpirationDateFromToken(token, secret);
        return expirationDate.before(Date.from(Instant.now()));
    }
}
