package policonsultorio.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import policonsultorio.demo.controller.UserController;
import policonsultorio.demo.dto.LoginRequestDTO;
import policonsultorio.demo.service.UserService;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class TestUserController {


    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCrearUsuario() throws Exception {
        LoginRequestDTO user = new LoginRequestDTO(null, "alex13", "1234", "algon@gmai.com", "32536987", null, null);

        // User user = new User(alex);
        when(userService.register(any(LoginRequestDTO.class))).thenReturn(user);

        // Configurar la solicitud
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        // Realizar la solicitud
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("alex13")))
                .andReturn();


        // Verificar el resultado
        verify(userService, times(1)).register(any(LoginRequestDTO.class));
    }
}
