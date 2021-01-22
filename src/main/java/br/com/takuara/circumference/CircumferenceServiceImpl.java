package br.com.takuara.circumference;

import br.com.takuara.circumferencehistory.CircumferenceHistory;
import br.com.takuara.circumferencehistory.CircumferenceHistoryService;
import br.com.takuara.enumeration.CircumferenceFields;
import br.com.takuara.framework.BaseServiceImpl;
import br.com.takuara.user.User;
import br.com.takuara.user.UserService;
import br.com.takuara.utils.ReflectionUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

@ApplicationScoped
public class CircumferenceServiceImpl extends BaseServiceImpl<Circumference> implements CircumferenceService {

    @Inject private CircumferenceRepository circumferenceRepository;
    @Inject private CircumferenceHistoryService circumferenceHistoryService;
    @Inject private UserService userService;

    @Override
    protected PanacheRepository<Circumference> getRepository() {
        return circumferenceRepository;
    }

    @Override
    public List<Circumference> findAll(@NotNull User user) {
        if (!userService.isAdmin(user)){
            return Collections.singletonList(circumferenceRepository.findByUser(user));
        } else {
            return findAll();
        }
    }

    @Override
    public Circumference findByUser(@NotNull User user) {
        return circumferenceRepository.findByUser(user);
    }

    @Override
    public Circumference preProcessorSave(Circumference entity) {
        if (entity.id != null){
            List<CircumferenceHistory> historyList = getHistoryForSave(findById(entity.id));
            if (isNotEmpty(historyList)){
                historyList.forEach(history -> circumferenceHistoryService.save(history));
            }
        }
        return super.preProcessorSave(entity);
    }

    private List<CircumferenceHistory> getHistoryForSave(Circumference entity){
        List<CircumferenceHistory> forSave = new ArrayList<>();

        Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> !field.getAnnotation(Column.class).name().equals(CircumferenceFields.USER_ID.getLabel()))
                .filter(field -> ReflectionUtils.getDouble(field, entity) != null)
                .forEach(field -> {
                    forSave.add(CircumferenceHistory.builder()
                            .dateInsert(LocalDateTime.now())
                            .user(entity.getUser())
                            .field(CircumferenceFields.fromColumn(field.getAnnotation(Column.class)))
                            .value(ReflectionUtils.getDouble(field, entity))
                            .build()
                    );
                });
        return forSave;
    }
}
