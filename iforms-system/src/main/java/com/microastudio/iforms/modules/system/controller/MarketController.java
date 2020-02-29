package com.microastudio.iforms.modules.system.controller;

import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.modules.system.service.MarketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peng
 */
@Api(tags = "Market")
@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    //        @Log("查询用户")
    @ApiOperation("查询所有激活市场")
    @GetMapping(value = "/allActive")
    public ResultResponse<Object> getMarkets() {
        Object data = marketService.findAllActive(Byte.valueOf("1"));
        return ResultResponse.success(data);
    }

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
}
