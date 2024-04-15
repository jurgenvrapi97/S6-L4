package epicode.u5d8hw.controllers;

import epicode.u5d8hw.payloads.AuthorLoginDTO;
import epicode.u5d8hw.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody AuthorLoginDTO payload){
        return authService.authenticateAuthorAndGenerateToken(payload);
    }
}