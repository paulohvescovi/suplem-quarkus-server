package br.com.takuara.circumferencehistory;

import br.com.takuara.framework.BaseServiceImpl;
import br.com.takuara.framework.security.Role;
import br.com.takuara.user.User;
import br.com.takuara.user.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApplicationScoped
public class CircumferenceServiceImpl extends BaseServiceImpl<CircumferenceHistory> implements CircumferenceHistoryService {

    @Inject private CircumferenceHistoryRepository circumferenceHistoryRepository;
    @Inject private UserService userService;

    @Override
    protected PanacheRepository<CircumferenceHistory> getRepository() {
        return circumferenceHistoryRepository;
    }

    @Override
    public List<CircumferenceHistory> findAll(@NotNull User user) {
        if (!userService.isAdmin(user)){
            return circumferenceHistoryRepository.findByUser(user);
        } else {
            return findAll();
        }
    }
}
