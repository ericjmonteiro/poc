package br.com.wareline.domain.service;

import br.com.wareline.infrastructure.database.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

public interface UserService {

  UserEntity save(@Valid UserEntity userEntity);

  List<UserEntity> findAll();

  Optional<UserEntity> findByName(String name);
}
