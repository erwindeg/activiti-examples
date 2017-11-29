import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource("activiti.cfg.xml")
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                System.out.println("Number of process definitions : "
                        + repositoryService.createProcessDefinitionQuery().count());
                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                Map<String, Object> inputVariables = new HashMap<>();
                inputVariables.put("text", "intial text");
                runtimeService.startProcessInstanceByKey("oneTaskProcess", inputVariables);

                System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());

                ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

                TaskService taskService = processEngine.getTaskService();
                List<Task> tasks = taskService.createTaskQuery().taskDefinitionKey("task1").includeProcessVariables().orderByTaskCreateTime().desc().list();

                for (Task task : tasks) {
                    Map<String, Object> variables = task.getProcessVariables();
                }
            }
        };

    }

    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

                Group group = identityService.newGroup("user");
                group.setName("users");
                group.setType("security-role");
                identityService.saveGroup(group);

                User admin = identityService.newUser("admin");
                admin.setPassword("admin");
                identityService.saveUser(admin);

            }
        };
    }
}