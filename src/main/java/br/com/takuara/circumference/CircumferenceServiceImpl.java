package br.com.takuara.circumference;

import br.com.takuara.circumferencehistory.CircumferenceHistory;
import br.com.takuara.framework.BaseServiceImpl;
import br.com.takuara.user.User;
import br.com.takuara.user.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApplicationScoped
public class CircumferenceServiceImpl extends BaseServiceImpl<Circumference> implements CircumferenceService {

    @Inject private CircumferenceRepository circumferenceRepository;
    @Inject private UserService userService;

    @Override
    protected PanacheRepository<Circumference> getRepository() {
        return circumferenceRepository;
    }

    @Override
    public List<Circumference> findAll(@NotNull User user) {
        if (!userService.isAdmin(user)){
            return circumferenceRepository.findByUser(user);
        } else {
            return findAll();
        }
    }
}
