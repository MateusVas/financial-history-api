package com.mateuscarvalho.financialhistory.service;

import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.dto.UserDTO;
import com.mateuscarvalho.financialhistory.exception.NotFoundException;
import com.mateuscarvalho.financialhistory.mapper.UserMapper;
import com.mateuscarvalho.financialhistory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class UserService {

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Value("${async.milliseconds:5000}")
    private Long asyncMilliseconds;

    public UserDTO findById(Long id) {
        return userMapper.userToDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!")));
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        return userMapper.userToDto(userRepository.findAll(pageable));
    }

    public boolean existsByEmailIgnoreCase(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public UserDTO save(UserDTO userDTO) {
        final UserEntity saved = userRepository.save(userMapper.dtoToUser(userDTO));
        return userMapper.userToDto(saved);
    }

    @Async("threadPoolTaskExecutor")
    public void saveAsync(UserDTO userDTO) {
        try {
            logger.log(Level.INFO, "User save process started asynchronously!");
            Thread.sleep(asyncMilliseconds);
            userRepository.save(userMapper.dtoToUser(userDTO));
        } catch (InterruptedException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
