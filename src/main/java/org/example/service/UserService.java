package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.data.entity.security.RoleEnum;
import org.example.data.entity.security.User;
import org.example.data.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import java.util.Objects;
import java.util.Optional;
//import org.springframework.security.crypto.bcrypt.BCrypt;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Проверка логина и пароля
    public boolean authenticate(String username, String password) {
        User user = userRepository.findByEmail(username);

        if (user != null) {
            System.out.println(passwordEncoder.encode(password));
            return passwordEncoder.matches(password, user.getPassword());
        }

        return false;
    }

    public boolean checkAdminRole(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new RuntimeException("Пользователь с переданными данными не найден");
        }
        return  (user.get().getRole().getName() == RoleEnum.ADMIN);
    }

    // Получить пользователя по имени
    public User findUserByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return user;
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

}
