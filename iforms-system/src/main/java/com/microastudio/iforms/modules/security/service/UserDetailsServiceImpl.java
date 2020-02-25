package com.microastudio.iforms.modules.security.service;

import com.microastudio.iforms.common.exception.BadRequestException;
import com.microastudio.iforms.common.utils.RoleEnum;
import com.microastudio.iforms.modules.security.domain.JwtUser;
import com.microastudio.iforms.modules.system.dto.UserDto;
import com.microastudio.iforms.modules.system.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author peng
 */
@Service("userDetailsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto user = userService.findByName(username);
        if (user == null) {
            throw new BadRequestException("账号不存在");
        } else {
            if (user.getIsActive() != 1) {
                throw new BadRequestException("账号未激活");
            }
            return createJwtUser(user);
        }
    }

    private UserDetails createJwtUser(UserDto user) {
        return new JwtUser(
                user.getId(),
                user.getUserId(),
                user.getUserName(),
                user.getNickName(),
                user.getSex(),
                user.getRole(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                null,
                mapToGrantedAuthorities(user),
                user.getIsActive() == 1,
                user.getCreatedDate(),
                user.getModifiedDate(),
                user.getClient(),
                user.getDept()
        );
    }

    private Collection<GrantedAuthority> mapToGrantedAuthorities(UserDto user) {
        Set<String> permissions = new HashSet<String>();

        switch (RoleEnum.getByValue(user.getRole())) {
            case SUPER_ADMIN:
                permissions.add(RoleEnum.SUPER_ADMIN.getName());
                break;
            case ADMIN:
                permissions.add(RoleEnum.ADMIN.getName());
                break;
            default:
                permissions.add(RoleEnum.USER.getName());
                break;
        }

        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
