package fei.upce.st60982.pocketbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fei.upce.st60982.pocketbook.DataClasses.Task;
import fei.upce.st60982.pocketbook.DataClasses.User;
import fei.upce.st60982.pocketbook.Repositories.TaskDAO;
import fei.upce.st60982.pocketbook.Repositories.UserDAO;
import fei.upce.st60982.pocketbook.Security.LoginModel;
import fei.upce.st60982.pocketbook.Security.SignInData;
import fei.upce.st60982.pocketbook.Services.UserSingInService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PocketBookControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private UserDAO userRepository;

	@MockBean
	private TaskDAO taskRepository;

	@MockBean
	private UserSingInService userSignInService;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testLogin() throws Exception {
		String username = "Username1";
		String password = "Password1";
		User mockUser = new User();
		mockUser.setUsername(username);
		mockUser.setPassword(password);
		String mockToken = "dummyToken";

		// Mock the userRepository.findByUsername() method
		when(userRepository.findByUsername(username)).thenReturn(mockUser);

		// Mock the userSignInService.signIn() method
		when(userSignInService.signIn(username, password)).thenReturn(mockToken);

		// Perform the request and assert the response
		mockMvc.perform(post("/authenticate")
						.content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.token").value(mockToken))
				.andExpect(jsonPath("$.loggedInUser.Username").value(username));
	}

	@Test
	public void testDoneChangeTask() throws Exception {
		// Login and obtain the token
		String username = "Username1";
		String password = "Password1";
		String token = obtainToken(username, password); // Implement the method to obtain the token

		// Create a mock Task object
		Task mockTask = new Task();
		mockTask.setId(1);
		mockTask.setDone(false);

		// Save the mock Task object to the repository
		taskRepository.save(mockTask);

		// Mock the behavior of taskRepository.findTaskById()
		when(taskRepository.findTaskById(1)).thenReturn(mockTask);

		// Perform the request with the token
		mockMvc.perform(get("/task/doneChange/1")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));

		// Retrieve the task from the repository and verify the updated value
		Task updatedTask = taskRepository.findTaskById(1);
		assertTrue(updatedTask.isDone());
	}

	private String obtainToken(String username, String password) throws Exception {
		// Create a SignInData object with the username and password
		SignInData signInData = new SignInData(username, password);

		// Perform the login request and obtain the token
		MvcResult result = mockMvc.perform(post("/authenticate")
						.content(asJsonString(signInData))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		// Extract the token from the response body
		String responseContent = result.getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		LoginModel loginModel = objectMapper.readValue(responseContent, LoginModel.class);
		return loginModel.getToken();
	}

	// Utility method to convert an object to JSON string
	private static String asJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}