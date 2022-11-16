package com.codecool.helpinghands.service;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.ImageData;
import com.codecool.helpinghands.repository.EventRepository;
import com.codecool.helpinghands.repository.ImageDataRepository;
import com.codecool.helpinghands.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageDataService {

    private final ImageDataRepository imageDataRepository;
    private final EventService eventService;

    @Autowired
    public ImageDataService (ImageDataRepository imageDataRepository, EventService eventService){
        this.imageDataRepository = imageDataRepository;
        this.eventService = eventService;
    }

    /*
    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {
        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());
    }
     */


    public byte[] getImageByEventId(int eventId) {
        Event event = eventService.getEventById(eventId);
        Optional<ImageData> dbImage = imageDataRepository.findByEvent(event);
        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }
}
