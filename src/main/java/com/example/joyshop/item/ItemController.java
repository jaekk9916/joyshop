package com.example.joyshop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRopository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;

    @GetMapping("/list")
    String list(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "list";
    }

    @GetMapping("/write")
    String write(Model model){
        return "write";
    }

    @PostMapping("/add")
    String writePost(@ModelAttribute Item item){
        itemService.saveItem(item.getTitle(), item.getPrice());
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()) {
            model.addAttribute("data", result.get());
            return "detail";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model){
        Optional<Item> result = itemRepository.findById(id);
        if(result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(Long id, String title, Integer price){
        itemService.editItem(id, title, price);
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id){
        itemService.deleteItem(id);
        System.out.println("!!id: "+id);
//        return ResponseEntity.status(200).body("Complete to delete item");
        return ResponseEntity.ok().build();

    }

    @GetMapping("/list/page/{page}")
    String getListPage(Model model, @PathVariable Integer page){
        Page<Item> result =  itemRepository.findPageBy(PageRequest.of(page - 1,5));
        model.addAttribute("items", result);
        return "list";
    }

    @GetMapping("/presigned-url")
    ResponseEntity<String> getURL(@RequestParam String filename){
        var presignedUrl = s3Service.createPresignedUrl("test/"+filename);
        return ResponseEntity.ok(presignedUrl);
    }
}
