package com.tutorial.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"jwt.secret=abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789ab",
		"jwt.expiration=3600000"
})
class LibraryApplicationTests {

	@Test
	void contextLoads() {
	}

}