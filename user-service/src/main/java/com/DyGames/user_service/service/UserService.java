package com.DyGames.user_service.service;

import com.DyGames.user_service.dto.UserRespuesta;
import com.DyGames.user_service.mapper.UserMapper;
import com.DyGames.user_service.model.User;
import com.DyGames.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El username ya existe");
        }
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User u = userRepository.findById(id).orElse(null);
        if (u == null) return null;
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setNombre(user.getNombre());
        u.setPfpUrl(user.getPfpUrl());
        return userRepository.save(u);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // aqui es copy paste de los metodos dto de category pero ahora con user gracias a mapper
    public UserRespuesta findDTO(Long id) {
        return userMapper.toDTO(findById(id));
    }

    public List<UserRespuesta> findDTOList() {
        return userMapper.toDTOList(findAll());
    }

    public UserRespuesta findByUsernameDTO(String username) {
        return userMapper.toDTO(userRepository.findByUsername(username).orElse(null));
    }

    public UserRespuesta findByEmailDTO(String email) {
        return userMapper.toDTO(userRepository.findByEmail(email).orElse(null));
    }
}
