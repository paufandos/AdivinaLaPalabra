package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.User;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.UserDetailsImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) throw new UsernameNotFoundException("No se ha encontrado el usuario con nombre: " + username);

        return UserDetailsImpl.build(user);
    }
}
