package br.com.aws.run.task.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.DescribeTaskDefinitionRequest;
import com.amazonaws.services.ecs.model.DescribeTaskDefinitionResult;
import com.amazonaws.services.ecs.model.RunTaskRequest;
import com.amazonaws.services.ecs.model.RunTaskResult;

@Order(1)
@Component
public class RunTask implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(RunTask.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
    	if (!args.containsOption("cluster") || args.getOptionValues("cluster").get(0).isEmpty()) {
    		throw new IllegalArgumentException("argumento cluster não enviado!!!");
    	}
    	if (!args.containsOption("task") || args.getOptionValues("task").get(0).isEmpty()) {
    		throw new IllegalArgumentException("argumento task não enviado!!!");
    	}
    	
    	List<String> clusters = args.getOptionValues("cluster");
		List<String> tasks = args.getOptionValues("task");
		
		String cluster = clusters.get(0);
		String task = tasks.get(0);
    	
		logger.info("cluster: {}", cluster);
		logger.info("task: {}", task); 
		
    	if (args.containsOption("type") && "describe-task".equals(args.getOptionValues("type").get(0))) {
	    	AmazonECS client = AmazonECSClientBuilder.standard().build();
	    	
	    	DescribeTaskDefinitionRequest requestTask = new DescribeTaskDefinitionRequest().withTaskDefinition(task);
	    	DescribeTaskDefinitionResult describeTask = client.describeTaskDefinition(requestTask);
			
	    	logger.info("type: {}", args.getOptionValues("type").get(0)); 
	    	logger.info("describe-task: {}", describeTask);
		}
    	if (args.containsOption("type") && "run-task".equals(args.getOptionValues("type").get(0))) {
    		AmazonECS client = AmazonECSClientBuilder.standard().build();
    		
    		RunTaskRequest request = new RunTaskRequest()
    				.withCluster(cluster)
                    .withTaskDefinition(task)
                    .withLaunchType("FARGATE");
    		
    		RunTaskResult taskResult = client.runTask(request);
    		
    		logger.info("type: {}", args.getOptionValues("type").get(0)); 
    		logger.info("run-task: {}", taskResult);
    	}    	
    }
}
