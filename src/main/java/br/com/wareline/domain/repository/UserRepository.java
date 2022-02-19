package br.com.wareline.domain.repository;

import br.com.wareline.infrastructure.database.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.Optional;

public interface UserRepository extends PanacheRepository<UserEntity> {

  Optional<UserEntity> findByName(String name);
}
