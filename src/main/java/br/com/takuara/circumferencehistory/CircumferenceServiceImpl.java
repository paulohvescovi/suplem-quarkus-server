package br.com.takuara.circumferencehistory;

import br.com.takuara.circumference.Circumference;
import br.com.takuara.circumference.CircumferenceService;
import br.com.takuara.enumeration.CircumferenceFields;
import br.com.takuara.framework.BaseServiceImpl;
import br.com.takuara.framework.security.Role;
import br.com.takuara.user.User;
import br.com.takuara.user.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CircumferenceServiceImpl extends BaseServiceImpl<CircumferenceHistory> implements CircumferenceHistoryService {

    @Inject private CircumferenceHistoryRepository circumferenceHistoryRepository;
    @Inject private CircumferenceService circumferenceService;
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

    @Override
    public List<CircumferenceHistory> findByUserAndField(User authenticatedUser, CircumferenceFields field) {
        //find history values
        List<CircumferenceHistory> historyList = circumferenceHistoryRepository.findByUserAndField(authenticatedUser, field);

        //add current value to list for represent with last history value
        Optional<CircumferenceHistory> current = Optional.ofNullable(getCurrentValueFromField(authenticatedUser, field));
        current.ifPresent(historyList::add);

        return historyList;
    }

    private CircumferenceHistory getCurrentValueFromField(User user, CircumferenceFields circumferenceField){
        Circumference currentCircunference = circumferenceService.findByUser(user);

        //todo refactor this method
        return Arrays.stream(currentCircunference.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> field.getAnnotation(Column.class).name().equals(circumferenceField.getColumnName()))
                .findFirst()
                .map(fieldValue -> {
                    fieldValue.setAccessible(true);
                    try {
                        Double value = (Double) fieldValue.get(currentCircunference);
                        if (value == null){
                            return null;
                        }
                        return CircumferenceHistory.builder()
                                .user(user)
                                .dateInsert(LocalDateTime.now())
                                .field(circumferenceField)
                                .value(value)
                                .build();
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .orElse(null);

    }
}
