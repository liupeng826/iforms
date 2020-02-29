package com.microastudio.iforms.modules.system.service.impl;


import com.microastudio.iforms.common.utils.ValidationUtil;
import com.microastudio.iforms.modules.system.domain.Market;
import com.microastudio.iforms.modules.system.dto.MarketDto;
import com.microastudio.iforms.modules.system.mapper.MarketMapper;
import com.microastudio.iforms.modules.system.repository.MarketRepository;
import com.microastudio.iforms.modules.system.service.MarketService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author peng
 */
@Service
@CacheConfig(cacheNames = "market")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MarketServiceImpl implements MarketService {

    private final MarketRepository marketRepository;

    private final MarketMapper marketMapper;

    public MarketServiceImpl(MarketRepository marketRepository, MarketMapper marketMapper) {
        this.marketRepository = marketRepository;
        this.marketMapper = marketMapper;
    }

    @Override
    @Cacheable(key = "#p0")
    public MarketDto findById(String id) {
        Market market = marketRepository.findById(id).orElseGet(Market::new);
        ValidationUtil.isNull(market.getId(), "Market", "id", id);
        return toDto(market);
    }

    @Override
    @Cacheable
    public List<MarketDto> findAll() {
        return toDto(marketRepository.findAll());
    }

    @Override
    @Cacheable
    public List<MarketDto> findAllActive(byte isActive) {
        return toDto(marketRepository.findByIsActive(isActive));
    }

    @Override
    public MarketDto findByIdAndIsActive(String marketId) {
        return toDto(marketRepository.findByIdAndIsActive(marketId, Byte.valueOf("1")));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public MarketDto create(Market resources) {
        return toDto(marketRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Market resources) {
        Market market = marketRepository.findById(resources.getId()).orElseGet(Market::new);
        ValidationUtil.isNull(market.getId(), "Market", "id", resources.getId());
        resources.setId(market.getId());
        marketRepository.save(resources);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<MarketDto> marketDtos) {
        for (MarketDto marketDto : marketDtos) {
            marketRepository.deleteById(marketDto.getId());
        }
    }


    public Market toEntity(MarketDto dto) {
        if (dto == null) {
            return null;
        }

        Market market = new Market();

        market.setId(dto.getId());
        market.setName(dto.getName());
        market.setIsActive(dto.getIsActive());

        return market;
    }

    public MarketDto toDto(Market entity) {
        if (entity == null) {
            return null;
        }

        MarketDto marketDto = new MarketDto();

        marketDto.setId(entity.getId());
        marketDto.setName(entity.getName());
        marketDto.setIsActive(entity.getIsActive());

        return marketDto;
    }

    public List<Market> toEntity(List<MarketDto> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Market> list = new ArrayList<Market>(dtoList.size());
        for (MarketDto marketDto : dtoList) {
            list.add(toEntity(marketDto));
        }

        return list;
    }

    public List<MarketDto> toDto(List<Market> entityList) {
        if (entityList == null) {
            return null;
        }

        List<MarketDto> list = new ArrayList<MarketDto>(entityList.size());
        for (Market market : entityList) {
            list.add(toDto(market));
        }

        return list;
    }
}
