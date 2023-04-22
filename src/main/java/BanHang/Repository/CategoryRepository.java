/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Repository;

import BanHang.Entities.Category;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author hp
 */

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByCategoryId(int CategoryId);
}
