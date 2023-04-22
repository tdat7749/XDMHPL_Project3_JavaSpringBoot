/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Repository;

import BanHang.Entities.Vegetable;
import BanHang.ViewModel.VegetableVm;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author hp
 */
public interface VegetableRepository extends CrudRepository<Vegetable, Integer> {

    @Query(value = "SELECT NEW BanHang.ViewModel.VegetableVm(v.vegetableId,c.name,v.name,v.unit,v.amount,v.image,v.price,v.sold) FROM Vegetable AS v JOIN v.Category AS c WHERE v.vegetableId = :id")
    VegetableVm GetVegetableVm(@Param("id") Integer id);

    @Query(value = "SELECT v from Vegetable AS v LEFT JOIN v.Category AS c WHERE c.categoryId = :CategoryId")
    Iterable<Vegetable> findByCategoryId(@Param("CategoryId") Integer CategoryId);

    @Query(value = "SELECT v from Vegetable AS v LEFT JOIN v.Category AS c WHERE v.name LIKE %:keyword% OR c.name LIKE %:keyword%")
    Iterable<Vegetable> findByCategoryNameOrVegetableName(@Param("keyword") String keyword);

    // @Query(value = "Update Vegetable AS v SET v.amount = v.amount - :amount WHERE
    // v.vegetableId = :id", nativeQuery = true)
    // Boolean UpdateAmount(@Param("amount") Integer amount, @Param("id") Integer
    // id);
}
