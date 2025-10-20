package tech.outsource.domain.users;

import com.example.core.common.interfaces.IMapper;
import org.mapstruct.Mapper;
import tech.outsource.repository.database.users.UserInformationEntity;

@Mapper(componentModel = "spring")
public interface UserInformationMapper extends IMapper<UserInformationEntity, UserInformation> {
}
