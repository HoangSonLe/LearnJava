package tech.outsource.domain.users;

import com.example.core.common.interfaces.IMapper;
import org.mapstruct.Mapper;
import tech.outsource.repository.database.users.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper extends IMapper<UserEntity, User> {
}
