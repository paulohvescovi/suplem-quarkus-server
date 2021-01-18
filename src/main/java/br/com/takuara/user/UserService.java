package br.com.takuara.user;

import br.com.takuara.framework.BaseService;

public interface UserService extends BaseService<User> {

    User findByEmail(String email);

}
