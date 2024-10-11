package com.ohgiraffers.springdatajpa.menu.service;

import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.entity.Menu;
import com.ohgiraffers.springdatajpa.category.repository.CategoryRepository;
import com.ohgiraffers.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

	private final MenuRepository menuRepository;
	private final ModelMapper modelMapper;

	// @Autowired를 작성하지 않아도 자동 적용됨을 잊지 말자.
	public MenuService(MenuRepository menuRepository, ModelMapper modelMapper) {
		this.menuRepository = menuRepository;
		this.modelMapper = modelMapper;
	}
	
	public MenuDTO findMenuByCode(int menuCode) {
		
		Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
		
		/* modelMapper를 이용하여 entity를 DTO로 변환해서 반환 */
		return modelMapper.map(menu, MenuDTO.class);
	}
	
	public List<MenuDTO> findMenuList() {
		
		List<Menu> menuList = menuRepository.findAll(Sort.by("menuCode").descending());

		// 람다식 사용 버전
		return menuList
				.stream()
				.map(menu -> modelMapper.map(menu, MenuDTO.class))
				.collect(Collectors.toList());

		// 람다식 대신 for문 사용한 버전
//		List<MenuDTO> menuDTOList = new ArrayList<>();
//		for (Menu menu : menuList) {
//			MenuDTO menuDTO = modelMapper.map(menu, MenuDTO.class);
//			menuDTOList.add(menuDTO);
//		}
//		return menuDTOList;
	}

	public Page<MenuDTO> findMenuList(Pageable pageable) {
		
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
					pageable.getPageSize(),
					Sort.by("menuCode").descending());
		
		Page<Menu> menuList = menuRepository.findAll(pageable);
		
		return menuList.map(menu -> modelMapper.map(menu, MenuDTO.class));
	}

	public List<MenuDTO> findByMenuPrice(Integer menuPrice) {

		List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);

//		List<Menu> menuList = menuRepository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);

//		List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice, Sort.by("menuPrice").descending());

		// 람다식 사용 버전
		return menuList
				.stream()
				.map(menu -> modelMapper.map(menu, MenuDTO.class))
				.collect(Collectors.toList());

		// 람다식 대신 for문 사용한 버전
//		List<MenuDTO> menuDTOList = new ArrayList<>();
//		for (Menu menu : menuList) {
//			MenuDTO menuDTO = modelMapper.map(menu, MenuDTO.class);
//			menuDTOList.add(menuDTO);
//		}
//		return menuDTOList;
	}

	// CategoryService 참고

	@Transactional
	public void registNewMenu(MenuDTO newMenu) {
		
		menuRepository.save(modelMapper.map(newMenu, Menu.class));
	}

	@Transactional
	public void modifyMenu(MenuDTO modifyMenu) {

		/* Note. Menu 엔티티의 @Setter 작성 후 수행 */
		Menu foundMenu = menuRepository.findById(modifyMenu.getMenuCode()).orElseThrow(IllegalArgumentException::new);
		foundMenu.setMenuName(modifyMenu.getMenuName());
	}

	@Transactional
	public void deleteMenu(Integer menuCode) {
		menuRepository.deleteById(menuCode);
	}



}
