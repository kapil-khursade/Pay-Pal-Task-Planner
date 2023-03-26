package com.taskPlanner.ServiceInterfaceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskPlanner.Beans.Invites;
import com.taskPlanner.Beans.Member;
import com.taskPlanner.Beans.Project;
import com.taskPlanner.DAO.MemberDAO;
import com.taskPlanner.DTO.LoginDTO;
import com.taskPlanner.DTO.OutputMessage;
import com.taskPlanner.Exceptions.MemberException;
import com.taskPlanner.ServiceInterface.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public OutputMessage registerMember(Member member) throws MemberException {
		// TODO Auto-generated method stub
		member.setListOfInvites(new ArrayList());
		member.setProjectWorkingOn(new ArrayList<>());
		Member savedMember = memberDAO.save(member);
		if(savedMember==null)throw new MemberException("Member Failed To Register");
		return new OutputMessage("Member Registered Sucessfully");
	}

	@Override
	public Member loginMember(LoginDTO login) throws MemberException {
		// TODO Auto-generated method stub
		Optional<Member> memberGet  = memberDAO.findByUsername(login.getUsername());
		if(memberGet.isEmpty())throw new MemberException("Username Does Not Exist");
		Member member = memberGet.get();
		if(!member.getPassword().equals(login.getPassword()))throw new MemberException("Wrong Password");
		return member;
	}

	@Override
	public List<Project> getAllMyProjects(Integer memberId) throws MemberException {
		// TODO Auto-generated method stub
		Optional<Member> member = memberDAO.findById(memberId);
		if(member.isEmpty())throw new MemberException("Member Does Not Exist");
		
		return null;
	}

	@Override
	public List<Invites> getAllMyInvites(Integer memberId) throws MemberException {
		// TODO Auto-generated method stub
		Optional<Member> member = memberDAO.findById(memberId);
		if(member.isEmpty())throw new MemberException("Member Does Not Exist");
		List<Invites> myInvitesList = member.get().getListOfInvites();
		return myInvitesList;
	}

}
