package br.com.wareline.domain.service;

import br.com.wareline.application.exception.CustomException;
import br.com.wareline.domain.repository.UserRepository;
import br.com.wareline.infrastructure.database.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Slf4j
public class UserServiceImpl implements UserService {

  @ConfigProperty(name = "message.user.error.notfound")
  String userNotFound;

  @Inject
  UserRepository repository;

  @Override
  @Transactional
  public UserEntity save(UserEntity userEntity) {
    repository.persistAndFlush(userEntity);
    return userEntity;
  }

  @Override
  public List<UserEntity> findAll() {
    return repository.findAll().stream().collect(Collectors.toList());
  }

  @Override
  public Optional<UserEntity> findByName(String name) {
    final Optional<UserEntity> user = repository.findByName(name);
    if (user.isEmpty()) {
      throw new CustomException(userNotFound);
    }
    return user;
  }
}
