package com.microastudio.iforms.modules.system.repository;


import com.microastudio.iforms.modules.system.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author peng
 */
@SuppressWarnings("all")
public interface MarketRepository extends JpaRepository<Market, String>, JpaSpecificationExecutor<Market> {

    Market findByIdAndIsActive(String marketId, byte isActive);

    List<Market> findByIsActive(byte isActive);

    /**
     * 根据ID查询名称
     *
     * @param id ID
     * @return /
     */
    @Query(value = "select name from dept where id = ?1", nativeQuery = true)
    String findNameById(String id);
}
