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
        groupsQuery = "select role from tutors__roles where id = (select id from tutors where login = ?)",
        hashAlgorithm = Pbkdf2PasswordHash.class
)
public class AuthenticationConfig {
}
