package shushi.auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.auth.dto.LoginUserDto;
import shushi.auth.dto.RegisterDto;
import shushi.auth.repository.UserRepository;

import java.util.Map;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Object> login(LoginUserDto loginUserDto) {

        return null;
    }

    @Override
    public Map<String, Object> register(RegisterDto registerDto) {
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo() {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }

    @Override
    public boolean verifyEmail(String email, String verificationCode) {
        return false;
    }

    @Override
    public boolean sendPasswordResetEmail(String email) {
        return false;
    }

    @Override
    public boolean resetPassword(String email, String resetCode, String newPassword) {
        return false;
    }
}
