package com.apotik.controller.api;

import com.apotik.dto.menu.MenuCreateDto;
import com.apotik.dto.menu.MenuUpdateDto;
import com.apotik.dto.other.ReturnSearch;
import com.apotik.entity.Menu;
import com.apotik.service.menu.MenuService;
import com.apotik.utils.ErrorUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/getMenus")
    public List<Menu> getMenus() throws Exception {
        try{
            return menuService.getMenus();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

    }

    @GetMapping(value = "/getById/{id}")
    public Menu getMenuById(@PathVariable("id")Long id){
        return menuService.findById(id);
    }

    @PostMapping(value = "/createMenu")
    public String createMenu(@RequestBody @Valid MenuCreateDto menuCreateDto,
                             BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }

        try{
            menuService.saveMenu(menuCreateDto);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();

    }

    @PostMapping(value = "/updateMenu")
    public String updateMenu(@RequestBody @Valid MenuUpdateDto menuUpdateDto,
                             BindingResult bindingResult) throws Exception {

        JSONObject jsonObject = new JSONObject();
        if (bindingResult.hasErrors()){
            return ErrorUtils.customErrors(bindingResult.getAllErrors());
        }

        try{
            menuService.updateMenu(menuUpdateDto);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();

    }

    @PostMapping(value = "/deleteMenu/{id}")
    public String deleteMenu(@PathVariable("id") Long id) throws Exception {

        JSONObject jsonObject = new JSONObject();
        try{
            menuService.deleteMenu(id);
            jsonObject.put("status", "Success");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return jsonObject.toString();

    }

    @GetMapping(value = "/searchMenuByName")
    public List<ReturnSearch> searchMenuByName(String q) throws Exception {

        try{
            return menuService.findMenuByName(q);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
