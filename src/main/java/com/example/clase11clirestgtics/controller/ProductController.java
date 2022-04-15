package com.example.clase11clirestgtics.controller;


import com.example.clase11clirestgtics.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    //@Autowired
    //ProductRepository productRepository;

    @GetMapping({"/list", "", "/"})
    public String listarProductos(Model model) {
        //model.addAttribute("listaProductos", productRepository.findAll());
        return "product/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(@ModelAttribute("product") Product product, Model model) {
        //model.addAttribute("listaCategorias", categoryRepository.findAll());
        //model.addAttribute("listaProveedores", supplierRepository.findAll());
        return "product/form";
    }

    @PostMapping("/save")
    public String guardarProducto(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult,
                                  Model model, RedirectAttributes attr) {

        if (bindingResult.hasErrors()) {
            //model.addAttribute("listaCategorias", categoryRepository.findAll());
            //model.addAttribute("listaProveedores", supplierRepository.findAll());
            return "product/form";
        } else {
            String msg = "Producto " + (product.getId() == 0 ? "creado" : "actualizado") + " exitosamente";
            attr.addFlashAttribute("msg", msg);
            // productRepository.save(product);
            return "redirect:/product";
        }
    }

    @GetMapping("/edit")
    public String editarTransportista(@ModelAttribute("product") Product product,
                                      Model model, @RequestParam("id") int id) {

        //Optional<Product> optProduct = productRepository.findById(id);

        //if (optProduct.isPresent()) {
            //Product product = optProduct.get();
            model.addAttribute("product", product);
            //model.addAttribute("listaCategorias", categoryRepository.findAll());
            //model.addAttribute("listaProveedores", supplierRepository.findAll());
            return "product/form";
        //} else {
            //return "redirect:/product";
        //}
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model, @RequestParam("id") int id, RedirectAttributes attr) {

        //Optional<Product> optProduct = productRepository.findById(id);

        //if (optProduct.isPresent()) {
            //productRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Producto borrado exitosamente");
        //}
        return "redirect:/product";

    }
}
