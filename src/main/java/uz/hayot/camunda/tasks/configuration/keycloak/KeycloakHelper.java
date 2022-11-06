package uz.hayot.camunda.tasks.configuration.keycloak;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;

@Configuration
public class KeycloakHelper {

    private final String secretKey;
    private final String clientId;
    private final String authUrl;
    private final String realm;

    public KeycloakHelper( @Value("${call-center.credentials.secret}") String secretKey,
                                      @Value("${call-center.resource}") String clientId,
                                      @Value("${call-center.auth-server-url}") String authUrl,
                                      @Value("${call-center.realm}") String realm) {
        this.secretKey = secretKey;
        this.clientId = clientId;
        this.authUrl = authUrl;
        this.realm = realm;
    }

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .grantType(CLIENT_CREDENTIALS)
                .serverUrl(authUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(secretKey)
                .build();
    }


    public String realm(){
        return realm;
    }


}
