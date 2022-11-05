package com.mateuscarvalho.financialhistory;

import com.mateuscarvalho.financialhistory.domain.AccountEntity;
import com.mateuscarvalho.financialhistory.domain.PlatformEntity;
import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.repository.AccountRepository;
import com.mateuscarvalho.financialhistory.repository.PlatformRepository;
import com.mateuscarvalho.financialhistory.repository.TransactionRepository;
import com.mateuscarvalho.financialhistory.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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

    public static void main(String[] args) {
        SpringApplication.run(FinancialHistoryApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        final PlatformEntity platformEntity =
                new PlatformEntity(1L, "Platform_test", "Financial", true);
        final UserEntity userEntity = new UserEntity(1L, "Mateus", "mateus@test.com");
        final AccountEntity accountEntity =
                new AccountEntity(1L, 200.00, userEntity, platformEntity);
        platformRepository.save(platformEntity);
        userRepository.save(userEntity);
        accountRepository.save(accountEntity);
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
