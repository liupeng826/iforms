package com.microastudio.iforms.modules.system.controller;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.common.exception.BadRequestException;
import com.microastudio.iforms.common.utils.SecurityUtils;
import com.microastudio.iforms.modules.system.domain.UserPassVo;
import com.microastudio.iforms.modules.system.dto.UserDto;
import com.microastudio.iforms.modules.system.dto.UserQueryCriteria;
import com.microastudio.iforms.modules.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author peng
 */
@Api(tags = "User")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Value("${rsa.private_key}")
    private String privateKey;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    //    @Log("导出用户数据")
//    @ApiOperation("导出用户数据")
//    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check('user:list')")
//    public void download(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
//        userService.download(userService.queryAll(criteria), response);
//    }

    //        @Log("查询用户")
    @ApiOperation("查询用户")
    @GetMapping
    @PreAuthorize("hasRole('SuperAdmin')")
    public ResultResponse<Object> getUsers(UserQueryCriteria criteria, Pageable pageable) {

//        Set<Long> deptSet = new HashSet<>();
//        Set<Long> result = new HashSet<>();
//        if (!ObjectUtils.isEmpty(criteria.getDeptId())) {
//            deptSet.add(criteria.getDeptId());
//            deptSet.addAll(dataScope.getDeptChildren(deptService.findByPid(criteria.getDeptId())));
//        }
        // 数据权限
//        Set<Long> deptIds = dataScope.getDeptIds();
        // 查询条件不为空并且数据权限不为空则取交集
//        if (!CollectionUtils.isEmpty(deptIds) && !CollectionUtils.isEmpty(deptSet)) {
        // 取交集
//            result.addAll(deptSet);
//            result.retainAll(deptIds);
        // 若无交集，则代表无数据权限
//            criteria.setDeptIds(result);
//            if (result.size() == 0) {
//                return new ResultResponse<>(PageUtil.toPage(null, 0), HttpStatus.OK);
//            } else {
        Object data = userService.queryAll(criteria);
        return ResultResponse.success(data);
//            }
//            // 否则取并集
//        } else {
//            result.addAll(deptSet);
//            result.addAll(deptIds);
//            criteria.setDeptIds(result);
//            return new ResultResponse<>(userService.queryAll(criteria, pageable), HttpStatus.OK);
//        }
    }

    //
//    //    @Log("新增用户")
//    @ApiOperation("新增用户")
//    @PostMapping
//    @PreAuthorize("@el.check('user:add')")
//    public ResponseEntity<Object> create(@Validated @RequestBody User resources) {
//        checkLevel(resources);
//        // 默认密码 123456
//        resources.setPassword(passwordEncoder.encode("123456"));
//        return new ResponseEntity<>(userService.create(resources), HttpStatus.CREATED);
//    }
//
//    //    @Log("修改用户")
//    @ApiOperation("修改用户")
//    @PutMapping
//    @PreAuthorize("@el.check('user:edit')")
//    public ResponseEntity<Object> update(@Validated(User.Update.class) @RequestBody User resources) {
//        checkLevel(resources);
//        userService.update(resources);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    //    @Log("修改用户：个人中心")
//    @ApiOperation("修改用户：个人中心")
//    @PutMapping(value = "center")
//    public ResponseEntity<Object> center(@Validated(User.Update.class) @RequestBody User resources) {
//        UserDto userDto = userService.findByName(SecurityUtils.getUsername());
//        if (!resources.getId().equals(userDto.getId())) {
//            throw new BadRequestException("不能修改他人资料");
//        }
//        userService.updateCenter(resources);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @Log("删除用户")
//    @ApiOperation("删除用户")
//    @DeleteMapping
//    @PreAuthorize("@el.check('user:del')")
//    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids){
//        UserDto user = userService.findByName(SecurityUtils.getUsername());
//        for (Long id : ids) {
//            Integer currentLevel =  Collections.min(roleService.findByUsersId(user.getId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
//            Integer optLevel =  Collections.min(roleService.findByUsersId(id).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
//            if (currentLevel > optLevel) {
//                throw new BadRequestException("角色权限不足，不能删除：" + userService.findByName(SecurityUtils.getUsername()).getUsername());
//            }
//        }
//        userService.delete(ids);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePass")
    public ResultResponse<Object> updatePass(@RequestBody UserPassVo passVo) {

        // 密码解密
        RSA rsa = new RSA(privateKey, null);
        String oldPass = new String(rsa.decrypt(passVo.getOldPass(), KeyType.PrivateKey));
        String newPass = new String(rsa.decrypt(passVo.getNewPass(), KeyType.PrivateKey));

        UserDto user = userService.findByName(SecurityUtils.getUsername());
        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new BadRequestException("修改失败，旧密码错误");
        }

        if (passwordEncoder.matches(newPass, user.getPassword())) {
            throw new BadRequestException("新密码不能与旧密码相同");
        }

        userService.updatePass(user.getUserName(), passwordEncoder.encode(newPass));
        return ResultResponse.success("");
    }

//    /**
//     * 如果当前用户的角色级别低于创建用户的角色级别，则抛出权限不足的错误
//     *
//     * @param resources /
//     */
//    private void checkLevel(User resources) {
//        UserDto user = userService.findByName(SecurityUtils.getUsername());
//        Integer currentLevel = Collections.min(roleService.findByUsersId(user.getId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
//        Integer optLevel = roleService.findByRoles(resources.getRoles());
//        if (currentLevel > optLevel) {
//            throw new BadRequestException("角色权限不足");
//        }
//    }
}
