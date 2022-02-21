package br.com.wareline.domain.service;

import br.com.wareline.application.exception.user.UserNotFoundException;
import br.com.wareline.domain.repository.UserRepository;
import br.com.wareline.infrastructure.database.entity.UserEntity;
import java.util.List;
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

  @Inject UserRepository repository;

  @Override
  public UserEntity findById(Long id) {
    return repository
        .findByIdOptional(id)
        .orElseThrow(() -> new UserNotFoundException(userNotFound));
  }

  @Override
  @Transactional
  public UserEntity save(UserEntity userEntity) {
    repository.persistAndFlush(userEntity);
    return userEntity;
  }

  @Override
  @Transactional
  public UserEntity update(Long id, UserEntity userEntity) {
    UserEntity modify = findById(id);
    modify.setAge(userEntity.getAge());

    log.debug("Modify user id: {}", id);
    repository.persist(modify);

    return modify;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    log.debug("Remove user by id: {}", id);
    repository.delete(findById(id));
  }

  @Override
  public List<UserEntity> findAll() {
    return repository.findAll().stream().collect(Collectors.toList());
  }

  @Override
  public UserEntity findByName(String name) {
    return repository.findByName(name).orElseThrow(() -> new UserNotFoundException(userNotFound));
  }
}
