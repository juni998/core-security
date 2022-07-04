package com.security.coresecurity.controller.user;


import com.security.coresecurity.domain.Account;
import com.security.coresecurity.domain.AccountDto;
import com.security.coresecurity.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(value = "/mypage")
	public String myPage() throws Exception {

		return "user/mypage";
	}


	@GetMapping("/users")
	public String createUser() {
		return "user/login/register";

	}

	@PostMapping("/users")
	public String createUser(AccountDto accountDto) {

		//accountDto -> account 에 매핑하는 역할
		ModelMapper modelMapper = new ModelMapper();
		Account account = modelMapper.map(accountDto, Account.class);

		//패스워드 암호화
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		userService.createUser(account);

		return "redirect:/";

	}
}
