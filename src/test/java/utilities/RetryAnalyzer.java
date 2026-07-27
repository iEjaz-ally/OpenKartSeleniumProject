package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	int maxRetry = 4;
	int count = 0;
	
	@Override
	public boolean retry(ITestResult result) {
		
		
				
				if(count< maxRetry) {
					count++;
					return true;
				}
				
				return false;
	}



}
