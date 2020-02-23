package com.microastudio.iforms.modules.system.mapper;

import com.microastudio.iforms.common.base.BaseMapper;
import com.microastudio.iforms.modules.system.domain.Dept;
import com.microastudio.iforms.modules.system.dto.DeptDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author peng
*/
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {

}
