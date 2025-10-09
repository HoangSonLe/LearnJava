package tech.outsource.service.users;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import tech.core.common.models.PageRequestCustom;
import tech.outsource.dto.users.User;
import tech.outsource.dto.users.UserInformation;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UsersUseCaseService {

    @NonNull
    UsersCommandService usersCommandService;

    @NonNull
    UsersQueryService usersQueryService;

    @Transactional
    public void create(User user) {

        User userEntity = usersCommandService.create(user);
        UserInformation userInformation = UserInformation.builder()
                                                        .userId(userEntity.userId())
                                                        .email(user.email())
                                                        .build();
        usersCommandService.createUserInformation(userInformation);
    }

    public Page<User> findAll(PageRequestCustom pageRequestCustom) {
        return usersQueryService.findAll(pageRequestCustom);
    }

    public User findById(Integer userId) {
        return usersQueryService.findById(userId);
    }
}
