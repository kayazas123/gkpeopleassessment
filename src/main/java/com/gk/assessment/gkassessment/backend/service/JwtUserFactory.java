package com.gk.assessment.gkassessment.backend.service;


import com.gk.assessment.gkassessment.web.domain.frontend.Authority;
import com.gk.assessment.gkassessment.web.domain.frontend.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


/**
 * Created by AYAZ on 13/04/2018.
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
	return new JwtUser(
	    user.getId(),
	    user.getUsername(),
	    user.getFirstname(),
	    user.getLastname(),
	    user.getEmail(),
	    user.getPassword(),
	    mapToGrantedAuthorities(user.getAuthorities()),
	    user.getEnabled(),
	    user.getLastPasswordResetDate()
	);
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
	return authorities.stream()
	    .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
	    .collect(Collectors.toList());
    }

}
