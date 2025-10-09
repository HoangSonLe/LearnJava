package tech.outsource.dto.users;

import org.mapstruct.Mapper;
import tech.core.common.models.IMapper;
import tech.outsource.repository.database.users.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper extends IMapper<UserEntity, User> {
}
