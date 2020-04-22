package me.d4ve.iot.homeenv.service;

import me.d4ve.iot.homeenv.model.EnvironmentalData;
import me.d4ve.iot.homeenv.repository.EnvironmentalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironementalDataService implements IEnvironmentalDataService {

    @Autowired
    private EnvironmentalDataRepository repository;

    @Override
    public List<EnvironmentalData> findAll() {
        var data = (List<EnvironmentalData>) repository.findAll();;
        return data;
    }

    @Override
    public void insert(EnvironmentalData data) {
        repository.save(data);
    }
}
