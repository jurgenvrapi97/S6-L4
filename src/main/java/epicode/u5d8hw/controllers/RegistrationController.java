package epicode.u5d8hw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import epicode.u5d8hw.entities.Author;
import epicode.u5d8hw.exceptions.BadRequestException;
import epicode.u5d8hw.services.AuthorsService;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private AuthorsService authorsService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Author registerAuthor(@RequestBody Author body){
        return authorsService.save(body);
    }
}