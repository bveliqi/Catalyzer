package com.hackzurich.catalyzer.auth;

import com.google.common.base.Optional;
import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.jdbi.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Created by behar on 11.10.14.
 */
public class SimpleAuthenticator implements Authenticator<BasicCredentials, User> {

    private final UserDao userDao;

    public SimpleAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        final String username = credentials.getUsername();
        final User user = userDao.getByName(username);
        if(user == null) {
            throw new AuthenticationException("User with username " + username + " does not exist.");
        }
        final String storedPassword = user.getPassword();
        final String givenPassword = credentials.getPassword();
        final String hashedPassword = PasswordHashing.hash(givenPassword);
        if (hashedPassword.equals(storedPassword)) {
            return Optional.of(user);
        }
        return Optional.absent();
    }
}