package tech.outsource.service.users;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tech.core.common.models.PageRequestCustom;
import tech.core.controller.exceptions.ApplicationException;
import tech.outsource.common.errors.UserErrorCodes;
import tech.outsource.dto.users.User;
import tech.outsource.dto.users.UserId;
import tech.outsource.dto.users.UserInformation;
import tech.outsource.dto.users.UserInformationMapper;
import tech.outsource.dto.users.UserMapper;
import tech.outsource.repository.database.users.UserEntity;
import tech.outsource.repository.database.users.UserInformationEntity;
import tech.outsource.repository.database.users.UserInformationRepository;
import tech.outsource.repository.database.users.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UsersQueryService {
    @NonNull
    UserRepository userRepository;

    @NonNull
    UserInformationRepository userInformationRepository;

    @NonNull
    UserMapper userMapper;

    @NonNull
    UserInformationMapper userInformationMapper;

    public Page<User> findAll(PageRequestCustom pageRequestCustom) {
        Page<UserEntity> users = userRepository.findAll(pageRequestCustom.pageRequest());
        return users.map(userMapper::toDto);
    }

    public User findById(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(
                        UserErrorCodes.NOT_FOUND,
                        UserErrorCodes.NOT_FOUND.getMessage(),
                        HttpStatus.BAD_REQUEST));

        return userMapper.toDto(user);
    }

    public UserInformation findByIdToUserInformation(UserId userId) {
        UserInformationEntity user = userInformationRepository.findById(userId.value())
                .orElseThrow(() -> new ApplicationException(
                        UserErrorCodes.NOT_FOUND,
                        UserErrorCodes.NOT_FOUND.getMessage(),
                        HttpStatus.BAD_REQUEST));

        return userInformationMapper.toDto(user);
    }

    public User findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(
                        UserErrorCodes.NOT_FOUND,
                        UserErrorCodes.NOT_FOUND.getMessage(),
                        HttpStatus.BAD_REQUEST));;
        return userMapper.toDto(user);
    }
}
