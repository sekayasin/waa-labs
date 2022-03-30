package me.sekayasin.lab1.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListMapper<T, E> {
    @Autowired
    ModelMapper modelMapper;

    public List<?> mapList(List<T> list, E convertTo) {
        return list
                .stream()
                .map(element -> modelMapper.map(element, convertTo.getClass()))
                .collect(Collectors.toList());
    }
}
