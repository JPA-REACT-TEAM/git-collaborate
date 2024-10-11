package com.ohgiraffers.springdatajpa.menu.controller;

import java.util.List;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButtonInfo;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	// @Autowired를 작성하지 않아도 자동 적용됨을 잊지 말자.
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}


//	@GetMapping("/list")
//	public String findMenuList(Model model) {
//
//		List<MenuDTO> menuList = menuService.findMenuList();
//
//		model.addAttribute("menuList", menuList);
//
//		return "menu/list";
//	}
	
	/**
	 * 주어진 Pageable 정보를 바탕으로 메뉴 리스트를 조회하고, Model에 페이지네이션 정보를 추가한 후 반환한다.
	 *
	 * <p>{@link org.springframework.data.domain.Pageable} 객체를 인자로 받아 페이지 요청 정보를
	 * 처리한다. @PageableDefault 어노테이션을 통해 기본 페이지 설정을 지정할 수 있다.</p>
	 * <p>예를 들어, 기본 페이지 크기나 정렬 기준을 설정할 수 있으며, 필요한 경우 클라이언트 요청에 따라 동적으로
	 * 변경될 수도 있다.</p>
	 *
	 * @param pageable {@link org.springframework.data.domain.Pageable} 객체로, 페이지 번호, 크기, 정렬 정보를 관리한다.
	 *                  {@code @PageableDefault(size = 10, sort = "name")} 와 같이 기본 페이지 크기를 10, 정렬 기준을 이름으로 설정할 수 있다.
	 * @param model {@link org.springframework.ui.Model} 객체로, 뷰에 페이지 정보와 메뉴 리스트를 추가하는 데 사용된다.
	 * @return 조회된 {@link java.util.List} 객체로, DB로부터 검색된 메뉴 리스트를 반환한다.
	 */

	// menuCode
	// 진기
    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode, Model model) {

        MenuDTO menu = menuService.findMenuByCode(menuCode);

        model.addAttribute("menu", menu);

        return "menu/detail";
    }


	// menuList
	// 성균
	@GetMapping("/list")
	public String findMenuList(@PageableDefault Pageable pageable, Model model) {

		/* page -> number, size, sort 파라미터가 Pageable 객체에 담긴다. */
		log.info("pageable : {}", pageable);

		Page<MenuDTO> menuList = menuService.findMenuList(pageable);

		log.info("조회한 내용 목록 : {}", menuList.getContent());
		log.info("총 페이지 수 : {}", menuList.getTotalPages());
		log.info("총 메뉴 수 : {}", menuList.getTotalElements());
		log.info("해당 페이지에 표시 될 요소 수 : {}", menuList.getSize());
		log.info("해당 페이지에 실제 요소 수 : {}", menuList.getNumberOfElements());
		log.info("첫 페이지 여부 : {}", menuList.isFirst());
		log.info("마지막 페이지 여부 : {}", menuList.isLast());
		log.info("정렬 방식 : {}", menuList.getSort());
		log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(menuList);

		model.addAttribute("paging", paging);
		model.addAttribute("menuList", menuList);

		return "menu/list";
	}


	// queryMethod
	// 예진

	// menuSearch

	// menuRegist
	// 성균

	@GetMapping("/regist")
	public void registPage() {}

	@PostMapping("/regist")
	public String registNewMenu(MenuDTO newMenu) {
		menuService.registNewMenu(newMenu);

		return "redirect:/menu/list";
	}

	// menuModify
	// 진기

	// menuDelete
	// 예진

}
