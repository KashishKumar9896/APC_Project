package com.example.disasterrelief.controller;

import com.example.disasterrelief.model.Admin;
import com.example.disasterrelief.model.Volunteer;
import com.example.disasterrelief.repository.AdminRepository;
import com.example.disasterrelief.repository.VolunteerRepository;
import com.example.disasterrelief.security.UserPrincipal;
import com.example.disasterrelief.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateToken((UserPrincipal) authentication.getPrincipal());

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("type", "Bearer");
            response.put("id", userPrincipal.getId());
            response.put("username", userPrincipal.getUsername());
            response.put("email", userPrincipal.getEmail());
            response.put("firstName", userPrincipal.getFirstName());
            response.put("lastName", userPrincipal.getLastName());
                response.put("role", userPrincipal.getAuthorities().iterator().next().getAuthority().replace("ROLE_", ""));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (volunteerRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        Volunteer volunteer = new Volunteer(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getPhoneNumber(), signUpRequest.getAddress(), signUpRequest.getCity(),
                signUpRequest.getState(), signUpRequest.getZipCode());

        volunteerRepository.save(volunteer);

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()) != null) {
            return ResponseEntity
                .badRequest()
                .body("Error: Email is already in use!");
        }
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return ResponseEntity.ok("Admin registered successfully!");
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class SignupRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phoneNumber;
        private String address;
        private String city;
        private String state;
        private String zipCode;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
