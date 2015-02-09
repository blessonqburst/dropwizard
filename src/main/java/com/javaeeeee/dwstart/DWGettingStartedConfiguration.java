package com.javaeeeee.dwstart;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class DWGettingStartedConfiguration extends Configuration {

    /**
     * User login.
     */
    @NotNull
    private String login;
    /**
     * User password.
     */
    @NotNull
    private String password;

    /**
     * Login getter.
     *
     * @return
     */
    @JsonProperty
    public String getLogin() {
        return login;
    }

    /**
     * Password getter.
     *
     * @return
     */
    @JsonProperty
    public String getPassword() {
        return password;
    }

}
