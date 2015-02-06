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

    @JsonProperty
    public String getLogin() {
        return login;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

}
