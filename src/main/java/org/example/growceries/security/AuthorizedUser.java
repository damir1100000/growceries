package org.example.growceries.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class AuthorizedUser extends User {
    private Long id;
    public AuthorizedUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, authorities);
        this.id = userId;
    }
    public Long getUserId() {
        return this.id;
    }
}
