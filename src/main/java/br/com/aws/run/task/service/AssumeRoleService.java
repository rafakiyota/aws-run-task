package br.com.aws.run.task.service;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;

@Service
public class AssumeRoleService {

	public BasicSessionCredentials assumeRole(String roleArn, Regions region) {
		
		AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard().withRegion(region).build();
		
		AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest().withRoleArn(roleArn).withRoleSessionName("session-role-list-clusters");
		
		AssumeRoleResult assumeRoleResult = stsClient.assumeRole(assumeRoleRequest);
		Credentials credentials = assumeRoleResult.getCredentials();
		
		BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(
				credentials.getAccessKeyId(), credentials.getSecretAccessKey(), credentials.getSessionToken());
		
    	return basicSessionCredentials;
	}
}
