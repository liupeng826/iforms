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
        return marketMapper.toDto(market);
    }

    @Override
    @Cacheable
    public List<MarketDto> findAll() {
        return marketMapper.toDto(marketRepository.findAll());
    }

    @Override
    @Cacheable
    public List<MarketDto> findAllActive(byte isActive) {
        return marketMapper.toDto(marketRepository.findByIsActive(isActive));
    }

    @Override
    public MarketDto findByIdAndIsActive(String marketId) {
        return marketMapper.toDto(marketRepository.findByIdAndIsActive(marketId, Byte.valueOf("1")));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public MarketDto create(Market resources) {
        return marketMapper.toDto(marketRepository.save(resources));
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
}
