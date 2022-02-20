package br.com.wareline;

import br.com.wareline.domain.service.UserService;
import br.com.wareline.infrastructure.database.entity.UserEntity;
import br.com.wareline.infrastructure.database.repository.UserRepositoryImpl;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import java.util.Optional;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class UserServiceTest {

  @Inject UserService service;

  @BeforeEach
  void setUp() {

    UserRepositoryImpl mock = Mockito.mock(UserRepositoryImpl.class);
    Mockito.when(mock.findByName("Eric"))
        .thenReturn(Optional.of(UserEntity.builder().ueername("Eric").age(30).build()));
    QuarkusMock.installMockForType(mock, UserRepositoryImpl.class);
  }

  @Test
  void whenFindByUser_thenUserBeFound() {
    Assertions.assertEquals(true, service.findByName("Eric").isPresent());
  }
}
