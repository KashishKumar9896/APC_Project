package com.example.disasterrelief.security;

import com.example.disasterrelief.model.Admin;
import com.example.disasterrelief.model.Volunteer;
import com.example.disasterrelief.repository.AdminRepository;
import com.example.disasterrelief.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Volunteer volunteer = volunteerRepository.findByEmail(email).orElse(null);
        if (volunteer != null) {
            return UserPrincipal.build(volunteer, "VOLUNTEER");
        }
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            return UserPrincipal.build(admin, "ADMIN");
        }
        throw new UsernameNotFoundException("User Not Found with email: " + email);
    }
}
