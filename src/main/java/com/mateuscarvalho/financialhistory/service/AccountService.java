package com.mateuscarvalho.financialhistory.service;

import com.mateuscarvalho.financialhistory.domain.AccountEntity;
import com.mateuscarvalho.financialhistory.dto.AccountDTO;
import com.mateuscarvalho.financialhistory.exception.BadRequestException;
import com.mateuscarvalho.financialhistory.exception.NotFoundException;
import com.mateuscarvalho.financialhistory.mapper.AccountMapper;
import com.mateuscarvalho.financialhistory.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    public AccountDTO findById(Long id) {
        return accountMapper.accountToDto(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found!")));
    }

    public AccountDTO save(AccountDTO accountDTO) {
        final AccountEntity saved = accountRepository.save(accountMapper.dtoToAccount(accountDTO));
        return accountMapper.accountToDto(saved);
    }

    public void deposit(Long id, Double value) {
        AccountDTO accountDTO = findById(id);
        accountDTO.setBalance(value += accountDTO.getBalance());
        accountRepository.save(accountMapper.dtoToAccount(accountDTO));
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
