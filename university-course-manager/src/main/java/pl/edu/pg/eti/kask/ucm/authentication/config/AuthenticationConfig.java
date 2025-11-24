package pl.edu.pg.eti.kask.ucm.authentication.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@ApplicationScoped
@BasicAuthenticationMechanismDefinition(realmName = "Simple UCM")
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/SimpleUniversityCourseManager",
        callerQuery = "select password from tutors where login = ?",
        groupsQuery = "select role from tutor__roles where id = (select id from tutors where login = ?)",
        hashAlgorithm = Pbkdf2PasswordHash.class
)

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/authentication/login.xhtml",
                errorPage = "/authentication/login_error.xhtml"
        )
)
public class AuthenticationConfig {
}
