package com.mateuscarvalho.financialhistory.service;

import com.mateuscarvalho.financialhistory.domain.UserEntity;
import com.mateuscarvalho.financialhistory.dto.UserDTO;
import com.mateuscarvalho.financialhistory.exception.BadRequestException;
import com.mateuscarvalho.financialhistory.exception.NotFoundException;
import com.mateuscarvalho.financialhistory.mapper.UserMapper;
import com.mateuscarvalho.financialhistory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserDTO findById(Long id) {
        return userMapper.userToDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!")));
    }

    public Page<UserDTO> findAll(Pageable pageable) {
        return userMapper.userToDto(userRepository.findAll(pageable));
    }

    public boolean existsByEmailIgnoreCase(String email) {
        return userRepository.existsByNameIgnoreCase(email);
    }

    public UserDTO save(UserDTO userDTO) {
        final UserEntity saved = userRepository.save(userMapper.dtoToUser(userDTO));
        return userMapper.userToDto(saved);
    }

    public void update(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new BadRequestException("User without id");
        }
        userRepository.save(userMapper.dtoToUser(userDTO));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
