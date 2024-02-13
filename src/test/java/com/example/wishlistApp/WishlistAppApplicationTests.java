package com.example.wishlistApp;

import com.example.wishlistApp.ItemServiceTest;
import com.example.wishlistApp.UserServiceTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WishlistAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void runItemServiceTests() {
		ItemServiceTest itemServiceTest = new ItemServiceTest();
		itemServiceTest.setup(); // Call setup method to initialize mocks
		//2 tests for ItemService(With all check(repo + service + constroller))
		itemServiceTest.testGetAllItems();
		itemServiceTest.testDeleteItem_ItemNotFound();
	}

	@Test
	void runUserServiceTests() {
		UserServiceTest userServiceTest = new UserServiceTest();
		userServiceTest.setup(); // Call setup method to initialize mocks
		//1 Test for UserService(With all check(repo + service + constroller))
		userServiceTest.testRegisterUser_UserExists();
	}
}
