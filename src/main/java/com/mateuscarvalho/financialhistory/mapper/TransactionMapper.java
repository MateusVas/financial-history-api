package com.mateuscarvalho.financialhistory.mapper;

import com.mateuscarvalho.financialhistory.domain.TransactionEntity;
import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionEntity dtoToTransaction(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, TransactionEntity.class);
    }

    public TransactionDTO transactionToDto(TransactionEntity transactionEntity) {
        return modelMapper.map(transactionEntity, TransactionDTO.class);
    }

    public Page<TransactionDTO> transactionToDto(Page<TransactionEntity> transactionDTOS) {
        return new PageImpl<>(
                transactionDTOS.stream().map(this::transactionToDto).collect(Collectors.toList()));
    }

    public List<TransactionDTO> transactionToDto(List<TransactionEntity> transactionDTOS) {
        return transactionDTOS.stream().map(this::transactionToDto).collect(Collectors.toList());
    }
}
