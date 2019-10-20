package com.softwaretalks.jangul.security;

public final class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/tokens";

    // Signing key for HS512 algorithm
    public static final String JWT_SECRET = "d4r5u8x/A%D*G-KaPdSgCkYp5d6v9y$C&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private SecurityConstants() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}
