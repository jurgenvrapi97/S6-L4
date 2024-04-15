package epicode.u5d8hw.security;

import epicode.u5d8hw.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import epicode.u5d8hw.entities.Author;


import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Author author){
        return Jwts.builder()
                .setSubject(String.valueOf(author.getId())) // Usa l'ID dell'autore come soggetto del token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Imposta la scadenza del token a 7 giorni da ora
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Firma il token con la tua chiave segreta
                .compact();
    }

    public void verifyToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
            // Il metodo .parse(token) mi lancerà delle eccezioni in caso di token scaduto o token manipolato
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
            // Non importa quale eccezione verrà lanciata da .parse(), a me alla fine interessa che tutte come risultato abbiano 401
        }

    }

}
