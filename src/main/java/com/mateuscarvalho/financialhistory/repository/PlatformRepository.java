package com.mateuscarvalho.financialhistory.repository;

import com.mateuscarvalho.financialhistory.domain.PlatformEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity, Long> {

    Page<PlatformEntity> findByNameAndModuleAndActiveIsTrue(Pageable pageable, String name,
            String module);
}
