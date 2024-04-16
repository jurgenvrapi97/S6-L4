package epicode.u5d8hw.controllers;

import epicode.u5d8hw.entities.Author;
import epicode.u5d8hw.payloads.AuthorLoginDTO;
import epicode.u5d8hw.services.AuthService;
import epicode.u5d8hw.services.AuthorsService;
import epicode.u5d8hw.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;
    @Autowired
    private CloudinaryService cloudinaryService;

    // 1. - POST http://localhost:3001/authors (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Author saveAuthor(@RequestBody Author body) throws Exception {
        return authorsService.save(body);
    }

    // 2. - GET http://localhost:3001/authors
    @GetMapping("")
    public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return authorsService.getAuthors(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/authors/{id}
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) {
        return authorsService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/authors/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public Author findAndUpdate(@PathVariable int authorId, @RequestBody Author body) {
        return authorsService.findByIdAndUpdate(authorId, body);
    }

    // 5. - DELETE http://localhost:3001/authors/{id}
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }

    @PutMapping("/{authorId}/uploadAvatar")
    public Author uploadAvatar(@PathVariable int authorId, @RequestParam("file") MultipartFile file) {

        String url = cloudinaryService.uploadFile(file);

        Author author = authorsService.findById(authorId);

        author.setAvatar(url);
        return authorsService.findByIdAndUpdate(authorId, author);
    }


}
