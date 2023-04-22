/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Services;

import BanHang.Entities.Category;
import BanHang.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */

@Service
public class CategoryService implements ICategoryService {

    @Autowired(required = true)
    private CategoryRepository _categoryRepository;

    // @Autowired
    // public CategoryService(CategoryRepository categoryRepository){
    // _categoryRepository = categoryRepository;
    // }

    @Override
    public Iterable<Category> getAll() {
        return _categoryRepository.findAll();
    }

}
