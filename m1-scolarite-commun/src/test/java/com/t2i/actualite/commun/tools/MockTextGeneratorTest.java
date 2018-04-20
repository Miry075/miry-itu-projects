package com.t2i.actualite.commun.tools;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.t2i.actualite.commun._config.CommonTestConfig;
import com.t2i.actualite.commun.test.junit.SpringUnitTest;
@ActiveProfiles("tests")
@ContextConfiguration(classes=CommonTestConfig.class)
public class MockTextGeneratorTest extends SpringUnitTest {
	
	@Autowired
	MockTextGenerator generator ;
	
	
	@Test
	public void GetOnlyOne(){
		
		String got = generator.getFixed(4) ;
		Assert.assertTrue(got.length()>0);
		
	}

}
