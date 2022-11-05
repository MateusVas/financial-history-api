package com.mateuscarvalho.financialhistory.mapper;

import com.mateuscarvalho.financialhistory.domain.AccountEntity;
import com.mateuscarvalho.financialhistory.dto.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AccountEntity dtoToAccount(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, AccountEntity.class);
    }

    public AccountDTO accountToDto(AccountEntity accountEntity) {
        return modelMapper.map(accountEntity, AccountDTO.class);
    }

    public List<AccountDTO> accountToDto(List<AccountEntity> accountEntities) {
        return accountEntities.stream().map(this::accountToDto).collect(Collectors.toList());
    }

    public Page<AccountDTO> accountToDto(Page<AccountEntity> accountEntities) {
        return new PageImpl<>(
                accountEntities.stream().map(this::accountToDto).collect(Collectors.toList()));
    }
}
