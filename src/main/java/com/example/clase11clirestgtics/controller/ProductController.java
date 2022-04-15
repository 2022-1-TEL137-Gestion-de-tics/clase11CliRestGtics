package com.example.clase11clirestgtics.controller;


import com.example.clase11clirestgtics.dao.*;
import com.example.clase11clirestgtics.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    SupplierDao supplierDao;

    @GetMapping({"/list", "", "/"})
    public String listarProductos(Model model) {
        model.addAttribute("listaProductos", productDao.listarProductos());
        return "product/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("listaCategorias", categoryDao.listarCategorias());
        model.addAttribute("listaProveedores", supplierDao.listarProveedores());
        return "product/form";
    }

    @PostMapping("/save")
    public String guardarProducto(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                                  Model model, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listaCategorias", categoryDao.listarCategorias());
            model.addAttribute("listaProveedores", supplierDao.listarProveedores());
            return "product/form";
        } else {
            String msg = "Producto " + (product.getId() == 0 ? "creado" : "actualizado") + " exitosamente";
            attr.addFlashAttribute("msg", msg);
            productDao.guardarProducto(product);
            return "redirect:/product";
        }
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model, @RequestParam("id") int id) {

        Product productBuscar = productDao.obtenerProductoPorId(id);

        if (productBuscar != null) {
            model.addAttribute("product", productBuscar);
            model.addAttribute("listaCategorias", categoryDao.listarCategorias());
            model.addAttribute("listaProveedores", supplierDao.listarProveedores());
            return "product/form";
        } else {
            return "redirect:/product";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model, @RequestParam("id") int id, RedirectAttributes attr) {

        Product productBuscar = productDao.obtenerProductoPorId(id);

        if (productBuscar != null) {
            productDao.borrarProducto(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        }
        return "redirect:/product";

    }
}
