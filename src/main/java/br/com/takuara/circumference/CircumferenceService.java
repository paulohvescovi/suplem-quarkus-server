package br.com.takuara.circumference;

import br.com.takuara.circumferencehistory.CircumferenceHistory;
import br.com.takuara.framework.BaseService;
import br.com.takuara.user.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CircumferenceService extends BaseService<Circumference> {

    List<Circumference> findAll(@NotNull User user);

}
