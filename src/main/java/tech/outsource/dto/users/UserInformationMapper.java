package tech.outsource.dto.users;

import org.mapstruct.Mapper;
import tech.core.common.models.IMapper;
import tech.outsource.repository.database.users.UserEntity;
import tech.outsource.repository.database.users.UserInformationEntity;

@Mapper(componentModel = "spring")
public interface UserInformationMapper extends IMapper<UserInformationEntity, UserInformation> {
}
