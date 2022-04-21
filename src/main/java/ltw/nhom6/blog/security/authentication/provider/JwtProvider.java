package ltw.nhom6.blog.security.authentication.provider;

import io.jsonwebtoken.*;
import ltw.nhom6.blog.exception.common.BusinessException;
import ltw.nhom6.blog.security.authentication.payload.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtProvider {

    private final UserPrincipalService userPrincipalService;

    @Autowired
    public JwtProvider(UserPrincipalService userDetailsService) {
        this.userPrincipalService = userDetailsService;
    }

    private final static String JWT_HEADER = "Authorization";

    private final static String JWT_SECRET_KEY = "aibles";

    private final static long JWT_LIFE_TIME_MILLISECONDS = 604800000;

    private final static String JWT_PREFIX= "Bearer";

    public String generateToken(String email){
        Claims claims = Jwts.claims().setSubject(email);
        Date now  = new Date();
        Date expirationDate = new Date (now.getTime() + JWT_LIFE_TIME_MILLISECONDS);
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (JwtException e){
            throw new BusinessException("JWT token is expired", HttpStatus.UNAUTHORIZED);
        }catch (IllegalArgumentException e){
            throw new BusinessException("JWT token is invalid", HttpStatus.UNAUTHORIZED);
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userPrincipalService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader(JWT_HEADER);
        if (token != null && token.startsWith(JWT_PREFIX)){
            return token.substring(7);
        }
        return null;
    }

}
