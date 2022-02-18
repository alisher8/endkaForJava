package com.endterm.project.mapper;

import com.endterm.project.dto.ChatNotificationDto;
import com.endterm.project.dto.UserDto;
import com.endterm.project.entities.MyUser;
import com.endterm.project.websocketEntities.ChatNotification;
import org.mapstruct.*;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    MyUser map(UserDto userDto);

    UserDto mapToDto(MyUser user);

    List<UserDto> mapListToDto(List<MyUser> users);

    @Mapping(source ="senderNotification.userId",target = "userId")
    @Mapping(source ="senderNotification.firstName",target = "firstName")
    @Mapping(source ="senderNotification.lastName",target = "lastName")
    ChatNotificationDto mapToChatNotificationDto(ChatNotification chatNotification);
}
