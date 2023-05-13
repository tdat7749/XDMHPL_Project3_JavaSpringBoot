/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Services;

import BanHang.DataBinding.FilterVegetableDTO;
import BanHang.DataBinding.OrderDetailDTO;
import BanHang.Entities.Vegetable;
import BanHang.ViewModel.VegetableVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author hp
 */

@Service
public interface IVegetableService {
    @Autowired
    public Iterable<Vegetable> getAll();

    public VegetableVm GetVegetableVm(Integer id);

    // public Iterable<Vegetable> FilterVegetable(Integer CategoryId);

    // public Iterable<Vegetable> SearchVegetables(String keyword);

    public List<Vegetable> GetFilterVegetable(FilterVegetableDTO filter);

    Boolean UpdateAmount(Collection<OrderDetailDTO> orderDetailDTOs);
}
