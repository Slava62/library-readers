package com.example.readers.service;
import java.util.List;
import java.util.Optional;

import com.example.readers.exception.ResourceNotFoundException;
import com.example.readers.model.Reader;
import com.example.readers.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class LibraryReaderService implements ReaderService {


    @Autowired
    private ReaderRepository readerRepository;


    @Override
    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Reader updateReader(Reader reader) throws ResourceNotFoundException {
        Optional <Reader> readerDb = this.readerRepository.findById(reader.getId());

        if (readerDb.isPresent()) {
            Reader readerUpdate = readerDb.get();
            readerUpdate.setId(reader.getId());
            readerUpdate.setName(reader.getName());
            readerUpdate.setCardid(reader.getCardid());
            readerRepository.save(readerUpdate);
            return readerUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + reader.getId().toString());
        }
    }

    @Override
    public List <Reader> getAllReaders() {
        return this.readerRepository.findAll();
    }

    @Override
    public Reader getReaderById(Long readerId) throws ResourceNotFoundException {

        Optional <Reader> readerDb = this.readerRepository.findById(readerId);

        if (readerDb.isPresent()) {
            return readerDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + readerId);
        }
    }

    @Override
    public void deleteReader(Long readerId) throws ResourceNotFoundException {
        Optional <Reader> productDb = this.readerRepository.findById(readerId);

        if (productDb.isPresent()) {
            this.readerRepository.delete(productDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + readerId);
        }

    }
}
