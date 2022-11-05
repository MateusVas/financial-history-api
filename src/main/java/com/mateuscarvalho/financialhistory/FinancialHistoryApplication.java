package com.mateuscarvalho.financialhistory;

import com.mateuscarvalho.financialhistory.domain.AccountEntity;
import com.mateuscarvalho.financialhistory.domain.PlatformEntity;
import com.mateuscarvalho.financialhistory.domain.TransactionEntity;
import com.mateuscarvalho.financialhistory.domain.TransactionHistoryEntity;
import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.repository.AccountRepository;
import com.mateuscarvalho.financialhistory.repository.PlatformRepository;
import com.mateuscarvalho.financialhistory.repository.TransactionHistoryRepository;
import com.mateuscarvalho.financialhistory.repository.TransactionRepository;
import com.mateuscarvalho.financialhistory.repository.UserRepository;
import com.mateuscarvalho.financialhistory.enums.TransactionType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class FinancialHistoryApplication implements CommandLineRunner {

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(FinancialHistoryApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        final PlatformEntity platformEntity = new PlatformEntity(1L, "PicPay", "Financial", true);

        final List<UserEntity> userEntities =
                Arrays.asList(new UserEntity(1L, "Mateus", "mateus@piclover.com"),
                        new UserEntity(2L, "João", "joao@piclover.com"),
                        new UserEntity(3L, "Maria", "maria@piclover.com"));

        final List<AccountEntity> accountEntities =
                Arrays.asList(new AccountEntity(1L, 200.00, userEntities.get(0), platformEntity),
                        new AccountEntity(2L, 950.00, userEntities.get(1), platformEntity),
                        new AccountEntity(3L, 550.00, userEntities.get(2), platformEntity));

        final TransactionEntity transactionEntity =
                new TransactionEntity(1L, TransactionType.PIX, LocalDateTime.now(),
                        accountEntities.get(0), accountEntities.get(1), 20.00);
        final TransactionHistoryEntity transactionHistoryEntity =
                new TransactionHistoryEntity(transactionEntity.getId(), "loan made",
                        LocalDateTime.now());
        platformRepository.save(platformEntity);
        userRepository.saveAll(userEntities);
        accountRepository.saveAll(accountEntities);
        transactionRepository.save(transactionEntity);
        transactionHistoryRepository.save(transactionHistoryEntity);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "threadPoolTaskExecutor")
    public Executor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}
