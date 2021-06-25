package runner;


import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="src/test/java/features/"
, glue={"stepDefinition","hook"}
, tags="@Test"
)
public class TestRunner extends AbstractTestNGCucumberTests {
	

		@Override
	    @DataProvider(parallel=false)
	    public Object[][] scenarios() {  	
	    	 return super.scenarios();
	    }
	    
	    
	    
}

