package com.mateuscarvalho.financialhistory.repository;

import com.mateuscarvalho.financialhistory.domain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
