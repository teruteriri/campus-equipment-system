package edu.cit.carao.estellefelicity.campusequipmentloan.service;

import edu.cit.carao.estellefelicity.campusequipmentloan.entity.UserEntity;
import edu.cit.carao.estellefelicity.campusequipmentloan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
