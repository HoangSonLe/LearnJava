package tech.outsource.service.users;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.core.controller.exceptions.ApplicationException;
import tech.outsource.common.errors.UserErrorCodes;
import tech.outsource.dto.users.User;
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
public class UsersCommandService {
    @NonNull
    UserRepository userRepository;

    @NonNull
    UserMapper userMapper;

    @NonNull
    UserInformationMapper userInformationMapper;

    @NonNull
    UserInformationRepository userInformationRepository;

    @NonNull
    PasswordEncoder passwordEncoder;

    public User create(User user) {
        if (userRepository.existsByUsername(user.username())) {
            throw new ApplicationException(
                    UserErrorCodes.DUPLICATE_DATA_ERROR,
                    "Username already exists: " + user.username(),
                    HttpStatus.CONFLICT
            );
        }

        if (userRepository.existsByEmail(user.email())) {
            throw new ApplicationException(
                    UserErrorCodes.DUPLICATE_DATA_ERROR,
                    "Email already exists: " + user.email(),
                    HttpStatus.CONFLICT
            );
        }


        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity savedEntity;
        try {
            savedEntity = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            // fallback trong trường hợp race condition
            throw new ApplicationException(UserErrorCodes.DUPLICATE_DATA_ERROR,
                    UserErrorCodes.DUPLICATE_DATA_ERROR.getMessage() + ":" + user.username() + ", " + user.email(),
                    HttpStatus.CONFLICT);
        }
        return userMapper.toDto(savedEntity);
    }

    public UserInformation createUserInformation(UserInformation user) {

        UserInformationEntity userInformationEntity = userInformationMapper.toEntity(user);
        UserInformationEntity saveEntity = userInformationRepository.save(userInformationEntity);
        return userInformationMapper.toDto(saveEntity);
    }
}
