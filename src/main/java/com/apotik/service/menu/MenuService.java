package com.apotik.service.menu;

import com.apotik.dto.menu.MenuCreateDto;
import com.apotik.dto.menu.MenuUpdateDto;
import com.apotik.dto.other.ReturnSearch;
import com.apotik.entity.Menu;
import com.apotik.entity.Obat;
import com.apotik.exception.ResourceNotFoundException;
import com.apotik.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data Menu Cannot Find"));
    }

    public void saveMenu(MenuCreateDto menuCreateDto) {
        Menu menu =
                Menu.builder()
                        .namaMenu(menuCreateDto.getNamaMenu())
                        .deskripsiMenu(menuCreateDto.getDeskripsiMenu())
                        .harga(menuCreateDto.getHarga())
                        .build();
        menuRepository.save(menu);
    }

    public void updateMenu(MenuUpdateDto menuUpdateDto){
        Optional<Menu> menu =
                Optional.ofNullable(menuRepository.findById(menuUpdateDto.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Menu Cannot Found In Database"));
        if(menu.isPresent()){
            menu.get().setId(menuUpdateDto.getId());
            menu.get().setNamaMenu(menuUpdateDto.getNamaMenu());
            menu.get().setDeskripsiMenu(menuUpdateDto.getDeskripsiMenu());
            menu.get().setHarga(menuUpdateDto.getHarga());
            menuRepository.save(menu.get());
        }
    }

    public void deleteMenu(Long id){
        Optional<Menu> menu =
                Optional.ofNullable(menuRepository.findById(id))
                        .orElseThrow(() -> new ResourceNotFoundException("Menu Cannot Found In Database"));
        if(menu.isPresent()){
            menuRepository.delete(menu.get());
        }
    }




}
