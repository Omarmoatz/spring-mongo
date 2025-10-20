package omar.mongo.services;

import static org.mockito.ArgumentMatchers.anyString;
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
    void shouldReturnUserWhenFoundByEmail() {
        // Arrange
        var testEmail = "omar@mail.com";
        User mockUser = new User(testEmail);
        when(userRepository.findUserByEmail(testEmail)).thenReturn(Optional.of(mockUser));

        // Act
        var result = userService.getUserByEmail(testEmail);

        // Assert
        assertThat(result.getEmail()).isEqualTo(testEmail);
        verify(userRepository).findUserByEmail(testEmail);

    }

    @Test
    void shouldThrowExcWhenUserNotFound(){
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserByEmail("test@mail.com"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("User Not Found");
    }

}

// - @SpringBootTest loads the full Spring context—not suitable for unit tests.
// - Use JUnit 5 + Mockito for lightweight, fast tests.

// Follow the AAA Pattern (Arrange, Act, Assert)

// Unit tests are not the place to test:
// - Dependency injection
// - Properties loading
// - Actual DB connections
// Leave that for integration tests.