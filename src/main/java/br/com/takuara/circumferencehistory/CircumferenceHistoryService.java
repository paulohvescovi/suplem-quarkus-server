package br.com.takuara.circumferencehistory;

import br.com.takuara.framework.BaseService;
import br.com.takuara.user.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CircumferenceHistoryService extends BaseService<CircumferenceHistory> {

    List<CircumferenceHistory> findAll(@NotNull User user);

}
