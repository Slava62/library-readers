package com.example.readers.service;

import com.example.readers.exception.ResourceNotFoundException;
import com.example.readers.model.Reader;

import java.util.List;

public interface ReaderService {
    Reader createReader(Reader reader);

    Reader updateReader(Reader reader, Long id) throws ResourceNotFoundException;

    List<Reader> getAllReaders();

    Reader getReaderById(Long Id) throws ResourceNotFoundException;

    void deleteReader(Long id) throws ResourceNotFoundException;
}
