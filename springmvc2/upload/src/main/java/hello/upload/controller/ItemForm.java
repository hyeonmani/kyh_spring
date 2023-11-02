package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long id;
    private String itemName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;

    public ItemForm(Long id, String itemName, MultipartFile attachFile, List<MultipartFile> imageFiles) {
        this.id = id;
        this.itemName = itemName;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}
