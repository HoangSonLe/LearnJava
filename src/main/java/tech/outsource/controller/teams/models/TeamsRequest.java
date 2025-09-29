package tech.outsource.controller.teams.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TeamsRequest(
        @NotBlank(message = "Mã đội không được để trống")
        @Size(max = 100, message = "Mã đội không được vượt quá 100 ký tự")
        @Pattern(regexp = "^[A-Z0-9_]+$", message = "Mã đội chỉ được chứa chữ hoa, số và dấu gạch dưới")
        String teamCode,

        @NotBlank(message = "Tên đội không được để trống")
        @Size(max = 256, message = "Tên đội không được vượt quá 256 ký tự")
        String teamName,

        String description,

        String path,

        @NotBlank(message = "Mã trại phụ không được để trống")
        @Size(max = 50, message = "Mã trại phụ không được vượt quá 50 ký tự")
        String subPrisonCode,

        @NotBlank(message = "Tên trại phụ không được để trống")
        @Size(max = 256, message = "Tên trại phụ không được vượt quá 256 ký tự")
        String subPrisonName,

        @NotBlank(message = "Mã trại không được để trống")
        @Size(max = 50, message = "Mã trại không được vượt quá 50 ký tự")
        String prisonCode,

        @NotBlank(message = "Tên trại không được để trống")
        @Size(max = 256, message = "Tên trại không được vượt quá 256 ký tự")
        String prisonName
) {


}
