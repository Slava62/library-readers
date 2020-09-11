package com.example.readers.controller;

import com.example.readers.exception.ResourceNotFoundException;
import com.example.readers.model.Reader;
import com.example.readers.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    //createReader
    @PostMapping("/readers")
    public ResponseEntity <Reader> createReader(@RequestBody Reader reader) {
        String r= reader.getName();
        return ResponseEntity.ok().body(this.readerService.createReader(reader));
    }
    //readReaderById
    @GetMapping("/readers/{id}")
    public ResponseEntity <Reader> getReaderById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(readerService.getReaderById(id));
    }
    //readAllReaders
    @GetMapping("/readers")
    public ResponseEntity<List<Reader>> getAllReader() {
      return ResponseEntity.ok().body(readerService.getAllReaders());
    }
    //updateReader
    @PutMapping("/readers/{id}")
    public ResponseEntity <Reader> updateReader(@PathVariable Long id, @RequestBody Reader reader) throws ResourceNotFoundException {
        reader.setId(id);
        return ResponseEntity.ok().body(this.readerService.updateReader(reader, id));
    }
    //deleteReader
    @DeleteMapping("/readers/{id}")
    public HttpStatus deleteReader(@PathVariable Long id) {
        try{
        this.readerService.deleteReader(id);
        return HttpStatus.OK;}
        catch (ResourceNotFoundException e){
            return HttpStatus.NOT_FOUND;
        }
    }
}
