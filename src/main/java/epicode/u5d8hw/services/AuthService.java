package epicode.u5d8hw.services;

import epicode.u5d8hw.exceptions.UnauthorizedException;
import epicode.u5d8hw.payloads.AuthorLoginDTO;
import epicode.u5d8hw.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import epicode.u5d8hw.entities.Author;


@Service
public class AuthService {
    @Autowired
    private AuthorsService authorsService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateAuthorAndGenerateToken(AuthorLoginDTO payload){
        // 1. Controllo le credenziali
        // 1.1 Cerco nel db tramite l'email l'autore
        Author author = this.authorsService.findByEmail(payload.email());
        // 1.2 Verifico se la password combacia con quella ricevuta nel payload
        if(author.getPassword().equals(payload.password())) {
            // 2. Se è tutto OK, genero un token e lo torno
            return jwtTools.createToken(author);
        } else {
            // 3. Se le credenziali invece non fossero OK --> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }
    }
}
