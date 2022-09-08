package com.company.util;

import com.company.dto.JwtDTO;
import com.company.enums.ProfileRole;
import com.company.exp.BadRequestException;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final String secretKey = "someKeyWord";

    public static String encode(String phone) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(phone);
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
//        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000 * 24)));
        jwtBuilder.setIssuer("mazgi production");

        return jwtBuilder.compact();
    }

    public static JwtDTO decode(String jwt) {
        try {
            JwtParser jwtParser = Jwts.parser();

            jwtParser.setSigningKey(secretKey);
            Jws jws = jwtParser.parseClaimsJws(jwt);

            Claims claims = (Claims) jws.getBody();

            String phone = claims.getSubject();

            return new JwtDTO(phone);

        } catch (JwtException e) {
            throw new BadRequestException("JWT invalid!");
        }
    }
}
