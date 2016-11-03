package com.example.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Answer;
import com.example.domain.AnswerRepository;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "/users/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(loginUser, question,contents);
		answerRepository.save(answer);
		
		return String.format("redirect:/questions/%d", questionId);
	}
	
	
	
}
