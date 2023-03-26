package com.taskPlanner.ServiceInterface;

import java.util.List;

import com.taskPlanner.Beans.Invites;
import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.DTO.LoginDTO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.MemberException;

public interface MemberService {

	public OutputMessage registerMember(Member member) throws MemberException;
	public Member loginMember(LoginDTO login) throws MemberException;
	public List<Project> getAllMyProjects(Integer memberId) throws MemberException;
	public List<Invites> getAllMyInvites(Integer memberId) throws MemberException;
	
}
