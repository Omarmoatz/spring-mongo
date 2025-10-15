package omar.mongo.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import omar.mongo.models.User;
import omar.mongo.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MyUserService userService;

    @Test
    void shouldReturnUserWhenFoundByEmail(){
        var testEmail = "omar@mail.com";
        User mockUser = new User(testEmail);
        when(userRepository.findUserByEmail(testEmail)).thenReturn(Optional.of(mockUser));

        var result = userService.getUserByEmail(testEmail);

        assertThat(result.getEmail()).isEqualTo(testEmail);
        verify(userRepository).findUserByEmail(testEmail);

    }

    
}
