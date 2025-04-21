package com.fiap.fast_food_tc.cross;

import com.fiap.fast_food_tc.adapter.db.model.User;
import com.fiap.fast_food_tc.adapter.dto.UserRequestDto;
import com.fiap.fast_food_tc.adapter.dto.UserResponseDto;
import com.fiap.fast_food_tc.domain.entity.EUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    EUser toEntity(User eUser);

    User toDomain(EUser user);

    @Mapping(target = "id", ignore = true)
    EUser messageToEntity(UserRequestDto user);

    UserResponseDto entityToMessage(EUser user);

}
