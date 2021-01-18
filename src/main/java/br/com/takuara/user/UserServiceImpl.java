package br.com.takuara.user;

import br.com.takuara.framework.BaseServiceImpl;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Inject private UserRepository userRepository;

    @Override
    protected PanacheRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public User findByEmail(String email) {
        return getRepository().find("email",  email).firstResult();
    }
}
