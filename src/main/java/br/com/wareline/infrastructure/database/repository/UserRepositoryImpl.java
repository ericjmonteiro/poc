package br.com.wareline.infrastructure.database.repository;

import br.com.wareline.domain.repository.UserRepository;
import br.com.wareline.infrastructure.database.entity.UserEntity;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

  @Override
  public Optional<UserEntity> findByName(String name) {
    return find("name", name).firstResultOptional();
  }
}
