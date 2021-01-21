package br.com.takuara.user;

import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.security.Role;

import javax.ws.rs.core.SecurityContext;
import java.util.Comparator;
import java.util.function.Supplier;

public interface UserService extends BaseService<User> {

    Role getMainRole(User user);
    boolean isAdmin(User user);
    User findByEmail(String email);

}
