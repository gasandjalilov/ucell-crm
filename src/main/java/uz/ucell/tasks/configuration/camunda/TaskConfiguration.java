package uz.ucell.tasks.configuration.camunda;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SelfManagedAuthentication;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.ZeebeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class TaskConfiguration {


    public TaskConfiguration(SelfManagedAuthentication sma) {
        this.sma = sma;
    }

    SelfManagedAuthentication sma = new SelfManagedAuthentication()
            .clientId("tasklist")
            .clientSecret("aEeRP1nM4cyIQRVUnhQMGX5vIYmgYEJm")
            .keycloakRealm("Ucell")
            .keycloakUrl("http://192.168.23.225:8080");


    public SelfManagedAuthentication selfManagedAuthentication(){
        return sma;
    }


    @Bean
    public CamundaTaskListClient client() throws TaskListException {
        CamundaTaskListClient client = new CamundaTaskListClient.Builder()
                .shouldReturnVariables()
                .taskListUrl("http://192.168.51.12:8082")
                .authentication(sma).build();
        return client;
    }

    @Bean
    public ZeebeClient zeebeClient(){
        return ZeebeClient.newClientBuilder()
                .gatewayAddress("192.168.51.12:26500")
                .usePlaintext()
                .build();
    }
}
