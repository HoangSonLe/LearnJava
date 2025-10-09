package tech.outsource.controller.users;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.outsource.controller.users.models.UserRequest;

@RequestMapping("/v1/user")
public interface UserAPI {
    @PostMapping
    void create(@RequestBody @Valid UserRequest request);
}
