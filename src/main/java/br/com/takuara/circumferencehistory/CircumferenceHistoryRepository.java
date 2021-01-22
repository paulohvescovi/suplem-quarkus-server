package br.com.takuara.circumferencehistory;

import br.com.takuara.enumeration.CircumferenceFields;
import br.com.takuara.user.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CircumferenceHistoryRepository implements PanacheRepository<CircumferenceHistory> {

    public List<CircumferenceHistory> findByUser(User user){
        return list("user", user);
    }

    public List<CircumferenceHistory> findByUserAndField(User user, CircumferenceFields field){
        return find("user = ?1 and field = ?2", user, field).list();
    }



}

