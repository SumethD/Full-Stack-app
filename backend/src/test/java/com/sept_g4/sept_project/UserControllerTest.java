package com.sept_g4.sept_project;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testLoginUser() throws Exception {
        //created a new user login DTO
        LoginDTO loginDTO = new LoginDTO("john@example.com", "password");
        //gives him details
        Users user = new Users(1L, "John", "Doe", new Date(), "john@example.com", "1234567890", "password");
        //logs in
        LoginResponse loginResponse = new LoginResponse("Login Success", true);


        //returns the login response 
        when(userService.loginUser(any(LoginDTO.class))).thenReturn(loginResponse);
        //attempts to find it by email
        when(userRepository.findByEmail(eq("john@example.com"))).thenReturn(user);



        RequestBuilder requestBuilder = MockMvcRequestBuilders
                //posts request to login endpoint
                .post("/login")
                //sets the type to json
                .contentType(MediaType.APPLICATION_JSON)
                //converts to json
                .content(asJsonString(loginDTO));

        mockMvc.perform(requestBuilder)
                //initiates request and expects code to be okay
                .andExpect(status().isOk())
                //expects the json message to say login success
                .andExpect(jsonPath("$.message").value("Login Success"))
                //expects the status to be true
                .andExpect(jsonPath("$.status").value(true));
        //verfies if theyve been called more than once and will fail if that is the case
        verify(userService, times(1)).loginUser(any(LoginDTO.class));
    }

    // Utility method to convert an object to its JSON representation
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }



}





