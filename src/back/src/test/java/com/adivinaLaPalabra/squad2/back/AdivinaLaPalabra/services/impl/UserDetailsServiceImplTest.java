package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.User;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.UserDetailsImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories.UserRepository;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    UserRepository userRepository;

    @Mock
    UserDetailsImpl userDetailsImpl;

    @Test
    public void testLoadUserByUsernameMustReturnUserLoaded() {
        final User USER_LOADED = new User(DEFAULT_USERNAME, "anypassword");
        final UserDetails EXPECTED_USER = UserDetailsImpl.build(USER_LOADED);

        when(userRepository.findByName(DEFAULT_USERNAME)).thenReturn(USER_LOADED);
        UserDetails user = userDetailsServiceImpl.loadUserByUsername(DEFAULT_USERNAME);

        assertThat(user).usingRecursiveComparison().isEqualTo(EXPECTED_USER);
    }

    @Test
    public void testLoadUserByUsernameWithNonExistingUserMustThrowUsernameNotFoundException() {
        assertThatThrownBy(() -> userDetailsServiceImpl.loadUserByUsername(null))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
