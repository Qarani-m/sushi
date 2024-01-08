package shushi.auth.service;

import shushi.auth.dto.LoginUserDto;
import shushi.auth.dto.RegisterDto;

import java.util.Map;

public interface UserService {
    Map<String, Object> login(LoginUserDto loginUserDto);

    Map<String, Object> register(RegisterDto registerDto);

    Map<String, Object> getUserInfo();

    boolean logout();

    boolean verifyEmail(String email, String verificationCode);

    boolean sendPasswordResetEmail(String email);

    boolean resetPassword(String email, String resetCode, String newPassword);
}
