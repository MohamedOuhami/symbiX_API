package com.v01d.symbiX.helper;

import java.nio.file.AccessDeniedException;
import java.security.Key;
import java.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * JwtHelper
 */
public class JwtHelper {

  // Setup the jwt parameters

  // Encryption algorith,
  private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  // Token ttl
  private static final int MINUTES = 60;

  // Generate the JWTtoken
  public static String generateToken(String email) {
    var now = Instant.now();

    return Jwts.builder()
        .subject(email)
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  // Get the body inside the token
  private static Claims getTokenBody(String token) {
    try {
      return Jwts
          .parser()
          .setSigningKey(SECRET_KEY)
          .build()
          .parseSignedClaims(token)
          .getPayload();
    } catch (JwtException e) {
      throw new JwtException("Access Denied: " + e.getMessage());
    }
  }

  // Extract the username from the payloado
  public static String extractUsername(String token) {
    return getTokenBody(token).getSubject();
  }

  // Validate the token
  public static Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  // Check if the token is expired
  private static Boolean isTokenExpired(String token) {
    Claims claims = getTokenBody(token);
    return claims.getExpiration().before(new Date());
  }

}
