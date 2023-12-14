package ma.ensaf.Qcmexamcenterbackend.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ma.ensaf.Qcmexamcenterbackend.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static ma.ensaf.Qcmexamcenterbackend.config.SecurityConstants.*;

@Service
public class JwtService {
    private static final String jwtSecretKey = TOKEN_SECRET;
    Long jwtExpirationMs = TOKEN_EXPIRATION_TIME;
    long jwtRefreshExpirationMs = REFRESH_TOKEN_EXPIRATION_TIME;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String generateToken(UserEntity userEntityDetails){
        return generateToken(new HashMap<>(), userEntityDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserEntity userEntity
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userEntity.getEmail())
                .claim("userRole", userEntity.getUserRole())
                .claim("schoolId", userEntity.getSchool().getSchoolId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(UserEntity userEntity){
        return generateRefreshToken(new HashMap<>(), userEntity);
    }
    public String generateRefreshToken(
            Map<String, Object> extraClaims,
            UserEntity userEntity
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userEntity.getEmail())
                .claim("userRole", userEntity.getUserRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
