package com.taskPlanner.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskPlanner.Beans.Status;
import com.taskPlanner.Beans.Task;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.TaskException;
import com.taskPlanner.ServiceInterface.TaskService;

@RestController
@RequestMapping("taskplanner/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/add")
	public ResponseEntity<OutputMessage> createTaskForTheSprintInProjectHandler(@RequestBody Task task)throws TaskException{
		return new ResponseEntity<OutputMessage>(taskService.createTaskForTheSprintInProject(task),
				HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<OutputMessage> deleteTaskByIdHandler(@PathVariable("taskId") Integer taskId)throws TaskException{
		return new ResponseEntity<OutputMessage>(taskService.deleteTaskById(taskId),
				HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus/{taskId}/{newStatus}")
	public ResponseEntity<OutputMessage> updateTaskStatusHandler(@PathVariable("taskId") Integer taskId, 
			@PathVariable("newStatus") Status newStatus)throws TaskException{
		return new ResponseEntity<OutputMessage>(taskService.updateTaskStatus(taskId, newStatus),
				HttpStatus.OK);
	}
	
	@PutMapping("reassign/{taskId}/{memberId}")
	public ResponseEntity<OutputMessage> reassignTaskToAnotherTeamMemberHandler(@PathVariable("taskId") Integer taskId, 
			@PathVariable("memberId") Integer memberId)throws TaskException{
		return new ResponseEntity<OutputMessage>(taskService.reassignTaskToAnotherTeamMember(taskId, memberId),
				HttpStatus.OK);
	}
}
