package com.devstarrk.challenger.dto;

import com.devstarrk.challenger.entities.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O ID não pode ser nulo")
    private Long id;

    @NotBlank(message = "O título não pode estar em branco")
    private String title;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String description;

    @Pattern(
            regexp = "^(https?|ftp)://[a-zA-Z0-9+&@#/%?=~_|!:,.;-]*[a-zA-Z0-9+&@#/%=~_|]",
            message = "A URL fornecida não é válida"
    )
    @NotBlank(message = "A URL não pode estar em branco.")
    private String url;

    public VideoDTO(Video entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        url = entity.getUrl();
    }
}
