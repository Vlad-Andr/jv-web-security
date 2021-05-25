package mate.service.auth;

import java.util.Optional;
import mate.exception.AuthenticationException;
import mate.lib.Injector;
import mate.lib.Service;
import mate.model.Driver;
import mate.service.DriverService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Injector injector = Injector.getInstance("mate");
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverService.findByLogin(login);
        if (driver.isEmpty()) {
            throw new AuthenticationException("Login or password was incorrect");
        }
        if (driver.get().getPassword().equals(password)) {
            return driver.get();
        }
        throw new AuthenticationException("Login or password was incorrect");
    }
}