package com.taskPlanner.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskPlanner.Beans.Invites;
import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.DTO.LoginDTO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.MemberException;
import com.taskPlanner.ServiceInterface.MemberService;

@RestController
@RequestMapping("taskplanner/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/register")
	public ResponseEntity<OutputMessage> registerMember(@RequestBody Member member) throws MemberException{
		return new ResponseEntity<OutputMessage>(memberService.registerMember(member), HttpStatus.CREATED);
	}
	
	@GetMapping("/login")
	public ResponseEntity<Member> loginMember(@RequestBody LoginDTO login) throws MemberException{
		return new ResponseEntity<Member>(memberService.loginMember(login), HttpStatus.OK);
	}
	
	@GetMapping("/getMyProjects/{memberId}")
	public ResponseEntity<List<Project>> getAllMyProjects(@PathVariable("memberId") Integer memberId) throws MemberException{
		return new ResponseEntity<List<Project>>(memberService.getAllMyProjects(memberId), HttpStatus.OK);
	}
	
	@GetMapping("/getMyInvites/{memberId}")
	public ResponseEntity<List<Invites>> getAllMyInvites(@PathVariable("memberId") Integer memberId) throws MemberException{
		return new ResponseEntity<List<Invites>>(memberService.getAllMyInvites(memberId), HttpStatus.OK);
	}
	
}
