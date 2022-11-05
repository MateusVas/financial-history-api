package com.mateuscarvalho.financialhistory.mapper;

import com.mateuscarvalho.financialhistory.domain.AccountEntity;
import com.mateuscarvalho.financialhistory.domain.TransactionHistoryEntity;
import com.mateuscarvalho.financialhistory.dto.AccountDTO;
import com.mateuscarvalho.financialhistory.dto.TransactionDTO;
import com.mateuscarvalho.financialhistory.dto.TransactionHistoryDTO;
import enums.TransactionType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionHistoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    private final String TRANSACTION_DESCRIPTION =
            "Operation %s in the amount of BRL %.2f carried out for beneficiary %o";

    public TransactionHistoryEntity dtoToTransactionHistory(TransactionHistoryDTO accountDTO) {
        return modelMapper.map(accountDTO, TransactionHistoryEntity.class);
    }

    public TransactionHistoryDTO transactionHistoryToDto(
            TransactionHistoryEntity transactionHistoryEntity) {
        return modelMapper.map(transactionHistoryEntity, TransactionHistoryDTO.class);
    }

    public List<TransactionHistoryDTO> transactionHistoryToDto(
            List<TransactionHistoryEntity> transactionHistoryEntities) {
        return transactionHistoryEntities.stream().map(this::transactionHistoryToDto)
                .collect(Collectors.toList());
    }

    public Page<TransactionHistoryDTO> transactionHistoryToDto(
            Page<TransactionHistoryEntity> transactionHistoryEntities) {
        return new PageImpl<>(transactionHistoryEntities.stream().map(this::transactionHistoryToDto)
                .collect(Collectors.toList()));
    }

    public TransactionHistoryEntity convertToEntity(TransactionDTO transactionDTO) {
        final TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
        transactionHistoryEntity.setTransaction_id(transactionDTO.getId());
        transactionHistoryEntity.setLocalDateTime(transactionDTO.getLocalDateTime());

        switch (transactionDTO.getTransactionType()) {
            case PIX:
                transactionHistoryEntity.setDescription(
                        String.format(TRANSACTION_DESCRIPTION, TransactionType.PIX,
                                transactionDTO.getValue(), transactionDTO.getFavored().getId()));
            case TED:
                transactionHistoryEntity.setDescription(
                        String.format(TRANSACTION_DESCRIPTION, TransactionType.TED,
                                transactionDTO.getValue(), transactionDTO.getFavored().getId()));
            default:
                transactionHistoryEntity.setDescription(
                        String.format(TRANSACTION_DESCRIPTION, TransactionType.DOC,
                                transactionDTO.getValue(), transactionDTO.getFavored().getId()));
        }
        return transactionHistoryEntity;
    }
}
