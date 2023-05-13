/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BanHang.Services;

import BanHang.Entities.Vegetable;
import BanHang.Entities.Category;
import BanHang.Repository.CategoryRepository;
import BanHang.Repository.VegetableRepository;
import BanHang.ViewModel.VegetableVm;
import jakarta.transaction.Transactional;
import BanHang.DataBinding.FilterVegetableDTO;
import BanHang.DataBinding.OrderDetailDTO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 *
 * @author hp
 */
@Service
public class VegetableService implements IVegetableService {

    private final VegetableRepository _vegetableRepository;
    private final CategoryRepository _categoryRepository;

    public VegetableService(VegetableRepository vegetableRepository, CategoryRepository categoryRepository) {
        _vegetableRepository = vegetableRepository;
        _categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Vegetable> getAll() {
        return _vegetableRepository.findAll();
    }

    @Override
    public VegetableVm GetVegetableVm(Integer id) {
        return _vegetableRepository.GetVegetableVm(id);
    }

    // @Override
    // public Iterable<Vegetable> FilterVegetable(Integer CategoryId) {
    // return _vegetableRepository.findByCategoryId(CategoryId);
    // }

    // @Override
    // public Iterable<Vegetable> SearchVegetables(String keyword) {
    // return _vegetableRepository.findByCategoryNameOrVegetableName(keyword);
    // }

    @Override
    public List<Vegetable> GetFilterVegetable(FilterVegetableDTO filter) {
        Iterable<Vegetable> iterableVegetable = _vegetableRepository.findAll();

        // convert Iterable to List
        List<Vegetable> listVegetable = new ArrayList<>();
        iterableVegetable.forEach(listVegetable::add);

        // filter vegetable
        if (filter != null) {
            if (filter.getCatId() != null) {
                Category cat = _categoryRepository.findById(filter.getCatId()).orElse(null);
                listVegetable = listVegetable
                        .stream()
                        .filter(x -> x.getCategory() == cat)
                        .collect(Collectors.toList());
            }

            if (filter.getKeyword() != null) {
                listVegetable = listVegetable
                        .stream()
                        .filter(x -> x.getName().toLowerCase().contains(filter.getKeyword().toLowerCase()))
                        .collect(Collectors.toList());
            }

            if (filter.getSort() != null) {
                if (filter.getSort().equals("best-seller")) {
                    listVegetable.sort(Comparator.comparing(Vegetable::getSold).reversed());
                }
            }
        }

        return listVegetable;
    }

    @Override
    @Transactional
    @Modifying
    public Boolean UpdateAmount(Collection<OrderDetailDTO> orderDetailDTOs) {
        for (OrderDetailDTO item : orderDetailDTOs) {
            // Boolean checkUpdate = _vegetableRepository.UpdateAmount(item.getQuantity(),
            // item.getVegetableId());
            Vegetable vegetable = _vegetableRepository.findById(item.getVegetableId()).orElse(null);
            if (vegetable == null) {
                return false;
            }
            vegetable.setAmount(vegetable.getAmount() - item.getQuantity());
            vegetable.setSold(vegetable.getSold() + item.getQuantity());
            _vegetableRepository.save(vegetable);
        }
        return true;
    }
}
