package zadatak.com.twitter;

import org.junit.After;
import org.junit.Before;

import com.twitter.Twitter;

public class TwitterTest {
	Twitter instance;

	@Before
	public void setUp() throws Exception {
		instance = new Twitter();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	
}
