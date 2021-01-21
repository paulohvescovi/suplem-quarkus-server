package br.com.takuara.circumferencehistory;

import br.com.takuara.user.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CircumferenceHistoryRepository implements PanacheRepository<CircumferenceHistory> {

    public List<CircumferenceHistory> findByUser(User user){
        return list("user", user);
    }

}

