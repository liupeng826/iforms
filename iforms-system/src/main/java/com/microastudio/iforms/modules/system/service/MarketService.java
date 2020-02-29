package com.microastudio.iforms.modules.system.service;


import com.microastudio.iforms.modules.system.domain.Market;
import com.microastudio.iforms.modules.system.dto.MarketDto;

import java.util.List;
import java.util.Set;

/**
 * @author peng
 */
public interface MarketService {

    List<MarketDto> findAll();

    MarketDto findById(String id);

    MarketDto findByIdAndIsActive(String id);

    List<MarketDto> findAllActive(byte isActive);

    /**
     * 创建
     *
     * @param resources /
     * @return /
     */
    MarketDto create(Market resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Market resources);

    /**
     * 删除
     *
     * @param marketDtos /
     */
    void delete(Set<MarketDto> marketDtos);
}
