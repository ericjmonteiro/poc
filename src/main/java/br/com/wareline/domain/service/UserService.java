package br.com.wareline.domain.service;

import br.com.wareline.infrastructure.database.entity.UserEntity;
import java.util.List;
import javax.validation.Valid;

public interface UserService {

  UserEntity findById(Long id);

  UserEntity save(@Valid UserEntity userEntity);

  UserEntity update(Long id, @Valid UserEntity userEntity);

  void delete(Long id);

  List<UserEntity> findAll();

  UserEntity findByName(String name);
}
