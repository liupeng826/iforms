package com.microastudio.iforms.modules.system.controller;

import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.common.exception.BadRequestException;
import com.microastudio.iforms.common.utils.ThrowableUtil;
import com.microastudio.iforms.modules.system.domain.Dept;
import com.microastudio.iforms.modules.system.dto.DeptDto;
import com.microastudio.iforms.modules.system.dto.DeptQueryCriteria;
import com.microastudio.iforms.modules.system.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author peng
 */
@RestController
@Api(tags = "Dept")
@RequestMapping("/api/dept")
public class DeptController {

    private final DeptService deptService;

    private static final String ENTITY_NAME = "dept";

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    //    @Log("查询部门")
    @ApiOperation("查询部门是否存在")
    @GetMapping("/checkDeptById")
    public ResultResponse<Object> checkDeptById(String id) {

        Dept dept = deptService.findByIdAndIsActive(id);
        if (dept != null) {
            return new ResultResponse(CommonConstants.ERRORS_CODE_EXISTS, CommonConstants.ERRORS_MSG_EXISTS);
        }

        return ResultResponse.success("");
    }

    //    @Log("导出部门数据")
    @ApiOperation("导出部门数据")
    @GetMapping(value = "/download")
    @PreAuthorize("hasRole('SuperAdmin')")
    public void download(HttpServletResponse response, DeptQueryCriteria criteria) throws IOException {
        deptService.download(deptService.queryAll(criteria), response);
    }

    //    @Log("查询部门")
    @ApiOperation("查询部门")
    @GetMapping
    @PreAuthorize("hasRole('SuperAdmin')")
    public ResultResponse<Object> getDepts(DeptQueryCriteria criteria) {
        List<DeptDto> deptDtos = deptService.queryAll(criteria);
        return ResultResponse.success(deptDtos);
//        return ResultResponse.success(deptService.buildTree(deptDtos));
    }

    //    @Log("新增部门")
    @ApiOperation("新增部门")
    @PostMapping
//    @PreAuthorize("hasRole('SuperAdmin')")
    public ResultResponse<Object> create(@Validated @RequestBody Dept resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        return ResultResponse.success(deptService.create(resources));
    }

    //    @Log("修改部门")
    @ApiOperation("修改部门")
    @PutMapping
    @PreAuthorize("hasRole('SuperAdmin')")
    public ResultResponse<Object> update(@Validated(Dept.Update.class) @RequestBody Dept resources) {
        deptService.update(resources);
        return ResultResponse.success("");
    }

    //    @Log("删除部门")
    @ApiOperation("删除部门")
    @DeleteMapping
    @PreAuthorize("hasRole('SuperAdmin')")
    public ResultResponse<Object> delete(@RequestBody Set<String> ids) {
        Set<DeptDto> deptDtos = new HashSet<>();
        for (String id : ids) {
            deptDtos.add(deptService.findById(id));
        }

        try {
            deptService.delete(deptDtos);
        } catch (Throwable e) {
            ThrowableUtil.throwForeignKeyException(e, "所选部门中存在岗位或者角色关联，请取消关联后再试");
        }
        return ResultResponse.success("");
    }
}
