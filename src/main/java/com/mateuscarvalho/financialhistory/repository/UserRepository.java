package com.mateuscarvalho.financialhistory.repository;

import com.mateuscarvalho.financialhistory.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByNameIgnoreCase(String name);
}
