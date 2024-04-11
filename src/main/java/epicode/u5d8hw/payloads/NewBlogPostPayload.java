package epicode.u5d8hw.payloads;

import lombok.Getter;
import javax.validation.constraints.*;

@Getter
public class NewBlogPostPayload {
    @NotNull(message = "Author ID cannot be null")
    private int authorId;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @Positive(message = "Reading time must be positive")
    private double readingTime;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;
}
