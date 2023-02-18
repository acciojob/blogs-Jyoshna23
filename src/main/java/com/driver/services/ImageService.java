package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);

        List<Image> imageList = blog.getImageList();

        imageList.add(image);
        blog.setImageList(imageList);

        blogRepository2.save(blog);

        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        String[] screenDimenArray = screenDimensions.split("X");

        Image image = imageRepository2.findById(id).get();
        String dimensions = image.getDimensions();
        String[] imgDimenArray = dimensions.split("X");


        int screenDimenLen = Integer.parseInt(screenDimenArray[0]);
        int screenDimenBreadth = Integer.parseInt(screenDimenArray[1]);

        int imageLen = Integer.parseInt(imgDimenArray[0]);
        int imageBreadth = Integer.parseInt(imgDimenArray[1]);

        return ( screenDimenLen / imageLen ) * (screenDimenBreadth/imageBreadth);
    }
}
