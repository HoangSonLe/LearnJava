package tech.outsource.controller.teams;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.outsource.controller.teams.models.TeamsRequest;

@RequestMapping("/v1/teams")
public interface TeamAPI {
    @PostMapping
    void create(@RequestBody @Valid TeamsRequest request);

}
