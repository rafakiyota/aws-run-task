package br.com.aws.run.task.command;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.amazonaws.services.ecs.model.DescribeTaskDefinitionResult;
import com.amazonaws.services.ecs.model.RunTaskResult;

import br.com.aws.run.task.service.EcsTaskService;

@Order(1)
@Component
public class RunTask implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(RunTask.class);
	
	@Autowired
	private EcsTaskService ecsTaskService;

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
	    	
	    	DescribeTaskDefinitionResult describeTask = ecsTaskService.describeTask(task);
			
	    	logger.info("type: {}", args.getOptionValues("type").get(0)); 
	    	logger.info("describe-task: {}", describeTask);
		}
    	if (args.containsOption("type") && "run-task".equals(args.getOptionValues("type").get(0))) {
    		
    		if (!args.containsOption("subnetids") || args.getOptionValues("subnetids").get(0).isEmpty()) {
        		throw new IllegalArgumentException("argumento subnetids não enviado!!!");
        	}
    		if (!args.containsOption("securitygroupids") || args.getOptionValues("securitygroupids").get(0).isEmpty()) {
    			throw new IllegalArgumentException("argumento securitygroupids não enviado!!!");
    		}
    		
    		String subnetids = args.getOptionValues("subnetids").get(0);
    		String securitygroupids = args.getOptionValues("securitygroupids").get(0);
    		
    		RunTaskResult taskResult = ecsTaskService.runTask(cluster, task, subnetids, securitygroupids);
    		
    		logger.info("type: {}", args.getOptionValues("type").get(0));
    		logger.info("run-task: {}", taskResult);
    	}    	
    }
}
