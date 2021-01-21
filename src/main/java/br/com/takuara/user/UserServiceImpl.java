package br.com.takuara.user;

import br.com.takuara.framework.BaseServiceImpl;
import br.com.takuara.framework.security.PBKDF2Encoder;
import br.com.takuara.framework.security.Role;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Comparator;

@ApplicationScoped
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Inject UserRepository userRepository;
    @Inject PBKDF2Encoder passwordEncoder;

    @Override
    protected PanacheRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public Role getMainRole(User user) {
        return user.getRoles().stream()
                .sorted(Comparator.comparingInt(Role::getPriotiry))
                .findFirst()
                .get();
    }

    @Override
    public boolean isAdmin(User user) {
        return Role.ADMIN.equals(
                user.getRoles().stream()
                        .sorted(Comparator.comparingInt(Role::getPriotiry))
                        .findFirst()
                        .get()
        );
    }

    @Override
    public User findByEmail(String email) {
        return getRepository().find("email",  email).firstResult();
    }

    @Override
    public User preProcessorSave(User entity) {
        if (entity.id == null){
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        return super.preProcessorSave(entity);
    }
}
