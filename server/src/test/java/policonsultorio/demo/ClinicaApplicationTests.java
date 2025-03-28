package policonsultorio.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;
import policonsultorio.demo.controller.UserController;
import policonsultorio.demo.dto.LoginRequestDTO;
import policonsultorio.demo.entity.User;
import policonsultorio.demo.service.UserService;



@SpringBootTest
class ClinicaApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Test 1:Save user")
    @Order(1)
    @Rollback(value = false)
    public void createUserTest() throws Exception {

            LoginRequestDTO alex = new LoginRequestDTO(null, "alex", "1234", "algon@gmai.com", "32536987", null,null);

            LoginRequestDTO userCreated = userService.register(alex);

            Assertions.assertThat(userCreated.id()).isPositive();

    }




}
