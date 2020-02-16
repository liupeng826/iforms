package com.microastudio.iforms.modules.system.mapper;

import com.microastudio.iforms.common.base.BaseMapper;
import com.microastudio.iforms.modules.system.domain.User;
import com.microastudio.iforms.modules.system.dto.UserDto;
import org.mapstruct.Mapping;

/**
 * @author peng
 */
public interface UserMapper extends BaseMapper<UserDto, User> {

    /**
     * 转换
     * @param user 原始数据
     * @return /
     */
    @Override
    @Mapping(source = "user.userAvatar.realName",target = "avatar")
    UserDto toDto(User user);
}
